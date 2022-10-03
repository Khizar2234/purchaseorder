package com.ros.inventory.serviceImpl;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ros.inventory.Exception.InventoryException;
import com.ros.inventory.Repository.ProductRepository;
import com.ros.inventory.Repository.PurchaseRepository;
import com.ros.inventory.controller.dto.ApproveViewDto;
import com.ros.inventory.controller.dto.AttachmentsDto;
import com.ros.inventory.controller.dto.DeliveryDto;
import com.ros.inventory.controller.dto.DraftsDto;
import com.ros.inventory.controller.dto.InvoicePDto;
import com.ros.inventory.controller.dto.ProductPDto;
import com.ros.inventory.controller.dto.RejectedDto;
import com.ros.inventory.controller.dto.SiTeTransfersDto;
import com.ros.inventory.entities.Attachments;
import com.ros.inventory.entities.Product;
import com.ros.inventory.entities.PurchaseOrder;
import com.ros.inventory.mapper.ApprovedMapper;
import com.ros.inventory.mapper.ApprovedViewMapper;
import com.ros.inventory.mapper.AttachmentsMapper;
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
	@Autowired
	private AttachmentsMapper amapper;
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
	public List<SiTeTransfersDto> getAllBySupplierType(String supplierType) throws InventoryException {
		// TODO Auto-generated method stub
		List<PurchaseOrder> purchaseFromDB = purchaseRepo.getAllBySupplierType("Internal");
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
	public List<ProductPDto> showProduct(UUID purchasedId) throws InventoryException {
		// TODO Auto-generated method stub
		PurchaseOrder purchaseFromDB=purchaseRepo.getById(purchasedId);
		if (purchaseFromDB == null ) {
			throw new InventoryException(" No PurchaseOrder Is Present");
		}
		List<ProductPDto> products=new ArrayList<>();
		//for(PurchaseOrder order: purchaseFromDB)
		//{
		List<Product> prods=purchaseFromDB.getSupplier().getProducts();
		for(Product prod:prods)
		{
			products.add(productpmapper.convertToPurchasePDto(prod));
		}
		//}
		return products;
	}
	/*
	 * .......................Showing Delivery Details............................
	 */
	@Override
	public List<DeliveryDto> showDelivery(UUID purchasedId) throws InventoryException {
		// TODO Auto-generated method stub
		PurchaseOrder purchaseFromDB=purchaseRepo.getById(purchasedId);
		if (purchaseFromDB == null ) {
			throw new InventoryException(" No PurchaseOrder Is Present");
		}
		List<DeliveryDto> deliverDto=new ArrayList<>();
		//for(PurchaseOrder order: purchaseFromDB)
		//{
		List<Product> prods=purchaseFromDB.getSupplier().getProducts();
		for(Product prod:prods)
		{
			DeliveryDto pd = dmapper.convertToDeliveryDto(prod);
			//.convertToDeliveryDto(p);
			deliverDto.add(pd);
		}
		//}
		return deliverDto;
	}
	/*
	 * ............................Showing Invoice
	 * Details...........................
	 */
	@Override
	public List<InvoicePDto> showInvoice(UUID purchasedId) throws InventoryException {
		// TODO Auto-generated method stub
		PurchaseOrder purchaseFromDB=purchaseRepo.getById(purchasedId);
		if (purchaseFromDB == null ) {
			throw new InventoryException(" No PurchaseOrder Is Present");
		}
		List<InvoicePDto> invoiceDto=new ArrayList<>();
		//for(PurchaseOrder order: purchaseFromDB)
		//{
		List<Product> prods=purchaseFromDB.getSupplier().getProducts();
		for(Product prod:prods)
		{
			InvoicePDto pd = imapper.convertToInvoicePDto(prod);
			//.convertToDeliveryDto(p);
			invoiceDto.add(pd);
		}
		//}
		return invoiceDto;
	}
	/*
	 * ............................Showing Attachments
	 * Details...........................
	 */
	@Override
	public AttachmentsDto showAttachments(UUID purchaseID) throws InventoryException {
		// TODO Auto-generated method stub
		Attachments attachmentFromDB = purchaseRepo.getById(purchaseID).getAttachements();
		if (attachmentFromDB == null) {
			throw new InventoryException(" No PurchaseOrder Is Present");
		}
//		List<AttachmentsDto> attachmentsDto = new ArrayList<AttachmentsDto>();

			AttachmentsDto attachmentsDto = amapper.convertToAttachmentsDto(attachmentFromDB);


		return attachmentsDto;
	}
}
