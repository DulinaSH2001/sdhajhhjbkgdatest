package com.example.ecommerce.user.service;

import com.example.ecommerce.user.dto.UserProfileDto;

public interface UserProfileService {
    UserProfileDto updateUserProfile(Long userId, UserProfileDto userProfileDto);
}