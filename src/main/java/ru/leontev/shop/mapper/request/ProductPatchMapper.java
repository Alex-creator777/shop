package ru.leontev.shop.mapper.request;

import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import ru.leontev.shop.dto.request.ProductPatchRequestDto;
import ru.leontev.shop.model.ProductEntity;

@Mapper(componentModel = "spring")
public interface ProductPatchMapper {

    // Если значение поля в DTO равно null, то соответствующее поле сущности не обновляется
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    // Не обновляем идентификатор и категорию (категорию обновим отдельно)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "category", ignore = true)
    void updateProductFromPatch(ProductPatchRequestDto dto, @MappingTarget ProductEntity productEntity);
}
