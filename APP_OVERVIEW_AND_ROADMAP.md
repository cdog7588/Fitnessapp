# 🏋️ FitnessApp - Project Overview & Frontend-Focused 7-Day Roadmap

Welcome back to your **FitnessApp** project! The backend is now focused on running as a standalone service with stable REST APIs and verified business logic.

---

## 📐 1. System Overview & Architecture

**FitnessApp** is a strength training intelligence backend built with **Spring Boot 3**, **Spring Data JPA**, **MySQL**, and **JWT Security**.

### Key Mission & Value Proposition
- **Data-Driven Workouts**: Plan workouts based on target muscle groups (PPL, Bro Split, Upper/Lower, etc.).
- **Strength Analytics**: Track 1-Rep Max (1RM) progression across exercises over time using multiple industry-standard formulas (Epley, Brzycki, Lombardi, Lander).
- **Intelligent Recommendations**: Calculate recommended weights based on previous performance, fatigue indicators, and plateau detection.
- **Stimulus & EMG Analysis**: Measure muscle volume and stimulus distribution across primary and secondary muscle groups.

---

## 🗂️ 2. Package & Domain Structure

```
com.example.fitnessapp
├── config                 # Security & Spring Config (JWT, PasswordEncoder)
├── controllers            # REST Endpoints (Auth, Exercises, Plans, Workouts, Recommendations, Stimulus)
├── dto                    # Request & Response Data Transfer Objects
├── exceptions             # Global Exception Handler
├── models                 # JPA Entities
│   ├── exercises          # Exercise entity definitions
│   ├── plans              # WorkoutPlan, WorkoutDay, MuscleGroup
│   ├── workouts           # WorkoutSession, WorkoutExercise, WorkoutSet
│   ├── timeline           # TimelineEntry entity
│   └── AppUser.java       # User entity & authentication details
├── repositories           # Spring Data JPA Repository Interfaces
├── security               # JWT Service, UserDetailsService, Auth Filters
├── services               # Core Business Logic & Algorithms
└── utils                  # Deterministic Calculators (1RM, Strength Ratios, Weight/Rep Adjusters)
```

---

## 🔌 3. API Catalog

### Authentication & Users (`/auth`)
- `POST /auth/register` - Create new user account (`{ "username": "...", "password": "..." }`).
- `POST /auth/login` - Authenticate user & receive JWT token (`{ "username": "...", "password": "..." }`).
- `POST /auth/refresh` - Refresh active JWT token (`Header: Authorization: Bearer <token>`).
- `POST /auth/logout` - Client token invalidation response.

### Exercises & Muscle Groups (`/exercises`, `/api/exercise-muscle-distributions`)
- `GET /exercises` - List all exercises.
- `GET /exercises/{id}` - Fetch details for a specific exercise.
- `POST /exercises`, `PUT /exercises/{id}`, `DELETE /exercises/{id}` - Manage exercise definitions.
- `POST /api/exercise-muscle-distributions` - Map exercise activation percentages to target muscle groups.

### Workout Plans & Generation (`/plans`, `/generator`)
- `GET /plans` - View available workout plans.
- `GET /plans/{id}` - View plan days & targeted muscle groups.
- `GET /generator/day/{dayId}` - Auto-generate recommended exercises and set allocations for a workout day.

### Workout Session Logging (`/api/workout`, `/api/workout-exercises`, `/api/workout-sets`)
- `GET /api/workout/session/{sessionId}` - Retrieve full workout hierarchy (Session → Exercises → Sets).
- `GET /api/workout/history` - User's complete workout history.
- `POST /api/workout-sets` - Log actual set metrics (`actualWeight`, `actualReps`, `targetWeight`, `targetReps`).

### Intelligence & Analytics (`/api/recommendations`, `/api/stimulus`, `/api/strength-timeline`, `/api/workout-summary`, `/set`)
- `GET /api/recommendations/exercise/{exerciseId}` - Calculate progressive overload recommendations.
- `GET /api/stimulus/{userId}` - Calculate accumulated muscle group stimulus.
- `GET /api/strength-timeline/exercise/{exerciseId}` - 1RM progression timeline data over time.
- `GET /api/workout-summary/session/{sessionId}` - Aggregate session summary (total volume, best set, 1RM).
- `GET /set/predict` - Real-time PR predictor for a given set weight & reps.

---

## 🛠️ 4. Backend Completed Tasks

2. **Schema & Database Script Alignment**:
   - Fixed `user_id` types to `BIGINT` across `workout_session`, `user_exercise`, and `timeline_entry` in `schema.sql`. Added `exercise_id` and `workout_session_id` to `workout_set`. Cleaned duplicate `ddl-auto` settings in `application.properties`.
3. **Null Safety & Memory Optimization**:
   - Added derived JPA queries (`findBySessionId`, `findByExerciseId`, `findByWorkoutExerciseId`) to replace unsafe in-memory full-table streaming.
   - Enforced null-checks across `WorkoutService`, `WorkoutSummaryService`, `RecommendedWeightService`, `StrengthTimelineService`, `WorkoutGeneratorService`, `MuscleGroup`, and `AuthController`.
4. **Automated Unit Testing**:
   - Built and verified unit test suite (`OneRepMaxCalculatorTest`, `StimulusCalculatorTest`, `StrengthRatioCalculatorTest`, `RecommendedWeightServiceTest`). 100% tests passing (`./mvnw test`).

---

## 📅 5. 7-Day Frontend Focus Roadmap

Since backend development is **100% complete and verified today**, days 2 through 7 are completely dedicated to building, connecting, and launching your frontend application!

```
 🗓️ DAY 1: Backend Completion & Verification (COMPLETED TODAY)
 [x] Audit codebase, fix syntax/schema/null bugs.
 [x] Build unit test suite & verify build via `./mvnw test` (13/13 tests passed).

 🗓️ DAY 2: Frontend Setup & Auth Flow (Login & Register)
 [ ] Initialize frontend project (React, Vite, Next.js, or mobile app).
 [ ] Build Authentication UI (Login / Registration screens).
 [ ] Wire frontend API client to store JWT token in localStorage/secure storage and set `Authorization: Bearer <token>` header.

 🗓️ DAY 3: Exercise Library & Workout Plan Views
 [ ] Build Exercise Library screen calling `GET /exercises`.
 [ ] Build Workout Plan selector calling `GET /plans` and `GET /plans/{id}`.

 🗓️ DAY 4: Interactive Workout Generator & Live Session Logging
 [ ] Build "Generate Workout" screen calling `GET /generator/day/{dayId}` to display recommended exercises and sets.
 [ ] Build Active Workout Logger interface to log completed sets via `POST /api/workout-sets`.

 🗓️ DAY 5: Strength Analytics & Progression Dashboard
 [ ] Build Workout Summary view calling `GET /api/workout-summary/session/{sessionId}` displaying total volume & 1RM.
 [ ] Display 1RM progression charts calling `GET /api/strength-timeline/exercise/{exerciseId}`.
 [ ] Integrate weight recommendation pills/cards calling `GET /api/recommendations/exercise/{exerciseId}`.

 🗓️ DAY 6: UI Polish, Dark Mode & Micro-Animations
 [ ] Apply cohesive visual design system (typography, dark mode, smooth transitions, responsive layout).
 [ ] Verify error boundary states and loading spinners for API calls.

 🗓️ DAY 7: End-to-End Testing & Production Launch!
 [ ] Perform full end-to-end user flow: Register user → View Plans → Generate Workout → Log Sets → View Analytics.
 [ ] Final deployment check & tag release `v1.0.0`!
```

---

## 💡 Quick Commands Reference

- **Run Backend Unit Tests**: `./mvnw test`
- **Start Backend Application**: `./mvnw spring-boot:run`
- **Build Production Jar**: `./mvnw clean package`
