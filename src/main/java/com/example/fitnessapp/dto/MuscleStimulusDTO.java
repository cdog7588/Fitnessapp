package com.example.fitnessapp.dto;

public class MuscleStimulusDTO {

    private Long muscleGroupId;
    private String muscleName;
    private double stimulus;

    public MuscleStimulusDTO(Long muscleGroupId, String muscleName, double stimulus) {
        this.muscleGroupId = muscleGroupId;
        this.muscleName = muscleName;
        this.stimulus = stimulus;
    }

    public Long getMuscleGroupId() {
        return muscleGroupId;
    }

    public String getMuscleName() {
        return muscleName;
    }

    public double getStimulus() {
        return stimulus;
    }
}
