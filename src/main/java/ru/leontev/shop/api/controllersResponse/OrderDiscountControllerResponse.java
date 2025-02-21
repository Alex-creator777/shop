package ru.leontev.shop.api.controllersResponse;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.web.bind.annotation.*;
import ru.leontev.shop.dto.response.OrderDiscountResponseDto;
import ru.leontev.shop.service.serviceResponse.OrderDiscountServiceResponse;
import java.util.List;

/**
 * OrderDiscountControllerResponse предоставляет REST API для получения данных о скидках, примененных к заказу.
 * Взаимодействует с OrderDiscountServiceResponse.
 */
@RestController
@RequestMapping("/order-discounts")
public class OrderDiscountControllerResponse {
    private final OrderDiscountServiceResponse orderDiscountServiceResponse;

    public OrderDiscountControllerResponse(OrderDiscountServiceResponse orderDiscountServiceResponse) {
        this.orderDiscountServiceResponse = orderDiscountServiceResponse;
    }

    @Operation(summary = "Получает скидки, примененные к заказу, по ID заказа")
    @GetMapping("/order/{orderId}")
    public List<OrderDiscountResponseDto> getDiscountsByOrder(@PathVariable Long orderId) {
        return orderDiscountServiceResponse.getDiscountsByOrderId(orderId);
    }
}
