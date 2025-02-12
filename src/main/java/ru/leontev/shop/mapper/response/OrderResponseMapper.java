package ru.leontev.shop.mapper.response;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.leontev.shop.dto.response.OrderResponseDto;
import ru.leontev.shop.model.*;

@Mapper(componentModel = "spring")
public interface OrderResponseMapper {
    @Mapping(target = "status", expression = "java(orderEntity.getOrderStatus().name())") // Конвертируем код в строку
    OrderResponseDto orderToOrderDto(OrderEntity orderEntity);
}
