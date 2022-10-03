package com.ros.inventory.serviceImpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ros.inventory.Exception.InventoryException;
import com.ros.inventory.Repository.ProductRepository;
import com.ros.inventory.Repository.PurchaseRepository;
import com.ros.inventory.controller.dto.ApproveViewDto;
import com.ros.inventory.controller.dto.DeliveryDto;
import com.ros.inventory.controller.dto.DraftsDto;
import com.ros.inventory.controller.dto.InvoicePDto;
import com.ros.inventory.controller.dto.ProductPDto;
import com.ros.inventory.controller.dto.RejectedDto;
import com.ros.inventory.controller.dto.SiTeTransfersDto;
import com.ros.inventory.entities.Product;
import com.ros.inventory.entities.PurchaseOrder;
import com.ros.inventory.mapper.ApprovedMapper;
import com.ros.inventory.mapper.ApprovedViewMapper;
import com.ros.inventory.mapper.DeliverMapper;
import com.ros.inventory.mapper.InvoicePMapper;
import com.ros.inventory.mapper.ProductPMapper;
import com.ros.inventory.mapper.PurchaseOrderMapper;
import com.ros.inventory.mapper.SiteTransfersPurchaseMapper;
import com.ros.inventory.service.IPurchaseOrderSiteTransferManager;

@Service
public class PurchaseOrderSiteTransferManager implements IPurchaseOrderSiteTransferManager {

	@Autowired
	private PurchaseRepository purchaseRepo;
	@Autowired
	private SiteTransfersPurchaseMapper purchaseMapper;

	@Autowired
	private ApprovedViewMapper apMapper;
	@Autowired
	private ProductRepository productRepo;
	@Autowired
	private ProductPMapper productpmapper;

	@Autowired
	private DeliverMapper dmapper;

	@Autowired
	private InvoicePMapper imapper;

	@Override
	public List<SiTeTransfersDto> showByStatus() throws InventoryException {
		// TODO Auto-generated method stub
		List<PurchaseOrder> purchaseFromDB = purchaseRepo.showByStatus("exported");

		if (purchaseFromDB == null || purchaseFromDB.size() == 0) {
			throw new InventoryException(" No Site Transfer Is Present");
		}

		List<SiTeTransfersDto> transferDto = new ArrayList<SiTeTransfersDto>();
		for (PurchaseOrder p : purchaseFromDB) {
			SiTeTransfersDto pd = purchaseMapper.convertToSiTeTransfersDto(p);
			transferDto.add(pd);
		}

		return transferDto;
	}

	@Override
	public List<ApproveViewDto> showApprove() throws InventoryException {
		List<PurchaseOrder> purchaseFromDB = purchaseRepo.getAll();

		if (purchaseFromDB == null || purchaseFromDB.size() == 0) {
			throw new InventoryException(" No PurchaseOrder Is Present");
		}

		List<ApproveViewDto> approvedviewDto = new ArrayList<ApproveViewDto>();

		for (PurchaseOrder p : purchaseFromDB) {
			ApproveViewDto pd = apMapper.convertToApproveViewDto(p);
			approvedviewDto.add(pd);
		}

		return approvedviewDto;

	}

	/* ......Showing the Product Details......................... */
	@Override
	public List<ProductPDto> showProduct() throws InventoryException {
		// TODO Auto-generated method stub
		List<Product> productFromDB = productRepo.getAll();
		if (productFromDB == null || productFromDB.size() == 0) {
			throw new InventoryException(" No PurchaseOrder Is Present");
		}
		List<ProductPDto> productPDto = new ArrayList<ProductPDto>();
		for (Product p : productFromDB) {
			ProductPDto pd = productpmapper.convertToPurchasePDto(p);
			productPDto.add(pd);
		}

		return productPDto;
	}
	/*
	 * .......................Showing Delivery Details............................
	 */

	@Override
	public List<DeliveryDto> showDelivery() throws InventoryException {
		// TODO Auto-generated method stub
		List<Product> purchaseFromDB = productRepo.getAll();
		if (purchaseFromDB == null || purchaseFromDB.size() == 0) {
			throw new InventoryException(" No PurchaseOrder Is Present");
		}
		List<DeliveryDto> deliverDto = new ArrayList<DeliveryDto>();
		for (Product p : purchaseFromDB) {
			DeliveryDto pd = dmapper.convertToDeliveryDto(p);
			// .convertToDeliveryDto(p);
			deliverDto.add(pd);
		}

		return deliverDto;
	}

	/*
	 * ............................Showing Invoice
	 * Details...........................
	 */

	@Override
	public List<InvoicePDto> showInvoice() throws InventoryException {
		// TODO Auto-generated method stub
		List<Product> productFromDB = productRepo.getAll();
		if (productFromDB == null || productFromDB.size() == 0) {
			throw new InventoryException(" No PurchaseOrder Is Present");
		}
		List<InvoicePDto> invoiceDto = new ArrayList<InvoicePDto>();
		for (Product p : productFromDB) {
			InvoicePDto pd = imapper.convertToInvoicePDto(p);
			invoiceDto.add(pd);
		}

		return invoiceDto;
	}

}