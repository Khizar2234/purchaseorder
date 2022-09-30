package com.ros.inventory.controller;

import com.ros.inventory.Repository.PurchaseRepository;
import com.ros.inventory.entities.PurchaseOrder;
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

import java.util.List;


@RestController
@RequestMapping("/purchase/rejected")
@CrossOrigin("*")
public class PurchaseOrderRejectedController {
	@Autowired
    private IPurchaseOrderRejectedManager poRejected;

	@Autowired
	private PurchaseRepository purchaseRepository;

	@GetMapping("/view")
	   @ResponseBody
	   public ResponseEntity<?> show()
	   {
		   ResponseEntity response;
			   try
	   			{
				   response = new ResponseEntity(poRejected.showByStatus(),HttpStatus.OK);
	   			}
			   catch (InventoryException e)
				{
					response = new ResponseEntity(e.getMessage(), HttpStatus.OK);
					e.printStackTrace();
				}
				return response;	
	   }

	@GetMapping("view/total")
	@Operation(summary = "View total amount of submitted orders")
	public double showTotal() throws InventoryException {
		List<PurchaseOrder> purchaseOrderList = purchaseRepository.showByStatus("submited");
		double total = 0.0;

		for (PurchaseOrder purchaseOrder : purchaseOrderList) {
			total = total + purchaseOrder.getTotalAmount();
		}

		return total;
	}


}
