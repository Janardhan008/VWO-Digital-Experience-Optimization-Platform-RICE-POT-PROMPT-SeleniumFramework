# Multivariate Testing — Test Cases

**File**: `TC-MVT-Multivariate.md` | **Module**: MVT | **Total**: 5 TC | **Automated**: 3

---

### TC-MVT-001 • Create MVT with 4+ Combination Variations

| Field | Value |
|-------|-------|
| **Priority** | P0 | **FR ID** | FR1 | **Auto** | ✅ `CreateMVTestTest#testCreateMultivariateTest` |
| **Test Data** | 2 headlines × 2 CTA colors = 4 combinations |
| **Steps** | 1. Create MVT → 2. Name experiment → 3. Add 4 combination variations → 4. Select primary metric → 5. Save Draft |
| **Expected** | 5 total entries (control + 4 combinations). All combinations listed. |
| **Validation** | `experimentPage.getVariationCount() == 5` |

### TC-MVT-002 • Preview All MVT Combinations

| Field | Value |
|-------|-------|
| **Priority** | P1 | **FR ID** | FR1 | **Auto** | ✅ `CreateMVTestTest#testMVTCombinationPreview` |
| **Steps** | 1. Create MVT → 2. Add combinations → 3. Click "Preview" |
| **Expected** | Each combination renders correctly in preview. Switch between combos shows correct content. |

### TC-MVT-003 • Configure Multiple Goals for MVT

| Field | Value |
|-------|-------|
| **Priority** | P1 | **FR ID** | FR1 | **Auto** | ✅ `CreateMVTestTest#testMVTWithMultipleGoals` |
| **Steps** | 1. Create MVT → 2. Add primary metric + 2 secondary goals |
| **Expected** | Multiple goals configured. Results will show performance per goal per combination. |

### TC-MVT-004 • MVT Results Broken Down by Factor Interaction

| Field | Value |
|-------|-------|
| **Priority** | P2 | **FR ID** | FR2 | **Auto** | ❌ Manual |
| **Steps** | 1. Launch MVT → 2. Wait for data → 3. Open results |
| **Expected** | Results show main effects (per factor) AND interaction effects (combination). SmartStats calculates significance per factor. |

### TC-MVT-005 • MVT with > 8 Combinations

| Field | Value |
|-------|-------|
| **Priority** | P2 | **FR ID** | FR1 | **Auto** | ❌ Manual |
| **Test Data** | 3 factors × 3 levels = 9 combinations |
| **Steps** | 1. Create MVT with 9+ combinations → 2. Observe UI performance |
| **Expected** | UI handles large number of combinations without lag. All combinations previewable. |
