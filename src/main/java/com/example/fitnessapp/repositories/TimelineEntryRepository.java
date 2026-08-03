package com.example.fitnessapp.repositories;

import com.example.fitnessapp.models.timeline.TimelineEntry;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TimelineEntryRepository extends JpaRepository<TimelineEntry, Long> {
}
