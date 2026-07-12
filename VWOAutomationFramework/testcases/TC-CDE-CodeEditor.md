# Code Editor — Test Cases

**File**: `TC-CDE-CodeEditor.md` | **Module**: Code Editor | **Total**: 4 TC | **Automated**: 3

---

### TC-CDE-001 • Code Editor Accepts CSS for Variation Changes

| Field | Value |
|-------|-------|
| **Priority** | P0 | **FR ID** | FR3 | **Auto** | ✅ `CodeEditorTest#testCodeEditorForVariations` |
| **Test Data** | `body { background-color: #f5f5f5; } .cta-button { color: #ffffff; background-color: #28a745; }` |
| **Steps** | 1. Open A/B test → 2. Add variation → 3. Click "Code Editor" → 4. Enter CSS → 5. Apply & Save |
| **Expected** | CSS injected. Preview shows the styled page. Changes persisted on save. |

### TC-CDE-002 • Code Editor Accepts JavaScript

| Field | Value |
|-------|-------|
| **Priority** | P1 | **FR ID** | FR3 | **Auto** | ✅ `CodeEditorTest#testCodeEditorWithJavaScript` |
| **Test Data** | `document.querySelector('.hero-title').innerText = 'Welcome to Optimized VWO';` |
| **Steps** | 1. Open Code Editor → 2. Enter JS → 3. Apply |
| **Expected** | JavaScript executes in the preview. DOM changes visible. |

### TC-CDE-003 • Toggle Between Visual and Code Editors

| Field | Value |
|-------|-------|
| **Priority** | P1 | **FR ID** | FR3 | **Auto** | ✅ `CodeEditorTest#testToggleBetweenVisualAndCodeEditor` |
| **Steps** | 1. Open Visual Editor → make a change → 2. Switch to Code Editor → 3. Switch back to Visual Editor |
| **Expected** | Code changes visible when switching to Code Editor. Visual Editor reflects the code. No data corruption on toggle. |

### TC-CDE-004 • Syntax Error Handling in Code Editor

| Field | Value |
|-------|-------|
| **Priority** | P1 | **FR ID** | FR3 | **Auto** | ❌ Manual |
| **Test Data** | Malformed CSS: `body { background-color: ; }` |
| **Steps** | 1. Open Code Editor → 2. Enter invalid CSS/JS → 3. Apply |
| **Expected** | Error message: "Invalid syntax at line X". Code NOT applied. Variation retains last valid state. |
