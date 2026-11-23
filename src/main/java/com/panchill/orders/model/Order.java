package com.panchill.orders.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Order {
    private final UUID id;
    private final List<OrderItem> items = new ArrayList<>();
    private OrderStatus status = OrderStatus.NEW;

    public Order(UUID id) {
        if (id == null) {
            throw new IllegalArgumentException("uuid не может быть null");
        }
        this.id = id;
    }

    public UUID getId() {
        return id;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void addItem(OrderItem item) {
        if (item == null) {
            throw new IllegalArgumentException("item не может быть null");
        }
        if (status != OrderStatus.NEW) {
            throw new IllegalStateException("Статус заказа не NEW");
        }
        items.add(item);
    }

    public BigDecimal getTotalPrice() {
        BigDecimal totalSum = BigDecimal.ZERO;
        for (OrderItem item : items) {
            totalSum = totalSum.add(item.getTotalPrice());
        }
        return totalSum;
    }

    public List<OrderItem> getItems() {
        return new ArrayList<>(items);
    }

    public void confirm() {
        if (status != OrderStatus.NEW) {
            throw new IllegalStateException("Заказ уже подтвержден");
        }
        if (items.isEmpty()) {
            throw new IllegalStateException("Нельзя подтвердить пустой заказ");
        }
        status = OrderStatus.CONFIRMED;
    }
}
