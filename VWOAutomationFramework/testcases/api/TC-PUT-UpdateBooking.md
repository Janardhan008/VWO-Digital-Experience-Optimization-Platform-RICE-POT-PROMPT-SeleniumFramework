# Booking API — Full Update (PUT) — Test Cases

**File**: `api/TC-PUT-UpdateBooking.md` | **Endpoint**: `PUT /booking/{id}` | **Total**: 10 TC | **Automated**: 10

---

### TC-PUT-001 • Full Update with Valid Auth Returns 200

| Field | Value |
|-------|-------|
| **Priority** | P0 | **Auto** | ✅ `UpdateBookingTest#testFullUpdateWithAuth` |
| **Test Data** | Auth = Basic `admin:password123`, Body = `{"firstname":"Jane","lastname":"Doe","totalprice":500,"depositpaid":false,"bookingdates":{"checkin":"2026-08-01","checkout":"2026-08-10"},"additionalneeds":"Lunch"}` |
| **Steps** | 1. Create booking → 2. `PUT /booking/{id}` with full payload + auth |
| **Expected** | Status `200`. Updated values returned in response. |

### TC-PUT-002 • All Fields Updated Correctly

| Field | Value |
|-------|-------|
| **Priority** | P0 | **Auto** | ✅ `UpdateBookingTest#testFullUpdateAllFields` |
| **Steps** | PUT → GET booking → compare |
| **Expected** | Every field matches the PUT payload. Round-trip verified. |

### TC-PUT-003 • Response Body Matches Request (Round-Trip)

| Field | Value |
|-------|-------|
| **Priority** | P0 | **Auto** | ✅ `UpdateBookingTest#testFullUpdateRoundTrip` |
| **Expected** | PUT response body == request body (all fields). |

### TC-PUT-004 • Update Without Auth Returns 403

| Field | Value |
|-------|-------|
| **Priority** | P0 | **Auto** | ✅ `UpdateBookingTest#testFullUpdateWithoutAuth` |
| **Steps** | `PUT /booking/{id}` with valid body but NO Auth header |
| **Expected** | Status `403 Forbidden`. |

### TC-PUT-005 • Update with Invalid Auth Returns 403

| Field | Value |
|-------|-------|
| **Priority** | P1 | **Auto** | ✅ `UpdateBookingTest#testFullUpdateWithInvalidAuth` |
| **Steps** | `PUT /booking/{id}` with Base64("bad:creds") |
| **Expected** | Status `403` or `401`. |

### TC-PUT-006 • Update to Non-Existent ID

| Field | Value |
|-------|-------|
| **Priority** | P1 | **Auto** | ✅ `UpdateBookingTest#testFullUpdateNonExistentId` |
| **Steps** | `PUT /booking/99999` with valid auth + body |
| **Expected** | Status `404` or `405`. |

### TC-PUT-007 • Missing Required Fields

| Field | Value |
|-------|-------|
| **Priority** | P1 | **Auto** | ✅ `UpdateBookingTest#testFullUpdateMissingFields` |
| **Test Data** | `{"firstname": "OnlyFirst"}` (missing lastname, totalprice, etc.) |
| **Expected** | Status `400 Bad Request` OR `200` (depending on API design — document behavior). |

### TC-PUT-008 • Invalid Data Types

| Field | Value |
|-------|-------|
| **Priority** | P1 | **Auto** | ✅ `UpdateBookingTest#testFullUpdateInvalidDataTypes` |
| **Test Data** | `"totalprice": "not-a-number"` |
| **Expected** | Status `400` or `500`. Type mismatch should be rejected. |

### TC-PUT-009 • Extra Unknown Fields Accepted (Tolerant)

| Field | Value |
|-------|-------|
| **Priority** | P1 | **Auto** | ✅ `UpdateBookingTest#testFullUpdateWithExtraFields` |
| **Test Data** | Extra fields: `"extraField": "shouldBeIgnored"` |
| **Expected** | Status `200`. Extra fields ignored (not persisted). Backward compatible. |

### TC-PUT-010 • checkout Before checkin

| Field | Value |
|-------|-------|
| **Priority** | P1 | **Auto** | ✅ `UpdateBookingTest#testFullUpdateCheckoutBeforeCheckin` |
| **Test Data** | `checkin="2026-02-01"`, `checkout="2026-01-01"` |
| **Expected** | Status `400` OR system auto-corrects. |
