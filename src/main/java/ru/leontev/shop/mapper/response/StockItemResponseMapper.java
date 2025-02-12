package ru.leontev.shop.mapper.response;

import org.mapstruct.Mapper;
import ru.leontev.shop.dto.response.StockItemResponseDto;
import ru.leontev.shop.model.StockItemEntity;

import java.util.List;

@Mapper(componentModel = "spring")
public interface StockItemResponseMapper {
    StockItemResponseDto stockItemToStockItemDto(StockItemEntity stockItemEntity);

    List<StockItemResponseDto> stockItemsToStockItemDtos(List<StockItemEntity> stockItemEntities);
}
