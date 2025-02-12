package ru.leontev.shop.mapper.response;

import org.mapstruct.Mapper;
import ru.leontev.shop.dto.response.RestockResponseDto;
import ru.leontev.shop.model.RestockEntity;

import java.util.List;

@Mapper(componentModel = "spring")
public interface RestockResponseMapper {
    RestockResponseDto restockToRestockDto(RestockEntity restockEntity);

    List<RestockResponseDto> restocksToRestockDtos(List<RestockEntity> restockEntities);
}
