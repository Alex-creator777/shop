package ru.leontev.shop.mapper.response;

import org.mapstruct.Mapper;
import ru.leontev.shop.dto.response.CategoryResponseDto;
import ru.leontev.shop.model.*;

@Mapper(componentModel = "spring")
public interface CategoryResponseMapper {
    CategoryResponseDto categoryToCategoryDto(CategoryEntity categoryEntity);
   }
