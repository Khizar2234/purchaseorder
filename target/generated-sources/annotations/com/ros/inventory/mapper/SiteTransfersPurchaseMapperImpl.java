package com.ros.inventory.mapper;

import com.ros.inventory.controller.dto.SiTeTransfersDto;
import com.ros.inventory.controller.dto.SiTeTransfersDto.SiTeTransfersDtoBuilder;
import com.ros.inventory.entities.PurchaseOrder;
import com.ros.inventory.entities.Supplier;
import com.ros.inventory.entities.SupplierBasicInfo;
import java.time.format.DateTimeFormatter;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-09-21T13:30:18+0530",
    comments = "version: 1.4.1.Final, compiler: Eclipse JDT (IDE) 1.4.50.v20210914-1429, environment: Java 17.0.2 (Eclipse Adoptium)"
)
public class SiteTransfersPurchaseMapperImpl implements SiteTransfersPurchaseMapper {

    @Override
    public SiTeTransfersDto convertToSiTeTransfersDto(PurchaseOrder purchase) {
        if ( purchase == null ) {
            return null;
        }

        SiTeTransfersDtoBuilder siTeTransfersDto = SiTeTransfersDto.builder();

        siTeTransfersDto.supplierName( purchaseSupplierSupplierBasicSupplierBusinessName( purchase ) );
        if ( purchase.getPurchaseOrderDate() != null ) {
            siTeTransfersDto.date( DateTimeFormatter.ISO_LOCAL_DATE.format( purchase.getPurchaseOrderDate() ) );
        }
        siTeTransfersDto.transferType( purchase.getTransferType() );

        return siTeTransfersDto.build();
    }

    private String purchaseSupplierSupplierBasicSupplierBusinessName(PurchaseOrder purchaseOrder) {
        if ( purchaseOrder == null ) {
            return null;
        }
        Supplier supplier = purchaseOrder.getSupplier();
        if ( supplier == null ) {
            return null;
        }
        SupplierBasicInfo supplierBasic = supplier.getSupplierBasic();
        if ( supplierBasic == null ) {
            return null;
        }
        String supplierBusinessName = supplierBasic.getSupplierBusinessName();
        if ( supplierBusinessName == null ) {
            return null;
        }
        return supplierBusinessName;
    }
}
