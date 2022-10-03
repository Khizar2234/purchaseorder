package com.ros.inventory.mapper;

import com.ros.inventory.controller.dto.ApproveViewDto;
import com.ros.inventory.controller.dto.ApproveViewDto.ApproveViewDtoBuilder;
import com.ros.inventory.controller.dto.SupplierDto1;
import com.ros.inventory.controller.dto.SupplierDto1.SupplierDto1Builder;
import com.ros.inventory.entities.PurchaseOrder;
import com.ros.inventory.entities.Supplier;
import java.time.format.DateTimeFormatter;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-10-03T20:34:09+0530",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 17.0.2 (Oracle Corporation)"
)
public class ApprovedViewMapperImpl implements ApprovedViewMapper {

    @Override
    public ApproveViewDto convertToApproveViewDto(PurchaseOrder purchase) {
        if ( purchase == null ) {
            return null;
        }

        ApproveViewDtoBuilder approveViewDto = ApproveViewDto.builder();

        approveViewDto.supplier( supplierToSupplierDto1( purchase.getSupplier() ) );
        approveViewDto.purchasedNumber( purchase.getPurchasedId() );
        if ( purchase.getPurchaseOrderDate() != null ) {
            approveViewDto.purchaseDate( DateTimeFormatter.ISO_LOCAL_DATE.format( purchase.getPurchaseOrderDate() ) );
        }

        return approveViewDto.build();
    }

    protected SupplierDto1 supplierToSupplierDto1(Supplier supplier) {
        if ( supplier == null ) {
            return null;
        }

        SupplierDto1Builder supplierDto1 = SupplierDto1.builder();

        supplierDto1.supplierName( supplier.getSupplierName() );
        if ( supplier.getSupplierType() != null ) {
            supplierDto1.supplierType( supplier.getSupplierType().name() );
        }

        return supplierDto1.build();
    }
}
