package com.taskmanagerlite.taskmanagerlite.auth.dto;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import com.taskmanagerlite.taskmanagerlite.auth.entity.User;

public record UserResponse(
        String email,
        String role,
        String createdAt) {
    public static UserResponse from(User user) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm a");

        String formattedDate = user.getCreatedAt().format(formatter);
        return new UserResponse(
                user.getEmail(),
                user.getRole().toString(),
                formattedDate);
    }
}
