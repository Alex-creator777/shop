package ru.leontev.shop.model;

import lombok.*;
import jakarta.persistence.*;
import java.util.List;
//склад
@Entity
@Table(name = "warehouses")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class WarehouseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //Название склада
    @Column(name = "name", nullable = false)
    private String name;

    //Где находится склад
    @Column(name = "location")
    private String location;

    //Максимальная вместимость
    @Column(name = "capacity")
    private Integer capacity;

    //Список товаров на складе/mappedBy = "warehouse" - связь уже описана в классе StockItemEntity. У каждого товара (StockItemEntity) есть поле warehouse_id, которое указывает, на каком складе он находится.
    //А List<StockItemEntity> stockItemEntities автоматически подтянет все товары, хранящиеся на этом складе.
    @OneToMany(mappedBy = "warehouse")
    private List<StockItemEntity> stockItemEntities;
}