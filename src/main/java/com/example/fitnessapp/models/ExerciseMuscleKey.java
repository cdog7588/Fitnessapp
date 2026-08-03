package com.example.fitnessapp.models;
import jakarta.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class ExerciseMuscleKey implements Serializable {

    private Long exerciseId;
    private Long muscleGroupId;

    public ExerciseMuscleKey() {}

    public ExerciseMuscleKey(Long exerciseId, Long muscleGroupId) {
        this.exerciseId = exerciseId;
        this.muscleGroupId = muscleGroupId;
    }

    public Long getExerciseId() { return exerciseId; }
    public void setExerciseId(Long exerciseId) { this.exerciseId = exerciseId; }

    public Long getMuscleGroupId() { return muscleGroupId; }
    public void setMuscleGroupId(Long muscleGroupId) { this.muscleGroupId = muscleGroupId; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ExerciseMuscleKey)) return false;
        ExerciseMuscleKey that = (ExerciseMuscleKey) o;
        return Objects.equals(exerciseId, that.exerciseId) &&
               Objects.equals(muscleGroupId, that.muscleGroupId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(exerciseId, muscleGroupId);
    }
}