package com.ros.inventory.mapper;

import com.ros.inventory.controller.dto.InvoicePDto;
import com.ros.inventory.controller.dto.InvoicePDto.InvoicePDtoBuilder;
import com.ros.inventory.entities.Product;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-10-03T20:34:08+0530",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 17.0.2 (Oracle Corporation)"
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
