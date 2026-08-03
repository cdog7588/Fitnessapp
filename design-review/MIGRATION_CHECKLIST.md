# Migration Checklist — What Copilot Needs to Fix

> **Purpose**: Step-by-step list of what needs to change to bring the current codebase in line with the design system. Work through this list in order.

---

## Phase 1: Foundation (Do First)

### 1.1 — Fix Font Loading
- [ ] Remove Inter font reference from `styles.css`
- [ ] Add Unbounded via Google Fonts link in `index.html`
- [ ] Install `geist` npm package: `npm install geist`
- [ ] Import Geist fonts in `main.tsx`:
  ```tsx
  import 'geist/font/geist-sans.css';
  import 'geist/font/geist-mono.css';
  ```
- [ ] Update `styles.css` `:root` to use: `font-family: 'Geist', sans-serif;`

### 1.2 — Fix Base Colors in styles.css
- [ ] Change `color: #f5f7fb` → `color: var(--color-text-primary)` (white)
- [ ] Change `background: #07111f` → `background: var(--color-bg-primary)` (#09090B)
- [ ] Update `.card` class:
  - `background: #0f172a` → `var(--color-bg-tertiary)`
  - `border: 1px solid #1f2e46` → `1px solid var(--color-border-default)`
  - `border-radius: 16px` → `var(--radius-lg)`
  - Add `transition: background var(--transition-default)`
  - Add hover state: `background: var(--color-bg-quaternary)`
- [ ] Ensure `tokens.css` is imported in `main.tsx` or `styles.css`:
  ```tsx
  import './styles/tokens.css';
  ```

---

## Phase 2: Layout Shell

### 2.1 — Rebuild Layout.tsx
- [ ] Replace all inline styles with token references
- [ ] Sidebar background: `var(--color-bg-secondary)` (not `#07111f`)
- [ ] Sidebar width: `var(--sidebar-width)` (260px, not 220px)
- [ ] Border: `var(--color-border-default)` (not `#1f2e46`)
- [ ] App title "FitnessApp": use `--font-display` at 20px weight 900
- [ ] Nav items: follow active/hover/default states from COMPONENT_PATTERNS.md
  - Active state: lime accent background tint + left border, NOT just color change
  - Replace `#7dd3fc` active color with `var(--color-accent-primary)` (#CCFF00)
  - Replace `#cbd5e1` default color with `var(--color-text-secondary)`
- [ ] Add responsive breakpoint: sidebar → bottom tab bar below 768px

---

## Phase 3: Pages (Work Through Each)

### 3.1 — LoginPage.tsx
- [ ] Remove gradient background `linear-gradient(135deg, #07111f, #0f172a)` → solid `var(--color-bg-primary)`
- [ ] Card width: 440px (not 420px), padding 32px (not 24px)
- [ ] All text colors: replace `#94a3b8` → `var(--color-text-secondary)`
- [ ] Heading font: `--font-display` (Unbounded), not default sans-serif
- [ ] Input styles: use token-based inputs (see COMPONENT_PATTERNS.md)
  - Replace `border: 1px solid #334155` → `var(--color-border-default)`
  - Add focus state: `border-color: var(--color-border-accent)`
- [ ] Button: replace `background: #38bdf8` → `var(--color-accent-primary)` (#CCFF00)
  - Text color: `#000000` (black on lime)
- [ ] Error text: replace `#fda4af` → `var(--color-error)`
- [ ] Link color: replace `#7dd3fc` → `var(--color-accent-primary)`

### 3.2 — RegisterPage.tsx
- [ ] Same fixes as LoginPage
- [ ] Add confirm password field

### 3.3 — DashboardPage.tsx
- [ ] Heading: use `--font-display` (Unbounded) for "Training dashboard"
- [ ] Replace `color: '#94a3b8'` → `var(--color-text-secondary)`
- [ ] Replace `color: '#7dd3fc'` stat values → `var(--color-text-primary)` (white) or `var(--color-accent-primary)` (lime)
- [ ] Add stat card pattern: kicker label (mono uppercase) + large value + accent left bar
- [ ] Add quick actions section
- [ ] Add recent activity section (or empty state)

### 3.4 — ExercisesPage.tsx
- [ ] Same heading/color fixes
- [ ] Add search input at top
- [ ] Add muscle group filter pills
- [ ] Exercise cards: add equipment badge, muscle group pills
- [ ] Replace `color: '#7dd3fc'` → `var(--color-text-tertiary)` for equipment

### 3.5 — PlansPage.tsx
- [ ] Same heading/color fixes
- [ ] Plan cards: use `--radius-xl` (16px), padding 24px
- [ ] Add day count badge in `--color-accent-primary`
- [ ] Replace link color `#7dd3fc` → `var(--color-accent-primary)`
- [ ] Make entire card clickable

### 3.6 — WorkoutLoggerPage.tsx
- [ ] Replace bare inputs with styled inputs using tokens
- [ ] Add proper set table layout (see PAGE_SPECS.md)
- [ ] Set numbers in `--font-mono` bold, `--color-accent-primary`
- [ ] Add column headers in mono uppercase
- [ ] Add save button (Primary) and add-set button (Ghost)

### 3.7 — AnalyticsPage.tsx
- [ ] Same heading/color fixes
- [ ] Replace placeholder text with actual data-connected components
- [ ] Add period selector pills
- [ ] Replace `color: '#7dd3fc'` → appropriate token colors
- [ ] Prepare chart area for recharts integration

---

## Phase 4: Polish

- [ ] Add hover transitions to all interactive elements
- [ ] Add skeleton loading states to pages that fetch data
- [ ] Add empty states with centered icon + title + description + CTA
- [ ] Add toast notifications for actions (save workout, errors)
- [ ] Ensure all focus states use `--color-border-accent` (lime) outline
- [ ] Test responsive layout at 768px and 1024px breakpoints
- [ ] Remove all remaining hardcoded hex values — search codebase for `#` in style props

---

## Validation

After completing all phases, search the codebase for these strings that should no longer exist:
- `#07111f` — old background
- `#0f172a` — old card background
- `#38bdf8` — old blue accent
- `#7dd3fc` — old light blue
- `#94a3b8` — old secondary text
- `#334155` — old border
- `#1f2e46` — old border
- `#fda4af` — old error color
- `#cbd5e1` — old text color
- `Inter` — old font family

All of these should be replaced with CSS variable or theme.ts token references.
