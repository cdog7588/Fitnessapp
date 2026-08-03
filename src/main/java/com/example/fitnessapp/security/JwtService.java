package com.example.fitnessapp.security;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class JwtService {

    private static final String SECRET = "fitness-app-secret-key-1234567890";
    private static final String HMAC_ALGORITHM = "HmacSHA256";
    private static final long EXPIRATION_MILLIS = 60L * 60L * 1000L;
    private static final Pattern SUBJECT_PATTERN = Pattern.compile("\"sub\":\"([^\"]+)\"");
    private static final Pattern EXPIRATION_PATTERN = Pattern.compile("\"exp\":([0-9]+)");

    public String generateToken(String username) {
        long now = System.currentTimeMillis();
        long exp = now + EXPIRATION_MILLIS;

        String header = base64UrlEncode("{\"alg\":\"HS256\",\"typ\":\"JWT\"}");
        String payload = base64UrlEncode("{\"sub\":\"" + username + "\",\"iat\":" + (now / 1000) + ",\"exp\":" + (exp / 1000) + "}");
        String signingInput = header + "." + payload;
        String signature = base64UrlEncode(sign(signingInput));

        return signingInput + "." + signature;
    }

    public String extractUsername(String token) {
        String[] parts = token.split("\\.");
        if (parts.length != 3) {
            throw new IllegalArgumentException("Invalid token format");
        }

        String signingInput = parts[0] + "." + parts[1];
        String expectedSignature = base64UrlEncode(sign(signingInput));
        if (!MessageDigest.isEqual(parts[2].getBytes(StandardCharsets.UTF_8), expectedSignature.getBytes(StandardCharsets.UTF_8))) {
            throw new IllegalArgumentException("Invalid token signature");
        }

        String payloadJson = new String(Base64.getUrlDecoder().decode(parts[1]), StandardCharsets.UTF_8);
        Matcher expMatcher = EXPIRATION_PATTERN.matcher(payloadJson);
        if (expMatcher.find()) {
            long expirationSeconds = Long.parseLong(expMatcher.group(1));
            if (System.currentTimeMillis() / 1000 >= expirationSeconds) {
                throw new IllegalArgumentException("Token has expired");
            }
        }

        Matcher subjectMatcher = SUBJECT_PATTERN.matcher(payloadJson);
        if (subjectMatcher.find()) {
            return subjectMatcher.group(1);
        }

        throw new IllegalArgumentException("Token is missing a subject");
    }

    private byte[] sign(String input) {
        try {
            Mac mac = Mac.getInstance(HMAC_ALGORITHM);
            SecretKeySpec secretKey = new SecretKeySpec(SECRET.getBytes(StandardCharsets.UTF_8), HMAC_ALGORITHM);
            mac.init(secretKey);
            return mac.doFinal(input.getBytes(StandardCharsets.UTF_8));
        } catch (NoSuchAlgorithmException | InvalidKeyException e) {
            throw new IllegalStateException("Unable to sign token", e);
        }
    }

    private String base64UrlEncode(String value) {
        return Base64.getUrlEncoder().withoutPadding().encodeToString(value.getBytes(StandardCharsets.UTF_8));
    }

    private String base64UrlEncode(byte[] value) {
        return Base64.getUrlEncoder().withoutPadding().encodeToString(value);
    }
  
    public boolean isTokenValid(String token, UserDetails userDetails) {
    try {
        String username = extractUsername(token);
        return username.equals(userDetails.getUsername()) && !isTokenExpired(token);
    } catch (IllegalArgumentException e) {
        return false;
    }
}


//Expiration logic for jwt 
public boolean isTokenExpired(String token) {
    try {
        String[] parts = token.split("\\.");
        if (parts.length != 3) return true;

        String payloadJson = new String(Base64.getUrlDecoder().decode(parts[1]), StandardCharsets.UTF_8);
        Matcher expMatcher = EXPIRATION_PATTERN.matcher(payloadJson);
        if (expMatcher.find()) {
            long expirationSeconds = Long.parseLong(expMatcher.group(1));
            return System.currentTimeMillis() / 1000 >= expirationSeconds;
        }
        return true; // no exp field means invalid
    } catch (Exception e) {
        return true;
    }
}


}
