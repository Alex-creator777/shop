package ru.leontev.shop.api.controllersRequest;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.leontev.shop.dto.request.PaymentRequestDto;
import ru.leontev.shop.dto.response.PaymentResponseDto;
import ru.leontev.shop.service.serviceRequest.PaymentServiceRequest;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/payments")
public class PaymentControllerRequest {

    private final PaymentServiceRequest paymentServiceRequest;

    public PaymentControllerRequest(PaymentServiceRequest paymentServiceRequest) {
        this.paymentServiceRequest = paymentServiceRequest;
    }

    @Operation(summary = "Создает запись платежа для заказа")
    @PostMapping
    public ResponseEntity<PaymentResponseDto> createPayment(@Valid @RequestBody PaymentRequestDto dto) {
        PaymentResponseDto responseDto = paymentServiceRequest.createPayment(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseDto);
    }

    @Operation(summary = "Обновляет статус платежа")
    @PutMapping("/{id}/status")
    public ResponseEntity<PaymentResponseDto> updatePaymentStatus(
            @PathVariable Long id,
            @RequestParam Integer newStatus,
            @RequestParam(required = false) String transactionId,
            @RequestParam(required = false) String errorMessage) {
        PaymentResponseDto responseDto = paymentServiceRequest.updatePaymentStatus(id, newStatus, transactionId, errorMessage);
        return ResponseEntity.ok(responseDto);
    }
}
