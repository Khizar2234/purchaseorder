package com.ros.inventory.mapper;

import com.ros.inventory.controller.dto.AttachmentsDto;
import com.ros.inventory.controller.dto.AttachmentsDto.AttachmentsDtoBuilder;
import com.ros.inventory.entities.Attachments;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-09-29T14:46:45+0530",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 11.0.16 (Oracle Corporation)"
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

        attachmentsDto.discrepency( attachments.getDiscrepency() );
        attachmentsDto.creditNote( attachments.getCreditNote() );
        attachmentsDto.invoiceReceived( attachments.getInvoiceReceived() );

        return attachmentsDto.build();
    }
}
