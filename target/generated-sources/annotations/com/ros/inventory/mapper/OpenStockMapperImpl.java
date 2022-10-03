package com.ros.inventory.mapper;

import com.ros.inventory.controller.dto.OpeningStockDto;
import com.ros.inventory.controller.dto.OpeningStockDto.OpeningStockDtoBuilder;
import com.ros.inventory.entities.OpeningStock;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-10-03T20:34:09+0530",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 17.0.2 (Oracle Corporation)"
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

        openingStock.setProductCode( productDto.getProductCode() );
        openingStock.setProductName( productDto.getProductName() );
        openingStock.setUnitMeasurement( productDto.getUnitMeasurement() );
        openingStock.setPricePerUnit( productDto.getPricePerUnit() );
        openingStock.setQty( productDto.getQty() );

        return openingStock;
    }
}
