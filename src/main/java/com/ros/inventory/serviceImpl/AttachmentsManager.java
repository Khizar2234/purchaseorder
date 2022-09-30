package com.ros.inventory.serviceImpl;

import com.ros.inventory.Repository.AttachmentsRepository;
import com.ros.inventory.Repository.PurchaseRepository;
import com.ros.inventory.controller.dto.AttachmentsDto;
import com.ros.inventory.entities.Attachments;
import com.ros.inventory.entities.PurchaseOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class AttachmentsManager {

    @Autowired
    private PurchaseRepository purchaseRepository;

    @Autowired
    private AttachmentsRepository attachmentsRepository;

    public Attachments getAttachments(UUID purchaseId) {
        PurchaseOrder purchaseOrder = purchaseRepository.getById(purchaseId);

        Attachments attachment = purchaseOrder.getAttachements();
        return attachment;
    }
}
