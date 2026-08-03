package com.example.fitnessapp.utils;

public class StrengthRatioCalculator {

    // Calculate expected weight for an accessory exercise
    public static double expectedWeight(double reference1RM, double ratioMin, double ratioMax) {
        double avgRatio = (ratioMin + ratioMax) / 2.0;
        return reference1RM * avgRatio;
    }

    // Calculate minimum expected weight
    public static double expectedWeightMin(double reference1RM, double ratioMin) {
        return reference1RM * ratioMin;
    }

    // Calculate maximum expected weight
    public static double expectedWeightMax(double reference1RM, double ratioMax) {
        return reference1RM * ratioMax;
    }

    // Compare user performance to expected strength
    public static double performancePercentage(double actualWeight, double expectedWeight) {
        if (expectedWeight == 0) return 0;
        return (actualWeight / expectedWeight) * 100.0;
    }

    // Determine if user is weak, normal, or strong for this exercise
    public static String strengthCategory(double performancePercent) {
        if (performancePercent < 85) return "WEAK";
        if (performancePercent < 115) return "NORMAL";
        return "STRONG";
    }

    // Suggest weight increase based on performance
    public static double suggestedIncrease(double actualWeight, double performancePercent) {
        if (performancePercent < 85) {
            return actualWeight * 0.05; // add 5%
        } else if (performancePercent > 115) {
            return actualWeight * 0.10; // add 10%
        }
        return actualWeight * 0.025; // add 2.5% for normal
    }
}
