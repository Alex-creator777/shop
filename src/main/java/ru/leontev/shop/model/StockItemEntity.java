package ru.leontev.shop.model;

import lombok.*;
import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "stock_items")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class StockItemEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //product_id в БД — это внешний ключ, указывающий на таблицу products.
    //ManyToOne означает, что один товар (ProductEntity) может находиться в разных точках (на разных складах), но каждая запись в StockItemEntity относится только к одному складу
    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    private ProductEntity productEntity;

    //warehouse_id в БД — это внешний ключ, указывающий на таблицу warehouses.
    //ManyToOne - один склад (WarehouseEntity) может хранить много разных товаров, но специальная запись StockItemEntity относится к конкретному складу.
    @ManyToOne
    @JoinColumn(name = "warehouse_id", nullable = false)
    private WarehouseEntity warehouse;

    //Количество данного товара на складе
    @Column(name = "quantity", nullable = false)
    private Integer quantity;

    //Когда информация о наличии товара обновлена была в последний раз. Это полезно для инвентаризации и автоматического обновления данных.
    @Column(name = "last_updated_date")
    private LocalDateTime lastUpdatedDate;
}

/*
* Класс StockItemEntity отвечает за учет товара на складах. Он связывает конкретный товар и склад, указывая, сколько товара хранится на этом складе.
Этот класс нужен, чтобы следить:
какой товар хранить,
на каком-то складе,
в каком количестве,
когда данные обновлялись последний раз.*/