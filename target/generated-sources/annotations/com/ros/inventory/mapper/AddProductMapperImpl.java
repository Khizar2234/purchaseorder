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
    date = "2022-09-29T14:46:45+0530",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 11.0.16 (Oracle Corporation)"
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

        supplier.setSupplierId( add.getSupplierId() );
        supplier.setProducts( productDtoListToProductList( add.getProducts() ) );

        return supplier;
    }

    protected ProductDto productToProductDto(Product product) {
        if ( product == null ) {
            return null;
        }

        ProductDtoBuilder productDto = ProductDto.builder();

        productDto.productId( product.getProductId() );
        productDto.productCode( product.getProductCode() );
        productDto.productName( product.getProductName() );
        productDto.productType( product.getProductType() );
        productDto.pricePerUnit( product.getPricePerUnit() );
        productDto.unitMeasurement( product.getUnitMeasurement() );
        productDto.qty( product.getQty() );
        productDto.productEffectiveDate( product.getProductEffectiveDate() );
        productDto.productVatTax( product.getProductVatTax() );

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

        product.setProductId( productDto.getProductId() );
        product.setProductCode( productDto.getProductCode() );
        product.setProductName( productDto.getProductName() );
        product.setProductType( productDto.getProductType() );
        product.setPricePerUnit( productDto.getPricePerUnit() );
        product.setUnitMeasurement( productDto.getUnitMeasurement() );
        product.setQty( productDto.getQty() );
        product.setProductEffectiveDate( productDto.getProductEffectiveDate() );
        product.setProductVatTax( productDto.getProductVatTax() );

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
