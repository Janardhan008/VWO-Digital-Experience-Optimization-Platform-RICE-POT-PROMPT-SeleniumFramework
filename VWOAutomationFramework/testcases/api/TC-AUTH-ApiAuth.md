# Booking API — Authentication — Test Cases

**File**: `api/TC-AUTH-ApiAuth.md` | **Endpoint**: `POST /auth` | **Total**: 5 TC | **Automated**: 5

---

### TC-AUTH-020 • Valid Credentials Return 200 with Token

| Field | Value |
|-------|-------|
| **Priority** | P0 | **API Ref** | `POST /auth` | **Auto** | ✅ `AuthTest#testAuthWithValidCredentials` |
| **Test Data** | `{"username": "admin", "password": "password123"}` |
| **Steps** | `POST /auth` with valid JSON body |
| **Expected** | Status `200`. Response: `{"token": "<non-empty-string>"}` |

### TC-AUTH-021 • Token is Non-Empty String

| Field | Value |
|-------|-------|
| **Priority** | P0 | **API Ref** | `POST /auth` | **Auto** | ✅ `AuthTest#testTokenIsNonEmptyString` |
| **Steps** | Extract token from auth response |
| **Expected** | `token` is not null, not empty, and is a string. |

### TC-AUTH-022 • Invalid Credentials Handling

| Field | Value |
|-------|-------|
| **Priority** | P1 | **API Ref** | `POST /auth` | **Auto** | ✅ `AuthTest#testAuthWithInvalidCredentials` |
| **Test Data** | `{"username": "invalid", "password": "invalid"}` |
| **Steps** | `POST /auth` with bad credentials |
| **Expected** | Status `200` but token may not work for write operations. System behavior documented. |

### TC-AUTH-023 • Empty Credentials

| Field | Value |
|-------|-------|
| **Priority** | P1 | **API Ref** | `POST /auth` | **Auto** | ✅ `AuthTest#testAuthWithEmptyCredentials` |
| **Test Data** | `{"username": "", "password": ""}` |
| **Steps** | `POST /auth` with empty fields |
| **Expected** | Status `400 Bad Request` or `200`. Error message if 400. |

### TC-AUTH-024 • Missing Content-Type Header

| Field | Value |
|-------|-------|
| **Priority** | P1 | **API Ref** | `POST /auth` | **Auto** | ✅ `AuthTest#testAuthMissingContentType` |
| **Steps** | Send POST /auth with JSON body but NO `Content-Type: application/json` header |
| **Expected** | Status `400 Bad Request` or `415 Unsupported Media Type`. |
