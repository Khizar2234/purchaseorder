package com.ros.inventory.service;

import java.time.LocalDate;
import java.util.Date;
//import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.util.MultiValueMap;

//import org.springframework.util.MultiValueMap;

import com.ros.inventory.Exception.InventoryException;
import com.ros.inventory.controller.dto.InvoiceDto;
import com.ros.inventory.controller.dto.InvoiceDto2;

//import org.springframework.util.MultiValueMap;

import com.ros.inventory.entities.Invoice;

public interface IInvoiceManager {

	List<InvoiceDto> getInvoice() throws InventoryException;

	InvoiceDto2 viewInvoice(UUID id) throws InventoryException;

	List<InvoiceDto> monthlyviewInvoice(String date) throws InventoryException;

	List<InvoiceDto> yearlyviewInvoice(int yyyy) throws InventoryException;

}