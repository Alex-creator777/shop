package ru.leontev.shop.mapper.request;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import ru.leontev.shop.dto.request.ProductUpdateRequestDto;
import ru.leontev.shop.model.ProductEntity;

@Mapper(componentModel = "spring")
public interface ProductUpdateMapper {

    // Обновляет поля существующей сущности из DTO.
    // Поле category здесь игнорируется, так как его обновление требует получения объекта CategoryEntity из репозитория.
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "category", ignore = true)
    void updateProductFromDto(ProductUpdateRequestDto dto, @MappingTarget ProductEntity productEntity);
}
