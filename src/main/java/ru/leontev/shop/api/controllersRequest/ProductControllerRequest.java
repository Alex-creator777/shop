package ru.leontev.shop.api.controllersRequest;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.leontev.shop.dto.request.ProductRequestDto;
import ru.leontev.shop.dto.response.ProductResponseDto;
import ru.leontev.shop.service.serviceRequest.ProductServiceRequest;
import ru.leontev.shop.service.serviceResponse.ProductService;
// добавление продукта в каталог
@RestController
@RequestMapping("/products")
public class ProductControllerRequest {

    private final ProductService productService;
    private final ProductServiceRequest productServiceRequest;

    public ProductControllerRequest(ProductService productService, ProductServiceRequest productServiceRequest) {
        this.productService = productService;
        this.productServiceRequest = productServiceRequest;
    }

    @Operation(summary = "Добавляет новый товар")
    @PostMapping
    public ResponseEntity<ProductResponseDto> createProduct(@RequestBody ProductRequestDto productRequestDto) {
        ProductResponseDto createdProduct = productServiceRequest.createProduct(productRequestDto);
        return ResponseEntity.ok(createdProduct);
    }
}
