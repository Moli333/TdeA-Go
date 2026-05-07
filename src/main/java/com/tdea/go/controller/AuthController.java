package com.tdea.go.controller;

import com.tdea.go.dto.*;
import com.tdea.go.service.AuthService;

import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "http://localhost:3000")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    // POST /api/auth/register
    @PostMapping("/register")
    public Map<String, Object> register(
            @RequestBody RegisterRequest request) {

        return authService.register(request);
    }

    // POST /api/auth/login
    @PostMapping("/login")
    public Map<String, Object> login(
            @RequestBody LoginRequest request) {

        return authService.login(request);
    }
}