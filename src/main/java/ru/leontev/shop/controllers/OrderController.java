package ru.leontev.shop.controllers;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.web.bind.annotation.*;
import ru.leontev.shop.dto.response.OrderResponseDto;
import ru.leontev.shop.service.OrderService;


import java.util.List;

@RestController
@RequestMapping("/order") // Базовый URL для всех методов контроллера
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @Operation(summary = "получение списка всех заказов")
    @GetMapping
    public List<OrderResponseDto> getAllOrders() {
        return orderService.getAllOrders();
    }

    @Operation(summary = "получение заказа по id")
    @GetMapping("/{id}")
    public OrderResponseDto getOrderById(@PathVariable Long id) {
        return orderService.getOrderById(id);
    }
}
