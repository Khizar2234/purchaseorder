package com.ros.inventory.mapper;

import com.ros.inventory.controller.dto.DeliveryDto;
import com.ros.inventory.controller.dto.DeliveryDto.DeliveryDtoBuilder;
import com.ros.inventory.entities.Product;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-09-21T13:30:18+0530",
    comments = "version: 1.4.1.Final, compiler: Eclipse JDT (IDE) 1.4.50.v20210914-1429, environment: Java 17.0.2 (Eclipse Adoptium)"
)
public class DeliverMapperImpl implements DeliverMapper {

    @Override
    public DeliveryDto convertToDeliveryDto(Product product) {
        if ( product == null ) {
            return null;
        }

        DeliveryDtoBuilder deliveryDto = DeliveryDto.builder();

        deliveryDto.productCode( (int) product.getProductCode() );
        deliveryDto.productName( product.getProductName() );
        deliveryDto.qtyReceived( product.getQtyReceived() );
        deliveryDto.initialPrice( product.getInitialPrice() );
        deliveryDto.finalPrice( product.getFinalPrice() );
        deliveryDto.qty( product.getQty() );

        return deliveryDto.build();
    }
}
