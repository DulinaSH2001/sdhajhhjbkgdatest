package com.example.ecommerce.order.service;

import com.example.ecommerce.order.dto.OrderRequest;
import com.example.ecommerce.order.dto.OrderResponse;

import java.util.List;

public interface OrderService {
    OrderResponse placeOrder(OrderRequest orderRequest);
    List<OrderResponse> getOrdersByUserId(Long userId);
}