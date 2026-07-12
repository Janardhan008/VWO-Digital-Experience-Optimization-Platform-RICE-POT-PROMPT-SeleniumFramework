# VWO — Test Case Master Index

| Module | File | Total TC | P0 | P1 | P2 | Automated |
|--------|------|:--------:|:--:|:--:|:--:|:---------:|
| Authentication & Access Control | `TC-AUTH-Login.md` | 12 | 6 | 5 | 1 | 10 |
| Dashboard | `TC-DSH-Dashboard.md` | 6 | 4 | 2 | 0 | 4 |
| A/B Testing | `TC-ABT-ABTesting.md` | 18 | 10 | 6 | 2 | 16 |
| Split URL Testing | `TC-SPL-SplitURL.md` | 4 | 2 | 2 | 0 | 3 |
| Multivariate Testing | `TC-MVT-Multivariate.md` | 5 | 2 | 1 | 2 | 3 |
| SmartStats Engine | `TC-SS-SmartStats.md` | 6 | 3 | 3 | 0 | 4 |
| Code Editor | `TC-CDE-CodeEditor.md` | 4 | 2 | 2 | 0 | 3 |
| Audience Targeting | `TC-AUD-Audience.md` | 6 | 3 | 3 | 0 | 5 |
| Heatmaps | `TC-HTM-Heatmaps.md` | 6 | 3 | 2 | 1 | 5 |
| Session Recordings | `TC-REC-SessionRecordings.md` | 5 | 2 | 3 | 0 | 4 |
| Funnels & Surveys | `TC-FUN-FunnelsSurveys.md` | 5 | 2 | 2 | 1 | 5 |
| Personalization | `TC-PER-Personalization.md` | 10 | 5 | 5 | 0 | 8 |
| Reports & Dashboards | `TC-RPT-Reports.md` | 10 | 4 | 5 | 1 | 10 |
| Integrations | `TC-INT-Integrations.md` | 3 | 0 | 1 | 2 | 2 |
| Plan Management | `TC-PLN-PlanManagement.md` | 3 | 0 | 1 | 2 | 2 |
| **VWO UI Total** | | **103** | **48** | **43** | **12** | **84** |
| Booking API — Health Check | `api/TC-PNG-HealthCheck.md` | 6 | 4 | 2 | 0 | 6 |
| Booking API — Auth | `api/TC-AUTH-ApiAuth.md` | 5 | 2 | 3 | 0 | 5 |
| Booking API — Get All | `api/TC-BOK-GetAllBookings.md` | 10 | 4 | 6 | 0 | 10 |
| Booking API — Get Single | `api/TC-BOK-GetSingleBooking.md` | 12 | 8 | 4 | 0 | 12 |
| Booking API — Full Update | `api/TC-PUT-UpdateBooking.md` | 10 | 4 | 6 | 0 | 10 |
| Booking API — Partial Update | `api/TC-PTC-PartialUpdate.md` | 10 | 4 | 6 | 0 | 10 |
| Booking API — Delete | `api/TC-DEL-DeleteBooking.md` | 8 | 6 | 2 | 0 | 8 |
| **Booking API Total** | | **61** | **32** | **29** | **0** | **61** |
| Performance | `nfr/TC-NFR-Performance.md` | 6 | 6 | 0 | 0 | 0 |
| Security | `nfr/TC-NFR-Security.md` | 6 | 4 | 2 | 0 | 0 |
| Cross-Browser & Responsive | `nfr/TC-NFR-CrossBrowser.md` | 6 | 3 | 3 | 0 | 0 |
| Accessibility | `nfr/TC-NFR-Accessibility.md` | 5 | 1 | 4 | 0 | 0 |
| **NFR Total** | | **23** | **14** | **9** | **0** | **0** |
| **GRAND TOTAL** | | **187** | **94** | **81** | **12** | **145** |

## How to Use

- **P0**: Must pass before any release. Automated in CI pipeline.
- **P1**: Must pass before GA release. Automated in nightly regression.
- **P2**: Nice-to-have. Manual / exploratory.
- Each file uses the format: `TC-{MODULE}-{NNN}` for traceability to the VWO Test Plan.
