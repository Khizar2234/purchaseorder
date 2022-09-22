package com.ros.inventory.service;

import java.util.List;
import java.util.UUID;

import com.ros.inventory.Exception.InventoryException;
import com.ros.inventory.controller.dto.DraftsDto;
import com.ros.inventory.entities.PurchaseOrder;

public interface IPurchaseOrderManager 
{

	PurchaseOrder save(PurchaseOrder purchase)throws InventoryException;
	
	PurchaseOrder updatePurchase(PurchaseOrder purchase)throws InventoryException;
	
	List<DraftsDto> showByStatus()throws InventoryException;

	PurchaseOrder delete(UUID number)throws InventoryException;

	String deleteALL(List<PurchaseOrder> pr) throws InventoryException;

	//List<PurchaseOrder> saveAll(PurchaseOrder purchase) throws InventoryException;
	String bulkSubmit(List<DraftsDto> bulksubmit) throws InventoryException;
	






}
