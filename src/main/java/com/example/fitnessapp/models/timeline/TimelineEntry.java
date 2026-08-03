package com.example.fitnessapp.models.timeline;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import java.time.LocalDateTime;

@Entity
@Table(name = "timeline_entry")
public class TimelineEntry {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int userId;                // which user this entry belongs to
    private String type;               // PR, WORKOUT, PHOTO, AI_FEEDBACK
    private LocalDateTime timestamp;   // proper timestamp type
    private String data;               // JSON or text payload

    public TimelineEntry() {}

    public TimelineEntry(int userId, String type, LocalDateTime timestamp, String data) {
        this.userId = userId;
        this.type = type;
        this.timestamp = timestamp;
        this.data = data;
    }

    // Getters
    public Long getId() { return id; }
    public int getUserId() { return userId; }
    public String getType() { return type; }
    public LocalDateTime getTimestamp() { return timestamp; }
    public String getData() { return data; }

    // Setters
    public void setUserId(int userId) { this.userId = userId; }
    public void setType(String type) { this.type = type; }
    public void setTimestamp(LocalDateTime timestamp) { this.timestamp = timestamp; }
    public void setData(String data) { this.data = data; }
}
