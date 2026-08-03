package com.example.fitnessapp.utils;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class OneRepMaxCalculatorTest {

    @Test
    public void testEpleyFormula() {
        assertEquals(225.0, OneRepMaxCalculator.epley(225.0, 1), 0.001);
        double est = OneRepMaxCalculator.epley(200.0, 10);
        // 200 * (1 + 10/30) = 266.666
        assertEquals(266.666, est, 0.01);
    }

    @Test
    public void testBrzyckiFormula() {
        assertEquals(200.0, OneRepMaxCalculator.brzycki(200.0, 1), 0.001);
        double est = OneRepMaxCalculator.brzycki(200.0, 10);
        // 200 * (36 / 27) = 266.666
        assertEquals(266.666, est, 0.01);
    }

    @Test
    public void testLombardiFormula() {
        assertEquals(200.0, OneRepMaxCalculator.lombardi(200.0, 1), 0.001);
        double est = OneRepMaxCalculator.lombardi(200.0, 10);
        // 200 * 10^0.1 = 251.785
        assertEquals(251.785, est, 0.1);
    }

    @Test
    public void testLanderFormula() {
        assertEquals(200.0, OneRepMaxCalculator.lander(200.0, 1), 0.001);
        double est = OneRepMaxCalculator.lander(200.0, 10);
        // (100 * 200) / (101.3 - 26.7123) = 20000 / 74.5877 = 268.14
        assertTrue(est > 260.0 && est < 275.0);
    }

    @Test
    public void testAverage1RM() {
        double avg = OneRepMaxCalculator.average1RM(200.0, 10);
        assertTrue(avg > 250.0 && avg < 280.0);
    }
}
