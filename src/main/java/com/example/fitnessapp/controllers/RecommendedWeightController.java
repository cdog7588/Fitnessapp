package com.example.fitnessapp.controllers;

import com.example.fitnessapp.dto.RecommendedWeightResponse;
import com.example.fitnessapp.services.RecommendedWeightService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/recommendations")
public class RecommendedWeightController {

    private final RecommendedWeightService recommendedWeightService;

    public RecommendedWeightController(RecommendedWeightService recommendedWeightService) {
        this.recommendedWeightService = recommendedWeightService;
    }

    @GetMapping("/exercise/{exerciseId}")
    public ResponseEntity<RecommendedWeightResponse> getRecommendedWeight(@PathVariable Long exerciseId) {
        return ResponseEntity.ok(recommendedWeightService.getRecommendedWeight(exerciseId));
    }
}
