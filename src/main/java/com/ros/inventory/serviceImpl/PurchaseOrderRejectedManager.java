package com.ros.inventory.serviceImpl;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.netflix.discovery.converters.Auto;
import com.ros.inventory.Repository.SupplierRepository;
import com.ros.inventory.entities.OrderStatus;
import com.ros.inventory.entities.Supplier;
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

		@Autowired
		private SupplierRepository supplierRepository;
	
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
				//RejectedDto pd = purchaseMapper.convertToRejectedDto(p);
				//rejectDto.add(pd);
			}
			
			return rejectDto;
		}

		@Override
		public double rejectedTotal() {
			List<PurchaseOrder> purchaseOrderList = purchaseRepo.showByStatus("rejected");
			double total = 0.0;

			for (PurchaseOrder purchaseOrder : purchaseOrderList) {
				total = total + purchaseOrder.getTotalAmount();
			}

			return total;
		}

		@Override
		public List<RejectedDto> getRejected() {
			List<PurchaseOrder> purchaseOrderList = new ArrayList<>();

			List<RejectedDto> rejectedDto = new ArrayList<>();

			purchaseOrderList = purchaseRepo.getAllByPurchaseOrderStatus(OrderStatus.rejected);

			for (PurchaseOrder order : purchaseOrderList) {
				RejectedDto rejected = new RejectedDto();

				rejected.setPurchasedNumber(order.getPurchasedId());
				rejected.setPurchaseDate(String.valueOf(order.getPurchaseOrderDate()));

				Supplier supplier = supplierRepository.getBySupplierId(order.getSupplier().getSupplierId());

				rejected.setSupplierName(supplier.getSupplierBasic().getSupplierBusinessName());
				rejected.setSupplierType(String.valueOf(supplier.getSupplierType()));
				rejected.setValue(order.getTotalAmount());
				rejected.setRejectionDate(LocalDate.now());

				rejectedDto.add(rejected);
			}

			return rejectedDto;
		}
}
