package ru.leontev.shop.api.controllersResponse;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.web.bind.annotation.*;
import ru.leontev.shop.dto.response.PaymentResponseDto;
import ru.leontev.shop.service.serviceResponse.PaymentServiceResponse;

@RestController
@RequestMapping("/payments")
public class PaymentControllerResponse {

    private final PaymentServiceResponse paymentServiceResponse;

    public PaymentControllerResponse(PaymentServiceResponse paymentServiceResponse) {
        this.paymentServiceResponse = paymentServiceResponse;
    }

    @Operation(summary = "Получает платеж по ID")
    @GetMapping("/{id}")
    public PaymentResponseDto getPaymentById(@PathVariable Long id) {
        return paymentServiceResponse.getPaymentById(id);
    }

    @Operation(summary = "Получает платеж по ID заказа")
    @GetMapping("/order/{orderId}")
    public PaymentResponseDto getPaymentByOrderId(@PathVariable Long orderId) {
        return paymentServiceResponse.getPaymentByOrderId(orderId);
    }
}
