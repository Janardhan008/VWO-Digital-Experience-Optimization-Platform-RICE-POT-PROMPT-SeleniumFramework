# Booking API — Get Single Booking — Test Cases

**File**: `api/TC-BOK-GetSingleBooking.md` | **Endpoint**: `GET /booking/{id}` | **Total**: 12 TC | **Automated**: 12

---

### TC-BOK-011 • Valid ID Returns 200 with Full Booking

| Field | Value |
|-------|-------|
| **Priority** | P0 | **Auto** | ✅ `GetSingleBookingTest#testGetBookingByIdReturns200` |
| **Steps** | `GET /booking/1` |
| **Expected** | Status `200`. Full booking object returned. |

### TC-BOK-012 • All Required Fields Present

| Field | Value |
|-------|-------|
| **Priority** | P0 | **Auto** | ✅ `GetSingleBookingTest#testGetBookingByIdHasAllFields` |
| **Expected** | Fields: `firstname`, `lastname`, `totalprice`, `depositpaid`, `bookingdates` (with `checkin`, `checkout`), `additionalneeds`. None null. |

### TC-BOK-013 • Name Fields Non-Empty

| Field | Value |
|-------|-------|
| **Priority** | P0 | **Auto** | ✅ `GetSingleBookingTest#testNameFieldsAreNonEmpty` |
| **Expected** | `firstname.trim().length() > 0`, `lastname.trim().length() > 0` |

### TC-BOK-014 • totalprice is Positive

| Field | Value |
|-------|-------|
| **Priority** | P0 | **Auto** | ✅ `GetSingleBookingTest#testTotalPriceIsPositive` |
| **Expected** | `totalprice > 0` |

### TC-BOK-015 • depositpaid is Boolean

| Field | Value |
|-------|-------|
| **Priority** | P0 | **Auto** | ✅ `GetSingleBookingTest#testDepositPaidIsBoolean` |
| **Expected** | `depositpaid == true || depositpaid == false` (not null, not string, not int) |

### TC-BOK-016 • Booking Dates in YYYY-MM-DD Format

| Field | Value |
|-------|-------|
| **Priority** | P0 | **Auto** | ✅ `GetSingleBookingTest#testBookingDatesFormat` |
| **Expected** | `checkin` and `checkout` match regex: `\d{4}-\d{2}-\d{2}` |

### TC-BOK-017 • checkout >= checkin

| Field | Value |
|-------|-------|
| **Priority** | P0 | **Auto** | ✅ `GetSingleBookingTest#testCheckoutAfterCheckin` |
| **Expected** | `checkout >= checkin` (string comparison on YYYY-MM-DD is valid) |

### TC-BOK-018 • Non-Existent ID Returns 404

| Field | Value |
|-------|-------|
| **Priority** | P0 | **Auto** | ✅ `GetSingleBookingTest#testNonExistentIdReturns404` |
| **Steps** | `GET /booking/99999` |
| **Expected** | Status `404 Not Found` |

### TC-BOK-019 • Negative ID Returns 400/404

| Field | Value |
|-------|-------|
| **Priority** | P1 | **Auto** | ✅ `GetSingleBookingTest#testNegativeId` |
| **Steps** | `GET /booking/-1` |
| **Expected** | Status `400` or `404` |

### TC-BOK-020 • String ID Returns 400/404

| Field | Value |
|-------|-------|
| **Priority** | P1 | **Auto** | ✅ `GetSingleBookingTest#testStringId` |
| **Steps** | `GET /booking/abc` |
| **Expected** | Status `400` or `404` |

### TC-BOK-021 • Special Character ID Returns 400/404

| Field | Value |
|-------|-------|
| **Priority** | P1 | **Auto** | ✅ `GetSingleBookingTest#testSpecialCharId` |
| **Steps** | `GET /booking/@#$` |
| **Expected** | Status `400` or `404` |

### TC-BOK-022 • Response Defaults to JSON

| Field | Value |
|-------|-------|
| **Priority** | P1 | **Auto** | ✅ `GetSingleBookingTest#testResponseDefaultsToJson` |
| **Steps** | `GET /booking/1` (no Accept header) |
| **Expected** | Status `200`. `Content-Type` contains `application/json`. |
