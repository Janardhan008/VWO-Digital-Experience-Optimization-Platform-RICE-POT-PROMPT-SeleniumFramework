# Dashboard — Test Cases

**File**: `TC-DSH-Dashboard.md` | **Module**: Dashboard | **Total**: 6 TC | **Automated**: 4

| Ref | Priority | Automated |
|:---:|:--------:|:---------:|
| FR6, FR8 | P0: 4, P1: 2 | ✅ 4 / ❌ 2 |

---

### TC-DSH-001 • Dashboard Loads with Key Elements

| Field | Value |
|-------|-------|
| **Priority** | P0 |
| **FR ID** | FR6 |
| **Automation** | ✅ `tests.dashboard.DashboardTest#testDashboardKeyElements` |
| **Precondition** | Valid login completed |
| **Test Data** | N/A |
| **Steps** | 1. Login successfully → 2. Observe dashboard page |
| **Expected** | Dashboard header visible. Active experiments count displayed. Recent activity panel visible. Sidebar navigation present. Notification bell visible. |
| **Validation** | `dashboardPage.isDashboardDisplayed() == true` AND `dashboardPage.isRecentActivityVisible() == true` |

### TC-DSH-002 • Recent Activity Panel Shows Latest Actions

| Field | Value |
|-------|-------|
| **Priority** | P1 |
| **FR ID** | FR6 |
| **Automation** | ❌ Manual (requires prior experiment activity) |
| **Precondition** | At least one experiment was created/modified recently |
| **Steps** | 1. Login → 2. Observe "Recent Activity" panel on dashboard |
| **Expected** | Panel shows timestamped entries (e.g., "Experiment X launched 5 min ago"). Entries sorted newest-first. |
| **Validation** | Activity count > 0. Most recent entry timestamp <= current time. |

### TC-DSH-003 • Active Experiments Count Matches Actual

| Field | Value |
|-------|-------|
| **Priority** | P0 |
| **FR ID** | FR6 |
| **Automation** | ❌ Manual (requires known experiment state) |
| **Precondition** | There are active (Running) experiments in the account |
| **Steps** | 1. Login → 2. Note the "Active Experiments" count → 3. Navigate to Experiments list → 4. Count Running experiments |
| **Expected** | Dashboard count equals the actual count of Running experiments. |
| **Validation** | `Number(dashboard.activeCount) == experimentsPage.getRunningExperiments().size()` |

### TC-DSH-004 • Sidebar Navigation to All Modules

| Field | Value |
|-------|-------|
| **Priority** | P0 |
| **FR ID** | FR6 |
| **Automation** | ✅ `tests.dashboard.DashboardTest#testNavigationToAllSections` |
| **Precondition** | Dashboard loaded |
| **Steps** | 1. Click each sidebar item: Experiments, Insights, Personalize, Reports, Plan, Integrations, Settings |
| **Expected** | Each click navigates to the correct page (page header matches). Active state is highlighted. |
| **Validation** | Each click → correct URL pattern + page header text |

### TC-DSH-005 • Search Filters Experiments in Real-Time

| Field | Value |
|-------|-------|
| **Priority** | P1 |
| **FR ID** | FR6 |
| **Automation** | ✅ `tests.dashboard.DashboardTest#testDashboardSearch` |
| **Precondition** | At least 2 experiments exist with distinct names |
| **Test Data** | Search term matching exactly one experiment name |
| **Steps** | 1. Type partial experiment name in search bar → 2. Observe experiment cards |
| **Expected** | Only experiments matching the search term are displayed. Cards filter as user types (debounced). |
| **Validation** | Visible card count ≤ total card count. All visible cards contain the search term. |

### TC-DSH-006 • Project Selector Filters Workspace Data

| Field | Value |
|-------|-------|
| **Priority** | P1 |
| **FR ID** | FR8 |
| **Automation** | ✅ `tests.dashboard.DashboardTest` |
| **Precondition** | User has access to multiple projects |
| **Test Data** | Project name = "Default Project" |
| **Steps** | 1. Click project selector dropdown → 2. Select a different project |
| **Expected** | Dashboard reloads with data scoped to the selected project. Active experiments count updates. |
| **Validation** | Counts change after switching projects |
