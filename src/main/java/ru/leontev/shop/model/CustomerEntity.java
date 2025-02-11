package ru.leontev.shop.model;

import lombok.*;
import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "customers")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomerEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "email", nullable = false, unique = true)
    private String email;
    //адрес доставки
    @Column(name = "address")
    private String address;

    //@OneToMany(mappedBy = "customer") — один клиент может иметь много заказов.
    //mappedBy = "customer" — указывает, что связь описана в поле customer класса OrderEntity.
    //private List<OrderEntity> orderEntities; — список всех заказов этого клиента.
    //Hibernate подтянет заказы автоматически при вызове customer.getOrderEntities()
    @OneToMany(mappedBy = "customer")
    private List<OrderEntity> orderEntities;
}
