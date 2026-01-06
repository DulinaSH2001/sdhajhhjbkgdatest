package com.example.ecommerce.order.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
public class OrderItemDto {
    @NotNull
    private Long productId;
    
    @Positive
    private Integer quantity;
}