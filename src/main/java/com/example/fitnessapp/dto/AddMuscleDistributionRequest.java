package com.example.fitnessapp.dto;

public class AddMuscleDistributionRequest {
    private Long muscleGroupId;
    private int percentage;

    public Long getMuscleGroupId() { return muscleGroupId; }
    public void setMuscleGroupId(Long muscleGroupId) { this.muscleGroupId = muscleGroupId; }

    public int getPercentage() { return percentage; }
    public void setPercentage(int percentage) { this.percentage = percentage; }
}