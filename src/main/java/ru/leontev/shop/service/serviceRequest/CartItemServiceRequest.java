package ru.leontev.shop.service.serviceRequest;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.leontev.shop.dto.request.CartItemRequestDto;
import ru.leontev.shop.dto.response.CartItemResponseDto;
import ru.leontev.shop.mapper.response.CartItemResponseMapper;
import ru.leontev.shop.model.CartEntity;
import ru.leontev.shop.model.CartItemEntity;
import ru.leontev.shop.repository.CartItemRepository;
import ru.leontev.shop.repository.CartRepository;

import java.time.LocalDateTime;

@Service
public class CartItemServiceRequest {

    private final CartItemRepository cartItemRepository;
    private final CartRepository cartRepository;
    private final CartItemResponseMapper cartItemResponseMapper;

    public CartItemServiceRequest(CartItemRepository cartItemRepository,
                                  CartRepository cartRepository,
                                  CartItemResponseMapper cartItemResponseMapper) {
        this.cartItemRepository = cartItemRepository;
        this.cartRepository = cartRepository;
        this.cartItemResponseMapper = cartItemResponseMapper;
    }

    @Transactional
    public CartItemResponseDto addCartItem(CartItemRequestDto dto) {
        // Находим корзину по ID
        CartEntity cart = cartRepository.findById(dto.getCartId())
                .orElseThrow(() -> new RuntimeException("Cart not found"));

        // Создаем новую позицию корзины
        CartItemEntity cartItem = new CartItemEntity();
        cartItem.setCartEntity(cart);
        cartItem.setProductId(dto.getProductId());
        cartItem.setQuantity(dto.getQuantity());
        cartItem.setPrice(dto.getPrice());
        cartItem.setAddedAt(LocalDateTime.now());

        // Сохраняем позицию
        CartItemEntity savedCartItem = cartItemRepository.save(cartItem);
        return cartItemResponseMapper.cartItemEntityToCartItemResponseDto(savedCartItem);
    }
}
