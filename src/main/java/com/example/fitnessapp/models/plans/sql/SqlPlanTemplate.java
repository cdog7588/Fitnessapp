package com.example.fitnessapp.models.plans.sql;

import jakarta.persistence.*;

@Entity
@Table(name = "workout_plans")
public class SqlPlanTemplate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String description;

    public SqlPlanTemplate() {}

    public Long getId() { return id; }
    public String getName() { return name; }
    public String getDescription() { return description; }

    public void setName(String name) { this.name = name; }
    public void setDescription(String description) { this.description = description; }
}
