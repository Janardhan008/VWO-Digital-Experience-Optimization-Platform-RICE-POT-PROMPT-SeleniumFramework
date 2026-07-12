# Audience Targeting — Test Cases

**File**: `TC-AUD-Audience.md` | **Module**: Audience | **Total**: 6 TC | **Automated**: 5

---

### TC-AUD-001 • Target by Geography

| Field | Value |
|-------|-------|
| **Priority** | P0 | **FR ID** | FR5 | **Auto** | ✅ `AudienceTargetingTest#testAudienceByGeography` |
| **Test Data** | Geography = "United States" |
| **Steps** | 1. Create A/B test → 2. Go to Audience section → 3. Add condition: Geography = United States → 4. Save |
| **Expected** | Audience condition saved. Only US visitors will see the experiment. |

### TC-AUD-002 • Target by Device Type

| Field | Value |
|-------|-------|
| **Priority** | P0 | **FR ID** | FR5 | **Auto** | ✅ `AudienceTargetingTest#testAudienceByDeviceType` |
| **Test Data** | Device = "Mobile" |
| **Steps** | 1. Add audience condition: Device = Mobile → 2. Save |
| **Expected** | Only mobile visitors see the experiment. |

### TC-AUD-003 • Target by Behavior (Returning vs New)

| Field | Value |
|-------|-------|
| **Priority** | P0 | **FR ID** | FR5 | **Auto** | ✅ `AudienceTargetingTest#testAudienceByBehavior` |
| **Test Data** | Behavior = "Returning Visitor" |
| **Steps** | 1. Add audience condition: Behavior = Returning Visitor → 2. Save |
| **Expected** | Only logged-in returning visitors see the experiment. New visitors see control. |

### TC-AUD-004 • Target by Custom Attribute

| Field | Value |
|-------|-------|
| **Priority** | P1 | **FR ID** | FR5 | **Auto** | ✅ `AudienceTargetingTest` |
| **Test Data** | Custom attribute: `plan_type = enterprise` |
| **Steps** | 1. Add audience condition → Custom Attribute → 2. Set key=plan_type, value=enterprise → 3. Save |
| **Expected** | Condition saved. Only visitors with matching custom attribute are targeted. |

### TC-AUD-005 • Combine Multiple Conditions (AND Logic)

| Field | Value |
|-------|-------|
| **Priority** | P1 | **FR ID** | FR5 | **Auto** | ✅ `AudienceTargetingTest#testMultipleAudienceConditions` |
| **Test Data** | Geography=US AND Device=Desktop AND Behavior=New Visitor |
| **Steps** | 1. Add 3 audience conditions → 2. Save |
| **Expected** | All conditions apply with AND logic. Audience preview shows reduced reach. Narrows down to visitors matching ALL conditions. |

### TC-AUD-006 • Audience Preview Shows Estimated Reach

| Field | Value |
|-------|-------|
| **Priority** | P1 | **FR ID** | FR5 | **Auto** | ✅ `AudienceTargetingTest#testAudiencePreviewReachEstimate` |
| **Steps** | 1. Add audience condition → 2. Observe audience preview panel |
| **Expected** | Preview shows: "Estimated reach: X% of all visitors (Y visitors/month)". Reach updates in real-time as conditions change. |
