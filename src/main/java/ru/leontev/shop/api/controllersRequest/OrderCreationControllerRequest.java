package ru.leontev.shop.api.controllersRequest;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.leontev.shop.dto.request.OrderFinalizationRequestDto;
import ru.leontev.shop.dto.response.OrderResponseDto;
import ru.leontev.shop.service.serviceRequest.OrderCreationServiceRequest;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/orders")
public class OrderCreationControllerRequest {

    private final OrderCreationServiceRequest orderCreationService;

    public OrderCreationControllerRequest(OrderCreationServiceRequest orderCreationService) {
        this.orderCreationService = orderCreationService;
    }

    @Operation(summary = "Оформляет заказ из корзины")
    @PostMapping("/from-cart")
    public ResponseEntity<OrderResponseDto> createOrderFromCart(@Valid @RequestBody OrderFinalizationRequestDto dto) {
        OrderResponseDto responseDto = orderCreationService.createOrderFromCart(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseDto);
    }
}
