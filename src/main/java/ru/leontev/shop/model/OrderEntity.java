package ru.leontev.shop.model;

import lombok.*;
import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "orders")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //Дата заказа (orderDate) – когда был создан заказ.
    @Column(name = "order_date")
    private LocalDateTime orderDate;

    //в каком состоянии находится заказ (например, «NEW» – новый, «ОБРАБОТКА» – в обработке, «ДОСТАВЛЕНО» – доставлен).
    @Column(name = "status", nullable = false)
    private String status; // Например, "NEW", "PROCESSING", "DELIVERED" и т.д.

    //сколько стоит весь заказ.
    @Column(name = "total_amount")
    private BigDecimal totalAmount;

    //Cсылка на покупателя, который оформил заказ. В БД будет внешний ключ customer_id, который связывает заказ с таблицей customers.
    @ManyToOne
    @JoinColumn(name = "customer_id")
    private CustomerEntity customer;

    //Список товаров – какие именно товары были куплены, их количество и цена (связь с OrderItemEntity). cascade = CascadeType.ALL– если удалить заказ, удалятся и все, что с этим связано OrderItemEntity
    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<OrderItemEntity> orderItemEntities;
}


