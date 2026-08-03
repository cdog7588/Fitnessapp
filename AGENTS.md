# FitnessApp Agent Manifest

## Mission
Build a backend that helps users plan workouts, track performance, and improve strength through data-driven recommendations.

## Product Direction
- Primary domain: strength training intelligence.
- User value: actionable recommendations from workout history.
- Delivery style: stable API first, then analytics and AI enhancements.

## Current Architecture
- Framework: Spring Boot 3 + Spring Data JPA + Spring Security (JWT).
- Package root: com.example.fitnessapp.
- Data layer: MySQL.

## Domain Modules
- Auth and user identity
- Exercises and muscle distributions
- Plans and workout days
- Workout sessions, exercises, and sets
- Strength timeline and summaries
- Stimulus analysis and recommendations

## Engineering Priorities
- Keep API contracts backward compatible where possible.
- Prefer explicit DTOs for request and response boundaries.
- Preserve data integrity in entity relationships.
- Favor deterministic calculations in services.
- Add tests for business logic before large refactors.

## Agent Operating Rules
- Do not change package root without explicit migration plan.
- Do not remove existing endpoints unless replacement is provided.
- For schema changes, include migration-safe SQL and entity updates.
- For security changes, keep auth endpoints public and protect private resources.
- For AI features, keep deterministic fallback behavior.

## Definition of Done
- Project compiles with Maven.
- New endpoint behavior is documented.
- Diagnostics report no compile errors.
- Any new config or manifest file is committed with examples.

## Integration Handoff
This backend is intended to run as a standalone service. External clients should use the public REST API directly and consult this AGENTS.md file for product context.
