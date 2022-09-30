package com.ros.inventory.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.ros.inventory.Repository.PurchaseRepository;
import com.ros.inventory.Repository.SupplierRepository;
import com.ros.inventory.entities.OrderStatus;
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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ros.inventory.Exception.InventoryException;
import com.ros.inventory.controller.dto.DraftsDto;
import com.ros.inventory.entities.PurchaseOrder;
import com.ros.inventory.entities.Supplier;
import com.ros.inventory.service.IPurchaseOrderManager;
import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping("/purchase/drafts")
@CrossOrigin("*")

public class PurchasedOrderDraftsController
{
	@Autowired
	private  IPurchaseOrderManager purchaseorder;

	@Autowired
	private PurchaseRepository purchaseRepository;

	@Autowired
	private SupplierRepository supplierRepository;

/* .............. Saving the purchaseOrder in Drafts ........*/
	@PostMapping("/add")
	@ResponseBody
	@Operation(summary = "Adding the Items In Drafts")
	public ResponseEntity<?> addItem(@RequestBody PurchaseOrder purchase )
	{
		ResponseEntity<?> response;
		try
		{
			response=new ResponseEntity<Object>(purchaseorder.save(purchase),HttpStatus.OK);
				
		}
		catch (InventoryException e)
		{
			response = new ResponseEntity(e.getMessage(), HttpStatus.OK);
			e.printStackTrace();
		}
		return response;	
	}

/*.............Updating the purchaseOrder in Drafts...............*/
	
	   @PutMapping("/update")
	   @ResponseBody
	   @Operation(summary = "Updating the Items In Drafts")
	   public ResponseEntity<?> update(@RequestBody  PurchaseOrder purchase)
	   {
		   ResponseEntity response;
		   try
		   {
			   response = new ResponseEntity<Object>(purchaseorder.updatePurchase(purchase),HttpStatus.OK);
			
		   }
		   catch (InventoryException e)
			{
				response = new ResponseEntity(e.getMessage(), HttpStatus.OK);
				e.printStackTrace();
			}
			return response;	
	   }
	
/*....................View the Data in Drafts.........*/
	   
	   @GetMapping("/view")
	   @ResponseBody
	   @Operation(summary = "Viewing  the Items In Drafts")
	   public ResponseEntity<?> show()
	   {
		   ResponseEntity response;
			   try
	   			{
				   response = new ResponseEntity(purchaseorder.showByStatus(),HttpStatus.OK);
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
		List<PurchaseOrder> purchaseOrderList = purchaseRepository.showByStatus("drafts");
		double total = 0.0;

		for (PurchaseOrder purchaseOrder : purchaseOrderList) {
			total = total + purchaseOrder.getTotalAmount();
		}

		return total;
	}
/*...............Deleting the data in Drafts............*/	
	   @DeleteMapping("/delete/{id}")
	   @ResponseBody
	   @Operation(summary = "Deleting the Items In Drafts")
	   public ResponseEntity<?> delete(@PathVariable(name="id") UUID id)
	   {
		   ResponseEntity response;
		   try
		   {
			   response = new ResponseEntity(purchaseorder.delete(id),HttpStatus.OK);
		   }
		   catch (InventoryException e)
			{
				response = new ResponseEntity(e.getMessage(), HttpStatus.OK);
				e.printStackTrace();
			}
			return response;	
	   }
	
/*............DeleteAll the data in the database...........*/
	   @DeleteMapping("/deleteList")
	   @ResponseBody
	   @Operation(summary = "Delete the List Of Items In Drafts")
	   public ResponseEntity<?> deleteALL(@RequestBody List<PurchaseOrder> pr)
	   {
		   ResponseEntity response;
		   try
		   {
			   response = new ResponseEntity(purchaseorder.deleteALL(pr),HttpStatus.OK);
		   }
		   catch (InventoryException e)
			{
				response = new ResponseEntity(e.getMessage(), HttpStatus.OK);
				e.printStackTrace();
			}
			return response;	
	   }
/*...................Bulk Submit the purchase order details........................*/
	@PutMapping("/bulksubmitted")
	@ResponseBody
	@Operation(summary = "Submitting All the Items In Drafts")
	public ResponseEntity<?> bulkSubmit(@RequestBody List<DraftsDto> bulksubmit)
			throws InventoryException {
		ResponseEntity<?> response;

		//System.out.println(close_stock_bulk);

		response = new ResponseEntity<Object>(purchaseorder.bulkSubmit(bulksubmit), HttpStatus.OK);

		return response;
	}

	//Drafts information
	@GetMapping(value = "/getDrafts")
	@Operation(summary = "info of all drafts")
	public List<DraftsDto> getDrafts() {
		List<PurchaseOrder> purchaseOrderList = new ArrayList<>();

		List<DraftsDto> draftsDtoList = new ArrayList<>();

		purchaseOrderList = purchaseRepository.getAllByPurchaseOrderStatus(OrderStatus.drafts);

		for (PurchaseOrder order : purchaseOrderList) {
			DraftsDto draft = new DraftsDto();

			draft.setPurchasedNumber(order.getPurchasedId());
			draft.setPurchaseDate(String.valueOf(order.getPurchaseOrderDate()));

			Supplier supplier = supplierRepository.getBySupplierId(order.getSupplier().getSupplierId());

			draft.setSupplierName(supplier.getSupplierBasic().getSupplierBusinessName());
			draft.setSupplierType(String.valueOf(supplier.getSupplierType()));
			draft.setValue(order.getTotalAmount());

			draftsDtoList.add(draft);
		}

		return draftsDtoList;
	}

}
