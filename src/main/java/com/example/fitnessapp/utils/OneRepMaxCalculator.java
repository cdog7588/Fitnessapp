package com.example.fitnessapp.utils;

public class OneRepMaxCalculator {

    // Epley Formula: 1RM = weight * (1 + reps / 30)
    public static double epley(double weight, int reps) {
        if (reps == 1) return weight;
        return weight * (1 + reps / 30.0);
    }

    // Brzycki Formula: 1RM = weight * (36 / (37 - reps))
    public static double brzycki(double weight, int reps) {
        if (reps == 1) return weight;
        if (reps >= 37) return weight; // avoid division by zero
        return weight * (36.0 / (37 - reps));
    }

    // Lombardi Formula: 1RM = weight * reps^0.10
    public static double lombardi(double weight, int reps) {
        if (reps == 1) return weight;
        return weight * Math.pow(reps, 0.10);
    }

    // Lander Formula: 1RM = (100 * weight) / (101.3 - 2.67123 * reps)
    public static double lander(double weight, int reps) {
        if (reps == 1) return weight;
        return (100 * weight) / (101.3 - 2.67123 * reps);
    }

    // Average of all formulas (recommended default)
    public static double average1RM(double weight, int reps) {
        double e = epley(weight, reps);
        double b = brzycki(weight, reps);
        double l = lombardi(weight, reps);
        double ld = lander(weight, reps);

        return (e + b + l + ld) / 4.0;
    }
}
