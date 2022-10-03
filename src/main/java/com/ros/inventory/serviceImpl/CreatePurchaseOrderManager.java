package com.ros.inventory.serviceImpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ros.inventory.Exception.InventoryException;
import com.ros.inventory.Repository.PurchaseRepository;
import com.ros.inventory.Repository.SupplierRepository;
import com.ros.inventory.controller.dto.DraftsDto;
import com.ros.inventory.controller.dto.ProductPDto;
import com.ros.inventory.entities.OrderStatus;
import com.ros.inventory.entities.Product;
import com.ros.inventory.entities.PurchaseOrder;
import com.ros.inventory.entities.Supplier;
import com.ros.inventory.entities.TransferType;
import com.ros.inventory.mapper.ProductMapper;
import com.ros.inventory.mapper.ProductPMapper;
import com.ros.inventory.mapper.PurchaseOrderMapper;
import com.ros.inventory.service.ICreatePurchaseOrder;

@Service
public class CreatePurchaseOrderManager implements ICreatePurchaseOrder{


	@Autowired
	SupplierRepository suppRepo;
	
	  @Autowired
	  private ProductPMapper productMapper;
	  
	  @Autowired
		private PurchaseRepository purchaseRepo;
	  
	
	  
	@Override
	public List<String> showBySuppliers() throws InventoryException {
		// TODO Auto-generated method stub
		List<String> suppList=new ArrayList<>();
		List<Supplier> suppliersFromDB= suppRepo.findAll();
		for(Supplier supp:suppliersFromDB)
		{
			suppList.add(supp.getSupplierName());
		}
		return suppList;
	}
/*	@Override
	public List<ProductPDto> getBySupplierName(String supplierName) throws InventoryException {
		// TODO Auto-generated method stub
		List<Supplier> suppliersFromDB= suppRepo.getByName(supplierName);
		List<ProductPDto> products=new ArrayList<>();
		for(Supplier supp: suppliersFromDB){
			for(Product p : supp.getProducts()) {
				ProductPDto pdto = productMapper.convertToPurchasePDto(p);
				products.add(pdto);}
		}
		return products;
	}*/
	@Override
	public List<ProductPDto> getBySupplierName(String supplierName) throws InventoryException {
		// TODO Auto-generated method stub
		Supplier suppliersFromDB= suppRepo.getByName(supplierName);
		List<ProductPDto> products=new ArrayList<>();
		//for(Supplier supp: suppliersFromDB){
			for(Product p : suppliersFromDB.getProducts()) {
				ProductPDto pdto = productMapper.convertToPurchasePDto(p);
				products.add(pdto);}
		//}
		return products;
	}

	@Override
	public PurchaseOrder saveDraft(List<ProductPDto> products, String supplierName) throws InventoryException {
		// TODO Auto-generated method stub
		Supplier supplier=suppRepo.getByName(supplierName);
		List<Product> prodList=new ArrayList<>();
		double amount=0;
		for(ProductPDto prodDto: products)
		{
			prodList.add(productMapper.convertToProduct(prodDto));
			amount+=prodDto.getPricePerUnit()*prodDto.getQty();
		}
		supplier.setProducts(prodList);
		PurchaseOrder purchaseOrder=new PurchaseOrder();
		purchaseOrder.setTotalAmount(amount);
		purchaseOrder.setPurchaseOrderDate(java.time.LocalDate.now());
		purchaseOrder.setPurchaseOrderStatus(OrderStatus.drafts);
		purchaseOrder.setTransferType(TransferType.TransferIn);
		purchaseOrder.setSupplier(supplier);
		
		purchaseRepo.save(purchaseOrder);
		
		return purchaseOrder;
	}
	@Override
	public PurchaseOrder saveSubmit(List<ProductPDto> products, String supplierName) throws InventoryException {
		// TODO Auto-generated method stub
		Supplier supplier=suppRepo.getByName(supplierName);
		List<Product> prodList=new ArrayList<>();
		double amount=0;
		for(ProductPDto prodDto: products)
		{
			prodList.add(productMapper.convertToProduct(prodDto));
			amount+=prodDto.getPricePerUnit()*prodDto.getQty();
		}
		supplier.setProducts(prodList);
		PurchaseOrder purchaseOrder=new PurchaseOrder();
		purchaseOrder.setTotalAmount(amount);
		purchaseOrder.setPurchaseOrderDate(java.time.LocalDate.now());
		purchaseOrder.setPurchaseOrderStatus(OrderStatus.submited);
		purchaseOrder.setTransferType(TransferType.TransferIn);
		purchaseOrder.setSupplier(supplier);
		
		purchaseRepo.save(purchaseOrder);
		
		return purchaseOrder;
	}


	


}
