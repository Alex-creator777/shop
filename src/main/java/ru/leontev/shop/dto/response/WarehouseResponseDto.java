package ru.leontev.shop.dto.response;

import lombok.Data;

@Data
public class WarehouseResponseDto {

    private Long id;
    private String name;
    private String location;
    private Integer capacity;
}
