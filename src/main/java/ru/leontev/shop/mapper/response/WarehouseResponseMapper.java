package ru.leontev.shop.mapper.response;

import org.mapstruct.Mapper;
import ru.leontev.shop.dto.response.WarehouseResponseDto;
import ru.leontev.shop.model.*;

@Mapper(componentModel = "spring")
public interface WarehouseResponseMapper {
    WarehouseResponseDto warehouseToWarehouseDto(WarehouseEntity warehouseEntity);
  }
