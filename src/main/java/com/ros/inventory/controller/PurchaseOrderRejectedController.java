package com.ros.inventory.controller;

import com.ros.inventory.Repository.PurchaseRepository;
import com.ros.inventory.Repository.SupplierRepository;
import com.ros.inventory.controller.dto.DraftsDto;
import com.ros.inventory.controller.dto.RejectedDto;
import com.ros.inventory.entities.OrderStatus;
import com.ros.inventory.entities.PurchaseOrder;
import com.ros.inventory.entities.Supplier;
import com.ros.inventory.serviceImpl.PurchaseOrderRejectedManager;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ros.inventory.Exception.InventoryException;
import com.ros.inventory.service.IPurchaseOrderRejectedManager;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@RestController
@RequestMapping("/purchase/rejected")
@CrossOrigin("*")
public class PurchaseOrderRejectedController {
	@Autowired
    private IPurchaseOrderRejectedManager poRejected;

	@Autowired
	private PurchaseRepository purchaseRepository;

	@Autowired
	private SupplierRepository supplierRepository;

	@Autowired
	private PurchaseOrderRejectedManager purchaseOrderRejectedManager;

	@GetMapping("/view")
	   @ResponseBody
	   public ResponseEntity<?> show()
	   {
		   ResponseEntity response;
		   response = new ResponseEntity(poRejected.getRejected(),HttpStatus.OK);
		   return response;
	   }

	@GetMapping("view/total")
	@Operation(summary = "View total amount of submitted orders")
	public double showTotal() throws InventoryException {
		return purchaseOrderRejectedManager.rejectedTotal();
	}

	//Drafts information
	/*@GetMapping(value = "/getRejected")
	@Operation(summary = "info of all drafts")
	public List<RejectedDto> getRejected() {
		return purchaseOrderRejectedManager.getRejected();
	}

	 */


}
