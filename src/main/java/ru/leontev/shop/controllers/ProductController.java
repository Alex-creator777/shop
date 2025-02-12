package ru.leontev.shop.controllers;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.web.bind.annotation.*;
import ru.leontev.shop.dto.response.ProductResponseDto;
import ru.leontev.shop.service.ProductService;

import java.util.List;

@RestController
@RequestMapping("/products") // Базовый URL для всех методов контроллера
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }


    @Operation(summary = "Получает список всех товаров из базы данных")
    @GetMapping
    public List<ProductResponseDto> getAllProducts() {
        return productService.getAllProducts();
    }

    @Operation(summary = "Получает товар по id")
    @GetMapping("/{id}")
    public ProductResponseDto getProductById(@PathVariable Long id) {
        return productService.getProductById(id);
    }
}
