package com.taskmanagerlite.taskmanagerlite.auth.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.taskmanagerlite.taskmanagerlite.auth.dto.RegisterRequest;
import com.taskmanagerlite.taskmanagerlite.auth.dto.UserResponse;
import com.taskmanagerlite.taskmanagerlite.auth.service.AuthService;

import io.swagger.v3.oas.annotations.parameters.RequestBody;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController 
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor 
public class AuthController {
    private final AuthService authService;
    
    @PostMapping("/register")
    public ResponseEntity<UserResponse> registerUser(
        @Valid @RequestBody RegisterRequest registerRequest){
            UserResponse userResponse = authService.registerUser(registerRequest);

            return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .body(userResponse);
    }
}
