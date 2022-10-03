package com.ros.inventory.controller;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import com.ros.inventory.Exception.InventoryException;
import com.ros.inventory.Repository.PurchaseRepository;
import com.ros.inventory.service.IPurchaseOrderSiteTransferManager;
import io.swagger.v3.oas.annotations.Operation;
@RestController
@RequestMapping("/purchase/sitetransfer")
@CrossOrigin("*")
public class PurchaseOrderSiteTransferController {
	@Autowired
	private IPurchaseOrderSiteTransferManager sitetransfer;
	@Autowired
	private PurchaseRepository pRepo;
	/*Showing the Details which are present in SiteTransfer Section */
	@GetMapping("/view")
	@ResponseBody
	@Operation(summary = "Showing the internal supplier details in the SiteTransfer Section")
	public ResponseEntity<?> show()
	{
		ResponseEntity response;
		try
		{
			response = new ResponseEntity(sitetransfer.getAllBySupplierType("Internal"),HttpStatus.OK);
		}
		catch (InventoryException e)
		{
			response = new ResponseEntity(e.getMessage(), HttpStatus.OK);
			e.printStackTrace();
		}
		return response;
	}
	/*...............Showing the Product Details.................. */
	@GetMapping("/viewpurchaseorder/{id}")
	@ResponseBody
	@Operation(summary = "Showing the Product Details in the SiteTransfer Section")
	public ResponseEntity<?> showproduct(@Param("id") UUID purchaseId)
	{
		ResponseEntity response;
		try
		{
			response = new ResponseEntity(sitetransfer.showProduct(purchaseId),HttpStatus.OK);
		}
		catch (InventoryException e)
		{
			response = new ResponseEntity(e.getMessage(), HttpStatus.OK);
			e.printStackTrace();
		}
		return response;
	}
	/*..............Showing Delivery Details.............*/
	@GetMapping("/delivery/{id}")
	@ResponseBody
	@Operation(summary = "Showing the Delivery Details in the SiteTransfer Section")
	public ResponseEntity<?> showDelivery(@Param("id") UUID purchaseId)
	{
		ResponseEntity response;
		try
		{
			response = new ResponseEntity(sitetransfer.showDelivery(purchaseId),HttpStatus.OK);
		}
		catch (InventoryException e)
		{
			response = new ResponseEntity(e.getMessage(), HttpStatus.OK);
			e.printStackTrace();
		}
		return response;
	}
	/*..............Showing Invoice Details.............*/
	@GetMapping("/invoice/{id}")
	@ResponseBody
	@Operation(summary = "Showing the Invoice Details in the SiteTransfer Section")
	public ResponseEntity<?> showInvoice(@Param("id") UUID purchaseId)
	{
		ResponseEntity response;
		try
		{
			response = new ResponseEntity(sitetransfer.showInvoice(purchaseId),HttpStatus.OK);
		}
		catch (InventoryException e)
		{
			response = new ResponseEntity(e.getMessage(), HttpStatus.OK);
			e.printStackTrace();
		}
		return response;
	}
	/*..............Showing Attachments Details.............*/
	@GetMapping("/attachments/{id}")
	@ResponseBody
	@Operation(summary = "Showing the Attachment Details in the SiteTransfer Section")
	public ResponseEntity<?> showAttachments(@Param("id") UUID purchaseId)
	{
		ResponseEntity response;
		try
		{
			response = new ResponseEntity(sitetransfer.showAttachments(purchaseId),HttpStatus.OK);
		}
		catch (InventoryException e)
		{
			response = new ResponseEntity(e.getMessage(), HttpStatus.OK);
			e.printStackTrace();
		}
		return response;
	}
}
