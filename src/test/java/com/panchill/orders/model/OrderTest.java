package com.panchill.orders.model;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

public class OrderTest {

    @Test
    void shouldCreateOrderWithNewStatus() {
        Order order = new Order(UUID.randomUUID());

        assertEquals(OrderStatus.NEW, order.getStatus());
    }

    @Test
    void shouldCalculateTotalPrice() {
        Order order = new Order(UUID.randomUUID());
        OrderItem item1 = new OrderItem(new Product("Cheeps", new BigDecimal("124.12")), 2);
        OrderItem item2 = new OrderItem(new Product("Fruits", new BigDecimal("12.12")), 20);
        order.addItem(item1);
        order.addItem(item2);
        assertEquals(new BigDecimal("490.64"), order.getTotalPrice());
    }

    @Test
    void shouldReturnZeroForEmptyOrder() {
        Order order = new Order(UUID.randomUUID());
        assertEquals(BigDecimal.ZERO, order.getTotalPrice());
    }

    @Test
    void shouldThrowExceptionWhenIdIsNull(){
        assertThrows(IllegalArgumentException.class, () -> new Order(null));
    }

    @Test
    void shouldThrowExceptionWhenConfirmingEmptyOrder(){
        Order order = new Order(UUID.randomUUID());
        assertThrows(IllegalStateException.class, order::confirm);
    }

    @Test
    void shouldConfirmNonEmptyOrder(){
        Order order = new Order(UUID.randomUUID());
        OrderItem item1 = new OrderItem(new Product("Cheeps", new BigDecimal("124.12")), 2);
        order.addItem(item1);
        order.confirm();
        assertEquals(OrderStatus.CONFIRMED, order.getStatus());
    }

    @Test
    void shouldThrowExceptionWhenConfirmingAlreadyConfirmedOrder(){
        Order order = new Order(UUID.randomUUID());
        OrderItem item1 = new OrderItem(new Product("Cheeps", new BigDecimal("124.12")), 2);
        order.addItem(item1);
        order.confirm();
        assertThrows(IllegalStateException.class, order::confirm);
    }

    @Test
    void shouldThrowExceptionWhenAddingItemToConfirmedOrder(){
        Order order = new Order(UUID.randomUUID());
        OrderItem item1 = new OrderItem(new Product("Cheeps", new BigDecimal("124.12")), 2);
        order.addItem(item1);
        order.confirm();
        assertThrows(IllegalStateException.class, () -> order.addItem(item1));
    }

    @Test
    void shouldProtectInternalItemsList(){
        Order order = new Order(UUID.randomUUID());
        OrderItem item1 = new OrderItem(new Product("Cheeps", new BigDecimal("124.12")), 2);
        order.addItem(item1);
        List<OrderItem> items = order.getItems();
        items.clear();
        assertEquals(1, order.getItems().size());
    }

    @Test
    void shouldThrowExceptionWhenAddingNullItem(){
        Order order = new Order(UUID.randomUUID());
        assertThrows(IllegalArgumentException.class, () -> order.addItem(null));
    }

    @Test
    void shouldReturnCorrectOrderId(){
        UUID uuid = UUID.randomUUID();
        Order order = new Order(uuid);
        assertEquals(uuid, order.getId());
    }
}
