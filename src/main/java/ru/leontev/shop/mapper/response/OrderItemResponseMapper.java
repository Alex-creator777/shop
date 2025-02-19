package ru.leontev.shop.mapper.response;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.leontev.shop.dto.response.OrderItemResponseDto;
import ru.leontev.shop.model.OrderItemEntity;

import java.util.List;

@Mapper(componentModel = "spring")
public interface OrderItemResponseMapper {

    @Mapping(target = "orderId", source = "order.id")
    @Mapping(target = "productId", source = "productEntity.id")
    OrderItemResponseDto orderItemToOrderItemDto(OrderItemEntity orderItemEntity);

    List<OrderItemResponseDto> orderItemsToOrderItemDtos(List<OrderItemEntity> orderItemEntities);
}
