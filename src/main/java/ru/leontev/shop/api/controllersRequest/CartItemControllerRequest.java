package ru.leontev.shop.api.controllersRequest;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.leontev.shop.dto.request.CartItemRequestDto;
import ru.leontev.shop.dto.response.CartItemResponseDto;
import ru.leontev.shop.service.serviceRequest.CartItemServiceRequest;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/cart-items")
public class CartItemControllerRequest {

    private final CartItemServiceRequest cartItemService;

    public CartItemControllerRequest(CartItemServiceRequest cartItemService) {
        this.cartItemService = cartItemService;
    }

    @Operation(summary = "Добавляет новый товар в корзину")
    @PostMapping
    public ResponseEntity<CartItemResponseDto> addCartItem(@Valid @RequestBody CartItemRequestDto dto) {
        CartItemResponseDto responseDto = cartItemService.addCartItem(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseDto);
    }
}
