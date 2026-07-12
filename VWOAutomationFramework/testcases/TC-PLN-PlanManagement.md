# Plan Management — Test Cases

**File**: `TC-PLN-PlanManagement.md` | **Module**: Plan | **Total**: 3 TC | **Automated**: 2

---

### TC-PLN-001 • Access Plan/Program Management Dashboard

| Field | Value |
|-------|-------|
| **Priority** | P1 | **FR ID** | FR9 | **Auto** | ✅ `PlanManagementTest#testNavigateToPlanManagement` |
| **Steps** | 1. Click "Plan" in sidebar |
| **Expected** | Plan dashboard loads. Kanban-style board visible. Columns: Backlog, To Do, In Progress, Review, Done. Experiment cards in each column. |

### TC-PLN-002 • Create Experiment Backlog Task

| Field | Value |
|-------|-------|
| **Priority** | P2 | **FR ID** | FR9 | **Auto** | ❌ Manual |
| **Test Data** | Task name = "Test homepage hero section redesign" |
| **Steps** | 1. Click "Add Task" in Backlog column → 2. Enter title → 3. Assign owner → 4. Set priority → 5. Add description/hypothesis → 6. Save |
| **Expected** | Task card appears in Backlog column. Card shows title, owner avatar, priority badge. |

### TC-PLN-003 • Kanban Column Drag and Drop

| Field | Value |
|-------|-------|
| **Priority** | P2 | **FR ID** | FR9 | **Auto** | ❌ Manual |
| **Steps** | 1. Drag a task card from "Backlog" → 2. Drop into "To Do" column |
| **Expected** | Card moves to "To Do". Status updated. Column counts update. Change persists on page refresh. |
