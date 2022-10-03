package com.ros.inventory.serviceImpl;

import com.netflix.discovery.converters.Auto;
import com.ros.inventory.Repository.PurchaseRepository;
import com.ros.inventory.Repository.SupplierRepository;
import com.ros.inventory.controller.dto.DraftsDto;
import com.ros.inventory.entities.OrderStatus;
import com.ros.inventory.entities.PurchaseOrder;
import com.ros.inventory.entities.Supplier;
import com.ros.inventory.service.IPurchaseOrderDraftsManager;
import jdk.dynalink.linker.LinkerServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PurchaseOrderDraftsManager implements IPurchaseOrderDraftsManager {

    @Autowired
    private PurchaseRepository purchaseRepository;

    @Autowired
    private SupplierRepository supplierRepository;

    @Override
    public List<DraftsDto> getDrafts() {
        List<PurchaseOrder> purchaseOrderList = new ArrayList<>();

        List<DraftsDto> draftsDtoList = new ArrayList<>();

        purchaseOrderList = purchaseRepository.getAllByPurchaseOrderStatus(OrderStatus.drafts);

        for (PurchaseOrder order : purchaseOrderList) {
            DraftsDto draft = new DraftsDto();

            draft.setPurchasedNumber(order.getPurchasedId());
            draft.setPurchaseDate(String.valueOf(order.getPurchaseOrderDate()));

            Supplier supplier = supplierRepository.getBySupplierId(order.getSupplier().getSupplierId());

            draft.setSupplierName(supplier.getSupplierBasic().getSupplierBusinessName());
            draft.setSupplierType(String.valueOf(supplier.getSupplierType()));
            draft.setValue(order.getTotalAmount());

            draftsDtoList.add(draft);
        }

        return draftsDtoList;
    }
}
