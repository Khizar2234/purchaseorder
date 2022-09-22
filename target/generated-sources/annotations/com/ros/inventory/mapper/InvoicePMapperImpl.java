package com.ros.inventory.mapper;

import com.ros.inventory.controller.dto.InvoicePDto;
import com.ros.inventory.controller.dto.InvoicePDto.InvoicePDtoBuilder;
import com.ros.inventory.entities.Product;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-09-21T13:30:18+0530",
    comments = "version: 1.4.1.Final, compiler: Eclipse JDT (IDE) 1.4.50.v20210914-1429, environment: Java 17.0.2 (Eclipse Adoptium)"
)
public class InvoicePMapperImpl implements InvoicePMapper {

    @Override
    public InvoicePDto convertToInvoicePDto(Product product) {
        if ( product == null ) {
            return null;
        }

        InvoicePDtoBuilder invoicePDto = InvoicePDto.builder();

        invoicePDto.productCode( product.getProductCode() );
        invoicePDto.productName( product.getProductName() );
        invoicePDto.pricePerUnit( product.getPricePerUnit() );
        invoicePDto.unitMeasurement( product.getUnitMeasurement() );
        invoicePDto.qty( product.getQty() );
        invoicePDto.vat( product.getProductVatTax() );
        invoicePDto.netPrice( product.getProductNetPrice() );

        return invoicePDto.build();
    }
}
