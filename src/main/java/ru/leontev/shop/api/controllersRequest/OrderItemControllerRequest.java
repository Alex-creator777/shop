package ru.leontev.shop.api.controllersRequest;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.leontev.shop.dto.request.OrderItemRequestDto;
import ru.leontev.shop.dto.response.OrderItemResponseDto;
import ru.leontev.shop.service.serviceRequest.OrderItemServiceRequest;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/order-items")
public class OrderItemControllerRequest {

    private final OrderItemServiceRequest orderItemServiceRequest;

    public OrderItemControllerRequest(OrderItemServiceRequest orderItemServiceRequest) {
        this.orderItemServiceRequest = orderItemServiceRequest;
    }

    @Operation(summary = "Добавляет новую позицию заказа и обновляет итоговую сумму заказа")
    @PostMapping
    public ResponseEntity<OrderItemResponseDto> addOrderItem(@Valid @RequestBody OrderItemRequestDto dto) {
        OrderItemResponseDto responseDto = orderItemServiceRequest.addOrderItem(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseDto);
    }
}
