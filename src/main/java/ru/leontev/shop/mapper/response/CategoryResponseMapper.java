package ru.leontev.shop.mapper.response;

import org.mapstruct.Mapper;
import ru.leontev.shop.dto.response.CategoryResponseDto;
import ru.leontev.shop.model.*;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CategoryResponseMapper {

    CategoryResponseDto categoryToCategoryDto(CategoryEntity categoryEntity);

    List<CategoryResponseDto> categoriesToCategoryDtos(List<CategoryEntity> categoryEntities);
}

