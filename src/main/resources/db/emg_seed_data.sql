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

-- ============================================
-- PUSH MOVEMENTS — CHEST / SHOULDERS / TRICEPS
-- ============================================

-- 1. Bench Press (ID = 1)
INSERT INTO exercise_muscle_distribution (exercise_id, muscle_group_id, percentage) VALUES
(1, 31, 40),   -- Chest
(1, 30, 20),   -- Triceps
(1, 40, 25),   -- Front Delts
(1, 50, 15);   -- Upper Chest

-- 2. Incline Dumbbell Press (ID = 2)
INSERT INTO exercise_muscle_distribution VALUES
(2, 30, 55),   -- Upper Chest
(2, 31, 25),   -- Chest
(2, 40, 15),   -- Front Delts
(2, 50, 5);    -- Stabilizers

-- 3. Overhead Press (ID = 3)
INSERT INTO exercise_muscle_distribution VALUES
(3, 40, 50),   -- Front Delts
(3, 41, 25),   -- Side Delts
(3, 50, 25);   -- Upper Chest

-- 4. Dumbbell Shoulder Press (ID = 13)
INSERT INTO exercise_muscle_distribution VALUES
(13, 40, 45),  -- Front Delts
(13, 41, 20),  -- Side Delts
(13, 30, 35);  -- Triceps

-- 5. Arnold Press (ID = 14)
INSERT INTO exercise_muscle_distribution VALUES
(14, 40, 50),  -- Front Delts
(14, 50, 20),  -- Upper Chest
(14, 41, 10),  -- Side Delts
(14, 30, 20);  -- Triceps

-- 6. Chest Fly (Cable/Machine) (ID = 15)
INSERT INTO exercise_muscle_distribution VALUES
(15, 31, 70),  -- Chest
(15, 50, 20),  -- Upper Chest
(15, 40, 10);  -- Front Delts

-- 7. Push-Up (ID = 16)
INSERT INTO exercise_muscle_distribution VALUES
(16, 31, 45),  -- Chest
(16, 30, 20),  -- Triceps
(16, 40, 25),  -- Front Delts
(16, 50, 10);  -- Upper Chest

-- 8. Dips (Chest Variation) (ID = 17)
INSERT INTO exercise_muscle_distribution VALUES
(17, 31, 50),  -- Chest
(17, 30, 35),  -- Triceps
(17, 40, 15);  -- Front Delts

-- 9. Tricep Rope Pushdown (ID = 18)
INSERT INTO exercise_muscle_distribution VALUES
(18, 30, 90),  -- Triceps
(18, 40, 10);  -- Front Delts (stabilization)

-- 10. Skull Crushers (ID = 19)
INSERT INTO exercise_muscle_distribution VALUES
(19, 30, 85),  -- Triceps
(19, 40, 15);  -- Front Delts (stabilization)

-- 11. Close-Grip Bench Press (ID = 20)
INSERT INTO exercise_muscle_distribution VALUES
(20, 30, 55),  -- Triceps
(20, 31, 25),  -- Chest
(20, 40, 20);  -- Front Delts

-- 12. Pec Deck Machine (ID = 21)
INSERT INTO exercise_muscle_distribution VALUES
(21, 31, 75),  -- Chest
(21, 50, 15),  -- Upper Chest
(21, 40, 10);  -- Front Delts

-- ============================================
-- PUSH MOVEMENTS — CONTINUED
-- ============================================

-- 13. Cable Crossover (ID = 22)
INSERT INTO exercise_muscle_distribution VALUES
(22, 31, 65),  -- Chest (sternal)
(22, 50, 20),  -- Upper Chest (clavicular)
(22, 40, 15);  -- Front Delts

-- 14. Front Raise (ID = 23)
INSERT INTO exercise_muscle_distribution VALUES
(23, 40, 90),  -- Front Delts
(23, 41, 10);  -- Side Delts

-- 15. Machine Shoulder Press (ID = 24)
INSERT INTO exercise_muscle_distribution VALUES
(24, 40, 45),  -- Front Delts
(24, 41, 15),  -- Side Delts
(24, 30, 40);  -- Triceps

-- 16. Decline Bench Press (ID = 25)
INSERT INTO exercise_muscle_distribution VALUES
(25, 31, 55),  -- Chest (sternal/lower emphasis)
(25, 30, 25),  -- Triceps
(25, 40, 20);  -- Front Delts

-- 17. Landmine Press (ID = 26)
INSERT INTO exercise_muscle_distribution VALUES
(26, 50, 40),  -- Upper Chest
(26, 40, 40),  -- Front Delts
(26, 30, 20);  -- Triceps

-- 18. Cable Tricep Overhead Extension (ID = 27)
INSERT INTO exercise_muscle_distribution VALUES
(27, 30, 90),  -- Triceps (long head)
(27, 40, 10);  -- Front Delts (stabilization)

-- 19. Machine Chest Press (ID = 28)
INSERT INTO exercise_muscle_distribution VALUES
(28, 31, 60),  -- Chest
(28, 30, 25),  -- Triceps
(28, 40, 15);  -- Front Delts

-- 20. Seated Dumbbell Fly (ID = 29)
INSERT INTO exercise_muscle_distribution VALUES
(29, 31, 70),  -- Chest
(29, 50, 20),  -- Upper Chest
(29, 40, 10);  -- Front Delts

-- ============================================
-- PULL MOVEMENTS — BACK / LATS / REAR DELTS / BICEPS
-- ============================================

-- 1. Lat Pulldown (ID = 5)
INSERT INTO exercise_muscle_distribution VALUES
(5, 70, 60),   -- Lats
(5, 71, 20),   -- Upper Back (rhomboids)
(5, 73, 10),   -- Rear Delts
(5, 60, 10);   -- Biceps

-- 2. Barbell Row (ID = 6)
INSERT INTO exercise_muscle_distribution VALUES
(6, 71, 40),   -- Upper Back
(6, 70, 25),   -- Lats
(6, 73, 20),   -- Rear Delts
(6, 60, 15);   -- Biceps

-- 3. Seated Cable Row (ID = 7)
INSERT INTO exercise_muscle_distribution VALUES
(7, 70, 45),   -- Lats
(7, 71, 30),   -- Upper Back
(7, 73, 15),   -- Rear Delts
(7, 60, 10);   -- Biceps

-- 4. Face Pull (ID = 8)
INSERT INTO exercise_muscle_distribution VALUES
(8, 42, 50),   -- Rear Delts
(8, 73, 30),   -- Mid Back / Rhomboids
(8, 71, 20);   -- Upper Back

-- 5. Pull-Up (ID = 30)
INSERT INTO exercise_muscle_distribution VALUES
(30, 70, 55),  -- Lats
(30, 71, 20),  -- Upper Back
(30, 73, 10),  -- Rear Delts
(30, 60, 15);  -- Biceps

-- 6. Chin-Up (ID = 31)
INSERT INTO exercise_muscle_distribution VALUES
(31, 70, 40),  -- Lats
(31, 60, 40),  -- Biceps
(31, 71, 10),  -- Upper Back
(31, 73, 10);  -- Rear Delts

-- 7. Single-Arm Dumbbell Row (ID = 32)
INSERT INTO exercise_muscle_distribution VALUES
(32, 70, 50),  -- Lats
(32, 71, 25),  -- Upper Back
(32, 73, 15),  -- Rear Delts
(32, 60, 10);  -- Biceps

-- 8. T-Bar Row (ID = 33)
INSERT INTO exercise_muscle_distribution VALUES
(33, 71, 45),  -- Upper Back
(33, 70, 30),  -- Lats
(33, 73, 15),  -- Rear Delts
(33, 60, 10);  -- Biceps

-- 9. Cable Rear Delt Fly (ID = 34)
INSERT INTO exercise_muscle_distribution VALUES
(34, 42, 80),  -- Rear Delts
(34, 73, 20);  -- Mid Back

-- 10. Machine Row (ID = 35)
INSERT INTO exercise_muscle_distribution VALUES
(35, 70, 50),  -- Lats
(35, 71, 30),  -- Upper Back
(35, 73, 10),  -- Rear Delts
(35, 60, 10);  -- Biceps

-- ============================================
-- PULL MOVEMENTS — CONTINUED
-- ============================================

-- 11. Deadlift (ID = 36)
INSERT INTO exercise_muscle_distribution VALUES
(36, 71, 35),   -- Upper Back
(36, 73, 20),   -- Rear Delts
(36, 90, 25),   -- Hamstrings
(36, 91, 10),   -- Glutes
(36, 60, 10);   -- Biceps (stabilization)

-- 12. Romanian Deadlift (ID = 11) — already provided
-- Keeping original values

-- 13. Rack Pull (ID = 37)
INSERT INTO exercise_muscle_distribution VALUES
(37, 71, 45),   -- Upper Back
(37, 73, 25),   -- Rear Delts
(37, 90, 20),   -- Hamstrings
(37, 60, 10);   -- Biceps (stabilization)

-- 14. Shrugs (ID = 38)
INSERT INTO exercise_muscle_distribution VALUES
(38, 72, 85),   -- Traps
(38, 71, 10),   -- Upper Back
(38, 73, 5);    -- Rear Delts

-- 15. Cable Pullover (ID = 39)
INSERT INTO exercise_muscle_distribution VALUES
(39, 70, 70),   -- Lats
(39, 71, 20),   -- Upper Back
(39, 60, 10);   -- Biceps (minimal)

-- 16. Reverse Pec Deck (ID = 40)
INSERT INTO exercise_muscle_distribution VALUES
(40, 42, 75),   -- Rear Delts
(40, 73, 25);   -- Mid Back

-- 17. Barbell Curl (ID = 41)
INSERT INTO exercise_muscle_distribution VALUES
(41, 60, 80),   -- Biceps
(41, 61, 20);   -- Forearms

-- 18. Dumbbell Curl (ID = 42)
INSERT INTO exercise_muscle_distribution VALUES
(42, 60, 75),   -- Biceps
(42, 61, 25);   -- Forearms

-- 19. Hammer Curl (ID = 43)
INSERT INTO exercise_muscle_distribution VALUES
(43, 61, 60),   -- Forearms / Brachioradialis
(43, 60, 40);   -- Biceps (long head)

-- 20. Preacher Curl (ID = 44)
INSERT INTO exercise_muscle_distribution VALUES
(44, 60, 85),   -- Biceps
(44, 61, 15);   -- Forearms

-- 21. Cable Curl (ID = 45)
INSERT INTO exercise_muscle_distribution VALUES
(45, 60, 80),   -- Biceps
(45, 61, 20);   -- Forearms

-- 22. Machine Curl (ID = 46)
INSERT INTO exercise_muscle_distribution VALUES
(46, 60, 85),   -- Biceps
(46, 61, 15);   -- Forearms

-- 23. Reverse Curl (ID = 47)
INSERT INTO exercise_muscle_distribution VALUES
(47, 61, 70),   -- Forearms
(47, 60, 30);   -- Biceps

-- 24. Wrist Curl (ID = 48)
INSERT INTO exercise_muscle_distribution VALUES
(48, 61, 90);   -- Forearms

-- 25. Wrist Extension (ID = 49)
INSERT INTO exercise_muscle_distribution VALUES
(49, 61, 90);   -- Forearms

-- 26. Cable High Row (ID = 50)
INSERT INTO exercise_muscle_distribution VALUES
(50, 71, 40),   -- Upper Back
(50, 73, 35),   -- Rear Delts
(50, 70, 15),   -- Lats
(50, 60, 10);   -- Biceps

-- 27. Chest-Supported Row (ID = 51)
INSERT INTO exercise_muscle_distribution VALUES
(51, 71, 45),   -- Upper Back
(51, 70, 30),   -- Lats
(51, 73, 15),   -- Rear Delts
(51, 60, 10);   -- Biceps

-- 28. Meadows Row (ID = 52)
INSERT INTO exercise_muscle_distribution VALUES
(52, 73, 40),   -- Rear Delts
(52, 71, 35),   -- Upper Back
(52, 70, 15),   -- Lats
(52, 60, 10);   -- Biceps

-- 29. Seal Row (ID = 53)
INSERT INTO exercise_muscle_distribution VALUES
(53, 71, 50),   -- Upper Back
(53, 70, 30),   -- Lats
(53, 73, 10),   -- Rear Delts
(53, 60, 10);   -- Biceps

-- 30. Cable Lat Iso Hold (ID = 54)
INSERT INTO exercise_muscle_distribution VALUES
(54, 70, 85),   -- Lats
(54, 71, 10),   -- Upper Back
(54, 60, 5);    -- Biceps

-- ============================================
-- LOWER BODY — QUADS / HAMSTRINGS / GLUTES / CALVES
-- ============================================

-- 1. Back Squat (ID = 9)
INSERT INTO exercise_muscle_distribution VALUES
(9, 110, 35),   -- Quads
(9, 111, 25),   -- Glutes
(9, 113, 20),   -- Hamstrings
(9, 80, 20);    -- Core (stabilization)

-- 2. Leg Press (ID = 10)
INSERT INTO exercise_muscle_distribution VALUES
(10, 110, 30),  -- Quads
(10, 111, 30),  -- Glutes
(10, 113, 20),  -- Hamstrings
(10, 80, 20);   -- Core

-- 3. Romanian Deadlift (ID = 11)
INSERT INTO exercise_muscle_distribution VALUES
(11, 90, 40),   -- Hamstrings
(11, 91, 30),   -- Glutes
(11, 92, 20),   -- Lower Back
(11, 80, 10);   -- Core

-- 4. Leg Extension (ID = 12)
INSERT INTO exercise_muscle_distribution VALUES
(12, 110, 60),  -- Quads
(12, 111, 40);  -- Glutes (minor stabilization)

-- 5. Front Squat (ID = 55)
INSERT INTO exercise_muscle_distribution VALUES
(55, 110, 50),  -- Quads
(55, 111, 20),  -- Glutes
(55, 113, 10),  -- Hamstrings
(55, 80, 20);   -- Core

-- 6. Hack Squat (ID = 56)
INSERT INTO exercise_muscle_distribution VALUES
(56, 110, 55),  -- Quads
(56, 111, 25),  -- Glutes
(56, 113, 10),  -- Hamstrings
(56, 80, 10);   -- Core

-- 7. Bulgarian Split Squat (ID = 57)
INSERT INTO exercise_muscle_distribution VALUES
(57, 110, 45),  -- Quads
(57, 111, 35),  -- Glutes
(57, 113, 15),  -- Hamstrings
(57, 80, 5);    -- Core

-- 8. Walking Lunge (ID = 58)
INSERT INTO exercise_muscle_distribution VALUES
(58, 110, 40),  -- Quads
(58, 111, 35),  -- Glutes
(58, 113, 15),  -- Hamstrings
(58, 80, 10);   -- Core

-- 9. Hip Thrust (ID = 59)
INSERT INTO exercise_muscle_distribution VALUES
(59, 91, 60),   -- Glutes
(59, 90, 25),   -- Hamstrings
(59, 92, 10),   -- Lower Back
(59, 80, 5);    -- Core

-- 10. Glute Bridge (ID = 60)
INSERT INTO exercise_muscle_distribution VALUES
(60, 91, 65),   -- Glutes
(60, 90, 20),   -- Hamstrings
(60, 92, 10),   -- Lower Back
(60, 80, 5);    -- Core

-- 11. Deadlift (ID = 36) — already included in pull but repeated here for legs
INSERT INTO exercise_muscle_distribution VALUES
(36, 90, 25),   -- Hamstrings
(36, 91, 10),   -- Glutes
(36, 92, 20),   -- Lower Back
(36, 80, 10);   -- Core

-- 12. Sumo Deadlift (ID = 61)
INSERT INTO exercise_muscle_distribution VALUES
(61, 111, 40),  -- Glutes
(61, 113, 25),  -- Hamstrings
(61, 114, 20),  -- Adductors
(61, 92, 10),   -- Lower Back
(61, 80, 5);    -- Core

-- 13. Leg Curl (ID = 62)
INSERT INTO exercise_muscle_distribution VALUES
(62, 90, 70),   -- Hamstrings
(62, 91, 20),   -- Glutes
(62, 92, 10);   -- Lower Back

-- 14. Seated Leg Curl (ID = 63)
INSERT INTO exercise_muscle_distribution VALUES
(63, 90, 75),   -- Hamstrings
(63, 91, 15),   -- Glutes
(63, 92, 10);   -- Lower Back

-- 15. Standing Calf Raise (ID = 64)
INSERT INTO exercise_muscle_distribution VALUES
(64, 120, 80),  -- Gastrocnemius
(64, 121, 20);  -- Soleus

-- 16. Seated Calf Raise (ID = 65)
INSERT INTO exercise_muscle_distribution VALUES
(65, 121, 85),  -- Soleus
(65, 120, 15);  -- Gastrocnemius

-- 17. Adductor Machine (ID = 66)
INSERT INTO exercise_muscle_distribution VALUES
(66, 114, 85),  -- Adductors
(66, 111, 10),  -- Glutes (minor)
(66, 110, 5);   -- Quads (stabilization)

-- 18. Abductor Machine (ID = 67)
INSERT INTO exercise_muscle_distribution VALUES
(67, 111, 70),  -- Glutes (medius/minimus)
(67, 114, 20),  -- Adductors (stabilization)
(67, 80, 10);   -- Core

-- 19. Step-Up (ID = 68)
INSERT INTO exercise_muscle_distribution VALUES
(68, 110, 40),  -- Quads
(68, 111, 35),  -- Glutes
(68, 113, 15),  -- Hamstrings
(68, 80, 10);   -- Core

-- 20. Smith Machine Squat (ID = 69)
INSERT INTO exercise_muscle_distribution VALUES
(69, 110, 45),  -- Quads
(69, 111, 30),  -- Glutes
(69, 113, 15),  -- Hamstrings
(69, 80, 10);   -- Core

-- ============================================
-- CORE MOVEMENTS — ABS / OBLIQUES / LOWER BACK
-- ============================================

-- 1. Plank (ID = 70)
INSERT INTO exercise_muscle_distribution VALUES
(70, 130, 50),   -- Rectus Abdominis
(70, 131, 30),   -- Obliques
(70, 92, 10),    -- Lower Back (erectors)
(70, 80, 10);    -- Core Stabilizers

-- 2. Side Plank (ID = 71)
INSERT INTO exercise_muscle_distribution VALUES
(71, 131, 60),   -- Obliques
(71, 130, 20),   -- Rectus Abdominis
(71, 80, 20);    -- Core Stabilizers

-- 3. Crunch (ID = 72)
INSERT INTO exercise_muscle_distribution VALUES
(72, 130, 75),   -- Rectus Abdominis
(72, 131, 15),   -- Obliques
(72, 80, 10);    -- Core Stabilizers

-- 4. Cable Crunch (ID = 73)
INSERT INTO exercise_muscle_distribution VALUES
(73, 130, 80),   -- Rectus Abdominis
(73, 131, 10),   -- Obliques
(73, 80, 10);    -- Core Stabilizers

-- 5. Hanging Leg Raise (ID = 74)
INSERT INTO exercise_muscle_distribution VALUES
(74, 130, 60),   -- Rectus Abdominis
(74, 132, 25),   -- Hip Flexors
(74, 131, 10),   -- Obliques
(74, 80, 5);     -- Core Stabilizers

-- 6. Knee Raise (ID = 75)
INSERT INTO exercise_muscle_distribution VALUES
(75, 130, 55),   -- Rectus Abdominis
(75, 132, 25),   -- Hip Flexors
(75, 131, 10),   -- Obliques
(75, 80, 10);    -- Core Stabilizers

-- 7. Russian Twist (ID = 76)
INSERT INTO exercise_muscle_distribution VALUES
(76, 131, 60),   -- Obliques
(76, 130, 25),   -- Rectus Abdominis
(76, 80, 15);    -- Core Stabilizers

-- 8. Bicycle Crunch (ID = 77)
INSERT INTO exercise_muscle_distribution VALUES
(77, 131, 50),   -- Obliques
(77, 130, 40),   -- Rectus Abdominis
(77, 80, 10);    -- Core Stabilizers

-- 9. Back Extension (ID = 78)
INSERT INTO exercise_muscle_distribution VALUES
(78, 92, 70),    -- Lower Back (erectors)
(78, 91, 20),    -- Glutes
(78, 90, 10);    -- Hamstrings

-- 10. Superman (ID = 79)
INSERT INTO exercise_muscle_distribution VALUES
(79, 92, 65),    -- Lower Back
(79, 91, 25),    -- Glutes
(79, 90, 10);    -- Hamstrings

-- 11. Pallof Press (ID = 80)
INSERT INTO exercise_muscle_distribution VALUES
(80, 131, 50),   -- Obliques (anti-rotation)
(80, 130, 30),   -- Rectus Abdominis
(80, 80, 20);    -- Core Stabilizers

-- 12. Ab Wheel Rollout (ID = 81)
INSERT INTO exercise_muscle_distribution VALUES
(81, 130, 60),   -- Rectus Abdominis
(81, 131, 20),   -- Obliques
(81, 92, 15),    -- Lower Back
(81, 80, 5);     -- Core Stabilizers

-- 13. Weighted Sit-Up (ID = 82)
INSERT INTO exercise_muscle_distribution VALUES
(82, 130, 70),   -- Rectus Abdominis
(82, 131, 20),   -- Obliques
(82, 80, 10);    -- Core Stabilizers

-- 14. V-Up (ID = 83)
INSERT INTO exercise_muscle_distribution VALUES
(83, 130, 65),   -- Rectus Abdominis
(83, 131, 20),   -- Obliques
(83, 132, 10),   -- Hip Flexors
(83, 80, 5);     -- Core Stabilizers

-- 15. Toe Touch (ID = 84)
INSERT INTO exercise_muscle_distribution VALUES
(84, 130, 70),   -- Rectus Abdominis
(84, 131, 20),   -- Obliques
(84, 80, 10);    -- Core Stabilizers

-- 16. Oblique Crunch (ID = 85)
INSERT INTO exercise_muscle_distribution VALUES
(85, 131, 70),   -- Obliques
(85, 130, 20),   -- Rectus Abdominis
(85, 80, 10);    -- Core Stabilizers

-- 17. Hip Flexor Raise (ID = 86)
INSERT INTO exercise_muscle_distribution VALUES
(86, 132, 70),   -- Hip Flexors
(86, 130, 20),   -- Rectus Abdominis
(86, 80, 10);    -- Core Stabilizers

-- 18. Reverse Crunch (ID = 87)
INSERT INTO exercise_muscle_distribution VALUES
(87, 130, 65),   -- Rectus Abdominis
(87, 131, 20),   -- Obliques
(87, 80, 15);    -- Core Stabilizers

-- 19. Cable Woodchopper (ID = 88)
INSERT INTO exercise_muscle_distribution VALUES
(88, 131, 65),   -- Obliques
(88, 130, 25),   -- Rectus Abdominis
(88, 80, 10);    -- Core Stabilizers

-- 20. Farmer’s Carry (ID = 89)
INSERT INTO exercise_muscle_distribution VALUES
(89, 80, 50),    -- Core Stabilizers
(89, 131, 20),   -- Obliques
(89, 130, 15),   -- Rectus Abdominis
(89, 92, 15);    -- Lower Back

-- ============================================
-- MACHINE MOVEMENTS — FULL EMG DATA
-- ============================================

-- 1. Machine Chest Press (ID = 28)
INSERT INTO exercise_muscle_distribution VALUES
(28, 31, 60),   -- Chest
(28, 30, 25),   -- Triceps
(28, 40, 15);   -- Front Delts

-- 2. Pec Deck Machine (ID = 21)
INSERT INTO exercise_muscle_distribution VALUES
(21, 31, 75),   -- Chest
(21, 50, 15),   -- Upper Chest
(21, 40, 10);   -- Front Delts

-- 3. Machine Shoulder Press (ID = 24)
INSERT INTO exercise_muscle_distribution VALUES
(24, 40, 45),   -- Front Delts
(24, 41, 15),   -- Side Delts
(24, 30, 40);   -- Triceps

-- 4. Machine Lateral Raise (ID = 90)
INSERT INTO exercise_muscle_distribution VALUES
(90, 41, 85),   -- Side Delts
(90, 42, 15);   -- Rear Delts

-- 5. Machine Row (ID = 35)
INSERT INTO exercise_muscle_distribution VALUES
(35, 70, 50),   -- Lats
(35, 71, 30),   -- Upper Back
(35, 73, 10),   -- Rear Delts
(35, 60, 10);   -- Biceps

-- 6. Machine Lat Pulldown (ID = 91)
INSERT INTO exercise_muscle_distribution VALUES
(91, 70, 65),   -- Lats
(91, 71, 20),   -- Upper Back
(91, 73, 10),   -- Rear Delts
(91, 60, 5);    -- Biceps

-- 7. Machine Low Row (ID = 92)
INSERT INTO exercise_muscle_distribution VALUES
(92, 70, 55),   -- Lats
(92, 71, 30),   -- Upper Back
(92, 73, 10),   -- Rear Delts
(92, 60, 5);    -- Biceps

-- 8. Machine Back Extension (ID = 93)
INSERT INTO exercise_muscle_distribution VALUES
(93, 92, 70),   -- Lower Back
(93, 91, 20),   -- Glutes
(93, 90, 10);   -- Hamstrings

-- 9. Leg Press Machine (ID = 10)
-- Already included in legs, keeping original values

-- 10. Hack Squat Machine (ID = 56)
-- Already included in legs

-- 11. Leg Extension Machine (ID = 12)
-- Already included in legs

-- 12. Seated Leg Curl Machine (ID = 63)
-- Already included in legs

-- 13. Standing Calf Machine (ID = 64)
-- Already included in legs

-- 14. Seated Calf Machine (ID = 65)
-- Already included in legs

-- 15. Hip Abductor Machine (ID = 67)
-- Already included in legs

-- 16. Hip Adductor Machine (ID = 66)
-- Already included in legs

-- 17. Smith Machine Squat (ID = 69)
-- Already included in legs

-- 18. Smith Machine Bench Press (ID = 94)
INSERT INTO exercise_muscle_distribution VALUES
(94, 31, 50),   -- Chest
(94, 30, 30),   -- Triceps
(94, 40, 20);   -- Front Delts

-- 19. Smith Machine Shoulder Press (ID = 95)
INSERT INTO exercise_muscle_distribution VALUES
(95, 40, 50),   -- Front Delts
(95, 41, 20),   -- Side Delts
(95, 30, 30);   -- Triceps

-- 20. Cable Machine Bicep Curl (ID = 45)
-- Already included in pull

-- 21. Cable Machine Tricep Pushdown (ID = 18)
-- Already included in push

-- 22. Cable Machine Fly (ID = 15)
-- Already included in push

-- 23. Cable Machine Rear Delt Fly (ID = 34)
-- Already included in pull

-- 24. Cable Machine Woodchopper (ID = 88)
-- Already included in core

-- 25. Machine Preacher Curl (ID = 46)
-- Already included in pull

-- 26. Machine Dip (ID = 96)
INSERT INTO exercise_muscle_distribution VALUES
(96, 30, 55),   -- Triceps
(96, 31, 30),   -- Chest
(96, 40, 15);   -- Front Delts

-- 27. Machine Tricep Extension (ID = 97)
INSERT INTO exercise_muscle_distribution VALUES
(97, 30, 85),   -- Triceps
(97, 40, 15);   -- Front Delts (stabilization)

-- 28. Machine Curl (ID = 46)
-- Already included in pull

-- 29. Machine Ab Crunch (ID = 98)
INSERT INTO exercise_muscle_distribution VALUES
(98, 130, 75),  -- Rectus Abdominis
(98, 131, 15),  -- Obliques
(98, 80, 10);   -- Core Stabilizers

-- 30. Machine Oblique Twist (ID = 99)
INSERT INTO exercise_muscle_distribution VALUES
(99, 131, 70),  -- Obliques
(99, 130, 20),  -- Rectus Abdominis
(99, 80, 10);   -- Core Stabilizers
