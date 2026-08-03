package com.example.fitnessapp.services;

import com.example.fitnessapp.models.AppUser;
import com.example.fitnessapp.repositories.AppUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AppUserService {

    @Autowired
    private AppUserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    // Register a new user from JSON request
    public AppUser register(String username, String rawPassword) {
    if (userRepository.findByUsername(username).isPresent()) {
        throw new IllegalArgumentException("Username already exists");
    }
    String encodedPassword = passwordEncoder.encode(rawPassword);
    AppUser user = new AppUser(username, encodedPassword);
    return userRepository.save(user);
}

    // Find user by username
    public Optional<AppUser> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    // Validate login credentials (used by JSON login)
    public boolean login(String username, String rawPassword) {
        return findByUsername(username)
                .map(user -> passwordEncoder.matches(rawPassword, user.getPassword()))
                .orElse(false);
    }
}