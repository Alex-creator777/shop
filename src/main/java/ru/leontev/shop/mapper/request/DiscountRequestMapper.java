package ru.leontev.shop.mapper.request;

import org.mapstruct.Mapper;
import ru.leontev.shop.dto.request.DiscountRequestDto;
import ru.leontev.shop.model.DiscountEntity;

/**
 * DiscountRequestMapper преобразует DiscountRequestDto в DiscountEntity.
 * Используется при создании и обновлении скидок.
 */
@Mapper(componentModel = "spring")
public interface DiscountRequestMapper {
    DiscountEntity toEntity(DiscountRequestDto dto);
}
