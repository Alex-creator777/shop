package ru.leontev.shop.model;

import lombok.*;
import jakarta.persistence.*;
import java.math.BigDecimal;
//Этот класс представляет собой связь между заказом (OrderEntity) и товаром (ProductEntity).
@Entity
@Table(name = "order_items")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderItemEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    //@ManyToOne— в одном заказе может быть много позиций товаров.
    //@JoinColumn(name = "order_id", nullable = false) создаёт в таблице order_items внешний ключ order_id, который указывает на таблицу orders.
    //nullable = false— Ваша запись order_items обязательно должна быть вынесена к какому-то заказу.
    @ManyToOne
    @JoinColumn(name = "order_id", nullable = false)
    private OrderEntity order;


    //@ManyToOne— один товар (ProductEntity) может встретиться в разных позициях заказов (OrderItemEntity).
    //@JoinColumn(name = "product_id", nullable = false)создаёт внешний ключ product_id, который указан в таблице products.
    //nullable = false— запись order_items должна быть объявлена к какому-то товару .
    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    private ProductEntity productEntity;


    //Поле quantity хранит количество единиц товара в данной заказанной позиции.
    @Column(name = "quantity", nullable = false)
    private Integer quantity;


    //unitPrice— это цена одного товара на момент покупки.
    //BigDecimal Чтобы избежать проблем с округлением
    @Column(name = "unit_price", nullable = false)
    private BigDecimal unitPrice;
}

/*
* Этот класс нужен для хранения конкретных товаров в составе заказа.
Он связывает заказ OrderEntity и ProductEntity,
Сколько штук товара было заказано (quantity).
По какой цене этот товар был куплен (unitPrice).
Без этого класса мы бы не могли отобразить, какие именно товары входят в заказ и в каком количестве.
* */