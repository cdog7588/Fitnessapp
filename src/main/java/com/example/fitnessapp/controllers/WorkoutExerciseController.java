package com.example.fitnessapp.controllers;

import com.example.fitnessapp.models.workouts.WorkoutExercise;
import com.example.fitnessapp.services.WorkoutExerciseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

// 🌐 REST controller for WorkoutExercise endpoints
@RestController
@RequestMapping("/api/workout-exercises")
public class WorkoutExerciseController {

    private final WorkoutExerciseService workoutExerciseService;

    // ⚙️ Constructor injection for service
    @Autowired
    public WorkoutExerciseController(WorkoutExerciseService workoutExerciseService) {
        this.workoutExerciseService = workoutExerciseService;
    }

    // ➕ Create a new WorkoutExercise
    @PostMapping
    public ResponseEntity<WorkoutExercise> createWorkoutExercise(@RequestBody WorkoutExercise workoutExercise) {
        WorkoutExercise created = workoutExerciseService.createWorkoutExercise(workoutExercise);
        return ResponseEntity.ok(created);
    }

    // 🔍 Get all WorkoutExercises
    @GetMapping
    public ResponseEntity<List<WorkoutExercise>> getAllWorkoutExercises() {
        List<WorkoutExercise> exercises = workoutExerciseService.getAllWorkoutExercises();
        return ResponseEntity.ok(exercises);
    }

    // 🔎 Get a WorkoutExercise by ID
    @GetMapping("/{id}")
    public ResponseEntity<WorkoutExercise> getWorkoutExerciseById(@PathVariable Long id) {
        Optional<WorkoutExercise> exercise = workoutExerciseService.getWorkoutExerciseById(id);
        return exercise.map(ResponseEntity::ok)
                       .orElse(ResponseEntity.notFound().build());
    }

    // ✏️ Update a WorkoutExercise
    @PutMapping("/{id}")
    public ResponseEntity<WorkoutExercise> updateWorkoutExercise(
            @PathVariable Long id,
            @RequestBody WorkoutExercise updatedExercise) {
        WorkoutExercise updated = workoutExerciseService.updateWorkoutExercise(id, updatedExercise);
        return ResponseEntity.ok(updated);
    }

    // ❌ Delete a WorkoutExercise
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteWorkoutExercise(@PathVariable Long id) {
        workoutExerciseService.deleteWorkoutExercise(id);
        return ResponseEntity.noContent().build();
    }
}
