package com.ros.inventory.mapper;

import com.ros.inventory.controller.dto.ClosingValueDto;
import com.ros.inventory.controller.dto.ClosingValueDto.ClosingValueDtoBuilder;
import com.ros.inventory.entities.Product;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-10-02T13:11:50+0530",
    comments = "version: 1.4.2.Final, compiler: Eclipse JDT (IDE) 1.4.50.v20210914-1429, environment: Java 17.0.2 (Oracle Corporation)"
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
