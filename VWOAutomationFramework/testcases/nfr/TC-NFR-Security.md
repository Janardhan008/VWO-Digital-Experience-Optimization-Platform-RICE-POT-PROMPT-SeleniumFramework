# Non-Functional — Security — Test Cases

**File**: `nfr/TC-NFR-Security.md` | **Module**: Security | **Total**: 6 TC | **Automated**: 0

---

### TC-SEC-01 • 2FA Enforcement for Enterprise Accounts

| Field | Value |
|-------|-------|
| **Priority** | P0 | **NFR** | Security | **Auto** | ❌ |
| **Precondition** | VWO account with 2FA enabled |
| **Steps** | 1. Login with valid credentials → 2. Observe post-login screen |
| **Expected** | 2FA code input MANDATORY. User cannot skip/ bypass. Dashboard NOT accessible without 2FA. |

### TC-SEC-02 • RBAC — Admin vs Viewer Permissions

| Field | Value |
|-------|-------|
| **Priority** | P0 | **NFR** | Security | **Auto** | ❌ |
| **Test Data** | Two accounts: Admin role + Viewer role |
| **Steps** | 1. Login as Admin → can create/launch/delete experiments → 2. Login as Viewer → observe options |
| **Expected** | Admin: full CRUD. Viewer: read-only (no Create/Edit/Delete buttons). API returns 403 for write operations. |

### TC-SEC-03 • Session Timeout After 30 Minutes

| Field | Value |
|-------|-------|
| **Priority** | P0 | **NFR** | Security | **Auto** | ❌ |
| **Steps** | 1. Login → 2. Wait 30 min without interaction → 3. Click any navigation link |
| **Expected** | Redirected to login page. Session token invalidated. API returns 401. |

### TC-SEC-04 • XSS Injection Prevention

| Field | Value |
|-------|-------|
| **Priority** | P1 | **NFR** | Security | **Auto** | ❌ |
| **Test Data** | Payload: `<script>alert('XSS')</script>` in firstname, additionalneeds |
| **Steps** | 1. Create booking with XSS payload in string fields → 2. GET booking → 3. Render in UI |
| **Expected** | API stores payload as-is. UI renders as escaped text (NOT executed). No alert dialog. |

### TC-SEC-05 • CSRF Token Validation

| Field | Value |
|-------|-------|
| **Priority** | P1 | **NFR** | Security | **Auto** | ❌ |
| **Steps** | 1. Submit form with missing/invalid CSRF token |
| **Expected** | Request rejected: `403 Forbidden`. CSRF token is required for state-changing requests. |
| **Tool** | REST Assured / OWASP ZAP |

### TC-SEC-06 • Password Complexity Enforcement

| Field | Value |
|-------|-------|
| **Priority** | P1 | **NFR** | Security | **Auto** | ❌ |
| **Test Data** | password = "short" (fails all rules) |
| **Steps** | 1. Navigate to password change/setup → 2. Enter weak password |
| **Expected** | Error: "Password must be at least 8 characters, contain 1 uppercase, 1 lowercase, 1 number, 1 special character." |
