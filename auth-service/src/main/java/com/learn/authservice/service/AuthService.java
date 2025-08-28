package com.learn.authservice.service;

import com.learn.authservice.exception.InvalidCredentialsException;
import com.learn.authservice.exception.UserAlreadyExistsException;
import com.learn.authservice.dto.LoginRequest;
import com.learn.authservice.dto.LoginResponse;
import com.learn.authservice.dto.SignupRequest;
import com.learn.authservice.dto.UserResponse;
import com.learn.authservice.entity.User;
import com.learn.authservice.mapper.SignUpMapper;
import com.learn.authservice.repository.UserRepository;
import com.learn.authservice.security.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    public UserResponse registerUser(SignupRequest request) {
        if (userRepository.existsByEmail(request.email())) {
            throw new UserAlreadyExistsException("Email already registered");
        }
        User user = User.builder()
                .email(request.email())
                .username(request.userName())
                .password(passwordEncoder.encode(request.password()))
                .role("ROLE_USER")
                .build();
        userRepository.save(user);
        return SignUpMapper.toResponse(user);
    }

    public LoginResponse login(LoginRequest request) {
        User user = userRepository.findByEmail(request.email())
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
        if (!passwordEncoder.matches(request.password(), user.getPassword())) {
            throw new InvalidCredentialsException("Invalid email or password");
        }
        String token = jwtUtil.generateToken(user.getEmail(), user.getRole());
        return new LoginResponse(token);
    }
}
