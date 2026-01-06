package com.example.ecommerce.auth.service;

import com.example.ecommerce.auth.dto.JwtResponse;
import com.example.ecommerce.auth.dto.LoginRequest;
import com.example.ecommerce.auth.dto.RegisterRequest;

public interface AuthService {
    JwtResponse register(RegisterRequest request);
    JwtResponse login(LoginRequest request);
}