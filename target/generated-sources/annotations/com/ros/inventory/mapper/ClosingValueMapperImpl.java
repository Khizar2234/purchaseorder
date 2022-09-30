package com.ros.inventory.mapper;

import com.ros.inventory.controller.dto.ClosingValueDto;
import com.ros.inventory.controller.dto.ClosingValueDto.ClosingValueDtoBuilder;
import com.ros.inventory.entities.Product;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-09-29T14:46:45+0530",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 11.0.16 (Oracle Corporation)"
)
public class ClosingValueMapperImpl implements ClosingValueMapper {

    @Override
    public ClosingValueDto convertToClosingValueDto(Product product) {
        if ( product == null ) {
            return null;
        }

        ClosingValueDtoBuilder closingValueDto = ClosingValueDto.builder();

        closingValueDto.productCode( product.getProductCode() );
        closingValueDto.productName( product.getProductName() );
        closingValueDto.pricePerUnit( product.getPricePerUnit() );
        closingValueDto.unitMeasurement( product.getUnitMeasurement() );
        closingValueDto.qty( product.getQty() );

        return closingValueDto.build();
    }
}
