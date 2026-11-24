package com.panchill.orders.service;

import com.panchill.orders.exception.OrderNotFoundException;
import com.panchill.orders.model.Order;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class OrderServiceTest {

    @Test
    void shouldThrowExceptionWhenOrderNotFound() {
        OrderService service = new OrderService();
        UUID unknownId = UUID.randomUUID();

        OrderNotFoundException exception = assertThrows(OrderNotFoundException.class, () -> service.getById(unknownId));
        assertEquals("Order not found: " + unknownId, exception.getMessage());
    }

    @Test
    void shouldReturnOrderWhenOrderExists() {
        OrderService service = new OrderService();
        Order order = new Order(UUID.randomUUID());
        service.addOrder(order);

        assertEquals(order, service.getById(order.getId()));
    }

    @Test
    void shouldThrowExceptionWhenOrderIsNull() {
        OrderService service = new OrderService();
        Order order = null;
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> service.addOrder(order));

        assertEquals("Order не может быть null", exception.getMessage());
    }
}