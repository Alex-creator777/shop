package ru.leontev.shop.dto.response;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class RestockResponseDto {

    private Long id;
    private Long productId; // Идентификатор товара
    private Long warehouseId; // Идентификатор склада
    private Integer suppliedQuantity;
    private LocalDateTime restockDate;
    private String supplierInfo;
}
