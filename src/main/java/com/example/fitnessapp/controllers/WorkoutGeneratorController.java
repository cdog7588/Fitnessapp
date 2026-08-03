package com.example.fitnessapp.controllers;

import com.example.fitnessapp.dto.GeneratedWorkoutDTO;
import com.example.fitnessapp.services.WorkoutGeneratorService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/generator")
public class WorkoutGeneratorController {

    private final WorkoutGeneratorService generatorService;

    public WorkoutGeneratorController(WorkoutGeneratorService generatorService) {
        this.generatorService = generatorService;
    }

    @GetMapping("/day/{dayId}")
    public GeneratedWorkoutDTO generate(@PathVariable Long dayId) {
        return generatorService.generateWorkout(dayId);
    }
}
