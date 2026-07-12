# Reports & Dashboards — Test Cases

**File**: `TC-RPT-Reports.md` | **Module**: Reports | **Total**: 10 TC | **Automated**: 10

---

### TC-RPT-001 • Reports Page Loads with Summary Statistics

| Field | Value |
|-------|-------|
| **Priority** | P0 | **FR ID** | FR6 | **Auto** | ✅ `ReportsTest#testNavigateToReportsPage` |
| **Steps** | 1. Navigate to Reports |
| **Expected** | Reports dashboard loads. Summary cards visible: Active Experiments, Total Visitors, Avg Conversion Rate, Revenue Impact. |
| **Validation** | `reportsPage.isReportsPageDisplayed() == true` |

### TC-RPT-002 • Experiment Performance Table

| Field | Value |
|-------|-------|
| **Priority** | P0 | **FR ID** | FR6 | **Auto** | ✅ `ReportsTest#testReportsDisplayExperimentPerformance`, `testExperimentsTableColumns` |
| **Steps** | 1. Open Reports → 2. View experiment table |
| **Expected** | Table columns: Name, Variations, Visitors, Conversions, Conversion Rate, Confidence, Lift, Revenue. Sortable by each column. Paginated. |
| **Validation** | Headers contain "Name", "Conversion", "Visitors" |

### TC-RPT-003 • Filter Reports by Status

| Field | Value |
|-------|-------|
| **Priority** | P1 | **FR ID** | FR6 | **Auto** | ✅ `ReportsTest#testFilterReportsByStatus` |
| **Test Data** | Filter = "Running" |
| **Steps** | 1. Click status filter → 2. Select "Running" → 3. Apply |
| **Expected** | Table shows only Running experiments. Count updates. Other statuses hidden. |

### TC-RPT-004 • Filter Reports by Project

| Field | Value |
|-------|-------|
| **Priority** | P1 | **FR ID** | FR6 | **Auto** | ✅ `ReportsTest#testFilterReportsByProject` |
| **Test Data** | Project = "Default Project" |
| **Steps** | 1. Click project filter → 2. Select project → 3. Apply |
| **Expected** | Data scoped to selected project. All metrics update. |

### TC-RPT-005 • Filter Reports by Date Range

| Field | Value |
|-------|-------|
| **Priority** | P1 | **FR ID** | FR6 | **Auto** | ✅ `ReportsTest#testDateRangeFilterOnReports` |
| **Test Data** | Range = "2026-01-01 to 2026-01-31" |
| **Steps** | 1. Open date picker → 2. Select start + end → 3. Apply |
| **Expected** | Charts + tables refresh to show data only within the date range. |

### TC-RPT-006 • Chart Visualizations Render

| Field | Value |
|-------|-------|
| **Priority** | P1 | **FR ID** | FR6 | **Auto** | ✅ `ReportsTest#testReportChartsDisplay` |
| **Steps** | 1. Open Reports → 2. Observe chart area |
| **Expected** | Charts render: Conversion trend line, Experiment performance bar chart, Traffic distribution pie chart. Interactive (hover shows tooltip). |
| **Validation** | `reportsPage.isChartDisplayed() == true` |

### TC-RPT-007 • Export Report to CSV

| Field | Value |
|-------|-------|
| **Priority** | P1 | **FR ID** | FR6 | **Auto** | ✅ `ReportsTest#testExportReportToCSV` |
| **Steps** | 1. Click "Export" → 2. Select "CSV" |
| **Expected** | File downloads with `.csv` extension. Contains all current table data (respecting filters). Columns match UI. |

### TC-RPT-008 • Export Report to PDF

| Field | Value |
|-------|-------|
| **Priority** | P1 | **FR ID** | FR6 | **Auto** | ✅ `ReportsTest#testExportReportToPDF` |
| **Steps** | 1. Click "Export" → 2. Select "PDF" |
| **Expected** | PDF downloads. Contains Summary cards + Charts + Table. Formatted for printing. |

### TC-RPT-009 • Schedule Recurring Report Delivery

| Field | Value |
|-------|-------|
| **Priority** | P2 | **FR ID** | FR6 | **Auto** | ✅ `ReportsTest#testScheduleReportDelivery` |
| **Test Data** | Recipients = "qa-team@vwo.com", Frequency = "Weekly" |
| **Steps** | 1. Click "Schedule Report" → 2. Enter email recipients → 3. Select frequency (Daily/Weekly/Monthly) → 4. Confirm |
| **Expected** | Scheduled report created. Confirmation: "Report scheduled successfully." Email sent at configured frequency. |

### TC-RPT-010 • Conversion Summary Shows Lift/Decline Trends

| Field | Value |
|-------|-------|
| **Priority** | P1 | **FR ID** | FR6 | **Auto** | ✅ `ReportsTest#testReportsDisplayExperimentPerformance` |
| **Steps** | 1. Open Reports → 2. View conversion summary card |
| **Expected** | Summary shows: Current conversion rate, Change vs previous period (↑/↓ arrow + percentage), Trend indicator (positive/negative/neutral). |
