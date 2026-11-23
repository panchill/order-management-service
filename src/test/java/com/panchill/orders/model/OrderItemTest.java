package com.panchill.orders.model;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class OrderItemTest {
    Product product = new Product("chips", new BigDecimal("150.50"));
    @Test
    void shouldCalculateCorrectPrice() {

        OrderItem orderItem = new OrderItem(product, 3);

        assertEquals(new BigDecimal("451.50"), orderItem.getTotalPrice());
    }

    @Test
    void shouldThrowExceptionWhenQuantityIsInvalid(){
        assertThrows(IllegalArgumentException.class,() -> new OrderItem(product, 0));
    }

    @Test
    void shouldThrowExceptionWhenProductIsNull(){
        assertThrows(IllegalArgumentException.class,() -> new OrderItem(null, 3));
    }
}
