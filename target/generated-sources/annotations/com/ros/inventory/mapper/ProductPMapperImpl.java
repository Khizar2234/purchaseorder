package com.ros.inventory.mapper;

import com.ros.inventory.controller.dto.ProductPDto;
import com.ros.inventory.controller.dto.ProductPDto.ProductPDtoBuilder;
import com.ros.inventory.entities.Product;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-09-21T13:30:18+0530",
    comments = "version: 1.4.1.Final, compiler: Eclipse JDT (IDE) 1.4.50.v20210914-1429, environment: Java 17.0.2 (Eclipse Adoptium)"
)
public class ProductPMapperImpl implements ProductPMapper {

    @Override
    public Product convertToProduct(ProductPDto product) {
        if ( product == null ) {
            return null;
        }

        Product product1 = new Product();

        product1.setProductCode( product.getProductCode() );
        product1.setProductName( product.getProductName() );
        product1.setProductType( product.getProductType() );
        product1.setPricePerUnit( product.getPricePerUnit() );
        product1.setUnitMeasurement( product.getUnitMeasurement() );
        product1.setQty( product.getQty() );

        return product1;
    }

    @Override
    public ProductPDto convertToPurchasePDto(Product product) {
        if ( product == null ) {
            return null;
        }

        ProductPDtoBuilder productPDto = ProductPDto.builder();

        productPDto.pricePerUnit( product.getPricePerUnit() );
        productPDto.productCode( product.getProductCode() );
        productPDto.productName( product.getProductName() );
        productPDto.productType( product.getProductType() );
        productPDto.qty( product.getQty() );
        productPDto.unitMeasurement( product.getUnitMeasurement() );

        return productPDto.build();
    }
}
