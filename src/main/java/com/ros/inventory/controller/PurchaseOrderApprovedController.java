package com.ros.inventory.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

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
import com.ros.inventory.controller.dto.DraftsDto;
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

}
