package com.example.fitnessapp.controllers;

import org.springframework.core.io.ClassPathResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Map;
import java.util.List;

@RestController
@RequestMapping("/architecture")
public class ArchitectureController {

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Map<String, Object>> getArchitecture() {
        Map<String, Object> architecture = Map.of(
                "application", "fitnessapp",
                "framework", "Spring Boot 3",
                "database", "MySQL",
                "security", "JWT",
                "packageRoot", "com.example.fitnessapp",
                "modules", List.of(
                        "auth",
                        "exercises",
                        "muscle-distributions",
                        "plans",
                        "workout-sessions",
                        "workout-exercises",
                        "workout-sets",
                        "recommendations",
                        "stimulus",
                        "strength-timeline"
                ),
                "status", "HEALTHY"
        );
        return ResponseEntity.ok(architecture);
    }

    @GetMapping(value = "/manifest", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> getManifest() {
        try {
            ClassPathResource resource = new ClassPathResource("project-intent.json");
            String content = StreamUtils.copyToString(resource.getInputStream(), StandardCharsets.UTF_8);
            return ResponseEntity.ok(content);
        } catch (IOException e) {
            return ResponseEntity.internalServerError()
                    .body("{\"error\":\"Could not read project intent manifest\"}");
        }
    }
}
