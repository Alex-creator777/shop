package ru.leontev.shop.mapper.response;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.leontev.shop.dto.response.OrderDiscountResponseDto;
import ru.leontev.shop.model.OrderDiscountEntity;

/**
 * OrderDiscountResponseMapper преобразует OrderDiscountEntity в OrderDiscountResponseDto.
 */
@Mapper(componentModel = "spring")
public interface OrderDiscountResponseMapper {
    @Mapping(target = "orderId", source = "order.id")
    @Mapping(target = "discountId", source = "discount.id")
    OrderDiscountResponseDto toDto(OrderDiscountEntity entity);
}
