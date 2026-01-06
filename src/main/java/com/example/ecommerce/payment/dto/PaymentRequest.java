package com.example.ecommerce.payment.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class PaymentRequest {
    @NotNull
    private Long orderId;
    
    @NotNull
    private String paymentMethod;
    
    @NotNull
    private BigDecimal amount;
}