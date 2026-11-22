package com.panchill.orders.model;

import java.math.BigDecimal;

public class OrderItem {
    private final Product product;
    private final int quantity;

    public OrderItem(Product product, int quantity) {
        if (product == null) {
            throw new IllegalArgumentException("Продукт не может быть null");
        }

        if (quantity <= 0) {
            throw new IllegalArgumentException("Количество должно быть больше нуля");
        }
        this.product = product;
        this.quantity = quantity;
    }

    public int getQuantity() {
        return quantity;
    }

    public Product getProduct() {
        return product;
    }

    public BigDecimal getTotalPrice()
    {
        return this.product.getPrice().multiply(BigDecimal.valueOf(this.quantity));
    }
}
