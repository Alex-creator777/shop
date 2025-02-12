package ru.leontev.shop.controllers;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.web.bind.annotation.*;
import ru.leontev.shop.dto.response.StockItemResponseDto;
import ru.leontev.shop.service.StockItemService;

import java.util.List;

@RestController
@RequestMapping("/stockItem") // Базовый URL для всех методов контроллера
public class StockItemController {

    private final StockItemService stockItemService;

    public StockItemController(StockItemService stockItemService) {
        this.stockItemService = stockItemService;
    }

    @Operation(summary = "Получает список всех позиций товаров на складе")
    @GetMapping
    public List<StockItemResponseDto> getAllStockItemService() {
        return stockItemService.getAllStockItems();
    }

    @Operation(summary = "Получает товар на складе по id")
    @GetMapping("/{id}")
    public StockItemResponseDto getStockItemById(@PathVariable Long id) {
        return stockItemService.getStockItemById(id);
    }
}
