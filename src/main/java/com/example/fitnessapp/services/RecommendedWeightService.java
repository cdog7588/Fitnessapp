package com.example.fitnessapp.services;

import com.example.fitnessapp.dto.RecommendedWeightResponse;
import com.example.fitnessapp.models.workouts.WorkoutSet;
import com.example.fitnessapp.repositories.WorkoutSetRepository;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Objects;

@Service
public class RecommendedWeightService {

    private final WorkoutSetRepository setRepo;

    public RecommendedWeightService(WorkoutSetRepository setRepo) {
        this.setRepo = setRepo;
    }

    public RecommendedWeightResponse getRecommendedWeight(Long exerciseId) {

        // Get all sets for this exercise using repository query
        List<WorkoutSet> sets = setRepo.findByExerciseId(exerciseId)
                .stream()
                .sorted(Comparator.comparing(WorkoutSet::getId, Comparator.nullsLast(Comparator.reverseOrder())))
                .toList();

        if (sets.isEmpty()) {
            return new RecommendedWeightResponse(
                    exerciseId,
                    45.0, // default empty barbell
                    0.0,
                    0.0,
                    "No history found. Starting with empty barbell."
            );
        }

        WorkoutSet lastSet = sets.get(0);

        double lastWeight = lastSet.getActualWeight() != null ? lastSet.getActualWeight() : 0.0;
        double estimated1RM = lastSet.getEstimated1RmAfterSet() != null ? lastSet.getEstimated1RmAfterSet() : 0.0;

        // Basic progression: 5% of estimated 1RM
        double progressionAmount = estimated1RM * 0.05;

        // Fatigue adjustment: if reps < target, reduce progression
        boolean fatigued = lastSet.getActualReps() != null &&
                           lastSet.getTargetReps() != null &&
                           lastSet.getActualReps() < lastSet.getTargetReps();

        if (fatigued) {
            progressionAmount *= 0.5; // reduce progression by 50%
        }

        // Plateau detection: if last 3 sets have same estimated 1RM
        boolean plateau = sets.size() >= 3 &&
                Objects.equals(sets.get(0).getEstimated1RmAfterSet(), sets.get(1).getEstimated1RmAfterSet()) &&
                Objects.equals(sets.get(1).getEstimated1RmAfterSet(), sets.get(2).getEstimated1RmAfterSet()) &&
                sets.get(0).getEstimated1RmAfterSet() != null;

        if (plateau) {
            progressionAmount = estimated1RM * 0.02; // slow progression
        }

        double recommendedWeight = lastWeight + progressionAmount;

        String reason = fatigued
                ? "Reduced progression due to fatigue."
                : plateau
                    ? "Plateau detected. Slower progression applied."
                    : "Standard 5% progression applied.";

        return new RecommendedWeightResponse(
                exerciseId,
                recommendedWeight,
                lastWeight,
                estimated1RM,
                reason
        );
    }
}
