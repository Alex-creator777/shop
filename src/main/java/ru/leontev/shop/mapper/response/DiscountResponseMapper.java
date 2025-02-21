package ru.leontev.shop.mapper.response;

import org.mapstruct.Mapper;
import ru.leontev.shop.dto.response.DiscountResponseDto;
import ru.leontev.shop.model.DiscountEntity;

/**
 * DiscountResponseMapper преобразует DiscountEntity в DiscountResponseDto.
 * Используется для отправки данных о скидке клиенту.
 */
@Mapper(componentModel = "spring")
public interface DiscountResponseMapper {
    DiscountResponseDto toDto(DiscountEntity entity);
}
