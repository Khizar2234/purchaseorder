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
    date = "2022-10-03T20:34:09+0530",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 17.0.2 (Oracle Corporation)"
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
