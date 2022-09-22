package com.ros.inventory.mapper;

import com.ros.inventory.controller.dto.ProductDto;
import com.ros.inventory.controller.dto.ProductDto.ProductDtoBuilder;
import com.ros.inventory.entities.Product;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-09-21T13:30:18+0530",
    comments = "version: 1.4.1.Final, compiler: Eclipse JDT (IDE) 1.4.50.v20210914-1429, environment: Java 17.0.2 (Eclipse Adoptium)"
)
public class ProductMapperImpl implements ProductMapper {

    @Override
    public ProductDto convertToDto(Product p) {
        if ( p == null ) {
            return null;
        }

        ProductDtoBuilder productDto = ProductDto.builder();

        productDto.productId( p.getProductId() );
        productDto.productCode( p.getProductCode() );
        productDto.productName( p.getProductName() );
        productDto.productType( p.getProductType() );
        productDto.pricePerUnit( p.getPricePerUnit() );
        productDto.unitMeasurement( p.getUnitMeasurement() );
        productDto.qty( p.getQty() );
        productDto.productEffectiveDate( p.getProductEffectiveDate() );
        productDto.productVatTax( p.getProductVatTax() );

        return productDto.build();
    }

    @Override
    public Product convertToEntity(ProductDto addp) {
        if ( addp == null ) {
            return null;
        }

        Product product = new Product();

        product.setPricePerUnit( addp.getPricePerUnit() );
        product.setProductCode( addp.getProductCode() );
        product.setProductEffectiveDate( addp.getProductEffectiveDate() );
        product.setProductId( addp.getProductId() );
        product.setProductName( addp.getProductName() );
        product.setProductType( addp.getProductType() );
        product.setProductVatTax( addp.getProductVatTax() );
        product.setQty( addp.getQty() );
        product.setUnitMeasurement( addp.getUnitMeasurement() );

        return product;
    }
}
