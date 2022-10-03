package com.ros.inventory.service;

import java.util.List;
import java.util.UUID;

import com.ros.inventory.Exception.InventoryException;
import com.ros.inventory.controller.dto.DraftsDto;
import com.ros.inventory.entities.PurchaseOrder;

public interface IPurchasedOrderSubmittedManager {
	PurchaseOrder save(PurchaseOrder purchase)throws InventoryException;
	
	PurchaseOrder updatePurchase(PurchaseOrder purchase)throws InventoryException;
	
	List<DraftsDto> showByStatus()throws InventoryException;

	PurchaseOrder delete(UUID number)throws InventoryException;
	
	String approveSinglePurchaseOrder(UUID purchasedId) throws InventoryException;
	
	String rejectSinglePurchaseOrder(UUID purchasedId) throws InventoryException;

	String bulkApprove(List<DraftsDto> bulkapprove) throws InventoryException;

	String bulkReject(List<DraftsDto> bulkreject) throws InventoryException;

}
