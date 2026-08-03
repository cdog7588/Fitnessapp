package com.example.fitnessapp.services;

import com.example.fitnessapp.models.workouts.WorkoutSession;
import com.example.fitnessapp.repositories.WorkoutSessionRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WorkoutSessionService {

    private final WorkoutSessionRepository workoutSessionRepository;

    public WorkoutSessionService(WorkoutSessionRepository workoutSessionRepository) {
        this.workoutSessionRepository = workoutSessionRepository;
    }

    // ✅ Get all sessions
    public List<WorkoutSession> getAllWorkoutSessions() {
        return workoutSessionRepository.findAll();
    }

    // ✅ Get one session by ID
    public WorkoutSession getWorkoutSessionById(Long id) {
        return workoutSessionRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Workout session not found with id: " + id));
    }

    // ✅ Create new session
    public WorkoutSession createWorkoutSession(WorkoutSession session) {
        return workoutSessionRepository.save(session);
    }

    // ✅ Update existing session
    public WorkoutSession updateWorkoutSession(Long id, WorkoutSession updatedSession) {
        WorkoutSession existing = getWorkoutSessionById(id);
        existing.setName(updatedSession.getName());
        existing.setDate(updatedSession.getDate());
        existing.setWorkoutSets(updatedSession.getWorkoutSets());
        return workoutSessionRepository.save(existing);
    }

    // ✅ Delete session
    public void deleteWorkoutSession(Long id) {
        WorkoutSession existing = getWorkoutSessionById(id);
        workoutSessionRepository.delete(existing);
    }
}