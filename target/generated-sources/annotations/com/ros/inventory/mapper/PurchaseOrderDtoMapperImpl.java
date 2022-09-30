package com.ros.inventory.mapper;

import com.ros.inventory.controller.dto.purchaseOrderDto;
import com.ros.inventory.entities.PurchaseOrder;
import com.ros.inventory.entities.Supplier;
import com.ros.inventory.entities.SupplierBasicInfo;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-09-29T14:46:45+0530",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 11.0.16 (Oracle Corporation)"
)
public class PurchaseOrderDtoMapperImpl implements PurchaseOrderDtoMapper {

    @Override
    public purchaseOrderDto convertTopurchaseOrderDto(PurchaseOrder purchase) {
        if ( purchase == null ) {
            return null;
        }

        purchaseOrderDto purchaseOrderDto = new purchaseOrderDto();

        purchaseOrderDto.setPurchasedId( purchase.getPurchasedId() );
        purchaseOrderDto.setPurchaseOrderDate( purchase.getPurchaseOrderDate() );
        purchaseOrderDto.setTotalAmount( purchase.getTotalAmount() );
        purchaseOrderDto.setPurchaseOrderStatus( purchase.getPurchaseOrderStatus() );
        purchaseOrderDto.setSupplierBusinessName( purchaseSupplierSupplierBasicSupplierBusinessName( purchase ) );

        return purchaseOrderDto;
    }

    @Override
    public PurchaseOrder convertToPurchaseOrder(purchaseOrderDto purchase) {
        if ( purchase == null ) {
            return null;
        }

        PurchaseOrder purchaseOrder = new PurchaseOrder();

        purchaseOrder.setPurchasedId( purchase.getPurchasedId() );
        purchaseOrder.setPurchaseOrderDate( purchase.getPurchaseOrderDate() );
        purchaseOrder.setPurchaseOrderStatus( purchase.getPurchaseOrderStatus() );
        purchaseOrder.setTotalAmount( purchase.getTotalAmount() );

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
}
