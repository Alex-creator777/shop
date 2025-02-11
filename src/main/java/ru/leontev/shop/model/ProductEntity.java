package ru.leontev.shop.model;


import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;

@Entity
@Table(name = "products")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductEntity {

    @Id//@Id – помечает поле как первичный ключ (Primary Key).
    @GeneratedValue(strategy = GenerationType.IDENTITY) //указывает, что база данных будет сама генерировать значения id (например, через AUTO_INCREMENT в MySQL или SERIAL в PostgreSQL).; В Liquibase схему таблицы нужно будет прописать вручную, но поле id также должно быть AUTO_INCREMENT  (то есть БД сама будет увеличивать его при добавлении новой категории).
    private Long id;

    //Поле name – название продукта:
    //@Column(name = "name", nullable = false) – указывает, что в таблице будет столбец name, который не может быть NULL (nullable = false)
    @Column(name = "name", nullable = false)
    private String name;


    //Поле description – описание товара:
    //
    //@Column(name = "description") – просто указывает название столбца (по умолчанию совпало бы с именем поля).
    @Column(name = "description")
    private String description;

    //Поле price – цена товара:
    //
    //@Column(name = "price", nullable = false) – столбец обязательно должен быть заполнен (nullable = false).
    //Тип BigDecimal выбран, потому что double и float могут давать погрешности при расчетах с деньгами.
    @Column(name = "price", nullable = false)
    private BigDecimal price;

    // характеристики напряжения
    @Column(name = "voltage")
    private Double voltage;

    //характеристики мощности
    @Column(name = "power")
    private Double power;
    //тип разъема
    @Column(name = "connector_type")
    private String connectorType;

    //Связь с CategoryEntity (категория товара):
    //@ManyToOne – устанавливает связь много товаров → к одной категории.
    //@JoinColumn(name = "category_id") – в таблице products будет столбец category_id, который является внешним ключом (FK), указывающим на таблицу categories
    @ManyToOne
    @JoinColumn(name = "category_id")
    private CategoryEntity category;
}



  /*
javax.persistence.* – стандартные JPA-аннотации, используемые для ORM (Object-Relational Mapping).
java.math.BigDecimal – используется для хранения цен, чтобы избежать проблем с точностью вычислений (например, при использовании double).
Аннотации, определяющие сущность:

@Entity – говорит Hibernate, что этот класс является сущностью и должен быть отображён в таблицу базы данных.
@Table(name = "products") – указывает, что эта сущность будет храниться в таблице products.
@Data – Lombok-аннотация, которая автоматически генерирует геттеры, сеттеры, equals(), hashCode() и toString().
@NoArgsConstructor – создаёт конструктор без аргументов.
@AllArgsConstructor – создаёт конструктор со всеми аргументами.*/