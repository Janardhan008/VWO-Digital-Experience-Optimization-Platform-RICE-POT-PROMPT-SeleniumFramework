# Booking API — Partial Update (PATCH) — Test Cases

**File**: `api/TC-PTC-PartialUpdate.md` | **Endpoint**: `PATCH /booking/{id}` | **Total**: 10 TC | **Automated**: 10

---

### TC-PTC-001 • Patch Single Field Returns 200

| Field | Value |
|-------|-------|
| **Priority** | P0 | **Auto** | ✅ `PartialUpdateBookingTest#testPartialUpdateSingleField` |
| **Test Data** | `{"firstname": "UpdatedName"}` |
| **Steps** | Create booking → `PATCH /booking/{id}` with single field + auth |
| **Expected** | Status `200`. `firstname` updated. |

### TC-PTC-002 • Only Patched Field Changes, Others Unchanged

| Field | Value |
|-------|-------|
| **Priority** | P0 | **Auto** | ✅ `PartialUpdateBookingTest#testPartialUpdatePreservesOtherFields` |
| **Steps** | GET before → PATCH `firstname` → GET after |
| **Expected** | `firstname` changed. `lastname`, `totalprice`, `depositpaid`, `bookingdates`, `additionalneeds` all identical to pre-patch values. |

### TC-PTC-003 • Patch totalprice

| Field | Value |
|-------|-------|
| **Priority** | P0 | **Auto** | ✅ `PartialUpdateBookingTest#testPartialUpdateTotalPrice` |
| **Test Data** | `{"totalprice": 999}` |
| **Expected** | `totalprice` updated to 999. |

### TC-PTC-004 • Patch bookingdates.checkin

| Field | Value |
|-------|-------|
| **Priority** | P0 | **Auto** | ✅ `PartialUpdateBookingTest#testPartialUpdateCheckin` |
| **Test Data** | `{"bookingdates": {"checkin": "2026-12-01"}}` |
| **Expected** | Only `checkin` changes. `checkout` stays same. |

### TC-PTC-005 • Patch Without Auth Returns 403

| Field | Value |
|-------|-------|
| **Priority** | P0 | **Auto** | ✅ `PartialUpdateBookingTest#testPartialUpdateWithoutAuth` |
| **Expected** | Status `403 Forbidden`. |

### TC-PTC-006 • Empty Body Returns 400

| Field | Value |
|-------|-------|
| **Priority** | P1 | **Auto** | ✅ `PartialUpdateBookingTest#testPartialUpdateEmptyBody` |
| **Test Data** | `{}` |
| **Expected** | Status `400` or `200` (idempotent). |

### TC-PTC-007 • Patch Non-Existent ID Returns 404

| Field | Value |
|-------|-------|
| **Priority** | P1 | **Auto** | ✅ `PartialUpdateBookingTest#testPartialUpdateNonExistentId` |
| **Steps** | `PATCH /booking/99999` |
| **Expected** | Status `404` or `405`. |

### TC-PTC-008 • Invalid Field Name Ignored

| Field | Value |
|-------|-------|
| **Priority** | P1 | **Auto** | ✅ `PartialUpdateBookingTest#testPartialUpdateInvalidField` |
| **Test Data** | `{"nonexistentField": "shouldBeIgnored"}` |
| **Expected** | Status `200`. No error. Field ignored. |

### TC-PTC-009 • Null Value for Required Field

| Field | Value |
|-------|-------|
| **Priority** | P1 | **Auto** | ✅ `PartialUpdateBookingTest#testPartialUpdateNullValue` |
| **Test Data** | `{"firstname": null}` |
| **Expected** | Status `400` (reject null for required fields). |

### TC-PTC-010 • additionalneeds Set to Empty String

| Field | Value |
|-------|-------|
| **Priority** | P1 | **Auto** | ✅ `PartialUpdateBookingTest#testPartialUpdateEmptyAdditionalNeeds` |
| **Test Data** | `{"additionalneeds": ""}` |
| **Expected** | Status `200`. `additionalneeds` is empty string. |
