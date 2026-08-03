# FitnessApp Implementation Handoff

**Version:** 1.0.0  
**Date:** 2026-08-03  
**Design Owner:** Figma AI / cdog7588  
**Design File:** [Figma File](https://www.figma.com/design/Q8Q4RnR7XK2SrMFHm7tTR5)  
**Stack:** React + TypeScript + Vite (frontend), Spring Boot 3 + MySQL + JWT (backend)

---

## 1. Product Scope and Final Frame Index

### Canonical Frame Names

| Page | Frame Name | Node ID | Platform | Route |
|---|---|---|---|---|
| Desktop Auth | login | 8:9 | Desktop | `/login` |
| Desktop Auth | register | 8:40 | Desktop | `/register` |
| Desktop Core | dashboard | 8:77 | Desktop | `/` |
| Desktop Core | exercises-list | 8:235 | Desktop | `/exercises` |
| Desktop Core | plans-list | 8:350 | Desktop | `/plans` |
| Desktop Core | plan-detail | 8:445 | Desktop | `/plans/:id` |
| Desktop Analytics | workout-generator | 8:676 | Desktop | `/generator/:dayId` |
| Desktop Analytics | workout-logger | 8:801 | Desktop | `/logger` |
| Desktop Analytics | analytics-dashboard | 8:988 | Desktop | `/analytics` |
| Desktop Analytics | settings | 8:1124 | Desktop | `/settings` |
| Desktop Analytics | user-profile | 8:1216 | Desktop | `/profile` |
| Mobile | mobile-login | 8:1337 | Mobile | `/login` |
| Mobile | mobile-register | 8:1377 | Mobile | `/register` |
| Mobile | mobile-dashboard | 8:1422 | Mobile | `/` |
| Mobile | mobile-exercises | 8:1524 | Mobile | `/exercises` |
| Mobile | mobile-plans | 8:1621 | Mobile | `/plans` |
| Mobile | mobile-plan-detail | 8:1704 | Mobile | `/plans/:id` |
| Mobile | mobile-generator | 8:1791 | Mobile | `/generator/:dayId` |
| Mobile | mobile-workout-logger | 8:1841 | Mobile | `/logger` |
| Mobile | mobile-analytics | 8:1935 | Mobile | `/analytics` |
| Popups | confirmation-dialogs | 8:2134 | Both | overlay |
| Popups | toast-notifications | 8:2187 | Both | overlay |
| Popups | loading-empty-states | 8:2233 | Both | overlay |
| Popups | contextual-micro-ui | 8:2293 | Both | overlay |

### "Do Not Change" Visual/UX Rules

1. Dark theme is the primary and default theme. All screens ship dark-first.
2. Brand name is **NEURAL.FIT** — always uppercase, always use the brand lockup.
3. Electric lime (`#CCFF00`) is the primary accent. Cyan (`#00E5FF`) is reserved for data/analytics values only.
4. Monospace font (`Geist Mono`) is used exclusively for data labels, metrics, and system-style identifiers. Never for headings or body copy.
5. Sidebar navigation width is fixed at 260px on desktop. It does not collapse or resize.
6. All destructive actions (delete, purge, discard) use `#FF3B30` and require confirmation dialogs.
7. The workout logger must remain usable one-handed on mobile — no small targets, no horizontal scrolling.
8. All auth screens (login/register) are standalone full-bleed — no sidebar, no topbar.

---

## 2. Route and Screen Mapping

### Navigation Structure

**Desktop:** Fixed left sidebar (260px) + top bar (56px height).
- Sidebar items: Dashboard, Exercises, Plans, Analytics, Settings
- Sidebar footer: User avatar + name + logout button
- Top bar: Page title (left), JWT status indicator + logout icon (right)

**Mobile:** Bottom tab bar (56px height, 5 tabs).
- Tabs: Home, Exercises, Plans, Analytics, Profile
- Each tab uses icon + label
- Active tab: lime accent icon + label color

### Route Table

| Route | Screen | Purpose | Entry Point | Primary Actions | Secondary Actions |
|---|---|---|---|---|---|
| `/login` | Login | Authenticate user | App launch (unauthenticated) | Submit login form | Navigate to Register |
| `/register` | Register | Create account | Login page link | Submit registration form | Navigate to Login |
| `/` | Dashboard | Hub + quick actions | Post-login redirect | Start Workout, View Plans | Browse recent, view sparklines |
| `/exercises` | Exercises List | Browse exercise library | Sidebar nav | Search, filter exercises | View exercise detail (future) |
| `/plans` | Plans List | Browse workout plans | Sidebar nav | View plan detail | Compare plans |
| `/plans/:id` | Plan Detail | View plan days/structure | Plans list card click | Generate workout for a day | Edit plan, delete plan |
| `/generator/:dayId` | Workout Generator | View auto-generated workout | Plan detail "Generate Workout" CTA | Start workout session | Shuffle/regenerate, add/remove exercises |
| `/logger` | Workout Logger | Log active session | Generator "Start Session" CTA | Log sets (weight/reps/RPE) | Navigate exercises, finish workout |
| `/analytics` | Analytics | View strength progression | Sidebar nav | Change time range, select exercise | View recommendations |
| `/settings` | Settings | App preferences | Sidebar nav | Change units, rest timer | Export data, purge history |
| `/profile` | User Profile | View training stats | Sidebar nav (mobile: tab) | View PRs, activity timeline | Edit profile (future) |

---

## 3. Data Contract Per Screen

### Login (`POST /auth/login`)

| Field | Type | Required | Notes |
|---|---|---|---|
| username | string | yes | Min 3 chars |
| password | string | yes | Min 6 chars, show/hide toggle |

- **Empty state:** Fields empty with placeholder text
- **Error state:** Red border on invalid field, error message below field ("Invalid username or password")
- **Loading state:** Button shows spinner, inputs disabled
- **Success:** Redirect to `/`, store JWT in localStorage

### Register (`POST /auth/register`)

| Field | Type | Required | Notes |
|---|---|---|---|
| username | string | yes | Min 3 chars, unique check |
| password | string | yes | Min 6 chars |
| confirmPassword | string | yes | Must match password |

- **Validation:** Inline, real-time after blur
- **Error state:** Red borders, per-field error messages
- **Success:** Redirect to `/login` with success toast

### Dashboard (`/`)

| Widget | Data Source | Fields | Empty Behavior |
|---|---|---|---|
| Quick Actions | Static | — | Always visible |
| Recent Workout | `GET /api/workout/history` (latest 1) | date, planName, totalVolume, duration, setsCompleted | "No workouts yet" + Start CTA |
| Strength Sparklines | `GET /api/strength-timeline/exercise/{id}` (top 3) | exerciseName, dataPoints[]{date, estimated1RM} | "Log more workouts to see trends" |
| Weekly Volume | `GET /api/workout/history` (aggregated) | weekData[]{week, totalVolume} | Show empty chart with "No data" |
| Next Planned | `GET /plans` (user's active plan, next day) | dayName, muscleGroups[], exerciseCount | "No active plan" + Browse Plans CTA |

### Exercises List (`GET /exercises`)

| Field | Type | Required | Notes |
|---|---|---|---|
| id | number | yes | Unique identifier |
| name | string | yes | Display name |
| primaryMuscleGroup | string | yes | Badge display |
| secondaryMuscleGroups | string[] | no | Small tags |
| emgActivation | number | no | 0-100, shown as bar percentage |
| equipmentType | string | no | Used for filtering |

- **Search:** Client-side filter by name, debounced 300ms
- **Filters:** Muscle group chips (CHEST, BACK, LEGS, SHOULDERS, ARMS), equipment chips (BARBELL, DUMBBELL, CABLE, BODYWEIGHT)
- **Empty:** "No exercises match your filters" + Clear Filters CTA
- **Loading:** 8 skeleton cards in grid
- **Pagination:** Not required (full list from `GET /exercises`), client-side virtual scroll if >50 items

### Plans List (`GET /plans`)

| Field | Type | Notes |
|---|---|---|
| id | number | Unique identifier |
| name | string | e.g. "Push Pull Legs (PPL)" |
| type | string | Category label (HYPERTROPHY, VOLUME, EFFICIENCY) |
| daysPerWeek | number | Shown as "X_DAYS_WK" |
| muscleDistribution | object | { muscleGroup: percentage } for distribution bar |

- **Empty:** "No plans available" + Create Plan CTA (future)
- **Loading:** 3 skeleton cards

### Plan Detail (`GET /plans/:id`)

| Field | Type | Notes |
|---|---|---|
| id | number | Plan ID |
| name | string | Plan name |
| description | string | Plan description text |
| days | WorkoutDay[] | Array of day definitions |

**WorkoutDay:**

| Field | Type | Notes |
|---|---|---|
| id | number | Day ID (used for `/generator/:dayId`) |
| name | string | e.g. "Day 1: Push Sequence A" |
| muscleGroups | string[] | Colored badges |
| exerciseCount | number | "X_EXERCISES_QUEUED" |
| estimatedDuration | string | "EST_XX_MINS_DURATION" |

- **Empty:** "This plan has no days configured"
- **Actions:** Edit Protocol (secondary), Purge Map (destructive, needs confirmation), Generate Workout (primary, per day)

### Workout Generator (`GET /generator/day/:dayId`)

| Field | Type | Notes |
|---|---|---|
| dayName | string | Day title |
| targetMuscleGroups | string[] | Target zones |
| exercises | GeneratedExercise[] | Recommended exercises |

**GeneratedExercise:**

| Field | Type | Notes |
|---|---|---|
| exerciseId | number | Exercise reference |
| name | string | Exercise name |
| sets | number | Recommended set count |
| reps | number | Recommended rep count |
| targetWeight | number | Calculated from 1RM (shown as "XX kg (YY% 1RM)") |
| restPeriod | number | Rest in seconds |

- **Actions per row:** "ADDED" toggle button (lime when active)
- **Footer:** Total estimated volume display, "START_WORKOUT_SESSION" primary CTA
- **Shuffle:** Top-right "SHUFFLE_SELECTIONS" button to regenerate

### Workout Logger (Active Session)

| Field | Type | Notes |
|---|---|---|
| sessionId | number | From session creation |
| currentExercise | string | Large heading display |
| rpeAverage | number | Running RPE average |
| sets | WorkoutSet[] | Editable set rows |

**WorkoutSet (per row):**

| Field | Type | Required | Notes |
|---|---|---|---|
| setNumber | number | yes | Auto-incremented |
| targetWeight | number | yes | Pre-filled from generator |
| targetReps | number | yes | Pre-filled from generator |
| actualWeight | number | yes (on save) | User input, large input field |
| actualReps | number | yes (on save) | User input, large input field |
| rpe | number | no | 1-10 scale, button selector |

- **Sidebar panel:** Session Queue showing exercise list with ACTIVE/QUEUED status
- **Metric telemetry panel:** Elapsed time (live counter, cyan), accumulated volume (kg), sets completed (X / Y)
- **Navigation:** PREV_EXERCISE / NEXT_EXERCISE buttons
- **Finish:** "FINISH_WORKOUT" red CTA at bottom
- **Auto-save:** Every set save triggers `POST /api/workout-sets` immediately
- **Optimistic UI:** Set row shows checkmark immediately, reverts on API failure

### Analytics Dashboard

| Widget | Data Source | Fields |
|---|---|---|
| 1RM Chart | `GET /api/strength-timeline/exercise/{id}` | dataPoints[]{date, estimated1RM}, exerciseName |
| Total Sessions | `GET /api/workout/history` count | number, trend% |
| Volume Accumulated | `GET /api/workout/history` sum | number (kg), trend% |
| Avg Duration | `GET /api/workout/history` avg | number (mins), trend% |
| Muscle Stimulus | `GET /api/stimulus/{userId}` | muscleGroups[]{name, percentage} |
| Recommendations | `GET /api/recommendations/exercise/{id}` | text, reasoning |

- **Time range selector:** 1W, 1M, 3M (default), 6M, 1Y, ALL — pill buttons
- **Chart:** Line chart with data points, "MAX_ACHIEVED" annotation on peak
- **Empty:** "Log at least 3 workouts to see trends" with progress bar

### Settings

| Section | Fields | Behavior |
|---|---|---|
| Account | username (read-only), password (change form) | Inline edit |
| Units | kg/lbs toggle | Segmented control, immediate save |
| Workout Preferences | Default rest period (seconds input), weight step unit (kg input) | Save on blur |
| Data Management | Export (download), Purge (destructive) | Purge requires confirmation dialog |
| System Info | Version, secure protocol | Read-only display |

### User Profile

| Field | Data Source | Type |
|---|---|---|
| username | JWT decode / user API | string |
| avatar | placeholder | image |
| memberSince | user API | date |
| lifetimeWorkouts | aggregated history | number |
| aggregatedVolume | aggregated history | number (kg) |
| setsAccumulated | aggregated history | number |
| preferentialTarget | most-trained muscle | string |
| personalRecords | strength timeline | {exercise, weight, status}[] |
| recentLogs | workout history | {date, dayName, volume}[] |
| activeStreak | calculated | number (weeks) |

---

## 4. Design Tokens

### Colors

| Token Name | Hex | Role |
|---|---|---|
| `--color-bg-primary` | `#09090B` | App background / deepest layer |
| `--color-bg-secondary` | `#141416` | Card backgrounds, content areas |
| `--color-bg-tertiary` | `#1D1D21` | Elevated cards, sidebar, input backgrounds |
| `--color-bg-quaternary` | `#27272D` | Hover states, subtle highlights |
| `--color-border-default` | `#2A2A32` | Card borders, dividers, input borders |
| `--color-border-accent` | `#CCFF00` | Active states, selected items |
| `--color-border-error` | `#FF3B30` | Error state borders |
| `--color-text-primary` | `#FFFFFF` | Headings, primary text |
| `--color-text-secondary` | `#9E9EA8` | Labels, descriptions, metadata |
| `--color-text-tertiary` | `#5F5F69` | Placeholders, disabled text |
| `--color-accent-primary` | `#CCFF00` | Primary CTAs, active nav, badges |
| `--color-accent-data` | `#00E5FF` | Data values, chart highlights, metrics |
| `--color-error` | `#FF3B30` | Errors, destructive actions, delete buttons |
| `--color-success` | `#34C759` | Success toasts, checkmarks |
| `--color-warning` | `#FFD60A` | Warning toasts, caution states |
| `--color-surface-overlay` | `#000000` at 60% opacity | Modal/dialog backdrop |

### Typography

| Token Name | Family | Weight | Size | Line Height | Usage |
|---|---|---|---|---|---|
| `--type-display` | Unbounded | 900 (Black) | 24px | auto | Page titles, hero numbers |
| `--type-heading-lg` | Unbounded | 800 (ExtraBold) | 20px | auto | Section headings |
| `--type-heading-md` | Unbounded | 800 (ExtraBold) | 16px | auto | Card titles, sub-headings |
| `--type-heading-sm` | Unbounded | 800 (ExtraBold) | 14px | auto | Small headings, nav labels |
| `--type-heading-xs` | Unbounded | 800 (ExtraBold) | 12px | auto | Kicker labels above headings |
| `--type-body-md` | Geist | 500 (Medium) | 14px | auto | Body text, descriptions |
| `--type-body-sm` | Geist | 400 (Regular) | 13px | auto | Secondary body text |
| `--type-body-semibold` | Geist | 600 (SemiBold) | 14px | auto | Emphasized body, nav items |
| `--type-body-bold` | Geist | 700 (Bold) | 14px | auto | Strong emphasis |
| `--type-mono-md` | Geist Mono | 400 (Regular) | 14px | auto | Data values, code |
| `--type-mono-sm` | Geist Mono | 400 (Regular) | 13px | auto | Table data, secondary metrics |
| `--type-mono-xs` | Geist Mono | 400 (Regular) | 11px | auto | Labels, metadata, kickers |
| `--type-mono-xxs` | Geist Mono | 400 (Regular) | 10px | auto | Micro-labels, chart annotations |
| `--type-mono-bold` | Geist Mono | 700 (Bold) | 14px | auto | Emphasized data values |
| `--type-mono-bold-xs` | Geist Mono | 700 (Bold) | 11px | auto | Emphasized micro-labels |

### Spacing Scale

| Token | Value | Usage |
|---|---|---|
| `--space-1` | 4px | Inline element gaps, icon-to-text |
| `--space-2` | 6px | Tight padding |
| `--space-3` | 8px | Default inline spacing, chip padding |
| `--space-4` | 10px | Small component padding |
| `--space-5` | 12px | Input padding, card internal spacing |
| `--space-6` | 14px | Medium component gaps |
| `--space-7` | 16px | Sidebar padding, section gaps |
| `--space-8` | 20px | Card padding, grid gaps |
| `--space-9` | 24px | Large card padding, section margins |
| `--space-10` | 28px | Section separators |
| `--space-11` | 32px | Page-level padding (sidebar top/bottom) |
| `--space-12` | 40px | Major section breaks |
| `--space-13` | 64px | Hero spacing |

### Radius Scale

| Token | Value | Usage |
|---|---|---|
| `--radius-xs` | 2px | Micro-badges, inline tags |
| `--radius-sm` | 4px | Chips, small badges |
| `--radius-md` | 6px | Table cells, small cards |
| `--radius-default` | 8px | Buttons, inputs, icon buttons |
| `--radius-lg` | 12px | Cards, exercise cards |
| `--radius-xl` | 16px | Auth cards, plan cards, large panels |
| `--radius-2xl` | 18px | Avatars, special containers |

### Border Tokens

| Token | Value | Usage |
|---|---|---|
| `--border-default` | 1px solid `#2A2A32` | Card borders, input borders, dividers |
| `--border-accent` | 1px solid `#CCFF00` | Active/selected elements |
| `--border-error` | 1px solid `#FF3B30` | Error state inputs |
| `--border-black` | 1px solid `#000000` | Inner card separators |

### Shadow Tokens

No drop shadows are used in the current dark theme. Depth is communicated through background color layering:
- Level 0: `#09090B` (app background)
- Level 1: `#141416` (main content area)
- Level 2: `#1D1D21` (cards, sidebar)
- Level 3: `#27272D` (elevated elements, hovers)

### Z-Index Scale

| Token | Value | Usage |
|---|---|---|
| `--z-base` | 0 | Default content |
| `--z-sidebar` | 10 | Fixed sidebar |
| `--z-topbar` | 20 | Top bar |
| `--z-sticky` | 30 | Sticky CTAs (mobile bottom bar) |
| `--z-dropdown` | 40 | Dropdowns, filter panels |
| `--z-toast` | 50 | Toast notifications |
| `--z-modal-backdrop` | 60 | Modal overlay background |
| `--z-modal` | 70 | Modal content |
| `--z-tooltip` | 80 | Tooltips |

### Light Theme

Light theme tokens are not yet designed. See Missing Decisions section.

---

## 5. Component System Spec

### Button

| Variant | BG | Text Color | Border | Height | Padding | Radius |
|---|---|---|---|---|---|---|
| Primary | `#CCFF00` | `#000000` | none | 48px (lg) / 40px (md) / 34px (sm) | 16px 24px | 8px |
| Secondary | transparent | `#FFFFFF` | 1px `#2A2A32` | 48px / 40px / 34px | 16px 24px | 8px |
| Destructive | `#FF3B30` | `#FFFFFF` | none | 48px / 40px / 34px | 16px 24px | 8px |
| Ghost | transparent | `#9E9EA8` | none | 36px | 8px 12px | 8px |
| Icon | `#1D1D21` | `#FFFFFF` | 1px `#2A2A32` | 36px | 8px | 8px |

**States:**
- Default: As specified above
- Hover: Background lightens 10% (or border becomes `#CCFF00` for secondary)
- Focus: 2px outline ring `#CCFF00` with 2px offset
- Active/Pressed: Background darkens 5%
- Disabled: Opacity 0.4, cursor `not-allowed`
- Loading: Content replaced with 16px spinner, same dimensions

**Accessibility:**
- Minimum touch target: 44x44px (add invisible padding if button is smaller)
- All buttons must have accessible label (text content or `aria-label`)
- Focus visible on keyboard navigation, not on mouse click

### Input / Text Field

| Property | Value |
|---|---|
| Height | 48px |
| Background | `#1D1D21` |
| Border | 1px `#2A2A32` |
| Border (focus) | 1px `#CCFF00` |
| Border (error) | 1px `#FF3B30` |
| Radius | 8px |
| Padding | 12px 16px |
| Text | Geist, 14px, 400, `#FFFFFF` |
| Placeholder | Geist, 14px, 400, `#5F5F69` |
| Label | Geist Mono, 11px, 400, `#9E9EA8`, uppercase |

**States:**
- Default: `#2A2A32` border
- Focus: `#CCFF00` border + focus ring
- Error: `#FF3B30` border + error message below (Geist, 12px, `#FF3B30`)
- Disabled: Opacity 0.4, no interaction
- Filled: `#FFFFFF` text

**Password toggle:** Eye icon button inside input, right-aligned, 20x20px icon area

### Card

| Variant | Dimensions | Padding | Radius | Border |
|---|---|---|---|---|
| Auth Card | 440px wide, hug height | 32px | 16px | 1px `#2A2A32` |
| Exercise Card | flex (4-col grid) | 20px | 12px | 1px `#2A2A32` |
| Plan Card | flex (3-col grid) | 24px | 16px | 1px `#2A2A32` |
| Stat Tile | flex | 20px | 12px | 1px `#2A2A32` |
| Day Card | 100% width | 20px 24px | 12px | 1px `#2A2A32` |

**Background:** `#1D1D21`
**Hover:** Background shifts to `#27272D` + cursor pointer (if clickable)

### Chip / Badge

| Variant | Height | Padding | Radius | BG | Text |
|---|---|---|---|---|---|
| Filter Chip (active) | 28px | 4px 12px | 4px | `#CCFF00` | `#000000`, Geist 12px 500 |
| Filter Chip (inactive) | 28px | 4px 12px | 4px | transparent | `#9E9EA8`, Geist 12px 400, border `#2A2A32` |
| Muscle Badge | 22px | 2px 8px | 4px | `#CCFF00` | `#000000`, Geist Mono 10px 700 |
| Status Badge | 22px | 2px 8px | 4px | varies | Geist Mono 10px 700 |

### Table / List Row

| Property | Value |
|---|---|
| Row height | 56px minimum |
| Padding | 16px horizontal |
| Border bottom | 1px `#2A2A32` |
| Hover BG | `#27272D` |
| Text | Geist, 14px, `#FFFFFF` (primary), `#9E9EA8` (secondary) |
| Data values | Geist Mono, 13px, `#00E5FF` |

### Segmented Control (e.g., Units toggle)

| Property | Value |
|---|---|
| Container BG | `#1D1D21` |
| Container radius | 8px |
| Container border | 1px `#2A2A32` |
| Segment height | 36px |
| Active segment BG | `#CCFF00` |
| Active segment text | `#000000`, Geist 13px 600 |
| Inactive segment text | `#9E9EA8`, Geist 13px 400 |

### Modal / Dialog

| Property | Value |
|---|---|
| Backdrop | `#000000` at 60% opacity |
| Container BG | `#1D1D21` |
| Container radius | 16px |
| Container border | 1px `#2A2A32` |
| Max width | 480px |
| Padding | 32px |
| Title | Unbounded 16px 800, `#FFFFFF` |
| Body | Geist 14px 400, `#9E9EA8` |
| Button row | Right-aligned, 12px gap between buttons |
| Close behavior | Esc key, outside click, explicit close/cancel button |
| Focus trap | Yes — tab cycles within modal only |
| Restore focus | Return focus to trigger element on close |

### Toast Notification

| Variant | Left accent color | Icon |
|---|---|---|
| Success | `#34C759` | Checkmark |
| Error | `#FF3B30` | X circle |
| Warning | `#FFD60A` | Triangle alert |

| Property | Value |
|---|---|
| Position | Top-right, 24px from edges |
| Width | 360px max |
| BG | `#1D1D21` |
| Border | 1px `#2A2A32` + 3px left accent |
| Radius | 8px |
| Padding | 16px |
| Auto-dismiss | 5s (success), 8s (warning), manual only (error) |
| Animation | Slide in from right 300ms ease-out, fade out 200ms |
| Stack | Newest on top, 8px gap between toasts |

### Banner

| Property | Value |
|---|---|
| Position | Full-width, top of content area (below topbar) |
| Height | 40px |
| BG (offline) | `#FF3B30` at 15% opacity |
| BG (reconnect) | `#34C759` at 15% opacity |
| Text | Geist 13px 500, centered |
| Dismiss | Auto-dismiss reconnect after 5s, offline stays until resolved |

### Skeleton Loader

| Property | Value |
|---|---|
| BG | `#1D1D21` |
| Shimmer | Linear gradient sweep `#1D1D21` → `#27272D` → `#1D1D21` |
| Animation | 1.5s ease-in-out infinite |
| Radius | Match the component being loaded |
| Pattern | Replace text with 60%-width bars, images with full rectangles |

### Empty State

| Property | Value |
|---|---|
| Container | Centered in parent, max-width 400px |
| Icon/illustration | 64x64 placeholder, `#5F5F69` |
| Heading | Unbounded 16px 800, `#FFFFFF` |
| Description | Geist 14px 400, `#9E9EA8`, max 2 lines |
| CTA | Primary button (md size) |

### Progress Bar / Activation Bar

| Property | Value |
|---|---|
| Track BG | `#27272D` |
| Track height | 8px |
| Track radius | 4px |
| Fill | `#CCFF00` (primary) or segment colors for distribution |
| Label | Geist Mono 10px, right-aligned or inline |

### RPE Selector (Workout Logger)

| Property | Value |
|---|---|
| Layout | Horizontal row of 10 circular buttons |
| Button size | 28px x 28px |
| Radius | 50% (circle) |
| Default BG | `#27272D` |
| Selected BG | `#CCFF00` |
| Selected text | `#000000` |
| Default text | `#9E9EA8` |
| Font | Geist Mono 11px 700 |

---

## 6. Screen-by-Screen Detailed Specs

### Login Screen

**Desktop Layout:**
- Full viewport, centered auth card (440x431px)
- Background: `#09090B`
- Card: 32px padding, 16px radius
- Brand lockup: NEURAL.FIT logo + icon, top of card
- Form fields: username, password (with show/hide toggle)
- CTA: "LOG IN" primary button, full-width (376px), 48px height
- "Create Account" text link below, `#9E9EA8`, underline on hover

**Mobile Layout:**
- Full-screen, card fills width with 20px horizontal margin
- Same component specs, fields stack vertically
- CTA remains full-width

**States:**
- Empty: Placeholder text in fields
- Loading: Button shows spinner, fields disabled
- Error: Red border on fields, "Invalid username or password" below form
- Success: Redirect to `/`

### Register Screen

**Same as Login with additions:**
- Confirm password field (third field)
- CTA text: "CREATE ACCOUNT"
- "Back to Login" link below
- Inline validation: checkmark or X per field on blur

### Dashboard

**Desktop Layout (1440x1024):**
- Sidebar: 260px fixed, `#141416` background
  - Top: NEURAL.FIT brand lockup (16px padding)
  - Nav items: 40px height each, 8px radius, `#CCFF00` bg when active
  - Nav text: Geist 14px 500
  - Footer: Avatar (32px circle) + username + logout icon button
- Main content: `width: calc(100% - 260px)`
  - Top bar: 56px height, page title (Unbounded 20px 800) left, auth status right
  - Content padding: 32px
  - Layout: Two-column below topbar
    - Left column (~60%): Quick action cards row, then strength sparklines
    - Right column (~40%): Recent workout card, weekly volume chart, next planned workout

**Mobile Layout:**
- No sidebar, bottom tab bar instead
- Single column, full-width cards stacked
- Quick actions as horizontal scroll row
- Bottom tab: 56px, 5 items equally spaced

### Exercises List

**Desktop Layout:**
- Sidebar + topbar chrome (same as Dashboard)
- Search bar: Full content width, 48px height, search icon left
- Filter chips: Horizontal row below search, 8px gap
- Exercise grid: 4 columns, 20px gap
- Card: Exercise name (Unbounded 14px 800), primary muscle badge, secondary tags, EMG bar

**Mobile Layout:**
- Search bar full-width, filters as horizontal scroll
- Single column card list, full-width cards
- Card height: ~120px minimum

### Plans List

**Desktop:** 3-column grid of plan cards, 20px gap
**Mobile:** Single column, full-width cards stacked

### Plan Detail

**Desktop:**
- Plan header: Name (Unbounded 20px 800), description, Edit/Purge action buttons (right-aligned)
- Day cards: Full-width, stacked vertically, 16px gap
- Each day card: Day name, muscle badges, exercise count, "GENERATE_WORKOUT" lime CTA (right-aligned)

**Mobile:**
- Same vertical stack, cards full-width
- CTA becomes full-width below day info

### Workout Generator

**Desktop:**
- Day heading with target muscle groups
- Table layout: Exercise | Volume | Target Weight (1RM) | Rest Period | Action
- Table rows: 56px height, `#2A2A32` bottom border
- Footer bar: Total volume display + "START_WORKOUT_SESSION" CTA (lime, 48px)
- Shuffle button: Top-right, secondary style

**Mobile:**
- Card-per-exercise instead of table
- Sticky bottom: Start Session CTA

### Workout Logger

**Desktop (1440x1024):**
- Three-panel layout:
  - Center (main): Current exercise name (Unbounded 20px 800), set entry table
  - Right panel (~280px): Metric Telemetry (elapsed time in cyan, volume, sets) + Session Queue (exercise list with active/queued status)
- Set table columns: SET | TARGET_WT | TARGET_REPS | ACTUAL_WT (input) | ACTUAL_REPS (input) | RPE (selector) | STATUS (checkmark)
- Input fields in table: 80px wide, 40px height, `#1D1D21` bg
- Bottom bar: PREV_EXERCISE + NEXT_EXERCISE buttons (left), FINISH_WORKOUT red CTA (right)

**Mobile:**
- Single column, current exercise card at top
- Set entry as stacked cards (not table)
- Large inputs: full-width, 56px height for gym gloves
- RPE as large circular buttons (36px each)
- Sticky bottom: Finish Workout CTA
- Swipe between exercises (or prev/next buttons)

**Critical UX:**
- Each set auto-saves on checkmark tap
- Optimistic UI: checkmark appears immediately
- If save fails: checkmark reverts to X, error toast with Retry
- Timer display: Live elapsed time counter
- Session Queue: Tap to jump to exercise

### Analytics Dashboard

**Desktop:**
- Time range pills: Right-aligned in header area (1W, 1M, 3M, 6M, 1Y, ALL)
- Main chart: 1RM line chart, ~600px height, full content width minus stat tiles
- Stat tiles: 3 tiles in right column (Total Sessions, Volume, Avg Duration) — each with number + trend%
- Muscle Stimulus: Horizontal bar chart with labels and percentages
- Recommendation cards: Below charts, full-width, `#1D1D21` bg, lime accent border-left

**Mobile:**
- Time range: Horizontal scroll pills
- Chart: Full-width, 250px height
- Stat tiles: 2x2 grid
- Stimulus bars: Full-width stacked
- Recommendations: Full-width cards

### Settings

**Desktop:**
- Two-column layout:
  - Left (~60%): Account info + Workout Preferences
  - Right (~40%): Units toggle, Data Management, System Info
- Account: Read-only username field, masked password field
- Units: Segmented control (KG / LBS)
- Data purge: Red CTA with warning banner above it

**Mobile:** Single column, all sections stacked

### User Profile

**Desktop:**
- Profile header: Avatar (80px circle) + name + badge + member since
- Active streak tile: Right-aligned in header ("14 WEEKS" with accent)
- Stat tiles: 4-column row (workouts, volume, sets, favorite target)
- Two-column below: Personal Records list (left), Recent Logs timeline (right)

**Mobile:** Single column, stats as 2x2 grid, PR list and logs stacked

---

## 7. Popup and Overlay Matrix

| Popup | Trigger | Content | Buttons | Close Behavior | Focus Trap |
|---|---|---|---|---|---|
| Logout Confirmation | Sidebar logout button | "End your session?" | Cancel (secondary), Logout (destructive) | Esc, outside click, Cancel | Yes |
| Delete Plan | Plan detail delete button | "Delete this workout plan? This action cannot be undone." | Cancel (secondary), Delete (destructive) | Esc, outside click, Cancel | Yes |
| Unsaved Changes | Navigate away during active workout | "You have unsaved workout data. Your progress will be lost." | Discard (secondary), Save & Continue (primary) | Esc (= discard), Cancel | Yes |
| Session Timeout | JWT expiry approaching (5min warning) | "Your session is about to expire" + countdown | Stay Logged In (primary) | No outside click dismiss | Yes |
| Purge Data | Settings purge button | "WARNING: PURGING WILL WIPE ALL 1RM HISTORIES PERMANENTLY" | Cancel (secondary), Purge All Data (destructive) | Esc, outside click, Cancel | Yes |
| Quick Add Set | Logger "+" button | Weight input, reps input, RPE selector | Save (primary) | Esc, outside click | Yes |
| Edit Set | Logger set row double-click | Same as Add Set, pre-filled + Delete Set option | Save (primary), Delete (destructive) | Esc, outside click | Yes |
| Exercise Filter | Exercises filter icon | Muscle group checkboxes, equipment checkboxes, sort dropdown | Apply Filters (primary), Clear All (ghost) | Esc, outside click | Yes |

**All modals:**
- Restore focus to trigger element on close
- Backdrop: `#000000` at 60% opacity
- Enter animation: Scale from 95% + fade in, 200ms ease-out
- Exit animation: Scale to 95% + fade out, 150ms ease-in

---

## 8. User Flow Specs

### Flow 1: New User Registration → First Login

1. User lands on `/login` (default for unauthenticated)
2. Clicks "Create Account" link
3. Navigates to `/register`
4. Fills username, password, confirm password
5. Inline validation on each field blur (green check or red X)
6. Clicks "CREATE ACCOUNT"
7. Loading state: button spinner, fields disabled
8. **Success:** Redirect to `/login` with success toast "Account created successfully"
9. **Failure:** Error message below form ("Username already taken" or API error)
10. On login page, fills credentials
11. Clicks "LOG IN"
12. **Success:** JWT stored in `localStorage`, redirect to `/`
13. **Failure:** Red border on fields, "Invalid username or password"

### Flow 2: Plan → Generate → Log → Summary

1. From Dashboard, click "View Plans" quick action or Plans sidebar nav
2. On `/plans`, browse plan cards
3. Click "VIEW_SYSTEM_PLAN" on a plan card
4. Navigate to `/plans/:id` — view day breakdown
5. Click "GENERATE_WORKOUT" on a day card
6. Navigate to `/generator/:dayId` — view recommended exercises
7. Toggle exercises to add/remove, optionally shuffle
8. Click "START_WORKOUT_SESSION"
9. Navigate to `/logger` — begin logging
10. For each exercise:
    a. View target weight/reps pre-filled
    b. Enter actual weight in input (tap to focus, number keyboard on mobile)
    c. Enter actual reps
    d. Optionally select RPE (1-10)
    e. Tap checkmark to save set → `POST /api/workout-sets`
    f. Optimistic checkmark appears immediately
    g. If API fails: toast error + retry button, checkmark reverts
11. Navigate between exercises via PREV/NEXT or session queue tap
12. Click "FINISH_WORKOUT" → confirmation dialog (if incomplete sets exist)
13. Redirect to workout summary view (analytics page with session-specific data)

### Flow 3: Analytics Review

1. Navigate to `/analytics` via sidebar
2. Default view: 3M time range, most-trained exercise 1RM chart
3. Change time range by clicking pills
4. Chart updates with loading skeleton during API call
5. Scroll down: stat tiles show period-specific aggregates
6. Muscle Stimulus section shows volume distribution
7. Recommendation cards show plain-language suggestions

### Flow 4: API Failure During Logging

1. User is on `/logger`, mid-session
2. Taps checkmark to save a set
3. `POST /api/workout-sets` fails (network error or 500)
4. Checkmark reverts to empty state
5. Error toast appears: "Failed to save set" + Retry button
6. Retry taps → re-attempt POST
7. If retry succeeds: success toast, checkmark restored
8. If retry fails again: error toast persists, "Retry" remains available
9. Offline banner appears at top if network is down
10. All subsequent saves are queued locally
11. On reconnect: banner changes to "Connection restored — syncing data..."
12. Queued sets are auto-posted sequentially
13. Success toast: "All pending sets synced"

### Flow 5: Interrupted Workout Recovery

1. User is logging a workout, has completed 3 of 5 exercises
2. App closes (browser tab close, phone lock, accidental navigation)
3. Workout session state is persisted in `localStorage`:
   - `sessionId`, `currentExerciseIndex`, `completedSets[]`, `elapsedTimeMs`
4. User returns to app, navigates to `/` or `/logger`
5. Recovery banner appears at top: "Resume your unfinished workout from 2 hours ago?"
   - Resume (primary) → Navigate to `/logger` with restored state
   - Discard (secondary) → Clear localStorage, dismiss banner
6. On resume: Logger loads at the exercise where user left off
7. Elapsed time continues from saved value
8. Previously saved sets show as completed (synced with server)

---

## 9. Motion and Interaction Spec

### Transition Durations

| Interaction | Duration | Easing |
|---|---|---|
| Page navigation | 200ms | ease-in-out |
| Modal open | 200ms | ease-out |
| Modal close | 150ms | ease-in |
| Toast enter | 300ms | ease-out (slide right) |
| Toast exit | 200ms | ease-in (fade) |
| Hover state | 150ms | ease |
| Focus ring | 0ms (instant) | — |
| Button press | 100ms | ease |
| Skeleton shimmer | 1500ms | ease-in-out (infinite) |
| Chart data load | 400ms | ease-out |
| Tab/pill switch | 150ms | ease |
| Sidebar nav highlight | 150ms | ease |
| Banner slide down | 300ms | ease-out |
| Banner dismiss | 200ms | ease-in |

### Hover Behaviors

- Buttons: Background lightens 10%
- Cards: Background shifts to `#27272D`, cursor pointer
- Table rows: Background `#27272D`
- Links: Underline appears
- Nav items: Background `#27272D`

### Focus Behaviors

- All interactive elements: 2px outline `#CCFF00`, 2px offset
- Inputs: Border changes to `#CCFF00`
- Focus visible only on keyboard navigation (`:focus-visible`), not mouse

### Reduced Motion

- `@media (prefers-reduced-motion: reduce)`:
  - All transitions: 0ms
  - Skeleton shimmer: Static `#27272D` (no animation)
  - Chart animations: Instant render
  - Toast: Instant appear/disappear
  - Modal: Instant show/hide (no scale)

---

## 10. Accessibility Compliance Checklist

### Contrast (WCAG 2.2 AA)

| Pair | Foreground | Background | Ratio | Pass? |
|---|---|---|---|---|
| Primary text on bg | `#FFFFFF` | `#141416` | 17.4:1 | Yes |
| Secondary text on bg | `#9E9EA8` | `#141416` | 5.8:1 | Yes |
| Tertiary text on bg | `#5F5F69` | `#141416` | 3.2:1 | No — decorative only |
| Accent on bg | `#CCFF00` | `#141416` | 12.8:1 | Yes |
| Data cyan on bg | `#00E5FF` | `#141416` | 10.2:1 | Yes |
| Error on bg | `#FF3B30` | `#141416` | 4.6:1 | Yes (AA) |
| Button text on accent | `#000000` | `#CCFF00` | 16.2:1 | Yes |

### Keyboard Navigation

- Tab order follows visual reading order (left-to-right, top-to-bottom)
- Sidebar: Arrow keys navigate items, Enter activates
- Modals: Tab trapped inside, Esc closes
- Tables: Arrow keys navigate cells
- RPE selector: Arrow left/right to change value
- Time range pills: Arrow left/right to navigate, Enter to select
- All interactive elements reachable by Tab
- Skip-to-content link as first focusable element

### ARIA Requirements

| Element | ARIA |
|---|---|
| Sidebar nav | `<nav aria-label="Main navigation">` |
| Bottom tab bar | `<nav aria-label="Main navigation">` |
| Modal | `role="dialog" aria-modal="true" aria-labelledby={titleId}` |
| Toast | `role="alert" aria-live="polite"` (success/warning), `aria-live="assertive"` (error) |
| Skeleton loader | `aria-busy="true" aria-label="Loading"` |
| Chart | `role="img" aria-label="[chart description]"` + hidden data table |
| Progress bar | `role="progressbar" aria-valuenow={value} aria-valuemin={0} aria-valuemax={100}` |
| RPE selector | `role="radiogroup" aria-label="Rate of Perceived Exertion"` with `role="radio"` per button |
| Filter chips | `role="checkbox"` per chip, `role="group" aria-label="Muscle group filters"` |
| Password toggle | `aria-label="Show password"` / `"Hide password"` |

### Touch Targets

- Minimum: 44x44px for all interactive elements
- Workout Logger inputs: 56px height on mobile (gym gloves accommodation)
- RPE buttons: 36px desktop, 44px mobile
- Bottom tab items: Full tab width, 56px height

### Charts

- All charts include a visually hidden `<table>` with equivalent data
- Trend indicators use text ("+12%") alongside color
- Data points on chart use shape markers (circles) in addition to line color
- Muscle stimulus bars include percentage text labels

---

## 11. Asset Export Manifest

### Icons

| Icon | Usage | Format | Size |
|---|---|---|---|
| NEURAL.FIT logo | Brand lockup, sidebar header | SVG | 24x24 |
| Dashboard | Sidebar/tab nav | SVG | 20x20 |
| Exercises (dumbbell) | Sidebar/tab nav | SVG | 20x20 |
| Plans (clipboard) | Sidebar/tab nav | SVG | 20x20 |
| Analytics (chart) | Sidebar/tab nav | SVG | 20x20 |
| Settings (gear) | Sidebar/tab nav | SVG | 20x20 |
| Profile (user) | Tab nav, avatar placeholder | SVG | 20x20 |
| Logout (arrow-right-from-bracket) | Sidebar footer, topbar | SVG | 20x20 |
| Search | Search bar | SVG | 16x16 |
| Eye / Eye-off | Password toggle | SVG | 20x20 |
| Checkmark | Set completion, success | SVG | 16x16 |
| X / Close | Modal close, error | SVG | 16x16 |
| Shuffle | Generator shuffle button | SVG | 16x16 |
| ChevronLeft/Right | Exercise navigation | SVG | 16x16 |
| Warning triangle | Warnings, destructive confirmations | SVG | 20x20 |
| Info circle | Tooltips, recommendations | SVG | 16x16 |

**Format:** SVG, stroke-based, `currentColor` for fill/stroke to inherit theme colors.
**Naming:** `icon-{name}.svg` (kebab-case)

### Images

| Image | Usage | Format | Notes |
|---|---|---|---|
| Default avatar | Profile, sidebar | PNG @2x | 160x160px, circular crop |
| Empty state illustration | Empty lists/analytics | SVG | 128x128px, monochrome `#5F5F69` |

---

## 12. Engineering QA Checklist

### Pixel Parity Checks
- [ ] Sidebar width exactly 260px
- [ ] Topbar height exactly 56px
- [ ] Auth card width exactly 440px
- [ ] All button heights match spec (48/40/34px)
- [ ] Input heights exactly 48px
- [ ] Card border radius matches spec (12/16px)
- [ ] Bottom tab bar exactly 56px on mobile
- [ ] Typography sizes match token table exactly

### State Coverage Checks
- [ ] Every screen has loading skeleton
- [ ] Every screen has empty state with CTA
- [ ] Every API-dependent widget has error state
- [ ] Every form has validation error display
- [ ] Every destructive action has confirmation dialog
- [ ] Toast appears for success/error on all mutations
- [ ] Offline banner appears when network drops
- [ ] Session timeout dialog appears at JWT expiry warning

### Responsive Checks
- [ ] Desktop (1440px+): sidebar layout
- [ ] Tablet (768-1439px): sidebar collapses to icons or overlay (see Missing Decisions)
- [ ] Mobile (<768px): bottom tab nav, single-column
- [ ] Workout logger usable one-handed on 375px width
- [ ] No horizontal scroll on any breakpoint
- [ ] Sticky CTAs remain reachable on all viewports

### Accessibility Checks
- [ ] All interactive elements reachable by keyboard
- [ ] Focus visible ring on all interactive elements (keyboard only)
- [ ] Modal focus trapping works
- [ ] Skip-to-content link present
- [ ] All images have alt text
- [ ] Charts have hidden data tables
- [ ] Color is never sole status indicator
- [ ] Touch targets >= 44px
- [ ] `prefers-reduced-motion` respected

### Interaction Checks
- [ ] Optimistic set save with revert on failure
- [ ] Auto-save indicators in logger
- [ ] Recovery banner on interrupted session
- [ ] Filter chips toggle correctly
- [ ] Time range pills update charts
- [ ] Password show/hide toggle works
- [ ] RPE selector only allows single selection
- [ ] Toast auto-dismisses per variant timing

### Error State Checks
- [ ] Network failure during set save shows error toast + retry
- [ ] Invalid login shows field-level errors
- [ ] Invalid registration shows per-field validation
- [ ] 404 route shows not-found page
- [ ] Generic API error shows dialog with retry option
- [ ] Offline state detected and communicated

---

## 13. Missing Decisions

| Item | Why It Matters | Recommended Default | Risk If Not Decided |
|---|---|---|---|
| Light theme tokens | User may prefer light mode; OS preference detection | Ship dark-only for v1, light theme in v1.1 | Low — dark-only is viable for gym context |
| Tablet breakpoint behavior (768-1439px) | Sidebar at 260px eats too much space on small desktops | Collapsible sidebar with icon-only mode at <1024px | Medium — layout breaks on iPad landscape |
| Exercise detail view | Currently no dedicated screen for single exercise (backend supports `GET /exercises/{id}`) | Modal overlay from exercise card click showing full EMG data, history, and recommendations | Medium — users can't see exercise detail |
| Chart library | No chart library specified for 1RM timeline, volume trends, stimulus bars | Recharts (React-native, responsive, accessible) | Low — any chart lib works with the data |
| Workout plan CRUD UI | Backend supports `POST/PUT/DELETE /exercises` and plan management but no create/edit UI exists | Add plan/exercise creation forms in v1.1 | Medium — users can only use pre-seeded data |
| Real-time PR predictor UI | Backend has `GET /set/predict` but no screen for it | Show inline prediction tooltip in workout logger as user enters weight/reps | Low — nice-to-have feature |
| Data export format | Settings has "export data" but format undefined | JSON export of all user workout history | Low — can decide at implementation |
| Password recovery | No backend endpoint exists for password reset | Show "Contact administrator" for v1 | Low — single-user app for now |
| Session storage vs localStorage for JWT | Security implications for token storage | `localStorage` with HttpOnly cookie migration in v1.1 | Medium — localStorage is XSS-vulnerable |
| Error boundary strategy | What happens on unhandled React errors | React Error Boundary wrapping each route with fallback UI + "Return to Dashboard" link | Low — rare edge case |
| Animation library | No library specified for transitions | CSS transitions + `@keyframes` for v1, Framer Motion for complex interactions in v1.1 | Low — CSS handles all current specs |
| Notification sound/haptics | Gym users may want audio/haptic feedback for set completion | None for v1 — add as setting in v1.1 | Low — purely additive feature |

---

## 14. Blocking Gaps

| Item | Why It Blocks |
|---|---|
| No backend endpoint for user profile data | Profile screen requires aggregated stats (total workouts, volume, streak) that aren't served by any single endpoint. Either create `GET /api/user/profile` or aggregate client-side from `GET /api/workout/history`. |
| No backend endpoint for settings persistence | Settings changes (units, rest timer, theme preference) have no backend storage. Either create `GET/PUT /api/user/settings` or store in `localStorage`. |
| No workout session creation endpoint documented | Logger requires creating a session before logging sets, but no `POST /api/workout` for session creation is documented. Verify if this exists or needs to be built. |
| Font licensing | Unbounded (Google Fonts, OFL — free), Geist + Geist Mono (Vercel, open source — free). Confirm both are bundled or loaded via CDN. No licensing blockers expected but must be verified. |

---

## 15. Responsive Breakpoints

| Breakpoint | Width | Layout |
|---|---|---|
| Mobile | < 768px | Bottom tab nav, single column, cards full-width |
| Tablet | 768px — 1023px | **Decision needed** — suggest icon-only sidebar (64px) |
| Desktop sm | 1024px — 1439px | Full sidebar (260px), 2-column content |
| Desktop lg | >= 1440px | Full sidebar (260px), 2-3 column content, max-width containers |

### Per-Screen Adaptation Rules

| Screen | Mobile | Desktop |
|---|---|---|
| Login/Register | Card fills width minus 40px margin | Card centered, 440px fixed width |
| Dashboard | Single column cards, horizontal scroll quick actions | 2-column layout (60/40) |
| Exercises | Single column card list | 4-column card grid |
| Plans | Single column cards | 3-column card grid |
| Plan Detail | Full-width day cards | Full-width day cards (same, max-width 1000px) |
| Generator | Card-per-exercise | Table layout |
| Logger | Card-per-set, large inputs (56px), sticky bottom CTA | Table layout, side panels for metrics + queue |
| Analytics | Full-width chart, 2x2 stat grid | Chart + side stat tiles, 2-column below |
| Settings | Single column sections | 2-column (60/40) |
| Profile | Single column, 2x2 stats | Full-width header, 2-column below |

---

## 16. Implementation Readiness Score

**Score: 78 / 100**

### Deductions:
- -5: No light theme tokens
- -5: Tablet breakpoint behavior undefined
- -4: Exercise detail view missing
- -3: Profile/settings backend endpoints unconfirmed
- -2: Chart library not specified
- -2: Plan/exercise CRUD UI missing
- -1: No animation library decision

### Top 10 Risks to Fidelity

1. **Workout Logger mobile usability** — This is the most-used screen in a gym. If inputs are too small or layout requires two hands, users abandon the app. Test with actual gym gloves.
2. **Chart implementation** — 1RM chart and stimulus bars need a chart library. Recharts is recommended. If a different library is chosen, axis labeling, tooltip styling, and responsive behavior will differ.
3. **Optimistic UI for set saves** — If not implemented, every set save will feel laggy in the gym. The checkmark must appear instantly.
4. **Font loading** — Three font families (Unbounded, Geist, Geist Mono) must load before first paint. Use `font-display: swap` and preload critical weights.
5. **Session recovery** — LocalStorage persistence for in-progress workouts is critical. Without it, closing the browser tab = lost workout data.
6. **JWT refresh timing** — Session timeout dialog must appear ~5min before expiry. If the refresh endpoint fails, the user must re-login without losing in-progress data.
7. **Offline handling** — Network detection and local queuing of workout sets is essential for gym environments with poor connectivity.
8. **Skeleton loaders** — Every API-dependent section needs a skeleton. Missing skeletons create jarring layout shifts.
9. **Responsive grid breakpoints** — Exercise (4→1 column) and plan (3→1 column) grids must transition smoothly. Test at 768px boundary.
10. **Color contrast on tertiary text** — `#5F5F69` on `#141416` fails AA (3.2:1). Use only for truly decorative text — never for information-carrying content.

### Immediate Next Actions for Design Team

1. **Design exercise detail modal/screen** — The backend supports it; users need it.
2. **Define tablet breakpoint** — Test sidebar behavior at 768-1024px range.
3. **Create light theme token set** — Map all dark tokens to light equivalents.
4. **Design plan/exercise CRUD forms** — Create, edit, delete flows for exercises and plans.
5. **Add real-time PR predictor UI** — The backend has it; surface it in the logger.
