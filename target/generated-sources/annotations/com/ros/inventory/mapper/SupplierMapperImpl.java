package com.ros.inventory.mapper;

import com.ros.inventory.controller.dto.SupplierDto;
import com.ros.inventory.controller.dto.SupplierDto.SupplierDtoBuilder;
import com.ros.inventory.entities.Supplier;
import com.ros.inventory.entities.SupplierBasicInfo;
import com.ros.inventory.entities.SupplierContact;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-10-03T20:34:09+0530",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 17.0.2 (Oracle Corporation)"
)
public class SupplierMapperImpl implements SupplierMapper {

    @Override
    public SupplierDto convertToSupplierDto(Supplier supplier) {
        if ( supplier == null ) {
            return null;
        }

        SupplierDtoBuilder supplierDto = SupplierDto.builder();

        supplierDto.supplierName( supplierSupplierBasicSupplierBusinessName( supplier ) );
        if ( supplier.getSupplierType() != null ) {
            supplierDto.supplierType( supplier.getSupplierType().name() );
        }
        supplierDto.email( supplierSupplierContactEmail( supplier ) );
        supplierDto.mobile( supplierSupplierContactMobile( supplier ) );

        return supplierDto.build();
    }

    private String supplierSupplierBasicSupplierBusinessName(Supplier supplier) {
        if ( supplier == null ) {
            return null;
        }
        SupplierBasicInfo supplierBasic = supplier.getSupplierBasic();
        if ( supplierBasic == null ) {
            return null;
        }
        String supplierBusinessName = supplierBasic.getSupplierBusinessName();
        if ( supplierBusinessName == null ) {
            return null;
        }
        return supplierBusinessName;
    }

    private String supplierSupplierContactEmail(Supplier supplier) {
        if ( supplier == null ) {
            return null;
        }
        SupplierContact supplierContact = supplier.getSupplierContact();
        if ( supplierContact == null ) {
            return null;
        }
        String email = supplierContact.getEmail();
        if ( email == null ) {
            return null;
        }
        return email;
    }

    private long supplierSupplierContactMobile(Supplier supplier) {
        if ( supplier == null ) {
            return 0L;
        }
        SupplierContact supplierContact = supplier.getSupplierContact();
        if ( supplierContact == null ) {
            return 0L;
        }
        long mobile = supplierContact.getMobile();
        return mobile;
    }
}
