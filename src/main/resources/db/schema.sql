SET FOREIGN_KEY_CHECKS = 0;

DROP TABLE IF EXISTS workout_set;
DROP TABLE IF EXISTS workout_exercise;
DROP TABLE IF EXISTS workout_session;
DROP TABLE IF EXISTS workout_day_muscle_groups;
DROP TABLE IF EXISTS workout_day;
DROP TABLE IF EXISTS user_exercise;
DROP TABLE IF EXISTS exercise_performance;
DROP TABLE IF EXISTS timeline_entry;
DROP TABLE IF EXISTS exercise_muscle_distribution;
DROP TABLE IF EXISTS exercise;
DROP TABLE IF EXISTS muscle_group;
DROP TABLE IF EXISTS workout_plan;
DROP TABLE IF EXISTS app_user;

SET FOREIGN_KEY_CHECKS = 1;

-- ============================
-- USERS
-- ============================

CREATE TABLE IF NOT EXISTS app_user (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    username VARCHAR(255) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL
);

-- ============================
-- MUSCLE GROUP HIERARCHY
-- ============================

CREATE TABLE IF NOT EXISTS muscle_group (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(255) NOT NULL,
    parent_id BIGINT NULL,
    FOREIGN KEY (parent_id) REFERENCES muscle_group(id)
);

-- ============================
-- EXERCISES
-- ============================

CREATE TABLE IF NOT EXISTS exercise (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(255) NOT NULL,
    description TEXT,
    equipment_type VARCHAR(255),
    is_compound BOOLEAN,
    is_primary BOOLEAN,
    strength_ratio_reference VARCHAR(255),
    ratio_min DOUBLE,
    ratio_max DOUBLE
);

-- ============================
-- WORKOUT PLANS (PPL, Bro Split, etc.)
-- ============================

CREATE TABLE IF NOT EXISTS workout_plan (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(255) NOT NULL,
    description TEXT
);

-- ============================
-- WORKOUT DAYS (each plan has multiple days)
-- ============================

CREATE TABLE IF NOT EXISTS workout_day (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    plan_id BIGINT NOT NULL,
    day_number INT NOT NULL,
    day_name VARCHAR(255),
    FOREIGN KEY (plan_id) REFERENCES workout_plan(id)
);

-- ============================
-- WORKOUT DAY ↔ MUSCLE GROUP (many-to-many)
-- ============================

CREATE TABLE IF NOT EXISTS workout_day_muscle_groups (
    workout_day_id BIGINT NOT NULL,
    muscle_group_id BIGINT NOT NULL,
    PRIMARY KEY (workout_day_id, muscle_group_id),
    FOREIGN KEY (workout_day_id) REFERENCES workout_day(id),
    FOREIGN KEY (muscle_group_id) REFERENCES muscle_group(id)
);

-- ============================
-- WORKOUT SESSIONS (user logs)
-- ============================

CREATE TABLE IF NOT EXISTS workout_session (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    user_id BIGINT NULL,
    name VARCHAR(255) NULL,
    date DATE NOT NULL,
    notes TEXT,
    workout_day_id BIGINT NULL,
    FOREIGN KEY (user_id) REFERENCES app_user(id),
    FOREIGN KEY (workout_day_id) REFERENCES workout_day(id)
);

-- ============================
-- WORKOUT EXERCISES (generated or logged)
-- ============================

CREATE TABLE IF NOT EXISTS workout_exercise (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    session_id BIGINT NULL,
    exercise_id BIGINT NOT NULL,
    order_index INT,
    FOREIGN KEY (session_id) REFERENCES workout_session(id),
    FOREIGN KEY (exercise_id) REFERENCES exercise(id)
);

-- ============================
-- WORKOUT SETS (sets for each exercise)
-- ============================

CREATE TABLE IF NOT EXISTS workout_set (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    workout_exercise_id BIGINT NULL,
    workout_session_id BIGINT NULL,
    exercise_id BIGINT NULL,
    set_number INT,
    target_percent_of_1rm DOUBLE,
    target_weight DOUBLE,
    target_reps INT,
    actual_weight DOUBLE,
    actual_reps INT,
    estimated_1rm_after_set DOUBLE,
    FOREIGN KEY (workout_exercise_id) REFERENCES workout_exercise(id),
    FOREIGN KEY (workout_session_id) REFERENCES workout_session(id),
    FOREIGN KEY (exercise_id) REFERENCES exercise(id)
);

-- ============================
-- USER EXERCISE METRICS
-- ============================

CREATE TABLE IF NOT EXISTS user_exercise (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    user_id BIGINT NOT NULL,
    exercise_id BIGINT NOT NULL,
    notes TEXT,
    estimated1rm DOUBLE,
    best_weight DOUBLE,
    best_reps INT,
    last_pr_date DATE,
    FOREIGN KEY (user_id) REFERENCES app_user(id),
    FOREIGN KEY (exercise_id) REFERENCES exercise(id)
);

-- ============================
-- PERFORMANCE HISTORY
-- ============================

CREATE TABLE IF NOT EXISTS exercise_performance (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    exercise_id BIGINT NOT NULL,
    date DATE,
    weight DOUBLE,
    reps INT,
    estimated1rm DOUBLE,
    FOREIGN KEY (exercise_id) REFERENCES exercise(id)
);

-- ============================
-- TIMELINE (PRs, milestones, etc.)
-- ============================

CREATE TABLE IF NOT EXISTS timeline_entry (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    user_id BIGINT NOT NULL,
    type VARCHAR(255) NOT NULL,
    timestamp DATETIME NOT NULL,
    data TEXT,
    FOREIGN KEY (user_id) REFERENCES app_user(id)
);

-- ============================
-- Muscle percentage table
-- ============================

CREATE TABLE IF NOT EXISTS exercise_muscle_distribution (
    exercise_id BIGINT NOT NULL,
    muscle_group_id BIGINT NOT NULL,
    percentage INT NOT NULL,

    PRIMARY KEY (exercise_id, muscle_group_id),

    CONSTRAINT fk_exercise
        FOREIGN KEY (exercise_id)
        REFERENCES exercise(id)
        ON DELETE CASCADE,

    CONSTRAINT fk_muscle_group
        FOREIGN KEY (muscle_group_id)
        REFERENCES muscle_group(id)
        ON DELETE CASCADE
);
