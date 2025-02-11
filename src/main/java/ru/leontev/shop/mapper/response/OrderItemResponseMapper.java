package ru.leontev.shop.mapper.response;

import org.mapstruct.Mapper;
import ru.leontev.shop.dto.response.OrderItemResponseDto;
import ru.leontev.shop.model.*;

@Mapper(componentModel = "spring")
public interface OrderItemResponseMapper {
    OrderItemResponseDto orderItemToOrderItemDto(OrderItemEntity orderItemEntity);
  }
