package com.example.fitnessapp.models.plans;

import jakarta.persistence.*;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name = "workout_day")
public class WorkoutDay {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer dayNumber;

    private String dayName;

    @ManyToMany
        @JoinTable(
            name = "workout_day_muscle_groups",
            joinColumns = @JoinColumn(name = "workout_day_id"),
            inverseJoinColumns = @JoinColumn(name = "muscle_group_id")
        )
        private List<MuscleGroup> muscleGroups;

        @ManyToOne
    @JoinColumn(name = "plan_id")
    @JsonBackReference
    private WorkoutPlan plan;


    public WorkoutDay() {}

    public Long getId() { return id; }
    public Integer getDayNumber() { return dayNumber; }
    public String getDayName() { return dayName; }
    public List<MuscleGroup> getMuscleGroups() { return muscleGroups; }

    public void setDayNumber(Integer dayNumber) { this.dayNumber = dayNumber; }
    public void setDayName(String dayName) { this.dayName = dayName; }
    public void setMuscleGroups(List<MuscleGroup> muscleGroups) { this.muscleGroups = muscleGroups; }
}
