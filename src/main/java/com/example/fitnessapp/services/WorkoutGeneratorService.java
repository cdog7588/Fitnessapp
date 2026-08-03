package com.example.fitnessapp.services;

import com.example.fitnessapp.dto.GeneratedExerciseDTO;
import com.example.fitnessapp.dto.GeneratedWorkoutDTO;
import com.example.fitnessapp.models.exercises.Exercise;
import com.example.fitnessapp.models.ExerciseMuscleDistribution;
import com.example.fitnessapp.models.plans.MuscleGroup;
import com.example.fitnessapp.models.plans.WorkoutDay;
import com.example.fitnessapp.repositories.ExerciseMuscleDistributionRepository;
import com.example.fitnessapp.repositories.ExerciseRepository;
import com.example.fitnessapp.repositories.WorkoutDayRepository;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class WorkoutGeneratorService {

    private final WorkoutDayRepository workoutDayRepository;
    private final ExerciseRepository exerciseRepository;
    private final ExerciseMuscleDistributionRepository distributionRepository;

    public WorkoutGeneratorService(
            WorkoutDayRepository workoutDayRepository,
            ExerciseRepository exerciseRepository,
            ExerciseMuscleDistributionRepository distributionRepository
    ) {
        this.workoutDayRepository = workoutDayRepository;
        this.exerciseRepository = exerciseRepository;
        this.distributionRepository = distributionRepository;
    }

    public GeneratedWorkoutDTO generateWorkout(Long dayId) {

        WorkoutDay day = workoutDayRepository.findById(dayId)
                .orElseThrow(() -> new RuntimeException("Workout day not found"));

        // 1. Get target muscle groups
        Set<Long> targetMuscles = new HashSet<>();

        if (day.getMuscleGroups() != null) {
            for (MuscleGroup mg : day.getMuscleGroups()) {
                collectAllChildren(mg, targetMuscles);
            }
        }

        // 2. Get all exercises
        List<Exercise> allExercises = exerciseRepository.findAll();

        // 3. Score exercises
        Map<Exercise, Double> scores = new HashMap<>();

        for (Exercise exercise : allExercises) {
            List<ExerciseMuscleDistribution> dist =
                    distributionRepository.findByExerciseId(exercise.getId());

            double score = dist.stream()
                    .filter(d -> d.getMuscleGroup() != null && targetMuscles.contains(d.getMuscleGroup().getId()))
                    .mapToDouble(ExerciseMuscleDistribution::getPercentage)
                    .sum();

            // Compound bonus
            if (exercise.isCompound()) score *= 1.3;

            // Primary bonus
            if (exercise.isPrimary()) score *= 1.2;

            if (score > 0) {
                scores.put(exercise, score);
            }
        }

        // 4. Sort by score
        List<Map.Entry<Exercise, Double>> sorted =
                scores.entrySet().stream()
                        .sorted((a, b) -> Double.compare(b.getValue(), a.getValue()))
                        .toList();

        // 5. Allocate sets
        List<GeneratedExerciseDTO> generated = new ArrayList<>();

        for (Map.Entry<Exercise, Double> entry : sorted) {
            Exercise ex = entry.getKey();

            int sets;
            if (ex.isCompound()) sets = 4;
            else if (ex.isPrimary()) sets = 3;
            else sets = 2;

            generated.add(new GeneratedExerciseDTO(
                    ex.getId(),
                    ex.getName(),
                    sets
            ));
        }

        return new GeneratedWorkoutDTO(day.getDayName(), generated);
    }

    private void collectAllChildren(MuscleGroup group, Set<Long> result) {
        if (group == null) return;
        if (group.getId() != null) {
            result.add(group.getId());
        }
        if (group.getChildren() != null) {
            for (MuscleGroup child : group.getChildren()) {
                collectAllChildren(child, result);
            }
        }
    }
}
