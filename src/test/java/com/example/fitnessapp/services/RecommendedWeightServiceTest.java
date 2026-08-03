package com.example.fitnessapp.services;

import com.example.fitnessapp.dto.RecommendedWeightResponse;
import com.example.fitnessapp.models.exercises.Exercise;
import com.example.fitnessapp.models.workouts.WorkoutSet;
import com.example.fitnessapp.repositories.WorkoutSetRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

public class RecommendedWeightServiceTest {

    @Test
    public void testGetRecommendedWeightNoHistory() {
        WorkoutSetRepository setRepo = Mockito.mock(WorkoutSetRepository.class);
        when(setRepo.findByExerciseId(anyLong())).thenReturn(Collections.emptyList());

        RecommendedWeightService service = new RecommendedWeightService(setRepo);
        RecommendedWeightResponse response = service.getRecommendedWeight(1L);

        assertEquals(1L, response.getExerciseId());
        assertEquals(45.0, response.getRecommendedWeight());
        assertTrue(response.getReason().contains("No history found"));
    }

    @Test
    public void testGetRecommendedWeightStandardProgression() {
        WorkoutSetRepository setRepo = Mockito.mock(WorkoutSetRepository.class);

        Exercise ex = new Exercise();
        ex.setId(1L);

        WorkoutSet s1 = new WorkoutSet();
        s1.setId(1L);
        s1.setExercise(ex);
        s1.setActualWeight(200.0);
        s1.setEstimated1RMAfterSet(250.0);
        s1.setActualReps(10);
        s1.setTargetReps(10);

        when(setRepo.findByExerciseId(1L)).thenReturn(List.of(s1));

        RecommendedWeightService service = new RecommendedWeightService(setRepo);
        RecommendedWeightResponse response = service.getRecommendedWeight(1L);

        // 200 + (250 * 0.05) = 212.5
        assertEquals(212.5, response.getRecommendedWeight(), 0.001);
        assertTrue(response.getReason().contains("Standard 5% progression"));
    }
}
