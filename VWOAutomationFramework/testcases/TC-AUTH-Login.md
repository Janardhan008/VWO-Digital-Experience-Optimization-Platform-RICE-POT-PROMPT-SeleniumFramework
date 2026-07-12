# Authentication & Access Control — Test Cases

**File**: `TC-AUTH-Login.md` | **Module**: Login | **Total**: 12 TC | **Automated**: 10

| Ref | Priority | Automated |
|:---:|:--------:|:---------:|
| FR6, NFR | P0: 6, P1: 5, P2: 1 | ✅ 10 / ❌ 2 manual |

---

### TC-AUTH-001 • Valid Login with Email & Password

| Field | Value |
|-------|-------|
| **Priority** | P0 |
| **FR ID** | FR6 |
| **Automation** | ✅ `tests.login.LoginTest#testValidLogin` |
| **Precondition** | User has valid credentials (ConfigReader.getUsername / getPassword) |
| **Test Data** | email = `qa.automation@vwo.com` , password = `VWO@Test#2026` |
| **Steps** | 1. Navigate to https://app.vwo.com — 2. Enter email → 3. Enter password → 4. Click Login |
| **Expected** | Dashboard page loads with user avatar visible. URL contains `/dashboard` |
| **Validation** | `dashboardPage.isDashboardDisplayed() == true` |

### TC-AUTH-002 • Invalid Password Shows Error

| Field | Value |
|-------|-------|
| **Priority** | P0 |
| **FR ID** | FR6 |
| **Automation** | ✅ `tests.login.LoginTest#testLoginWithInvalidPassword` |
| **Precondition** | Known valid email, incorrect password |
| **Test Data** | email = `qa.automation@vwo.com` , password = `WrongPassword@999` |
| **Steps** | 1. Enter email → 2. Enter wrong password → 3. Click Login |
| **Expected** | Error message displayed: "Invalid email or password" (or similar). User remains on login page. |
| **Validation** | `loginPage.getErrorMessage()` contains "Invalid" or "incorrect" or "password" |

### TC-AUTH-003 • Unregistered Email Shows Error

| Field | Value |
|-------|-------|
| **Priority** | P0 |
| **FR ID** | FR6 |
| **Automation** | ✅ `tests.login.LoginTest#testLoginWithUnregisteredEmail` |
| **Precondition** | Email does not exist in VWO system |
| **Test Data** | email = `nonexistent@unknown.com` , password = `VWO@Test#2026` |
| **Steps** | 1. Enter unregistered email → 2. Enter password → 3. Click Login |
| **Expected** | Error: "Account not found" or "No account with this email" |
| **Validation** | Error message contains "not found" or "unrecognized" |

### TC-AUTH-004 • Empty Credentials Blocked

| Field | Value |
|-------|-------|
| **Priority** | P0 |
| **FR ID** | FR6 |
| **Automation** | ✅ `tests.login.LoginTest#testLoginWithEmptyCredentials` |
| **Precondition** | Login page loaded |
| **Test Data** | email = `""` , password = `""` |
| **Steps** | 1. Leave email empty → 2. Leave password empty → 3. Click Login |
| **Expected** | Validation error: "Email is required" or equivalent. No network call made. |
| **Validation** | Error message contains "required" or "empty" or "email" |

### TC-AUTH-005 • Password Field Masks Input

| Field | Value |
|-------|-------|
| **Priority** | P0 |
| **FR ID** | NFR (Security) |
| **Automation** | ✅ `tests.login.LoginTest#testPasswordFieldMasking` |
| **Precondition** | Login page loaded |
| **Steps** | 1. Type any password into password field |
| **Expected** | Characters appear as bullets/dots (`●●●●●●●●`), not plain text |
| **Validation** | `passwordField.getAttribute("type")` equals `"password"` |

### TC-AUTH-006 • 2FA Code Input Appears After Login

| Field | Value |
|-------|-------|
| **Priority** | P0 |
| **FR ID** | NFR (Security) |
| **Automation** | ❌ Manual (requires 2FA-enabled test account) |
| **Precondition** | VWO account has 2FA enabled |
| **Test Data** | email/password for a 2FA-enabled account |
| **Steps** | 1. Enter valid credentials → 2. Click Login → 3. Observe next screen |
| **Expected** | 2FA code input field is displayed instead of Dashboard. User cannot bypass. |
| **Validation** | `loginPage.is2FAFieldDisplayed() == true` |

### TC-AUTH-007 • Invalid 2FA Code Blocks Access

| Field | Value |
|-------|-------|
| **Priority** | P1 |
| **FR ID** | NFR (Security) |
| **Automation** | ❌ Manual |
| **Precondition** | 2FA input field is visible |
| **Test Data** | 2FA code = `000000` (wrong code) |
| **Steps** | 1. Enter wrong 2FA code → 2. Click Verify |
| **Expected** | Error: "Invalid verification code". User NOT redirected to dashboard. |
| **Validation** | Error toast visible, URL unchanged |

### TC-AUTH-008 • Forgot Password Sends Reset Email

| Field | Value |
|-------|-------|
| **Priority** | P1 |
| **FR ID** | FR6 |
| **Automation** | ✅ `tests.login.LoginTest#testForgotPasswordFlow` |
| **Precondition** | Login page loaded |
| **Test Data** | email = `testuser@vwo.com` |
| **Steps** | 1. Click "Forgot Password?" → 2. Enter registered email → 3. Click "Send Reset Link" |
| **Expected** | Toast message: "Password reset link sent to your email" |
| **Validation** | Toast contains "sent" or "email" or "reset" |

### TC-AUTH-009 • SSO Login Redirects to IdP

| Field | Value |
|-------|-------|
| **Priority** | P1 |
| **FR ID** | FR6 |
| **Automation** | ✅ `tests.login.LoginTest` |
| **Precondition** | Login page loaded, SSO configured for the test org |
| **Steps** | 1. Click "Sign in with SSO" → 2. Enter company domain |
| **Expected** | Redirected to identity provider (Okta/Azure AD). VWO login page no longer visible. |
| **Validation** | Current URL contains the IdP domain |

### TC-AUTH-010 • Remember Me Persists Session

| Field | Value |
|-------|-------|
| **Priority** | P0 |
| **FR ID** | FR6 |
| **Automation** | ✅ `tests.login.LoginTest#testRememberMeFunctionality` |
| **Precondition** | Browser has no prior VWO session cookies |
| **Test Data** | Valid credentials |
| **Steps** | 1. Check "Remember Me" → 2. Login → 3. Close browser → 4. Re-open → 5. Navigate to app.vwo.com |
| **Expected** | User is still logged in (session restored from cookie/token) |
| **Validation** | Dashboard loads without re-entering credentials |

### TC-AUTH-011 • Session Timeout Logs Out After Inactivity

| Field | Value |
|-------|-------|
| **Priority** | P1 |
| **FR ID** | NFR (Security) |
| **Automation** | ❌ Manual (requires waiting 30 min) |
| **Precondition** | User is logged in |
| **Steps** | 1. Login → 2. Remain idle for 30 minutes → 3. Click any navigation link |
| **Expected** | User is redirected to login page. Session token expired. |
| **Validation** | Login page is displayed, API returns 401 |

### TC-AUTH-012 • Logout Clears Session

| Field | Value |
|-------|-------|
| **Priority** | P0 |
| **FR ID** | FR6 |
| **Automation** | ✅ `tests.login.LoginTest#testLogoutFunctionality` |
| **Precondition** | User is logged in (Dashboard visible) |
| **Steps** | 1. Click user avatar → 2. Click "Log out" |
| **Expected** | Returned to login page. Browser back button does NOT return to Dashboard. |
| **Validation** | `loginPage.isLoginPageDisplayed() == true`. Navigating back shows login page. |
