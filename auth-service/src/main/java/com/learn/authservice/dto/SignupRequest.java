package com.learn.authservice.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record SignupRequest(
        @NotNull(message = "email is required for register")
        @NotBlank
        @Email
        String email,
        @NotNull
        @NotBlank
        String password,
        @NotNull
        @NotBlank
        String userName
) {
}
