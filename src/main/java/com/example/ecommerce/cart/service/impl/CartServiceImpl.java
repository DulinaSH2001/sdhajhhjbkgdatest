package com.example.ecommerce.cart.service.impl;

import com.example.ecommerce.cart.dto.CartItemDto;
import com.example.ecommerce.cart.dto.CartResponse;
import com.example.ecommerce.cart.entity.Cart;
import com.example.ecommerce.cart.repository.CartRepository;
import com.example.ecommerce.cart.service.CartService;
import com.example.ecommerce.product.entity.Product;
import com.example.ecommerce.product.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CartServiceImpl implements CartService {

    private final CartRepository cartRepository;
    private final ProductRepository productRepository;

    @Override
    public CartResponse getCartByUserId(Long userId) {
        Cart cart = cartRepository.findByUserId(userId).orElseThrow();
        
        List<CartItemDto> items = cart.getItems().stream()
                .map(item -> {
                    String productName = productRepository.findById(item.getProductId())
                            .map(Product::getName)
                            .orElse("");
                    return new CartItemDto(
                            item.getProductId(),
                            productName,
                            item.getQuantity(),
                            item.getPrice()
                    );
                })
                .collect(Collectors.toList());
        
        return CartResponse.builder()
                .id(cart.getId())
                .userId(userId)
                .items(items)
                .totalPrice(cart.getTotalPrice())
                .build();
    }
}