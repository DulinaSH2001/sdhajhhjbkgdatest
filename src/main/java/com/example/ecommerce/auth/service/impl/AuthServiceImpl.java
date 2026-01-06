package com.example.ecommerce.auth.service.impl;

import com.example.ecommerce.auth.dto.JwtResponse;
import com.example.ecommerce.auth.dto.LoginRequest;
import com.example.ecommerce.auth.dto.RegisterRequest;
import com.example.ecommerce.auth.entity.Role;
import com.example.ecommerce.auth.entity.User;
import com.example.ecommerce.auth.repository.UserRepository;
import com.example.ecommerce.auth.service.AuthService;
import com.example.ecommerce.auth.service.JwtTokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtTokenService jwtTokenService;

    @Override
    public JwtResponse register(RegisterRequest request) {
        var user = User.builder()
                .username(request.getUsername())
                .password(passwordEncoder.encode(request.getPassword()))
                .email(request.getEmail())
                .role(Role.USER)
                .build();
        userRepository.save(user);
        
        var jwt = jwtTokenService.generateToken(user);
        return new JwtResponse(jwt, user.getUsername());
    }

    @Override
    public JwtResponse login(LoginRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
        var user = userRepository.findByUsername(request.getUsername()).orElseThrow();
        var jwt = jwtTokenService.generateToken(user);
        return new JwtResponse(jwt, user.getUsername());
    }
}