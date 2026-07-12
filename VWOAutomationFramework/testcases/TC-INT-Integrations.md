# Integrations — Test Cases

**File**: `TC-INT-Integrations.md` | **Module**: Integrations | **Total**: 3 TC | **Automated**: 2

---

### TC-INT-001 • Navigate to Integrations Listing Page

| Field | Value |
|-------|-------|
| **Priority** | P1 | **FR ID** | FR8 | **Auto** | ✅ `IntegrationsTest#testNavigateToIntegrations` |
| **Steps** | 1. Click "Integrations" in sidebar |
| **Expected** | Integrations page loads. Available connectors listed with search/filter. Categories: Analytics, CRM, Commerce, Data Platforms. |

### TC-INT-002 • Search for Integration Connector

| Field | Value |
|-------|-------|
| **Priority** | P2 | **FR ID** | FR8 | **Auto** | ❌ Manual |
| **Test Data** | Search term = "Salesforce" |
| **Steps** | 1. On Integrations page → 2. Type connector name in search |
| **Expected** | Matching connectors filtered in real-time. Partial match works ("Sales" finds "Salesforce"). No results message if not found. |

### TC-INT-003 • Connect to Google Analytics

| Field | Value |
|-------|-------|
| **Priority** | P2 | **FR ID** | FR8 | **Auto** | ❌ Manual |
| **Steps** | 1. Click Google Analytics connector → 2. Click "Connect" → 3. Authorize via OAuth → 4. Select GA property → 5. Save |
| **Expected** | Connection established. Status shows "Connected". Data sync begins. Experiments created in VWO appear in GA as annotations. |
