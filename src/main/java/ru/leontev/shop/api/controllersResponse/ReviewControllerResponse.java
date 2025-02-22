package ru.leontev.shop.api.controllersResponse;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.web.bind.annotation.*;
import ru.leontev.shop.dto.response.ReviewResponseDto;
import ru.leontev.shop.service.serviceResponse.ReviewServiceResponse;
import java.util.List;

/**
 * ReviewControllerResponse предоставляет REST API для получения данных отзывов.
 * Класс взаимодействует с ReviewServiceResponse для получения отзыва по ID и списка отзывов для конкретного товара.
 * Endpoint: GET /reviews/{id} и GET /reviews/product/{productId}.
 */
@RestController
@RequestMapping("/reviews")
public class ReviewControllerResponse {

    private final ReviewServiceResponse reviewServiceResponse; // Сервис для получения отзывов

    public ReviewControllerResponse(ReviewServiceResponse reviewServiceResponse) {
        this.reviewServiceResponse = reviewServiceResponse;
    }

    @Operation(summary = "Получает отзыв по ID")
    @GetMapping("/{id}")
    public ReviewResponseDto getReviewById(@PathVariable Long id) {
        // Возвращает отзыв по его идентификатору
        return reviewServiceResponse.getReviewById(id);
    }

    @Operation(summary = "Получает все отзывы для товара по его ID")
    @GetMapping("/product/{productId}")
    public List<ReviewResponseDto> getReviewsByProduct(@PathVariable Long productId) {
        // Возвращает список отзывов для заданного товара
        return reviewServiceResponse.getReviewsByProduct(productId);
    }
}
