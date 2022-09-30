package com.ros.inventory.mapper;

import com.ros.inventory.controller.dto.InvoiceDto;
import com.ros.inventory.controller.dto.InvoiceDto.InvoiceDtoBuilder;
import com.ros.inventory.entities.PurchaseOrder;
import com.ros.inventory.entities.Supplier;
import com.ros.inventory.entities.SupplierBasicInfo;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-09-29T14:46:45+0530",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 11.0.16 (Oracle Corporation)"
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
