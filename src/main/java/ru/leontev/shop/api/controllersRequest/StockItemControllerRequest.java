package ru.leontev.shop.api.controllersRequest;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.leontev.shop.dto.request.StockItemRequestDto;
import ru.leontev.shop.dto.response.StockItemResponseDto;
import ru.leontev.shop.service.StockItemServiceRequest;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/stock-items")
public class StockItemControllerRequest {

    private final StockItemServiceRequest stockItemService;

    public StockItemControllerRequest(StockItemServiceRequest stockItemService) {
        this.stockItemService = stockItemService;
    }

    @Operation(summary = "Добавляет новый товар на склад")
    @PostMapping
    public ResponseEntity<StockItemResponseDto> addStockItem(@Valid @RequestBody StockItemRequestDto dto) {
        StockItemResponseDto responseDto = stockItemService.addStockItem(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseDto);
    }
}
