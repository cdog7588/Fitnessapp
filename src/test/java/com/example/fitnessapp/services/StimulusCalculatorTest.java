package com.example.fitnessapp.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class StimulusCalculatorTest {

    private StimulusCalculator calculator;

    @BeforeEach
    public void setUp() {
        calculator = new StimulusCalculator();
    }

    @Test
    public void testCalculateIntensity() {
        assertEquals(0.8, calculator.calculateIntensity(200.0, 250.0), 0.001);
        assertEquals(0.0, calculator.calculateIntensity(200.0, 0.0), 0.001);
    }

    @Test
    public void testCalculateFatigue() {
        assertEquals(1.3, calculator.calculateFatigue(8.0), 0.001);
        assertEquals(1.5, calculator.calculateFatigue(10.0), 0.001);
    }

    @Test
    public void testCalculateMuscleStimulus() {
        double stimulus = calculator.calculateMuscleStimulus(10, 100.0, 0.8, 1.3);
        // 10 * 0.8 * 1.3 = 10.4
        assertEquals(10.4, stimulus, 0.01);
    }
}
