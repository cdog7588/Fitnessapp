package com.example.fitnessapp.controllers;

import com.example.fitnessapp.dto.AddMuscleDistributionRequest;
import com.example.fitnessapp.models.ExerciseMuscleDistribution;
import com.example.fitnessapp.services.ExerciseMuscleDistributionService;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/exercises")
public class ExerciseMuscleDistributionController {

    private final ExerciseMuscleDistributionService distributionService;

    public ExerciseMuscleDistributionController(ExerciseMuscleDistributionService distributionService) {
        this.distributionService = distributionService;
    }

    @PostMapping("/{exerciseId}/muscles")
    public ExerciseMuscleDistribution addOrUpdateMuscleDistribution(
            @PathVariable Long exerciseId,
            @RequestBody AddMuscleDistributionRequest request) {

        return distributionService.addOrUpdateMuscleDistribution(
                exerciseId,
                request.getMuscleGroupId(),
                request.getPercentage()
        );
    }
}