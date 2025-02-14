package ru.leontev.shop.service.serviceRequest;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.leontev.shop.dto.request.ProductUpdateRequestDto;
import ru.leontev.shop.dto.response.ProductResponseDto;
import ru.leontev.shop.mapper.request.ProductUpdateMapper;
import ru.leontev.shop.mapper.response.ProductResponseMapper;
import ru.leontev.shop.model.CategoryEntity;
import ru.leontev.shop.model.ProductEntity;
import ru.leontev.shop.repository.CategoryRepository;
import ru.leontev.shop.repository.ProductRepository;

@Service
public class ProductUpdateService {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final ProductUpdateMapper productUpdateMapper;
    private final ProductResponseMapper productResponseMapper;

    public ProductUpdateService(ProductRepository productRepository,
                                CategoryRepository categoryRepository,
                                ProductUpdateMapper productUpdateMapper,
                                ProductResponseMapper productResponseMapper) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
        this.productUpdateMapper = productUpdateMapper;
        this.productResponseMapper = productResponseMapper;
    }

    @Transactional
    public ProductResponseDto updateProduct(Long id, ProductUpdateRequestDto dto) {
        // Найти существующий продукт по id
        ProductEntity product = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        // Применить обновления из DTO к найденному продукту
        productUpdateMapper.updateProductFromDto(dto, product);

        // Обновить категорию, если она изменилась
        CategoryEntity category = categoryRepository.findById(dto.getCategoryId())
                .orElseThrow(() -> new RuntimeException("Category not found"));
        product.setCategory(category);

        // Сохранить обновленный продукт в базе данных
        ProductEntity updatedProduct = productRepository.save(product);

        // Преобразовать обновленный продукт в DTO и вернуть
        return productResponseMapper.productToProductDto(updatedProduct);
    }
}
