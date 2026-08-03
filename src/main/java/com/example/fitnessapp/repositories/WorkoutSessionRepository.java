package com.example.fitnessapp.repositories;

import com.example.fitnessapp.models.workouts.WorkoutSession;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WorkoutSessionRepository extends JpaRepository<WorkoutSession, Long> {
}
