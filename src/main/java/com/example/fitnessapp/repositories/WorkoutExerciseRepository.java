package com.example.fitnessapp.repositories;

import com.example.fitnessapp.models.workouts.WorkoutExercise;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WorkoutExerciseRepository extends JpaRepository<WorkoutExercise, Long> {
    List<WorkoutExercise> findBySessionId(Long sessionId);
}