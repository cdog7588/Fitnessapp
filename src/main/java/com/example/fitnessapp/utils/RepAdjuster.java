package com.example.fitnessapp.utils;

public class RepAdjuster {

    public static int increase(int reps) {
        return reps + 1;
    }

    public static int decrease(int reps) {
        return Math.max(1, reps - 1);
    }
}
