package com.example.fitnessapp.repositories;

import com.example.fitnessapp.models.ExerciseMuscleDistribution;
import com.example.fitnessapp.models.ExerciseMuscleKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExerciseMuscleDistributionRepository extends JpaRepository<ExerciseMuscleDistribution, ExerciseMuscleKey> {

    // Get all muscle distributions for a specific exercise
    List<ExerciseMuscleDistribution> findByExerciseId(Long exerciseId);

    // Get all exercises that target a specific muscle group
    List<ExerciseMuscleDistribution> findByMuscleGroupId(Long muscleGroupId);
}
