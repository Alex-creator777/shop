package ru.leontev.shop.mapper.response;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.leontev.shop.dto.response.FavoriteResponseDto;
import ru.leontev.shop.model.FavoriteEntity;

@Mapper(componentModel = "spring", uses = { ProductResponseMapper.class })
public interface FavoriteResponseMapper {
    @Mapping(target = "customerId", source = "customer.id")
    @Mapping(target = "productId", source = "product.id")
    FavoriteResponseDto favoriteEntityToFavoriteResponseDto(FavoriteEntity entity);
}
