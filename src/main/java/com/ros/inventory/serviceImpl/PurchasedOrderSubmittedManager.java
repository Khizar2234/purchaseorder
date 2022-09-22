package com.ros.inventory.serviceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;

import com.ros.inventory.Exception.InventoryException;
import com.ros.inventory.Repository.PurchaseRepository;
//import com.ros.inventory.controller.dto.ApprovalStatus;
import com.ros.inventory.controller.dto.DraftsDto;
import com.ros.inventory.controller.dto.SupplierDto;
import com.ros.inventory.entities.OrderStatus;
import com.ros.inventory.entities.PurchaseOrder;
import com.ros.inventory.entities.Supplier;
import com.ros.inventory.mapper.PurchaseOrderMapper;
import com.ros.inventory.service.IPurchaseOrderManager;
import com.ros.inventory.service.IPurchasedOrderSubmittedManager;

@Service
public class PurchasedOrderSubmittedManager implements IPurchasedOrderSubmittedManager
{
    @Autowired
	private PurchaseRepository purchaseRepo;
    @Autowired
    private PurchaseOrderMapper purchaseMapper;
	
	@Override
	public PurchaseOrder save(PurchaseOrder purchase) throws InventoryException 
	{
		// TODO Auto-generated method stub
		PurchaseOrder orderFromDB = purchaseRepo.getById(purchase.getPurchasedId());
		if(orderFromDB!= null)
		{
			throw new InventoryException("order with Id already exist ");
		}
		
		return purchaseRepo.save(purchase);
	}

	@Override
	public PurchaseOrder updatePurchase(PurchaseOrder purchase) throws InventoryException {
		// TODO Auto-generated method stub
		return purchaseRepo.saveAndFlush(purchase);
	}
	


	

	/*..........Saving as a Draft....................*/
	 @Override
		public List<DraftsDto> showByStatus() throws InventoryException {
			// TODO Auto-generated method stub
			List<PurchaseOrder>purchaseFromDB=purchaseRepo.showByStatus("submited");
			
			if (purchaseFromDB == null || purchaseFromDB.size() == 0) {
				throw new InventoryException(" No Purchase Orders are Sumbitted");
			}
			
			List<DraftsDto> draftDto = new ArrayList<DraftsDto>();
			for (PurchaseOrder p : purchaseFromDB) {
				DraftsDto pd = purchaseMapper.convertToPurchaseDto(p);
				draftDto.add(pd);
			}
			
			return draftDto;
		}
	 	@Override
	public PurchaseOrder delete(UUID id) throws InventoryException {
		// TODO Auto-generated method stub
		PurchaseOrder purchaseFromDB = purchaseRepo.getById(id);

		if (purchaseFromDB == null) {
			throw new InventoryException("No PurchaseOrder is present");
		} else {
			purchaseRepo.deleteById(id);
		}
		return purchaseFromDB;
		
	}
	
/*................Approving a single Draft................................... */
	@Override
	@Modifying
	public String approveSinglePurchaseOrder(UUID purchasedId) throws InventoryException {
		// TODO Auto-generated method stub		
		PurchaseOrder dataFromDB=purchaseRepo.getOne(purchasedId);
		if(dataFromDB ==null) {
			throw new InventoryException("Sorry data not available");
		}
		// set status to approved
		dataFromDB.setPurchaseOrderStatus(OrderStatus.approved);
		purchaseRepo.save(dataFromDB);
		return "Approved";
	}

///*.................Rejecting a single draft............................*/
	@Override
	@Modifying
	public String rejectSinglePurchaseOrder(UUID purchasedId) throws InventoryException {
		// TODO Auto-generated method stub		
		PurchaseOrder dataFromDB=purchaseRepo.getOne(purchasedId);
		if(dataFromDB ==null) {
			throw new InventoryException("Sorry data not available");
		}
		// set status to approved
		dataFromDB.setPurchaseOrderStatus(OrderStatus.rejected);
		purchaseRepo.save(dataFromDB);
		return "Rejected";
	}


///*................Approving  a bulk draft................................*/
	@Override
	public String bulkApprove(List<DraftsDto> bulkapprove) throws InventoryException {

		for (DraftsDto drafts : bulkapprove) {
			PurchaseOrder p = purchaseRepo.getOne(drafts.getPurchasedNumber());
			p.setPurchaseOrderStatus(OrderStatus.approved);
			purchaseRepo.saveAndFlush(p);
					}

		return "Approved";

	}
   
	///*................Rejecting  a bulk draft................................*/
	@Override
	public String bulkReject(List<DraftsDto> bulkreject) throws InventoryException {

		for (DraftsDto drafts : bulkreject) {
			PurchaseOrder p = purchaseRepo.getOne(drafts.getPurchasedNumber());
			p.setPurchaseOrderStatus(OrderStatus.rejected);
			purchaseRepo.saveAndFlush(p);
		}

		return "Rejected";

	}
	



	

}
