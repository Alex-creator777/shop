package ru.leontev.shop.service;

import org.springframework.stereotype.Service;
import ru.leontev.shop.dto.response.ProductResponseDto;
import ru.leontev.shop.mapper.response.ProductResponseMapper;
import ru.leontev.shop.model.ProductEntity;
import ru.leontev.shop.repository.ProductRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductService {

    private final ProductRepository productRepository;//Не содержит данных. Содержит методы для взаимодействия с базой данных, такие как findAll(), findById(), save() и т.д..
    private final ProductResponseMapper productResponseMapper;//не содержит данных, определяет, как преобразовывать данные между сущностью и DTO

    // Инъекция зависимостей через конструктор
    public ProductService(ProductRepository productRepository, ProductResponseMapper productResponseMapper) {
        this.productRepository = productRepository;
        this.productResponseMapper = productResponseMapper;
    }

    // Метод для получения всех продуктов и преобразования их в DTO
    public List<ProductResponseDto> getAllProducts() {
        // Получаем все сущности продуктов из базы данных (entity) через репозиторий
        List<ProductEntity> products = productRepository.findAll();

        // Преобразуем каждую сущность в соответствующее DTO
        return products.stream()
                .map(productResponseMapper::productToProductDto)  // Маппинг сущности в DTO. Или в лябда productEntity -> productResponseMapper.productToProductDto(productEntity)
                .collect(Collectors.toList());  // Собираем все преобразованные DTO в список
    }

    // Метод для получения одного продукта по его ID
    public ProductResponseDto getProductById(Long id) {
        // Находим продукт по ID в репозитории
        ProductEntity product = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        // Преобразуем сущность в DTO и возвращаем
        return productResponseMapper.productToProductDto(product);
    }

    // Другие методы для обработки продуктов могут быть добавлены здесь...
}
