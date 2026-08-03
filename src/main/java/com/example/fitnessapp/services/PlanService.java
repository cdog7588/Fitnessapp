package com.example.fitnessapp.services;

import com.example.fitnessapp.models.plans.WorkoutPlan;
import com.example.fitnessapp.repositories.WorkoutPlanRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlanService {

    private final WorkoutPlanRepository workoutPlanRepository;

    public PlanService(WorkoutPlanRepository workoutPlanRepository) {
        this.workoutPlanRepository = workoutPlanRepository;
    }

    public List<WorkoutPlan> getAllPlans() {
        return workoutPlanRepository.findAll();
    }

    public WorkoutPlan getPlanById(Long id) {
        return workoutPlanRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Plan not found: " + id));
    }
}
