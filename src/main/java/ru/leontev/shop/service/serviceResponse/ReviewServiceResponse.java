package ru.leontev.shop.service.serviceResponse;

import org.springframework.stereotype.Service;
import ru.leontev.shop.dto.response.ReviewResponseDto;
import ru.leontev.shop.mapper.response.ReviewResponseMapper;
import ru.leontev.shop.model.ReviewEntity;
import ru.leontev.shop.repository.ReviewRepository;

import java.util.List;
import java.util.stream.Collectors;

/**
 * ReviewServiceResponse отвечает за получение данных отзывов.
 * Предоставляет методы для получения отдельного отзыва по идентификатору и списка отзывов для конкретного товара.
 * Взаимодействует с ReviewRepository и ReviewResponseMapper.
 */
@Service
public class ReviewServiceResponse {

    private final ReviewRepository reviewRepository; // Репозиторий для отзывов
    private final ReviewResponseMapper reviewResponseMapper; // Маппер для преобразования сущности в DTO

    public ReviewServiceResponse(ReviewRepository reviewRepository, ReviewResponseMapper reviewResponseMapper) {
        this.reviewRepository = reviewRepository;
        this.reviewResponseMapper = reviewResponseMapper;
    }

    public List<ReviewResponseDto> getReviewsByProduct(Long productId) {
        // Получаем список отзывов для указанного товара
        List<ReviewEntity> reviews = reviewRepository.findByProduct_Id(productId);
        // Преобразуем каждую сущность в DTO и возвращаем список
        return reviews.stream()
                .map(reviewResponseMapper::toDto)
                .collect(Collectors.toList());
    }

    public ReviewResponseDto getReviewById(Long id) {
        // Получаем отзыв по его идентификатору
        ReviewEntity review = reviewRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Review not found"));
        // Преобразуем сущность в DTO и возвращаем
        return reviewResponseMapper.toDto(review);
    }
}
