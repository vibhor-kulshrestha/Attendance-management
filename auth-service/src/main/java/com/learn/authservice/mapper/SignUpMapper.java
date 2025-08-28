package com.learn.authservice.mapper;

import com.learn.authservice.dto.SignupRequest;
import com.learn.authservice.dto.UserResponse;
import com.learn.authservice.entity.User;

public class SignUpMapper {
    public static UserResponse toResponse(User user) {
        return new UserResponse(
                user.getId(),
                user.getEmail(),
                user.getRole()
        );
    }
}
