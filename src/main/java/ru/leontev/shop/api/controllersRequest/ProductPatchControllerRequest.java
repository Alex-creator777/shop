package ru.leontev.shop.api.controllersRequest;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.leontev.shop.dto.request.ProductPatchRequestDto;
import ru.leontev.shop.dto.response.ProductResponseDto;
import ru.leontev.shop.service.serviceRequest.ProductPatchService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/products")
public class ProductPatchControllerRequest {

    private final ProductPatchService productPatchService;

    public ProductPatchControllerRequest(ProductPatchService productPatchService) {
        this.productPatchService = productPatchService;
    }

    @Operation(
            summary = "Частичное обновление продукта",
            description = "Обновляет отдельные поля продукта по ID. Передаются только те поля, которые необходимо изменить."
    )
    @PatchMapping("/{id}")
    public ResponseEntity<ProductResponseDto> patchProduct(
            @PathVariable Long id,
            @Valid @RequestBody ProductPatchRequestDto dto) {

        ProductResponseDto updatedProduct = productPatchService.patchProduct(id, dto);
        return ResponseEntity.ok(updatedProduct);
    }
}
