package com.ros.inventory.service;

import java.util.List;

import org.springframework.util.MultiValueMap;

import com.ros.inventory.Exception.InventoryException;
import com.ros.inventory.controller.dto.ProductPDto;
import com.ros.inventory.entities.PurchaseOrder;

public interface ICreatePurchaseOrder {

	List<String> showBySuppliers()throws InventoryException;

	List<ProductPDto> getBySupplierName(String supplierName)throws InventoryException;

	PurchaseOrder saveDraft(List<ProductPDto> products, String supplierName) throws InventoryException;
	
	PurchaseOrder saveSubmit(List<ProductPDto> products, String supplierName) throws InventoryException;

	

	

}
