package ru.leontev.shop.service.serviceResponse;

import org.springframework.stereotype.Service;
import ru.leontev.shop.dto.response.RestockResponseDto;
import ru.leontev.shop.mapper.response.RestockResponseMapper;
import ru.leontev.shop.model.RestockEntity;
import ru.leontev.shop.repository.RestockRepository;

import java.util.List;

@Service
public class RestockService {
    private final RestockRepository restockRepository;
    private final RestockResponseMapper restockResponseMapper;

    public RestockService(RestockRepository restockRepository, RestockResponseMapper restockResponseMapper) {
        this.restockRepository = restockRepository;
        this.restockResponseMapper = restockResponseMapper;
    }

    public List<RestockResponseDto> getAllRestocks() {
        List<RestockEntity> restocks = restockRepository.findAll();
        return restockResponseMapper.restocksToRestockDtos(restocks);
    }

    public RestockResponseDto getRestockById(Long id) {
        RestockEntity restock = restockRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Restock not found"));
        return restockResponseMapper.restockToRestockDto(restock);
    }
}
