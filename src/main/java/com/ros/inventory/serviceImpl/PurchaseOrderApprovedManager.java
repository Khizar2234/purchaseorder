package com.ros.inventory.serviceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ros.inventory.Exception.InventoryException;
import com.ros.inventory.Repository.ProductRepository;
import com.ros.inventory.Repository.PurchaseRepository;
import com.ros.inventory.controller.dto.ApproveViewDto;
import com.ros.inventory.controller.dto.ApprovedDto;
import com.ros.inventory.controller.dto.AttachmentsDto;
import com.ros.inventory.controller.dto.DeliveryDto;
import com.ros.inventory.controller.dto.DraftsDto;
import com.ros.inventory.controller.dto.InvoicePDto;
import com.ros.inventory.controller.dto.ProductDto;
import com.ros.inventory.controller.dto.ProductPDto;
import com.ros.inventory.controller.dto.RejectedDto;
import com.ros.inventory.entities.Attachments;
import com.ros.inventory.entities.OrderStatus;
import com.ros.inventory.entities.Product;
import com.ros.inventory.entities.PurchaseOrder;
import com.ros.inventory.mapper.ApprovedMapper;
import com.ros.inventory.mapper.ApprovedViewMapper;
import com.ros.inventory.mapper.AttachmentsMapper;
import com.ros.inventory.mapper.DeliverMapper;
import com.ros.inventory.mapper.InvoicePMapper;
import com.ros.inventory.mapper.ProductMapper;
import com.ros.inventory.mapper.ProductPMapper;
import com.ros.inventory.mapper.PurchaseOrderMapper;
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
		public List<ProductPDto> showProduct() throws InventoryException {
			// TODO Auto-generated method stub
			List<Product> productFromDB=productRepo.getAll();
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
/*.......................Showing Delivery Details............................ */
		@Override
		public List<DeliveryDto> showDelivery() throws InventoryException {
			// TODO Auto-generated method stub
			List<Product> purchaseFromDB=productRepo.getAll();
				
			if (purchaseFromDB == null || purchaseFromDB.size() == 0) {
				throw new InventoryException(" No PurchaseOrder Is Present");
			}
			List<DeliveryDto> deliverDto = new ArrayList<DeliveryDto>();
			for (Product p : purchaseFromDB) {
				DeliveryDto pd = dmapper.convertToDeliveryDto(p);
						//.convertToDeliveryDto(p);
				deliverDto.add(pd);
			}

			return deliverDto;
		}
		

/*............................Showing Invoice Details........................... */
		
		@Override
		public List<InvoicePDto> showInvoice() throws InventoryException {
			// TODO Auto-generated method stub
			List<Product> productFromDB=productRepo.getAll();
			if (productFromDB == null || productFromDB.size() == 0) {
				throw new InventoryException(" No PurchaseOrder Is Present");
			}
			List<InvoicePDto> invoiceDto = new ArrayList<InvoicePDto>();
			for (Product p : productFromDB) {
				InvoicePDto pd =imapper.convertToInvoicePDto(p);
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



		
/*.....................Showing Attachments....................................*/
		
//		@Override
//		public List<AttachmentsDto> showAttachments() throws InventoryException {
//			// TODO Auto-generated method stub
//			List<Attachments> attachmentFromDB=purchaseRepo.getAllAttachments();
//			if (attachmentFromDB == null || attachmentFromDB.size() == 0) {
//				throw new InventoryException(" No PurchaseOrder Is Present");
//			}
//			List<AttachmentsDto> attachmentDto = new ArrayList<AttachmentsDto>();
//			for (Attachments a : attachmentFromDB) {
//				AttachmentsDto ad = aMapper.convertToAttachmentsDto(a);
//				attachmentDto.add(ad);
//			}
//
//			return attachmentDto;
//		}
//		
		

		
		
		
		
		

}
