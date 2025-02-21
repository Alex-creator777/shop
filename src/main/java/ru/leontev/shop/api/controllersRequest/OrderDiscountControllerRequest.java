package ru.leontev.shop.api.controllersRequest;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.leontev.shop.dto.request.OrderDiscountRequestDto;
import ru.leontev.shop.dto.response.OrderDiscountResponseDto;
import ru.leontev.shop.service.serviceRequest.OrderDiscountServiceRequest;
import jakarta.validation.Valid;

/**
 * OrderDiscountControllerRequest предоставляет REST API для применения скидки к заказу.
 * Взаимодействует с OrderDiscountServiceRequest.
 */
@RestController
@RequestMapping("/api/order-discounts")
public class OrderDiscountControllerRequest {
    private final OrderDiscountServiceRequest orderDiscountServiceRequest;

    public OrderDiscountControllerRequest(OrderDiscountServiceRequest orderDiscountServiceRequest) {
        this.orderDiscountServiceRequest = orderDiscountServiceRequest;
    }

    @Operation(summary = "Применяет скидку к заказу")
    @PostMapping
    public ResponseEntity<OrderDiscountResponseDto> applyDiscount(@Valid @RequestBody OrderDiscountRequestDto dto) {
        OrderDiscountResponseDto responseDto = orderDiscountServiceRequest.applyDiscount(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseDto);
    }
}
