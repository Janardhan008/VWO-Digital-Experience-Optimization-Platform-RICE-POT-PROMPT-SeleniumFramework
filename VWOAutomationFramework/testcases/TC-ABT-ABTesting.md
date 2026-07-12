# A/B Testing — Test Cases

**File**: `TC-ABT-ABTesting.md` | **Module**: Experiments | **Total**: 18 TC | **Automated**: 16

| Ref | Priority | Automated |
|:---:|:--------:|:---------:|
| FR1, FR2, FR3 | P0: 10, P1: 6, P2: 2 | ✅ 16 / ❌ 2 manual |

---

### TC-ABT-001 • Create A/B Test with Valid Data

| Field | Value |
|-------|-------|
| **Priority** | P0 | **FR ID** | FR1 | **Auto** | ✅ `CreateABTestTest#testCreateABTestWithValidData` |
| **Precondition** | Logged in, on Dashboard |
| **Test Data** | name="A/B Test - Homepage CTA Button", url="https://app.vwo.com/", hypothesis="Changing CTA button from blue to green will increase CTR by 15%" |
| **Steps** | 1. Click "Create Experiment" → "A/B Test" → 2. Fill name, description, URL, hypothesis → 3. Add variation "Green CTA Button" → 4. Select primary metric "Click-Through Rate" → 5. Add goal "Newsletter Sign-ups" → 6. Save Draft |
| **Expected** | Experiment saved successfully as "Draft". Success toast shown. Experiment appears in experiments list. |
| **Validation** | `experimentPage.isSuccessDisplayed() == true` |

### TC-ABT-002 • Create A/B Test Without Name – Validation Error

| Field | Value |
|-------|-------|
| **Priority** | P0 | **FR ID** | FR1 (negative) | **Auto** | ✅ `CreateABTestTest#testCreateExperimentWithoutName` |
| **Steps** | 1. Create experiment with empty name → 2. Click Save |
| **Expected** | Error: "Experiment name is required". Experiment NOT saved. |
| **Validation** | `experimentPage.isErrorDisplayed() == true` |

### TC-ABT-003 • Add Multiple Variations (Control + Up to 10)

| Field | Value |
|-------|-------|
| **Priority** | P0 | **FR ID** | FR1 | **Auto** | ✅ `CreateABTestTest#testAddMultipleVariationsToABTest` |
| **Test Data** | Control + 3 named variations |
| **Steps** | 1. Create A/B test → 2. Add 3 variations |
| **Expected** | Total 4 variations displayed (control + 3). Each variation block is editable. |
| **Validation** | `experimentPage.getVariationCount() == 4` |

### TC-ABT-004 • Visual Editor (WYSIWYG) Variation Editing

| Field | Value |
|-------|-------|
| **Priority** | P0 | **FR ID** | FR3 | **Auto** | ✅ `CreateABTestTest#testVisualEditorForVariations` |
| **Steps** | 1. Create A/B test with variation → 2. Click "Visual Editor" tab → 3. Modify element (click + edit) → 4. Save |
| **Expected** | Visual editor loads the page in an iframe. Changes apply in real-time. Save persists changes. |
| **Validation** | `experimentPage.isSuccessDisplayed() == true` |

### TC-ABT-005 • Code Editor Variation Editing (CSS/JS)

| Field | Value |
|-------|-------|
| **Priority** | P1 | **FR ID** | FR3 | **Auto** | ✅ `CodeEditorTest#testCodeEditorForVariations` |
| **Test Data** | CSS: `body { background-color: #f5f5f5; }` |
| **Steps** | 1. Create A/B test → 2. Add variation → 3. Click "Code Editor" → 4. Enter CSS/JS → 5. Apply |
| **Expected** | Code injected successfully. Preview reflects the CSS/JS changes. |

### TC-ABT-006 • Toggle Between Visual and Code Editors

| Field | Value |
|-------|-------|
| **Priority** | P1 | **FR ID** | FR3 | **Auto** | ✅ `CodeEditorTest#testToggleBetweenVisualAndCodeEditor` |
| **Steps** | 1. Open Visual Editor → make change → 2. Switch to Code Editor → verify code reflects change → 3. Switch back |
| **Expected** | Changes sync between editors. No data loss on toggle. |

### TC-ABT-007 • Set Primary Metric

| Field | Value |
|-------|-------|
| **Priority** | P0 | **FR ID** | FR1 | **Auto** | ✅ `CreateABTestTest#testCreateABTestWithValidData` |
| **Test Data** | Metric = "Click-Through Rate" |
| **Steps** | 1. Create A/B test → 2. Select primary metric from dropdown |
| **Expected** | Metric selected. Goal configuration UI updates accordingly. |

### TC-ABT-008 • Add Multiple Goals

| Field | Value |
|-------|-------|
| **Priority** | P1 | **FR ID** | FR1 | **Auto** | ✅ `CreateABTestTest#testCreateABTestWithValidData` |
| **Steps** | 1. Create A/B test → 2. Add goal "Revenue per Visitor" → 3. Add goal "Time on Page" |
| **Expected** | Both goals listed. Primary metric distinct from secondary goals. |

### TC-ABT-009 • Launch Experiment → Status "Running"

| Field | Value |
|-------|-------|
| **Priority** | P0 | **FR ID** | FR1 | **Auto** | ✅ `CreateABTestTest#testLaunchABTest` |
| **Steps** | 1. Create A/B test → Save Draft → 2. Click "Launch" |
| **Expected** | Status changes from "Draft" to "Running". Traffic starts flowing. |
| **Validation** | `experimentPage.getExperimentStatus().equalsIgnoreCase("running")` |

### TC-ABT-010 • Pause Running Experiment

| Field | Value |
|-------|-------|
| **Priority** | P0 | **FR ID** | FR1 | **Auto** | ✅ `CreateABTestTest#testPauseRunningExperiment` |
| **Steps** | 1. Launch experiment → 2. Click "Pause" |
| **Expected** | Status changes to "Paused". Visitors see control (original) only. |
| **Validation** | Status equals "paused" |

### TC-ABT-011 • Resume Paused Experiment

| Field | Value |
|-------|-------|
| **Priority** | P0 | **FR ID** | FR1 | **Auto** | ❌ Manual |
| **Steps** | 1. Pause running experiment → 2. Click "Resume" |
| **Expected** | Status changes back to "Running". Existing data preserved, new visitors added. |

### TC-ABT-012 • Stop Experiment

| Field | Value |
|-------|-------|
| **Priority** | P0 | **FR ID** | FR1 | **Auto** | ✅ `CreateABTestTest#testStopExperimentAndDeclareWinner` |
| **Steps** | 1. Launch experiment → 2. Click "Stop" |
| **Expected** | Status "Stopped". No more traffic allocated. Final results frozen. |

### TC-ABT-013 • Declare Winner After Statistical Significance

| Field | Value |
|-------|-------|
| **Priority** | P1 | **FR ID** | FR2 | **Auto** | ✅ `SmartStatsTest#testWinnerDeclaration` |
| **Precondition** | Experiment has reached 95% confidence for one variation |
| **Steps** | 1. Open experiment → 2. Click "Declare Winner" → 3. Select winning variation → 4. Confirm |
| **Expected** | Selected variation marked as winner. All traffic now goes to winning variation. |

### TC-ABT-014 • Schedule Experiment with Dates

| Field | Value |
|-------|-------|
| **Priority** | P1 | **FR ID** | FR1 | **Auto** | ✅ `CreateABTestTest#testExperimentScheduling` |
| **Test Data** | start="2026-01-15", end="2026-02-15" |
| **Steps** | 1. Create A/B test → 2. Click "Schedule" → 3. Set start + end dates → 4. Confirm |
| **Expected** | Schedule saved. Experiment auto-launches on start date, auto-stops on end date. |

### TC-ABT-015 • Preview Variations Across Devices

| Field | Value |
|-------|-------|
| **Priority** | P1 | **FR ID** | FR1 | **Auto** | ✅ (via preview) |
| **Precondition** | Experiment with at least one variation |
| **Steps** | 1. Open experiment → 2. Click "Preview" → 3. Toggle between device sizes (Desktop/Mobile/Tablet) |
| **Expected** | Variation renders correctly on each device size. Device toggle changes viewport. |

### TC-ABT-016 • Set Traffic Allocation Percentage

| Field | Value |
|-------|-------|
| **Priority** | P1 | **FR ID** | FR1 | **Auto** | ✅ `CreateABTestTest` |
| **Test Data** | Slider value = 50 |
| **Steps** | 1. In experiment config → 2. Drag traffic allocation slider to 50% → 3. Save |
| **Expected** | 50% visitors see control, 50% see variation. Visual indicator updates. |

### TC-ABT-017 • Duplicate Existing Experiment

| Field | Value |
|-------|-------|
| **Priority** | P2 | **FR ID** | FR1 | **Auto** | ❌ Manual |
| **Steps** | 1. Open existing experiment → 2. Click "Duplicate" → 3. Rename → 4. Save |
| **Expected** | New experiment created with same config. Original unchanged. |

### TC-ABT-018 • Delete Experiment in Draft Status

| Field | Value |
|-------|-------|
| **Priority** | P2 | **FR ID** | FR1 | **Auto** | ❌ Manual |
| **Precondition** | Experiment in "Draft" status |
| **Steps** | 1. Open draft experiment → 2. Click "Delete" → 3. Confirm |
| **Expected** | Experiment removed from list. Cannot be recovered. Running experiments cannot be deleted. |
