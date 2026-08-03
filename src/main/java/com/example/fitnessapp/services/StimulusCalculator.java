package com.example.fitnessapp.services;

public class StimulusCalculator {

    public double calculateIntensity(double weight, double estimated1RM) {
        if (estimated1RM == 0) return 0;
        return weight / estimated1RM;
    }

    public double calculateFatigue(double rpe) {
        return 0.5 + (rpe / 10.0);
    }

    public double calculateMuscleStimulus(int reps, double activationPercent,
                                          double intensity, double fatigue) {

        double effectiveActivation = activationPercent / 100.0 * intensity;
        return reps * effectiveActivation * fatigue;
    }
}

