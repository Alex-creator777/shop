package ru.leontev.shop.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

/**
 * ReviewEntity представляет сущность отзыва на товар.
 * Класс взаимодействует с ProductEntity (связь с товаром) и CustomerEntity (связь с клиентом),
 * хранит текст отзыва, оценку (rating) и дату создания (createdAt). Используется для сохранения
 * и получения отзывов из базы данных.
 */
@Entity
@Table(name = "reviews")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReviewEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // Идентификатор отзыва

    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    private ProductEntity product; // Ссылка на товар, к которому относится отзыв

    @ManyToOne
    @JoinColumn(name = "customer_id", nullable = false)
    private CustomerEntity customer; // Ссылка на клиента, оставившего отзыв

    @Column(name = "review_text", nullable = false, columnDefinition = "TEXT")
    private String reviewText; // Текст отзыва

    @Column(name = "rating", nullable = false)
    private Integer rating; // Оценка отзыва (обычно от 1 до 5)

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt = LocalDateTime.now(); // Дата и время создания отзыва
}
