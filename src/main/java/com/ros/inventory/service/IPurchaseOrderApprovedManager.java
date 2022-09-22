package com.ros.inventory.service;

import java.util.List;

import org.springframework.util.MultiValueMap;

import com.ros.inventory.Exception.InventoryException;
import com.ros.inventory.controller.dto.ApproveViewDto;
import com.ros.inventory.controller.dto.ApprovedDto;
import com.ros.inventory.controller.dto.AttachmentsDto;
import com.ros.inventory.controller.dto.DeliveryDto;
import com.ros.inventory.controller.dto.DraftsDto;
import com.ros.inventory.controller.dto.InvoicePDto;
import com.ros.inventory.controller.dto.ProductDto;
import com.ros.inventory.controller.dto.ProductPDto;
import com.ros.inventory.controller.dto.RejectedDto;
import com.ros.inventory.entities.PurchaseOrder;

public interface IPurchaseOrderApprovedManager {
	//List<ApprovedDto> show()throws InventoryException;
	
	//showing the product details
	List<ProductPDto> showProduct() throws InventoryException;
	
	List<ApprovedDto>showByStatus() throws InventoryException;
	
	//showing approved product details in the purchase order
	List<ApproveViewDto> showApprove() throws InventoryException;
	
	//showing the delivery detail
	List<DeliveryDto>showDelivery() throws InventoryException;
	//showing invoice details
	List<InvoicePDto> showInvoice() throws InventoryException;

	String setExported() throws InventoryException;
	
	//List<AttachmentsDto>showAttachments()throws InventoryException;

	
	

}
