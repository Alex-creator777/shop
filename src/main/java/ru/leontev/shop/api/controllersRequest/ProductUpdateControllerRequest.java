package ru.leontev.shop.api.controllersRequest;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.leontev.shop.dto.request.ProductUpdateRequestDto;
import ru.leontev.shop.dto.response.ProductResponseDto;
import ru.leontev.shop.service.serviceRequest.ProductUpdateService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/products")
public class ProductUpdateControllerRequest {

    private final ProductUpdateService productUpdateService;

    public ProductUpdateControllerRequest(ProductUpdateService productUpdateService) {
        this.productUpdateService = productUpdateService;
    }

    @Operation(
            summary = "Обновление продукта",
            description = "Обновляет данные продукта по указанному ID. Перед обновлением проверяет, что ID в URL совпадает с ID в запросе."
    )
    @PutMapping("/{id}")
    public ResponseEntity<ProductResponseDto> updateProduct(
            @PathVariable Long id,
            @Valid @RequestBody ProductUpdateRequestDto dto) {

        // Проверка, что id из пути совпадает с id из DTO (опционально)
        if (!id.equals(dto.getId())) {
            return ResponseEntity.badRequest().build();
        }

        ProductResponseDto updatedProduct = productUpdateService.updateProduct(id, dto);
        return ResponseEntity.ok(updatedProduct);
    }
}
