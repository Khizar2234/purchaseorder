package com.ros.inventory.mapper;

import com.ros.inventory.controller.dto.CloseStockDto;
import com.ros.inventory.controller.dto.CloseStockDto.CloseStockDtoBuilder;
import com.ros.inventory.entities.CloseStock;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-09-29T14:46:45+0530",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 11.0.16 (Oracle Corporation)"
)
public class CloseStockDtoMapperImpl implements CloseStockDtoMapper {

    @Override
    public CloseStockDto convertToCloseStockDto(CloseStock close) {
        if ( close == null ) {
            return null;
        }

        CloseStockDtoBuilder closeStockDto = CloseStockDto.builder();

        closeStockDto.stockID( close.getStockID() );
        closeStockDto.stock_start_date( close.getStock_start_date() );
        closeStockDto.stock_end_date( close.getStock_end_date() );
        closeStockDto.opening_stock_value( close.getOpening_stock_value() );
        closeStockDto.closing_stock_value( close.getClosing_stock_value() );
        closeStockDto.cost_of_sales( close.getCost_of_sales() );

        return closeStockDto.build();
    }

    @Override
    public CloseStock convertToEntity(CloseStockDto closeDto) {
        if ( closeDto == null ) {
            return null;
        }

        CloseStock closeStock = new CloseStock();

        closeStock.setStockID( closeDto.getStockID() );
        closeStock.setStock_start_date( closeDto.getStock_start_date() );
        closeStock.setStock_end_date( closeDto.getStock_end_date() );
        closeStock.setOpening_stock_value( closeDto.getOpening_stock_value() );
        closeStock.setClosing_stock_value( closeDto.getClosing_stock_value() );
        closeStock.setCost_of_sales( closeDto.getCost_of_sales() );

        return closeStock;
    }
}
