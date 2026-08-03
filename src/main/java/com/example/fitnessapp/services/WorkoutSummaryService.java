package com.example.fitnessapp.services;

import com.example.fitnessapp.dto.WorkoutSummaryResponse;
import com.example.fitnessapp.models.workouts.WorkoutSet;
import com.example.fitnessapp.repositories.WorkoutSessionRepository;
import com.example.fitnessapp.repositories.WorkoutSetRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WorkoutSummaryService {

    private final WorkoutSessionRepository sessionRepo;
    private final WorkoutSetRepository setRepo;

    public WorkoutSummaryService(
            WorkoutSessionRepository sessionRepo,
            WorkoutSetRepository setRepo
    ) {
        this.sessionRepo = sessionRepo;
        this.setRepo = setRepo;
    }

    public WorkoutSummaryResponse getSessionSummary(Long sessionId) {

        if (!sessionRepo.existsById(sessionId)) {
            throw new IllegalArgumentException("Session not found");
        }

        // Get all sets in the session using optimized JPQL query
        List<WorkoutSet> sets = setRepo.findBySessionId(sessionId);

        int totalSets = sets.size();

        int totalReps = sets.stream()
                .mapToInt(s -> s.getActualReps() != null ? s.getActualReps() : 0)
                .sum();

        double totalVolume = sets.stream()
                .mapToDouble(s -> {
                    double w = s.getActualWeight() != null ? s.getActualWeight() : 0;
                    int r = s.getActualReps() != null ? s.getActualReps() : 0;
                    return w * r;
                })
                .sum();

        // Best estimated 1RM (using stored estimated1RMAfterSet)
        WorkoutSet bestSet = null;
        double best1RM = 0;

        for (WorkoutSet set : sets) {
            double est = set.getEstimated1RmAfterSet() != null ? set.getEstimated1RmAfterSet() : 0;
            if (est > best1RM) {
                best1RM = est;
                bestSet = set;
            }
        }

        return new WorkoutSummaryResponse(
                sessionId,
                totalSets,
                totalReps,
                totalVolume,
                best1RM,
                bestSet,
                sets
        );
    }
}
