package com.ros.inventory.mapper;

import com.ros.inventory.controller.dto.wastageDto;
import com.ros.inventory.entities.Wastage;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-09-21T13:30:18+0530",
    comments = "version: 1.4.1.Final, compiler: Eclipse JDT (IDE) 1.4.50.v20210914-1429, environment: Java 17.0.2 (Eclipse Adoptium)"
)
public class wastageDtoMapperImpl implements wastageDtoMapper {

    @Override
    public wastageDto convertToDto(Wastage product) {
        if ( product == null ) {
            return null;
        }

        wastageDto wastageDto = new wastageDto();

        wastageDto.setWastageId( product.getWastageId() );
        wastageDto.setProductCode( product.getProductCode() );
        wastageDto.setProductName( product.getProductName() );
        wastageDto.setPricePerUnit( product.getPricePerUnit() );
        wastageDto.setUnitMeasurement( product.getUnitMeasurement() );
        wastageDto.setQty( product.getQty() );

        return wastageDto;
    }

    @Override
    public Wastage convertToEntity(wastageDto product) {
        if ( product == null ) {
            return null;
        }

        Wastage wastage = new Wastage();

        wastage.setPricePerUnit( product.getPricePerUnit() );
        wastage.setProductCode( product.getProductCode() );
        wastage.setProductName( product.getProductName() );
        wastage.setQty( product.getQty() );
        wastage.setUnitMeasurement( product.getUnitMeasurement() );
        wastage.setWastageId( product.getWastageId() );

        return wastage;
    }
}
