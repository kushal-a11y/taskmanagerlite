package com.taskmanagerlite.taskmanagerlite.auth.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record RegisterRequest(

        @Email(message = "Please enter a valid email") 
        @NotBlank(message = "Email can not be left blank") 
        @Size(max = 254, message = "Email too long") 
        String email,

        @NotBlank(message = "Password can not be left blank") 
        @Size(min = 8, message = "Password too small! Write longer password.")
        @Size(max = 72, message = "Password length exceeded!Too large password!")
        String password

) {}
