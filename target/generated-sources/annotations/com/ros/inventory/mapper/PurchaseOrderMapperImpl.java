package com.ros.inventory.mapper;

import com.ros.inventory.controller.dto.DraftsDto;
import com.ros.inventory.controller.dto.DraftsDto.DraftsDtoBuilder;
import com.ros.inventory.entities.PurchaseOrder;
import com.ros.inventory.entities.Supplier;
import com.ros.inventory.entities.SupplierBasicInfo;
import com.ros.inventory.entities.SupplierType;
import java.time.format.DateTimeFormatter;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-10-02T13:11:50+0530",
    comments = "version: 1.4.2.Final, compiler: Eclipse JDT (IDE) 1.4.50.v20210914-1429, environment: Java 17.0.2 (Oracle Corporation)"
)
public class PurchaseOrderMapperImpl implements PurchaseOrderMapper {

    @Override
    public DraftsDto convertToPurchaseDto(PurchaseOrder purchase) {
        if ( purchase == null ) {
            return null;
        }

        DraftsDtoBuilder draftsDto = DraftsDto.builder();

        draftsDto.supplierName( purchaseSupplierSupplierBasicSupplierBusinessName( purchase ) );
        SupplierType supplierType = purchaseSupplierSupplierType( purchase );
        if ( supplierType != null ) {
            draftsDto.supplierType( supplierType.name() );
        }
        draftsDto.purchasedNumber( purchase.getPurchasedId() );
        if ( purchase.getPurchaseOrderDate() != null ) {
            draftsDto.purchaseDate( DateTimeFormatter.ISO_LOCAL_DATE.format( purchase.getPurchaseOrderDate() ) );
        }

        return draftsDto.build();
    }

    @Override
    public PurchaseOrder convertToPurchaseOrder(DraftsDto draftDto) {
        if ( draftDto == null ) {
            return null;
        }

        PurchaseOrder purchaseOrder = new PurchaseOrder();

        return purchaseOrder;
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

    private SupplierType purchaseSupplierSupplierType(PurchaseOrder purchaseOrder) {
        if ( purchaseOrder == null ) {
            return null;
        }
        Supplier supplier = purchaseOrder.getSupplier();
        if ( supplier == null ) {
            return null;
        }
        SupplierType supplierType = supplier.getSupplierType();
        if ( supplierType == null ) {
            return null;
        }
        return supplierType;
    }
}
