package com.example.fitnessapp.services;

import com.example.fitnessapp.models.workouts.WorkoutExercise;
import com.example.fitnessapp.models.workouts.WorkoutSession;
import com.example.fitnessapp.models.workouts.WorkoutSet;
import com.example.fitnessapp.repositories.WorkoutExerciseRepository;
import com.example.fitnessapp.repositories.WorkoutSessionRepository;
import com.example.fitnessapp.repositories.WorkoutSetRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WorkoutService {

    private final WorkoutSessionRepository sessionRepo;
    private final WorkoutExerciseRepository exerciseRepo;
    private final WorkoutSetRepository setRepo;

    public WorkoutService(
            WorkoutSessionRepository sessionRepo,
            WorkoutExerciseRepository exerciseRepo,
            WorkoutSetRepository setRepo
    ) {
        this.sessionRepo = sessionRepo;
        this.exerciseRepo = exerciseRepo;
        this.setRepo = setRepo;
    }

    // 🔍 Return full workout with nested exercises + sets
    public WorkoutSession getFullWorkout(Long sessionId) {

        WorkoutSession session = sessionRepo.findById(sessionId)
                .orElseThrow(() -> new IllegalArgumentException("Workout session not found"));

        // Get exercises for this session
        List<WorkoutExercise> exercises = exerciseRepo.findBySessionId(sessionId);

        // Attach sets to each exercise
        exercises.forEach(exercise -> {
            List<WorkoutSet> sets = setRepo.findByWorkoutExerciseId(exercise.getId());
            exercise.setSets(sets);
        });

        session.setWorkoutExercises(exercises);
        return session;
    }

    // 🔍 Return all workouts (history)
    public List<WorkoutSession> getWorkoutHistory() {
        return sessionRepo.findAll();
    }

    // 🔍 Return all sets for a specific exercise
    public List<WorkoutSet> getExerciseHistory(Long exerciseId) {
        return setRepo.findByExerciseId(exerciseId);
    }
}
