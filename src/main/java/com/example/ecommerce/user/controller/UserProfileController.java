package com.example.ecommerce.user.controller;

import com.example.ecommerce.user.dto.UserProfileDto;
import com.example.ecommerce.user.service.UserProfileService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserProfileController {

    private final UserProfileService userProfileService;

    @PutMapping("/{userId}")
    @PreAuthorize("hasRole('USER') and #userId == principal.id")
    public ResponseEntity<UserProfileDto> updateUserProfile(
            @PathVariable Long userId,
            @Valid @RequestBody UserProfileDto userProfileDto) {
        return ResponseEntity.ok(userProfileService.updateUserProfile(userId, userProfileDto));
    }
}