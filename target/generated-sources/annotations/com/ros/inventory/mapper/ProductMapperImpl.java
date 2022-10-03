package com.ros.inventory.mapper;

import com.ros.inventory.controller.dto.ProductDto;
import com.ros.inventory.controller.dto.ProductDto.ProductDtoBuilder;
import com.ros.inventory.entities.Product;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-10-03T20:34:09+0530",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 17.0.2 (Oracle Corporation)"
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

        product.setProductId( addp.getProductId() );
        product.setProductCode( addp.getProductCode() );
        product.setProductName( addp.getProductName() );
        product.setProductType( addp.getProductType() );
        product.setPricePerUnit( addp.getPricePerUnit() );
        product.setUnitMeasurement( addp.getUnitMeasurement() );
        product.setQty( addp.getQty() );
        product.setProductEffectiveDate( addp.getProductEffectiveDate() );
        product.setProductVatTax( addp.getProductVatTax() );

        return product;
    }
}
