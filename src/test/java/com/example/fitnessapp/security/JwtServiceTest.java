package com.example.fitnessapp.security;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;

class JwtServiceTest {

    private static final String SECRET = "a-test-secret-that-is-longer-than-thirty-two-characters";

    @Test
    void generatesAndVerifiesTokenForUsername() {
        JwtService service = new JwtService(SECRET);

        String token = service.generateToken("athlete");

        assertEquals("athlete", service.extractUsername(token));
        assertFalse(service.isTokenExpired(token));
    }

    @Test
    void rejectsTamperedToken() {
        JwtService service = new JwtService(SECRET);
        String token = service.generateToken("athlete");
        String tampered = token.substring(0, token.length() - 1) + "x";

        assertThrows(IllegalArgumentException.class, () -> service.extractUsername(tampered));
    }

    @Test
    void requiresSufficientlyLongSigningSecret() {
        assertThrows(IllegalStateException.class, () -> new JwtService("too-short"));
    }
}
