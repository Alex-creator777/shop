package ru.leontev.shop.service.serviceRequest;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.leontev.shop.dto.request.ProductRequestDto;
import ru.leontev.shop.dto.response.ProductResponseDto;
import ru.leontev.shop.mapper.request.ProductRequestMapper;
import ru.leontev.shop.mapper.response.ProductResponseMapper;
import ru.leontev.shop.model.CategoryEntity;
import ru.leontev.shop.model.ProductEntity;
import ru.leontev.shop.repository.CategoryRepository;
import ru.leontev.shop.repository.ProductRepository;

@Service
public class ProductServiceRequest {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final ProductRequestMapper productRequestMapper;
    private final ProductResponseMapper productResponseMapper;

    public ProductServiceRequest(ProductRepository productRepository, CategoryRepository categoryRepository,
                                 ProductRequestMapper productRequestMapper, ProductResponseMapper productResponseMapper) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
        this.productRequestMapper = productRequestMapper;
        this.productResponseMapper = productResponseMapper;
    }

    @Transactional
    public ProductResponseDto createProduct(ProductRequestDto dto) {
        CategoryEntity category = categoryRepository.findById(dto.getCategoryId())
                .orElseThrow(() -> new RuntimeException("Категория не найдена"));

        ProductEntity product = productRequestMapper.toEntity(dto, category);
        ProductEntity savedProduct = productRepository.save(product);
        return productResponseMapper.productToProductDto(savedProduct);
    }
}
