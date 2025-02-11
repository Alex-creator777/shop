package ru.leontev.shop.mapper.response;

import org.mapstruct.Mapper;
import ru.leontev.shop.dto.response.OrderResponseDto;
import ru.leontev.shop.model.*;

@Mapper(componentModel = "spring")
public interface OrderResponseMapper {
    OrderResponseDto orderToOrderDto(OrderEntity orderEntity);
   }
