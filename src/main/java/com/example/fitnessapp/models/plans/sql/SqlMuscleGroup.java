package com.example.fitnessapp.models.plans.sql;

import jakarta.persistence.*;

@Entity
@Table(name = "muscle_groups")
public class SqlMuscleGroup {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Column(name = "parent_id")
    private Long parentId;

    public SqlMuscleGroup() {}

    public Long getId() { return id; }
    public String getName() { return name; }
    public Long getParentId() { return parentId; }

    public void setName(String name) { this.name = name; }
    public void setParentId(Long parentId) { this.parentId = parentId; }
}
