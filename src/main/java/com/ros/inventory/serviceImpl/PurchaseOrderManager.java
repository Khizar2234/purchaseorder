package com.ros.inventory.serviceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ros.inventory.Exception.InventoryException;
import com.ros.inventory.Repository.PurchaseRepository;
//import com.ros.inventory.controller.dto.ApprovalStatus;
import com.ros.inventory.controller.dto.DraftsDto;
import com.ros.inventory.controller.dto.RejectedDto;
import com.ros.inventory.controller.dto.SupplierDto;
import com.ros.inventory.entities.OrderStatus;
import com.ros.inventory.entities.PurchaseOrder;
import com.ros.inventory.entities.Supplier;
import com.ros.inventory.mapper.PurchaseOrderMapper;
import com.ros.inventory.service.IPurchaseOrderManager;

@Service
public class PurchaseOrderManager implements IPurchaseOrderManager
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
			List<PurchaseOrder>purchaseFromDB=purchaseRepo.showByStatus("drafts");
			
			if (purchaseFromDB == null || purchaseFromDB.size() == 0) {
				throw new InventoryException(" No drafts are Present");
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
	
	@Override
	public String deleteALL(List<PurchaseOrder> pr) throws InventoryException {
		// TODO Auto-generated method stub
		for(PurchaseOrder p:pr) {
			purchaseRepo.delete(p);
		}
		return "Deleted All";
	}
	
	@Override
	public String bulkSubmit(List<DraftsDto> bulksubmit) throws InventoryException {
		for (DraftsDto drafts : bulksubmit) {
			PurchaseOrder p = purchaseRepo.getOne(drafts.getPurchasedNumber());
			p.setPurchaseOrderStatus(OrderStatus.submited);
			purchaseRepo.saveAndFlush(p);
		}

		return "submited";
	
	}

	
	

}
