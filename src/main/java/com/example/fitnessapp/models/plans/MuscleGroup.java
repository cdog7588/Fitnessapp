package com.example.fitnessapp.models.plans;

import jakarta.persistence.*;

import com.example.fitnessapp.models.ExerciseMuscleDistribution;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "muscle_group")
public class MuscleGroup {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    //emg muscle activation data
    @OneToMany(mappedBy = "muscleGroup")
private List<ExerciseMuscleDistribution> exercises = new ArrayList<>();


    // Parent muscle group (e.g., Push → Chest)
    @ManyToOne
    @JoinColumn(name = "parent_id")
    @JsonBackReference   // prevents infinite recursion
    private MuscleGroup parent;

    // Children muscle groups (e.g., Chest → Upper Chest, Lower Chest)
    @OneToMany(mappedBy = "parent")
    @JsonManagedReference
    private List<MuscleGroup> children = new ArrayList<>();

    public MuscleGroup() {}

    public Long getId() { return id; }
    public String getName() { return name; }
    public MuscleGroup getParent() { return parent; }
    public List<MuscleGroup> getChildren() { return children; }

    public void setName(String name) { this.name = name; }
    public void setParent(MuscleGroup parent) { this.parent = parent; }
    public void setChildren(List<MuscleGroup> children) { this.children = children; }
}
