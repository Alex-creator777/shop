package ru.leontev.shop.service.serviceRequest;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.leontev.shop.dto.request.OrderFinalizationRequestDto;
import ru.leontev.shop.dto.response.OrderResponseDto;
import ru.leontev.shop.mapper.response.OrderResponseMapper;
import ru.leontev.shop.model.CartEntity;
import ru.leontev.shop.model.CartItemEntity;
import ru.leontev.shop.model.CustomerEntity;
import ru.leontev.shop.model.OrderEntity;
import ru.leontev.shop.model.OrderItemEntity;
import ru.leontev.shop.model.ProductEntity;
import ru.leontev.shop.repository.CartRepository;
import ru.leontev.shop.repository.OrderItemRepository;
import ru.leontev.shop.repository.OrderRepository;
import ru.leontev.shop.repository.ProductRepository;
import ru.leontev.shop.repository.CustomerRepository;
import ru.leontev.shop.service.calculations.CartCalculator;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class OrderCreationServiceRequest {

    private final CartRepository cartRepository;
    private final OrderRepository orderRepository;
    private final OrderItemRepository orderItemRepository;
    private final ProductRepository productRepository;
    private final CustomerRepository customerRepository;
    private final OrderResponseMapper orderResponseMapper;

    public OrderCreationServiceRequest(CartRepository cartRepository,
                                       OrderRepository orderRepository,
                                       OrderItemRepository orderItemRepository,
                                       ProductRepository productRepository,
                                       CustomerRepository customerRepository,
                                       OrderResponseMapper orderResponseMapper) {
        this.cartRepository = cartRepository;
        this.orderRepository = orderRepository;
        this.orderItemRepository = orderItemRepository;
        this.productRepository = productRepository;
        this.customerRepository = customerRepository;
        this.orderResponseMapper = orderResponseMapper;
    }

    /**
     * Преобразует корзину (черновик заказа) в оформленный заказ.
     * Извлекает корзину по идентификатору, вычисляет общую сумму, создает заказ и позиции заказа,
     * затем сохраняет заказ в базе данных и возвращает OrderResponseDto с заполненными связями.
     *
     * @param dto объект с данными для оформления заказа (cartId, customerId и т.п.)
     * @return OrderResponseDto с данными оформленного заказа
     */
    @Transactional
    public OrderResponseDto createOrderFromCart(OrderFinalizationRequestDto dto) {
        // Получаем корзину по ID
        CartEntity cart = cartRepository.findById(dto.getCartId())
                .orElseThrow(() -> new RuntimeException("Cart not found"));

        // Вычисляем общую сумму корзины, используя CartCalculator
        BigDecimal totalAmount = CartCalculator.calculateTotalAmount(cart.getCartItemEntities());

        // Загружаем CustomerEntity: если в корзине задан userId, используем его, иначе берем из dto
        Long customerId = (cart.getUserId() != null) ? cart.getUserId() : dto.getCustomerId();
        CustomerEntity customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new RuntimeException("Customer not found for id: " + customerId));

        // Создаем новый заказ
        OrderEntity order = new OrderEntity();
        order.setCustomer(customer);
        order.setOrderDate(LocalDateTime.now());
        order.setTotalAmount(totalAmount);
        order.setStatus(10); // статус "NEW"

        // Сохраняем заказ для получения сгенерированного идентификатора
        OrderEntity savedOrder = orderRepository.save(order);

        // Создаем список для позиций заказа
        List<OrderItemEntity> orderItems = new ArrayList<>();

        // Преобразуем каждую позицию корзины в позицию заказа
        for (CartItemEntity cartItem : cart.getCartItemEntities()) {
            // Извлекаем реальный ProductEntity из репозитория
            ProductEntity product = productRepository.findById(cartItem.getProductId())
                    .orElseThrow(() -> new RuntimeException("Product not found for id: " + cartItem.getProductId()));

            OrderItemEntity orderItem = new OrderItemEntity();
            orderItem.setOrder(savedOrder);
            orderItem.setProductEntity(product);
            orderItem.setQuantity(cartItem.getQuantity());
            orderItem.setUnitPrice(cartItem.getPrice());
            OrderItemEntity savedOrderItem = orderItemRepository.save(orderItem);
            orderItems.add(savedOrderItem);
        }

        // Устанавливаем заполненную коллекцию позиций в заказ и сохраняем его повторно
        savedOrder.setOrderItemEntities(orderItems);
        orderRepository.save(savedOrder);

        // Перезагружаем каждую позицию заказа для принудительной инициализации связей
        List<OrderItemEntity> reloadedOrderItems = new ArrayList<>();
        for (OrderItemEntity item : savedOrder.getOrderItemEntities()) {
            OrderItemEntity reloadedItem = orderItemRepository.findById(item.getId())
                    .orElseThrow(() -> new RuntimeException("Order item not found for id: " + item.getId()));
            // Принудительно инициализируем связанные объекты
            reloadedItem.getOrder().getId();
            reloadedItem.getProductEntity().getId();
            reloadedOrderItems.add(reloadedItem);
        }
        savedOrder.setOrderItemEntities(reloadedOrderItems);
        orderRepository.save(savedOrder);

        // Перезагружаем заказ целиком, чтобы гарантировать, что все связи корректно заполнены
        OrderEntity completeOrder = orderRepository.findById(savedOrder.getId())
                .orElseThrow(() -> new RuntimeException("Order not found after creation"));

        // Преобразуем заказ в DTO для ответа (возвращается один JSON)
        return orderResponseMapper.orderToOrderDto(completeOrder);
    }
}
