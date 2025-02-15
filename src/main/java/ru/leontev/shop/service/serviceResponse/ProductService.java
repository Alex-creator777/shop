package ru.leontev.shop.service.serviceResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import ru.leontev.shop.dto.response.ProductResponseDto;
import ru.leontev.shop.mapper.response.ProductResponseMapper;
import ru.leontev.shop.model.ProductEntity;
import ru.leontev.shop.repository.ProductRepository;

@Service
public class ProductService {

    // Инициализация логгера для данного класса
    private static final Logger logger = LoggerFactory.getLogger(ProductService.class);

    private final ProductRepository productRepository;
    private final ProductResponseMapper productResponseMapper;

    public ProductService(ProductRepository productRepository, ProductResponseMapper productResponseMapper) {
        this.productRepository = productRepository;
        this.productResponseMapper = productResponseMapper;
    }

    // Метод для получения всех продуктов с пагинацией
    public Page<ProductResponseDto> getAllProducts(Integer page, Integer size) {
        // Определяем значения страницы и размера по умолчанию
        int defaultPage = (page != null) ? page : 0;
        int defaultSize = (size != null) ? size : 10;

        // Логирование входных параметров запроса (страница и размер)
        logger.info("Запрос списка продуктов: страница={}, размер={}", defaultPage, defaultSize);

        // Создание объекта Pageable с сортировкой по имени
        Pageable pageable = PageRequest.of(defaultPage, defaultSize, Sort.by("name").ascending());

        // Выполнение запроса к репозиторию для получения только активных (не удалённых) продуктов
        Page<ProductEntity> productsPage = productRepository.findByIsDeletedFalse(pageable);

        // Логирование количества найденных продуктов
        logger.info("Найдено {} продуктов", productsPage.getTotalElements());

        // Преобразование сущностей в DTO и возврат результата
        return productsPage.map(productResponseMapper::productToProductDto);
    }

    // Метод для получения одного продукта по его ID
    public ProductResponseDto getProductById(Long id) {
        // Логирование запроса продукта по ID
        logger.info("Запрос продукта по ID: {}", id);

        // Попытка найти продукт по ID, если не найден — логируем ошибку
        ProductEntity product = productRepository.findById(id)
                .orElseThrow(() -> {
                    logger.error("Продукт с ID {} не найден", id);
                    return new RuntimeException("Product not found");
                });

        // Логирование информации о найденном продукте (например, его имя)
        logger.info("Продукт найден: {}", product.getName());

        // Преобразование найденной сущности в DTO и возврат результата
        return productResponseMapper.productToProductDto(product);
    }

    // Другие методы можно логировать аналогичным образом...
}
