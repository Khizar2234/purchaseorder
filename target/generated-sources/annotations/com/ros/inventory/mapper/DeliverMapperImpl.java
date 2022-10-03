package com.ros.inventory.mapper;

import com.ros.inventory.controller.dto.DeliveryDto;
import com.ros.inventory.controller.dto.DeliveryDto.DeliveryDtoBuilder;
import com.ros.inventory.entities.Product;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-10-03T20:34:08+0530",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 17.0.2 (Oracle Corporation)"
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
        deliveryDto.productPrice( product.getPricePerUnit() );
        deliveryDto.qtyReceived( product.getQtyReceived() );
        deliveryDto.initialPrice( product.getInitialPrice() );
        deliveryDto.finalPrice( product.getFinalPrice() );
        deliveryDto.qty( product.getQty() );

        return deliveryDto.build();
    }
}
