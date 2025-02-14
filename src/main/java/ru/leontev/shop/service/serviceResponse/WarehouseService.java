package ru.leontev.shop.service.serviceResponse;

import org.springframework.stereotype.Service;
import ru.leontev.shop.dto.response.WarehouseResponseDto;
import ru.leontev.shop.mapper.response.WarehouseResponseMapper;
import ru.leontev.shop.model.WarehouseEntity;
import ru.leontev.shop.repository.WarehouseRepository;

import java.util.List;

@Service
public class WarehouseService {
    private final WarehouseRepository warehouseRepository;
    private final WarehouseResponseMapper warehouseResponseMapper;

    public WarehouseService(WarehouseRepository warehouseRepository, WarehouseResponseMapper warehouseResponseMapper) {
        this.warehouseRepository = warehouseRepository;
        this.warehouseResponseMapper = warehouseResponseMapper;
    }

    public List<WarehouseResponseDto> getAllWarehouses() {
        List<WarehouseEntity> warehouses = warehouseRepository.findAll();
        return warehouseResponseMapper.toWarehouseDtoList(warehouses);
    }

    public WarehouseResponseDto getWarehouseById(Long id) {
        WarehouseEntity warehouse = warehouseRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Warehouse not found"));
        return warehouseResponseMapper.warehouseToWarehouseDto(warehouse);
    }
}
