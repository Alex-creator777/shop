package ru.leontev.shop.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import java.math.BigDecimal;

@Data
public class PaymentRequestDto {

    @NotNull(message = "Order ID is required")
    private Long orderId;

    @NotNull(message = "Payment method is required")
    private String paymentMethod;

    @NotNull(message = "Amount is required")
    private BigDecimal amount;

    // Если не указан, можно установить по умолчанию статус PENDING
    private Integer paymentStatus;

    private String transactionId;

    private String errorMessage;
}
