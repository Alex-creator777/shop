package ru.leontev.shop.model;

import lombok.*;
import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import ru.leontev.shop.model.OrderStatus;


@Entity
@Table(name = "orders")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Дата заказа
    @Column(name = "order_date", nullable = false)
    private LocalDateTime orderDate;

    // Хранение статуса в виде числа
    @Column(name = "status", nullable = false)
    private Integer status;

    // Общая сумма заказа
    @Column(name = "total_amount", nullable = false)
    private BigDecimal totalAmount;

    // Покупатель, оформивший заказ
    @ManyToOne
    @JoinColumn(name = "customer_id", nullable = false)
    private CustomerEntity customer;

    // Список товаров в заказе
    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OrderItemEntity> orderItemEntities;

    // Геттер и сеттер для преобразования числового статуса в Enum
    @Transient // Добавляем аннотацию, чтобы не сохранять этот метод в базе данных
    public OrderStatus getOrderStatus() {
        return OrderStatus.fromCode(this.status); // Преобразуем числовой статус в Enum
    }

    // Устанавливаем статус, передавая в качестве параметра Enum
    public void setOrderStatus(OrderStatus orderStatus) {
        this.status = orderStatus.getCode(); // Преобразуем статус Enum в число для сохранения в БД
    }
}
