package com.example.fitnessapp.models.workouts;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.OneToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.JoinColumn;

import com.example.fitnessapp.models.AppUser;
import jakarta.persistence.CascadeType;



@Entity
@Table(name = "workout_session")
public class WorkoutSession {

    private String name;

    @OneToMany(mappedBy = "workoutSession", cascade = CascadeType.ALL)
    private List<WorkoutSet> workoutSets = new ArrayList<>();

    @OneToMany(mappedBy = "session", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<WorkoutExercise> workoutExercises = new ArrayList<>();


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate date;
    private String notes;


    @ManyToOne
    @JoinColumn(name = "user_id")
    private AppUser user;

    public WorkoutSession() {}

    public WorkoutSession(LocalDate date, String notes) {
        this.date = date;
        this.notes = notes;
    }

    // Getters and setters
    public Long getId() { return id; }
    public LocalDate getDate() { return date; }
    public String getNotes() { return notes; }
    public AppUser getUser() { return user; }
    public String getName() { return name; }
    public List<WorkoutSet> getWorkoutSets() { return workoutSets; }
    public List<WorkoutExercise> getWorkoutExercises() { return workoutExercises;}


    public void setId(Long id) { this.id = id; }
    public void setDate(LocalDate date) { this.date = date; }
    public void setNotes(String notes) { this.notes = notes; }
    public void setUser(AppUser user) { this.user = user; }
    public void setName(String name) { this.name = name; }
    public void setWorkoutSets(List<WorkoutSet> workoutSets) { this.workoutSets = workoutSets; }
    public void setWorkoutExercises(List<WorkoutExercise> workoutExercises) {this.workoutExercises = workoutExercises;}


}

