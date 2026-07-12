# Funnels & Surveys — Test Cases

**File**: `TC-FUN-FunnelsSurveys.md` | **Module**: Insights - Funnels & Surveys | **Total**: 5 TC | **Automated**: 5

---

### TC-FUN-001 • Create Funnel with 2+ Steps

| Field | Value |
|-------|-------|
| **Priority** | P0 | **FR ID** | FR4 | **Auto** | ✅ `FunnelSurveyTest#testCreateFunnelWithMultipleSteps` |
| **Test Data** | Step URLs: `https://app.vwo.com/` → `https://app.vwo.com/pricing` → `https://app.vwo.com/signup` |
| **Steps** | 1. Navigate to Insights → Funnels → 2. Click "Create Funnel" → 3. Add step URLs in order → 4. Save |
| **Expected** | Funnel created with all steps. Steps ordered correctly. Save confirmation shown. |
| **Validation** | `insightsPage.getFunnelStepCount() >= 2` |

### TC-FUN-002 • Funnel Shows Drop-Off % at Each Step

| Field | Value |
|-------|-------|
| **Priority** | P0 | **FR ID** | FR4 | **Auto** | ✅ `FunnelSurveyTest#testFunnelDropOffVisualization` |
| **Precondition** | Funnel has collected visitor data |
| **Steps** | 1. Open funnel → 2. Observe visualization |
| **Expected** | Each step shows: Visitors entering, % drop-off from previous step, % continued. Visual bar/arrow indicating flow. Highest drop-off clearly highlighted. |

### TC-FUN-003 • Create and Publish On-Page Survey

| Field | Value |
|-------|-------|
| **Priority** | P1 | **FR ID** | FR4 | **Auto** | ✅ `FunnelSurveyTest#testCreateOnPageSurvey` |
| **Test Data** | Question = "How likely are you to recommend VWO to a colleague?" |
| **Steps** | 1. Navigate to Insights → Surveys → 2. Click "Create Survey" → 3. Enter question → 4. Configure targeting → 5. Publish |
| **Expected** | Survey created and published. Survey appears on target pages. Count increases by 1. |

### TC-FUN-004 • Survey Responses Collected in Dashboard

| Field | Value |
|-------|-------|
| **Priority** | P2 | **FR ID** | FR4 | **Auto** | ❌ Manual |
| **Precondition** | Survey is published and receiving responses |
| **Steps** | 1. Open published survey → 2. View "Responses" tab |
| **Expected** | Response count > 0. Individual responses listed with timestamps. Aggregate data (NPS score, distribution chart). |

### TC-FUN-005 • Export Funnel Data to CSV

| Field | Value |
|-------|-------|
| **Priority** | P1 | **FR ID** | FR6 | **Auto** | ✅ `FunnelSurveyTest#testExportInsightsData` |
| **Steps** | 1. Open funnel → 2. Click "Export" → 3. Select CSV format |
| **Expected** | CSV file downloads. File contains: Step name, Visitors, Drop-off count, Drop-off %, Conversion %. Data matches UI. |
