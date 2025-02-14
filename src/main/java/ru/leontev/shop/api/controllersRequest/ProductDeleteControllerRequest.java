package ru.leontev.shop.api.controllersRequest;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.leontev.shop.service.serviceRequest.ProductDeleteService;

@RestController
@RequestMapping("/api/products")
public class ProductDeleteControllerRequest {

    private final ProductDeleteService productDeleteService;

    public ProductDeleteControllerRequest(ProductDeleteService productDeleteService) {
        this.productDeleteService = productDeleteService;
    }

    @Operation(
            summary = "Удаление продукта - установка флага, с сохранением всех данных",
            description = "Скрывает продукт по указанному ID, при успешном выполнении возвращает 204 код"
    )
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> softDeleteProduct(@PathVariable Long id) {
        productDeleteService.softDeleteProduct(id);
        return ResponseEntity.noContent().build();
    }

    @Operation(
            summary = "Восстановление продукта",
            description = "Скрывает продукт по указанному ID, при успешном выполнении возвращает 200 код"
    )
    @PutMapping("/{id}/restore")
    public ResponseEntity<Void> restoreProduct(@PathVariable Long id) {
        productDeleteService.restoreProduct(id);
        return ResponseEntity.ok().build(); // 200 OK без тела ответа
    }


}
