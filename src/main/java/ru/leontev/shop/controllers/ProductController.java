package ru.leontev.shop.controllers;

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

    // Получение списка всех товаров
    @GetMapping
    public List<ProductResponseDto> getAllProducts() {
        return productService.getAllProducts();
    }

    // Получение одного товара по ID
    @GetMapping("/{id}")
    public ProductResponseDto getProductById(@PathVariable Long id) {
        return productService.getProductById(id);
    }
}
