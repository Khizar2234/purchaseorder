package com.ros.inventory.controller;

import com.ros.inventory.Exception.InventoryException;
import com.ros.inventory.Repository.AttachmentsRepository;
import com.ros.inventory.Repository.PurchaseRepository;
import com.ros.inventory.controller.dto.AttachmentsDto;
import com.ros.inventory.entities.Attachments;
import com.ros.inventory.entities.PurchaseOrder;
import com.ros.inventory.serviceImpl.AttachmentsManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/purchase/approved")
@CrossOrigin("*")
public class AttachmentsController {

    @Autowired
    private PurchaseRepository purchaseRepository;

    @Autowired
    private AttachmentsManager attachmentsManager;

    @GetMapping(value = "/attachments")
    public Attachments getAttachments(String purchaseId) throws InventoryException {
        List<PurchaseOrder> purchaseOrderList = purchaseRepository.getAll();
        for (PurchaseOrder purchaseOrder : purchaseOrderList) {
            if (String.valueOf(purchaseOrder.getPurchasedId()).equals(purchaseId)) {
                return attachmentsManager.getAttachments(purchaseOrder.getPurchasedId());
            }
        }

        throw new InventoryException("No data found");
    }
}
