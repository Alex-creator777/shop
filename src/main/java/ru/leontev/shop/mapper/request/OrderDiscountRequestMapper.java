package ru.leontev.shop.mapper.request;

import org.mapstruct.Mapper;
import ru.leontev.shop.dto.request.OrderDiscountRequestDto;
import ru.leontev.shop.model.OrderDiscountEntity;
import ru.leontev.shop.model.OrderEntity;
import ru.leontev.shop.model.DiscountEntity;

/**
 * OrderDiscountRequestMapper преобразует OrderDiscountRequestDto в OrderDiscountEntity.
 * Для этого требуется получить объекты OrderEntity и DiscountEntity, соответствующие переданным ID.
 */
@Mapper(componentModel = "spring")
public interface OrderDiscountRequestMapper {
    default OrderDiscountEntity toEntity(OrderDiscountRequestDto dto, OrderEntity order, DiscountEntity discount) {
        if(dto == null) {
            return null;
        }
        OrderDiscountEntity entity = new OrderDiscountEntity();
        entity.setOrder(order);
        entity.setDiscount(discount);
        entity.setDiscountValue(dto.getDiscountValue());
        entity.setAppliedAt(java.time.LocalDateTime.now());
        return entity;
    }
}
