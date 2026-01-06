package com.example.ecommerce.user.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class UserProfileDto {
    @NotBlank
    private String fullName;
    
    @NotBlank
    private String address;
    
    @NotBlank
    @Email
    private String email;
}