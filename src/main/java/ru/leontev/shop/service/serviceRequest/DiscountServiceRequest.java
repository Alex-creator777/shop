package ru.leontev.shop.service.serviceRequest;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.leontev.shop.dto.request.DiscountRequestDto;
import ru.leontev.shop.dto.response.DiscountResponseDto;
import ru.leontev.shop.mapper.request.DiscountRequestMapper;
import ru.leontev.shop.mapper.response.DiscountResponseMapper;
import ru.leontev.shop.model.DiscountEntity;
import ru.leontev.shop.repository.DiscountRepository;

/**
 * DiscountServiceRequest управляет созданием и обновлением скидок.
 * Взаимодействует с DiscountRepository, DiscountRequestMapper и DiscountResponseMapper.
 */
@Service
public class DiscountServiceRequest {
    private final DiscountRepository discountRepository;
    private final DiscountRequestMapper discountRequestMapper;
    private final DiscountResponseMapper discountResponseMapper;

    public DiscountServiceRequest(DiscountRepository discountRepository,
                                  DiscountRequestMapper discountRequestMapper,
                                  DiscountResponseMapper discountResponseMapper) {
        this.discountRepository = discountRepository;
        this.discountRequestMapper = discountRequestMapper;
        this.discountResponseMapper = discountResponseMapper;
    }

    @Transactional
    public DiscountResponseDto createDiscount(DiscountRequestDto dto) {
        DiscountEntity entity = discountRequestMapper.toEntity(dto);
        DiscountEntity saved = discountRepository.save(entity);
        return discountResponseMapper.toDto(saved);
    }

    @Transactional
    public DiscountResponseDto updateDiscount(Long id, DiscountRequestDto dto) {
        DiscountEntity entity = discountRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Discount not found"));
        // Обновляем поля скидки
        entity.setCode(dto.getCode());
        entity.setDescription(dto.getDescription());
        entity.setDiscountPercentage(dto.getDiscountPercentage());
        entity.setValidFrom(dto.getValidFrom());
        entity.setValidTo(dto.getValidTo());
        entity.setConditions(dto.getConditions());
        entity.setIsActive(dto.getIsActive());
        DiscountEntity updated = discountRepository.save(entity);
        return discountResponseMapper.toDto(updated);
    }
}
