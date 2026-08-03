package com.example.fitnessapp.services;

import com.example.fitnessapp.models.workouts.WorkoutSet;
import org.springframework.beans.factory.annotation.Autowired;
import com.example.fitnessapp.repositories.WorkoutSetRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class WorkoutSetService {
    private final WorkoutSetRepository workoutSetRepository;

    @Autowired
    public WorkoutSetService(WorkoutSetRepository workoutSetRepository) {
        this.workoutSetRepository = workoutSetRepository;
    }

    public WorkoutSet createWorkoutSet(WorkoutSet set) {
        return workoutSetRepository.save(set);
    }

    public List<WorkoutSet> getAllWorkoutSets() {
        return workoutSetRepository.findAll();
    }

    public Optional<WorkoutSet> getWorkoutSetById(Long id) {
        return workoutSetRepository.findById(id);
    }

    public void deleteWorkoutSet(Long id) {
        workoutSetRepository.deleteById(id);
    }
}
