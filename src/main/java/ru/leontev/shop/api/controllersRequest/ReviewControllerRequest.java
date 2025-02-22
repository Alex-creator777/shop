package ru.leontev.shop.api.controllersRequest;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.leontev.shop.dto.request.ReviewRequestDto;
import ru.leontev.shop.dto.response.ReviewResponseDto;
import ru.leontev.shop.service.serviceRequest.ReviewServiceRequest;
import jakarta.validation.Valid;

/**
 * ReviewControllerRequest предоставляет REST API для создания отзывов.
 * Класс взаимодействует с ReviewServiceRequest для обработки входящих запросов на добавление нового отзыва.
 * Endpoint: POST /api/reviews.
 */
@RestController
@RequestMapping("/api/reviews")
public class ReviewControllerRequest {

    private final ReviewServiceRequest reviewServiceRequest; // Сервис для создания отзывов

    public ReviewControllerRequest(ReviewServiceRequest reviewServiceRequest) {
        this.reviewServiceRequest = reviewServiceRequest;
    }

    @Operation(summary = "Добавляет новый отзыв на товар")
    @PostMapping
    public ResponseEntity<ReviewResponseDto> addReview(@Valid @RequestBody ReviewRequestDto dto) {
        // Создаем отзыв и возвращаем его с HTTP статусом 201 (Created)
        ReviewResponseDto responseDto = reviewServiceRequest.addReview(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseDto);
    }
}
