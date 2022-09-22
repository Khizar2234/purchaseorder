package com.ros.inventory.mapper;

import com.ros.inventory.controller.dto.AttachmentsDto;
import com.ros.inventory.controller.dto.AttachmentsDto.AttachmentsDtoBuilder;
import com.ros.inventory.entities.Attachments;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-09-21T13:30:18+0530",
    comments = "version: 1.4.1.Final, compiler: Eclipse JDT (IDE) 1.4.50.v20210914-1429, environment: Java 17.0.2 (Eclipse Adoptium)"
)
public class AttachmentsMapperImpl implements AttachmentsMapper {

    @Override
    public Attachments convertToAttachments(AttachmentsDto attachments) {
        if ( attachments == null ) {
            return null;
        }

        Attachments attachments1 = new Attachments();

        attachments1.setDiscrepency( attachments.getDiscrepency() );
        attachments1.setCreditNote( attachments.getCreditNote() );
        attachments1.setInvoiceReceived( attachments.getInvoiceReceived() );

        return attachments1;
    }

    @Override
    public AttachmentsDto convertToAttachmentsDto(Attachments attachments) {
        if ( attachments == null ) {
            return null;
        }

        AttachmentsDtoBuilder attachmentsDto = AttachmentsDto.builder();

        attachmentsDto.creditNote( attachments.getCreditNote() );
        attachmentsDto.discrepency( attachments.getDiscrepency() );
        attachmentsDto.invoiceReceived( attachments.getInvoiceReceived() );

        return attachmentsDto.build();
    }
}
