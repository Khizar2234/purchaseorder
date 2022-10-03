package com.ros.inventory.mapper;

import com.ros.inventory.controller.dto.InvoiceDto;
import com.ros.inventory.controller.dto.InvoiceDto.InvoiceDtoBuilder;
import com.ros.inventory.entities.PurchaseOrder;
import com.ros.inventory.entities.Supplier;
import com.ros.inventory.entities.SupplierBasicInfo;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-10-02T13:11:50+0530",
    comments = "version: 1.4.2.Final, compiler: Eclipse JDT (IDE) 1.4.50.v20210914-1429, environment: Java 17.0.2 (Oracle Corporation)"
)
public class InvoiceMapperImpl implements InvoiceMapper {

    @Override
    public InvoiceDto convertToInvoiceDto(PurchaseOrder p) {
        if ( p == null ) {
            return null;
        }

        InvoiceDtoBuilder invoiceDto = InvoiceDto.builder();

        invoiceDto.supplierName( pSupplierSupplierBasicSupplierBusinessName( p ) );
        invoiceDto.invoiceDate( p.getPurchaseOrderDate() );
        invoiceDto.total( p.getTotalAmount() );
        invoiceDto.PONumber( p.getPurchasedId() );

        return invoiceDto.build();
    }

    private String pSupplierSupplierBasicSupplierBusinessName(PurchaseOrder purchaseOrder) {
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
