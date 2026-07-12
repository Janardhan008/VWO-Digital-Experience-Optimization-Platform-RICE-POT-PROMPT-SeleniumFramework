# Split URL Testing — Test Cases

**File**: `TC-SPL-SplitURL.md` | **Module**: Split URL | **Total**: 4 TC | **Automated**: 3

---

### TC-SPL-001 • Create Split URL Test with 2+ Destination URLs

| Field | Value |
|-------|-------|
| **Priority** | P0 | **FR ID** | FR1 | **Auto** | ✅ `CreateSplitURLTest#testCreateSplitURLTest` |
| **Steps** | 1. Click "Create Experiment" → "Split URL" → 2. Enter control URL → 3. Add 2+ variation URLs → 4. Save |
| **Expected** | Experiment created with multiple URLs. Each variation maps to a different URL. |
| **Validation** | 3 total variations (control + 2) |

### TC-SPL-002 • Traffic Distribution Across Variations

| Field | Value |
|-------|-------|
| **Priority** | P1 | **FR ID** | FR1 | **Auto** | ✅ `CreateSplitURLTest#testSplitURLTrafficDistribution` |
| **Steps** | 1. Create Split URL with 2 variations → 2. Set traffic allocation → 3. Launch |
| **Expected** | Traffic split evenly (or per configured %) across all URLs. Distribution UI shows percentages. |

### TC-SPL-003 • Duplicate URL Validation

| Field | Value |
|-------|-------|
| **Priority** | P1 | **FR ID** | FR1 (negative) | **Auto** | ✅ `CreateSplitURLTest#testSplitURLWithDuplicateUrls` |
| **Test Data** | Variation URL = same as control URL |
| **Steps** | 1. Create Split URL → 2. Add variation with identical URL to control |
| **Expected** | Warning/error: "URL must be different from control". Not allowed to save. |

### TC-SPL-004 • Split URL with Different Domains

| Field | Value |
|-------|-------|
| **Priority** | P1 | **FR ID** | FR1 | **Auto** | ❌ Manual |
| **Test Data** | control="https://example.com/page-a", variation="https://example.org/page-b" |
| **Steps** | 1. Create Split URL with URLs on different domains |
| **Expected** | Test saves and launches. Cross-domain tracking works (cookies handled correctly). |
