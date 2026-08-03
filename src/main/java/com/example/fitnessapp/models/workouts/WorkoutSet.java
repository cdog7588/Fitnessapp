package com.example.fitnessapp.models.workouts;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.JoinColumn;
import com.example.fitnessapp.models.exercises.Exercise;

@Entity
@Table(name = "workout_set")
public class WorkoutSet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "exercise_id")
    private Exercise exercise;

    @ManyToOne
    @JoinColumn(name = "workout_session_id")
    private WorkoutSession workoutSession;
    
    @ManyToOne
@JoinColumn(name = "workout_exercise_id")
private WorkoutExercise workoutExercise;




    private Integer setNumber;

    private Double targetPercentOf1RM;
    private Double targetWeight;
    private Integer targetReps;

    private Double actualWeight;
    private Integer actualReps;

    private Double estimated1RMAfterSet;

    public WorkoutSet() {}

    public WorkoutSet(Exercise exercise, Integer setNumber,
                      Double targetPercentOf1RM, Double targetWeight, Integer targetReps,
                      Double actualWeight, Integer actualReps, Double estimated1RMAfterSet) {
        this.exercise = exercise;
        this.setNumber = setNumber;
        this.targetPercentOf1RM = targetPercentOf1RM;
        this.targetWeight = targetWeight;
        this.targetReps = targetReps;
        this.actualWeight = actualWeight;
        this.actualReps = actualReps;
        this.estimated1RMAfterSet = estimated1RMAfterSet;
    }

 // 🆔 Getter and Setter for ID
public Long getId() { return id; }
public void setId(Long id) { this.id = id; }

// 💪 Getter and Setter for Exercise
public Exercise getExercise() { return exercise; }
public void setExercise(Exercise exercise) { this.exercise = exercise; }

// 🏋️ Getter and Setter for WorkoutSession
public WorkoutSession getWorkoutSession() { return workoutSession; }
public void setWorkoutSession(WorkoutSession workoutSession) { this.workoutSession = workoutSession; }

// 📋 Getter and Setter for WorkoutExercise (planned exercise)
public WorkoutExercise getWorkoutExercise() { return workoutExercise; }
public void setWorkoutExercise(WorkoutExercise workoutExercise) { this.workoutExercise = workoutExercise; }

// 🔢 Getter and Setter for Set Number
public Integer getSetNumber() { return setNumber; }
public void setSetNumber(Integer setNumber) { this.setNumber = setNumber; }

// 🎯 Getter and Setter for Target Percent of 1RM
public Double getTargetPercentOf1Rm() { return targetPercentOf1RM; }
public void setTargetPercentOf1Rm(Double targetPercentOf1Rm) { this.targetPercentOf1RM = targetPercentOf1Rm; }

// ⚖️ Getter and Setter for Target Weight
public Double getTargetWeight() { return targetWeight; }
public void setTargetWeight(Double targetWeight) { this.targetWeight = targetWeight; }

// 🔁 Getter and Setter for Target Reps
public Integer getTargetReps() { return targetReps; }
public void setTargetReps(Integer targetReps) { this.targetReps = targetReps; }

// 🧮 Getter and Setter for Actual Weight
public Double getActualWeight() { return actualWeight; }
public void setActualWeight(Double actualWeight) { this.actualWeight = actualWeight; }

// 🔂 Getter and Setter for Actual Reps
public Integer getActualReps() { return actualReps; }
public void setActualReps(Integer actualReps) { this.actualReps = actualReps; }

// 📈 Getter and Setter for Estimated 1RM After Set
public Double getEstimated1RmAfterSet() { return estimated1RMAfterSet; }
public void setEstimated1RmAfterSet(Double estimated1RmAfterSet) { this.estimated1RMAfterSet = estimated1RmAfterSet; }

    public void setEstimated1RMAfterSet(Double estimated1RMAfterSet) { this.estimated1RMAfterSet = estimated1RMAfterSet; }
}
