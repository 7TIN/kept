package com.dev.kept.Auth;

import org.springframework.http.ResponseEntity;

import com.dev.kept.dto.LoginRequestDto;
import com.dev.kept.dto.RegisterRequestDto;

public interface AuthService {
    ResponseEntity<?> register(RegisterRequestDto request);
    ResponseEntity<?> login(LoginRequestDto request);
}
