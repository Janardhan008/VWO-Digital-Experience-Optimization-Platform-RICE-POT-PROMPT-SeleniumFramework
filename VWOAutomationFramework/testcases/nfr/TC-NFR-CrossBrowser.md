# Non-Functional — Cross-Browser & Responsive — Test Cases

**File**: `nfr/TC-NFR-CrossBrowser.md` | **Module**: Cross-Browser | **Total**: 6 TC | **Automated**: 0

---

### TC-CBR-01 • Login Flow Across All Browsers

| Field | Value |
|-------|-------|
| **Priority** | P0 | **NFR** | Cross-Browser | **Auto** | ❌ |
| **Browsers** | Chrome, Firefox, Edge, Safari |
| **Steps** | 1. Execute full login flow (TC-AUTH-001 through TC-AUTH-005) on each browser |
| **Expected** | Consistent behavior: same error messages, same UI layout, same page flow. No browser-specific rendering bugs. |

### TC-CBR-02 • A/B Test Creation Across Browsers

| Field | Value |
|-------|-------|
| **Priority** | P0 | **NFR** | Cross-Browser | **Auto** | ❌ |
| **Browsers** | Chrome, Firefox, Edge, Safari |
| **Steps** | 1. Execute TC-ABT-001 through TC-ABT-003 on each browser |
| **Expected** | Visual Editor loads correctly. Variation preview renders. Drag-and-drop works. |

### TC-CBR-03 • Heatmap Rendering Across Browsers

| Field | Value |
|-------|-------|
| **Priority** | P1 | **NFR** | Cross-Browser | **Auto** | ❌ |
| **Browsers** | Chrome, Firefox, Edge, Safari |
| **Expected** | Heatmap overlay renders identically. Color gradients match. Tooltips work. |

### TC-CBR-04 • Dashboard Layout at 1920×1080 (Desktop)

| Field | Value |
|-------|-------|
| **Priority** | P0 | **NFR** | Responsive | **Auto** | ❌ |
| **Steps** | 1. Resize browser to 1920×1080 → 2. Login → 3. Observe dashboard |
| **Expected** | Full layout: sidebar visible, experiment cards in grid, summary cards in row, no horizontal scroll. |

### TC-CBR-05 • Dashboard Layout at 768×1024 (Tablet)

| Field | Value |
|-------|-------|
| **Priority** | P1 | **NFR** | Responsive | **Auto** | ❌ |
| **Browsers** | Chrome, Safari |
| **Steps** | 1. Resize to 768×1024 → 2. Observe responsive breakpoints |
| **Expected** | Sidebar collapses to hamburger. Cards stack vertically. Touch targets ≥ 44px. No content cut off. |

### TC-CBR-06 • Dashboard Layout at 375×812 (Mobile)

| Field | Value |
|-------|-------|
| **Priority** | P1 | **NFR** | Responsive | **Auto** | ❌ |
| **Browsers** | Chrome, Safari (iPhone) |
| **Expected** | Single column layout. Experiment cards full width. CTAs still tappable. Font sizes readable (≥ 16px). |
