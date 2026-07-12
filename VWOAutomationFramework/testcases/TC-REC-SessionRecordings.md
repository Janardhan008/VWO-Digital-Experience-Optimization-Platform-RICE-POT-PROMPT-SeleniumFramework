# Session Recordings — Test Cases

**File**: `TC-REC-SessionRecordings.md` | **Module**: Insights - Recordings | **Total**: 5 TC | **Automated**: 4

---

### TC-REC-001 • Session Recordings List Loads with Metadata

| Field | Value |
|-------|-------|
| **Priority** | P0 | **FR ID** | FR4 | **Auto** | ✅ `SessionRecordingTest#testViewSessionRecordings` |
| **Steps** | 1. Navigate to Insights → Session Recordings |
| **Expected** | List of recorded sessions displayed. Each entry shows: User ID, Date, Duration, Pages visited, Actions count. Pagination if > 20. |
| **Validation** | `insightsPage.getSessionRecordingCount() >= 0` |

### TC-REC-002 • Play Session Recording Opens Player

| Field | Value |
|-------|-------|
| **Priority** | P0 | **FR ID** | FR4 | **Auto** | ✅ `SessionRecordingTest#testPlaySessionRecording` |
| **Steps** | 1. Click a recording → 2. Click "Play" |
| **Expected** | Recording player opens. Screen replay starts automatically. Timeline progress bar visible. |

### TC-REC-003 • Playback Controls Functional

| Field | Value |
|-------|-------|
| **Priority** | P1 | **FR ID** | FR4 | **Auto** | ✅ `SessionRecordingTest#testSessionPlaybackControls` |
| **Steps** | 1. Play recording → 2. Click Pause → 3. Click Play → 4. Change speed (2x, 4x) → 5. Skip forward/backward |
| **Expected** | Pause: replay freezes. Play: resumes. Speed: replay accelerates audibly. Skip: jumps to that timeline position. |

### TC-REC-004 • Filter Recordings by Date Range

| Field | Value |
|-------|-------|
| **Priority** | P1 | **FR ID** | FR4 | **Auto** | ✅ `SessionRecordingTest#testFilterSessionRecordingsByDate` |
| **Test Data** | Date range = "2026-01-01 to 2026-01-31" |
| **Steps** | 1. Click date filter → 2. Select start/end dates → 3. Apply |
| **Expected** | List filtered to recordings within the date range. Count updates. Empty state if no recordings. |

### TC-REC-005 • Filter Recordings by User ID

| Field | Value |
|-------|-------|
| **Priority** | P1 | **FR ID** | FR4 | **Auto** | ❌ Manual |
| **Test Data** | Known user ID from a previous test session |
| **Steps** | 1. Enter user ID in search → 2. Apply |
| **Expected** | Only recordings from that user displayed. Exact match required. |
