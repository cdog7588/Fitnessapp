package com.example.fitnessapp.controllers;

import com.example.fitnessapp.dto.StrengthTimelineResponse;
import com.example.fitnessapp.services.StrengthTimelineService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/strength-timeline")
public class StrengthTimelineController {

    private final StrengthTimelineService timelineService;

    public StrengthTimelineController(StrengthTimelineService timelineService) {
        this.timelineService = timelineService;
    }

    @GetMapping("/exercise/{exerciseId}")
    public ResponseEntity<StrengthTimelineResponse> getExerciseTimeline(@PathVariable Long exerciseId) {
        return ResponseEntity.ok(timelineService.getExerciseStrengthTimeline(exerciseId));
    }
}
