# Heatmaps — Test Cases

**File**: `TC-HTM-Heatmaps.md` | **Module**: Insights - Heatmaps | **Total**: 6 TC | **Automated**: 5

---

### TC-HTM-001 • Generate Click Heatmap for Valid Page URL

| Field | Value |
|-------|-------|
| **Priority** | P0 | **FR ID** | FR4 | **Auto** | ✅ `HeatmapTest#testGenerateClickHeatmap` |
| **Test Data** | URL = "https://app.vwo.com/", Type = "Click Heatmap" |
| **Steps** | 1. Navigate to Insights → Heatmaps → 2. Enter page URL → 3. Select "Click Heatmap" → 4. Click "Generate" |
| **Expected** | Heatmap overlay renders on the page. Click hotspots color-coded (red=most clicks, blue=least). Tooltip shows click count on hover. |
| **Validation** | `insightsPage.isHeatmapDisplayed() == true` |

### TC-HTM-002 • Generate Scroll Heatmap with Depth Overlay

| Field | Value |
|-------|-------|
| **Priority** | P0 | **FR ID** | FR4 | **Auto** | ✅ `HeatmapTest#testGenerateScrollHeatmap` |
| **Steps** | 1. Select "Scroll Heatmap" → 2. Enter URL → 3. Generate |
| **Expected** | Scroll depth overlay shows % of visitors who scrolled to each page section. "Fold" line visible at common breakpoints. |

### TC-HTM-003 • Generate Move Heatmap

| Field | Value |
|-------|-------|
| **Priority** | P1 | **FR ID** | FR4 | **Auto** | ✅ `HeatmapTest#testMoveHeatmapGeneration` |
| **Steps** | 1. Select "Move Heatmap" → 2. Generate |
| **Expected** | Mouse movement trails visualized. Areas with most cursor停留 highlighted. |

### TC-HTM-004 • Filter Heatmap by Device Type

| Field | Value |
|-------|-------|
| **Priority** | P1 | **FR ID** | FR4 | **Auto** | ✅ `HeatmapTest#testHeatmapDeviceFiltering` |
| **Steps** | 1. Generate heatmap → 2. Filter by "Mobile" → 3. Filter by "Desktop" |
| **Expected** | Heatmap refreshes per device. Mobile heatmap shows different click patterns (thumb zone). Desktop shows wider spread. |

### TC-HTM-005 • Invalid URL Shows Graceful Error

| Field | Value |
|-------|-------|
| **Priority** | P1 | **FR ID** | FR4 (negative) | **Auto** | ✅ `HeatmapTest#testHeatmapWithInvalidUrl` |
| **Test Data** | URL = "https://invalid-page-12345.com" |
| **Steps** | 1. Enter unreachable URL → 2. Generate |
| **Expected** | Error: "Unable to load page. Please verify the URL is accessible." Heatmap NOT generated. |
| **Validation** | `insightsPage.isHeatmapDisplayed() == false` |

### TC-HTM-006 • Heatmap Overlay Disappears on Exiting Heatmap View

| Field | Value |
|-------|-------|
| **Priority** | P2 | **FR ID** | FR4 | **Auto** | ❌ Manual |
| **Steps** | 1. Generate heatmap → 2. Close heatmap view / navigate away |
| **Expected** | Page returns to normal. No residual overlay elements. Original page layout unchanged. |
