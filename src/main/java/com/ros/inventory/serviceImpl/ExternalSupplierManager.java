package com.ros.inventory.serviceImpl;

import com.ros.inventory.Exception.InventoryException;
import com.ros.inventory.Repository.ProductRepository;
import com.ros.inventory.entities.Product;
import com.ros.inventory.entities.Supplier;
import com.ros.inventory.service.IExternalSupplierManager;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Collections;
import java.util.List;
import java.util.UUID;

public class ExternalSupplierManager implements IExternalSupplierManager {
  @Autowired
  ProductRepository productRepo;

  @Override
  public List<Supplier> getAll() throws InventoryException {
    List<Supplier> listOfSuppliers = productRepo.getAllSuppliers();
    if (listOfSuppliers == null) {
      throw new InventoryException("No Suppliers Available");
    }

    return listOfSuppliers;
  }

  @Override
  public List<Product> getProducts(UUID supplierId) throws InventoryException {
    List<Product> products = productRepo.getAll(supplierId);
    if (products == null) {
      throw new InventoryException("No products listed by supplier");
    }

    return products;
  }

  @Override
  public Supplier get(UUID id) throws InventoryException {
    Supplier supplier = productRepo.getSupplierDetails(id);
    if (supplier == null) {
      throw new InventoryException("No such supplier found");
    }

    return supplier;
  }
}
