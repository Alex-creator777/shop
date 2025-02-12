package ru.leontev.shop.mapper.response;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.leontev.shop.dto.response.WarehouseResponseDto;
import ru.leontev.shop.model.WarehouseEntity;

import java.util.List;

@Mapper(componentModel = "spring")
public interface WarehouseResponseMapper {
    WarehouseResponseDto warehouseToWarehouseDto(WarehouseEntity warehouseEntity);
    List<WarehouseResponseDto> toWarehouseDtoList(List<WarehouseEntity> warehouseEntities);
}
