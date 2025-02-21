package ru.leontev.shop.service.serviceRequest;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.leontev.shop.dto.request.FavoriteRequestDto;
import ru.leontev.shop.dto.response.FavoriteResponseDto;
import ru.leontev.shop.mapper.request.FavoriteRequestMapper;
import ru.leontev.shop.mapper.response.FavoriteResponseMapper;
import ru.leontev.shop.model.CustomerEntity;
import ru.leontev.shop.model.FavoriteEntity;
import ru.leontev.shop.model.ProductEntity;
import ru.leontev.shop.repository.CustomerRepository;
import ru.leontev.shop.repository.FavoriteRepository;
import ru.leontev.shop.repository.ProductRepository;

@Service
public class FavoriteServiceRequest {

    private final FavoriteRepository favoriteRepository;
    private final CustomerRepository customerRepository;
    private final ProductRepository productRepository;
    private final FavoriteRequestMapper favoriteRequestMapper;
    private final FavoriteResponseMapper favoriteResponseMapper;

    public FavoriteServiceRequest(FavoriteRepository favoriteRepository,
                                  CustomerRepository customerRepository,
                                  ProductRepository productRepository,
                                  FavoriteRequestMapper favoriteRequestMapper,
                                  FavoriteResponseMapper favoriteResponseMapper) {
        this.favoriteRepository = favoriteRepository;
        this.customerRepository = customerRepository;
        this.productRepository = productRepository;
        this.favoriteRequestMapper = favoriteRequestMapper;
        this.favoriteResponseMapper = favoriteResponseMapper;
    }

    @Transactional
    public FavoriteResponseDto addFavorite(FavoriteRequestDto dto) {
        // Получаем сущность клиента
        CustomerEntity customer = customerRepository.findById(dto.getCustomerId())
                .orElseThrow(() -> new RuntimeException("Customer not found"));
        // Получаем сущность товара
        ProductEntity product = productRepository.findById(dto.getProductId())
                .orElseThrow(() -> new RuntimeException("Product not found"));
        // Преобразуем DTO в сущность
        FavoriteEntity favoriteEntity = favoriteRequestMapper.toEntity(dto, customer, product);
        FavoriteEntity savedFavorite = favoriteRepository.save(favoriteEntity);
        return favoriteResponseMapper.favoriteEntityToFavoriteResponseDto(savedFavorite);
    }

    @Transactional
    public void removeFavorite(Long customerId, Long productId) {
        favoriteRepository.deleteByCustomer_IdAndProduct_Id(customerId, productId);
    }
}
