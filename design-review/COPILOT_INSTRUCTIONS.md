# Instructions for VS Code Copilot — FitnessApp Frontend Design

> **READ THIS FIRST.** This file tells you how to approach any UI work in this project. The design-review folder contains the complete visual specification. Follow it exactly.

---

## Rule 1: Always Use Design Tokens

This project has a complete design token system. Never invent colors, fonts, spacing, or radii.

**Token files:**
- `frontend/src/styles/tokens.css` — CSS custom properties
- `frontend/src/styles/theme.ts` — TypeScript theme constants

**Reference docs:**
- `design-review/DESIGN_SYSTEM.md` — Complete color, typography, spacing, and radius reference
- `design-review/COMPONENT_PATTERNS.md` — Component structure and styling specs
- `design-review/PAGE_SPECS.md` — Per-page layout and content specs
- `design-review/MIGRATION_CHECKLIST.md` — Step-by-step list of what to fix

---

## Rule 2: The Visual Identity

This is a **dark, high-contrast fitness app** with a bold, technical aesthetic:

- **Background**: Near-black (#09090B) with subtle surface elevation
- **Accent**: Electric lime (#CCFF00) — used sparingly for CTAs, active states, key data
- **Data accent**: Cyan (#00E5FF) — charts, analytics, data visualizations
- **Typography**: Unbounded (display/headings) + Geist (body) + Geist Mono (data/numbers)
- **Mood**: Precise, athletic, data-driven — like a high-end training computer

Do NOT use:
- Blue accents (#38bdf8, #7dd3fc) — these are from the old design
- Gradients on backgrounds
- Inter or system fonts for visible text
- Rounded corners larger than 16px (except pills at 9999px)
- Bright or saturated backgrounds

---

## Rule 3: Typography Hierarchy

Every text element falls into one of three font families:

| What | Font | Example |
|------|------|---------|
| Headings, titles, display | **Unbounded** (--font-display) | Page titles, card titles, section headings |
| Body text, descriptions, buttons | **Geist** (--font-body) | Descriptions, labels, button text, paragraphs |
| Data, numbers, values | **Geist Mono** (--font-mono) | Weights ("185 lbs"), reps ("4 × 8"), dates, input labels |

Always use uppercase + letter-spacing for small mono labels (input labels, kickers, column headers).

---

## Rule 4: Component Construction

When building any UI component, check `design-review/COMPONENT_PATTERNS.md` first. It has exact specs for:
- Cards (stat, exercise, plan, set row)
- Buttons (primary, secondary, ghost, icon)
- Inputs (with labels, focus states, error states)
- Toasts, modals, empty states, loading skeletons

Do not invent new component patterns. If a pattern isn't defined, use the closest existing one.

---

## Rule 5: Page Rebuilds

When modifying any page, check `design-review/PAGE_SPECS.md` for that page's exact layout spec. Follow the spec for:
- Content hierarchy (what headings, sections, cards appear)
- Data display format (what font, what color, what layout)
- Actions available (buttons, links, their styling)
- Empty and loading states

---

## Rule 6: Migration Priority

If asked to "fix the design" or "improve the UI" without specific instructions, follow `design-review/MIGRATION_CHECKLIST.md` in order:
1. Foundation (fonts + base colors)
2. Layout shell (sidebar + nav)
3. Pages (one by one)
4. Polish (transitions + loading + empty states)

---

## Rule 7: Never Do These Things

- Never use `style={{ color: '#94a3b8' }}` or any hardcoded hex — always use `var(--color-*)` or `colors.*`
- Never use `fontSize: 28` without specifying `fontFamily` — headings must be Unbounded
- Never make a button blue — primary buttons are lime (#CCFF00) with black text
- Never skip hover/focus states on interactive elements
- Never use `className="card"` without also having the card use token-based styles
- Never add a new color that isn't in `tokens.css`

---

## Quick Reference: Most-Used Tokens

```
Page background:      var(--color-bg-primary)       #09090B
Card background:      var(--color-bg-tertiary)      #1D1D21
Card hover:           var(--color-bg-quaternary)     #27272D
Primary text:         var(--color-text-primary)      #FFFFFF
Secondary text:       var(--color-text-secondary)    #9E9EA8
Muted text:           var(--color-text-tertiary)     #5F5F69
Card border:          var(--color-border-default)    #2A2A32
Primary accent:       var(--color-accent-primary)    #CCFF00
Data accent:          var(--color-accent-data)       #00E5FF
Default radius:       var(--radius-default)          8px
Card radius:          var(--radius-lg)               12px
Large card radius:    var(--radius-xl)               16px
Standard padding:     var(--space-7)                 16px
Card padding:         var(--space-8)                 20px
Section spacing:      var(--space-12)                40px
```
