package ru.leontev.shop.controllers;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.web.bind.annotation.*;
import ru.leontev.shop.dto.response.RestockResponseDto;
import ru.leontev.shop.service.RestockService;

import java.util.List;

@RestController
@RequestMapping("/restock") // Базовый URL для всех методов контроллера
public class RestockController {

    private final RestockService restockService;

    public RestockController(RestockService restockService) {
        this.restockService = restockService;    }


    @Operation(summary = "Получает список всех пополнений товаров")
    @GetMapping
    public List<RestockResponseDto> getAllRestock() {
        return restockService.getAllRestocks();
    }

    @Operation(summary = "Получает доставленный товар по id")
    @GetMapping("/{id}")
    public RestockResponseDto getRestockById(@PathVariable Long id) {
        return restockService.getRestockById(id);
    }
}
