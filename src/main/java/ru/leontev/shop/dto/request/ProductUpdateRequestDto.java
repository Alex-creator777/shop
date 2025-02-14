package ru.leontev.shop.dto.request;

import lombok.Data;
import java.math.BigDecimal;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Data
public class ProductUpdateRequestDto {

    @NotNull(message = "Product id is required")
    private Long id;

    @NotNull(message = "Name is required")
    @Size(min = 1, max = 255, message = "Name must be between 1 and 255 characters")
    private String name;

    private String description;

    @NotNull(message = "Price is required")
    private BigDecimal price;

    private Double voltage;

    private Double power;

    private String connectorType;

    @NotNull(message = "Category id is required")
    private Long categoryId;
}
