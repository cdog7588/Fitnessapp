package com.example.fitnessapp.dto;

import java.util.List;

public class StimulusSummaryDTO {

    private double totalStimulus;
    private List<MuscleStimulusDTO> muscleBreakdown;

    public StimulusSummaryDTO(double totalStimulus, List<MuscleStimulusDTO> muscleBreakdown) {
        this.totalStimulus = totalStimulus;
        this.muscleBreakdown = muscleBreakdown;
    }

    public double getTotalStimulus() {
        return totalStimulus;
    }

    public List<MuscleStimulusDTO> getMuscleBreakdown() {
        return muscleBreakdown;
    }
}
