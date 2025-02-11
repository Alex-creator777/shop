package ru.leontev.shop.model;

import lombok.*;
import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "categories")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CategoryEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "description")
    private String description;

/*Связь "один-ко-многим" (OneToMany) с ProductEntity.
@OneToMany(mappedBy = "category") означает, что одна категория может содержать много товаров.
mappedBy = "category" – указывает, что связь настроена через поле category в классе ProductEntity.
List<ProductEntity> productEntities; – список всех товаров, относящихся к этой категории.
В categories будет одна запись для каждой категории.
В productEntities будет много записей, и каждая будет ссылаться на category_id.
Hibernate подтягивает список productEntities в CategoryEntity автоматически за счёт внешнего ключа в базе данных и аннотации @OneToMany(mappedBy = "category") в коде. Эти товары Hibernate складывает в List<ProductEntity> productEntities*/
    @OneToMany(mappedBy = "category")
    private List<ProductEntity> productEntities;
}

