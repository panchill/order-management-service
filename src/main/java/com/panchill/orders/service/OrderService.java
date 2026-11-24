package com.panchill.orders.service;

import com.panchill.orders.exception.OrderNotFoundException;
import com.panchill.orders.model.Order;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

public class OrderService {

    private final Map<UUID, Order> orders = new HashMap<>();

    public Order getById(UUID id) {
        return Optional.ofNullable(orders.get(id)).orElseThrow(() -> new OrderNotFoundException(id));
    }

    public void addOrder(Order order) {
        if (order == null){
            throw new IllegalArgumentException("Order не может быть null");
        }

        orders.put(order.getId(), order);
    }
}
