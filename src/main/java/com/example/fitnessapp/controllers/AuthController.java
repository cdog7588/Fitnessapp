package com.example.fitnessapp.controllers;

import com.example.fitnessapp.models.AppUser;
import com.example.fitnessapp.services.AppUserService;

import jakarta.servlet.http.HttpServletRequest;

import com.example.fitnessapp.dto.RegisterRequest;
import com.example.fitnessapp.dto.LoginRequest;
import com.example.fitnessapp.dto.UserResponse;
import com.example.fitnessapp.security.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private JwtService jwtService;

    @Autowired
    private AppUserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterRequest request) {
        try {
            AppUser user = userService.register(request.getUsername(), request.getPassword());
            return ResponseEntity.ok(new UserResponse(user.getId(), user.getUsername()));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {
        Optional<AppUser> optionalUser = userService.findByUsername(request.getUsername());
        if (optionalUser.isPresent() &&
            passwordEncoder.matches(request.getPassword(), optionalUser.get().getPassword())) {
            String token = jwtService.generateToken(optionalUser.get().getUsername());
            return ResponseEntity.ok(Map.of("token", token));
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                .body(Map.of("error", "Invalid username or password"));
    }

    @PostMapping("/logout")
    public ResponseEntity<String> logout(HttpServletRequest request) {
        return ResponseEntity.ok("Logged out successfully. Please remove token from client storage.");
    }

    @PostMapping("/refresh")
    public ResponseEntity<?> refreshToken(@RequestHeader(value = "Authorization", required = false) String authHeader) {
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            return ResponseEntity.badRequest().body(Map.of("error", "Missing or invalid Authorization header"));
        }
        try {
            String oldToken = authHeader.substring(7);
            String username = jwtService.extractUsername(oldToken);
            String newToken = jwtService.generateToken(username);
            return ResponseEntity.ok(Map.of("token", newToken));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Map.of("error", e.getMessage()));
        }
    }
}