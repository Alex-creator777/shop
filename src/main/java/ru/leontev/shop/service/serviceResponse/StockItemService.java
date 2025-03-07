package ru.leontev.shop.service.serviceResponse;

import org.springframework.stereotype.Service;
import ru.leontev.shop.dto.response.StockItemResponseDto;
import ru.leontev.shop.mapper.response.StockItemResponseMapper;
import ru.leontev.shop.model.StockItemEntity;
import ru.leontev.shop.repository.StockItemRepository;

import java.util.List;

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
        return stockItemResponseMapper.stockItemsToStockItemDtos(stockItems);
    }

    public StockItemResponseDto getStockItemById(Long id) {
        StockItemEntity stockItem = stockItemRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Stock item not found"));
        return stockItemResponseMapper.stockItemToStockItemDto(stockItem);
    }
}
