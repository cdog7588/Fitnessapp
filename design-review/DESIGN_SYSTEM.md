# FitnessApp Design System — Copilot Reference

> **Purpose**: This document is the single source of truth for all visual decisions in the frontend. Every component, page, and layout must follow these rules. Do not invent new colors, fonts, spacing values, or component patterns — use what is defined here.

---

## 1. Token Usage — MANDATORY

The app already has a complete token system in two files:
- `frontend/src/styles/tokens.css` — CSS custom properties
- `frontend/src/styles/theme.ts` — TypeScript theme object

**RULE: Never hardcode colors, fonts, spacing, or radii.** Always reference tokens.

```tsx
// BAD — hardcoded values
<div style={{ background: '#07111f', color: '#94a3b8', padding: 16, borderRadius: 10 }}>

// GOOD — token references (CSS)
<div style={{ background: 'var(--color-bg-primary)', color: 'var(--color-text-secondary)', padding: 'var(--space-7)', borderRadius: 'var(--radius-default)' }}>

// GOOD — token references (TypeScript import)
import { colors, spacing, radius } from '../styles/theme';
<div style={{ background: colors.bg.primary, color: colors.text.secondary, padding: spacing[7], borderRadius: radius.default }}>
```

---

## 2. Color Palette

### Backgrounds (darkest to lightest)
| Token | Hex | Usage |
|-------|-----|-------|
| `--color-bg-primary` | `#09090B` | Page background, main canvas |
| `--color-bg-secondary` | `#141416` | Sidebar, secondary panels |
| `--color-bg-tertiary` | `#1D1D21` | Cards, inputs, modals |
| `--color-bg-quaternary` | `#27272D` | Hover states on cards/buttons |

### Text
| Token | Hex | Usage |
|-------|-----|-------|
| `--color-text-primary` | `#FFFFFF` | Headings, primary content |
| `--color-text-secondary` | `#9E9EA8` | Descriptions, labels, secondary info |
| `--color-text-tertiary` | `#5F5F69` | Disabled text, timestamps, hints |

### Accent & Semantic
| Token | Hex | Usage |
|-------|-----|-------|
| `--color-accent-primary` | `#CCFF00` | Primary buttons, active nav, focus rings, key metrics |
| `--color-accent-data` | `#00E5FF` | Charts, data visualizations, graph lines |
| `--color-error` | `#FF3B30` | Error states, destructive actions |
| `--color-success` | `#34C759` | Success toasts, completed sets |
| `--color-warning` | `#FFD60A` | Warnings, plateau alerts |

### Border
| Token | Hex | Usage |
|-------|-----|-------|
| `--color-border-default` | `#2A2A32` | Card borders, dividers, input borders |
| `--color-border-accent` | `#CCFF00` | Focused inputs, active/selected borders |

**IMPORTANT**: The old blue palette (`#07111f`, `#0f172a`, `#38bdf8`, `#7dd3fc`, `#94a3b8`, `#334155`) must be fully replaced. Those colors do not exist in the design system.

---

## 3. Typography

### Font Families
| Token | Font | Usage |
|-------|------|-------|
| `--font-display` | Unbounded | Page titles, section headings, display numbers |
| `--font-body` | Geist | Body text, descriptions, labels, buttons |
| `--font-mono` | Geist Mono | Data values, set numbers, weights, reps, input labels |

**Load these fonts** via Google Fonts or local files. Add to `index.html`:
```html
<link rel="preconnect" href="https://fonts.googleapis.com">
<link href="https://fonts.googleapis.com/css2?family=Unbounded:wght@800;900&family=Geist:wght@400;500;600;700&display=swap" rel="stylesheet">
```

For Geist and Geist Mono, install via npm:
```bash
npm install geist
```
Then import in `main.tsx`:
```tsx
import 'geist/font/geist-sans.css';
import 'geist/font/geist-mono.css';
```

### Type Scale
| Style | Font | Size | Weight | Usage |
|-------|------|------|--------|-------|
| Display | Unbounded | 24px | 900 | Page titles ("Training Dashboard") |
| Heading LG | Unbounded | 20px | 800 | Section headings |
| Heading MD | Unbounded | 16px | 800 | Card titles |
| Heading SM | Unbounded | 14px | 800 | Sub-section titles |
| Heading XS | Unbounded | 12px | 800 | Uppercase labels/kickers |
| Body MD | Geist | 14px | 500 | Default body text |
| Body SM | Geist | 13px | 400 | Secondary descriptions |
| Body Semibold | Geist | 14px | 600 | Emphasized body (button text) |
| Mono MD | Geist Mono | 14px | 400 | Data values, weights, reps |
| Mono XS | Geist Mono | 11px | 400 | Input labels, small data |
| Mono Bold | Geist Mono | 14px | 700 | Highlighted data values |

---

## 4. Spacing Scale

Use the spacing tokens — never arbitrary pixel values:

| Token | Value | Common Usage |
|-------|-------|--------------|
| `--space-1` | 4px | Tight gaps (icon-to-text) |
| `--space-3` | 8px | Inline padding, small gaps |
| `--space-5` | 12px | Input internal padding |
| `--space-7` | 16px | Standard card padding, nav gaps |
| `--space-8` | 20px | Card content padding |
| `--space-9` | 24px | Section spacing, page padding |
| `--space-11` | 32px | Large section gaps, modal padding |
| `--space-12` | 40px | Page section separators |
| `--space-13` | 64px | Major layout breathing room |

---

## 5. Border Radius

| Token | Value | Usage |
|-------|-------|---------|
| `--radius-sm` | 4px | Small pills, tags |
| `--radius-md` | 6px | Small buttons |
| `--radius-default` | 8px | Inputs, standard buttons, toasts |
| `--radius-lg` | 12px | Cards, exercise tiles, stat cards |
| `--radius-xl` | 16px | Large cards, modals, plan cards |
| `--radius-full` | 9999px | Circular avatars, pill badges |
