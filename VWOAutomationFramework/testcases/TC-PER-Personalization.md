# Personalization — Test Cases

**File**: `TC-PER-Personalization.md` | **Module**: Personalize | **Total**: 10 TC | **Automated**: 8

---

### TC-PER-001 • Create Personalization Campaign with Segments

| Field | Value |
|-------|-------|
| **Priority** | P0 | **FR ID** | FR7 | **Auto** | ✅ `PersonalizationTest#testCreatePersonalizationCampaignWithSegments` |
| **Test Data** | Name="US Visitor Personalized Banner", Segment=Geography=US, Content="Welcome to VWO - Exclusive US Offer!" |
| **Steps** | 1. Navigate to Personalize → 2. Click "Create Campaign" → 3. Enter name → 4. Add segment → 5. Add personalized content → 6. Save Draft |
| **Expected** | Campaign saved as "Draft". Appears in campaign list. |

### TC-PER-002 • Geographic Segment Targeting

| Field | Value |
|-------|-------|
| **Priority** | P0 | **FR ID** | FR7 | **Auto** | ✅ `PersonalizationTest#testCreatePersonalizationCampaignWithSegments` |
| **Test Data** | Segment = Geography: "United States" |
| **Steps** | 1. Create campaign → 2. Add geo segment |
| **Expected** | Segment configured. Audience preview shows US-only reach. |

### TC-PER-003 • Behavioral Segment Targeting

| Field | Value |
|-------|-------|
| **Priority** | P0 | **FR ID** | FR7 | **Auto** | ✅ `PersonalizationTest#testLaunchBehavioralTargetingCampaign` |
| **Test Data** | Segment = Behavior: "Returning Visitor" |
| **Steps** | 1. Create campaign → 2. Add behavioral segment → 3. Add content → 4. Launch |
| **Expected** | Campaign launched. Only returning visitors see personalized content. |

### TC-PER-004 • Device-Based Segment Targeting

| Field | Value |
|-------|-------|
| **Priority** | P1 | **FR ID** | FR7 | **Auto** | ✅ `PersonalizationTest#testDeviceBasedPersonalization` |
| **Test Data** | Segment = Device: "Mobile" |
| **Steps** | 1. Create campaign → 2. Add device segment → 3. Save |
| **Expected** | Campaign targets mobile users only. |

### TC-PER-005 • Configure Personalized Content

| Field | Value |
|-------|-------|
| **Priority** | P0 | **FR ID** | FR7 | **Auto** | ✅ `PersonalizationTest#testCreatePersonalizationCampaignWithSegments` |
| **Test Data** | Content = "Welcome to VWO - Exclusive US Offer!" |
| **Steps** | 1. Create campaign → 2. Enter personalized content in editor (text/HTML/CSS) |
| **Expected** | Content saved. Content renders correctly in preview. |

### TC-PER-006 • Preview Personalization Before Launch

| Field | Value |
|-------|-------|
| **Priority** | P1 | **FR ID** | FR7 | **Auto** | ✅ `PersonalizationTest#testPreviewPersonalizationCampaign` |
| **Steps** | 1. Create campaign with content → 2. Click "Preview" |
| **Expected** | Preview panel shows the page with personalized content applied. "View as different segments" toggle works. |
| **Validation** | `personalizePage.isPersonalizedPreviewDisplayed() == true` |

### TC-PER-007 • Launch Campaign → Status "Running"

| Field | Value |
|-------|-------|
| **Priority** | P0 | **FR ID** | FR7 | **Auto** | ✅ `PersonalizationTest#testLaunchBehavioralTargetingCampaign` |
| **Precondition** | Campaign has segments + content configured |
| **Steps** | 1. Click "Launch" |
| **Expected** | Status changes from "Draft" to "Running". Campaign starts serving personalized content. |

### TC-PER-008 • Pause Active Campaign

| Field | Value |
|-------|-------|
| **Priority** | P1 | **FR ID** | FR7 | **Auto** | ❌ Manual |
| **Steps** | 1. Open running campaign → 2. Click "Pause" |
| **Expected** | Status changes to "Paused". Original (non-personalized) content served to all visitors. |

### TC-PER-009 • Set Traffic Percentage for Campaign

| Field | Value |
|-------|-------|
| **Priority** | P1 | **FR ID** | FR7 | **Auto** | ✅ `PersonalizationTest#testTrafficAllocationForCampaign` |
| **Test Data** | Traffic % = 50 |
| **Steps** | 1. Create campaign → 2. Set traffic percentage slider to 50% → 3. Save |
| **Expected** | 50% of qualifying visitors see personalized content. 50% see original. |

### TC-PER-010 • Campaign with Multiple Segments

| Field | Value |
|-------|-------|
| **Priority** | P1 | **FR ID** | FR7 | **Auto** | ✅ `PersonalizationTest#testMultipleSegmentsInCampaign` |
| **Test Data** | Geo=US + Behavior=New Visitor + Device=Desktop |
| **Steps** | 1. Create campaign → 2. Add 3 segments (geo + behavior + device) → 3. Save |
| **Expected** | All 3 segments combined with AND logic. Reach narrowed to intersection. Campaign saved successfully. |
