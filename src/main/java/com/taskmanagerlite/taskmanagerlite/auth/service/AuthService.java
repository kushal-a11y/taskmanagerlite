package com.taskmanagerlite.taskmanagerlite.auth.service;

import static java.util.Locale.ROOT;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.taskmanagerlite.taskmanagerlite.auth.dto.RegisterRequest;
import com.taskmanagerlite.taskmanagerlite.auth.dto.UserResponse;
import com.taskmanagerlite.taskmanagerlite.auth.entity.Role;
import com.taskmanagerlite.taskmanagerlite.auth.entity.User;
import com.taskmanagerlite.taskmanagerlite.auth.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserResponse registerUser(RegisterRequest registerRequest) {
        String email = registerRequest.email().trim().toLowerCase(ROOT);
        if (userRepository.existsByEmail(email)) {
            throw new ResponseStatusException(
                    HttpStatus.CONFLICT,
                    "Email already registered. Try to login!");
        }
        User user = User.builder()
                .email(email)
                .passwordHash(passwordEncoder.encode(registerRequest.password()))
                .role(Role.USER)
                .build();

        user = userRepository.save(user);
        try {
            User savedUser = userRepository.saveAndFlush(user);
            return UserResponse.from(savedUser);
        } catch (DataIntegrityViolationException ex) {
            // Handles a concurrent registration race on the unique email.
            throw new ResponseStatusException(
                    HttpStatus.CONFLICT,
                    "Email is already registered");
        }
    }
}
