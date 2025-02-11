package ru.leontev.shop.mapper.response;

import org.mapstruct.Mapper;
import ru.leontev.shop.dto.response.StockItemResponseDto;
import ru.leontev.shop.model.*;

@Mapper(componentModel = "spring")
public interface StockItemResponseMapper {
    StockItemResponseDto stockItemToStockItemDto(StockItemEntity stockItemEntity);
}
