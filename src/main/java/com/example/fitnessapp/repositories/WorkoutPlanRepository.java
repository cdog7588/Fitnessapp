package com.example.fitnessapp.repositories;

import com.example.fitnessapp.models.plans.WorkoutPlan;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WorkoutPlanRepository extends JpaRepository<WorkoutPlan, Long> {
}
