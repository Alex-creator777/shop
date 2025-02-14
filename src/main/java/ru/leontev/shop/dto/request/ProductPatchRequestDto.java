package ru.leontev.shop.dto.request;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.math.BigDecimal;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProductPatchRequestDto {
    // Все поля опциональны, если null – поле не будет обновлено
    private String name;
    private String description;
    private BigDecimal price;
    private Double voltage;
    private Double power;
    private String connectorType;
    private Long categoryId;
}
