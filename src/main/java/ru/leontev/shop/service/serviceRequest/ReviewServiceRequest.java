package ru.leontev.shop.service.serviceRequest;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.leontev.shop.dto.request.ReviewRequestDto;
import ru.leontev.shop.dto.response.ReviewResponseDto;
import ru.leontev.shop.mapper.request.ReviewRequestMapper;
import ru.leontev.shop.mapper.response.ReviewResponseMapper;
import ru.leontev.shop.model.CustomerEntity;
import ru.leontev.shop.model.ProductEntity;
import ru.leontev.shop.model.ReviewEntity;
import ru.leontev.shop.repository.CustomerRepository;
import ru.leontev.shop.repository.ProductRepository;
import ru.leontev.shop.repository.ReviewRepository;

/**
 * ReviewServiceRequest обрабатывает запросы на создание новых отзывов.
 * Взаимодействует с ReviewRepository, ProductRepository, CustomerRepository, а также с мапперами
 * ReviewRequestMapper и ReviewResponseMapper для преобразования входящих данных в сущность и обратно.
 */
@Service
public class ReviewServiceRequest {

    private final ReviewRepository reviewRepository; // Репозиторий для работы с отзывами
    private final ProductRepository productRepository; // Репозиторий для работы с товарами
    private final CustomerRepository customerRepository; // Репозиторий для работы с клиентами
    private final ReviewRequestMapper reviewRequestMapper; // Маппер для преобразования запроса в сущность
    private final ReviewResponseMapper reviewResponseMapper; // Маппер для преобразования сущности в ответной DTO

    public ReviewServiceRequest(ReviewRepository reviewRepository,
                                ProductRepository productRepository,
                                CustomerRepository customerRepository,
                                ReviewRequestMapper reviewRequestMapper,
                                ReviewResponseMapper reviewResponseMapper) {
        this.reviewRepository = reviewRepository;
        this.productRepository = productRepository;
        this.customerRepository = customerRepository;
        this.reviewRequestMapper = reviewRequestMapper;
        this.reviewResponseMapper = reviewResponseMapper;
    }

    @Transactional
    public ReviewResponseDto addReview(ReviewRequestDto dto) {
        // Получаем товар по его идентификатору
        ProductEntity product = productRepository.findById(dto.getProductId())
                .orElseThrow(() -> new RuntimeException("Product not found"));
        // Получаем клиента по его идентификатору
        CustomerEntity customer = customerRepository.findById(dto.getCustomerId())
                .orElseThrow(() -> new RuntimeException("Customer not found"));

        // Преобразуем данные запроса в сущность отзыва
        ReviewEntity review = reviewRequestMapper.toEntity(dto, product, customer);
        // Сохраняем отзыв в базе данных
        ReviewEntity saved = reviewRepository.save(review);
        // Преобразуем сохранённую сущность в DTO ответа
        return reviewResponseMapper.toDto(saved);
    }
}
