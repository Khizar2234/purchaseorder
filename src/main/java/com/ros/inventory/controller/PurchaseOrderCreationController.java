package com.ros.inventory.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;


import com.ros.inventory.Exception.InventoryException;
import com.ros.inventory.Repository.ProductRepository;
import com.ros.inventory.controller.dto.DraftsDto;
import com.ros.inventory.controller.dto.ProductPDto;
import com.ros.inventory.entities.PurchaseOrder;
import com.ros.inventory.service.ICreatePurchaseOrder;
import com.ros.inventory.service.IPurchaseOrderManager;

import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping("/purchase/create")
@CrossOrigin("*")
public class PurchaseOrderCreationController {
	
	@Autowired
	private  ICreatePurchaseOrder createOrder;
	   @GetMapping("/viewSuppliers")
	   @ResponseBody
	   @Operation(summary = "Viewing  the Suppliers ")
	   public ResponseEntity<?> show()
	   {
		   ResponseEntity response;
			   try
	   			{
				   response = new ResponseEntity(createOrder.showBySuppliers(),HttpStatus.OK);
	   			}
			   catch (InventoryException e)
				{
					response = new ResponseEntity(e.getMessage(), HttpStatus.OK);
					e.printStackTrace();
				}
				return response;	
	   }
	
	   @GetMapping("/view/products/{supplierName}")
	   @ResponseBody
	   @Operation(summary = "View all the Items present  In Submitted Section")
	   public ResponseEntity<?> show(@PathVariable("supplierName") String supplierName)
	   {
		   ResponseEntity response;
			   try
	   			{
				   response = new ResponseEntity(createOrder.getBySupplierName(supplierName),HttpStatus.OK);
	   			}
			   catch (InventoryException e)
				{
					response = new ResponseEntity(e.getMessage(), HttpStatus.OK);
					e.printStackTrace();
				}
				return response;	
	   }
		@PostMapping("/add/ProductsToDraft/{products}/{supplierName}")
		@ResponseBody
		@Operation(summary = "Adding the Products to Draft In Create Purchase Section")
		public ResponseEntity<?> addItemDraft(@RequestBody List<ProductPDto> products, @RequestParam String supplierName )
		{
			ResponseEntity<?> response;
			try
			{
				response=new ResponseEntity<Object>(createOrder.saveDraft(products,supplierName),HttpStatus.OK);
			}
			catch (InventoryException e)
			{
				response = new ResponseEntity(e.getMessage(), HttpStatus.OK);
				e.printStackTrace();
			}
			return response;	
		}
		@PostMapping("/add/ProductsToSubmit/{products}/{supplierName}")
		@ResponseBody
		@Operation(summary = "Adding the Products to Submit In Create Section")
		public ResponseEntity<?> addItemSubmit(@RequestBody List<ProductPDto> products, @RequestParam String supplierName )
		{
			ResponseEntity<?> response;
			try
			{
				response=new ResponseEntity<Object>(createOrder.saveSubmit(products,supplierName),HttpStatus.OK);
			}
			catch (InventoryException e)
			{
				response = new ResponseEntity(e.getMessage(), HttpStatus.OK);
				e.printStackTrace();
			}
			return response;	
		}

}
