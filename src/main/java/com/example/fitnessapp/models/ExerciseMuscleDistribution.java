
package com.example.fitnessapp.models;
import jakarta.persistence.Entity;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.JoinColumn;
import com.example.fitnessapp.models.exercises.Exercise;
import com.example.fitnessapp.models.plans.MuscleGroup;

@Entity
public class ExerciseMuscleDistribution {

    @EmbeddedId
    private ExerciseMuscleKey id;

    @ManyToOne
    @MapsId("exerciseId")
    @JoinColumn(name = "exercise_id")
    private Exercise exercise;

    @ManyToOne
    @MapsId("muscleGroupId")
    @JoinColumn(name = "muscle_group_id")
    private MuscleGroup muscleGroup;

    private int percentage;

    public ExerciseMuscleDistribution() {}

    public ExerciseMuscleDistribution(Exercise exercise, MuscleGroup muscleGroup, int percentage) {
        this.id = new ExerciseMuscleKey(exercise.getId(), muscleGroup.getId());
        this.exercise = exercise;
        this.muscleGroup = muscleGroup;
        this.percentage = percentage;
    }

    public ExerciseMuscleKey getId() { return id; }
    public void setId(ExerciseMuscleKey id) { this.id = id; }

    public Exercise getExercise() { return exercise; }
    public void setExercise(Exercise exercise) { this.exercise = exercise; }

    public MuscleGroup getMuscleGroup() { return muscleGroup; }
    public void setMuscleGroup(MuscleGroup muscleGroup) { this.muscleGroup = muscleGroup; }

    public int getPercentage() { return percentage; }
    public void setPercentage(int percentage) { this.percentage = percentage; }
}
