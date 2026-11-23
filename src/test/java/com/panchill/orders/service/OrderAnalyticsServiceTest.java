package com.panchill.orders.service;

import com.panchill.orders.model.*;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class OrderAnalyticsServiceTest {

    private final OrderAnalyticsService service = new OrderAnalyticsService();

    @Test
    void shouldCalculateTotalForConfirmedOrders() {
        List<Order> orders = new ArrayList<>();

        Order order1 = new Order(UUID.randomUUID());
        Order order2 = new Order(UUID.randomUUID());

        OrderItem item1 = new OrderItem(new Product("Laptop", BigDecimal.valueOf(1500)), 2);
        OrderItem item2 = new OrderItem(new Product("Mouse", BigDecimal.valueOf(50)), 3);
        OrderItem item3 = new OrderItem(new Product("Laptop", BigDecimal.valueOf(1200)), 1);

        order1.addItem(item1);
        order1.addItem(item2);

        order2.addItem(item3);

        order1.confirm();
        order2.confirm();

        orders.add(order1);
        orders.add(order2);

        assertEquals(Map.of("Laptop", new BigDecimal("4200"), "Mouse", new BigDecimal("150")), service.calculateConfirmedTotalByProduct(orders));

    }

    @Test
    void shouldIgnoreUnconfirmedOrders() {
        List<Order> orders = new ArrayList<>();

        Order order1 = new Order(UUID.randomUUID());
        Order order2 = new Order(UUID.randomUUID());

        OrderItem item1 = new OrderItem(new Product("Laptop", BigDecimal.valueOf(1500)), 2);
        OrderItem item2 = new OrderItem(new Product("Mouse", BigDecimal.valueOf(50)), 3);

        order1.addItem(item1);
        order2.addItem(item2);

        order1.confirm();

        orders.add(order1);
        orders.add(order2);

        assertEquals(Map.of("Laptop", new BigDecimal("3000")), service.calculateConfirmedTotalByProduct(orders));
    }

    @Test
    void shouldReturnEmptyMapWhenOrdersListIsEmpty() {
        List<Order> orders = new ArrayList<>();
        assertEquals(Map.of(), service.calculateConfirmedTotalByProduct(orders));

    }
}