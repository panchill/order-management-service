package com.panchill.orders.model;

import java.math.BigDecimal;

public class Product {
    private final String name;
    private final BigDecimal price;

    public Product(String name, BigDecimal price) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Некорректное название продукта");
        }
        if (price == null || price.signum() < 0) {
            throw new IllegalArgumentException("Некорректная цена");
        }
        this.name = name;
        this.price = price;

    }

    public String getName() {
        return name;
    }

    public BigDecimal getPrice() {
        return price;
    }
}
