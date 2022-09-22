package com.ros.inventory.serviceImpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ros.inventory.Exception.InventoryException;
import com.ros.inventory.Repository.PurchaseRepository;
import com.ros.inventory.controller.dto.ApprovedDto;
import com.ros.inventory.controller.dto.DraftsDto;
import com.ros.inventory.controller.dto.RejectedDto;
import com.ros.inventory.entities.PurchaseOrder;
import com.ros.inventory.mapper.PurchaseOrderMapper;
import com.ros.inventory.mapper.RejectedProductMapper;
import com.ros.inventory.service.IPurchaseOrderRejectedManager;

@Service
public class PurchaseOrderRejectedManager implements IPurchaseOrderRejectedManager{
    
	    @Autowired
		private PurchaseRepository purchaseRepo;
	    @Autowired
	    private RejectedProductMapper purchaseMapper;
	
	    /*............. Showing the List of Stocks which is Rejected.....................*/	
	    @Override
		public List<RejectedDto> showByStatus() throws InventoryException {
			// TODO Auto-generated method stub
			List<PurchaseOrder>purchaseFromDB=purchaseRepo.showByStatus("rejected");;
			
			if (purchaseFromDB == null || purchaseFromDB.size() == 0) {
				throw new InventoryException(" No Rejected order Is Present");
			}
			
			List<RejectedDto> rejectDto = new ArrayList<RejectedDto>();
			for (PurchaseOrder p : purchaseFromDB) {
				RejectedDto pd = purchaseMapper.convertToRejectedDto(p);
				rejectDto.add(pd);
			}
			
			return rejectDto;
		}

	
	

}
