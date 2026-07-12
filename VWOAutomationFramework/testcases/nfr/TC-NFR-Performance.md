# Non-Functional — Performance — Test Cases

**File**: `nfr/TC-NFR-Performance.md` | **Module**: Performance | **Total**: 6 TC | **Automated**: 0

---

### TC-PRF-01 • Dashboard Page Load Time < 2s

| Field | Value |
|-------|-------|
| **Priority** | P0 | **NFR** | Performance | **Auto** | ❌ (needs Selenium + StopWatch) |
| **Precondition** | User logged in, cache cleared |
| **Steps** | 1. Start timer → 2. Navigate to Dashboard → 3. Wait for page fully loaded (all XHRs complete) → 4. Stop timer |
| **Expected** | Total load time < 2000ms (2 seconds). |
| **Tool** | Selenium + `StopWatch` or Navigation Timing API |

### TC-PRF-02 • Experiment Creation/Save < 2s

| Field | Value |
|-------|-------|
| **Priority** | P0 | **NFR** | Performance | **Auto** | ❌ |
| **Steps** | 1. Create experiment → 2. Measure time from "Save" click to success toast |
| **Expected** | < 2000ms for draft save. |

### TC-PRF-03 • Heatmap Generation < 5s

| Field | Value |
|-------|-------|
| **Priority** | P0 | **NFR** | Performance | **Auto** | ❌ |
| **Steps** | 1. Enter URL → 2. Click "Generate Heatmap" → 3. Measure until overlay renders |
| **Expected** | < 5000ms (5 seconds). |

### TC-PRF-04 • Report Page Load with 100+ Experiments < 3s

| Field | Value |
|-------|-------|
| **Priority** | P0 | **NFR** | Performance | **Auto** | ❌ |
| **Precondition** | Workspace has 100+ experiments |
| **Steps** | 1. Navigate to Reports → 2. Measure full page load time |
| **Expected** | < 3000ms (3 seconds). Pagination/no-data states handle gracefully. |

### TC-PRF-05 • API Response Time < 500ms

| Field | Value |
|-------|-------|
| **Priority** | P0 | **NFR** | Performance | **Auto** | ❌ |
| **Steps** | Call each API endpoint and measure response time |
| **Criteria** | `GET /booking` < 500ms, `GET /booking/{id}` < 500ms, `POST /booking` < 1000ms |

### TC-PRF-06 • Concurrent Experiment Launches (50 Simultaneous)

| Field | Value |
|-------|-------|
| **Priority** | P0 | **NFR** | Performance | **Auto** | ❌ |
| **Steps** | 1. Create 50 draft experiments → 2. Launch all simultaneously via API |
| **Expected** | No 500 errors. All 50 launch successfully. System recovers within 30s. |
| **Tool** | JMeter / Locust |
