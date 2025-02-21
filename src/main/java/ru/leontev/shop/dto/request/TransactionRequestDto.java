package ru.leontev.shop.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import java.math.BigDecimal;

@Data
public class TransactionRequestDto {

    @NotNull(message = "Payment ID is required")
    private Long paymentId;

    @NotNull(message = "Event type is required")
    private String eventType;

    @NotNull(message = "Amount is required")
    private BigDecimal amount;

    private String message;
}
