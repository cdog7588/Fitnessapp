package com.example.fitnessapp.utils;

public class WeightAdjuster {

    public static double increaseBy2_5(double weight) {
        return weight + 2.5;
    }

    public static double increaseBy5(double weight) {
        return weight + 5.0;
    }

    public static double decreaseBy2_5(double weight) {
        return Math.max(0, weight - 2.5);
    }

    public static double decreaseBy5(double weight) {
        return Math.max(0, weight - 5.0);
    }
}
