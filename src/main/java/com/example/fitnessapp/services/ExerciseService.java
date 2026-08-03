package com.example.fitnessapp.services;

import com.example.fitnessapp.models.exercises.Exercise;
import com.example.fitnessapp.repositories.ExerciseRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExerciseService {

    private final ExerciseRepository exerciseRepository;

    public ExerciseService(ExerciseRepository exerciseRepository) {
        this.exerciseRepository = exerciseRepository;
    }

    // ✅ Get all exercises
    public List<Exercise> getAllExercises() {
        return exerciseRepository.findAll();
    }

    // ✅ Get one exercise by ID
    public Exercise getExerciseById(Long id) {
        return exerciseRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Exercise not found with id: " + id));
    }

    // ✅ Create new exercise
    public Exercise createExercise(Exercise exercise) {
        return exerciseRepository.save(exercise);
    }

    // ✅ Update existing exercise
    public Exercise updateExercise(Long id, Exercise updatedExercise) {
        Exercise existing = getExerciseById(id);
        existing.setName(updatedExercise.getName());
        existing.setDescription(updatedExercise.getDescription());
        existing.setEquipmentType(updatedExercise.getEquipmentType());
        existing.setCompound(updatedExercise.isCompound());
        existing.setPrimary(updatedExercise.isPrimary());
        existing.setStrengthRatioReference(updatedExercise.getStrengthRatioReference());
        existing.setRatioMin(updatedExercise.getRatioMin());
        existing.setRatioMax(updatedExercise.getRatioMax());
        return exerciseRepository.save(existing);
    }

    // ✅ Delete exercise
    public void deleteExercise(Long id) {
        Exercise existing = getExerciseById(id);
        exerciseRepository.delete(existing);
    }
}