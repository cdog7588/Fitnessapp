package com.example.fitnessapp.repositories;

import com.example.fitnessapp.models.plans.WorkoutDay;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WorkoutDayRepository extends JpaRepository<WorkoutDay, Long> {
}
