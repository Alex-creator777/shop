package ru.leontev.shop.controllers;

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

    @GetMapping
    public List<StockItemResponseDto> getAllStockItemService() {
        return stockItemService.getAllStockItems();
    }

    @GetMapping("/{id}")
    public StockItemResponseDto getStockItemById(@PathVariable Long id) {
        return stockItemService.getStockItemById(id);
    }
}
