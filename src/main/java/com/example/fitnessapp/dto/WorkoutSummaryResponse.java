package com.example.fitnessapp.dto;

import com.example.fitnessapp.models.workouts.WorkoutSet;

import java.util.List;

public class WorkoutSummaryResponse {

    private Long sessionId;
    private int totalSets;
    private int totalReps;
    private double totalVolume;
    private double bestEstimated1RM;
    private WorkoutSet bestSet;
    private List<WorkoutSet> sets;

    public WorkoutSummaryResponse(Long sessionId, int totalSets, int totalReps,
                                  double totalVolume, double bestEstimated1RM,
                                  WorkoutSet bestSet, List<WorkoutSet> sets) {
        this.sessionId = sessionId;
        this.totalSets = totalSets;
        this.totalReps = totalReps;
        this.totalVolume = totalVolume;
        this.bestEstimated1RM = bestEstimated1RM;
        this.bestSet = bestSet;
        this.sets = sets;
    }

    public Long getSessionId() { return sessionId; }
    public int getTotalSets() { return totalSets; }
    public int getTotalReps() { return totalReps; }
    public double getTotalVolume() { return totalVolume; }
    public double getBestEstimated1RM() { return bestEstimated1RM; }
    public WorkoutSet getBestSet() { return bestSet; }
    public List<WorkoutSet> getSets() { return sets; }
}
