package com.example.fitnessapp.repositories;

import com.example.fitnessapp.models.plans.MuscleGroup;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MuscleGroupRepository extends JpaRepository<MuscleGroup, Long> {
}
