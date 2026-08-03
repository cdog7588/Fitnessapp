package com.example.fitnessapp.controllers;

import com.example.fitnessapp.models.plans.WorkoutPlan;
import com.example.fitnessapp.services.PlanService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/plans")
public class PlanController {

    private final PlanService planService;

    public PlanController(PlanService planService) {
        this.planService = planService;
    }

    @GetMapping
    public List<WorkoutPlan> getAllPlans() {
        return planService.getAllPlans();
    }

    @GetMapping("/{id}")
    public WorkoutPlan getPlanById(@PathVariable Long id) {
        return planService.getPlanById(id);
    }
}
