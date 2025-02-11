package ru.leontev.shop.controllers;

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


    @GetMapping
    public List<RestockResponseDto> getAllRestock() {
        return restockService.getAllRestocks();
    }

    @GetMapping("/{id}")
    public RestockResponseDto getRestockById(@PathVariable Long id) {
        return restockService.getRestockById(id);
    }
}
