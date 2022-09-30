package com.ros.inventory.mapper;

import com.ros.inventory.controller.dto.ProductPDto;
import com.ros.inventory.controller.dto.ProductPDto.ProductPDtoBuilder;
import com.ros.inventory.entities.Product;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-09-29T14:46:45+0530",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 11.0.16 (Oracle Corporation)"
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

        productPDto.productCode( product.getProductCode() );
        productPDto.productName( product.getProductName() );
        productPDto.productType( product.getProductType() );
        productPDto.unitMeasurement( product.getUnitMeasurement() );
        productPDto.pricePerUnit( product.getPricePerUnit() );
        productPDto.qty( product.getQty() );

        return productPDto.build();
    }
}
