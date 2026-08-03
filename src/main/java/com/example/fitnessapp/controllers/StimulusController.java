package com.example.fitnessapp.controllers;

import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;

import com.example.fitnessapp.services.StimulusService;

import java.util.Map;

@RestController
@RequestMapping("/api/stimulus")
public class StimulusController {

    @Autowired
    private StimulusService stimulusService;

    @GetMapping("/{userId}")
    public Map<Long, Double> getStimulus(@PathVariable Long userId) {
        return stimulusService.calculateStimulusForUser(userId);
    }
}

