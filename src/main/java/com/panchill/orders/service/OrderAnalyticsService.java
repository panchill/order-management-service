package com.panchill.orders.service;

import com.panchill.orders.model.Order;
import com.panchill.orders.model.OrderItem;
import com.panchill.orders.model.OrderStatus;
import com.panchill.orders.model.Product;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class OrderAnalyticsService {

    public Map<String, BigDecimal> calculateConfirmedTotalByProduct(List<Order> orders) {
        return orders.stream().filter(order -> order.getStatus() == OrderStatus.CONFIRMED).flatMap(order -> order.getItems().stream()).collect(Collectors.groupingBy(item -> item.getProduct().getName(), Collectors.reducing(BigDecimal.ZERO,OrderItem::getTotalPrice,BigDecimal::add)));
    }
}