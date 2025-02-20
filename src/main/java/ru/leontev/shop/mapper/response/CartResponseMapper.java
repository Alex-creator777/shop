package ru.leontev.shop.mapper.response;

import org.mapstruct.Mapper;
import ru.leontev.shop.dto.response.CartResponseDto;
import ru.leontev.shop.model.CartEntity;

@Mapper(componentModel = "spring")
public interface CartResponseMapper {
    CartResponseDto cartEntityToCartResponseDto(CartEntity cartEntity);
}
