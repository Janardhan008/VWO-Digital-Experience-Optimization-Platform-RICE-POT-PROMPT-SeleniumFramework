# Booking API — Delete Booking — Test Cases

**File**: `api/TC-DEL-DeleteBooking.md` | **Endpoint**: `DELETE /booking/{id}` | **Total**: 8 TC | **Automated**: 8

---

### TC-DEL-001 • Delete with Valid Auth Returns 201

| Field | Value |
|-------|-------|
| **Priority** | P0 | **Auto** | ✅ `DeleteBookingTest#testDeleteWithValidAuth` |
| **Steps** | Create booking → `DELETE /booking/{id}` with auth |
| **Expected** | Status `201 Created`. |

### TC-DEL-002 • Deleted Booking Returns 404 on GET

| Field | Value |
|-------|-------|
| **Priority** | P0 | **Auto** | ✅ `DeleteBookingTest#testDeletedBookingReturns404` |
| **Steps** | DELETE → GET same ID |
| **Expected** | DELETE: `201`. GET: `404 Not Found`. |

### TC-DEL-003 • Delete Without Auth Returns 403

| Field | Value |
|-------|-------|
| **Priority** | P0 | **Auto** | ✅ `DeleteBookingTest#testDeleteWithoutAuth` |
| **Steps** | `DELETE /booking/{id}` (no Auth header) |
| **Expected** | Status `403 Forbidden`. |

### TC-DEL-004 • Delete with Invalid Auth Returns 403

| Field | Value |
|-------|-------|
| **Priority** | P0 | **Auto** | ✅ `DeleteBookingTest#testDeleteWithInvalidAuth` |
| **Steps** | `DELETE /booking/{id}` with Base64("bad:creds") |
| **Expected** | Status `403` or `401`. |

### TC-DEL-005 • Delete Non-Existent ID Returns 405

| Field | Value |
|-------|-------|
| **Priority** | P1 | **Auto** | ✅ `DeleteBookingTest#testDeleteNonExistentId` |
| **Steps** | `DELETE /booking/99999` with auth |
| **Expected** | Status `405 Method Not Allowed`. |

### TC-DEL-006 • Double Delete Returns 405

| Field | Value |
|-------|-------|
| **Priority** | P1 | **Auto** | ✅ `DeleteBookingTest#testDoubleDelete` |
| **Steps** | DELETE same ID twice |
| **Expected** | First: `201`. Second: `405` or `404`. |

### TC-DEL-007 • GET on Delete Endpoint Returns 200

| Field | Value |
|-------|-------|
| **Priority** | P0 | **Auto** | ✅ `DeleteBookingTest#testGetOnDeleteEndpoint` |
| **Steps** | `GET /booking/{id}` (verifying method safety) |
| **Expected** | Status `200`. |

### TC-DEL-008 • Create → Delete → Verify Cycle

| Field | Value |
|-------|-------|
| **Priority** | P1 | **Auto** | ✅ `DeleteBookingTest#testCreateDeleteVerifyCycle` |
| **Steps** | POST (create) → GET (verify exists) → DELETE → GET (verify gone) |
| **Expected** | Full lifecycle works. No orphaned data. |
