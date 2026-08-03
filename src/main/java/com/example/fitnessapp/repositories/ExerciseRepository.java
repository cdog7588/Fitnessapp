package com.example.fitnessapp.repositories;

import com.example.fitnessapp.models.exercises.Exercise;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExerciseRepository extends JpaRepository<Exercise, Long> {
    // You can add custom queries later, for example:
    // List<Exercise> findByMuscleGroup(String muscleGroup);
}