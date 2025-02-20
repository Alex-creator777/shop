package ru.leontev.shop.mapper.response;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.leontev.shop.dto.response.CartItemResponseDto;
import ru.leontev.shop.model.CartItemEntity;

@Mapper(componentModel = "spring")
public interface CartItemResponseMapper {

    @Mapping(target = "cartId", source = "cartEntity.id")
    CartItemResponseDto cartItemEntityToCartItemResponseDto(CartItemEntity cartItemEntity);
}
