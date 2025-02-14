package ru.leontev.shop.service.serviceRequest;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.leontev.shop.dto.request.ProductPatchRequestDto;
import ru.leontev.shop.dto.response.ProductResponseDto;
import ru.leontev.shop.mapper.request.ProductPatchMapper;
import ru.leontev.shop.mapper.response.ProductResponseMapper;
import ru.leontev.shop.model.CategoryEntity;
import ru.leontev.shop.model.ProductEntity;
import ru.leontev.shop.repository.CategoryRepository;
import ru.leontev.shop.repository.ProductRepository;

@Service
public class ProductPatchService {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final ProductPatchMapper productPatchMapper;
    private final ProductResponseMapper productResponseMapper;

    public ProductPatchService(ProductRepository productRepository,
                               CategoryRepository categoryRepository,
                               ProductPatchMapper productPatchMapper,
                               ProductResponseMapper productResponseMapper) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
        this.productPatchMapper = productPatchMapper;
        this.productResponseMapper = productResponseMapper;
    }

    @Transactional
    public ProductResponseDto patchProduct(Long id, ProductPatchRequestDto dto) {
        // Находим существующий продукт по id
        ProductEntity product = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        // Обновляем только те поля, которые переданы в dto (остальные остаются без изменений)
        productPatchMapper.updateProductFromPatch(dto, product);

        // Если передан новый categoryId, то обновляем категорию
        if (dto.getCategoryId() != null) {
            CategoryEntity category = categoryRepository.findById(dto.getCategoryId())
                    .orElseThrow(() -> new RuntimeException("Category not found"));
            product.setCategory(category);
        }

        // Сохраняем обновленный продукт и возвращаем DTO ответа
        ProductEntity updatedProduct = productRepository.save(product);
        return productResponseMapper.productToProductDto(updatedProduct);
    }
}
