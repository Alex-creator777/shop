package ru.leontev.shop.mapper.response;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.leontev.shop.dto.response.OrderResponseDto;
import ru.leontev.shop.model.OrderEntity;

/**
 * Маппер для преобразования OrderEntity -> OrderResponseDto,
 * который при маппинге списка позиций заказа
 * (OrderItemEntity -> OrderItemResponseDto)
 * будет использовать OrderItemResponseMapper.
 */
@Mapper(
        componentModel = "spring",
        uses = { OrderItemResponseMapper.class } // <-- ВАЖНО: говорим, что мы используем другой маппер
)
public interface OrderResponseMapper {

    // Поле "status" берем из orderEntity.getOrderStatus().name()
    @Mapping(target = "status", expression = "java(orderEntity.getOrderStatus().name())")
    // Поле "customerId" берем из orderEntity.customer.id
    @Mapping(target = "customerId", source = "customer.id")
    // Поле "orderItems" берем из orderEntity.orderItemEntities,
    // а какой маппер вызывать для каждого OrderItemEntity, решит MapStruct,
    // увидев, что есть OrderItemResponseMapper#orderItemsToOrderItemDtos(...)
    @Mapping(target = "orderItems", source = "orderItemEntities")
    OrderResponseDto orderToOrderDto(OrderEntity orderEntity);
}
