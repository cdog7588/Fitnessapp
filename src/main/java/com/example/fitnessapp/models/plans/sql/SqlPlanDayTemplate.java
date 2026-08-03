package com.example.fitnessapp.models.plans.sql;

import jakarta.persistence.*;

@Entity
@Table(name = "workout_plan_days")
public class SqlPlanDayTemplate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "plan_id")
    private Long planId;

    @Column(name = "day_number")
    private int dayNumber;

    @Column(name = "top_level_group")
    private String topLevelGroup;

    public SqlPlanDayTemplate() {}

    public Long getId() { return id; }
    public Long getPlanId() { return planId; }
    public int getDayNumber() { return dayNumber; }
    public String getTopLevelGroup() { return topLevelGroup; }

    public void setPlanId(Long planId) { this.planId = planId; }
    public void setDayNumber(int dayNumber) { this.dayNumber = dayNumber; }
    public void setTopLevelGroup(String topLevelGroup) { this.topLevelGroup = topLevelGroup; }
}
