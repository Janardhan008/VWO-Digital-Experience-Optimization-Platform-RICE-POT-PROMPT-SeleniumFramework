# Booking API — Get All Bookings — Test Cases

**File**: `api/TC-BOK-GetAllBookings.md` | **Endpoint**: `GET /booking` | **Total**: 10 TC | **Automated**: 10

---

### TC-BOK-001 • Returns 200 OK with Booking IDs Array

| Field | Value |
|-------|-------|
| **Priority** | P0 | **API Ref** | `GET /booking` | **Auto** | ✅ `GetAllBookingTest#testGetAllBookingsReturns200` |
| **Steps** | `GET /booking` |
| **Expected** | Status `200`. Body: `[{"bookingid": 1}, {"bookingid": 2}, ...]` |

### TC-BOK-002 • Non-Empty JSON Array

| Field | Value |
|-------|-------|
| **Priority** | P0 | **API Ref** | `GET /booking` | **Auto** | ✅ `GetAllBookingTest#testGetAllBookingsReturnsNonEmptyArray` |
| **Steps** | `GET /booking` |
| **Expected** | Response is a JSON array with at least 1 element. |

### TC-BOK-003 • bookingid is Positive Integer

| Field | Value |
|-------|-------|
| **Priority** | P0 | **API Ref** | `GET /booking` | **Auto** | ✅ `GetAllBookingTest#testBookingIdsAreIntegers` |
| **Steps** | `GET /booking` — validate each `bookingid` |
| **Expected** | Every `bookingid` is a positive integer > 0. |

### TC-BOK-004 • Booking IDs Are Unique

| Field | Value |
|-------|-------|
| **Priority** | P1 | **API Ref** | `GET /booking` | **Auto** | ✅ `GetAllBookingTest#testBookingIdsAreUnique` |
| **Steps** | Extract all `bookingid` values |
| **Expected** | Count of distinct IDs == total count. No duplicates. |

### TC-BOK-005 • Filter by firstname

| Field | Value |
|-------|-------|
| **Priority** | P1 | **API Ref** | `GET /booking?firstname=John` | **Auto** | ✅ `GetAllBookingTest#testFilterByFirstname` |
| **Test Data** | `?firstname=John` |
| **Steps** | `GET /booking?firstname=John` |
| **Expected** | Status `200`. Results contain only bookings where `firstname == "John"`. |

### TC-BOK-006 • Filter by lastname

| Field | Value |
|-------|-------|
| **Priority** | P1 | **API Ref** | `GET /booking?lastname=Smith` | **Auto** | ✅ `GetAllBookingTest#testFilterByLastname` |
| **Test Data** | `?lastname=Smith` |

### TC-BOK-007 • Filter by checkin Date

| Field | Value |
|-------|-------|
| **Priority** | P1 | **API Ref** | `GET /booking?checkin=2026-01-01` | **Auto** | ✅ `GetAllBookingTest#testFilterByCheckin` |
| **Test Data** | `?checkin=2026-01-01` |
| **Expected** | Status `200`. Results have `bookingdates.checkin >= 2026-01-01`. |

### TC-BOK-008 • Filter by checkout Date

| Field | Value |
|-------|-------|
| **Priority** | P1 | **API Ref** | `GET /booking?checkout=2026-02-01` | **Auto** | ✅ `GetAllBookingTest#testFilterByCheckout` |

### TC-BOK-009 • Combined Filters

| Field | Value |
|-------|-------|
| **Priority** | P1 | **API Ref** | `GET /booking?firstname=John&checkin=2026-01-01` | **Auto** | ✅ `GetAllBookingTest#testCombinedFilters` |
| **Expected** | Results match BOTH conditions (AND logic). |

### TC-BOK-010 • Invalid Filter Returns Empty Array

| Field | Value |
|-------|-------|
| **Priority** | P1 | **API Ref** | `GET /booking` | **Auto** | ✅ `GetAllBookingTest#testInvalidFilterReturnsEmpty` |
| **Test Data** | `?firstname=NonExistentName_XYZ` |
| **Expected** | Status `200`. Body: `[]` (empty array). |
