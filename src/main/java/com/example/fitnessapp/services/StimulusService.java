package com.example.fitnessapp.services;

import com.example.fitnessapp.models.ExerciseMuscleDistribution;
import com.example.fitnessapp.models.workouts.WorkoutSet;
import com.example.fitnessapp.repositories.ExerciseMuscleDistributionRepository;
import com.example.fitnessapp.repositories.WorkoutSetRepository;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class StimulusService {

    private final WorkoutSetRepository workoutSetRepository;
    private final ExerciseMuscleDistributionRepository distributionRepository;
    private final StimulusCalculator calculator;

    public StimulusService(
            WorkoutSetRepository workoutSetRepository,
            ExerciseMuscleDistributionRepository distributionRepository
    ) {
        this.workoutSetRepository = workoutSetRepository;
        this.distributionRepository = distributionRepository;
        this.calculator = new StimulusCalculator();
    }

    public Map<Long, Double> calculateStimulusForUser(Long userId) {

        List<WorkoutSet> sets = workoutSetRepository.findByWorkoutSessionUserId(userId);

        Map<Long, Double> muscleStimulus = new HashMap<>();

        for (WorkoutSet set : sets) {

            var exercise = set.getExercise();
            if (exercise == null) {
                continue;
            }
            List<ExerciseMuscleDistribution> emgList = distributionRepository.findByExerciseId(exercise.getId());

            double actualWeight = set.getActualWeight() != null ? set.getActualWeight() : 0;
            double estimated1Rm = set.getEstimated1RmAfterSet() != null ? set.getEstimated1RmAfterSet() : 0;
            int actualReps = set.getActualReps() != null ? set.getActualReps() : 0;

            double intensity = calculator.calculateIntensity(actualWeight, estimated1Rm);
            double fatigue = calculator.calculateFatigue(8.0);

            for (ExerciseMuscleDistribution emg : emgList) {

                double stimulus = calculator.calculateMuscleStimulus(
                        actualReps,
                        emg.getPercentage(),
                        intensity,
                        fatigue
                );

                muscleStimulus.merge(
                        emg.getMuscleGroup().getId(),
                        stimulus,
                        Double::sum
                );
            }
        }

        return muscleStimulus;
    }
}
