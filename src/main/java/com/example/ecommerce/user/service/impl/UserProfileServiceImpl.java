package com.example.ecommerce.user.service.impl;

import com.example.ecommerce.auth.entity.User;
import com.example.ecommerce.auth.repository.UserRepository;
import com.example.ecommerce.user.dto.UserProfileDto;
import com.example.ecommerce.user.entity.UserProfile;
import com.example.ecommerce.user.repository.UserProfileRepository;
import com.example.ecommerce.user.service.UserProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserProfileServiceImpl implements UserProfileService {

    private final UserProfileRepository userProfileRepository;
    private final UserRepository userRepository;

    @Override
    @Transactional
    public UserProfileDto updateUserProfile(Long userId, UserProfileDto userProfileDto) {
        User user = userRepository.findById(userId).orElseThrow();
        UserProfile userProfile = userProfileRepository.findByUser(user)
                .orElseGet(() -> UserProfile.builder().user(user).build());
        
        userProfile.setFullName(userProfileDto.getFullName());
        userProfile.setAddress(userProfileDto.getAddress());
        userProfile.setEmail(userProfileDto.getEmail());
        
        userProfileRepository.save(userProfile);
        return userProfileDto;
    }
}