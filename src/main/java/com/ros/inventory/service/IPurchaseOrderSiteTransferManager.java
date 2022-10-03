package com.ros.inventory.service;

import java.util.List;

import com.ros.inventory.Exception.InventoryException;
import com.ros.inventory.controller.dto.ApproveViewDto;
import com.ros.inventory.controller.dto.DeliveryDto;
import com.ros.inventory.controller.dto.DraftsDto;
import com.ros.inventory.controller.dto.InvoicePDto;
import com.ros.inventory.controller.dto.ProductPDto;
import com.ros.inventory.controller.dto.SiTeTransfersDto;

public interface IPurchaseOrderSiteTransferManager {
	List<SiTeTransfersDto> showByStatus()throws InventoryException;

	List<ApproveViewDto> showApprove() throws InventoryException;

	List<ProductPDto> showProduct() throws InventoryException;

	List<DeliveryDto> showDelivery() throws InventoryException;

	List<InvoicePDto> showInvoice() throws InventoryException;
}
