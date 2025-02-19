package ru.leontev.shop.service.calculations;

public class InventoryCalculator {

    /**
     * Увеличивает количество товара на складе.
     *
     * @param currentStock текущее количество товара
     * @param quantityToAdd количество, которую нужно добавить
     * @return новое количество товара на складе
     */
    public static int increaseStock(int currentStock, int quantityToAdd) {
        return currentStock + quantityToAdd;
    }

    /**
     * Уменьшает количество товара на складе, проверяя, чтобы результат не был меньше нуля.
     *
     * @param currentStock текущее количество товара
     * @param quantityToSubtract количество, которую нужно вычесть
     * @return новое количество товара на складе
     * @throws IllegalArgumentException если вычитаемое количество больше текущего запаса
     */
    public static int decreaseStock(int currentStock, int quantityToSubtract) {
        if (quantityToSubtract > currentStock) {
            throw new IllegalArgumentException("Недостаточно товара на складе для списания");
        }
        return currentStock - quantityToSubtract;
    }
}
