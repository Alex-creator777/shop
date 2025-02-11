package ru.leontev.shop.service;

import org.springframework.stereotype.Service;
import ru.leontev.shop.dto.response.StockItemResponseDto;
import ru.leontev.shop.mapper.response.StockItemResponseMapper;
import ru.leontev.shop.model.StockItemEntity;
import ru.leontev.shop.repository.StockItemRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class StockItemService {
    private final StockItemRepository stockItemRepository;
    private final StockItemResponseMapper stockItemResponseMapper;

    public StockItemService(StockItemRepository stockItemRepository, StockItemResponseMapper stockItemResponseMapper) {
        this.stockItemRepository = stockItemRepository;
        this.stockItemResponseMapper = stockItemResponseMapper;
    }

    public List<StockItemResponseDto> getAllStockItems() {
        List<StockItemEntity> stockItems = stockItemRepository.findAll();
        return stockItems.stream()
                .map(stockItemResponseMapper::stockItemToStockItemDto)
                .collect(Collectors.toList());
    }

    public StockItemResponseDto getStockItemById(Long id) {
        StockItemEntity stockItem = stockItemRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Stock item not found"));
        return stockItemResponseMapper.stockItemToStockItemDto(stockItem);
    }
}
