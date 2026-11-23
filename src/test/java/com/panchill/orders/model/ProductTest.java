package com.panchill.orders.model;

import org.junit.jupiter.api.Test;
import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class ProductTest {

    @Test
    void shouldCreateProductWithValidData() {
        Product product = new Product("Laptop", new BigDecimal("999.99"));

        assertEquals("Laptop", product.getName());
        assertEquals(new BigDecimal("999.99"), product.getPrice());
    }

    @Test
    void shouldThrowExceptionWhenNameIsNull(){
        assertThrows(IllegalArgumentException.class, () -> new Product(null, new BigDecimal("999.99")));
    }

    @Test
    void shouldThrowExceptionWhenNameIsBlank(){
        assertThrows(IllegalArgumentException.class, () -> new Product(" ", new BigDecimal("999.99")));
    }

    @Test
    void shouldThrowExceptionWhenPriceIsNull(){
        assertThrows(IllegalArgumentException.class, () -> new Product("Phone", null));
    }

    @Test
    void shouldThrowExceptionWhenPriceIsNegative(){
        assertThrows(IllegalArgumentException.class, () -> new Product("Phone", new BigDecimal("-999.99")));
    }
}