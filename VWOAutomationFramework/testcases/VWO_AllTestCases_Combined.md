# VWO – Complete Test Case Specifications
---
**Master Document** | **187 Test Cases** | **Version 2.0** | **July 2026**
---



---

# VWO â€” Test Case Master Index

| Module | File | Total TC | P0 | P1 | P2 | Automated |
|--------|------|:--------:|:--:|:--:|:--:|:---------:|
| Authentication & Access Control | `TC-AUTH-Login.md` | 12 | 6 | 5 | 1 | 10 |
| Dashboard | `TC-DSH-Dashboard.md` | 6 | 4 | 2 | 0 | 4 |
| A/B Testing | `TC-ABT-ABTesting.md` | 18 | 10 | 6 | 2 | 16 |
| Split URL Testing | `TC-SPL-SplitURL.md` | 4 | 2 | 2 | 0 | 3 |
| Multivariate Testing | `TC-MVT-Multivariate.md` | 5 | 2 | 1 | 2 | 3 |
| SmartStats Engine | `TC-SS-SmartStats.md` | 6 | 3 | 3 | 0 | 4 |
| Code Editor | `TC-CDE-CodeEditor.md` | 4 | 2 | 2 | 0 | 3 |
| Audience Targeting | `TC-AUD-Audience.md` | 6 | 3 | 3 | 0 | 5 |
| Heatmaps | `TC-HTM-Heatmaps.md` | 6 | 3 | 2 | 1 | 5 |
| Session Recordings | `TC-REC-SessionRecordings.md` | 5 | 2 | 3 | 0 | 4 |
| Funnels & Surveys | `TC-FUN-FunnelsSurveys.md` | 5 | 2 | 2 | 1 | 5 |
| Personalization | `TC-PER-Personalization.md` | 10 | 5 | 5 | 0 | 8 |
| Reports & Dashboards | `TC-RPT-Reports.md` | 10 | 4 | 5 | 1 | 10 |
| Integrations | `TC-INT-Integrations.md` | 3 | 0 | 1 | 2 | 2 |
| Plan Management | `TC-PLN-PlanManagement.md` | 3 | 0 | 1 | 2 | 2 |
| **VWO UI Total** | | **103** | **48** | **43** | **12** | **84** |
| Booking API â€” Health Check | `api/TC-PNG-HealthCheck.md` | 6 | 4 | 2 | 0 | 6 |
| Booking API â€” Auth | `api/TC-AUTH-ApiAuth.md` | 5 | 2 | 3 | 0 | 5 |
| Booking API â€” Get All | `api/TC-BOK-GetAllBookings.md` | 10 | 4 | 6 | 0 | 10 |
| Booking API â€” Get Single | `api/TC-BOK-GetSingleBooking.md` | 12 | 8 | 4 | 0 | 12 |
| Booking API â€” Full Update | `api/TC-PUT-UpdateBooking.md` | 10 | 4 | 6 | 0 | 10 |
| Booking API â€” Partial Update | `api/TC-PTC-PartialUpdate.md` | 10 | 4 | 6 | 0 | 10 |
| Booking API â€” Delete | `api/TC-DEL-DeleteBooking.md` | 8 | 6 | 2 | 0 | 8 |
| **Booking API Total** | | **61** | **32** | **29** | **0** | **61** |
| Performance | `nfr/TC-NFR-Performance.md` | 6 | 6 | 0 | 0 | 0 |
| Security | `nfr/TC-NFR-Security.md` | 6 | 4 | 2 | 0 | 0 |
| Cross-Browser & Responsive | `nfr/TC-NFR-CrossBrowser.md` | 6 | 3 | 3 | 0 | 0 |
| Accessibility | `nfr/TC-NFR-Accessibility.md` | 5 | 1 | 4 | 0 | 0 |
| **NFR Total** | | **23** | **14** | **9** | **0** | **0** |
| **GRAND TOTAL** | | **187** | **94** | **81** | **12** | **145** |

## How to Use

- **P0**: Must pass before any release. Automated in CI pipeline.
- **P1**: Must pass before GA release. Automated in nightly regression.
- **P2**: Nice-to-have. Manual / exploratory.
- Each file uses the format: `TC-{MODULE}-{NNN}` for traceability to the VWO Test Plan.



---

# Authentication & Access Control â€” Test Cases

**File**: `TC-AUTH-Login.md` | **Module**: Login | **Total**: 12 TC | **Automated**: 10

| Ref | Priority | Automated |
|:---:|:--------:|:---------:|
| FR6, NFR | P0: 6, P1: 5, P2: 1 | âœ… 10 / âŒ 2 manual |

---

### TC-AUTH-001 â€¢ Valid Login with Email & Password

| Field | Value |
|-------|-------|
| **Priority** | P0 |
| **FR ID** | FR6 |
| **Automation** | âœ… `tests.login.LoginTest#testValidLogin` |
| **Precondition** | User has valid credentials (ConfigReader.getUsername / getPassword) |
| **Test Data** | email = `qa.automation@vwo.com` , password = `VWO@Test#2026` |
| **Steps** | 1. Navigate to https://app.vwo.com â€” 2. Enter email â†’ 3. Enter password â†’ 4. Click Login |
| **Expected** | Dashboard page loads with user avatar visible. URL contains `/dashboard` |
| **Validation** | `dashboardPage.isDashboardDisplayed() == true` |

### TC-AUTH-002 â€¢ Invalid Password Shows Error

| Field | Value |
|-------|-------|
| **Priority** | P0 |
| **FR ID** | FR6 |
| **Automation** | âœ… `tests.login.LoginTest#testLoginWithInvalidPassword` |
| **Precondition** | Known valid email, incorrect password |
| **Test Data** | email = `qa.automation@vwo.com` , password = `WrongPassword@999` |
| **Steps** | 1. Enter email â†’ 2. Enter wrong password â†’ 3. Click Login |
| **Expected** | Error message displayed: "Invalid email or password" (or similar). User remains on login page. |
| **Validation** | `loginPage.getErrorMessage()` contains "Invalid" or "incorrect" or "password" |

### TC-AUTH-003 â€¢ Unregistered Email Shows Error

| Field | Value |
|-------|-------|
| **Priority** | P0 |
| **FR ID** | FR6 |
| **Automation** | âœ… `tests.login.LoginTest#testLoginWithUnregisteredEmail` |
| **Precondition** | Email does not exist in VWO system |
| **Test Data** | email = `nonexistent@unknown.com` , password = `VWO@Test#2026` |
| **Steps** | 1. Enter unregistered email â†’ 2. Enter password â†’ 3. Click Login |
| **Expected** | Error: "Account not found" or "No account with this email" |
| **Validation** | Error message contains "not found" or "unrecognized" |

### TC-AUTH-004 â€¢ Empty Credentials Blocked

| Field | Value |
|-------|-------|
| **Priority** | P0 |
| **FR ID** | FR6 |
| **Automation** | âœ… `tests.login.LoginTest#testLoginWithEmptyCredentials` |
| **Precondition** | Login page loaded |
| **Test Data** | email = `""` , password = `""` |
| **Steps** | 1. Leave email empty â†’ 2. Leave password empty â†’ 3. Click Login |
| **Expected** | Validation error: "Email is required" or equivalent. No network call made. |
| **Validation** | Error message contains "required" or "empty" or "email" |

### TC-AUTH-005 â€¢ Password Field Masks Input

| Field | Value |
|-------|-------|
| **Priority** | P0 |
| **FR ID** | NFR (Security) |
| **Automation** | âœ… `tests.login.LoginTest#testPasswordFieldMasking` |
| **Precondition** | Login page loaded |
| **Steps** | 1. Type any password into password field |
| **Expected** | Characters appear as bullets/dots (`â—â—â—â—â—â—â—â—`), not plain text |
| **Validation** | `passwordField.getAttribute("type")` equals `"password"` |

### TC-AUTH-006 â€¢ 2FA Code Input Appears After Login

| Field | Value |
|-------|-------|
| **Priority** | P0 |
| **FR ID** | NFR (Security) |
| **Automation** | âŒ Manual (requires 2FA-enabled test account) |
| **Precondition** | VWO account has 2FA enabled |
| **Test Data** | email/password for a 2FA-enabled account |
| **Steps** | 1. Enter valid credentials â†’ 2. Click Login â†’ 3. Observe next screen |
| **Expected** | 2FA code input field is displayed instead of Dashboard. User cannot bypass. |
| **Validation** | `loginPage.is2FAFieldDisplayed() == true` |

### TC-AUTH-007 â€¢ Invalid 2FA Code Blocks Access

| Field | Value |
|-------|-------|
| **Priority** | P1 |
| **FR ID** | NFR (Security) |
| **Automation** | âŒ Manual |
| **Precondition** | 2FA input field is visible |
| **Test Data** | 2FA code = `000000` (wrong code) |
| **Steps** | 1. Enter wrong 2FA code â†’ 2. Click Verify |
| **Expected** | Error: "Invalid verification code". User NOT redirected to dashboard. |
| **Validation** | Error toast visible, URL unchanged |

### TC-AUTH-008 â€¢ Forgot Password Sends Reset Email

| Field | Value |
|-------|-------|
| **Priority** | P1 |
| **FR ID** | FR6 |
| **Automation** | âœ… `tests.login.LoginTest#testForgotPasswordFlow` |
| **Precondition** | Login page loaded |
| **Test Data** | email = `testuser@vwo.com` |
| **Steps** | 1. Click "Forgot Password?" â†’ 2. Enter registered email â†’ 3. Click "Send Reset Link" |
| **Expected** | Toast message: "Password reset link sent to your email" |
| **Validation** | Toast contains "sent" or "email" or "reset" |

### TC-AUTH-009 â€¢ SSO Login Redirects to IdP

| Field | Value |
|-------|-------|
| **Priority** | P1 |
| **FR ID** | FR6 |
| **Automation** | âœ… `tests.login.LoginTest` |
| **Precondition** | Login page loaded, SSO configured for the test org |
| **Steps** | 1. Click "Sign in with SSO" â†’ 2. Enter company domain |
| **Expected** | Redirected to identity provider (Okta/Azure AD). VWO login page no longer visible. |
| **Validation** | Current URL contains the IdP domain |

### TC-AUTH-010 â€¢ Remember Me Persists Session

| Field | Value |
|-------|-------|
| **Priority** | P0 |
| **FR ID** | FR6 |
| **Automation** | âœ… `tests.login.LoginTest#testRememberMeFunctionality` |
| **Precondition** | Browser has no prior VWO session cookies |
| **Test Data** | Valid credentials |
| **Steps** | 1. Check "Remember Me" â†’ 2. Login â†’ 3. Close browser â†’ 4. Re-open â†’ 5. Navigate to app.vwo.com |
| **Expected** | User is still logged in (session restored from cookie/token) |
| **Validation** | Dashboard loads without re-entering credentials |

### TC-AUTH-011 â€¢ Session Timeout Logs Out After Inactivity

| Field | Value |
|-------|-------|
| **Priority** | P1 |
| **FR ID** | NFR (Security) |
| **Automation** | âŒ Manual (requires waiting 30 min) |
| **Precondition** | User is logged in |
| **Steps** | 1. Login â†’ 2. Remain idle for 30 minutes â†’ 3. Click any navigation link |
| **Expected** | User is redirected to login page. Session token expired. |
| **Validation** | Login page is displayed, API returns 401 |

### TC-AUTH-012 â€¢ Logout Clears Session

| Field | Value |
|-------|-------|
| **Priority** | P0 |
| **FR ID** | FR6 |
| **Automation** | âœ… `tests.login.LoginTest#testLogoutFunctionality` |
| **Precondition** | User is logged in (Dashboard visible) |
| **Steps** | 1. Click user avatar â†’ 2. Click "Log out" |
| **Expected** | Returned to login page. Browser back button does NOT return to Dashboard. |
| **Validation** | `loginPage.isLoginPageDisplayed() == true`. Navigating back shows login page. |



---

# Dashboard â€” Test Cases

**File**: `TC-DSH-Dashboard.md` | **Module**: Dashboard | **Total**: 6 TC | **Automated**: 4

| Ref | Priority | Automated |
|:---:|:--------:|:---------:|
| FR6, FR8 | P0: 4, P1: 2 | âœ… 4 / âŒ 2 |

---

### TC-DSH-001 â€¢ Dashboard Loads with Key Elements

| Field | Value |
|-------|-------|
| **Priority** | P0 |
| **FR ID** | FR6 |
| **Automation** | âœ… `tests.dashboard.DashboardTest#testDashboardKeyElements` |
| **Precondition** | Valid login completed |
| **Test Data** | N/A |
| **Steps** | 1. Login successfully â†’ 2. Observe dashboard page |
| **Expected** | Dashboard header visible. Active experiments count displayed. Recent activity panel visible. Sidebar navigation present. Notification bell visible. |
| **Validation** | `dashboardPage.isDashboardDisplayed() == true` AND `dashboardPage.isRecentActivityVisible() == true` |

### TC-DSH-002 â€¢ Recent Activity Panel Shows Latest Actions

| Field | Value |
|-------|-------|
| **Priority** | P1 |
| **FR ID** | FR6 |
| **Automation** | âŒ Manual (requires prior experiment activity) |
| **Precondition** | At least one experiment was created/modified recently |
| **Steps** | 1. Login â†’ 2. Observe "Recent Activity" panel on dashboard |
| **Expected** | Panel shows timestamped entries (e.g., "Experiment X launched 5 min ago"). Entries sorted newest-first. |
| **Validation** | Activity count > 0. Most recent entry timestamp <= current time. |

### TC-DSH-003 â€¢ Active Experiments Count Matches Actual

| Field | Value |
|-------|-------|
| **Priority** | P0 |
| **FR ID** | FR6 |
| **Automation** | âŒ Manual (requires known experiment state) |
| **Precondition** | There are active (Running) experiments in the account |
| **Steps** | 1. Login â†’ 2. Note the "Active Experiments" count â†’ 3. Navigate to Experiments list â†’ 4. Count Running experiments |
| **Expected** | Dashboard count equals the actual count of Running experiments. |
| **Validation** | `Number(dashboard.activeCount) == experimentsPage.getRunningExperiments().size()` |

### TC-DSH-004 â€¢ Sidebar Navigation to All Modules

| Field | Value |
|-------|-------|
| **Priority** | P0 |
| **FR ID** | FR6 |
| **Automation** | âœ… `tests.dashboard.DashboardTest#testNavigationToAllSections` |
| **Precondition** | Dashboard loaded |
| **Steps** | 1. Click each sidebar item: Experiments, Insights, Personalize, Reports, Plan, Integrations, Settings |
| **Expected** | Each click navigates to the correct page (page header matches). Active state is highlighted. |
| **Validation** | Each click â†’ correct URL pattern + page header text |

### TC-DSH-005 â€¢ Search Filters Experiments in Real-Time

| Field | Value |
|-------|-------|
| **Priority** | P1 |
| **FR ID** | FR6 |
| **Automation** | âœ… `tests.dashboard.DashboardTest#testDashboardSearch` |
| **Precondition** | At least 2 experiments exist with distinct names |
| **Test Data** | Search term matching exactly one experiment name |
| **Steps** | 1. Type partial experiment name in search bar â†’ 2. Observe experiment cards |
| **Expected** | Only experiments matching the search term are displayed. Cards filter as user types (debounced). |
| **Validation** | Visible card count â‰¤ total card count. All visible cards contain the search term. |

### TC-DSH-006 â€¢ Project Selector Filters Workspace Data

| Field | Value |
|-------|-------|
| **Priority** | P1 |
| **FR ID** | FR8 |
| **Automation** | âœ… `tests.dashboard.DashboardTest` |
| **Precondition** | User has access to multiple projects |
| **Test Data** | Project name = "Default Project" |
| **Steps** | 1. Click project selector dropdown â†’ 2. Select a different project |
| **Expected** | Dashboard reloads with data scoped to the selected project. Active experiments count updates. |
| **Validation** | Counts change after switching projects |



---

# A/B Testing â€” Test Cases

**File**: `TC-ABT-ABTesting.md` | **Module**: Experiments | **Total**: 18 TC | **Automated**: 16

| Ref | Priority | Automated |
|:---:|:--------:|:---------:|
| FR1, FR2, FR3 | P0: 10, P1: 6, P2: 2 | âœ… 16 / âŒ 2 manual |

---

### TC-ABT-001 â€¢ Create A/B Test with Valid Data

| Field | Value |
|-------|-------|
| **Priority** | P0 | **FR ID** | FR1 | **Auto** | âœ… `CreateABTestTest#testCreateABTestWithValidData` |
| **Precondition** | Logged in, on Dashboard |
| **Test Data** | name="A/B Test - Homepage CTA Button", url="https://app.vwo.com/", hypothesis="Changing CTA button from blue to green will increase CTR by 15%" |
| **Steps** | 1. Click "Create Experiment" â†’ "A/B Test" â†’ 2. Fill name, description, URL, hypothesis â†’ 3. Add variation "Green CTA Button" â†’ 4. Select primary metric "Click-Through Rate" â†’ 5. Add goal "Newsletter Sign-ups" â†’ 6. Save Draft |
| **Expected** | Experiment saved successfully as "Draft". Success toast shown. Experiment appears in experiments list. |
| **Validation** | `experimentPage.isSuccessDisplayed() == true` |

### TC-ABT-002 â€¢ Create A/B Test Without Name â€“ Validation Error

| Field | Value |
|-------|-------|
| **Priority** | P0 | **FR ID** | FR1 (negative) | **Auto** | âœ… `CreateABTestTest#testCreateExperimentWithoutName` |
| **Steps** | 1. Create experiment with empty name â†’ 2. Click Save |
| **Expected** | Error: "Experiment name is required". Experiment NOT saved. |
| **Validation** | `experimentPage.isErrorDisplayed() == true` |

### TC-ABT-003 â€¢ Add Multiple Variations (Control + Up to 10)

| Field | Value |
|-------|-------|
| **Priority** | P0 | **FR ID** | FR1 | **Auto** | âœ… `CreateABTestTest#testAddMultipleVariationsToABTest` |
| **Test Data** | Control + 3 named variations |
| **Steps** | 1. Create A/B test â†’ 2. Add 3 variations |
| **Expected** | Total 4 variations displayed (control + 3). Each variation block is editable. |
| **Validation** | `experimentPage.getVariationCount() == 4` |

### TC-ABT-004 â€¢ Visual Editor (WYSIWYG) Variation Editing

| Field | Value |
|-------|-------|
| **Priority** | P0 | **FR ID** | FR3 | **Auto** | âœ… `CreateABTestTest#testVisualEditorForVariations` |
| **Steps** | 1. Create A/B test with variation â†’ 2. Click "Visual Editor" tab â†’ 3. Modify element (click + edit) â†’ 4. Save |
| **Expected** | Visual editor loads the page in an iframe. Changes apply in real-time. Save persists changes. |
| **Validation** | `experimentPage.isSuccessDisplayed() == true` |

### TC-ABT-005 â€¢ Code Editor Variation Editing (CSS/JS)

| Field | Value |
|-------|-------|
| **Priority** | P1 | **FR ID** | FR3 | **Auto** | âœ… `CodeEditorTest#testCodeEditorForVariations` |
| **Test Data** | CSS: `body { background-color: #f5f5f5; }` |
| **Steps** | 1. Create A/B test â†’ 2. Add variation â†’ 3. Click "Code Editor" â†’ 4. Enter CSS/JS â†’ 5. Apply |
| **Expected** | Code injected successfully. Preview reflects the CSS/JS changes. |

### TC-ABT-006 â€¢ Toggle Between Visual and Code Editors

| Field | Value |
|-------|-------|
| **Priority** | P1 | **FR ID** | FR3 | **Auto** | âœ… `CodeEditorTest#testToggleBetweenVisualAndCodeEditor` |
| **Steps** | 1. Open Visual Editor â†’ make change â†’ 2. Switch to Code Editor â†’ verify code reflects change â†’ 3. Switch back |
| **Expected** | Changes sync between editors. No data loss on toggle. |

### TC-ABT-007 â€¢ Set Primary Metric

| Field | Value |
|-------|-------|
| **Priority** | P0 | **FR ID** | FR1 | **Auto** | âœ… `CreateABTestTest#testCreateABTestWithValidData` |
| **Test Data** | Metric = "Click-Through Rate" |
| **Steps** | 1. Create A/B test â†’ 2. Select primary metric from dropdown |
| **Expected** | Metric selected. Goal configuration UI updates accordingly. |

### TC-ABT-008 â€¢ Add Multiple Goals

| Field | Value |
|-------|-------|
| **Priority** | P1 | **FR ID** | FR1 | **Auto** | âœ… `CreateABTestTest#testCreateABTestWithValidData` |
| **Steps** | 1. Create A/B test â†’ 2. Add goal "Revenue per Visitor" â†’ 3. Add goal "Time on Page" |
| **Expected** | Both goals listed. Primary metric distinct from secondary goals. |

### TC-ABT-009 â€¢ Launch Experiment â†’ Status "Running"

| Field | Value |
|-------|-------|
| **Priority** | P0 | **FR ID** | FR1 | **Auto** | âœ… `CreateABTestTest#testLaunchABTest` |
| **Steps** | 1. Create A/B test â†’ Save Draft â†’ 2. Click "Launch" |
| **Expected** | Status changes from "Draft" to "Running". Traffic starts flowing. |
| **Validation** | `experimentPage.getExperimentStatus().equalsIgnoreCase("running")` |

### TC-ABT-010 â€¢ Pause Running Experiment

| Field | Value |
|-------|-------|
| **Priority** | P0 | **FR ID** | FR1 | **Auto** | âœ… `CreateABTestTest#testPauseRunningExperiment` |
| **Steps** | 1. Launch experiment â†’ 2. Click "Pause" |
| **Expected** | Status changes to "Paused". Visitors see control (original) only. |
| **Validation** | Status equals "paused" |

### TC-ABT-011 â€¢ Resume Paused Experiment

| Field | Value |
|-------|-------|
| **Priority** | P0 | **FR ID** | FR1 | **Auto** | âŒ Manual |
| **Steps** | 1. Pause running experiment â†’ 2. Click "Resume" |
| **Expected** | Status changes back to "Running". Existing data preserved, new visitors added. |

### TC-ABT-012 â€¢ Stop Experiment

| Field | Value |
|-------|-------|
| **Priority** | P0 | **FR ID** | FR1 | **Auto** | âœ… `CreateABTestTest#testStopExperimentAndDeclareWinner` |
| **Steps** | 1. Launch experiment â†’ 2. Click "Stop" |
| **Expected** | Status "Stopped". No more traffic allocated. Final results frozen. |

### TC-ABT-013 â€¢ Declare Winner After Statistical Significance

| Field | Value |
|-------|-------|
| **Priority** | P1 | **FR ID** | FR2 | **Auto** | âœ… `SmartStatsTest#testWinnerDeclaration` |
| **Precondition** | Experiment has reached 95% confidence for one variation |
| **Steps** | 1. Open experiment â†’ 2. Click "Declare Winner" â†’ 3. Select winning variation â†’ 4. Confirm |
| **Expected** | Selected variation marked as winner. All traffic now goes to winning variation. |

### TC-ABT-014 â€¢ Schedule Experiment with Dates

| Field | Value |
|-------|-------|
| **Priority** | P1 | **FR ID** | FR1 | **Auto** | âœ… `CreateABTestTest#testExperimentScheduling` |
| **Test Data** | start="2026-01-15", end="2026-02-15" |
| **Steps** | 1. Create A/B test â†’ 2. Click "Schedule" â†’ 3. Set start + end dates â†’ 4. Confirm |
| **Expected** | Schedule saved. Experiment auto-launches on start date, auto-stops on end date. |

### TC-ABT-015 â€¢ Preview Variations Across Devices

| Field | Value |
|-------|-------|
| **Priority** | P1 | **FR ID** | FR1 | **Auto** | âœ… (via preview) |
| **Precondition** | Experiment with at least one variation |
| **Steps** | 1. Open experiment â†’ 2. Click "Preview" â†’ 3. Toggle between device sizes (Desktop/Mobile/Tablet) |
| **Expected** | Variation renders correctly on each device size. Device toggle changes viewport. |

### TC-ABT-016 â€¢ Set Traffic Allocation Percentage

| Field | Value |
|-------|-------|
| **Priority** | P1 | **FR ID** | FR1 | **Auto** | âœ… `CreateABTestTest` |
| **Test Data** | Slider value = 50 |
| **Steps** | 1. In experiment config â†’ 2. Drag traffic allocation slider to 50% â†’ 3. Save |
| **Expected** | 50% visitors see control, 50% see variation. Visual indicator updates. |

### TC-ABT-017 â€¢ Duplicate Existing Experiment

| Field | Value |
|-------|-------|
| **Priority** | P2 | **FR ID** | FR1 | **Auto** | âŒ Manual |
| **Steps** | 1. Open existing experiment â†’ 2. Click "Duplicate" â†’ 3. Rename â†’ 4. Save |
| **Expected** | New experiment created with same config. Original unchanged. |

### TC-ABT-018 â€¢ Delete Experiment in Draft Status

| Field | Value |
|-------|-------|
| **Priority** | P2 | **FR ID** | FR1 | **Auto** | âŒ Manual |
| **Precondition** | Experiment in "Draft" status |
| **Steps** | 1. Open draft experiment â†’ 2. Click "Delete" â†’ 3. Confirm |
| **Expected** | Experiment removed from list. Cannot be recovered. Running experiments cannot be deleted. |



---

# Split URL Testing â€” Test Cases

**File**: `TC-SPL-SplitURL.md` | **Module**: Split URL | **Total**: 4 TC | **Automated**: 3

---

### TC-SPL-001 â€¢ Create Split URL Test with 2+ Destination URLs

| Field | Value |
|-------|-------|
| **Priority** | P0 | **FR ID** | FR1 | **Auto** | âœ… `CreateSplitURLTest#testCreateSplitURLTest` |
| **Steps** | 1. Click "Create Experiment" â†’ "Split URL" â†’ 2. Enter control URL â†’ 3. Add 2+ variation URLs â†’ 4. Save |
| **Expected** | Experiment created with multiple URLs. Each variation maps to a different URL. |
| **Validation** | 3 total variations (control + 2) |

### TC-SPL-002 â€¢ Traffic Distribution Across Variations

| Field | Value |
|-------|-------|
| **Priority** | P1 | **FR ID** | FR1 | **Auto** | âœ… `CreateSplitURLTest#testSplitURLTrafficDistribution` |
| **Steps** | 1. Create Split URL with 2 variations â†’ 2. Set traffic allocation â†’ 3. Launch |
| **Expected** | Traffic split evenly (or per configured %) across all URLs. Distribution UI shows percentages. |

### TC-SPL-003 â€¢ Duplicate URL Validation

| Field | Value |
|-------|-------|
| **Priority** | P1 | **FR ID** | FR1 (negative) | **Auto** | âœ… `CreateSplitURLTest#testSplitURLWithDuplicateUrls` |
| **Test Data** | Variation URL = same as control URL |
| **Steps** | 1. Create Split URL â†’ 2. Add variation with identical URL to control |
| **Expected** | Warning/error: "URL must be different from control". Not allowed to save. |

### TC-SPL-004 â€¢ Split URL with Different Domains

| Field | Value |
|-------|-------|
| **Priority** | P1 | **FR ID** | FR1 | **Auto** | âŒ Manual |
| **Test Data** | control="https://example.com/page-a", variation="https://example.org/page-b" |
| **Steps** | 1. Create Split URL with URLs on different domains |
| **Expected** | Test saves and launches. Cross-domain tracking works (cookies handled correctly). |



---

# Multivariate Testing â€” Test Cases

**File**: `TC-MVT-Multivariate.md` | **Module**: MVT | **Total**: 5 TC | **Automated**: 3

---

### TC-MVT-001 â€¢ Create MVT with 4+ Combination Variations

| Field | Value |
|-------|-------|
| **Priority** | P0 | **FR ID** | FR1 | **Auto** | âœ… `CreateMVTestTest#testCreateMultivariateTest` |
| **Test Data** | 2 headlines Ã— 2 CTA colors = 4 combinations |
| **Steps** | 1. Create MVT â†’ 2. Name experiment â†’ 3. Add 4 combination variations â†’ 4. Select primary metric â†’ 5. Save Draft |
| **Expected** | 5 total entries (control + 4 combinations). All combinations listed. |
| **Validation** | `experimentPage.getVariationCount() == 5` |

### TC-MVT-002 â€¢ Preview All MVT Combinations

| Field | Value |
|-------|-------|
| **Priority** | P1 | **FR ID** | FR1 | **Auto** | âœ… `CreateMVTestTest#testMVTCombinationPreview` |
| **Steps** | 1. Create MVT â†’ 2. Add combinations â†’ 3. Click "Preview" |
| **Expected** | Each combination renders correctly in preview. Switch between combos shows correct content. |

### TC-MVT-003 â€¢ Configure Multiple Goals for MVT

| Field | Value |
|-------|-------|
| **Priority** | P1 | **FR ID** | FR1 | **Auto** | âœ… `CreateMVTestTest#testMVTWithMultipleGoals` |
| **Steps** | 1. Create MVT â†’ 2. Add primary metric + 2 secondary goals |
| **Expected** | Multiple goals configured. Results will show performance per goal per combination. |

### TC-MVT-004 â€¢ MVT Results Broken Down by Factor Interaction

| Field | Value |
|-------|-------|
| **Priority** | P2 | **FR ID** | FR2 | **Auto** | âŒ Manual |
| **Steps** | 1. Launch MVT â†’ 2. Wait for data â†’ 3. Open results |
| **Expected** | Results show main effects (per factor) AND interaction effects (combination). SmartStats calculates significance per factor. |

### TC-MVT-005 â€¢ MVT with > 8 Combinations

| Field | Value |
|-------|-------|
| **Priority** | P2 | **FR ID** | FR1 | **Auto** | âŒ Manual |
| **Test Data** | 3 factors Ã— 3 levels = 9 combinations |
| **Steps** | 1. Create MVT with 9+ combinations â†’ 2. Observe UI performance |
| **Expected** | UI handles large number of combinations without lag. All combinations previewable. |



---

# SmartStats Engine â€” Test Cases

**File**: `TC-SS-SmartStats.md` | **Module**: SmartStats | **Total**: 6 TC | **Automated**: 4

---

### TC-SS-001 â€¢ SmartStats Panel Visible in Experiment Details

| Field | Value |
|-------|-------|
| **Priority** | P0 | **FR ID** | FR2 | **Auto** | âœ… `SmartStatsTest#testSmartStatsBayesianAnalysis` |
| **Precondition** | Experiment has collected visitor data |
| **Steps** | 1. Open launched experiment â†’ 2. Scroll to results section |
| **Expected** | SmartStats panel visible. Shows Bayesian analysis with probability curves. |

### TC-SS-002 â€¢ Bayesian Confidence Score Displayed

| Field | Value |
|-------|-------|
| **Priority** | P0 | **FR ID** | FR2 | **Auto** | âœ… `SmartStatsTest#testConfidenceScoreDisplay` |
| **Steps** | 1. Open experiment with data â†’ 2. Observe confidence column |
| **Expected** | Each variation has a confidence score (percentage). Higher score = more likely to be better than control. |
| **Validation** | `experimentPage.getConfidenceScore()` is a non-empty percentage string |

### TC-SS-003 â€¢ Conversion Rate, Lift, and Visitors Count Shown

| Field | Value |
|-------|-------|
| **Priority** | P0 | **FR ID** | FR2 | **Auto** | âœ… `SmartStatsTest#testExperimentSummaryStats` |
| **Steps** | 1. Open experiment â†’ 2. View summary table |
| **Expected** | Columns: Variation, Visitors, Conversions, Conversion Rate, Lift, Confidence. All populated. |

### TC-SS-004 â€¢ Winner Automatically Suggested at 95% Confidence

| Field | Value |
|-------|-------|
| **Priority** | P1 | **FR ID** | FR2 | **Auto** | âœ… `SmartStatsTest#testWinnerDeclaration` |
| **Precondition** | Enough data collected for 95% confidence |
| **Steps** | 1. Monitor experiment â†’ 2. When confidence reaches 95% |
| **Expected** | System highlights the winning variation. "Declare Winner" button becomes prominent. Suggested winner label shown. |

### TC-SS-005 â€¢ Manual Winner Override

| Field | Value |
|-------|-------|
| **Priority** | P1 | **FR ID** | FR2 | **Auto** | âŒ Manual |
| **Steps** | 1. Open experiment â†’ 2. Click "Declare Winner" â†’ 3. Select a variation that is NOT the statistically suggested one |
| **Expected** | System allows manual override. Confirmation dialog: "Are you sure?" Winner declared as selected. |

### TC-SS-006 â€¢ SmartStats Data Refreshes in Real-Time

| Field | Value |
|-------|-------|
| **Priority** | P1 | **FR ID** | FR2 | **Auto** | âŒ Manual |
| **Precondition** | Experiment is running and receiving traffic |
| **Steps** | 1. Open experiment results â†’ 2. Wait 30 seconds without refreshing |
| **Expected** | Visitor count and confidence score update automatically. No manual refresh needed. |



---

# Code Editor â€” Test Cases

**File**: `TC-CDE-CodeEditor.md` | **Module**: Code Editor | **Total**: 4 TC | **Automated**: 3

---

### TC-CDE-001 â€¢ Code Editor Accepts CSS for Variation Changes

| Field | Value |
|-------|-------|
| **Priority** | P0 | **FR ID** | FR3 | **Auto** | âœ… `CodeEditorTest#testCodeEditorForVariations` |
| **Test Data** | `body { background-color: #f5f5f5; } .cta-button { color: #ffffff; background-color: #28a745; }` |
| **Steps** | 1. Open A/B test â†’ 2. Add variation â†’ 3. Click "Code Editor" â†’ 4. Enter CSS â†’ 5. Apply & Save |
| **Expected** | CSS injected. Preview shows the styled page. Changes persisted on save. |

### TC-CDE-002 â€¢ Code Editor Accepts JavaScript

| Field | Value |
|-------|-------|
| **Priority** | P1 | **FR ID** | FR3 | **Auto** | âœ… `CodeEditorTest#testCodeEditorWithJavaScript` |
| **Test Data** | `document.querySelector('.hero-title').innerText = 'Welcome to Optimized VWO';` |
| **Steps** | 1. Open Code Editor â†’ 2. Enter JS â†’ 3. Apply |
| **Expected** | JavaScript executes in the preview. DOM changes visible. |

### TC-CDE-003 â€¢ Toggle Between Visual and Code Editors

| Field | Value |
|-------|-------|
| **Priority** | P1 | **FR ID** | FR3 | **Auto** | âœ… `CodeEditorTest#testToggleBetweenVisualAndCodeEditor` |
| **Steps** | 1. Open Visual Editor â†’ make a change â†’ 2. Switch to Code Editor â†’ 3. Switch back to Visual Editor |
| **Expected** | Code changes visible when switching to Code Editor. Visual Editor reflects the code. No data corruption on toggle. |

### TC-CDE-004 â€¢ Syntax Error Handling in Code Editor

| Field | Value |
|-------|-------|
| **Priority** | P1 | **FR ID** | FR3 | **Auto** | âŒ Manual |
| **Test Data** | Malformed CSS: `body { background-color: ; }` |
| **Steps** | 1. Open Code Editor â†’ 2. Enter invalid CSS/JS â†’ 3. Apply |
| **Expected** | Error message: "Invalid syntax at line X". Code NOT applied. Variation retains last valid state. |



---

# Audience Targeting â€” Test Cases

**File**: `TC-AUD-Audience.md` | **Module**: Audience | **Total**: 6 TC | **Automated**: 5

---

### TC-AUD-001 â€¢ Target by Geography

| Field | Value |
|-------|-------|
| **Priority** | P0 | **FR ID** | FR5 | **Auto** | âœ… `AudienceTargetingTest#testAudienceByGeography` |
| **Test Data** | Geography = "United States" |
| **Steps** | 1. Create A/B test â†’ 2. Go to Audience section â†’ 3. Add condition: Geography = United States â†’ 4. Save |
| **Expected** | Audience condition saved. Only US visitors will see the experiment. |

### TC-AUD-002 â€¢ Target by Device Type

| Field | Value |
|-------|-------|
| **Priority** | P0 | **FR ID** | FR5 | **Auto** | âœ… `AudienceTargetingTest#testAudienceByDeviceType` |
| **Test Data** | Device = "Mobile" |
| **Steps** | 1. Add audience condition: Device = Mobile â†’ 2. Save |
| **Expected** | Only mobile visitors see the experiment. |

### TC-AUD-003 â€¢ Target by Behavior (Returning vs New)

| Field | Value |
|-------|-------|
| **Priority** | P0 | **FR ID** | FR5 | **Auto** | âœ… `AudienceTargetingTest#testAudienceByBehavior` |
| **Test Data** | Behavior = "Returning Visitor" |
| **Steps** | 1. Add audience condition: Behavior = Returning Visitor â†’ 2. Save |
| **Expected** | Only logged-in returning visitors see the experiment. New visitors see control. |

### TC-AUD-004 â€¢ Target by Custom Attribute

| Field | Value |
|-------|-------|
| **Priority** | P1 | **FR ID** | FR5 | **Auto** | âœ… `AudienceTargetingTest` |
| **Test Data** | Custom attribute: `plan_type = enterprise` |
| **Steps** | 1. Add audience condition â†’ Custom Attribute â†’ 2. Set key=plan_type, value=enterprise â†’ 3. Save |
| **Expected** | Condition saved. Only visitors with matching custom attribute are targeted. |

### TC-AUD-005 â€¢ Combine Multiple Conditions (AND Logic)

| Field | Value |
|-------|-------|
| **Priority** | P1 | **FR ID** | FR5 | **Auto** | âœ… `AudienceTargetingTest#testMultipleAudienceConditions` |
| **Test Data** | Geography=US AND Device=Desktop AND Behavior=New Visitor |
| **Steps** | 1. Add 3 audience conditions â†’ 2. Save |
| **Expected** | All conditions apply with AND logic. Audience preview shows reduced reach. Narrows down to visitors matching ALL conditions. |

### TC-AUD-006 â€¢ Audience Preview Shows Estimated Reach

| Field | Value |
|-------|-------|
| **Priority** | P1 | **FR ID** | FR5 | **Auto** | âœ… `AudienceTargetingTest#testAudiencePreviewReachEstimate` |
| **Steps** | 1. Add audience condition â†’ 2. Observe audience preview panel |
| **Expected** | Preview shows: "Estimated reach: X% of all visitors (Y visitors/month)". Reach updates in real-time as conditions change. |



---

# Heatmaps â€” Test Cases

**File**: `TC-HTM-Heatmaps.md` | **Module**: Insights - Heatmaps | **Total**: 6 TC | **Automated**: 5

---

### TC-HTM-001 â€¢ Generate Click Heatmap for Valid Page URL

| Field | Value |
|-------|-------|
| **Priority** | P0 | **FR ID** | FR4 | **Auto** | âœ… `HeatmapTest#testGenerateClickHeatmap` |
| **Test Data** | URL = "https://app.vwo.com/", Type = "Click Heatmap" |
| **Steps** | 1. Navigate to Insights â†’ Heatmaps â†’ 2. Enter page URL â†’ 3. Select "Click Heatmap" â†’ 4. Click "Generate" |
| **Expected** | Heatmap overlay renders on the page. Click hotspots color-coded (red=most clicks, blue=least). Tooltip shows click count on hover. |
| **Validation** | `insightsPage.isHeatmapDisplayed() == true` |

### TC-HTM-002 â€¢ Generate Scroll Heatmap with Depth Overlay

| Field | Value |
|-------|-------|
| **Priority** | P0 | **FR ID** | FR4 | **Auto** | âœ… `HeatmapTest#testGenerateScrollHeatmap` |
| **Steps** | 1. Select "Scroll Heatmap" â†’ 2. Enter URL â†’ 3. Generate |
| **Expected** | Scroll depth overlay shows % of visitors who scrolled to each page section. "Fold" line visible at common breakpoints. |

### TC-HTM-003 â€¢ Generate Move Heatmap

| Field | Value |
|-------|-------|
| **Priority** | P1 | **FR ID** | FR4 | **Auto** | âœ… `HeatmapTest#testMoveHeatmapGeneration` |
| **Steps** | 1. Select "Move Heatmap" â†’ 2. Generate |
| **Expected** | Mouse movement trails visualized. Areas with most cursoråœç•™ highlighted. |

### TC-HTM-004 â€¢ Filter Heatmap by Device Type

| Field | Value |
|-------|-------|
| **Priority** | P1 | **FR ID** | FR4 | **Auto** | âœ… `HeatmapTest#testHeatmapDeviceFiltering` |
| **Steps** | 1. Generate heatmap â†’ 2. Filter by "Mobile" â†’ 3. Filter by "Desktop" |
| **Expected** | Heatmap refreshes per device. Mobile heatmap shows different click patterns (thumb zone). Desktop shows wider spread. |

### TC-HTM-005 â€¢ Invalid URL Shows Graceful Error

| Field | Value |
|-------|-------|
| **Priority** | P1 | **FR ID** | FR4 (negative) | **Auto** | âœ… `HeatmapTest#testHeatmapWithInvalidUrl` |
| **Test Data** | URL = "https://invalid-page-12345.com" |
| **Steps** | 1. Enter unreachable URL â†’ 2. Generate |
| **Expected** | Error: "Unable to load page. Please verify the URL is accessible." Heatmap NOT generated. |
| **Validation** | `insightsPage.isHeatmapDisplayed() == false` |

### TC-HTM-006 â€¢ Heatmap Overlay Disappears on Exiting Heatmap View

| Field | Value |
|-------|-------|
| **Priority** | P2 | **FR ID** | FR4 | **Auto** | âŒ Manual |
| **Steps** | 1. Generate heatmap â†’ 2. Close heatmap view / navigate away |
| **Expected** | Page returns to normal. No residual overlay elements. Original page layout unchanged. |



---

# Session Recordings â€” Test Cases

**File**: `TC-REC-SessionRecordings.md` | **Module**: Insights - Recordings | **Total**: 5 TC | **Automated**: 4

---

### TC-REC-001 â€¢ Session Recordings List Loads with Metadata

| Field | Value |
|-------|-------|
| **Priority** | P0 | **FR ID** | FR4 | **Auto** | âœ… `SessionRecordingTest#testViewSessionRecordings` |
| **Steps** | 1. Navigate to Insights â†’ Session Recordings |
| **Expected** | List of recorded sessions displayed. Each entry shows: User ID, Date, Duration, Pages visited, Actions count. Pagination if > 20. |
| **Validation** | `insightsPage.getSessionRecordingCount() >= 0` |

### TC-REC-002 â€¢ Play Session Recording Opens Player

| Field | Value |
|-------|-------|
| **Priority** | P0 | **FR ID** | FR4 | **Auto** | âœ… `SessionRecordingTest#testPlaySessionRecording` |
| **Steps** | 1. Click a recording â†’ 2. Click "Play" |
| **Expected** | Recording player opens. Screen replay starts automatically. Timeline progress bar visible. |

### TC-REC-003 â€¢ Playback Controls Functional

| Field | Value |
|-------|-------|
| **Priority** | P1 | **FR ID** | FR4 | **Auto** | âœ… `SessionRecordingTest#testSessionPlaybackControls` |
| **Steps** | 1. Play recording â†’ 2. Click Pause â†’ 3. Click Play â†’ 4. Change speed (2x, 4x) â†’ 5. Skip forward/backward |
| **Expected** | Pause: replay freezes. Play: resumes. Speed: replay accelerates audibly. Skip: jumps to that timeline position. |

### TC-REC-004 â€¢ Filter Recordings by Date Range

| Field | Value |
|-------|-------|
| **Priority** | P1 | **FR ID** | FR4 | **Auto** | âœ… `SessionRecordingTest#testFilterSessionRecordingsByDate` |
| **Test Data** | Date range = "2026-01-01 to 2026-01-31" |
| **Steps** | 1. Click date filter â†’ 2. Select start/end dates â†’ 3. Apply |
| **Expected** | List filtered to recordings within the date range. Count updates. Empty state if no recordings. |

### TC-REC-005 â€¢ Filter Recordings by User ID

| Field | Value |
|-------|-------|
| **Priority** | P1 | **FR ID** | FR4 | **Auto** | âŒ Manual |
| **Test Data** | Known user ID from a previous test session |
| **Steps** | 1. Enter user ID in search â†’ 2. Apply |
| **Expected** | Only recordings from that user displayed. Exact match required. |



---

# Funnels & Surveys â€” Test Cases

**File**: `TC-FUN-FunnelsSurveys.md` | **Module**: Insights - Funnels & Surveys | **Total**: 5 TC | **Automated**: 5

---

### TC-FUN-001 â€¢ Create Funnel with 2+ Steps

| Field | Value |
|-------|-------|
| **Priority** | P0 | **FR ID** | FR4 | **Auto** | âœ… `FunnelSurveyTest#testCreateFunnelWithMultipleSteps` |
| **Test Data** | Step URLs: `https://app.vwo.com/` â†’ `https://app.vwo.com/pricing` â†’ `https://app.vwo.com/signup` |
| **Steps** | 1. Navigate to Insights â†’ Funnels â†’ 2. Click "Create Funnel" â†’ 3. Add step URLs in order â†’ 4. Save |
| **Expected** | Funnel created with all steps. Steps ordered correctly. Save confirmation shown. |
| **Validation** | `insightsPage.getFunnelStepCount() >= 2` |

### TC-FUN-002 â€¢ Funnel Shows Drop-Off % at Each Step

| Field | Value |
|-------|-------|
| **Priority** | P0 | **FR ID** | FR4 | **Auto** | âœ… `FunnelSurveyTest#testFunnelDropOffVisualization` |
| **Precondition** | Funnel has collected visitor data |
| **Steps** | 1. Open funnel â†’ 2. Observe visualization |
| **Expected** | Each step shows: Visitors entering, % drop-off from previous step, % continued. Visual bar/arrow indicating flow. Highest drop-off clearly highlighted. |

### TC-FUN-003 â€¢ Create and Publish On-Page Survey

| Field | Value |
|-------|-------|
| **Priority** | P1 | **FR ID** | FR4 | **Auto** | âœ… `FunnelSurveyTest#testCreateOnPageSurvey` |
| **Test Data** | Question = "How likely are you to recommend VWO to a colleague?" |
| **Steps** | 1. Navigate to Insights â†’ Surveys â†’ 2. Click "Create Survey" â†’ 3. Enter question â†’ 4. Configure targeting â†’ 5. Publish |
| **Expected** | Survey created and published. Survey appears on target pages. Count increases by 1. |

### TC-FUN-004 â€¢ Survey Responses Collected in Dashboard

| Field | Value |
|-------|-------|
| **Priority** | P2 | **FR ID** | FR4 | **Auto** | âŒ Manual |
| **Precondition** | Survey is published and receiving responses |
| **Steps** | 1. Open published survey â†’ 2. View "Responses" tab |
| **Expected** | Response count > 0. Individual responses listed with timestamps. Aggregate data (NPS score, distribution chart). |

### TC-FUN-005 â€¢ Export Funnel Data to CSV

| Field | Value |
|-------|-------|
| **Priority** | P1 | **FR ID** | FR6 | **Auto** | âœ… `FunnelSurveyTest#testExportInsightsData` |
| **Steps** | 1. Open funnel â†’ 2. Click "Export" â†’ 3. Select CSV format |
| **Expected** | CSV file downloads. File contains: Step name, Visitors, Drop-off count, Drop-off %, Conversion %. Data matches UI. |



---

# Personalization â€” Test Cases

**File**: `TC-PER-Personalization.md` | **Module**: Personalize | **Total**: 10 TC | **Automated**: 8

---

### TC-PER-001 â€¢ Create Personalization Campaign with Segments

| Field | Value |
|-------|-------|
| **Priority** | P0 | **FR ID** | FR7 | **Auto** | âœ… `PersonalizationTest#testCreatePersonalizationCampaignWithSegments` |
| **Test Data** | Name="US Visitor Personalized Banner", Segment=Geography=US, Content="Welcome to VWO - Exclusive US Offer!" |
| **Steps** | 1. Navigate to Personalize â†’ 2. Click "Create Campaign" â†’ 3. Enter name â†’ 4. Add segment â†’ 5. Add personalized content â†’ 6. Save Draft |
| **Expected** | Campaign saved as "Draft". Appears in campaign list. |

### TC-PER-002 â€¢ Geographic Segment Targeting

| Field | Value |
|-------|-------|
| **Priority** | P0 | **FR ID** | FR7 | **Auto** | âœ… `PersonalizationTest#testCreatePersonalizationCampaignWithSegments` |
| **Test Data** | Segment = Geography: "United States" |
| **Steps** | 1. Create campaign â†’ 2. Add geo segment |
| **Expected** | Segment configured. Audience preview shows US-only reach. |

### TC-PER-003 â€¢ Behavioral Segment Targeting

| Field | Value |
|-------|-------|
| **Priority** | P0 | **FR ID** | FR7 | **Auto** | âœ… `PersonalizationTest#testLaunchBehavioralTargetingCampaign` |
| **Test Data** | Segment = Behavior: "Returning Visitor" |
| **Steps** | 1. Create campaign â†’ 2. Add behavioral segment â†’ 3. Add content â†’ 4. Launch |
| **Expected** | Campaign launched. Only returning visitors see personalized content. |

### TC-PER-004 â€¢ Device-Based Segment Targeting

| Field | Value |
|-------|-------|
| **Priority** | P1 | **FR ID** | FR7 | **Auto** | âœ… `PersonalizationTest#testDeviceBasedPersonalization` |
| **Test Data** | Segment = Device: "Mobile" |
| **Steps** | 1. Create campaign â†’ 2. Add device segment â†’ 3. Save |
| **Expected** | Campaign targets mobile users only. |

### TC-PER-005 â€¢ Configure Personalized Content

| Field | Value |
|-------|-------|
| **Priority** | P0 | **FR ID** | FR7 | **Auto** | âœ… `PersonalizationTest#testCreatePersonalizationCampaignWithSegments` |
| **Test Data** | Content = "Welcome to VWO - Exclusive US Offer!" |
| **Steps** | 1. Create campaign â†’ 2. Enter personalized content in editor (text/HTML/CSS) |
| **Expected** | Content saved. Content renders correctly in preview. |

### TC-PER-006 â€¢ Preview Personalization Before Launch

| Field | Value |
|-------|-------|
| **Priority** | P1 | **FR ID** | FR7 | **Auto** | âœ… `PersonalizationTest#testPreviewPersonalizationCampaign` |
| **Steps** | 1. Create campaign with content â†’ 2. Click "Preview" |
| **Expected** | Preview panel shows the page with personalized content applied. "View as different segments" toggle works. |
| **Validation** | `personalizePage.isPersonalizedPreviewDisplayed() == true` |

### TC-PER-007 â€¢ Launch Campaign â†’ Status "Running"

| Field | Value |
|-------|-------|
| **Priority** | P0 | **FR ID** | FR7 | **Auto** | âœ… `PersonalizationTest#testLaunchBehavioralTargetingCampaign` |
| **Precondition** | Campaign has segments + content configured |
| **Steps** | 1. Click "Launch" |
| **Expected** | Status changes from "Draft" to "Running". Campaign starts serving personalized content. |

### TC-PER-008 â€¢ Pause Active Campaign

| Field | Value |
|-------|-------|
| **Priority** | P1 | **FR ID** | FR7 | **Auto** | âŒ Manual |
| **Steps** | 1. Open running campaign â†’ 2. Click "Pause" |
| **Expected** | Status changes to "Paused". Original (non-personalized) content served to all visitors. |

### TC-PER-009 â€¢ Set Traffic Percentage for Campaign

| Field | Value |
|-------|-------|
| **Priority** | P1 | **FR ID** | FR7 | **Auto** | âœ… `PersonalizationTest#testTrafficAllocationForCampaign` |
| **Test Data** | Traffic % = 50 |
| **Steps** | 1. Create campaign â†’ 2. Set traffic percentage slider to 50% â†’ 3. Save |
| **Expected** | 50% of qualifying visitors see personalized content. 50% see original. |

### TC-PER-010 â€¢ Campaign with Multiple Segments

| Field | Value |
|-------|-------|
| **Priority** | P1 | **FR ID** | FR7 | **Auto** | âœ… `PersonalizationTest#testMultipleSegmentsInCampaign` |
| **Test Data** | Geo=US + Behavior=New Visitor + Device=Desktop |
| **Steps** | 1. Create campaign â†’ 2. Add 3 segments (geo + behavior + device) â†’ 3. Save |
| **Expected** | All 3 segments combined with AND logic. Reach narrowed to intersection. Campaign saved successfully. |



---

# Reports & Dashboards â€” Test Cases

**File**: `TC-RPT-Reports.md` | **Module**: Reports | **Total**: 10 TC | **Automated**: 10

---

### TC-RPT-001 â€¢ Reports Page Loads with Summary Statistics

| Field | Value |
|-------|-------|
| **Priority** | P0 | **FR ID** | FR6 | **Auto** | âœ… `ReportsTest#testNavigateToReportsPage` |
| **Steps** | 1. Navigate to Reports |
| **Expected** | Reports dashboard loads. Summary cards visible: Active Experiments, Total Visitors, Avg Conversion Rate, Revenue Impact. |
| **Validation** | `reportsPage.isReportsPageDisplayed() == true` |

### TC-RPT-002 â€¢ Experiment Performance Table

| Field | Value |
|-------|-------|
| **Priority** | P0 | **FR ID** | FR6 | **Auto** | âœ… `ReportsTest#testReportsDisplayExperimentPerformance`, `testExperimentsTableColumns` |
| **Steps** | 1. Open Reports â†’ 2. View experiment table |
| **Expected** | Table columns: Name, Variations, Visitors, Conversions, Conversion Rate, Confidence, Lift, Revenue. Sortable by each column. Paginated. |
| **Validation** | Headers contain "Name", "Conversion", "Visitors" |

### TC-RPT-003 â€¢ Filter Reports by Status

| Field | Value |
|-------|-------|
| **Priority** | P1 | **FR ID** | FR6 | **Auto** | âœ… `ReportsTest#testFilterReportsByStatus` |
| **Test Data** | Filter = "Running" |
| **Steps** | 1. Click status filter â†’ 2. Select "Running" â†’ 3. Apply |
| **Expected** | Table shows only Running experiments. Count updates. Other statuses hidden. |

### TC-RPT-004 â€¢ Filter Reports by Project

| Field | Value |
|-------|-------|
| **Priority** | P1 | **FR ID** | FR6 | **Auto** | âœ… `ReportsTest#testFilterReportsByProject` |
| **Test Data** | Project = "Default Project" |
| **Steps** | 1. Click project filter â†’ 2. Select project â†’ 3. Apply |
| **Expected** | Data scoped to selected project. All metrics update. |

### TC-RPT-005 â€¢ Filter Reports by Date Range

| Field | Value |
|-------|-------|
| **Priority** | P1 | **FR ID** | FR6 | **Auto** | âœ… `ReportsTest#testDateRangeFilterOnReports` |
| **Test Data** | Range = "2026-01-01 to 2026-01-31" |
| **Steps** | 1. Open date picker â†’ 2. Select start + end â†’ 3. Apply |
| **Expected** | Charts + tables refresh to show data only within the date range. |

### TC-RPT-006 â€¢ Chart Visualizations Render

| Field | Value |
|-------|-------|
| **Priority** | P1 | **FR ID** | FR6 | **Auto** | âœ… `ReportsTest#testReportChartsDisplay` |
| **Steps** | 1. Open Reports â†’ 2. Observe chart area |
| **Expected** | Charts render: Conversion trend line, Experiment performance bar chart, Traffic distribution pie chart. Interactive (hover shows tooltip). |
| **Validation** | `reportsPage.isChartDisplayed() == true` |

### TC-RPT-007 â€¢ Export Report to CSV

| Field | Value |
|-------|-------|
| **Priority** | P1 | **FR ID** | FR6 | **Auto** | âœ… `ReportsTest#testExportReportToCSV` |
| **Steps** | 1. Click "Export" â†’ 2. Select "CSV" |
| **Expected** | File downloads with `.csv` extension. Contains all current table data (respecting filters). Columns match UI. |

### TC-RPT-008 â€¢ Export Report to PDF

| Field | Value |
|-------|-------|
| **Priority** | P1 | **FR ID** | FR6 | **Auto** | âœ… `ReportsTest#testExportReportToPDF` |
| **Steps** | 1. Click "Export" â†’ 2. Select "PDF" |
| **Expected** | PDF downloads. Contains Summary cards + Charts + Table. Formatted for printing. |

### TC-RPT-009 â€¢ Schedule Recurring Report Delivery

| Field | Value |
|-------|-------|
| **Priority** | P2 | **FR ID** | FR6 | **Auto** | âœ… `ReportsTest#testScheduleReportDelivery` |
| **Test Data** | Recipients = "qa-team@vwo.com", Frequency = "Weekly" |
| **Steps** | 1. Click "Schedule Report" â†’ 2. Enter email recipients â†’ 3. Select frequency (Daily/Weekly/Monthly) â†’ 4. Confirm |
| **Expected** | Scheduled report created. Confirmation: "Report scheduled successfully." Email sent at configured frequency. |

### TC-RPT-010 â€¢ Conversion Summary Shows Lift/Decline Trends

| Field | Value |
|-------|-------|
| **Priority** | P1 | **FR ID** | FR6 | **Auto** | âœ… `ReportsTest#testReportsDisplayExperimentPerformance` |
| **Steps** | 1. Open Reports â†’ 2. View conversion summary card |
| **Expected** | Summary shows: Current conversion rate, Change vs previous period (â†‘/â†“ arrow + percentage), Trend indicator (positive/negative/neutral). |



---

# Integrations â€” Test Cases

**File**: `TC-INT-Integrations.md` | **Module**: Integrations | **Total**: 3 TC | **Automated**: 2

---

### TC-INT-001 â€¢ Navigate to Integrations Listing Page

| Field | Value |
|-------|-------|
| **Priority** | P1 | **FR ID** | FR8 | **Auto** | âœ… `IntegrationsTest#testNavigateToIntegrations` |
| **Steps** | 1. Click "Integrations" in sidebar |
| **Expected** | Integrations page loads. Available connectors listed with search/filter. Categories: Analytics, CRM, Commerce, Data Platforms. |

### TC-INT-002 â€¢ Search for Integration Connector

| Field | Value |
|-------|-------|
| **Priority** | P2 | **FR ID** | FR8 | **Auto** | âŒ Manual |
| **Test Data** | Search term = "Salesforce" |
| **Steps** | 1. On Integrations page â†’ 2. Type connector name in search |
| **Expected** | Matching connectors filtered in real-time. Partial match works ("Sales" finds "Salesforce"). No results message if not found. |

### TC-INT-003 â€¢ Connect to Google Analytics

| Field | Value |
|-------|-------|
| **Priority** | P2 | **FR ID** | FR8 | **Auto** | âŒ Manual |
| **Steps** | 1. Click Google Analytics connector â†’ 2. Click "Connect" â†’ 3. Authorize via OAuth â†’ 4. Select GA property â†’ 5. Save |
| **Expected** | Connection established. Status shows "Connected". Data sync begins. Experiments created in VWO appear in GA as annotations. |



---

# Plan Management â€” Test Cases

**File**: `TC-PLN-PlanManagement.md` | **Module**: Plan | **Total**: 3 TC | **Automated**: 2

---

### TC-PLN-001 â€¢ Access Plan/Program Management Dashboard

| Field | Value |
|-------|-------|
| **Priority** | P1 | **FR ID** | FR9 | **Auto** | âœ… `PlanManagementTest#testNavigateToPlanManagement` |
| **Steps** | 1. Click "Plan" in sidebar |
| **Expected** | Plan dashboard loads. Kanban-style board visible. Columns: Backlog, To Do, In Progress, Review, Done. Experiment cards in each column. |

### TC-PLN-002 â€¢ Create Experiment Backlog Task

| Field | Value |
|-------|-------|
| **Priority** | P2 | **FR ID** | FR9 | **Auto** | âŒ Manual |
| **Test Data** | Task name = "Test homepage hero section redesign" |
| **Steps** | 1. Click "Add Task" in Backlog column â†’ 2. Enter title â†’ 3. Assign owner â†’ 4. Set priority â†’ 5. Add description/hypothesis â†’ 6. Save |
| **Expected** | Task card appears in Backlog column. Card shows title, owner avatar, priority badge. |

### TC-PLN-003 â€¢ Kanban Column Drag and Drop

| Field | Value |
|-------|-------|
| **Priority** | P2 | **FR ID** | FR9 | **Auto** | âŒ Manual |
| **Steps** | 1. Drag a task card from "Backlog" â†’ 2. Drop into "To Do" column |
| **Expected** | Card moves to "To Do". Status updated. Column counts update. Change persists on page refresh. |



---

# Booking API â€” Health Check â€” Test Cases

**File**: `api/TC-PNG-HealthCheck.md` | **Endpoint**: `GET /ping` | **Total**: 6 TC | **Automated**: 6

---

### TC-PNG-001 â€¢ Health Check Returns 201 Created

| Field | Value |
|-------|-------|
| **Priority** | P0 | **API Ref** | `GET /ping` | **Auto** | âœ… `HealthCheckTest#testHealthCheckReturns201` |
| **Steps** | `GET https://restful-booker.herokuapp.com/ping` |
| **Expected** | Status: `201 Created`. Body: empty. |

### TC-PNG-002 â€¢ Response Time Under 500ms

| Field | Value |
|-------|-------|
| **Priority** | P0 | **API Ref** | `GET /ping` | **Auto** | âœ… `HealthCheckTest#testHealthCheckResponseTime` |
| **Steps** | `GET /ping` â€” measure response time |
| **Expected** | `response.time < 500ms` |

### TC-PNG-003 â€¢ Responds Without Authentication

| Field | Value |
|-------|-------|
| **Priority** | P0 | **API Ref** | `GET /ping` | **Auto** | âœ… `HealthCheckTest#testHealthCheckNoAuth` |
| **Steps** | `GET /ping` (no Auth header) |
| **Expected** | Status: `201`. Endpoint is public. |

### TC-PNG-004 â€¢ Rejects POST/PUT/DELETE (405)

| Field | Value |
|-------|-------|
| **Priority** | P1 | **API Ref** | `GET /ping` | **Auto** | âœ… `HealthCheckTest#testHealthCheckRejectsUnsupportedMethods` |
| **Steps** | `POST /ping`, `PUT /ping`, `DELETE /ping`, `PATCH /ping` |
| **Expected** | Each returns `405 Method Not Allowed`. |

### TC-PNG-005 â€¢ Consecutive 10 Rapid Calls

| Field | Value |
|-------|-------|
| **Priority** | P1 | **API Ref** | `GET /ping` | **Auto** | âœ… `HealthCheckTest#testHealthCheckConsecutiveCalls` |
| **Steps** | Call `GET /ping` 10 times in rapid succession |
| **Expected** | All 10 return `201`. No rate limiting. No failures. |

### TC-PNG-006 â€¢ Response Headers Present

| Field | Value |
|-------|-------|
| **Priority** | P1 | **API Ref** | `GET /ping` | **Auto** | âœ… `HealthCheckTest#testHealthCheckResponseHeaders` |
| **Steps** | `GET /ping` â€” inspect headers |
| **Expected** | Headers present: `Content-Type`, `Server`, `Date`, `Content-Length` |



---

# Booking API â€” Authentication â€” Test Cases

**File**: `api/TC-AUTH-ApiAuth.md` | **Endpoint**: `POST /auth` | **Total**: 5 TC | **Automated**: 5

---

### TC-AUTH-020 â€¢ Valid Credentials Return 200 with Token

| Field | Value |
|-------|-------|
| **Priority** | P0 | **API Ref** | `POST /auth` | **Auto** | âœ… `AuthTest#testAuthWithValidCredentials` |
| **Test Data** | `{"username": "admin", "password": "password123"}` |
| **Steps** | `POST /auth` with valid JSON body |
| **Expected** | Status `200`. Response: `{"token": "<non-empty-string>"}` |

### TC-AUTH-021 â€¢ Token is Non-Empty String

| Field | Value |
|-------|-------|
| **Priority** | P0 | **API Ref** | `POST /auth` | **Auto** | âœ… `AuthTest#testTokenIsNonEmptyString` |
| **Steps** | Extract token from auth response |
| **Expected** | `token` is not null, not empty, and is a string. |

### TC-AUTH-022 â€¢ Invalid Credentials Handling

| Field | Value |
|-------|-------|
| **Priority** | P1 | **API Ref** | `POST /auth` | **Auto** | âœ… `AuthTest#testAuthWithInvalidCredentials` |
| **Test Data** | `{"username": "invalid", "password": "invalid"}` |
| **Steps** | `POST /auth` with bad credentials |
| **Expected** | Status `200` but token may not work for write operations. System behavior documented. |

### TC-AUTH-023 â€¢ Empty Credentials

| Field | Value |
|-------|-------|
| **Priority** | P1 | **API Ref** | `POST /auth` | **Auto** | âœ… `AuthTest#testAuthWithEmptyCredentials` |
| **Test Data** | `{"username": "", "password": ""}` |
| **Steps** | `POST /auth` with empty fields |
| **Expected** | Status `400 Bad Request` or `200`. Error message if 400. |

### TC-AUTH-024 â€¢ Missing Content-Type Header

| Field | Value |
|-------|-------|
| **Priority** | P1 | **API Ref** | `POST /auth` | **Auto** | âœ… `AuthTest#testAuthMissingContentType` |
| **Steps** | Send POST /auth with JSON body but NO `Content-Type: application/json` header |
| **Expected** | Status `400 Bad Request` or `415 Unsupported Media Type`. |



---

# Booking API â€” Get All Bookings â€” Test Cases

**File**: `api/TC-BOK-GetAllBookings.md` | **Endpoint**: `GET /booking` | **Total**: 10 TC | **Automated**: 10

---

### TC-BOK-001 â€¢ Returns 200 OK with Booking IDs Array

| Field | Value |
|-------|-------|
| **Priority** | P0 | **API Ref** | `GET /booking` | **Auto** | âœ… `GetAllBookingTest#testGetAllBookingsReturns200` |
| **Steps** | `GET /booking` |
| **Expected** | Status `200`. Body: `[{"bookingid": 1}, {"bookingid": 2}, ...]` |

### TC-BOK-002 â€¢ Non-Empty JSON Array

| Field | Value |
|-------|-------|
| **Priority** | P0 | **API Ref** | `GET /booking` | **Auto** | âœ… `GetAllBookingTest#testGetAllBookingsReturnsNonEmptyArray` |
| **Steps** | `GET /booking` |
| **Expected** | Response is a JSON array with at least 1 element. |

### TC-BOK-003 â€¢ bookingid is Positive Integer

| Field | Value |
|-------|-------|
| **Priority** | P0 | **API Ref** | `GET /booking` | **Auto** | âœ… `GetAllBookingTest#testBookingIdsAreIntegers` |
| **Steps** | `GET /booking` â€” validate each `bookingid` |
| **Expected** | Every `bookingid` is a positive integer > 0. |

### TC-BOK-004 â€¢ Booking IDs Are Unique

| Field | Value |
|-------|-------|
| **Priority** | P1 | **API Ref** | `GET /booking` | **Auto** | âœ… `GetAllBookingTest#testBookingIdsAreUnique` |
| **Steps** | Extract all `bookingid` values |
| **Expected** | Count of distinct IDs == total count. No duplicates. |

### TC-BOK-005 â€¢ Filter by firstname

| Field | Value |
|-------|-------|
| **Priority** | P1 | **API Ref** | `GET /booking?firstname=John` | **Auto** | âœ… `GetAllBookingTest#testFilterByFirstname` |
| **Test Data** | `?firstname=John` |
| **Steps** | `GET /booking?firstname=John` |
| **Expected** | Status `200`. Results contain only bookings where `firstname == "John"`. |

### TC-BOK-006 â€¢ Filter by lastname

| Field | Value |
|-------|-------|
| **Priority** | P1 | **API Ref** | `GET /booking?lastname=Smith` | **Auto** | âœ… `GetAllBookingTest#testFilterByLastname` |
| **Test Data** | `?lastname=Smith` |

### TC-BOK-007 â€¢ Filter by checkin Date

| Field | Value |
|-------|-------|
| **Priority** | P1 | **API Ref** | `GET /booking?checkin=2026-01-01` | **Auto** | âœ… `GetAllBookingTest#testFilterByCheckin` |
| **Test Data** | `?checkin=2026-01-01` |
| **Expected** | Status `200`. Results have `bookingdates.checkin >= 2026-01-01`. |

### TC-BOK-008 â€¢ Filter by checkout Date

| Field | Value |
|-------|-------|
| **Priority** | P1 | **API Ref** | `GET /booking?checkout=2026-02-01` | **Auto** | âœ… `GetAllBookingTest#testFilterByCheckout` |

### TC-BOK-009 â€¢ Combined Filters

| Field | Value |
|-------|-------|
| **Priority** | P1 | **API Ref** | `GET /booking?firstname=John&checkin=2026-01-01` | **Auto** | âœ… `GetAllBookingTest#testCombinedFilters` |
| **Expected** | Results match BOTH conditions (AND logic). |

### TC-BOK-010 â€¢ Invalid Filter Returns Empty Array

| Field | Value |
|-------|-------|
| **Priority** | P1 | **API Ref** | `GET /booking` | **Auto** | âœ… `GetAllBookingTest#testInvalidFilterReturnsEmpty` |
| **Test Data** | `?firstname=NonExistentName_XYZ` |
| **Expected** | Status `200`. Body: `[]` (empty array). |



---

# Booking API â€” Get Single Booking â€” Test Cases

**File**: `api/TC-BOK-GetSingleBooking.md` | **Endpoint**: `GET /booking/{id}` | **Total**: 12 TC | **Automated**: 12

---

### TC-BOK-011 â€¢ Valid ID Returns 200 with Full Booking

| Field | Value |
|-------|-------|
| **Priority** | P0 | **Auto** | âœ… `GetSingleBookingTest#testGetBookingByIdReturns200` |
| **Steps** | `GET /booking/1` |
| **Expected** | Status `200`. Full booking object returned. |

### TC-BOK-012 â€¢ All Required Fields Present

| Field | Value |
|-------|-------|
| **Priority** | P0 | **Auto** | âœ… `GetSingleBookingTest#testGetBookingByIdHasAllFields` |
| **Expected** | Fields: `firstname`, `lastname`, `totalprice`, `depositpaid`, `bookingdates` (with `checkin`, `checkout`), `additionalneeds`. None null. |

### TC-BOK-013 â€¢ Name Fields Non-Empty

| Field | Value |
|-------|-------|
| **Priority** | P0 | **Auto** | âœ… `GetSingleBookingTest#testNameFieldsAreNonEmpty` |
| **Expected** | `firstname.trim().length() > 0`, `lastname.trim().length() > 0` |

### TC-BOK-014 â€¢ totalprice is Positive

| Field | Value |
|-------|-------|
| **Priority** | P0 | **Auto** | âœ… `GetSingleBookingTest#testTotalPriceIsPositive` |
| **Expected** | `totalprice > 0` |

### TC-BOK-015 â€¢ depositpaid is Boolean

| Field | Value |
|-------|-------|
| **Priority** | P0 | **Auto** | âœ… `GetSingleBookingTest#testDepositPaidIsBoolean` |
| **Expected** | `depositpaid == true || depositpaid == false` (not null, not string, not int) |

### TC-BOK-016 â€¢ Booking Dates in YYYY-MM-DD Format

| Field | Value |
|-------|-------|
| **Priority** | P0 | **Auto** | âœ… `GetSingleBookingTest#testBookingDatesFormat` |
| **Expected** | `checkin` and `checkout` match regex: `\d{4}-\d{2}-\d{2}` |

### TC-BOK-017 â€¢ checkout >= checkin

| Field | Value |
|-------|-------|
| **Priority** | P0 | **Auto** | âœ… `GetSingleBookingTest#testCheckoutAfterCheckin` |
| **Expected** | `checkout >= checkin` (string comparison on YYYY-MM-DD is valid) |

### TC-BOK-018 â€¢ Non-Existent ID Returns 404

| Field | Value |
|-------|-------|
| **Priority** | P0 | **Auto** | âœ… `GetSingleBookingTest#testNonExistentIdReturns404` |
| **Steps** | `GET /booking/99999` |
| **Expected** | Status `404 Not Found` |

### TC-BOK-019 â€¢ Negative ID Returns 400/404

| Field | Value |
|-------|-------|
| **Priority** | P1 | **Auto** | âœ… `GetSingleBookingTest#testNegativeId` |
| **Steps** | `GET /booking/-1` |
| **Expected** | Status `400` or `404` |

### TC-BOK-020 â€¢ String ID Returns 400/404

| Field | Value |
|-------|-------|
| **Priority** | P1 | **Auto** | âœ… `GetSingleBookingTest#testStringId` |
| **Steps** | `GET /booking/abc` |
| **Expected** | Status `400` or `404` |

### TC-BOK-021 â€¢ Special Character ID Returns 400/404

| Field | Value |
|-------|-------|
| **Priority** | P1 | **Auto** | âœ… `GetSingleBookingTest#testSpecialCharId` |
| **Steps** | `GET /booking/@#$` |
| **Expected** | Status `400` or `404` |

### TC-BOK-022 â€¢ Response Defaults to JSON

| Field | Value |
|-------|-------|
| **Priority** | P1 | **Auto** | âœ… `GetSingleBookingTest#testResponseDefaultsToJson` |
| **Steps** | `GET /booking/1` (no Accept header) |
| **Expected** | Status `200`. `Content-Type` contains `application/json`. |



---

# Booking API â€” Full Update (PUT) â€” Test Cases

**File**: `api/TC-PUT-UpdateBooking.md` | **Endpoint**: `PUT /booking/{id}` | **Total**: 10 TC | **Automated**: 10

---

### TC-PUT-001 â€¢ Full Update with Valid Auth Returns 200

| Field | Value |
|-------|-------|
| **Priority** | P0 | **Auto** | âœ… `UpdateBookingTest#testFullUpdateWithAuth` |
| **Test Data** | Auth = Basic `admin:password123`, Body = `{"firstname":"Jane","lastname":"Doe","totalprice":500,"depositpaid":false,"bookingdates":{"checkin":"2026-08-01","checkout":"2026-08-10"},"additionalneeds":"Lunch"}` |
| **Steps** | 1. Create booking â†’ 2. `PUT /booking/{id}` with full payload + auth |
| **Expected** | Status `200`. Updated values returned in response. |

### TC-PUT-002 â€¢ All Fields Updated Correctly

| Field | Value |
|-------|-------|
| **Priority** | P0 | **Auto** | âœ… `UpdateBookingTest#testFullUpdateAllFields` |
| **Steps** | PUT â†’ GET booking â†’ compare |
| **Expected** | Every field matches the PUT payload. Round-trip verified. |

### TC-PUT-003 â€¢ Response Body Matches Request (Round-Trip)

| Field | Value |
|-------|-------|
| **Priority** | P0 | **Auto** | âœ… `UpdateBookingTest#testFullUpdateRoundTrip` |
| **Expected** | PUT response body == request body (all fields). |

### TC-PUT-004 â€¢ Update Without Auth Returns 403

| Field | Value |
|-------|-------|
| **Priority** | P0 | **Auto** | âœ… `UpdateBookingTest#testFullUpdateWithoutAuth` |
| **Steps** | `PUT /booking/{id}` with valid body but NO Auth header |
| **Expected** | Status `403 Forbidden`. |

### TC-PUT-005 â€¢ Update with Invalid Auth Returns 403

| Field | Value |
|-------|-------|
| **Priority** | P1 | **Auto** | âœ… `UpdateBookingTest#testFullUpdateWithInvalidAuth` |
| **Steps** | `PUT /booking/{id}` with Base64("bad:creds") |
| **Expected** | Status `403` or `401`. |

### TC-PUT-006 â€¢ Update to Non-Existent ID

| Field | Value |
|-------|-------|
| **Priority** | P1 | **Auto** | âœ… `UpdateBookingTest#testFullUpdateNonExistentId` |
| **Steps** | `PUT /booking/99999` with valid auth + body |
| **Expected** | Status `404` or `405`. |

### TC-PUT-007 â€¢ Missing Required Fields

| Field | Value |
|-------|-------|
| **Priority** | P1 | **Auto** | âœ… `UpdateBookingTest#testFullUpdateMissingFields` |
| **Test Data** | `{"firstname": "OnlyFirst"}` (missing lastname, totalprice, etc.) |
| **Expected** | Status `400 Bad Request` OR `200` (depending on API design â€” document behavior). |

### TC-PUT-008 â€¢ Invalid Data Types

| Field | Value |
|-------|-------|
| **Priority** | P1 | **Auto** | âœ… `UpdateBookingTest#testFullUpdateInvalidDataTypes` |
| **Test Data** | `"totalprice": "not-a-number"` |
| **Expected** | Status `400` or `500`. Type mismatch should be rejected. |

### TC-PUT-009 â€¢ Extra Unknown Fields Accepted (Tolerant)

| Field | Value |
|-------|-------|
| **Priority** | P1 | **Auto** | âœ… `UpdateBookingTest#testFullUpdateWithExtraFields` |
| **Test Data** | Extra fields: `"extraField": "shouldBeIgnored"` |
| **Expected** | Status `200`. Extra fields ignored (not persisted). Backward compatible. |

### TC-PUT-010 â€¢ checkout Before checkin

| Field | Value |
|-------|-------|
| **Priority** | P1 | **Auto** | âœ… `UpdateBookingTest#testFullUpdateCheckoutBeforeCheckin` |
| **Test Data** | `checkin="2026-02-01"`, `checkout="2026-01-01"` |
| **Expected** | Status `400` OR system auto-corrects. |



---

# Booking API â€” Partial Update (PATCH) â€” Test Cases

**File**: `api/TC-PTC-PartialUpdate.md` | **Endpoint**: `PATCH /booking/{id}` | **Total**: 10 TC | **Automated**: 10

---

### TC-PTC-001 â€¢ Patch Single Field Returns 200

| Field | Value |
|-------|-------|
| **Priority** | P0 | **Auto** | âœ… `PartialUpdateBookingTest#testPartialUpdateSingleField` |
| **Test Data** | `{"firstname": "UpdatedName"}` |
| **Steps** | Create booking â†’ `PATCH /booking/{id}` with single field + auth |
| **Expected** | Status `200`. `firstname` updated. |

### TC-PTC-002 â€¢ Only Patched Field Changes, Others Unchanged

| Field | Value |
|-------|-------|
| **Priority** | P0 | **Auto** | âœ… `PartialUpdateBookingTest#testPartialUpdatePreservesOtherFields` |
| **Steps** | GET before â†’ PATCH `firstname` â†’ GET after |
| **Expected** | `firstname` changed. `lastname`, `totalprice`, `depositpaid`, `bookingdates`, `additionalneeds` all identical to pre-patch values. |

### TC-PTC-003 â€¢ Patch totalprice

| Field | Value |
|-------|-------|
| **Priority** | P0 | **Auto** | âœ… `PartialUpdateBookingTest#testPartialUpdateTotalPrice` |
| **Test Data** | `{"totalprice": 999}` |
| **Expected** | `totalprice` updated to 999. |

### TC-PTC-004 â€¢ Patch bookingdates.checkin

| Field | Value |
|-------|-------|
| **Priority** | P0 | **Auto** | âœ… `PartialUpdateBookingTest#testPartialUpdateCheckin` |
| **Test Data** | `{"bookingdates": {"checkin": "2026-12-01"}}` |
| **Expected** | Only `checkin` changes. `checkout` stays same. |

### TC-PTC-005 â€¢ Patch Without Auth Returns 403

| Field | Value |
|-------|-------|
| **Priority** | P0 | **Auto** | âœ… `PartialUpdateBookingTest#testPartialUpdateWithoutAuth` |
| **Expected** | Status `403 Forbidden`. |

### TC-PTC-006 â€¢ Empty Body Returns 400

| Field | Value |
|-------|-------|
| **Priority** | P1 | **Auto** | âœ… `PartialUpdateBookingTest#testPartialUpdateEmptyBody` |
| **Test Data** | `{}` |
| **Expected** | Status `400` or `200` (idempotent). |

### TC-PTC-007 â€¢ Patch Non-Existent ID Returns 404

| Field | Value |
|-------|-------|
| **Priority** | P1 | **Auto** | âœ… `PartialUpdateBookingTest#testPartialUpdateNonExistentId` |
| **Steps** | `PATCH /booking/99999` |
| **Expected** | Status `404` or `405`. |

### TC-PTC-008 â€¢ Invalid Field Name Ignored

| Field | Value |
|-------|-------|
| **Priority** | P1 | **Auto** | âœ… `PartialUpdateBookingTest#testPartialUpdateInvalidField` |
| **Test Data** | `{"nonexistentField": "shouldBeIgnored"}` |
| **Expected** | Status `200`. No error. Field ignored. |

### TC-PTC-009 â€¢ Null Value for Required Field

| Field | Value |
|-------|-------|
| **Priority** | P1 | **Auto** | âœ… `PartialUpdateBookingTest#testPartialUpdateNullValue` |
| **Test Data** | `{"firstname": null}` |
| **Expected** | Status `400` (reject null for required fields). |

### TC-PTC-010 â€¢ additionalneeds Set to Empty String

| Field | Value |
|-------|-------|
| **Priority** | P1 | **Auto** | âœ… `PartialUpdateBookingTest#testPartialUpdateEmptyAdditionalNeeds` |
| **Test Data** | `{"additionalneeds": ""}` |
| **Expected** | Status `200`. `additionalneeds` is empty string. |



---

# Booking API â€” Delete Booking â€” Test Cases

**File**: `api/TC-DEL-DeleteBooking.md` | **Endpoint**: `DELETE /booking/{id}` | **Total**: 8 TC | **Automated**: 8

---

### TC-DEL-001 â€¢ Delete with Valid Auth Returns 201

| Field | Value |
|-------|-------|
| **Priority** | P0 | **Auto** | âœ… `DeleteBookingTest#testDeleteWithValidAuth` |
| **Steps** | Create booking â†’ `DELETE /booking/{id}` with auth |
| **Expected** | Status `201 Created`. |

### TC-DEL-002 â€¢ Deleted Booking Returns 404 on GET

| Field | Value |
|-------|-------|
| **Priority** | P0 | **Auto** | âœ… `DeleteBookingTest#testDeletedBookingReturns404` |
| **Steps** | DELETE â†’ GET same ID |
| **Expected** | DELETE: `201`. GET: `404 Not Found`. |

### TC-DEL-003 â€¢ Delete Without Auth Returns 403

| Field | Value |
|-------|-------|
| **Priority** | P0 | **Auto** | âœ… `DeleteBookingTest#testDeleteWithoutAuth` |
| **Steps** | `DELETE /booking/{id}` (no Auth header) |
| **Expected** | Status `403 Forbidden`. |

### TC-DEL-004 â€¢ Delete with Invalid Auth Returns 403

| Field | Value |
|-------|-------|
| **Priority** | P0 | **Auto** | âœ… `DeleteBookingTest#testDeleteWithInvalidAuth` |
| **Steps** | `DELETE /booking/{id}` with Base64("bad:creds") |
| **Expected** | Status `403` or `401`. |

### TC-DEL-005 â€¢ Delete Non-Existent ID Returns 405

| Field | Value |
|-------|-------|
| **Priority** | P1 | **Auto** | âœ… `DeleteBookingTest#testDeleteNonExistentId` |
| **Steps** | `DELETE /booking/99999` with auth |
| **Expected** | Status `405 Method Not Allowed`. |

### TC-DEL-006 â€¢ Double Delete Returns 405

| Field | Value |
|-------|-------|
| **Priority** | P1 | **Auto** | âœ… `DeleteBookingTest#testDoubleDelete` |
| **Steps** | DELETE same ID twice |
| **Expected** | First: `201`. Second: `405` or `404`. |

### TC-DEL-007 â€¢ GET on Delete Endpoint Returns 200

| Field | Value |
|-------|-------|
| **Priority** | P0 | **Auto** | âœ… `DeleteBookingTest#testGetOnDeleteEndpoint` |
| **Steps** | `GET /booking/{id}` (verifying method safety) |
| **Expected** | Status `200`. |

### TC-DEL-008 â€¢ Create â†’ Delete â†’ Verify Cycle

| Field | Value |
|-------|-------|
| **Priority** | P1 | **Auto** | âœ… `DeleteBookingTest#testCreateDeleteVerifyCycle` |
| **Steps** | POST (create) â†’ GET (verify exists) â†’ DELETE â†’ GET (verify gone) |
| **Expected** | Full lifecycle works. No orphaned data. |



---

# Non-Functional â€” Performance â€” Test Cases

**File**: `nfr/TC-NFR-Performance.md` | **Module**: Performance | **Total**: 6 TC | **Automated**: 0

---

### TC-PRF-01 â€¢ Dashboard Page Load Time < 2s

| Field | Value |
|-------|-------|
| **Priority** | P0 | **NFR** | Performance | **Auto** | âŒ (needs Selenium + StopWatch) |
| **Precondition** | User logged in, cache cleared |
| **Steps** | 1. Start timer â†’ 2. Navigate to Dashboard â†’ 3. Wait for page fully loaded (all XHRs complete) â†’ 4. Stop timer |
| **Expected** | Total load time < 2000ms (2 seconds). |
| **Tool** | Selenium + `StopWatch` or Navigation Timing API |

### TC-PRF-02 â€¢ Experiment Creation/Save < 2s

| Field | Value |
|-------|-------|
| **Priority** | P0 | **NFR** | Performance | **Auto** | âŒ |
| **Steps** | 1. Create experiment â†’ 2. Measure time from "Save" click to success toast |
| **Expected** | < 2000ms for draft save. |

### TC-PRF-03 â€¢ Heatmap Generation < 5s

| Field | Value |
|-------|-------|
| **Priority** | P0 | **NFR** | Performance | **Auto** | âŒ |
| **Steps** | 1. Enter URL â†’ 2. Click "Generate Heatmap" â†’ 3. Measure until overlay renders |
| **Expected** | < 5000ms (5 seconds). |

### TC-PRF-04 â€¢ Report Page Load with 100+ Experiments < 3s

| Field | Value |
|-------|-------|
| **Priority** | P0 | **NFR** | Performance | **Auto** | âŒ |
| **Precondition** | Workspace has 100+ experiments |
| **Steps** | 1. Navigate to Reports â†’ 2. Measure full page load time |
| **Expected** | < 3000ms (3 seconds). Pagination/no-data states handle gracefully. |

### TC-PRF-05 â€¢ API Response Time < 500ms

| Field | Value |
|-------|-------|
| **Priority** | P0 | **NFR** | Performance | **Auto** | âŒ |
| **Steps** | Call each API endpoint and measure response time |
| **Criteria** | `GET /booking` < 500ms, `GET /booking/{id}` < 500ms, `POST /booking` < 1000ms |

### TC-PRF-06 â€¢ Concurrent Experiment Launches (50 Simultaneous)

| Field | Value |
|-------|-------|
| **Priority** | P0 | **NFR** | Performance | **Auto** | âŒ |
| **Steps** | 1. Create 50 draft experiments â†’ 2. Launch all simultaneously via API |
| **Expected** | No 500 errors. All 50 launch successfully. System recovers within 30s. |
| **Tool** | JMeter / Locust |



---

# Non-Functional â€” Security â€” Test Cases

**File**: `nfr/TC-NFR-Security.md` | **Module**: Security | **Total**: 6 TC | **Automated**: 0

---

### TC-SEC-01 â€¢ 2FA Enforcement for Enterprise Accounts

| Field | Value |
|-------|-------|
| **Priority** | P0 | **NFR** | Security | **Auto** | âŒ |
| **Precondition** | VWO account with 2FA enabled |
| **Steps** | 1. Login with valid credentials â†’ 2. Observe post-login screen |
| **Expected** | 2FA code input MANDATORY. User cannot skip/ bypass. Dashboard NOT accessible without 2FA. |

### TC-SEC-02 â€¢ RBAC â€” Admin vs Viewer Permissions

| Field | Value |
|-------|-------|
| **Priority** | P0 | **NFR** | Security | **Auto** | âŒ |
| **Test Data** | Two accounts: Admin role + Viewer role |
| **Steps** | 1. Login as Admin â†’ can create/launch/delete experiments â†’ 2. Login as Viewer â†’ observe options |
| **Expected** | Admin: full CRUD. Viewer: read-only (no Create/Edit/Delete buttons). API returns 403 for write operations. |

### TC-SEC-03 â€¢ Session Timeout After 30 Minutes

| Field | Value |
|-------|-------|
| **Priority** | P0 | **NFR** | Security | **Auto** | âŒ |
| **Steps** | 1. Login â†’ 2. Wait 30 min without interaction â†’ 3. Click any navigation link |
| **Expected** | Redirected to login page. Session token invalidated. API returns 401. |

### TC-SEC-04 â€¢ XSS Injection Prevention

| Field | Value |
|-------|-------|
| **Priority** | P1 | **NFR** | Security | **Auto** | âŒ |
| **Test Data** | Payload: `<script>alert('XSS')</script>` in firstname, additionalneeds |
| **Steps** | 1. Create booking with XSS payload in string fields â†’ 2. GET booking â†’ 3. Render in UI |
| **Expected** | API stores payload as-is. UI renders as escaped text (NOT executed). No alert dialog. |

### TC-SEC-05 â€¢ CSRF Token Validation

| Field | Value |
|-------|-------|
| **Priority** | P1 | **NFR** | Security | **Auto** | âŒ |
| **Steps** | 1. Submit form with missing/invalid CSRF token |
| **Expected** | Request rejected: `403 Forbidden`. CSRF token is required for state-changing requests. |
| **Tool** | REST Assured / OWASP ZAP |

### TC-SEC-06 â€¢ Password Complexity Enforcement

| Field | Value |
|-------|-------|
| **Priority** | P1 | **NFR** | Security | **Auto** | âŒ |
| **Test Data** | password = "short" (fails all rules) |
| **Steps** | 1. Navigate to password change/setup â†’ 2. Enter weak password |
| **Expected** | Error: "Password must be at least 8 characters, contain 1 uppercase, 1 lowercase, 1 number, 1 special character." |



---

# Non-Functional â€” Cross-Browser & Responsive â€” Test Cases

**File**: `nfr/TC-NFR-CrossBrowser.md` | **Module**: Cross-Browser | **Total**: 6 TC | **Automated**: 0

---

### TC-CBR-01 â€¢ Login Flow Across All Browsers

| Field | Value |
|-------|-------|
| **Priority** | P0 | **NFR** | Cross-Browser | **Auto** | âŒ |
| **Browsers** | Chrome, Firefox, Edge, Safari |
| **Steps** | 1. Execute full login flow (TC-AUTH-001 through TC-AUTH-005) on each browser |
| **Expected** | Consistent behavior: same error messages, same UI layout, same page flow. No browser-specific rendering bugs. |

### TC-CBR-02 â€¢ A/B Test Creation Across Browsers

| Field | Value |
|-------|-------|
| **Priority** | P0 | **NFR** | Cross-Browser | **Auto** | âŒ |
| **Browsers** | Chrome, Firefox, Edge, Safari |
| **Steps** | 1. Execute TC-ABT-001 through TC-ABT-003 on each browser |
| **Expected** | Visual Editor loads correctly. Variation preview renders. Drag-and-drop works. |

### TC-CBR-03 â€¢ Heatmap Rendering Across Browsers

| Field | Value |
|-------|-------|
| **Priority** | P1 | **NFR** | Cross-Browser | **Auto** | âŒ |
| **Browsers** | Chrome, Firefox, Edge, Safari |
| **Expected** | Heatmap overlay renders identically. Color gradients match. Tooltips work. |

### TC-CBR-04 â€¢ Dashboard Layout at 1920Ã—1080 (Desktop)

| Field | Value |
|-------|-------|
| **Priority** | P0 | **NFR** | Responsive | **Auto** | âŒ |
| **Steps** | 1. Resize browser to 1920Ã—1080 â†’ 2. Login â†’ 3. Observe dashboard |
| **Expected** | Full layout: sidebar visible, experiment cards in grid, summary cards in row, no horizontal scroll. |

### TC-CBR-05 â€¢ Dashboard Layout at 768Ã—1024 (Tablet)

| Field | Value |
|-------|-------|
| **Priority** | P1 | **NFR** | Responsive | **Auto** | âŒ |
| **Browsers** | Chrome, Safari |
| **Steps** | 1. Resize to 768Ã—1024 â†’ 2. Observe responsive breakpoints |
| **Expected** | Sidebar collapses to hamburger. Cards stack vertically. Touch targets â‰¥ 44px. No content cut off. |

### TC-CBR-06 â€¢ Dashboard Layout at 375Ã—812 (Mobile)

| Field | Value |
|-------|-------|
| **Priority** | P1 | **NFR** | Responsive | **Auto** | âŒ |
| **Browsers** | Chrome, Safari (iPhone) |
| **Expected** | Single column layout. Experiment cards full width. CTAs still tappable. Font sizes readable (â‰¥ 16px). |



---

# Non-Functional â€” Accessibility (WCAG 2.1 AA) â€” Test Cases

**File**: `nfr/TC-NFR-Accessibility.md` | **Module**: Accessibility | **Total**: 5 TC | **Automated**: 0

---

### TC-ACC-01 â€¢ All Images Have Alt Text

| Field | Value |
|-------|-------|
| **Priority** | P1 | **NFR** | WCAG 2.1 AA | **Auto** | âŒ |
| **Criteria** | Every `<img>` element has a non-empty `alt` attribute. Decorative images have `alt=""`. |
| **Tool** | axe-core / Lighthouse |

### TC-ACC-02 â€¢ Color Contrast Ratio â‰¥ 4.5:1

| Field | Value |
|-------|-------|
| **Priority** | P1 | **NFR** | WCAG 2.1 AA | **Auto** | âŒ |
| **Criteria** | All text (including placeholder text) has contrast ratio â‰¥ 4.5:1 against background. Large text (â‰¥ 18px bold or â‰¥ 24px) has â‰¥ 3:1. |
| **Tool** | axe-core / Colour Contrast Analyser |

### TC-ACC-03 â€¢ Keyboard Navigation

| Field | Value |
|-------|-------|
| **Priority** | P0 | **NFR** | WCAG 2.1 AA | **Auto** | âŒ |
| **Criteria** | All interactive elements reachable via `Tab` key. Visible focus indicator (outline/ring). No keyboard traps. Logical tab order (leftâ†’right, topâ†’bottom). |
| **Steps** | 1. Tab through entire login flow â†’ 2. Tab through experiment creation flow â†’ 3. Tab through reports |
| **Expected** | Every button, link, input, dropdown, and toggle is reachable and actionable via keyboard alone. |

### TC-ACC-04 â€¢ Form Labels Associated with Inputs

| Field | Value |
|-------|-------|
| **Priority** | P1 | **NFR** | WCAG 2.1 AA | **Auto** | âŒ |
| **Criteria** | Every `<input>`, `<select>`, `<textarea>` has an associated `<label>` (via `for` attribute or wrapping). No placeholder-only labeling. |
| **Tool** | axe-core |

### TC-ACC-05 â€¢ ARIA Landmarks Present

| Field | Value |
|-------|-------|
| **Priority** | P1 | **NFR** | WCAG 2.1 AA | **Auto** | âŒ |
| **Criteria** | Page has: `<header role="banner">`, `<nav role="navigation">`, `<main role="main">`, `<footer role="contentinfo">`. Dynamic regions have `aria-live` attributes. |
| **Tool** | axe-core |

