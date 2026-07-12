# Booking API — Health Check — Test Cases

**File**: `api/TC-PNG-HealthCheck.md` | **Endpoint**: `GET /ping` | **Total**: 6 TC | **Automated**: 6

---

### TC-PNG-001 • Health Check Returns 201 Created

| Field | Value |
|-------|-------|
| **Priority** | P0 | **API Ref** | `GET /ping` | **Auto** | ✅ `HealthCheckTest#testHealthCheckReturns201` |
| **Steps** | `GET https://restful-booker.herokuapp.com/ping` |
| **Expected** | Status: `201 Created`. Body: empty. |

### TC-PNG-002 • Response Time Under 500ms

| Field | Value |
|-------|-------|
| **Priority** | P0 | **API Ref** | `GET /ping` | **Auto** | ✅ `HealthCheckTest#testHealthCheckResponseTime` |
| **Steps** | `GET /ping` — measure response time |
| **Expected** | `response.time < 500ms` |

### TC-PNG-003 • Responds Without Authentication

| Field | Value |
|-------|-------|
| **Priority** | P0 | **API Ref** | `GET /ping` | **Auto** | ✅ `HealthCheckTest#testHealthCheckNoAuth` |
| **Steps** | `GET /ping` (no Auth header) |
| **Expected** | Status: `201`. Endpoint is public. |

### TC-PNG-004 • Rejects POST/PUT/DELETE (405)

| Field | Value |
|-------|-------|
| **Priority** | P1 | **API Ref** | `GET /ping` | **Auto** | ✅ `HealthCheckTest#testHealthCheckRejectsUnsupportedMethods` |
| **Steps** | `POST /ping`, `PUT /ping`, `DELETE /ping`, `PATCH /ping` |
| **Expected** | Each returns `405 Method Not Allowed`. |

### TC-PNG-005 • Consecutive 10 Rapid Calls

| Field | Value |
|-------|-------|
| **Priority** | P1 | **API Ref** | `GET /ping` | **Auto** | ✅ `HealthCheckTest#testHealthCheckConsecutiveCalls` |
| **Steps** | Call `GET /ping` 10 times in rapid succession |
| **Expected** | All 10 return `201`. No rate limiting. No failures. |

### TC-PNG-006 • Response Headers Present

| Field | Value |
|-------|-------|
| **Priority** | P1 | **API Ref** | `GET /ping` | **Auto** | ✅ `HealthCheckTest#testHealthCheckResponseHeaders` |
| **Steps** | `GET /ping` — inspect headers |
| **Expected** | Headers present: `Content-Type`, `Server`, `Date`, `Content-Length` |
