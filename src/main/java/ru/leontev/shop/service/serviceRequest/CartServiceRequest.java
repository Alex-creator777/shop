package ru.leontev.shop.service.serviceRequest;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.leontev.shop.dto.request.CartRequestDto;
import ru.leontev.shop.dto.response.CartResponseDto;
import ru.leontev.shop.mapper.response.CartResponseMapper;
import ru.leontev.shop.model.CartEntity;
import ru.leontev.shop.repository.CartRepository;
import ru.leontev.shop.service.calculations.CartCalculator;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CartServiceRequest {

    private final CartRepository cartRepository;
    private final CartResponseMapper cartResponseMapper;

    public CartServiceRequest(CartRepository cartRepository, CartResponseMapper cartResponseMapper) {
        this.cartRepository = cartRepository;
        this.cartResponseMapper = cartResponseMapper;
    }

    @Transactional
    public CartResponseDto createCart(CartRequestDto dto) {
        CartEntity cart = new CartEntity();
        cart.setUserId(dto.getUserId());
        cart.setCreatedAt(LocalDateTime.now());
        cart.setUpdatedAt(LocalDateTime.now());
        CartEntity savedCart = cartRepository.save(cart);
        // Новая корзина, скорее всего, не содержит позиций, итого сумма = 0
        CartResponseDto responseDto = cartResponseMapper.cartEntityToCartResponseDto(savedCart);
        responseDto.setTotalAmount(BigDecimal.ZERO);
        return responseDto;
    }

    @Transactional(readOnly = true)
    public CartResponseDto getCartById(Long id) {
        CartEntity cart = cartRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cart not found"));
        CartResponseDto responseDto = cartResponseMapper.cartEntityToCartResponseDto(cart);
        // Вычисляем общую сумму, складывая (quantity * price) для всех позиций корзины
        BigDecimal totalAmount = CartCalculator.calculateTotalAmount(cart.getCartItemEntities());
        responseDto.setTotalAmount(totalAmount);
        return responseDto;
    }

    @Transactional(readOnly = true)
    public List<CartResponseDto> getAllCarts() {
        return cartRepository.findAll()
                .stream()
                .map(cart -> {
                    CartResponseDto dto = cartResponseMapper.cartEntityToCartResponseDto(cart);
                    BigDecimal totalAmount = CartCalculator.calculateTotalAmount(cart.getCartItemEntities());
                    dto.setTotalAmount(totalAmount);
                    return dto;
                })
                .collect(Collectors.toList());
    }
}
