package com.example.fitnessapp.models.exercises;

import com.example.fitnessapp.models.ExerciseMuscleDistribution;
import com.example.fitnessapp.models.workouts.WorkoutSet;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "exercise")
public class Exercise {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @OneToMany(mappedBy = "exercise")
    private List<ExerciseMuscleDistribution> muscleDistribution = new ArrayList<>();
   
    @OneToMany(mappedBy = "exercise")
    private List<WorkoutSet> sets = new ArrayList<>();


    private String equipmentType; // barbell, dumbbell, cable, machine
    private boolean isCompound;
    private boolean isPrimary;

    // ✅ Add these two fields to match your service layer
    private String description;
  

    // Strength ratio fields
    private String strengthRatioReference; // bench, squat, row, ohp
    private Double ratioMin;
    private Double ratioMax;

    public Exercise() {}

    public Exercise(String name, String equipmentType,
                    boolean isCompound, boolean isPrimary,
                    String strengthRatioReference, Double ratioMin, Double ratioMax) {
        this.name = name;
        this.equipmentType = equipmentType;
        this.isCompound = isCompound;
        this.isPrimary = isPrimary;
        this.strengthRatioReference = strengthRatioReference;
        this.ratioMin = ratioMin;
        this.ratioMax = ratioMax;
    }

    // ✅ Getters
    public Long getId() { return id; }
    public String getName() { return name; }
    public String getEquipmentType() { return equipmentType; }
    public boolean isCompound() { return isCompound; }
    public boolean isPrimary() { return isPrimary; }
    public String getStrengthRatioReference() { return strengthRatioReference; }
    public Double getRatioMin() { return ratioMin; }
    public Double getRatioMax() { return ratioMax; }
    public String getDescription() { return description; }
   

    // ✅ Setters
    public void setId(Long id) { this.id = id; }
    public void setName(String name) { this.name = name; }
    public void setEquipmentType(String equipmentType) { this.equipmentType = equipmentType; }
    public void setCompound(boolean compound) { isCompound = compound; }
    public void setPrimary(boolean primary) { isPrimary = primary; }
    public void setStrengthRatioReference(String strengthRatioReference) { this.strengthRatioReference = strengthRatioReference; }
    public void setRatioMin(Double ratioMin) { this.ratioMin = ratioMin; }
    public void setRatioMax(Double ratioMax) { this.ratioMax = ratioMax; }
    public void setDescription(String description) { this.description = description; }
    
}