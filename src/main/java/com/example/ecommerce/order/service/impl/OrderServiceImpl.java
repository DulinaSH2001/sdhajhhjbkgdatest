package com.example.ecommerce.order.service.impl;

import com.example.ecommerce.cart.entity.Cart;
import com.example.ecommerce.cart.repository.CartRepository;
import com.example.ecommerce.order.dto.OrderItemResponse;
import com.example.ecommerce.order.dto.OrderRequest;
import com.example.ecommerce.order.dto.OrderResponse;
import com.example.ecommerce.order.entity.Order;
import com.example.ecommerce.order.entity.OrderItem;
import com.example.ecommerce.order.repository.OrderRepository;
import com.example.ecommerce.order.service.OrderService;
import com.example.ecommerce.product.entity.Product;
import com.example.ecommerce.product.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final CartRepository cartRepository;
    private final ProductRepository productRepository;

    @Override
    @Transactional
    public OrderResponse placeOrder(OrderRequest orderRequest) {
        Cart cart = cartRepository.findByUserId(orderRequest.getUserId()).orElseThrow();
        Order order = new Order();
        order.setUserId(orderRequest.getUserId());
        order.setOrderDate(LocalDateTime.now());
        order.setStatus("PENDING");
        
        List<OrderItem> orderItems = cart.getItems().stream()
                .map(cartItem -> {
                    OrderItem orderItem = new OrderItem();
                    orderItem.setProductId(cartItem.getProductId());
                    orderItem.setQuantity(cartItem.getQuantity());
                    orderItem.setPrice(cartItem.getPrice());
                    orderItem.setOrder(order);
                    return orderItem;
                })
                .collect(Collectors.toList());
        
        order.setItems(orderItems);
        order.setTotalAmount(cart.getTotalPrice()); // Fixed to use setTotalAmount
        
        Order savedOrder = orderRepository.save(order);
        cart.getItems().clear();
        cartRepository.save(cart);
        
        return mapToOrderResponse(savedOrder);
    }

    @Override
    public List<OrderResponse> getOrdersByUserId(Long userId) {
        return orderRepository.findByUserId(userId).stream()
                .map(this::mapToOrderResponse)
                .collect(Collectors.toList());
    }

    private OrderResponse mapToOrderResponse(Order order) {
        List<OrderItemResponse> items = order.getItems().stream()
                .map(item -> {
                    String productName = productRepository.findById(item.getProductId())
                            .map(Product::getName)
                            .orElse("");
                    return new OrderItemResponse(
                            item.getProductId(),
                            productName,
                            item.getQuantity(),
                            item.getPrice()
                    );
                })
                .collect(Collectors.toList());
        
        return OrderResponse.builder()
                .id(order.getId())
                .userId(order.getUserId())
                .items(items)
                .totalAmount(order.getTotalAmount())
                .orderDate(order.getOrderDate())
                .status(order.getStatus())
                .build();
    }
}