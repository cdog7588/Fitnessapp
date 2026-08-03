# FitnessApp

FitnessApp is a strength training intelligence platform with a Spring Boot backend and a React frontend. It helps users plan workouts, log performance, and track strength progression using deterministic calculations.

## Stack

- Backend: Spring Boot 3, Spring Data JPA, Spring Security (JWT)
- Database: MySQL
- Frontend: React + TypeScript + Vite
- Build: Maven Wrapper (`mvnw` / `mvnw.cmd`)

## Repository Structure

- `src/main/java/com/example/fitnessapp`: backend code
- `src/main/resources`: configuration, schema, seed data
- `src/test/java/com/example/fitnessapp`: unit tests
- `frontend`: React client
- `scripts`: local launcher scripts for backend/frontend and desktop app mode
- `installer`: Inno Setup files and packaging helpers

## Prerequisites

- Java 17+
- Node.js 18+
- MySQL 8+

## Local Setup

1. Set environment variables for the backend (do not commit these values):
   - `DB_PASSWORD`: password for the MySQL account.
   - `DB_USERNAME` (optional): defaults to `root`.
   - `JWT_SECRET`: a private signing secret with at least 32 characters.
2. Start MySQL and ensure the configured database exists.
3. Run backend tests:
   - Windows: `./mvnw.cmd test`
   - Unix/macOS: `./mvnw test`
4. Run backend API:
   - Windows: `./mvnw.cmd spring-boot:run`
   - Unix/macOS: `./mvnw spring-boot:run`
5. Run frontend:
   - `cd frontend`
   - `npm install`
   - `npm run dev`

Frontend runs on `http://localhost:3000` and proxies API requests to backend port `8100`.

## Desktop Launch

For a desktop-style launch flow on Windows, use:

- `scripts/FitnessApp.cmd`

This starts backend + frontend, waits for readiness, and opens an app-style browser window.

## Download and Install (Windows)

Use the installer executable in this repository:

- `installer/FitnessAppSetup.exe`

Install steps:

1. Run `installer/FitnessAppSetup.exe` as Administrator.
2. Complete the installer wizard.
3. Launch **FitnessApp** from the Start Menu or desktop shortcut.

What the installer includes:

- App launchers and helper scripts from `scripts/`
- A desktop shortcut for **FitnessApp**
- A Start Menu shortcut for **FitnessApp Logs**

## First-Run Behavior

On first launch, FitnessApp:

1. Starts backend and frontend services in the background.
2. Waits for backend (`8100`) and frontend (`3000`) readiness.
3. Opens the app window at `http://localhost:3000`.

Logs are written to:

- `%LOCALAPPDATA%\\FitnessApp\\logs`

Useful log files:

- `backend.log`
- `frontend.log`
- `launcher.log`

## Core API Areas

- Auth: `/auth`
- Exercises: `/exercises`
- Plans and generation: `/plans`, `/generator`
- Workout logging: `/api/workout`, `/api/workout-exercises`, `/api/workout-sets`
- Analytics: `/api/recommendations`, `/api/stimulus`, `/api/strength-timeline`, `/api/workout-summary`, `/set`

## Notes

- Keep `target/`, `frontend/node_modules/`, and `frontend/dist/` out of source control.
- Prefer deterministic service logic for recommendations.
- Add tests when changing business logic.
