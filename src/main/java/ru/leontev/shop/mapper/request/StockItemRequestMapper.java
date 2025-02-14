package ru.leontev.shop.mapper.request;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.leontev.shop.dto.request.StockItemRequestDto;
import ru.leontev.shop.model.StockItemEntity;
import ru.leontev.shop.model.ProductEntity;
import ru.leontev.shop.model.WarehouseEntity;

@Mapper(componentModel = "spring")
public interface StockItemRequestMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "productEntity", source = "product")
    @Mapping(target = "warehouse", source = "warehouse")
    // Устанавливаем дату обновления как текущее время
    @Mapping(target = "lastUpdatedDate", expression = "java(java.time.LocalDateTime.now())")
    StockItemEntity toEntity(StockItemRequestDto dto, ProductEntity product, WarehouseEntity warehouse);
}
