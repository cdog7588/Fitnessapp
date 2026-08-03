package com.example.fitnessapp.controllers;

import com.example.fitnessapp.dto.WorkoutSummaryResponse;
import com.example.fitnessapp.services.WorkoutSummaryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/workout-summary")
public class WorkoutSummaryController {

    private final WorkoutSummaryService summaryService;

    public WorkoutSummaryController(WorkoutSummaryService summaryService) {
        this.summaryService = summaryService;
    }

    @GetMapping("/session/{sessionId}")
    public ResponseEntity<WorkoutSummaryResponse> getSessionSummary(@PathVariable Long sessionId) {
        return ResponseEntity.ok(summaryService.getSessionSummary(sessionId));
    }
}
