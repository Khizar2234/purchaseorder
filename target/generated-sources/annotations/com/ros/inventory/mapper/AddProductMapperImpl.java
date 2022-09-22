package com.ros.inventory.mapper;

import com.ros.inventory.controller.dto.AddProductDto;
import com.ros.inventory.controller.dto.AddProductDto.AddProductDtoBuilder;
import com.ros.inventory.controller.dto.ProductDto;
import com.ros.inventory.controller.dto.ProductDto.ProductDtoBuilder;
import com.ros.inventory.entities.Product;
import com.ros.inventory.entities.Supplier;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-09-21T13:30:18+0530",
    comments = "version: 1.4.1.Final, compiler: Eclipse JDT (IDE) 1.4.50.v20210914-1429, environment: Java 17.0.2 (Eclipse Adoptium)"
)
public class AddProductMapperImpl implements AddProductMapper {

    @Override
    public AddProductDto convertToDto(Supplier s) {
        if ( s == null ) {
            return null;
        }

        AddProductDtoBuilder addProductDto = AddProductDto.builder();

        addProductDto.supplierId( s.getSupplierId() );
        addProductDto.products( productListToProductDtoList( s.getProducts() ) );

        return addProductDto.build();
    }

    @Override
    public Supplier convertToEntity(AddProductDto add) {
        if ( add == null ) {
            return null;
        }

        Supplier supplier = new Supplier();

        supplier.setProducts( productDtoListToProductList( add.getProducts() ) );
        supplier.setSupplierId( add.getSupplierId() );

        return supplier;
    }

    protected ProductDto productToProductDto(Product product) {
        if ( product == null ) {
            return null;
        }

        ProductDtoBuilder productDto = ProductDto.builder();

        productDto.pricePerUnit( product.getPricePerUnit() );
        productDto.productCode( product.getProductCode() );
        productDto.productEffectiveDate( product.getProductEffectiveDate() );
        productDto.productId( product.getProductId() );
        productDto.productName( product.getProductName() );
        productDto.productType( product.getProductType() );
        productDto.productVatTax( product.getProductVatTax() );
        productDto.qty( product.getQty() );
        productDto.unitMeasurement( product.getUnitMeasurement() );

        return productDto.build();
    }

    protected List<ProductDto> productListToProductDtoList(List<Product> list) {
        if ( list == null ) {
            return null;
        }

        List<ProductDto> list1 = new ArrayList<ProductDto>( list.size() );
        for ( Product product : list ) {
            list1.add( productToProductDto( product ) );
        }

        return list1;
    }

    protected Product productDtoToProduct(ProductDto productDto) {
        if ( productDto == null ) {
            return null;
        }

        Product product = new Product();

        product.setPricePerUnit( productDto.getPricePerUnit() );
        product.setProductCode( productDto.getProductCode() );
        product.setProductEffectiveDate( productDto.getProductEffectiveDate() );
        product.setProductId( productDto.getProductId() );
        product.setProductName( productDto.getProductName() );
        product.setProductType( productDto.getProductType() );
        product.setProductVatTax( productDto.getProductVatTax() );
        product.setQty( productDto.getQty() );
        product.setUnitMeasurement( productDto.getUnitMeasurement() );

        return product;
    }

    protected List<Product> productDtoListToProductList(List<ProductDto> list) {
        if ( list == null ) {
            return null;
        }

        List<Product> list1 = new ArrayList<Product>( list.size() );
        for ( ProductDto productDto : list ) {
            list1.add( productDtoToProduct( productDto ) );
        }

        return list1;
    }
}
