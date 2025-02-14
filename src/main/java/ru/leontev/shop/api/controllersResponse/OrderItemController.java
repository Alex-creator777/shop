package ru.leontev.shop.api.controllersResponse;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.web.bind.annotation.*;
import ru.leontev.shop.dto.response.OrderItemResponseDto;
import ru.leontev.shop.service.serviceResponse.OrderItemService;


import java.util.List;

@RestController
@RequestMapping("/orderItem") // Базовый URL для всех методов контроллера
public class OrderItemController {

    private final OrderItemService orderItemService;

    public OrderItemController(OrderItemService orderItemService) {
        this.orderItemService = orderItemService;
    }

    @Operation(summary = "Получает список всех позиций товаров на складе")
    @GetMapping
    public List<OrderItemResponseDto> getAllOrderItems() {
        return orderItemService.getAllOrderItems();
    }

    @Operation(summary = "Получает позицию товара на складе по id")
    @GetMapping("/{id}")
    public OrderItemResponseDto getOrderItemById(@PathVariable Long id) {
        return orderItemService.getOrderItemById(id);
    }
}
