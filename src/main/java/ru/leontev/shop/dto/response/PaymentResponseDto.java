package ru.leontev.shop.dto.response;

import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class PaymentResponseDto {
    private Long id;
    private Long orderId;
    // Отображаем статус в виде строки (например, "PAID", "DECLINED", "PENDING")
    private String paymentStatus;
    private String paymentMethod;
    private String transactionId;
    private LocalDateTime paymentDate;
    private BigDecimal amount;
    private String errorMessage;
}
