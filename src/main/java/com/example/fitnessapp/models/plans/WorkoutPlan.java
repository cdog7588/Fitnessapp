package com.example.fitnessapp.models.plans;

import jakarta.persistence.*;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "workout_plan")
public class WorkoutPlan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String description;

    @OneToMany(mappedBy = "plan", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<WorkoutDay> days;

    public WorkoutPlan() {}

    public Long getId() { return id; }
    public String getName() { return name; }
    public String getDescription() { return description; }
    public List<WorkoutDay> getDays() { return days; }

    public void setName(String name) { this.name = name; }
    public void setDescription(String description) { this.description = description; }
    public void setDays(List<WorkoutDay> days) { this.days = days; }
}
