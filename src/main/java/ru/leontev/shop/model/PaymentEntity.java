package ru.leontev.shop.model;
//Таблица Payment хранит основную информацию о платеже для заказа: его итоговый статус, способ оплаты, сумму и т.д. Это «итоговая» запись, отражающая результат платежного процесса.
import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "payments")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PaymentEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Связь с заказом
    @OneToOne
    @JoinColumn(name = "order_id", nullable = false)
    private OrderEntity order;

    // Статус платежа (хранится как числовой код)
    @Column(name = "payment_status", nullable = false)
    private int paymentStatus;

    // Метод оплаты (например, CARD, WALLET, BANK_TRANSFER и т.п.)
    @Column(name = "payment_method", nullable = false)
    private String paymentMethod;

    // Идентификатор транзакции, полученный от платежного провайдера
    @Column(name = "transaction_id")
    private String transactionId;

    // Дата оплаты
    @Column(name = "payment_date", nullable = false)
    private LocalDateTime paymentDate;

    // Сумма платежа
    @Column(name = "amount", nullable = false)
    private BigDecimal amount;

    // Сообщение об ошибке (если применимо)
    @Column(name = "error_message")
    private String errorMessage;

    // Удобные геттеры/сеттеры для работы с PaymentStatus enum
    public PaymentStatus getPaymentStatusEnum() {
        return PaymentStatus.fromCode(this.paymentStatus);
    }

    public void setPaymentStatusEnum(PaymentStatus status) {
        this.paymentStatus = status.getCode();
    }
}
