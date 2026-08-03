package com.example.fitnessapp.models.plans.sql;

import jakarta.persistence.*;

@Entity
@Table(name = "exercises")
public class SqlExerciseDefinition {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Column(name = "muscle_group_id")
    private Long muscleGroupId;

    @Column(name = "is_compound")
    private boolean isCompound;

    @Column(name = "is_primary")
    private boolean isPrimary;

    @Column(name = "ratio_min")
    private Double ratioMin;

    @Column(name = "ratio_max")
    private Double ratioMax;

    public SqlExerciseDefinition() {}

    public Long getId() { return id; }
    public String getName() { return name; }
    public Long getMuscleGroupId() { return muscleGroupId; }
    public boolean isCompound() { return isCompound; }
    public boolean isPrimary() { return isPrimary; }
    public Double getRatioMin() { return ratioMin; }
    public Double getRatioMax() { return ratioMax; }

    public void setName(String name) { this.name = name; }
    public void setMuscleGroupId(Long muscleGroupId) { this.muscleGroupId = muscleGroupId; }
    public void setCompound(boolean compound) { isCompound = compound; }
    public void setPrimary(boolean primary) { isPrimary = primary; }
    public void setRatioMin(Double ratioMin) { this.ratioMin = ratioMin; }
    public void setRatioMax(Double ratioMax) { this.ratioMax = ratioMax; }
}
