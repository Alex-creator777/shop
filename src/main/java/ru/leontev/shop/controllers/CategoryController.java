package ru.leontev.shop.controllers;

import io.swagger.v3.oas.annotations.Operation;
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

    @Operation(summary = "получение списка все товаров")
    @GetMapping
    public List<CategoryResponseDto> getAllCategories() {
        return categoryService.getAllCategories();
    }

    @Operation(summary = "получение списка товара по id")
    @GetMapping("/{id}")
    public CategoryResponseDto getCategoryById(@PathVariable Long id) {
        return categoryService.getCategoryById(id);
    }
}
