# Component Patterns — Copilot Reference

> **Purpose**: Defines the exact structure and styling for every reusable component. When building or modifying any UI element, follow these patterns exactly.

---

## 1. Layout Shell

The app uses a sidebar + main content layout on desktop, bottom tab bar on mobile.

```
┌──────────────────────────────────────────────────┐
│ Sidebar (260px)  │  Top Bar (56px height)        │
│                  │───────────────────────────────│
│  Logo            │                               │
│  Nav items       │  Main Content Area             │
│  (vertical)      │  (scrollable)                  │
│                  │                               │
│                  │                               │
│  Bottom:         │                               │
│  User avatar     │                               │
│  Settings link   │                               │
└──────────────────────────────────────────────────┘
```

### Sidebar Specs
- Width: `var(--sidebar-width)` = 260px
- Background: `var(--color-bg-secondary)` (#141416)
- Border right: `1px solid var(--color-border-default)`
- Padding: `var(--space-9)` (24px) vertical, `var(--space-7)` (16px) horizontal
- Logo: Use `--font-display` at 20px weight 900, color `var(--color-text-primary)`
- Nav items: `--font-body` at 14px weight 500
  - Default: color `var(--color-text-secondary)`, no background
  - Hover: background `var(--color-bg-tertiary)`, color `var(--color-text-primary)`
  - Active: background `rgba(204, 255, 0, 0.08)`, color `var(--color-accent-primary)`, left border 2px `var(--color-accent-primary)`
  - Border radius: `var(--radius-default)` (8px)
  - Padding: `var(--space-4)` (10px) vertical, `var(--space-5)` (12px) horizontal
  - Gap between items: `var(--space-1)` (4px)

### Top Bar Specs
- Height: `var(--topbar-height)` = 56px
- Background: `var(--color-bg-primary)` with backdrop blur
- Border bottom: `1px solid var(--color-border-default)`
- Contains: Page title (left), action buttons (right)

### Mobile (< 768px)
- Sidebar becomes bottom tab bar, height `var(--bottom-tab-height)` = 56px
- Icons only, centered, with active indicator dot below
- Top bar remains but simplified

---

## 2. Cards

All cards use the same base then add variant-specific adjustments.

### Base Card
```css
.card {
  background: var(--color-bg-tertiary);       /* #1D1D21 */
  border: 1px solid var(--color-border-default); /* #2A2A32 */
  border-radius: var(--radius-lg);            /* 12px */
  padding: var(--space-8);                    /* 20px */
  transition: background var(--transition-default), border-color var(--transition-default);
}
.card:hover {
  background: var(--color-bg-quaternary);     /* #27272D */
}
```

### Stat Card (Dashboard)
For displaying numeric KPIs (plan count, exercise count, streak):
- Kicker label: `--font-mono` 11px weight 400, `--color-text-tertiary`, uppercase, letter-spacing 0.5px
- Value: `--font-display` 24px weight 900, `--color-text-primary`
- Accent bar: 2px left border in `--color-accent-primary` or `--color-accent-data`
- Optional trend indicator: small arrow + percentage in `--color-success` or `--color-error`

### Exercise Card
- Title: `--font-display` 16px weight 800
- Subtitle (equipment): `--font-mono` 11px, `--color-text-tertiary`
- Muscle group tags: pill badges with `--radius-full`, bg `var(--color-bg-quaternary)`, `--font-mono` 11px
- Padding: 20px

### Plan Card
- Title: `--font-display` 20px weight 800
- Description: `--font-body` 14px, `--color-text-secondary`
- Day count badge: `--font-mono` 11px bold, `--color-accent-primary` text
- Padding: 24px, radius: `var(--radius-xl)` (16px)
- Clickable: entire card is a link, hover lifts with subtle shadow

### Workout Set Row
For the workout logger — each set is a compact row:
- Background: `var(--color-bg-tertiary)`
- Grid: `set# | exercise | target | actual | status`
- Set number: `--font-mono` bold, `--color-accent-primary`
- Weight/rep values: `--font-mono` 14px
- Status indicator: green checkmark (`--color-success`) or dash
- Compact padding: `var(--space-5)` (12px)

---

## 3. Buttons

### Primary Button
```css
.btn-primary {
  background: var(--color-accent-primary);  /* #CCFF00 */
  color: #000000;
  border: none;
  font-family: var(--font-body);
  font-weight: 600;
  font-size: 14px;
  border-radius: var(--radius-default);     /* 8px */
  cursor: pointer;
  transition: opacity var(--transition-default);
}
.btn-primary:hover { opacity: 0.85; }
.btn-primary:active { transform: scale(0.98); }
```

### Secondary Button
```css
.btn-secondary {
  background: transparent;
  color: var(--color-text-primary);
  border: 1px solid var(--color-border-default);
  /* same font/radius/sizing as primary */
}
.btn-secondary:hover { background: var(--color-bg-tertiary); }
```

### Ghost Button
```css
.btn-ghost {
  background: transparent;
  color: var(--color-text-secondary);
  border: none;
}
.btn-ghost:hover { color: var(--color-text-primary); }
```

### Button Sizes
| Size | Height | Padding |
|------|--------|---------|
| Large | 48px | 16px 24px |
| Medium | 40px | 12px 20px |
| Small | 34px | 8px 16px |

---

## 4. Inputs

```css
.input {
  height: 48px;
  background: var(--color-bg-tertiary);
  border: 1px solid var(--color-border-default);
  border-radius: var(--radius-default);     /* 8px */
  padding: 12px 16px;
  color: var(--color-text-primary);
  font-family: var(--font-body);
  font-size: 14px;
  transition: border-color var(--transition-default);
}
.input:focus {
  outline: none;
  border-color: var(--color-border-accent); /* #CCFF00 */
}
.input-error {
  border-color: var(--color-border-error);  /* #FF3B30 */
}
```

### Input Labels
- Font: `--font-mono` 11px weight 400
- Color: `--color-text-secondary`
- Uppercase, letter-spacing 0.5px
- Margin bottom: `var(--space-2)` (6px)

---

## 5. Toasts / Notifications

- Width: 360px, positioned top-right (24px from edges)
- Background: `var(--color-bg-tertiary)`
- Border: `1px solid var(--color-border-default)`
- Left accent bar: 3px solid in semantic color (success/error/warning)
- Radius: `var(--radius-default)` (8px)
- Auto-dismiss: success 5s, warning 8s, error manual
- Animate in: slide from right + fade, 200ms ease-out

---

## 6. Modals

- Max width: 480px, centered
- Background: `var(--color-bg-tertiary)`
- Border: `1px solid var(--color-border-default)`
- Radius: `var(--radius-xl)` (16px)
- Padding: 32px
- Backdrop: `rgba(0, 0, 0, 0.6)` with backdrop-filter blur(8px)
- Animate in: scale(0.95) -> scale(1) + fade, 200ms ease-out

---

## 7. Empty States

When a page has no data (no plans, no exercises, no workout history):
- Centered vertically and horizontally in the content area
- Icon: 48px, `--color-text-tertiary` (use a relevant outline icon)
- Title: `--font-display` 16px weight 800, `--color-text-primary`
- Description: `--font-body` 14px, `--color-text-secondary`, max-width 320px, text-align center
- CTA button: Primary button below description
- No visible card border — just floating content

---

## 8. Loading States

Use skeleton loaders, not spinners:
- Skeleton shape matches the content it replaces
- Background: `var(--color-bg-tertiary)`
- Animated shimmer: linear gradient sweep, `--transition-skeleton` (1500ms)
- Radius matches the element it replaces
