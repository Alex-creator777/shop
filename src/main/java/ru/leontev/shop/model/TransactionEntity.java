package ru.leontev.shop.model;
//логирование транзакций/ каждая запись в таблице Transaction — это шаг в процессе обработки одного платежа, а в таблице Payment хранится сводная информация о платеже в целом.
import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "transactions")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TransactionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Связь с платежом
    @ManyToOne
    @JoinColumn(name = "payment_id", nullable = false)
    private PaymentEntity payment;

    // Тип события (например, INITIATED, SUCCESS, FAILED, REFUND и т.д.)
    @Column(name = "event_type", nullable = false)
    private String eventType;

    // Дата и время события
    @Column(name = "event_date", nullable = false)
    private LocalDateTime eventDate;

    // Сумма, связанная с транзакцией
    @Column(name = "amount", nullable = false)
    private BigDecimal amount;

    // Дополнительное сообщение (например, ошибка)
    @Column(name = "message")
    private String message;
}
