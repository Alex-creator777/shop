package ru.leontev.shop.service.serviceRequest;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.leontev.shop.dto.request.StockItemRequestDto;
import ru.leontev.shop.dto.response.StockItemResponseDto;
import ru.leontev.shop.mapper.request.StockItemRequestMapper;
import ru.leontev.shop.mapper.response.StockItemResponseMapper;
import ru.leontev.shop.model.StockItemEntity;
import ru.leontev.shop.model.ProductEntity;
import ru.leontev.shop.model.WarehouseEntity;
import ru.leontev.shop.repository.StockItemRepository;
import ru.leontev.shop.repository.ProductRepository;
import ru.leontev.shop.repository.WarehouseRepository;

@Service
public class StockItemServiceRequest {

    private final StockItemRepository stockItemRepository;
    private final ProductRepository productRepository;
    private final WarehouseRepository warehouseRepository;
    private final StockItemRequestMapper stockItemRequestMapper;
    private final StockItemResponseMapper stockItemResponseMapper;

    public StockItemServiceRequest(StockItemRepository stockItemRepository,
                            ProductRepository productRepository,
                            WarehouseRepository warehouseRepository,
                            StockItemRequestMapper stockItemRequestMapper,
                            StockItemResponseMapper stockItemResponseMapper) {
        this.stockItemRepository = stockItemRepository;
        this.productRepository = productRepository;
        this.warehouseRepository = warehouseRepository;
        this.stockItemRequestMapper = stockItemRequestMapper;
        this.stockItemResponseMapper = stockItemResponseMapper;
    }

    @Transactional
    public StockItemResponseDto addStockItem(StockItemRequestDto dto) {
        // Проверяем, что продукт существует
        ProductEntity product = productRepository.findById(dto.getProductId())
                .orElseThrow(() -> new RuntimeException("Product not found"));

        // Проверяем, что склад существует
        WarehouseEntity warehouse = warehouseRepository.findById(dto.getWarehouseId())
                .orElseThrow(() -> new RuntimeException("Warehouse not found"));

        // Преобразуем DTO в сущность
        StockItemEntity stockItemEntity = stockItemRequestMapper.toEntity(dto, product, warehouse);
        StockItemEntity savedEntity = stockItemRepository.save(stockItemEntity);

        // Преобразуем сохранённую сущность в ответный DTO
        return stockItemResponseMapper.stockItemToStockItemDto(savedEntity);
    }
}
