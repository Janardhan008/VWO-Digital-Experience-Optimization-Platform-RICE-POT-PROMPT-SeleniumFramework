# Non-Functional — Accessibility (WCAG 2.1 AA) — Test Cases

**File**: `nfr/TC-NFR-Accessibility.md` | **Module**: Accessibility | **Total**: 5 TC | **Automated**: 0

---

### TC-ACC-01 • All Images Have Alt Text

| Field | Value |
|-------|-------|
| **Priority** | P1 | **NFR** | WCAG 2.1 AA | **Auto** | ❌ |
| **Criteria** | Every `<img>` element has a non-empty `alt` attribute. Decorative images have `alt=""`. |
| **Tool** | axe-core / Lighthouse |

### TC-ACC-02 • Color Contrast Ratio ≥ 4.5:1

| Field | Value |
|-------|-------|
| **Priority** | P1 | **NFR** | WCAG 2.1 AA | **Auto** | ❌ |
| **Criteria** | All text (including placeholder text) has contrast ratio ≥ 4.5:1 against background. Large text (≥ 18px bold or ≥ 24px) has ≥ 3:1. |
| **Tool** | axe-core / Colour Contrast Analyser |

### TC-ACC-03 • Keyboard Navigation

| Field | Value |
|-------|-------|
| **Priority** | P0 | **NFR** | WCAG 2.1 AA | **Auto** | ❌ |
| **Criteria** | All interactive elements reachable via `Tab` key. Visible focus indicator (outline/ring). No keyboard traps. Logical tab order (left→right, top→bottom). |
| **Steps** | 1. Tab through entire login flow → 2. Tab through experiment creation flow → 3. Tab through reports |
| **Expected** | Every button, link, input, dropdown, and toggle is reachable and actionable via keyboard alone. |

### TC-ACC-04 • Form Labels Associated with Inputs

| Field | Value |
|-------|-------|
| **Priority** | P1 | **NFR** | WCAG 2.1 AA | **Auto** | ❌ |
| **Criteria** | Every `<input>`, `<select>`, `<textarea>` has an associated `<label>` (via `for` attribute or wrapping). No placeholder-only labeling. |
| **Tool** | axe-core |

### TC-ACC-05 • ARIA Landmarks Present

| Field | Value |
|-------|-------|
| **Priority** | P1 | **NFR** | WCAG 2.1 AA | **Auto** | ❌ |
| **Criteria** | Page has: `<header role="banner">`, `<nav role="navigation">`, `<main role="main">`, `<footer role="contentinfo">`. Dynamic regions have `aria-live` attributes. |
| **Tool** | axe-core |
