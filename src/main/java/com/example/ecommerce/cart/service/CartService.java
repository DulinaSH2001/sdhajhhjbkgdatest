package com.example.ecommerce.cart.service;

import com.example.ecommerce.cart.dto.CartResponse;

public interface CartService {
    CartResponse getCartByUserId(Long userId);
}