package ru.leontev.shop.controllers;

import org.springframework.web.bind.annotation.*;
import ru.leontev.shop.dto.response.OrderItemResponseDto;
import ru.leontev.shop.service.OrderItemService;


import java.util.List;

@RestController
@RequestMapping("/orderItem") // Базовый URL для всех методов контроллера
public class OrderItemController {

    private final OrderItemService orderItemService;

    public OrderItemController(OrderItemService orderItemService) {
        this.orderItemService = orderItemService;
    }

    @GetMapping
    public List<OrderItemResponseDto> getAllOrderItems() {
        return orderItemService.getAllOrderItems();
    }

    @GetMapping("/{id}")
    public OrderItemResponseDto getOrderItemById(@PathVariable Long id) {
        return orderItemService.getOrderItemById(id);
    }
}
