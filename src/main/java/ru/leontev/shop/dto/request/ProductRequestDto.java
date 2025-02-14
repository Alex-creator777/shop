package ru.leontev.shop.dto.request;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class ProductRequestDto {
    private String name;
    private String description;
    private BigDecimal price;
    private Double voltage;
    private Double power;
    private String connectorType;
    private Long categoryId;
}
