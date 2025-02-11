package ru.leontev.shop.dto.response;

import lombok.Data;

@Data
public class StockItemResponseDto {

    private Long id;
    private Long productId; // Идентификатор товара
    private Long warehouseId; // Идентификатор склада
    private Integer quantity;
}

