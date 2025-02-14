package ru.leontev.shop.mapper.request;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.leontev.shop.dto.request.ProductRequestDto;
import ru.leontev.shop.model.CategoryEntity;
import ru.leontev.shop.model.ProductEntity;

@Mapper(componentModel = "spring")
public interface ProductRequestMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "category", source = "categoryEntity")
    @Mapping(target = "name", source = "dto.name")
    @Mapping(target = "description", source = "dto.description")
    @Mapping(target = "price", source = "dto.price")
    @Mapping(target = "voltage", source = "dto.voltage")
    @Mapping(target = "power", source = "dto.power")
    @Mapping(target = "connectorType", source = "dto.connectorType")
    ProductEntity toEntity(ProductRequestDto dto, CategoryEntity categoryEntity);
}
