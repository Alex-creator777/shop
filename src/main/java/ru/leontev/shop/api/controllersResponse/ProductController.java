package ru.leontev.shop.api.controllersResponse;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import ru.leontev.shop.dto.response.ProductResponseDto;
import ru.leontev.shop.service.serviceResponse.ProductService;

@RestController
@RequestMapping("/products") // Базовый URL для всех методов контроллера
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }


    @Operation(summary = "Получает список всех товаров с пагинацией")
    @GetMapping
    public Page<ProductResponseDto> getAllProducts(
            @RequestParam(required = false, defaultValue = "0") Integer page,
            @RequestParam(required = false, defaultValue = "10") Integer size) {
        return productService.getAllProducts(page, size);
    }


    @Operation(summary = "Получает товар по id")
    @GetMapping("/{id}")
    public ProductResponseDto getProductById(@PathVariable Long id) {
        return productService.getProductById(id);
    }
}
