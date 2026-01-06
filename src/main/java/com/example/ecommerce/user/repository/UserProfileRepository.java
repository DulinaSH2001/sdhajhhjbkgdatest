package com.example.ecommerce.user.repository;

import com.example.ecommerce.auth.entity.User;
import com.example.ecommerce.user.entity.UserProfile;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserProfileRepository extends JpaRepository<UserProfile, Long> {
    Optional<UserProfile> findByUser(User user);
}