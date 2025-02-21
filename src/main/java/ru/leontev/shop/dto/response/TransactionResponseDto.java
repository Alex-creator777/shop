package ru.leontev.shop.dto.response;

import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class TransactionResponseDto {
    private Long id;
    private Long paymentId;
    private String eventType;
    private LocalDateTime eventDate;
    private BigDecimal amount;
    private String message;
}
