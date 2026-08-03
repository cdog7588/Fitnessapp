SET FOREIGN_KEY_CHECKS = 0;

DELETE FROM exercise_muscle_distribution;
DELETE FROM workout_set;
DELETE FROM workout_exercise;
DELETE FROM workout_session;
DELETE FROM workout_day_muscle_groups;
DELETE FROM workout_day;
DELETE FROM workout_plan;
DELETE FROM exercise;
DELETE FROM muscle_group;

SET FOREIGN_KEY_CHECKS = 1;


-- ============================
-- MUSCLE GROUP HIERARCHY
-- ============================

-- Top-level split groups
INSERT IGNORE INTO muscle_group (id, name, parent_id) VALUES
(1, 'Push', NULL),
(2, 'Pull', NULL),
(3, 'Legs', NULL),
(4, 'Upper', NULL),
(5, 'Lower', NULL),
(6, 'Arms', NULL);

-- Major body parts
INSERT IGNORE INTO muscle_group (id, name, parent_id) VALUES
(10, 'Chest', 1),
(11, 'Shoulders', 1),
(12, 'Triceps', 1),

(13, 'Back', 2),
(14, 'Biceps', 2),

(15, 'Glutes', 3),
(16, 'Hamstrings', 3),
(17, 'Calves', 3),
(18, 'Quads', 3),

(19, 'Forearms', 6);

-- Sub-muscles
INSERT IGNORE INTO muscle_group (id, name, parent_id) VALUES
(30, 'Upper Chest', 10),
(31, 'Mid Chest', 10),
(32, 'Lower Chest', 10),

(40, 'Front Delts', 11),
(41, 'Side Delts', 11),
(42, 'Rear Delts', 11),

(50, 'Long Head Triceps', 12),
(51, 'Lateral Head Triceps', 12),
(52, 'Medial Head Triceps', 12),

(60, 'Long Head Biceps', 14),
(61, 'Short Head Biceps', 14),

(70, 'Lats', 13),
(71, 'Upper Back', 13),
(72, 'Lower Back', 13),
(73, 'Traps', 13),

(80, 'Glute Max', 15),
(81, 'Glute Med', 15),
(82, 'Glute Min', 15),

(90, 'Biceps Femoris', 16),
(91, 'Semitendinosus', 16),
(92, 'Semimembranosus', 16),

(100, 'Gastrocnemius', 17),
(101, 'Soleus', 17),

(110, 'Vastus Lateralis', 18),
(111, 'Vastus Medialis', 18),
(112, 'Vastus Intermedius', 18),
(113, 'Rectus Femoris', 18),

(120, 'Brachioradialis', 19),
(121, 'Wrist Flexors', 19),
(122, 'Wrist Extensors', 19);

-- ============================
-- EXERCISES (mapped to new hierarchy)
-- ============================

INSERT IGNORE INTO exercise (id, name, equipment_type, is_compound, is_primary, strength_ratio_reference, ratio_min, ratio_max) VALUES
(1, 'Bench Press', 'barbell', TRUE, TRUE, 'bench', 0.8, 1.2),
(2, 'Incline Dumbbell Press', 'dumbbell', TRUE, FALSE, 'bench', 0.6, 0.9),
(3, 'Overhead Press', 'barbell', TRUE, TRUE, 'ohp', 0.5, 0.8),
(4, 'Lateral Raise', 'dumbbell', FALSE, FALSE, NULL, NULL, NULL),

(5, 'Lat Pulldown', 'cable', TRUE, TRUE, 'row', 0.6, 1.0),
(6, 'Barbell Row', 'barbell', TRUE, TRUE, 'row', 0.7, 1.1),
(7, 'Seated Cable Row', 'cable', TRUE, FALSE, 'row', 0.5, 0.9),
(8, 'Face Pull', 'cable', FALSE, FALSE, NULL, NULL, NULL),

(9, 'Back Squat', 'barbell', TRUE, TRUE, 'squat', 1.0, 1.6),
(10, 'Leg Press', 'machine', TRUE, FALSE, 'squat', 1.2, 2.0),
(11, 'Romanian Deadlift', 'barbell', TRUE, TRUE, 'deadlift', 0.8, 1.3),
(12, 'Leg Extension', 'machine', FALSE, FALSE, NULL, NULL, NULL);

-- ============================
-- WORKOUT PLAN (PPL example)
-- ============================

INSERT INTO workout_plan (id, name, description) VALUES
(1, 'Push Pull Legs', 'Classic PPL split');

-- ============================
-- WORKOUT DAYS
-- ============================

INSERT INTO workout_day (id, plan_id, day_number, day_name) VALUES
(1, 1, 1, 'Push Day'),
(2, 1, 2, 'Pull Day'),
(3, 1, 3, 'Leg Day');

-- ============================
-- WORKOUT DAY → MUSCLE GROUP MAPPINGS
-- ============================

-- Push Day
INSERT INTO workout_day_muscle_groups (workout_day_id, muscle_group_id) VALUES
(1, 1), -- Push
(1, 10),  -- Chest
(1, 11),  -- Shoulders
(1, 12);  -- Triceps

-- Pull Day
INSERT INTO workout_day_muscle_groups (workout_day_id, muscle_group_id) VALUES
(2, 2), -- Pull
(2, 13),  -- Back
(2, 14);  -- Biceps

-- Leg Day
INSERT INTO workout_day_muscle_groups (workout_day_id, muscle_group_id) VALUES
(3, 3), -- Legs
(3, 18),  -- Quads
(3, 16),  -- Hamstrings
(3, 15), -- GLutes
(3, 17);  -- Calves

-- ============================
-- BRO SPLIT
-- ============================

INSERT INTO workout_plan (id, name, description) VALUES
(2, 'Bro Split', 'Chest, Back, Shoulders, Arms, Legs');

-- Days
INSERT INTO workout_day (id, plan_id, day_number, day_name) VALUES
(10, 2, 1, 'Chest Day'),
(11, 2, 2, 'Back Day'),
(12, 2, 3, 'Shoulder Day'),
(13, 2, 4, 'Arms Day'),
(14, 2, 5, 'Leg Day');

-- Chest Day
INSERT INTO workout_day_muscle_groups VALUES
(10, 10);  -- Chest

-- Back Day
INSERT INTO workout_day_muscle_groups VALUES
(11, 13);  -- Back

-- Shoulder Day
INSERT INTO workout_day_muscle_groups VALUES
(12, 11);  -- Shoulders

-- Arms Day
INSERT INTO workout_day_muscle_groups VALUES
(13, 12),  -- Triceps
(13, 14);  -- Biceps

-- Leg Day
INSERT INTO workout_day_muscle_groups VALUES
(14, 18),  -- Quads
(14, 16),  -- Hamstrings
(14, 17);  -- Calves

-- ============================
-- UPPER / LOWER SPLIT
-- ============================

INSERT INTO workout_plan (id, name, description) VALUES
(3, 'Upper Lower', 'Upper body and lower body split');

-- Days
INSERT INTO workout_day (id, plan_id, day_number, day_name) VALUES
(20, 3, 1, 'Upper Day 1'),
(21, 3, 2, 'Lower Day 1'),
(22, 3, 3, 'Upper Day 2'),
(23, 3, 4, 'Lower Day 2');

-- Upper Days
INSERT INTO workout_day_muscle_groups VALUES
(20, 10),  -- Chest
(20, 11),  -- Shoulders
(20, 12),  -- Triceps
(20, 13),  -- Back
(20, 14);  -- Biceps

INSERT INTO workout_day_muscle_groups VALUES
(22, 10),
(22, 11),
(22, 12),
(22, 13),
(22, 14);

-- Lower Days
INSERT INTO workout_day_muscle_groups VALUES
(21, 18),  -- Quads
(21, 16),  -- Hamstrings
(21, 17),  -- Calves
(21, 15);  -- Glutes

INSERT INTO workout_day_muscle_groups VALUES
(23, 18),
(23, 16),
(23, 17),
(23, 15);

-- ============================
-- FULL BODY SPLIT
-- ============================

INSERT INTO workout_plan (id, name, description) VALUES
(4, 'Full Body', '3-day full body training');

-- Days
INSERT INTO workout_day (id, plan_id, day_number, day_name) VALUES
(30, 4, 1, 'Full Body A'),
(31, 4, 2, 'Full Body B'),
(32, 4, 3, 'Full Body C');

-- Each day trains all major groups
INSERT INTO workout_day_muscle_groups VALUES
(30, 10),  -- Chest
(30, 13),  -- Back
(30, 11),  -- Shoulders
(30, 14),  -- Biceps
(30, 12),  -- Triceps
(30, 18),  -- Quads
(30, 16),  -- Hamstrings
(30, 17);  -- Calves

INSERT INTO workout_day_muscle_groups VALUES
(31, 10),
(31, 13),
(31, 11),
(31, 14),
(31, 12),
(31, 18),
(31, 16),
(31, 17);

INSERT INTO workout_day_muscle_groups VALUES
(32, 10),
(32, 13),
(32, 11),
(32, 14),
(32, 12),
(32, 18),
(32, 16),
(32, 17);

-- ============================
-- ARNOLD SPLIT
-- ============================

INSERT INTO workout_plan (id, name, description) VALUES
(5, 'Arnold Split', 'Chest/Back, Shoulders/Arms, Legs');

-- Days
INSERT INTO workout_day (id, plan_id, day_number, day_name) VALUES
(40, 5, 1, 'Chest + Back'),
(41, 5, 2, 'Shoulders + Arms'),
(42, 5, 3, 'Legs');

-- Chest + Back
INSERT INTO workout_day_muscle_groups VALUES
(40, 10),  -- Chest
(40, 13);  -- Back

-- Shoulders + Arms
INSERT INTO workout_day_muscle_groups VALUES
(41, 11),  -- Shoulders
(41, 12),  -- Triceps
(41, 14);  -- Biceps

-- Legs
INSERT INTO workout_day_muscle_groups VALUES
(42, 18),  -- Quads
(42, 16),  -- Hamstrings
(42, 17),  -- Calves
(42, 15);  -- Glutes

-- ============================
-- PPL x2 (6-Day Split)
-- ============================

INSERT INTO workout_plan (id, name, description) VALUES
(7, 'PPL x2', '6-day Push Pull Legs repeated');

-- Days
INSERT INTO workout_day (id, plan_id, day_number, day_name) VALUES
(50, 7, 1, 'Push Day 1'),
(51, 7, 2, 'Pull Day 1'),
(52, 7, 3, 'Leg Day 1'),
(53, 7, 4, 'Push Day 2'),
(54, 7, 5, 'Pull Day 2'),
(55, 7, 6, 'Leg Day 2');

-- ============================
-- PUSH DAYS (Chest / Shoulders / Triceps)
-- ============================

INSERT INTO workout_day_muscle_groups VALUES
(50, 10),  -- Chest
(50, 11),  -- Shoulders
(50, 12);  -- Triceps

INSERT INTO workout_day_muscle_groups VALUES
(53, 10),
(53, 11),
(53, 12);

-- ============================
-- PULL DAYS (Back / Biceps)
-- ============================

INSERT INTO workout_day_muscle_groups VALUES
(51, 13),  -- Back
(51, 14);  -- Biceps

INSERT INTO workout_day_muscle_groups VALUES
(54, 13),
(54, 14);

-- ============================
-- LEG DAYS (Quads / Hamstrings / Calves / Glutes)
-- ============================

INSERT INTO workout_day_muscle_groups VALUES
(52, 18),  -- Quads
(52, 16),  -- Hamstrings
(52, 17),  -- Calves
(52, 15);  -- Glutes

INSERT INTO workout_day_muscle_groups VALUES
(55, 18),
(55, 16),
(55, 17),
(55, 15);


-- ============================
-- CUSTOM SPLIT TEMPLATE
-- ============================

INSERT INTO workout_plan (id, name, description) VALUES
(6, 'Custom', 'User-created split');

-- No days added — user will define them


-- ============================
-- WORKOUT SESSION (example)
-- ============================

INSERT INTO workout_session (id, user_id, date, notes, workout_day_id) VALUES
(1, 1, '2026-06-09', 'Good session', 1);

-- ============================
-- WORKOUT EXERCISE (example)
-- ============================

INSERT INTO workout_exercise (id, session_id, exercise_id, order_index) VALUES
(1, 1, 1, 1); -- Bench Press

-- ============================
-- WORKOUT SETS (example)
-- ============================

INSERT INTO workout_set (workout_exercise_id, set_number, target_weight, target_reps, actual_weight, actual_reps, estimated_1rm_after_set) VALUES
(1, 1, 135, 8, 135, 8, 165),
(1, 2, 155, 6, 155, 6, 180),
(1, 3, 175, 4, 175, 4, 195);

-- ============================
-- EXERCISE → MUSCLE DISTRIBUTIONS
-- ============================

-- 1. Bench Press
INSERT INTO exercise_muscle_distribution VALUES
(1, 31, 40),
(1, 30, 20),
(1, 40, 25),
(1, 50, 15);

-- 2. Incline Dumbbell Press
INSERT INTO exercise_muscle_distribution VALUES
(2, 30, 55),
(2, 31, 25),
(2, 40, 15),
(2, 50, 5);

-- 3. Overhead Press
INSERT INTO exercise_muscle_distribution VALUES
(3, 40, 50),
(3, 41, 25),
(3, 50, 25);

-- 4. Lateral Raise
INSERT INTO exercise_muscle_distribution VALUES
(4, 41, 90),
(4, 42, 10);

-- 5. Lat Pulldown
INSERT INTO exercise_muscle_distribution VALUES
(5, 70, 60),
(5, 71, 20),
(5, 73, 10),
(5, 60, 10);

-- 6. Barbell Row
INSERT INTO exercise_muscle_distribution VALUES
(6, 71, 40),
(6, 70, 25),
(6, 73, 20),
(6, 60, 15);

-- 7. Seated Cable Row
INSERT INTO exercise_muscle_distribution VALUES
(7, 70, 45),
(7, 71, 30),
(7, 73, 15),
(7, 60, 10);

-- 8. Face Pull
INSERT INTO exercise_muscle_distribution VALUES
(8, 42, 50),
(8, 73, 30),
(8, 71, 20);

-- 9. Back Squat
INSERT INTO exercise_muscle_distribution VALUES
(9, 110, 35),
(9, 111, 25),
(9, 113, 20),
(9, 80, 20);

-- 10. Leg Press
INSERT INTO exercise_muscle_distribution VALUES
(10, 110, 30),
(10, 111, 30),
(10, 113, 20),
(10, 80, 20);

-- 11. Romanian Deadlift
INSERT INTO exercise_muscle_distribution VALUES
(11, 90, 40),
(11, 91, 30),
(11, 92, 20),
(11, 80, 10);

-- 12. Leg Extension
INSERT INTO exercise_muscle_distribution VALUES
(12, 110, 60),
(12, 111, 40);
