package com.example.fitnessapp.repositories;

import com.example.fitnessapp.models.workouts.WorkoutSet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface WorkoutSetRepository extends JpaRepository<WorkoutSet, Long> {

    List<WorkoutSet> findByWorkoutSessionUserId(Long userId);

    List<WorkoutSet> findByWorkoutSessionId(Long sessionId);

    List<WorkoutSet> findByWorkoutExerciseId(Long workoutExerciseId);

    @Query("SELECT s FROM WorkoutSet s WHERE s.exercise.id = :exerciseId OR s.workoutExercise.exercise.id = :exerciseId")
    List<WorkoutSet> findByExerciseId(@Param("exerciseId") Long exerciseId);

    @Query("SELECT s FROM WorkoutSet s WHERE s.workoutSession.id = :sessionId OR s.workoutExercise.session.id = :sessionId")
    List<WorkoutSet> findBySessionId(@Param("sessionId") Long sessionId);
}
