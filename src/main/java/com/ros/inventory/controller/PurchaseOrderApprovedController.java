package com.ros.inventory.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.ros.inventory.Repository.SupplierRepository;
import com.ros.inventory.controller.dto.ApprovedDto;
import com.ros.inventory.entities.OrderStatus;
import com.ros.inventory.entities.Supplier;
import com.ros.inventory.serviceImpl.PurchaseOrderApprovedManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
	private PurchaseOrderApprovedManager purchaseOrderApprovedManager;

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

	@GetMapping("/PurchaseOrder/{purchasedId}")
	@ResponseBody
	@Operation(summary = "Showing the Product Items in the PurchaseOrder ApproveSection ")
	public ResponseEntity<?> showproduct(@PathVariable("purchasedId") UUID purchasedId) {

		ResponseEntity response;
		try {
			response = new ResponseEntity(poapproved.showProduct(purchasedId), HttpStatus.OK);
		} catch (InventoryException e) {
			response = new ResponseEntity(e.getMessage(), HttpStatus.OK);
			e.printStackTrace();
		}
		return response;

	}
	/* ..............Showing Delivery Details............. */
	@GetMapping("/Delivery/{purchasedId}")
	@ResponseBody
	@Operation(summary = "Showing the Deleiver Details in the PurchaseOder ApproveSection")
	public ResponseEntity<?> showDelivery(@PathVariable("purchasedId") UUID purchasedId) {
		ResponseEntity response;

		try {

			response = new ResponseEntity(poapproved.showDelivery(purchasedId), HttpStatus.OK);

		} catch (InventoryException e) {

			response = new ResponseEntity(e.getMessage(), HttpStatus.OK);

			e.printStackTrace();

		}

		return response;

	}



	/* ..............Showing Invoice Details............. */
	@GetMapping("/Invoice/{purchasedId}")

	@ResponseBody

	@Operation(summary = "Showing the Invoice Details in the PurchaseOrder ApproveSection ")

	public ResponseEntity<?> showInvoice(@PathVariable("purchasedId") UUID purchasedId) {

		ResponseEntity response;

		try {

			response = new ResponseEntity(poapproved.showInvoice(purchasedId), HttpStatus.OK);

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
		return purchaseOrderApprovedManager.approvedTotal();
	}


	//Drafts information
	/*@GetMapping(value = "/getApproved")
	@Operation(summary = "info of all drafts")
	public List<ApprovedDto> getApproved() {
		return purchaseOrderApprovedManager.getApproved();
	}

	 */
}
