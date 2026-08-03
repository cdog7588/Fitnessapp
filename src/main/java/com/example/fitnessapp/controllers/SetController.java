package com.example.fitnessapp.controllers;

import com.example.fitnessapp.services.SetService;
import com.example.fitnessapp.utils.SetPrediction;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/set")
public class SetController {

    private final SetService setService;

    public SetController(SetService setService) {
        this.setService = setService;
    }

    // Predict PR status for any set
    @GetMapping("/predict")
    public SetPrediction predict(
            @RequestParam double currentPR,
            @RequestParam double weight,
            @RequestParam int reps
    ) {
        return setService.predict(currentPR, weight, reps);
    }

    // Increase weight by 2.5
    @GetMapping("/increase-weight-2-5")
    public SetPrediction increaseWeight2_5(
            @RequestParam double currentPR,
            @RequestParam double weight,
            @RequestParam int reps
    ) {
        return setService.increaseWeightBy2_5(currentPR, weight, reps);
    }

    // Increase weight by 5
    @GetMapping("/increase-weight-5")
    public SetPrediction increaseWeight5(
            @RequestParam double currentPR,
            @RequestParam double weight,
            @RequestParam int reps
    ) {
        return setService.increaseWeightBy5(currentPR, weight, reps);
    }

    // Decrease weight by 2.5
    @GetMapping("/decrease-weight-2-5")
    public SetPrediction decreaseWeight2_5(
            @RequestParam double currentPR,
            @RequestParam double weight,
            @RequestParam int reps
    ) {
        return setService.decreaseWeightBy2_5(currentPR, weight, reps);
    }

    // Decrease weight by 5
    @GetMapping("/decrease-weight-5")
    public SetPrediction decreaseWeight5(
            @RequestParam double currentPR,
            @RequestParam double weight,
            @RequestParam int reps
    ) {
        return setService.decreaseWeightBy5(currentPR, weight, reps);
    }

    // Increase reps by 1
    @GetMapping("/increase-reps")
    public SetPrediction increaseReps(
            @RequestParam double currentPR,
            @RequestParam double weight,
            @RequestParam int reps
    ) {
        return setService.increaseReps(currentPR, weight, reps);
    }

    // Decrease reps by 1
    @GetMapping("/decrease-reps")
    public SetPrediction decreaseReps(
            @RequestParam double currentPR,
            @RequestParam double weight,
            @RequestParam int reps
    ) {
        return setService.decreaseReps(currentPR, weight, reps);
    }

    // Manual input for weight or reps
    @GetMapping("/manual")
    public SetPrediction manualInput(
            @RequestParam double currentPR,
            @RequestParam double weight,
            @RequestParam int reps
    ) {
        return setService.predict(currentPR, weight, reps);
    }
}

