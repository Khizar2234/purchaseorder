package com.ros.inventory.controller;

import java.util.ArrayList;
import java.util.List;

import com.ros.inventory.Repository.SupplierRepository;
import com.ros.inventory.controller.dto.ApprovedDto;
import com.ros.inventory.entities.OrderStatus;
import com.ros.inventory.entities.Supplier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ros.inventory.Exception.InventoryException;
import com.ros.inventory.Repository.PurchaseRepository;
import com.ros.inventory.entities.PurchaseOrder;
import com.ros.inventory.service.IPurchaseOrderApprovedManager;

import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping("/purchase/approved")
@CrossOrigin("*")
public class PurchaseOrderApprovedController {
	@Autowired
	private IPurchaseOrderApprovedManager poapproved;
	@Autowired
	private PurchaseRepository pRepo;

	@Autowired
	private SupplierRepository supplierRepository;
	@GetMapping("/view")
	@ResponseBody
	@Operation(summary = "Showing the Items Which are in Approve Section")
	public ResponseEntity<?> showByStatus() {
		ResponseEntity response;
		try {
			response = new ResponseEntity(poapproved.showByStatus(), HttpStatus.OK);

		} catch (InventoryException e) {
			response = new ResponseEntity(e.getMessage(), HttpStatus.OK);
			e.printStackTrace();
		}
		return response;
	}

	/* ...............Showing the Product Details.................. */
	@GetMapping("/PurchaseOrder")
	@ResponseBody
	@Operation(summary = "Showing the Product Items in the PurchaseOrder ApproveSection ")
	public ResponseEntity<?> showproduct() {
		ResponseEntity response;
		try {
			response = new ResponseEntity(poapproved.showProduct(), HttpStatus.OK);
		} catch (InventoryException e) {
			response = new ResponseEntity(e.getMessage(), HttpStatus.OK);
			e.printStackTrace();
		}
		return response;
	}

	/* ..............Showing Delivery Details............. */
	@GetMapping("/Delivery")
	@ResponseBody
	@Operation(summary = "Showing the Deleiver Details in the PurchaseOder ApproveSection")
	public ResponseEntity<?> showDelivery() {
		ResponseEntity response;
		try {
			response = new ResponseEntity(poapproved.showDelivery(), HttpStatus.OK);
		} catch (InventoryException e) {
			response = new ResponseEntity(e.getMessage(), HttpStatus.OK);
			e.printStackTrace();
		}
		return response;
	}

	/* ..............Showing Invoice Details............. */
	@GetMapping("/Invoice")
	@ResponseBody
	@Operation(summary = "Showing the Invoice Details in the PurchaseOrder ApproveSection ")
	public ResponseEntity<?> showInvoice() {
		ResponseEntity response;
		try {
			response = new ResponseEntity(poapproved.showInvoice(), HttpStatus.OK);
		} catch (InventoryException e) {
			response = new ResponseEntity(e.getMessage(), HttpStatus.OK);
			e.printStackTrace();
		}
		return response;
	}

	@PutMapping("/mark_exported")
	@ResponseBody
	public ResponseEntity<?> setExported() {
		ResponseEntity response;
		try {
			response = new ResponseEntity(poapproved.setExported(), HttpStatus.OK);
		} catch (InventoryException e) {
			response = new ResponseEntity(e.getMessage(), HttpStatus.OK);
			e.printStackTrace();
		}
		return response;
	}

	@GetMapping("view/total")
	@Operation(summary = "View total amount of approved orders")
	public double showTotal() throws InventoryException {
		List<PurchaseOrder> purchaseOrderList = pRepo.showByStatus("approved");
		double total = 0.0;

		for (PurchaseOrder purchaseOrder : purchaseOrderList) {
			total = total + purchaseOrder.getTotalAmount();
		}

		return total;
	}


	//Drafts information
	@GetMapping(value = "/getApproved")
	@Operation(summary = "info of all drafts")
	public List<ApprovedDto> getApproved() {
		List<PurchaseOrder> purchaseOrderList = new ArrayList<>();

		List<ApprovedDto> approvedDtoList = new ArrayList<>();

		purchaseOrderList = pRepo.getAllByPurchaseOrderStatus(OrderStatus.approved);

		for (PurchaseOrder order : purchaseOrderList) {
			ApprovedDto approved = new ApprovedDto();

			approved.setPurchasedNumber(order.getPurchasedId());
			approved.setPurchaseDate(String.valueOf(order.getPurchaseOrderDate()));

			Supplier supplier = supplierRepository.getBySupplierId(order.getSupplier().getSupplierId());

			approved.setSupplierName(supplier.getSupplierBasic().getSupplierBusinessName());
			approved.setSupplierType(String.valueOf(supplier.getSupplierType()));
			approved.setValue(order.getTotalAmount());
			approved.setStatus(order.getPurchaseOrderStatus());

			approvedDtoList.add(approved);
		}

		return approvedDtoList;
	}

}
