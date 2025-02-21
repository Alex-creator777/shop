package ru.leontev.shop.service.serviceResponse;

import org.springframework.stereotype.Service;
import ru.leontev.shop.dto.response.DiscountResponseDto;
import ru.leontev.shop.mapper.response.DiscountResponseMapper;
import ru.leontev.shop.model.DiscountEntity;
import ru.leontev.shop.repository.DiscountRepository;
import java.util.List;
import java.util.stream.Collectors;

/**
 * DiscountServiceResponse управляет извлечением данных о скидках.
 * Взаимодействует с DiscountRepository и DiscountResponseMapper.
 */
@Service
public class DiscountServiceResponse {
    private final DiscountRepository discountRepository;
    private final DiscountResponseMapper discountResponseMapper;

    public DiscountServiceResponse(DiscountRepository discountRepository, DiscountResponseMapper discountResponseMapper) {
        this.discountRepository = discountRepository;
        this.discountResponseMapper = discountResponseMapper;
    }

    public DiscountResponseDto getDiscountById(Long id) {
        DiscountEntity entity = discountRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Discount not found"));
        return discountResponseMapper.toDto(entity);
    }

    public List<DiscountResponseDto> getAllDiscounts() {
        List<DiscountEntity> list = discountRepository.findAll();
        return list.stream().map(discountResponseMapper::toDto).collect(Collectors.toList());
    }
}
