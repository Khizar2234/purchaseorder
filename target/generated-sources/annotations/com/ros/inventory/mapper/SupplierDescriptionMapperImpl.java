package com.ros.inventory.mapper;

import com.ros.inventory.controller.dto.BankDto;
import com.ros.inventory.controller.dto.BankDto.BankDtoBuilder;
import com.ros.inventory.controller.dto.ContactDto;
import com.ros.inventory.controller.dto.ContactDto.ContactDtoBuilder;
import com.ros.inventory.controller.dto.SupplierDescriptionDto;
import com.ros.inventory.controller.dto.SupplierDescriptionDto.SupplierDescriptionDtoBuilder;
import com.ros.inventory.entities.Address;
import com.ros.inventory.entities.Supplier;
import com.ros.inventory.entities.SupplierBankInfo;
import com.ros.inventory.entities.SupplierBasicInfo;
import com.ros.inventory.entities.SupplierContact;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-09-29T14:46:45+0530",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 11.0.16 (Oracle Corporation)"
)
public class SupplierDescriptionMapperImpl implements SupplierDescriptionMapper {

    @Override
    public SupplierDescriptionDto convertToDto(Supplier supplier) {
        if ( supplier == null ) {
            return null;
        }

        SupplierDescriptionDtoBuilder supplierDescriptionDto = SupplierDescriptionDto.builder();

        if ( supplier.getSupplierType() != null ) {
            supplierDescriptionDto.supplierType( supplier.getSupplierType().name() );
        }
        supplierDescriptionDto.supplierName( supplierSupplierBasicSupplierBusinessName( supplier ) );
        supplierDescriptionDto.supplierTradeName( supplierSupplierBasicSupplierTradeName( supplier ) );
        supplierDescriptionDto.image( supplierSupplierBasicImage( supplier ) );
        supplierDescriptionDto.contact( supplierContactToContactDto( supplier.getSupplierContact() ) );
        supplierDescriptionDto.street( supplierSupplierAddressStreet( supplier ) );
        supplierDescriptionDto.area( supplierSupplierAddressArea( supplier ) );
        supplierDescriptionDto.city( supplierSupplierAddressCity( supplier ) );
        supplierDescriptionDto.state( supplierSupplierAddressState( supplier ) );
        supplierDescriptionDto.country( supplierSupplierAddressCountry( supplier ) );
        supplierDescriptionDto.pincode( supplierSupplierAddressPincode( supplier ) );
        supplierDescriptionDto.bank( supplierBankInfoToBankDto( supplier.getSupplierBank() ) );

        return supplierDescriptionDto.build();
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

    private String supplierSupplierBasicSupplierTradeName(Supplier supplier) {
        if ( supplier == null ) {
            return null;
        }
        SupplierBasicInfo supplierBasic = supplier.getSupplierBasic();
        if ( supplierBasic == null ) {
            return null;
        }
        String supplierTradeName = supplierBasic.getSupplierTradeName();
        if ( supplierTradeName == null ) {
            return null;
        }
        return supplierTradeName;
    }

    private String supplierSupplierBasicImage(Supplier supplier) {
        if ( supplier == null ) {
            return null;
        }
        SupplierBasicInfo supplierBasic = supplier.getSupplierBasic();
        if ( supplierBasic == null ) {
            return null;
        }
        String image = supplierBasic.getImage();
        if ( image == null ) {
            return null;
        }
        return image;
    }

    protected ContactDto supplierContactToContactDto(SupplierContact supplierContact) {
        if ( supplierContact == null ) {
            return null;
        }

        ContactDtoBuilder contactDto = ContactDto.builder();

        contactDto.firstName( supplierContact.getFirstName() );
        contactDto.middleName( supplierContact.getMiddleName() );
        contactDto.lastName( supplierContact.getLastName() );
        contactDto.email( supplierContact.getEmail() );
        contactDto.fax( supplierContact.getFax() );
        contactDto.mobile( supplierContact.getMobile() );
        contactDto.telephone( supplierContact.getTelephone() );

        return contactDto.build();
    }

    private String supplierSupplierAddressStreet(Supplier supplier) {
        if ( supplier == null ) {
            return null;
        }
        Address supplierAddress = supplier.getSupplierAddress();
        if ( supplierAddress == null ) {
            return null;
        }
        String street = supplierAddress.getStreet();
        if ( street == null ) {
            return null;
        }
        return street;
    }

    private String supplierSupplierAddressArea(Supplier supplier) {
        if ( supplier == null ) {
            return null;
        }
        Address supplierAddress = supplier.getSupplierAddress();
        if ( supplierAddress == null ) {
            return null;
        }
        String area = supplierAddress.getArea();
        if ( area == null ) {
            return null;
        }
        return area;
    }

    private String supplierSupplierAddressCity(Supplier supplier) {
        if ( supplier == null ) {
            return null;
        }
        Address supplierAddress = supplier.getSupplierAddress();
        if ( supplierAddress == null ) {
            return null;
        }
        String city = supplierAddress.getCity();
        if ( city == null ) {
            return null;
        }
        return city;
    }

    private String supplierSupplierAddressState(Supplier supplier) {
        if ( supplier == null ) {
            return null;
        }
        Address supplierAddress = supplier.getSupplierAddress();
        if ( supplierAddress == null ) {
            return null;
        }
        String state = supplierAddress.getState();
        if ( state == null ) {
            return null;
        }
        return state;
    }

    private String supplierSupplierAddressCountry(Supplier supplier) {
        if ( supplier == null ) {
            return null;
        }
        Address supplierAddress = supplier.getSupplierAddress();
        if ( supplierAddress == null ) {
            return null;
        }
        String country = supplierAddress.getCountry();
        if ( country == null ) {
            return null;
        }
        return country;
    }

    private long supplierSupplierAddressPincode(Supplier supplier) {
        if ( supplier == null ) {
            return 0L;
        }
        Address supplierAddress = supplier.getSupplierAddress();
        if ( supplierAddress == null ) {
            return 0L;
        }
        long pincode = supplierAddress.getPincode();
        return pincode;
    }

    protected BankDto supplierBankInfoToBankDto(SupplierBankInfo supplierBankInfo) {
        if ( supplierBankInfo == null ) {
            return null;
        }

        BankDtoBuilder bankDto = BankDto.builder();

        bankDto.bankName( supplierBankInfo.getBankName() );
        bankDto.bankBranchName( supplierBankInfo.getBankBranchName() );
        bankDto.accountHolderName( supplierBankInfo.getAccountHolderName() );
        bankDto.accountNumber( supplierBankInfo.getAccountNumber() );
        bankDto.bankIFSCCode( supplierBankInfo.getBankIFSCCode() );

        return bankDto.build();
    }
}
