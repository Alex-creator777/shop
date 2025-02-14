package ru.leontev.shop.service.serviceRequest;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.leontev.shop.model.ProductEntity;
import ru.leontev.shop.repository.ProductRepository;

//использование мягкого удаления с помощью установки флага
@Service
public class ProductDeleteService {

    private final ProductRepository productRepository;

    public ProductDeleteService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Transactional
    public void softDeleteProduct(Long id) {
        ProductEntity product = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        product.setDeleted(true);
        productRepository.save(product);
    }

    //восстановление продукта
    @Transactional
    public void restoreProduct(Long id) {
        ProductEntity product = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        if (!product.isDeleted()) {
            throw new RuntimeException("Product is already active");
        }

        product.setDeleted(false);
        productRepository.save(product);
    }


}
