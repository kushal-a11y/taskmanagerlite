package com.taskmanagerlite.taskmanagerlite.auth.dto;

import java.time.LocalDateTime;

import com.taskmanagerlite.taskmanagerlite.auth.entity.User;

public record UserResponse(
        String email,
        String role,
        LocalDateTime createdAt) {
    public static UserResponse from(User user) {
        return new UserResponse(
                user.getEmail(),
                user.getRole().toString(),
                user.getCreatedAt());
    }
}
