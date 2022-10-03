package com.ros.inventory.serviceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import com.ros.inventory.Repository.SupplierRepository;
import com.ros.inventory.entities.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ros.inventory.Exception.InventoryException;
import com.ros.inventory.Repository.ProductRepository;
import com.ros.inventory.Repository.PurchaseRepository;
import com.ros.inventory.controller.dto.ApproveViewDto;
import com.ros.inventory.controller.dto.ApprovedDto;
import com.ros.inventory.controller.dto.AttachmentsDto;
import com.ros.inventory.controller.dto.DeliveryDto;
import com.ros.inventory.controller.dto.InvoicePDto;
import com.ros.inventory.controller.dto.ProductPDto;
import com.ros.inventory.mapper.ApprovedMapper;
import com.ros.inventory.mapper.ApprovedViewMapper;
import com.ros.inventory.mapper.AttachmentsMapper;
import com.ros.inventory.mapper.DeliverMapper;
import com.ros.inventory.mapper.InvoicePMapper;
import com.ros.inventory.mapper.ProductPMapper;
import com.ros.inventory.service.IPurchaseOrderApprovedManager;
@Service
public class PurchaseOrderApprovedManager implements IPurchaseOrderApprovedManager{

	    @Autowired
		private PurchaseRepository purchaseRepo;
	    @Autowired
	    private ApprovedMapper purchaseMapper;
	    @Autowired
	    private ApprovedViewMapper apMapper;
	    @Autowired
	    private ProductRepository productRepo;
	    @Autowired
	    private ProductPMapper productpmapper;

		@Autowired
		private SupplierRepository supplierRepository;

	    @Autowired
	    private DeliverMapper dmapper;
	    
	    @Autowired
	    private InvoicePMapper imapper;
	    
	    @Autowired
	    private AttachmentsMapper aMapper;
/*............. Showing the List of Stocks which is Approve.....................*/	
	    @Override
		public List<ApprovedDto> showByStatus() throws InventoryException {
			// TODO Auto-generated method stub
			List<PurchaseOrder>purchaseFromDB=purchaseRepo.showByStatus("approved");;
			
			if (purchaseFromDB == null || purchaseFromDB.size() == 0) {
				throw new InventoryException(" No Approved order Is Present");
			}
			
			List<ApprovedDto> approveDto = new ArrayList<ApprovedDto>();
			for (PurchaseOrder p : purchaseFromDB) {
				ApprovedDto pd = purchaseMapper.convertToApprovedDto(p);
				approveDto.add(pd);
			}
			
			return approveDto;
		}

		
		
		@Override
		public List<ApproveViewDto> showApprove() throws InventoryException {
			List<PurchaseOrder>purchaseFromDB=purchaseRepo.getAll();
			
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
	
		/*......Showing the Product Details......................... */
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
/*.......................Showing Delivery Details............................ */
	@Override
	public List<ProductPDto> showDelivery(UUID purchasedId) throws InventoryException {
		// TODO Auto-generated method stub
		PurchaseOrder purchaseFromDB=purchaseRepo.getById(purchasedId);
		if (purchaseFromDB == null ) {
			throw new InventoryException(" No PurchaseOrder Is Present");
		}
		List<ProductPDto> productPDtoList=new ArrayList<>();
		List<Product> prods=purchaseFromDB.getSupplier().getProducts();
		for(Product prod:prods) {
		//DeliveryDto pd = dmapper.convertToDeliveryDto(prod);
		//ProductPDto pd = productpmapper.convertToPurchasePDto(pd);
		//.convertToDeliveryDto(p);
		productPDtoList.add(productpmapper.convertToPurchasePDto(prod));
		}
		return productPDtoList;
	}
		

/*............................Showing Invoice Details........................... */

	@Override
	public List<InvoicePDto> showInvoice(UUID purchasedId) throws InventoryException {
		// TODO Auto-generated method stub
		PurchaseOrder purchaseFromDB=purchaseRepo.getById(purchasedId);
		if (purchaseFromDB == null ) {
			throw new InventoryException(" No PurchaseOrder Is Present");
		}
		List<InvoicePDto> invoiceDto=new ArrayList<>();

		List<Product> prods=purchaseFromDB.getSupplier().getProducts();

		for(Product prod:prods) {
			InvoicePDto pd = imapper.convertToInvoicePDto(prod);
			invoiceDto.add(pd);
		}
		return invoiceDto;
	}

//------------------------ Set Status to Exported ------------------------//

		@Override
		public String setExported() throws InventoryException {
			// TODO Auto-generated method stub
			
			List<PurchaseOrder> productFromDB = purchaseRepo.findAll().stream()
					.filter(x->x.getPurchaseOrderStatus()
							.equals(OrderStatus.approved))
					.collect(Collectors.toList());
			
			if(productFromDB.size()!=0) {
				for(PurchaseOrder p:productFromDB) {
					p.setPurchaseOrderStatus(OrderStatus.exported);
					purchaseRepo.save(p);
				}
			}
			else {
				return "Not data Available";
			}
			
			return "Exported";
		}

		@Override
		public double approvedTotal() {
			List<PurchaseOrder> purchaseOrderList = purchaseRepo.showByStatus("approved");
			double total = 0.0;

			for (PurchaseOrder purchaseOrder : purchaseOrderList) {
				total = total + purchaseOrder.getTotalAmount();
			}

			return total;
		}

		@Override
		public List<ApprovedDto> getApproved() {
			List<PurchaseOrder> purchaseOrderList = new ArrayList<>();

			List<ApprovedDto> approvedDtoList = new ArrayList<>();

			purchaseOrderList = purchaseRepo.getAllByPurchaseOrderStatus(OrderStatus.approved);

			for (PurchaseOrder order : purchaseOrderList) {
				ApprovedDto approved = new ApprovedDto();

				approved.setPurchasedNumber(order.getPurchasedId());
				approved.setPurchaseDate(String.valueOf(order.getPurchaseOrderDate()));

				Supplier supplier = supplierRepository.getBySupplierId(order.getSupplier().getSupplierId());

				approved.setSupplierName(supplier.getSupplierBasic().getSupplierBusinessName());
				approved.setSupplierType(String.valueOf(supplier.getSupplierType()));
				approved.setValue(order.getTotalAmount());
				approved.setStatus(order.getPurchaseOrderStatus());

				approvedDtoList.add(approved);
			}

			return approvedDtoList;
		}

		
/*.....................Showing Attachments....................................*/
		
	@Override
		public List<AttachmentsDto> showAttachments() throws InventoryException {
			// TODO Auto-generated method stub
			List<Attachments> attachmentFromDB=purchaseRepo.getAllAttachments();
			if (attachmentFromDB == null || attachmentFromDB.size() == 0) {
				throw new InventoryException(" No PurchaseOrder Is Present");
			}
			List<AttachmentsDto> attachmentDto = new ArrayList<AttachmentsDto>();
			for (Attachments a : attachmentFromDB) {
				AttachmentsDto ad = aMapper.convertToAttachmentsDto(a);
				attachmentDto.add(ad);
			}

			return attachmentDto;
		}

		@Override
		public Product setProductPrice(Product product) {
			productRepo.save(product);
			return product;
		}

}
