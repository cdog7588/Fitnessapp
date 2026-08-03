package com.example.fitnessapp.services;

import com.example.fitnessapp.models.workouts.WorkoutExercise;
import com.example.fitnessapp.repositories.WorkoutExerciseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

// 🧠 Service layer for WorkoutExercise entity
@Service
public class WorkoutExerciseService {

    private final WorkoutExerciseRepository workoutExerciseRepository;

    // ⚙️ Constructor injection for repository
    @Autowired
    public WorkoutExerciseService(WorkoutExerciseRepository workoutExerciseRepository) {
        this.workoutExerciseRepository = workoutExerciseRepository;
    }

    // ➕ Create a new WorkoutExercise
    public WorkoutExercise createWorkoutExercise(WorkoutExercise workoutExercise) {
        return workoutExerciseRepository.save(workoutExercise);
    }

    // 🔍 Get all WorkoutExercises
    public List<WorkoutExercise> getAllWorkoutExercises() {
        return workoutExerciseRepository.findAll();
    }

    // 🔎 Get a WorkoutExercise by ID
    public Optional<WorkoutExercise> getWorkoutExerciseById(Long id) {
        return workoutExerciseRepository.findById(id);
    }

    // ✏️ Update an existing WorkoutExercise
    public WorkoutExercise updateWorkoutExercise(Long id, WorkoutExercise updatedExercise) {
        return workoutExerciseRepository.findById(id)
                .map(existing -> {
                    existing.setExercise(updatedExercise.getExercise());
                    existing.setSession(updatedExercise.getSession());
                    existing.setSets(updatedExercise.getSets());
                    return workoutExerciseRepository.save(existing);
                })
                .orElseThrow(() -> new RuntimeException("WorkoutExercise not found with id " + id));
    }

    // ❌ Delete a WorkoutExercise
    public void deleteWorkoutExercise(Long id) {
        workoutExerciseRepository.deleteById(id);
    }
}
