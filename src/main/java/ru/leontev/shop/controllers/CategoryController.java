package ru.leontev.shop.controllers;

import org.springframework.web.bind.annotation.*;
import ru.leontev.shop.dto.response.CategoryResponseDto;
import ru.leontev.shop.service.CategoryService;


import java.util.List;

@RestController
@RequestMapping("/category") // Базовый URL для всех методов контроллера
public class CategoryController {

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryServiceService) {
        this.categoryService = categoryServiceService;
    }

    // Получение списка всех товаров
    @GetMapping
    public List<CategoryResponseDto> getAllCategories() {
        return categoryService.getAllCategories();
    }

    // Получение одного товара по ID
    @GetMapping("/{id}")
    public CategoryResponseDto getCategoryById(@PathVariable Long id) {
        return categoryService.getCategoryById(id);
    }
}
