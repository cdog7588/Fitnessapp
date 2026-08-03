package com.example.fitnessapp.dto;

public class RecommendedWeightResponse {

    private Long exerciseId;
    private double recommendedWeight;
    private double lastWeight;
    private double estimated1RM;
    private String reason;

    public RecommendedWeightResponse(Long exerciseId, double recommendedWeight,
                                     double lastWeight, double estimated1RM, String reason) {
        this.exerciseId = exerciseId;
        this.recommendedWeight = recommendedWeight;
        this.lastWeight = lastWeight;
        this.estimated1RM = estimated1RM;
        this.reason = reason;
    }

    public Long getExerciseId() { return exerciseId; }
    public double getRecommendedWeight() { return recommendedWeight; }
    public double getLastWeight() { return lastWeight; }
    public double getEstimated1RM() { return estimated1RM; }
    public String getReason() { return reason; }
}
