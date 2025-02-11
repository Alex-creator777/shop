package ru.leontev.shop.dto.response;

import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
public class OrderResponseDto {

    private Long id;
    private LocalDateTime orderDate;
    private String status;
    private BigDecimal totalAmount;
    private Long customerId; // Идентификатор клиента
    private List<OrderItemResponseDto> orderItems; // Список позиций заказа
}
