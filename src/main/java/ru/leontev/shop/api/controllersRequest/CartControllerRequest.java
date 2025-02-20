package ru.leontev.shop.api.controllersRequest;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.leontev.shop.dto.request.CartRequestDto;
import ru.leontev.shop.dto.response.CartResponseDto;
import ru.leontev.shop.service.serviceRequest.CartServiceRequest;

import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/carts")
public class CartControllerRequest {

    private final CartServiceRequest cartService;

    public CartControllerRequest(CartServiceRequest cartService) {
        this.cartService = cartService;
    }

    @Operation(summary = "Создание новой корзины")
    @PostMapping
    public ResponseEntity<CartResponseDto> createCart(@Valid @RequestBody CartRequestDto dto) {
        CartResponseDto responseDto = cartService.createCart(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseDto);
    }

    @Operation(summary = "Извлекает корзину по идентификатору, также вычисляет итоговую сумму всех позиций корзины")
    @GetMapping("/{id}")
    public ResponseEntity<CartResponseDto> getCartById(@PathVariable Long id) {
        CartResponseDto responseDto = cartService.getCartById(id);
        return ResponseEntity.ok(responseDto);
    }

    @Operation(summary = "Извлекает все корзины")
    @GetMapping
    public ResponseEntity<List<CartResponseDto>> getAllCarts() {
        List<CartResponseDto> carts = cartService.getAllCarts();
        return ResponseEntity.ok(carts);
    }
}
