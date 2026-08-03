package com.example.fitnessapp.utils;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class StrengthRatioCalculatorTest {

    @Test
    public void testExpectedWeight() {
        double expected = StrengthRatioCalculator.expectedWeight(200.0, 0.6, 0.8);
        assertEquals(140.0, expected, 0.001);
    }

    @Test
    public void testStrengthCategory() {
        assertEquals("WEAK", StrengthRatioCalculator.strengthCategory(80.0));
        assertEquals("NORMAL", StrengthRatioCalculator.strengthCategory(100.0));
        assertEquals("STRONG", StrengthRatioCalculator.strengthCategory(120.0));
    }

    @Test
    public void testSuggestedIncrease() {
        assertEquals(5.0, StrengthRatioCalculator.suggestedIncrease(100.0, 80.0), 0.001);
        assertEquals(2.5, StrengthRatioCalculator.suggestedIncrease(100.0, 100.0), 0.001);
        assertEquals(10.0, StrengthRatioCalculator.suggestedIncrease(100.0, 120.0), 0.001);
    }
}
