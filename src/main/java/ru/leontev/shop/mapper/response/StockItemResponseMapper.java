package ru.leontev.shop.mapper.response;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.leontev.shop.dto.response.StockItemResponseDto;
import ru.leontev.shop.model.StockItemEntity;

import java.util.List;

@Mapper(componentModel = "spring")
public interface StockItemResponseMapper {
    @Mapping(target = "productId", source = "productEntity.id")
    @Mapping(target = "warehouseId", source = "warehouse.id")
    StockItemResponseDto stockItemToStockItemDto(StockItemEntity stockItemEntity);

    List<StockItemResponseDto> stockItemsToStockItemDtos(List<StockItemEntity> stockItemEntities);
}
