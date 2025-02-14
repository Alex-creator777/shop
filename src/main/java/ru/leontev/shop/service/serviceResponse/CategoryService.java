package ru.leontev.shop.service.serviceResponse;

import org.springframework.stereotype.Service;
import ru.leontev.shop.dto.response.CategoryResponseDto;
import ru.leontev.shop.mapper.response.CategoryResponseMapper;
import ru.leontev.shop.model.CategoryEntity;
import ru.leontev.shop.repository.CategoryRepository;

import java.util.List;

@Service
public class CategoryService {
    private final CategoryRepository categoryRepository;
    private final CategoryResponseMapper categoryResponseMapper;

    public CategoryService(CategoryRepository categoryRepository, CategoryResponseMapper categoryResponseMapper) {
        this.categoryRepository = categoryRepository;
        this.categoryResponseMapper = categoryResponseMapper;
    }

    public List<CategoryResponseDto> getAllCategories() {
        List<CategoryEntity> categories = categoryRepository.findAll();
        return categoryResponseMapper.categoriesToCategoryDtos(categories);
    }


    public CategoryResponseDto getCategoryById(Long id) {
        CategoryEntity category = categoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Category not found"));
        return categoryResponseMapper.categoryToCategoryDto(category);
    }
}
