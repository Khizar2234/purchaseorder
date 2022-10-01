package com.ros.inventory.service;

import com.ros.inventory.Exception.InventoryException;
import com.ros.inventory.entities.Product;
import com.ros.inventory.entities.Supplier;

import java.util.List;
import java.util.UUID;

public interface IExternalSupplierManager {
  List<Supplier> getAll() throws InventoryException;

  List<Product> getProducts(UUID supplierId) throws InventoryException;

  Supplier get(UUID id) throws InventoryException;
}
