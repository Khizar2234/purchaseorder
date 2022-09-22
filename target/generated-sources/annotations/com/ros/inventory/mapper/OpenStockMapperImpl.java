package com.ros.inventory.mapper;

import com.ros.inventory.controller.dto.OpeningStockDto;
import com.ros.inventory.controller.dto.OpeningStockDto.OpeningStockDtoBuilder;
import com.ros.inventory.entities.OpeningStock;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-09-21T13:30:18+0530",
    comments = "version: 1.4.1.Final, compiler: Eclipse JDT (IDE) 1.4.50.v20210914-1429, environment: Java 17.0.2 (Eclipse Adoptium)"
)
public class OpenStockMapperImpl implements OpenStockMapper {

    @Override
    public OpeningStockDto convertToOpeningStockDto(OpeningStock product) {
        if ( product == null ) {
            return null;
        }

        OpeningStockDtoBuilder openingStockDto = OpeningStockDto.builder();

        openingStockDto.productCode( product.getProductCode() );
        openingStockDto.productName( product.getProductName() );
        openingStockDto.pricePerUnit( product.getPricePerUnit() );
        openingStockDto.unitMeasurement( product.getUnitMeasurement() );
        openingStockDto.qty( product.getQty() );

        return openingStockDto.build();
    }

    @Override
    public OpeningStock convertToOpeningStock(OpeningStockDto productDto) {
        if ( productDto == null ) {
            return null;
        }

        OpeningStock openingStock = new OpeningStock();

        openingStock.setPricePerUnit( productDto.getPricePerUnit() );
        openingStock.setProductCode( productDto.getProductCode() );
        openingStock.setProductName( productDto.getProductName() );
        openingStock.setQty( productDto.getQty() );
        openingStock.setUnitMeasurement( productDto.getUnitMeasurement() );

        return openingStock;
    }
}
