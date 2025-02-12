package ru.leontev.shop.controllers;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.data.domain.Page;
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

    @Operation(summary = "Получение списка всех заказов с пагинацией")
    @GetMapping
    public Page<OrderResponseDto> getAllOrders(
            @RequestParam(required = false, defaultValue = "0") Integer page,
            @RequestParam(required = false, defaultValue = "10") Integer size) {
        return orderService.getAllOrders(page, size);
    }


    @Operation(summary = "получение заказа по id")
    @GetMapping("/{id}")
    public OrderResponseDto getOrderById(@PathVariable Long id) {
        return orderService.getOrderById(id);
    }
}
