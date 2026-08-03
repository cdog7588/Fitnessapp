# Page-by-Page Design Specs — Copilot Reference

> **Purpose**: Exact layout and content specs for every page. When rebuilding or modifying a page, follow these specs precisely.

---

## 1. Login Page (`LoginPage.tsx`)

### Layout
- Full viewport height, centered both axes
- Background: `var(--color-bg-primary)` (#09090B) — no gradients
- Auth card: width 440px, padding 32px, radius `var(--radius-xl)` (16px)
- Card background: `var(--color-bg-tertiary)`
- Card border: `1px solid var(--color-border-default)`

### Content
- App logo or wordmark at top of card: "FitnessApp" in `--font-display` 20px weight 900
- Heading: "Welcome back" — `--font-display` 24px weight 900
- Subtext: "Sign in to continue training" — `--font-body` 14px, `--color-text-secondary`
- Username input with mono label "USERNAME"
- Password input with mono label "PASSWORD"
- Error message: `--font-body` 13px, `--color-error`
- Submit button: Primary (lime), full width, height 48px, text "Sign In"
- Footer: "No account?" + link "Create one" in `--color-accent-primary`

---

## 2. Register Page (`RegisterPage.tsx`)

Same layout as Login, with:
- Heading: "Create account"
- Subtext: "Start tracking your strength gains"
- Username + Password + Confirm Password fields
- Submit: "Create Account"
- Footer: "Already have an account?" + link "Sign in"

---

## 3. Dashboard Page (`DashboardPage.tsx`)

### Header Section
- Greeting: "Good [morning/afternoon/evening], [username]" — `--font-display` 24px
- Subtext: "Here's your training overview" — `--font-body` 14px, `--color-text-secondary`

### Stat Cards Row (3-4 cards in a grid)
Each stat card:
- Kicker: uppercase mono label (e.g., "ACTIVE PLANS", "EXERCISES", "THIS WEEK", "STREAK")
- Value: large display number in `--font-display` 24px weight 900
- Accent: left border in `--color-accent-primary` or `--color-accent-data`

### Quick Actions Section
- Section heading: "Quick actions" — `--font-display` 16px
- Row of action cards:
  - "Start Workout" — primary accent card (subtle lime bg tint)
  - "Browse Plans" — standard card
  - "View Analytics" — standard card
- Each has: icon (24px), title (`--font-display` 14px), description (`--font-body` 13px, secondary)

### Recent Activity Section
- Section heading: "Recent sessions" — `--font-display` 16px
- List of recent workout sessions with: date (mono), plan name, duration, total volume
- Empty state if no sessions yet

---

## 4. Exercise Library (`ExercisesPage.tsx`)

### Header
- Title: "Exercise Library" — `--font-display` 24px
- Search input: full width with search icon, placeholder "Search exercises..."
- Filter pills below search: muscle group filters ("All", "Chest", "Back", "Legs", etc.) — pill badges, horizontally scrollable

### Grid
- 3-column grid on desktop (minmax 280px), 2 on tablet, 1 on mobile
- Gap: `var(--space-7)` (16px)

### Exercise Card Content
- Exercise name: `--font-display` 16px weight 800
- Equipment type: `--font-mono` 11px, `--color-text-tertiary`
- Primary muscles: pill badges in a row
  - Badge: `--radius-full`, bg `var(--color-bg-quaternary)`, `--font-mono` 11px, `--color-text-secondary`
- Description (if available): `--font-body` 13px, `--color-text-secondary`, max 2 lines with ellipsis

---

## 5. Plans Page (`PlansPage.tsx`)

### Header
- Title: "Workout Plans" — `--font-display` 24px
- Subtitle: "Choose a training split" — `--font-body` 14px, `--color-text-secondary`

### Plan Cards (vertical stack, full width)
Each plan card:
- Padding: 24px, radius: `var(--radius-xl)` (16px)
- Layout: flex row, space-between
- Left side:
  - Plan name: `--font-display` 20px weight 800
  - Description: `--font-body` 14px, `--color-text-secondary`
  - Day count: `--font-mono` 11px bold, `--color-accent-primary` — e.g., "6 DAYS/WEEK"
- Right side:
  - Arrow icon or "View" link in `--color-accent-primary`
- Hover: background shifts to `var(--color-bg-quaternary)`, cursor pointer
- Entire card is clickable (Link wrapper)

---

## 6. Plan Detail Page (`PlanDetailPage.tsx`)

### Header
- Back link: "← Plans" — `--font-body` 14px, `--color-text-secondary`
- Plan name: `--font-display` 24px
- Plan description: `--font-body` 14px, `--color-text-secondary`

### Day Cards (vertical stack)
Each day card:
- Day label: "Day 1" — `--font-display` 16px weight 800
- Muscle groups targeted: pill badges
- "Generate Workout" button: Secondary button, right-aligned
- Padding: 20px 24px, radius: `var(--radius-lg)` (12px)

---

## 7. Workout Generator (`GeneratorPage.tsx`)

### Header
- Title: "Today's Workout" — `--font-display` 24px
- Plan + day context: `--font-mono` 11px, `--color-text-tertiary`

### Exercise List
Each generated exercise:
- Exercise name: `--font-display` 16px weight 800
- Sets x Reps: `--font-mono` 14px bold, `--color-accent-primary` — e.g., "4 × 8"
- Recommended weight: `--font-mono` 14px, `--color-accent-data` — e.g., "185 lbs"
- Card with subtle left accent bar in `--color-accent-primary`

### Action
- "Start Logging" button: Primary, full width at bottom

---

## 8. Workout Logger (`WorkoutLoggerPage.tsx`)

### Header
- Title: "Log Workout" — `--font-display` 24px
- Session info: date + plan name in `--font-mono` 11px, `--color-text-tertiary`

### Exercise Sections
Group sets by exercise. Each exercise group:
- Exercise name header: `--font-display` 16px weight 800
- Set rows in a compact table-like layout:

```
┌─────┬────────────┬────────────┬────────────┬────────────┬────────┐
│ SET │ TARGET WT  │ TARGET REP │ ACTUAL WT  │ ACTUAL REP │ STATUS │
├─────┼────────────┼────────────┼────────────┼────────────┼────────┤
│  1  │   185      │     8      │  [input]   │  [input]   │   ✓    │
│  2  │   185      │     8      │  [input]   │  [input]   │   —    │
└─────┴────────────┴────────────┴────────────┴────────────┴────────┘
```

- Column headers: `--font-mono` 11px, uppercase, `--color-text-tertiary`
- Set number: `--font-mono` bold, `--color-accent-primary`
- Target values: `--font-mono` 14px, `--color-text-secondary`
- Actual inputs: compact (height 36px), `--font-mono` 14px
- Status: checkmark icon in `--color-success` when completed

### Actions
- "Save Session" button: Primary, fixed at bottom or sticky
- "Add Set" button: Ghost button below each exercise

---

## 9. Analytics Page (`AnalyticsPage.tsx`)

### Header
- Title: "Analytics" — `--font-display` 24px
- Period selector: pill group ("1W", "1M", "3M", "ALL") — active pill has `--color-accent-primary` bg with black text

### Stat Summary Row (3 cards)
- Total volume this period
- Sessions completed
- Estimated 1RM best
- Same stat card pattern as Dashboard

### Strength Progression Chart
- Section heading: "1RM Progression" — `--font-display` 16px
- Exercise selector dropdown above chart
- Line chart: `--color-accent-data` (#00E5FF) for the line
- Grid lines: `--color-border-default`
- Axis labels: `--font-mono` 11px, `--color-text-tertiary`
- Chart background: `var(--color-bg-tertiary)` card
- Use a charting library (recharts recommended for React)

### Recommendations Section
- Section heading: "Recommendations" — `--font-display` 16px
- Recommendation cards with:
  - Exercise name: `--font-display` 14px weight 800
  - Suggestion text: `--font-body` 14px, `--color-text-secondary`
  - Recommended weight badge: `--font-mono` bold, `--color-accent-primary`

---

## General Page Rules

1. **Page padding**: `var(--space-9)` (24px) on all sides within the main content area
2. **Section spacing**: `var(--space-12)` (40px) between major sections
3. **Content max-width**: 1100px, centered
4. **All headings**: Use `--font-display` (Unbounded), never `--font-body` for headings
5. **All data values**: Use `--font-mono` (Geist Mono) for numbers, weights, reps, dates
6. **All descriptions**: Use `--font-body` (Geist) in `--color-text-secondary`
7. **Empty states**: Centered content with icon + title + description + CTA (see COMPONENT_PATTERNS.md)
8. **Loading states**: Skeleton loaders matching content shapes (see COMPONENT_PATTERNS.md)
