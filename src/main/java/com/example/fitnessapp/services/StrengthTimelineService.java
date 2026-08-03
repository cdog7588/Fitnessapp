package com.example.fitnessapp.services;

import com.example.fitnessapp.dto.StrengthTimelineResponse;
import com.example.fitnessapp.models.workouts.WorkoutSet;
import com.example.fitnessapp.repositories.ExerciseRepository;
import com.example.fitnessapp.repositories.WorkoutSetRepository;
import org.springframework.stereotype.Service;

import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
public class StrengthTimelineService {

    private final WorkoutSetRepository setRepo;
    private final ExerciseRepository exerciseRepo;

    public StrengthTimelineService(WorkoutSetRepository setRepo, ExerciseRepository exerciseRepo) {
        this.setRepo = setRepo;
        this.exerciseRepo = exerciseRepo;
    }

    public StrengthTimelineResponse getExerciseStrengthTimeline(Long exerciseId) {

        exerciseRepo.findById(exerciseId)
                .orElseThrow(() -> new IllegalArgumentException("Exercise not found"));

        // Get all sets for this exercise using repository query
        List<WorkoutSet> sets = setRepo.findByExerciseId(exerciseId);

        // Extract estimated 1RM values
        List<Double> estimated1RMValues = sets.stream()
                .map(s -> s.getEstimated1RmAfterSet() != null ? s.getEstimated1RmAfterSet() : 0.0)
                .toList();

        // Extract dates (from WorkoutSession)
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        List<String> dates = sets.stream()
                .map(s -> (s.getWorkoutSession() != null && s.getWorkoutSession().getDate() != null)
                        ? s.getWorkoutSession().getDate().format(fmt)
                        : "unknown")
                .toList();

        // Best 1RM
        double best1RM = estimated1RMValues.stream()
                .mapToDouble(Double::doubleValue)
                .max()
                .orElse(0.0);

        // Average 1RM
        double average1RM = estimated1RMValues.stream()
                .mapToDouble(Double::doubleValue)
                .average()
                .orElse(0.0);

        return new StrengthTimelineResponse(
                exerciseId,
                estimated1RMValues,
                dates,
                best1RM,
                average1RM
        );
    }
}
