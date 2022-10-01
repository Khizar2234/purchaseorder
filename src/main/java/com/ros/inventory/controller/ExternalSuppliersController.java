package com.ros.inventory.controller;

import com.ros.inventory.Exception.InventoryException;
import com.ros.inventory.service.IExternalSupplierManager;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/suppliers")
@CrossOrigin("*")
public class ExternalSuppliersController {
  @Autowired
  private IExternalSupplierManager externalSupplierManager;

  @GetMapping("/view")
  @ResponseBody
  @Operation(summary = "Get all suppliers")
  public ResponseEntity<?> get() {
    ResponseEntity<?> response;
    try {
      response = new ResponseEntity(externalSupplierManager.getAll(), HttpStatus.OK);
    } catch (InventoryException e) {
      response = new ResponseEntity(e.getMessage(), HttpStatus.NOT_FOUND);
      e.printStackTrace();
    }
    return response;
  }

  @GetMapping("/{supplierId}")
  @ResponseBody
  @Operation(summary = "Get supplier details")
  public ResponseEntity<?> get(@Param("supplierId")
      UUID supplierId) {
    ResponseEntity<?> response;
    try {
      response = new ResponseEntity(externalSupplierManager.get(supplierId), HttpStatus.OK);
    } catch (InventoryException e) {
      response = new ResponseEntity(e.getMessage(), HttpStatus.NOT_FOUND);
      e.printStackTrace();
    }
    return response;
  }

  @GetMapping("/products/{supplierId}")
  @ResponseBody
  @Operation(summary = "Get all products by supplier")
  public ResponseEntity<?> getProducts(@Param("supplierId") UUID supplierId) {
    ResponseEntity<?> response;
    try {
      response = new ResponseEntity(externalSupplierManager.getProducts(supplierId), HttpStatus.OK);
    } catch (InventoryException e) {
      response = new ResponseEntity(e.getMessage(), HttpStatus.NOT_FOUND);
      e.printStackTrace();
    }
    return response;
  }
}
