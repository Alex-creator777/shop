package ru.leontev.shop.dto.response;

import lombok.Data;
import java.math.BigDecimal;

@Data
public class ProductResponseDto {

    private Long id;
    private String name;
    private String description;
    private BigDecimal price;
    private Double voltage;
    private Double power;
    private String connectorType;
    private Long categoryId; // Идентификатор категории, чтобы можно было передавать без полной информации о категории
}

