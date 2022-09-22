package com.ros.inventory.serviceImpl;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ros.inventory.Exception.InventoryException;
import com.ros.inventory.Repository.InvoiceRepository;
import com.ros.inventory.Repository.PurchaseRepository;
import com.ros.inventory.controller.dto.InvoiceDto;
import com.ros.inventory.controller.dto.InvoiceDto2;
import com.ros.inventory.controller.dto.InvoiceProductDto;
import com.ros.inventory.entities.Invoice;
import com.ros.inventory.entities.OrderStatus;
import com.ros.inventory.entities.Product;
import com.ros.inventory.entities.PurchaseOrder;
import com.ros.inventory.mapper.InvoiceMapper;
//import com.ros.inventory.mapper.ProductMapper;
import com.ros.inventory.service.IInvoiceManager;

@Service
public class InvoiceManager implements IInvoiceManager {
	@Autowired
	InvoiceRepository invoiceRepo;
	@Autowired
	InvoiceMapper invoiceMapper;
	@Autowired
	PurchaseRepository perchase;

	@Override
	public List<InvoiceDto> getInvoice() throws InventoryException {
		// TODO Auto-generated method stub

		List<PurchaseOrder> po = perchase.findAll();
		List<InvoiceDto> i = new ArrayList();
		if(po.size() !=0)
		{

		List<PurchaseOrder> approved_purchase = po.stream()
				.filter(x -> x.getPurchaseOrderStatus() == OrderStatus.approved).collect(Collectors.toList());

		if (po == null || po.size() == 0) {
			throw new InventoryException(" No Purchase order Is aaproved");
		}
		
		for (PurchaseOrder p : approved_purchase) {
			InvoiceDto d = invoiceMapper.convertToInvoiceDto(p);
			i.add(d);
		}
		return i;
		}
		return i;
	}

	// view or download invoice for particular purchase order id
	@Override
	public InvoiceDto2 viewInvoice(UUID id) throws InventoryException {
		// TODO Auto-generated method stub
		Optional<Invoice> invoiceFromDB = invoiceRepo.findById(id);
		InvoiceDto2 invoice = new InvoiceDto2();
		if(invoiceFromDB.isPresent()) {
		List<InvoiceProductDto> ls = new ArrayList();
		if (invoiceFromDB == null) {
			throw new InventoryException(" No invoice Is Present");
		}

		// status check
		OrderStatus isPresent = invoiceFromDB.get().getPurchase().getPurchaseOrderStatus();

		if (isPresent == OrderStatus.approved) {

			UUID purchase_id = invoiceFromDB.get().getPurchase().getPurchasedId();
			LocalDate purchaseDate = invoiceFromDB.get().getPurchase().getPurchaseOrderDate();
			String supplierName = invoiceFromDB.get().getPurchase().getSupplier().getSupplierBasic()
					.getSupplierBusinessName();
			List<Product> product_list = invoiceFromDB.get().getPurchase().getSupplier().getProducts();
			invoice.setSupplierName(
					invoiceFromDB.get().getPurchase().getSupplier().getSupplierBasic().getSupplierBusinessName());
			invoice.setArea(invoiceFromDB.get().getPurchase().getSupplier().getSupplierAddress().getArea());
			invoice.setCity(invoiceFromDB.get().getPurchase().getSupplier().getSupplierAddress().getCity());
			invoice.setCountry(invoiceFromDB.get().getPurchase().getSupplier().getSupplierAddress().getCountry());
			invoice.setPincode(invoiceFromDB.get().getPurchase().getSupplier().getSupplierAddress().getPincode());
			invoice.setState(invoiceFromDB.get().getPurchase().getSupplier().getSupplierAddress().getState());
			invoice.setStreet(invoiceFromDB.get().getPurchase().getSupplier().getSupplierAddress().getStreet());
			invoice.setInvoiceDate(invoiceFromDB.get().getPurchase().getPurchaseOrderDate());

			double vat = 0, sub_total = 0;
			for (Product s : product_list) {
				InvoiceProductDto invoice_product = new InvoiceProductDto();
				invoice_product.setProductName(s.getProductName());
				invoice_product.setPricePerUnit(s.getPricePerUnit());
				invoice_product.setProductCode(s.getProductCode());
				invoice_product.setProductType(s.getProductType());
				invoice_product.setQtyReceived(s.getQtyReceived());
				invoice_product.setUnitMeasurement(s.getUnitMeasurement());
				invoice_product.setVat(s.getProductVatTax());
				double netprice = s.getQtyReceived() * s.getPricePerUnit();
				invoice_product.setNetPrice(netprice);
				vat += (netprice * s.getProductVatTax()) / 100;
				sub_total += netprice;
				ls.add(invoice_product);
			}
			invoice.setIpd(ls);

			invoice.setRestaurantName(invoiceFromDB.get().getPurchase().getSupplier().getRestaurantName());
			invoice.setRestaurantCity(invoiceFromDB.get().getRestaurant().getResturantAddress().getRestaurantCity());
			invoice.setRestaurantCountry(invoiceFromDB.get().getRestaurant().getResturantAddress().getRestaurantCountry());
			invoice.setRestaurantMob(invoiceFromDB.get().getRestaurant().getResturantAddress().getRestaurantMob());
			invoice.setRestaurantPin(invoiceFromDB.get().getRestaurant().getResturantAddress().getRestaurantPin());
			invoice.setRestaurantState(invoiceFromDB.get().getRestaurant().getResturantAddress().getRestaurantState());
			invoice.setRestaurantTel(invoiceFromDB.get().getRestaurant().getResturantAddress().getRestaurantTel());
			invoice.setRestaurantStreet(invoiceFromDB.get().getRestaurant().getResturantAddress().getRestaurantStreet());
			invoice.setVat(vat);
			invoice.setTotal(sub_total + vat);

		}
		return invoice;}
		return invoice;

	}

	public List<InvoiceDto> monthlyviewInvoice(String d1) throws InventoryException {
		// TODO Auto-generated method stub
		LocalDate date = LocalDate.parse(d1);
		LocalDate startdate = LocalDate.of(date.getYear(), date.getMonth(), date.getDayOfMonth());
		LocalDate enddate = LocalDate.of(date.getYear(), date.getMonth(), 30);

		List<PurchaseOrder> po = perchase.findAll();
		List<InvoiceDto> i = new ArrayList();
		if(po.size() !=0) {
		List<PurchaseOrder> approved_purchase = po.stream()
				.filter(x -> x.getPurchaseOrderStatus() == OrderStatus.approved
						&& x.getPurchaseOrderDate().isAfter(startdate) && x.getPurchaseOrderDate().isBefore(enddate)
						|| x.getPurchaseOrderDate().isEqual(enddate))
				.collect(Collectors.toList());

		if (po == null || po.size() == 0) {
			throw new InventoryException(" No Purchase order Is aaproved");
		}
		
		for (PurchaseOrder p : approved_purchase) {
			InvoiceDto d = invoiceMapper.convertToInvoiceDto(p);
			i.add(d);
		}
		return i;}
		return i;
	}

	@Override
	public List<InvoiceDto> yearlyviewInvoice(int yyyy) throws InventoryException {
		// TODO Auto-generated method stub

		LocalDate startdate = LocalDate.of(yyyy, 01, 01);
		LocalDate enddate = LocalDate.of(yyyy, 12, 31);

		List<PurchaseOrder> po = perchase.findAll();
		List<InvoiceDto> i = new ArrayList();
		
		if(po.size() != 0) {
		
		List<PurchaseOrder> approved_purchase = po.stream()
				.filter(x -> x.getPurchaseOrderStatus() == OrderStatus.approved
						&& x.getPurchaseOrderDate().isAfter(startdate) && x.getPurchaseOrderDate().isBefore(enddate))
				.collect(Collectors.toList());

		if (po == null || po.size() == 0) {
			throw new InventoryException(" No Purchase order Is aaproved");
		}
	
		for (PurchaseOrder p : approved_purchase) {
			InvoiceDto d = invoiceMapper.convertToInvoiceDto(p);
			i.add(d);
		}
		return i;}
		return i;
	

	
	}
}