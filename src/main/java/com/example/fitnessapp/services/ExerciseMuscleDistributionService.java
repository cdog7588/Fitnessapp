package com.example.fitnessapp.services;

import com.example.fitnessapp.models.ExerciseMuscleDistribution;
import com.example.fitnessapp.models.ExerciseMuscleKey;
import com.example.fitnessapp.models.exercises.Exercise;
import com.example.fitnessapp.models.plans.MuscleGroup;
import com.example.fitnessapp.repositories.ExerciseRepository;
import com.example.fitnessapp.repositories.MuscleGroupRepository;
import com.example.fitnessapp.repositories.ExerciseMuscleDistributionRepository;

import org.springframework.stereotype.Service;

@Service
public class ExerciseMuscleDistributionService {

    private final ExerciseRepository exerciseRepository;
    private final MuscleGroupRepository muscleGroupRepository;
    private final ExerciseMuscleDistributionRepository distributionRepository;

    public ExerciseMuscleDistributionService(
            ExerciseRepository exerciseRepository,
            MuscleGroupRepository muscleGroupRepository,
            ExerciseMuscleDistributionRepository distributionRepository) {

        this.exerciseRepository = exerciseRepository;
        this.muscleGroupRepository = muscleGroupRepository;
        this.distributionRepository = distributionRepository;
    }

   public ExerciseMuscleDistribution addOrUpdateMuscleDistribution(Long exerciseId, Long muscleGroupId, int percentage) {

    ExerciseMuscleKey key = new ExerciseMuscleKey(exerciseId, muscleGroupId);

    ExerciseMuscleDistribution distribution =
            distributionRepository.findById(key).orElse(null);

    if (distribution != null) {
        distribution.setPercentage(percentage);
        return distributionRepository.save(distribution);
    }

    Exercise exercise = exerciseRepository.findById(exerciseId)
            .orElseThrow(() -> new RuntimeException("Exercise not found"));

    MuscleGroup muscleGroup = muscleGroupRepository.findById(muscleGroupId)
            .orElseThrow(() -> new RuntimeException("Muscle group not found"));

    distribution = new ExerciseMuscleDistribution(exercise, muscleGroup, percentage);
    distribution.setId(key);

    return distributionRepository.save(distribution);
}

}
