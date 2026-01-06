package com.example.ecommerce.order.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.List;

@Data
public class OrderRequest {
    @NotNull
    private Long userId;
    
    @NotNull
    private List<OrderItemDto> items;
}