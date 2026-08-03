package com.example.fitnessapp.controllers;

import com.example.fitnessapp.models.workouts.WorkoutSession;
import com.example.fitnessapp.models.workouts.WorkoutSet;
import com.example.fitnessapp.services.WorkoutService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/workout")
public class WorkoutController {

    private final WorkoutService workoutService;

    public WorkoutController(WorkoutService workoutService) {
        this.workoutService = workoutService;
    }

    // 🔍 Get full workout (session → exercises → sets)
    @GetMapping("/session/{sessionId}")
    public ResponseEntity<WorkoutSession> getFullWorkout(@PathVariable Long sessionId) {
        return ResponseEntity.ok(workoutService.getFullWorkout(sessionId));
    }

    // 📜 Get workout history (all sessions)
    @GetMapping("/history")
    public ResponseEntity<List<WorkoutSession>> getWorkoutHistory() {
        return ResponseEntity.ok(workoutService.getWorkoutHistory());
    }

    // 📈 Get all sets for a specific exercise (exercise history)
    @GetMapping("/exercise/{exerciseId}/history")
    public ResponseEntity<List<WorkoutSet>> getExerciseHistory(@PathVariable Long exerciseId) {
        return ResponseEntity.ok(workoutService.getExerciseHistory(exerciseId));
    }
}
