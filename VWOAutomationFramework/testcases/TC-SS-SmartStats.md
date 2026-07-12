# SmartStats Engine — Test Cases

**File**: `TC-SS-SmartStats.md` | **Module**: SmartStats | **Total**: 6 TC | **Automated**: 4

---

### TC-SS-001 • SmartStats Panel Visible in Experiment Details

| Field | Value |
|-------|-------|
| **Priority** | P0 | **FR ID** | FR2 | **Auto** | ✅ `SmartStatsTest#testSmartStatsBayesianAnalysis` |
| **Precondition** | Experiment has collected visitor data |
| **Steps** | 1. Open launched experiment → 2. Scroll to results section |
| **Expected** | SmartStats panel visible. Shows Bayesian analysis with probability curves. |

### TC-SS-002 • Bayesian Confidence Score Displayed

| Field | Value |
|-------|-------|
| **Priority** | P0 | **FR ID** | FR2 | **Auto** | ✅ `SmartStatsTest#testConfidenceScoreDisplay` |
| **Steps** | 1. Open experiment with data → 2. Observe confidence column |
| **Expected** | Each variation has a confidence score (percentage). Higher score = more likely to be better than control. |
| **Validation** | `experimentPage.getConfidenceScore()` is a non-empty percentage string |

### TC-SS-003 • Conversion Rate, Lift, and Visitors Count Shown

| Field | Value |
|-------|-------|
| **Priority** | P0 | **FR ID** | FR2 | **Auto** | ✅ `SmartStatsTest#testExperimentSummaryStats` |
| **Steps** | 1. Open experiment → 2. View summary table |
| **Expected** | Columns: Variation, Visitors, Conversions, Conversion Rate, Lift, Confidence. All populated. |

### TC-SS-004 • Winner Automatically Suggested at 95% Confidence

| Field | Value |
|-------|-------|
| **Priority** | P1 | **FR ID** | FR2 | **Auto** | ✅ `SmartStatsTest#testWinnerDeclaration` |
| **Precondition** | Enough data collected for 95% confidence |
| **Steps** | 1. Monitor experiment → 2. When confidence reaches 95% |
| **Expected** | System highlights the winning variation. "Declare Winner" button becomes prominent. Suggested winner label shown. |

### TC-SS-005 • Manual Winner Override

| Field | Value |
|-------|-------|
| **Priority** | P1 | **FR ID** | FR2 | **Auto** | ❌ Manual |
| **Steps** | 1. Open experiment → 2. Click "Declare Winner" → 3. Select a variation that is NOT the statistically suggested one |
| **Expected** | System allows manual override. Confirmation dialog: "Are you sure?" Winner declared as selected. |

### TC-SS-006 • SmartStats Data Refreshes in Real-Time

| Field | Value |
|-------|-------|
| **Priority** | P1 | **FR ID** | FR2 | **Auto** | ❌ Manual |
| **Precondition** | Experiment is running and receiving traffic |
| **Steps** | 1. Open experiment results → 2. Wait 30 seconds without refreshing |
| **Expected** | Visitor count and confidence score update automatically. No manual refresh needed. |
