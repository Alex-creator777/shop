package ru.leontev.shop.controllers;

import org.springframework.web.bind.annotation.*;
import ru.leontev.shop.dto.response.StockItemResponseDto;
import ru.leontev.shop.dto.response.WarehouseResponseDto;
import ru.leontev.shop.service.StockItemService;
import ru.leontev.shop.service.WarehouseService;

import java.util.List;

@RestController
@RequestMapping("/warehouse") // Базовый URL для всех методов контроллера
public class WarehouseController {

    private final WarehouseService warehouseService;

    public WarehouseController(WarehouseService warehouseService) {
        this.warehouseService = warehouseService;
    }


    @GetMapping
    public List<WarehouseResponseDto> getWarehouseService() {
        return warehouseService.getAllWarehouses();
    }

    @GetMapping("/{id}")
    public WarehouseResponseDto getWarehouseById(@PathVariable Long id) {
        return warehouseService.getWarehouseById(id);
    }
}
