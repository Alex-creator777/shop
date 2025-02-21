package ru.leontev.shop.model;
/**

 Этот enum представляет возможные статусы платежей в системе. Он используется для конвертации между числовыми значениями, сохранёнными в базе данных (поле payment_status в PaymentEntity), и понятными статусами, такими как PAID, DECLINED или PENDING. При сохранении платежа код статуса устанавливается с помощью метода getCode(), а при извлечении из базы данных используется метод fromCode() для преобразования числового значения в соответствующий статус. Таким образом, PaymentStatus обеспечивает единообразное представление состояния платежа во всех слоях приложения и взаимодействует с сущностью PaymentEntity, гарантируя корректную интерпретацию и обработку платежных данных. */

public enum PaymentStatus {
    PAID(1),
    DECLINED(2),
    PENDING(0);

    private final int code;

    PaymentStatus(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public static PaymentStatus fromCode(int code) {
        for (PaymentStatus status : PaymentStatus.values()) {
            if (status.getCode() == code) {
                return status;
            }
        }
        throw new IllegalArgumentException("Unknown PaymentStatus code: " + code);
    }
}
