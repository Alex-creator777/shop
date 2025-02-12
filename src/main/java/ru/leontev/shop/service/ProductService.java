package ru.leontev.shop.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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

    // Метод для получения всех продуктов и преобразования их в DTO с пагинацией
    public Page<ProductResponseDto> getAllProducts(Integer page, Integer size) {
        int defaultPage = (page != null) ? page : 0;
        int defaultSize = (size != null) ? size : 10;

        Pageable pageable = PageRequest.of(defaultPage, defaultSize, Sort.by("name").ascending());
        Page<ProductEntity> productsPage = productRepository.findAll(pageable);

        return productsPage.map(productResponseMapper::productToProductDto);
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
