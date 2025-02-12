package ru.leontev.shop.controllers;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.web.bind.annotation.*;
import ru.leontev.shop.dto.response.WarehouseResponseDto;
import ru.leontev.shop.service.WarehouseService;

import java.util.List;

@RestController
@RequestMapping("/warehouse")
public class WarehouseController {

    private final WarehouseService warehouseService;

    public WarehouseController(WarehouseService warehouseService) {
        this.warehouseService = warehouseService;
    }

    @Operation(summary = "Получает список всех складов")
    @GetMapping
    public List<WarehouseResponseDto> getWarehouseService() {
        return warehouseService.getAllWarehouses();
    }

    @Operation(summary = "Получает информацию о складе по id")
    @GetMapping("/{id}")
    public WarehouseResponseDto getWarehouseById(@PathVariable Long id) {
        return warehouseService.getWarehouseById(id);
    }
}
