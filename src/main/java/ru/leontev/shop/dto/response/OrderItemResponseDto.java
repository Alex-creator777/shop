package ru.leontev.shop.dto.response;

import lombok.Data;
import java.math.BigDecimal;

@Data
public class OrderItemResponseDto {

    private Long id;
    private Long productId; // Идентификатор товара
    private Integer quantity;
    private BigDecimal unitPrice;
}
