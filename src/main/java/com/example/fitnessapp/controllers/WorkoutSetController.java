package com.example.fitnessapp.controllers;

import com.example.fitnessapp.models.workouts.WorkoutSet;
import com.example.fitnessapp.services.WorkoutSetService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/workout-sets")
public class WorkoutSetController {
    private final WorkoutSetService workoutSetService;

    public WorkoutSetController(WorkoutSetService workoutSetService) {
        this.workoutSetService = workoutSetService;
    }

    @PostMapping
    public ResponseEntity<WorkoutSet> createWorkoutSet(@RequestBody WorkoutSet set) {
        return ResponseEntity.ok(workoutSetService.createWorkoutSet(set));
    }

    @GetMapping
    public ResponseEntity<List<WorkoutSet>> getAllWorkoutSets() {
        return ResponseEntity.ok(workoutSetService.getAllWorkoutSets());
    }

    @GetMapping("/{id}")
    public ResponseEntity<WorkoutSet> getWorkoutSetById(@PathVariable Long id) {
        return workoutSetService.getWorkoutSetById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteWorkoutSet(@PathVariable Long id) {
        workoutSetService.deleteWorkoutSet(id);
        return ResponseEntity.noContent().build();
    }
}
