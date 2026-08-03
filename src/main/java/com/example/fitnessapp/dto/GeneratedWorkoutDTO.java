package com.example.fitnessapp.dto;

import java.util.List;

public record GeneratedWorkoutDTO(
        String dayName,
        List<GeneratedExerciseDTO> exercises
) {}
