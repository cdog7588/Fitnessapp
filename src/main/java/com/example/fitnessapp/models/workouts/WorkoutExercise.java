package com.example.fitnessapp.models.workouts;

import java.util.List;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import java.util.ArrayList;
@Entity
@Table(name = "workout_exercise")
public class WorkoutExercise {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "session_id")
    private WorkoutSession session;

    @ManyToOne
    @JoinColumn(name = "exercise_id")
    private com.example.fitnessapp.models.exercises.Exercise exercise;

    private Integer orderIndex;

    @OneToMany(mappedBy = "workoutExercise")
    private List<WorkoutSet> sets = new ArrayList<>();

    public WorkoutExercise() {}

    public WorkoutExercise(WorkoutSession session, com.example.fitnessapp.models.exercises.Exercise exercise, Integer orderIndex) {
        this.session = session;
        this.exercise = exercise;
        this.orderIndex = orderIndex;
    }

    // Getters
    public Long getId() { return id; }
    public WorkoutSession getSession() { return session; }
    public com.example.fitnessapp.models.exercises.Exercise getExercise() { return exercise; }
    public Integer getOrderIndex() { return orderIndex; }
    public List<WorkoutSet> getSets() { return sets; }

    // Setters
    public void setSession(WorkoutSession session) { this.session = session; }
    public void setExercise(com.example.fitnessapp.models.exercises.Exercise exercise) { this.exercise = exercise; }
    public void setOrderIndex(Integer orderIndex) { this.orderIndex = orderIndex; }
    public void setSets(List<WorkoutSet> sets) { this.sets = sets; }
}
