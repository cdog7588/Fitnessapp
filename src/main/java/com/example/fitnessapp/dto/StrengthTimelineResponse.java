package com.example.fitnessapp.dto;

import java.util.List;

public class StrengthTimelineResponse {

    private Long exerciseId;
    private List<Double> estimated1RMValues;
    private List<String> dates;
    private Double best1RM;
    private Double average1RM;

    public StrengthTimelineResponse(Long exerciseId, List<Double> estimated1RMValues,
                                    List<String> dates, Double best1RM, Double average1RM) {
        this.exerciseId = exerciseId;
        this.estimated1RMValues = estimated1RMValues;
        this.dates = dates;
        this.best1RM = best1RM;
        this.average1RM = average1RM;
    }

    public Long getExerciseId() { return exerciseId; }
    public List<Double> getEstimated1RMValues() { return estimated1RMValues; }
    public List<String> getDates() { return dates; }
    public Double getBest1RM() { return best1RM; }
    public Double getAverage1RM() { return average1RM; }
}
