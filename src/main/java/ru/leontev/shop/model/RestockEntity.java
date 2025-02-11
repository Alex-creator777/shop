package ru.leontev.shop.model;

import lombok.*;
import jakarta.persistence.*;
import java.time.LocalDateTime;
//Пополнение склада
@Entity
@Table(name = "restocks")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RestockEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //Какой товар пополнился
    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    private ProductEntity productEntity;

    //На какой склад он поступил
    @ManyToOne
    @JoinColumn(name = "warehouse_id", nullable = false)
    private WarehouseEntity warehouseEntity;

    //Сколько штук привезли
    @Column(name = "supplied_quantity", nullable = false)
    private Integer suppliedQuantity;

    //Дата пополнения
    @Column(name = "restock_date")
    private LocalDateTime restockDate;

    //Кто поставщик
    @Column(name = "supplier_info")
    private String supplierInfo;
}