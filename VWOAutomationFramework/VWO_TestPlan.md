# VWO – Digital Experience Optimization Platform
## Master Test Plan

| **Document** | VWO Master Test Plan |
|---|---|
| **Product** | VWO (Visual Website Optimizer) |
| **Version** | 2.0.0 |
| **Prepared By** | QA Lead – Automation CoE |
| **Date** | July 12, 2026 |
| **Status** | Draft |
| **Priority** | Critical |

---

## Revision History

| Version | Date | Author | Description |
|---------|------|--------|-------------|
| 1.0 | 12-Jul-2026 | QA Lead | Initial Test Plan – Phase 1 |

---

## Table of Contents

1. [Introduction](#1-introduction)
2. [Test Objectives](#2-test-objectives)
3. [Scope of Testing](#3-scope-of-testing)
4. [Out of Scope](#4-out-of-scope)
5. [Test Strategy](#5-test-strategy)
6. [Test Environment](#6-test-environment)
7. [Test Data Management](#7-test-data-management)
8. [Test Deliverables](#8-test-deliverables)
9. [Test Schedule & Effort Estimation](#9-test-schedule--effort-estimation)
10. [Roles & Responsibilities](#10-roles--responsibilities)
11. [Defect Management](#11-defect-management)
12. [Risk & Mitigation](#12-risk--mitigation)
13. [Entry & Exit Criteria](#13-entry--exit-criteria)
14. [Test Automation Strategy](#14-test-automation-strategy)
15. [Test Types & Coverage Matrix](#15-test-types--coverage-matrix)
16. [Functional Test Scenarios by Module](#16-functional-test-scenarios-by-module)
17. [Non-Functional Test Scenarios](#17-non-functional-test-scenarios)
18. [Regression Testing Approach](#18-regression-testing-approach)
19. [Approvals](#19-approvals)

---

## 1. Introduction

### 1.1 Purpose

This Test Plan outlines the comprehensive testing strategy, scope, resources, and schedule for validating the **VWO (Visual Website Optimizer)** Digital Experience Optimization Platform. It serves as the single source of truth for all QA activities across functional, automation, performance, security, and user acceptance testing.

### 1.2 Product Overview

VWO is an enterprise-grade DXO/CRO platform enabling businesses to:
- Run **A/B, Split URL, and Multivariate experiments**
- Analyze user behavior via **heatmaps, session recordings, and surveys**
- Deliver **personalized experiences** through audience segmentation
- Leverage **Bayesian SmartStats** for statistically validated results
- Integrate with **CRM, analytics, and commerce platforms** (Salesforce, GA, Shopify, Segment)

### 1.3 References

| Document | Source |
|----------|--------|
| PRD v1.0 – VWO Platform | Product Team |
| Technical Architecture Document | Engineering |
| API Specification v2.1 | Engineering |
| UX Design Specs (Figma) | Design Team |
| Automation Framework | VWOAutomationFramework/ |

---

## 2. Test Objectives

1. **Functional Correctness** – Validate all features operate per PRD specifications
2. **Data Accuracy** – Ensure SmartStats, conversion metrics, and funnel analytics produce correct results
3. **Cross-Browser Compatibility** – Verify consistent behavior on Chrome, Firefox, Edge, Safari
4. **Security & Access Control** – Validate 2FA, RBAC, session management, and data privacy (GDPR/CCPA)
5. **Performance & Scalability** – Confirm < 2s page load, 99.9% uptime SLA, high-concurrency support
6. **Integration Fidelity** – Ensure data sync with Salesforce, GA, Segment, Shopify is accurate
7. **Automation Coverage** – Achieve 80%+ automation coverage for regression cycles
8. **User Experience** – Validate onboarding, guided tours, error messaging, and accessibility (WCAG 2.1 AA)

---

## 3. Scope of Testing

### 3.1 In Scope

| Module | Features Covered |
|--------|-----------------|
| **Authentication** | Login/logout, 2FA, forgot password, SSO, remember me, session timeout, RBAC |
| **Dashboard** | Home page, project selector, search, quick start guide, notifications, sidebar navigation |
| **A/B Testing** | Create/edit/launch/pause/stop experiments, variations, visual & code editor, preview, scheduling |
| **Split URL Testing** | Multi-URL experiments, traffic allocation, duplicate URL validation |
| **Multivariate Testing** | Multi-combination experiments, preview, multi-goal configuration |
| **SmartStats Engine** | Bayesian analysis display, confidence scores, winner declaration, statistical summaries |
| **Audience Targeting** | Geo, device, behavior, custom attribute, URL pattern, combined conditions, reach preview |
| **Visual & Code Editor** | WYSIWYG editing, CSS injection, JS injection, toggle between editors |
| **Heatmaps** | Click, scroll, move heatmaps, device filtering, URL input, overlay rendering |
| **Session Recordings** | Recording list, playback, filter by date/user, playback controls, export |
| **Funnels & Surveys** | Multi-step funnel creation, drop-off visualization, survey creation/publishing |
| **Personalization** | Campaign creation, segment configuration (geo/behavior/device), content editor, preview, launch, traffic % |
| **Reports & Dashboards** | Real-time dashboards, conversion summary, experiment performance table, filters, CSV/PDF export, scheduled reports |
| **Integrations** | Navigation to integrations page, connector configuration simulation |
| **Plan Management** | Program management UI, Kanban-style workflow, collaboration tools |
| **Onboarding** | Guided tours, in-app support, quick start wizard |

### 3.2 Out of Scope

- Native mobile SDK testing (iOS/Android)
- Third-party API end-to-end validation
- Load testing beyond simulated concurrency
- AI-driven suggestion engine (future enhancement)
- Predictive analytics & ROI forecasting (future enhancement)
- Database-level data integrity audits
- Infrastructure/DevOps pipeline testing

---

## 4. Test Strategy

### 4.1 Testing Levels

```
┌─────────────────────────────────────┐
│        User Acceptance Testing       │
├─────────────────────────────────────┤
│         System / E2E Testing         │
├─────────────────────────────────────┤
│         Integration Testing          │
├─────────────────────────────────────┤
│         Component / API Testing      │
├─────────────────────────────────────┤
│          Unit Testing (Dev)          │
└─────────────────────────────────────┘
```

| Level | Approach | Responsibility | Tooling |
|-------|----------|---------------|---------|
| **Unit** | TDD by developers, mocked dependencies | Dev Team | JUnit, Mockito |
| **Component** | Page-level functional validation | QA | Selenium + TestNG |
| **Integration** | API contract testing, data flow between modules | QA | REST Assured, Postman |
| **System/E2E** | End-to-end business flow validation | QA | Selenium Grid + TestNG |
| **UAT** | Business stakeholder sign-off on real scenarios | Product + Business | Manual + Exploratory |

### 4.2 Testing Types

| Type | Description | Automation % |
|------|-------------|:-----------:|
| Functional Testing | Validate all features per PRD | 80% |
| Regression Testing | Re-test after changes to ensure stability | 90% |
| Smoke/Sanity Testing | Critical path validation on each build | 100% |
| Integration Testing | Data flow between modules and external systems | 60% |
| Cross-Browser Testing | Chrome, Firefox, Edge, Safari | 100% (parallel) |
| Responsive Testing | Desktop, Tablet, Mobile viewports | 40% |
| Security Testing | 2FA, RBAC, session management, XSS, CSRF | 30% |
| Performance Testing | Page load, API response, concurrency | 20% |
| Usability Testing | Onboarding, error messages, UX consistency | Manual |
| Accessibility Testing | WCAG 2.1 AA compliance | 20% |
| Data Validation Testing | Accuracy of SmartStats, conversion metrics | 70% |

---

## 5. Test Environment

### 5.1 Environment Matrix

| Environment | URL | Purpose | Data Freshness |
|-------------|-----|---------|:--------------:|
| **DEV** | https://dev.vwo.com | Dev integration testing | Daily refresh |
| **QA** | https://qa.vwo.com | Functional & regression testing | Weekly refresh |
| **STAGE** | https://stage.vwo.com | UAT, performance, pre-prod | Prod-like |
| **PROD** | https://app.vwo.com | Smoke after release | Live |

### 5.2 Browser/Device Matrix

| Browser | Version | OS | Resolution |
|---------|---------|----|:---------:|
| Google Chrome | Latest + Previous | Windows 11, macOS | 1920x1080 |
| Mozilla Firefox | Latest + ESR | Windows 11, macOS | 1366x768 |
| Microsoft Edge | Latest | Windows 11 | 1536x864 |
| Safari | Latest 2 versions | macOS, iOS | 375x812 (iPhone) |
| Mobile Chrome | Latest | Android 13/14 | 412x915 |

### 5.3 Test Infrastructure

| Component | Specification |
|-----------|-------------|
| **Selenium Grid** | 4.x Hub + 4 nodes (2 Windows, 2 Linux, 1 macOS) |
| **CI/CD** | Jenkins Pipeline / GitHub Actions |
| **Reporting** | Extent Reports 5 + Allure Dashboard |
| **Test Management** | Jira + Zephyr Scale |
| **Defect Tracking** | Jira |
| **Test Data** | MySQL + Excel (Apache POI) |

---

## 6. Test Data Management

### 6.1 Data Sources

| Data Type | Source | Format |
|-----------|--------|--------|
| Login credentials | Config properties | `.properties` |
| Experiment configs | Excel spreadsheets | `.xlsx` |
| Audience segments | JSON fixtures | `.json` |
| Personalization content | Excel + JSON | `.xlsx`, `.json` |

### 6.2 Data Strategy

- **Pre-requisite data**: API calls in `@BeforeTest` to create accounts, projects, experiments
- **Isolated data**: Each test creates and cleans up its own data
- **Masked data**: All PII in test environments is anonymized per GDPR/CCPA
- **Data refresh**: QA environment refreshed weekly from anonymized prod snapshot

---

## 7. Test Deliverables

| Deliverable | Owner | Format | Due |
|-------------|-------|--------|:----:|
| Master Test Plan | QA Lead | Markdown/PDF | Day 1 |
| Test Case Specifications | QA Team | Jira/Zephyr | Sprint 1 |
| Automation Framework | SDET | Java + Maven | Sprint 1 |
| Test Execution Reports | QA Lead | Extent Reports | Daily |
| Defect Reports | All QA | Jira | Real-time |
| Test Completion Report | QA Lead | PDF | End of Phase |
| Automation Coverage Report | SDET | Dashboard | Weekly |
| Performance Test Report | QA Lead | PDF/HTML | Pre-release |

---

## 8. Test Schedule & Effort Estimation

### 8.1 Phase-Wise Schedule

| Phase | Duration | Activities | Deliverables |
|-------|:--------:|------------|--------------|
| **Phase 0:** Test Planning | 3 days | Requirements analysis, test plan creation, environment setup | Test Plan, Environment Ready |
| **Phase 1:** Test Design | 5 days | Test case writing, test data prep, automation framework setup | Test Cases, Framework |
| **Phase 2:** Smoke Testing | 2 days | Critical path validation, build acceptance | Smoke Report |
| **Phase 3:** Functional Testing | 10 days | Module-wise functional execution, defect logging | Execution Reports |
| **Phase 4:** Integration Testing | 5 days | Cross-module and API integration validation | Integration Report |
| **Phase 5:** Regression Testing | 5 days | Full regression suite execution | Regression Report |
| **Phase 6:** Performance Testing | 3 days | Load, stress, scalability validation | Performance Report |
| **Phase 7:** Security Testing | 3 days | 2FA, RBAC, XSS, CSRF validation | Security Report |
| **Phase 8:** UAT & Sign-off | 3 days | Business stakeholder validation | UAT Sign-off |

**Total Estimated Effort**: 39 days (~ 8 weeks)

### 8.2 Resource Plan

| Role | Allocation | Focus Area |
|------|:----------:|------------|
| QA Lead / Test Manager | 1 (100%) | Strategy, reporting, stakeholder management |
| Senior QA Engineer | 2 (100%) | Test design, execution, defect management |
| SDET (Automation) | 2 (100%) | Framework development, script writing |
| Performance Engineer | 1 (50%) | Load & performance testing |
| Security Tester | 1 (25%) | Security test execution |

---

## 9. Roles & Responsibilities

| Role | Name/Team | Responsibilities |
|------|-----------|-----------------|
| **QA Lead** | Pramod Dutta | Test strategy, planning, reporting, stakeholder communication |
| **Senior QA** | QA Team – CoE | Test case design, manual execution, exploratory testing, UAT support |
| **SDET** | Automation CoE | Framework design, script development, CI/CD integration, code review |
| **Dev Lead** | Engineering | Unit testing, bug fixes, code review support |
| **Product Owner** | Product Team | Requirement clarification, UAT sign-off, priority decisions |
| **DevOps** | Infrastructure Team | Environment management, CI/CD pipeline, test infrastructure |

---

## 10. Defect Management

### 10.1 Severity & Priority Matrix

| | **P1 – Critical** | **P2 – High** | **P3 – Medium** | **P4 – Low** |
|---|---|---|---|---|
| **S1 – Blocker** | Fix immediately, stop release | Fix in current sprint | Fix in next sprint | Deferred |
| **S2 – Major** | Fix in 24 hrs | Fix in current sprint | Fix in next sprint | Deferred |
| **S3 – Minor** | Fix in current sprint | Fix in current sprint | Fix in next release | Deferred |
| **S4 – Trivial** | Fix in current sprint | Fix in next sprint | Fix in next release | Icebox |

### 10.2 Defect Lifecycle

```
New → Assigned → In Progress → Fixed → Ready for Retest → Verified → Closed
  ↓                                                          ↓
  ↓                                                     Rejected
  ↓                                                         ↓
  ↓                                                    Reopen → In Progress
  ↓
Duplicate / Won't Fix / Deferred
```

### 10.3 SLA for Defect Resolution

| Severity | Initial Response | Fix Time | Re-test Time |
|:--------:|:----------------:|:--------:|:------------:|
| S1 | 1 hour | 4 hours | 2 hours |
| S2 | 4 hours | 24 hours | 4 hours |
| S3 | 8 hours | 48 hours | 8 hours |
| S4 | 24 hours | Next sprint | 24 hours |

---

## 11. Risk & Mitigation

| # | Risk | Probability | Impact | Mitigation |
|---|------|:-----------:|:------:|------------|
| R1 | Environment instability (frequent resets/downtime) | High | High | Dedicated QA environment with SLA; containerized test infrastructure |
| R2 | Test data inconsistency across parallel runs | Medium | High | Isolated data per test; `@BeforeTest` data setup + `@AfterTest` cleanup |
| R3 | Flaky automated tests due to timing/network | High | Medium | Retry mechanism (max 2 retries); WebDriverWait everywhere; reducing test interdependency |
| R4 | Requirements gaps / mid-sprint changes | Medium | High | Daily sync with Product; BDD scenarios as living documentation; impact assessment on every change |
| R5 | Cross-browser compatibility issues discovered late | Medium | Medium | Browser matrix testing from Day 1; parallel grid execution every build |
| R6 | SmartStats accuracy – statistical validation complexity | Medium | High | Test against known datasets with expected outputs; statistical sanity checks in automation |
| R7 | Third-party integration unavailability | Low | High | Mock external APIs for functional testing; integration tests run against sandbox instances |

---

## 12. Entry & Exit Criteria

### 12.1 Phase Entry / Exit Gates

| Gate | Entry Criteria | Exit Criteria |
|------|----------------|---------------|
| **Smoke Testing** | Build deployed to QA env; smoke test cases ready | All P0 tests pass; no S1/S2 defects on critical path |
| **Functional Testing** | Smoke passed; test environment stable; test cases reviewed | All functional test cases executed; S1/S2 defects fixed & verified |
| **Regression Testing** | Functional testing completed; defect backlog < 10 | Regression pass rate > 95%; no S1/S2 open defects |
| **UAT** | Regression passed; Release Candidate deployed to Stage | Business stakeholders sign-off; no S1 defects |
| **Production Release** | UAT signed off; performance/stress tests within threshold; security scan clean | All entry criteria met; rollback plan confirmed |

### 12.2 Quality Gates (Release Criteria)

- [ ] All P0 and P1 test cases pass (100%)
- [ ] Zero S1 (Blocker) defects open
- [ ] S2 defects < 5 and no critical business flow impact
- [ ] Automation regression pass rate ≥ 95%
- [ ] Performance: Page load < 2s (editing workflows), API response < 500ms
- [ ] Cross-browser: All critical flows pass on Chrome, Firefox, Edge
- [ ] Security: 2FA, RBAC, session timeout verified; no XSS/CSRF vulnerabilities
- [ ] Accessibility: WCAG 2.1 AA compliance verified

---

## 13. Test Automation Strategy

### 13.1 Framework Architecture (Already Built)

```
VWOAutomationFramework/
├── src/main/java/
│   ├── base/         → BaseTest (ThreadLocal<WebDriver>), BasePage
│   ├── pages/        → 6 Page Objects (Login, Dashboard, Experiment, Insights, Personalize, Reports)
│   ├── utils/        → ConfigReader, ExcelReader, ScreenshotUtil, TestDataProvider
│   ├── listeners/    → TestListener (Extent Reports integration)
│   ├── constants/    → FrameworkConstants
│   └── enums/        → ExperimentType, ExperimentStatus, AudienceType, InsightType
├── src/test/java/tests/
│   ├── login/        → 10 test scenarios
│   ├── experiments/  → 19 test scenarios (A/B, Split URL, MVT, SmartStats, Code Editor, Audience)
│   ├── insights/     → 14 test scenarios (Heatmap, Recording, Funnel, Survey)
│   ├── personalization/ → 8 test scenarios
│   ├── reports/      → 10 test scenarios
│   ├── dashboard/    → 4 test scenarios
│   ├── integrations/ → 2 test scenarios
│   └── plan/         → 2 test scenarios
├── testng.xml        → 14 test suites, parallel=classes, thread-count=4
└── pom.xml           → Selenium 4, TestNG 7.11, Extent 5.1, Apache POI 5.4, Log4j2
```

### 13.2 Automation Coverage Target by Module

| Module | Total Test Cases | Automated | Coverage % |
|--------|:----------------:|:---------:|:----------:|
| Authentication | 12 | 10 | 83% |
| Dashboard | 6 | 4 | 67% |
| A/B Testing | 18 | 16 | 89% |
| Split URL Test | 4 | 3 | 75% |
| Multivariate Test | 5 | 3 | 60% |
| SmartStats | 6 | 4 | 67% |
| Code Editor | 4 | 3 | 75% |
| Audience Targeting | 6 | 5 | 83% |
| Heatmaps | 6 | 5 | 83% |
| Session Recordings | 5 | 4 | 80% |
| Funnels & Surveys | 6 | 5 | 83% |
| Personalization | 10 | 8 | 80% |
| Reports & Dashboards | 12 | 10 | 83% |
| Integrations | 3 | 2 | 67% |
| Plan Management | 3 | 2 | 67% |
| **TOTAL** | **106** | **84** | **79%** |

### 13.3 CI/CD Integration

```
[Git Push] → [GitHub Actions Trigger]
    → [Maven Compile] → [SonarQube Analysis]
    → [Parallel TestNG Suites on Selenium Grid]
        → [Chrome, Firefox, Edge – 4 parallel threads]
    → [Extent Reports Generation]
    → [Allure Dashboard Update]
    → [Jira Test Execution Sync (Zephyr)]
    → [Slack Notification – Summary + Report Link]
```

---

## 14. Test Types & Coverage Matrix

| Test Type | Validation Focus | Tool/Approach | Priority |
|-----------|-----------------|---------------|:--------:|
| **Positive Functional** | Happy path for each feature | Selenium + TestNG | P0 |
| **Negative Functional** | Error handling, validation messages | Selenium + TestNG | P0 |
| **Boundary Value Analysis** | Edge cases (min/max lengths, limits) | Data-driven | P1 |
| **Equivalence Partitioning** | Valid/invalid input classes | Data-driven | P1 |
| **Cross-Browser** | Consistent rendering across browsers | Selenium Grid | P0 |
| **Responsive/Viewport** | Layout adaptation at breakpoints | Selenium (window size) | P1 |
| **Data Integrity** | Accuracy of metrics, conversions, stats | SQL + API validation | P0 |
| **Concurrency** | Multiple users, parallel experiments | Selenium Grid + parallel threads | P1 |
| **Session Management** | Timeout, concurrent sessions, token expiry | Selenium | P0 |
| **RBAC** | Role-based access restrictions | Selenium (multi-role login) | P0 |
| **API Contract** | API request/response validation | REST Assured | P1 |

---

## 15. Functional Test Scenarios by Module

### 15.1 Authentication & Access Control

| # | Scenario | Priority | Automation | FR ID |
|---|----------|:--------:|:----------:|:-----:|
| TC-AUTH-001 | Verify login with valid email and password | P0 | ✅ | FR6 |
| TC-AUTH-002 | Verify login with invalid password shows error | P0 | ✅ | FR6 |
| TC-AUTH-003 | Verify login with unregistered email shows error | P0 | ✅ | FR6 |
| TC-AUTH-004 | Verify login with empty credentials blocked | P0 | ✅ | FR6 |
| TC-AUTH-005 | Verify password field masks input characters (security) | P0 | ✅ | NFR |
| TC-AUTH-006 | Verify 2FA code input appears after login | P0 | ✅ | NFR |
| TC-AUTH-007 | Verify invalid 2FA code blocks access | P1 | ❌ Manual | NFR |
| TC-AUTH-008 | Verify Forgot Password flow sends reset email | P1 | ✅ | FR6 |
| TC-AUTH-009 | Verify SSO login redirects to IdP | P1 | ✅ | FR6 |
| TC-AUTH-010 | Verify Remember Me persists session | P0 | ✅ | FR6 |
| TC-AUTH-011 | Verify session timeout logs user out after inactivity | P1 | ❌ Manual | NFR |
| TC-AUTH-012 | Verify logout clears session and returns to login | P0 | ✅ | FR6 |

### 15.2 Dashboard

| # | Scenario | Priority | Automation | FR ID |
|---|----------|:--------:|:----------:|:-----:|
| TC-DSH-001 | Verify dashboard loads with all key elements | P0 | ✅ | FR6 |
| TC-DSH-002 | Verify recent activity panel shows latest actions | P1 | ✅ | FR6 |
| TC-DSH-003 | Verify active experiments count matches actual | P0 | ✅ | FR6 |
| TC-DSH-004 | Verify sidebar navigation to all modules | P0 | ✅ | FR6 |
| TC-DSH-005 | Verify search filters experiments in real-time | P1 | ✅ | FR6 |
| TC-DSH-006 | Verify project selector filters workspace data | P1 | ✅ | FR8 |

### 15.3 A/B Testing

| # | Scenario | Priority | Automation | FR ID |
|---|----------|:--------:|:----------:|:-----:|
| TC-ABT-001 | Create A/B test with valid data | P0 | ✅ | FR1 |
| TC-ABT-002 | Create A/B test without name – validation error | P0 | ✅ | 6 |
| TC-ABT-003 | Add multiple variations (control + up to 10) | P0 | ✅ | FR1 |
| TC-ABT-004 | Edit variation using Visual Editor (WYSIWYG) | P0 | ✅ | FR3 |
| TC-ABT-005 | Edit variation using Code Editor (CSS/JS) | P1 | ✅ | FR3 |
| TC-ABT-006 | Toggle between Visual and Code Editors | P1 | ✅ | FR3 |
| TC-ABT-007 | Set primary metric (conversion rate, revenue, click) | P0 | ✅ | FR1 |
| TC-ABT-008 | Add multiple goals to experiment | P1 | ✅ | FR1 |
| TC-ABT-009 | Launch experiment → status changes to Running | P0 | ✅ | FR1 |
| TC-ABT-010 | Pause running experiment → status Paused | P0 | ✅ | FR1 |
| TC-ABT-011 | Resume paused experiment → status Running | P0 | ❌ Manual | FR1 |
| TC-ABT-012 | Stop experiment → status Stopped | P0 | ✅ | FR1 |
| TC-ABT-013 | Declare winner variation after significance | P1 | ✅ | FR2 |
| TC-ABT-014 | Schedule experiment with start/end dates | P1 | ✅ | FR1 |
| TC-ABT-015 | Preview variations across devices | P1 | ✅ | FR1 |
| TC-ABT-016 | Set traffic allocation percentage | P1 | ✅ | FR1 |
| TC-ABT-017 | Duplicate an existing experiment | P2 | ❌ Manual | FR1 |
| TC-ABT-018 | Delete experiment in draft status | P2 | ❌ Manual | FR1 |

### 15.4 Split URL Testing

| # | Scenario | Priority | Automation | FR ID |
|---|----------|:--------:|:----------:|:-----:|
| TC-SPL-001 | Create Split URL test with 2+ destination URLs | P0 | ✅ | FR1 |
| TC-SPL-002 | Traffic distribution across variations | P1 | ✅ | FR1 |
| TC-SPL-003 | Duplicate URL validation | P1 | ✅ | 6 |
| TC-SPL-004 | Split URL with different domains | P1 | ❌ Manual | FR1 |

### 15.5 Multivariate Testing

| # | Scenario | Priority | Automation | FR ID |
|---|----------|:--------:|:----------:|:-----:|
| TC-MVT-001 | Create MVT with 4+ combination variations | P0 | ✅ | FR1 |
| TC-MVT-002 | Preview all MVT combinations | P1 | ✅ | FR1 |
| TC-MVT-003 | Configure multiple goals for MVT | P1 | ✅ | FR1 |
| TC-MVT-004 | MVT results broken down by factor interaction | P2 | ❌ Manual | FR2 |
| TC-MVT-005 | MVT with > 8 combinations (performance check) | P2 | ❌ Manual | FR1 |

### 15.6 SmartStats Engine

| # | Scenario | Priority | Automation | FR ID |
|---|----------|:--------:|:----------:|:-----:|
| TC-SS-001 | SmartStats panel visible in experiment details | P0 | ✅ | FR2 |
| TC-SS-002 | Bayesian confidence score displayed per variation | P0 | ✅ | FR2 |
| TC-SS-003 | Conversion rate, lift, and visitor count shown | P0 | ✅ | FR2 |
| TC-SS-004 | Winner automatically suggested at 95% confidence | P1 | ✅ | FR2 |
| TC-SS-005 | Manual winner override possible | P1 | ❌ Manual | FR2 |
| TC-SS-006 | SmartStats data refreshes in real-time | P1 | ❌ Manual | FR2 |

### 15.7 Audience Targeting

| # | Scenario | Priority | Automation | FR ID |
|---|----------|:--------:|:----------:|:-----:|
| TC-AUD-001 | Target by geography (country, city, region) | P0 | ✅ | FR5 |
| TC-AUD-002 | Target by device type (Desktop, Mobile, Tablet) | P0 | ✅ | FR5 |
| TC-AUD-003 | Target by behavior (new vs returning visitor) | P0 | ✅ | FR5 |
| TC-AUD-004 | Target by custom attribute | P1 | ✅ | FR5 |
| TC-AUD-005 | Combine multiple audience conditions (AND logic) | P1 | ✅ | FR5 |
| TC-AUD-006 | Audience preview shows estimated reach % | P1 | ✅ | FR5 |

### 15.8 Heatmaps

| # | Scenario | Priority | Automation | FR ID |
|---|----------|:--------:|:----------:|:-----:|
| TC-HTM-001 | Generate click heatmap for valid page URL | P0 | ✅ | FR4 |
| TC-HTM-002 | Generate scroll heatmap with depth overlay | P0 | ✅ | FR4 |
| TC-HTM-003 | Generate move heatmap | P1 | ✅ | FR4 |
| TC-HTM-004 | Filter heatmap by device type (Mobile, Tablet, Desktop) | P1 | ✅ | FR4 |
| TC-HTM-005 | Invalid URL shows graceful error | P1 | ✅ | 6 |
| TC-HTM-006 | Heatmap overlay disappears on exiting heatmap view | P2 | ❌ Manual | FR4 |

### 15.9 Session Recordings

| # | Scenario | Priority | Automation | FR ID |
|---|----------|:--------:|:----------:|:-----:|
| TC-REC-001 | Session recordings list loads with metadata | P0 | ✅ | FR4 |
| TC-REC-002 | Play session recording opens player | P0 | ✅ | FR4 |
| TC-REC-003 | Playback controls (play, pause, speed) functional | P1 | ✅ | FR4 |
| TC-REC-004 | Filter recordings by date range | P1 | ✅ | FR4 |
| TC-REC-005 | Filter recordings by user ID | P1 | ✅ | FR4 |

### 15.10 Funnels & Surveys

| # | Scenario | Priority | Automation | FR ID |
|---|----------|:--------:|:----------:|:-----:|
| TC-FUN-001 | Create funnel with 2+ steps | P0 | ✅ | FR4 |
| TC-FUN-002 | Funnel shows drop-off % at each step | P0 | ✅ | FR4 |
| TC-FUN-003 | Create and publish on-page survey | P1 | ✅ | FR4 |
| TC-FUN-004 | Survey responses collected in insights dashboard | P2 | ❌ Manual | FR4 |
| TC-FUN-005 | Export funnel data to CSV | P1 | ✅ | FR6 |

### 15.11 Personalization

| # | Scenario | Priority | Automation | FR ID |
|---|----------|:--------:|:----------:|:-----:|
| TC-PER-001 | Create personalization campaign | P0 | ✅ | FR7 |
| TC-PER-002 | Add geographic segment to campaign | P0 | ✅ | FR7 |
| TC-PER-003 | Add behavioral segment (new vs returning) | P0 | ✅ | FR7 |
| TC-PER-004 | Add device-based segment | P1 | ✅ | FR7 |
| TC-PER-005 | Configure personalized content (text/HTML/CSS) | P0 | ✅ | FR7 |
| TC-PER-006 | Preview personalization before launch | P1 | ✅ | FR7 |
| TC-PER-007 | Launch campaign → status Running | P0 | ✅ | FR7 |
| TC-PER-008 | Pause active campaign | P1 | ✅ | FR7 |
| TC-PER-009 | Set traffic % for personalization | P1 | ✅ | FR7 |
| TC-PER-010 | Campaign with multiple segments (geo + behavior + device) | P1 | ✅ | FR7 |

### 15.12 Reports & Dashboards

| # | Scenario | Priority | Automation | FR ID |
|---|----------|:--------:|:----------:|:-----:|
| TC-RPT-001 | Reports page loads with summary statistics | P0 | ✅ | FR6 |
| TC-RPT-002 | Experiment performance table displays correctly | P0 | ✅ | FR6 |
| TC-RPT-003 | Filter reports by experiment status | P1 | ✅ | FR6 |
| TC-RPT-004 | Filter reports by project | P1 | ✅ | FR6 |
| TC-RPT-005 | Filter reports by date range | P1 | ✅ | FR6 |
| TC-RPT-006 | Chart visualizations render on reports | P1 | ✅ | FR6 |
| TC-RPT-007 | Export report to CSV | P1 | ✅ | FR6 |
| TC-RPT-008 | Export report to PDF | P1 | ✅ | FR6 |
| TC-RPT-009 | Schedule recurring email report | P2 | ✅ | FR6 |
| TC-RPT-010 | Conversion summary shows lift/decline trends | P1 | ✅ | FR6 |

### 15.13 Integrations

| # | Scenario | Priority | Automation | FR ID |
|---|----------|:--------:|:----------:|:-----:|
| TC-INT-001 | Navigate to Integrations listing page | P1 | ✅ | FR8 |
| TC-INT-002 | Search for integration connector | P2 | ❌ Manual | FR8 |
| TC-INT-003 | Connect to Google Analytics (API auth) | P2 | ❌ Manual | FR8 |

### 15.14 Plan Management

| # | Scenario | Priority | Automation | FR ID |
|---|----------|:--------:|:----------:|:-----:|
| TC-PLN-001 | Access Plan/Program Management dashboard | P1 | ✅ | FR9 |
| TC-PLN-002 | Create experiment backlog task | P2 | ❌ Manual | FR9 |
| TC-PLN-003 | Kanban column drag and drop | P2 | ❌ Manual | FR9 |

---

## 16. Non-Functional Test Scenarios

### 16.1 Performance (NFR)

| # | Scenario | Criteria | Tool |
|---|----------|----------|:----:|
| TC-PRF-01 | Dashboard page load time | < 2s | Selenium + StopWatch |
| TC-PRF-02 | Experiment creation/save workflow | < 2s | Selenium + StopWatch |
| TC-PRF-03 | Heatmap generation time | < 5s | Selenium + StopWatch |
| TC-PRF-04 | Report page load with 100+ experiments | < 3s | Selenium + StopWatch |
| TC-PRF-05 | API response time for experiment results | < 500ms | REST Assured |
| TC-PRF-06 | Concurrent experiment launches (50 simultaneous) | No failures | JMeter |

### 16.2 Security (NFR)

| # | Scenario | Criteria | Approach |
|---|----------|----------|:--------:|
| TC-SEC-01 | 2FA enforcement for enterprise accounts | Mandatory for enabled orgs | Selenium |
| TC-SEC-02 | RBAC – Admin can access all, Viewer read-only | Correct role mapping | Selenium (multi-role) |
| TC-SEC-03 | Session timeout after 30 min inactivity | Auto-redirect to login | Selenium |
| TC-SEC-04 | XSS injection prevention | Script tags sanitized | Selenium (inject + verify) |
| TC-SEC-05 | CSRF token validation on form submissions | Token mismatch returns 403 | REST Assured |
| TC-SEC-06 | Password complexity enforcement | Min 8 chars, 1 upper, 1 special | Selenium |

### 16.3 Cross-Browser & Responsive (NFR)

| # | Scenario | Browsers | Approach |
|---|----------|:--------:|:--------:|
| TC-CBR-01 | Login flow across all browsers | Chrome, FF, Edge, Safari | Selenium Grid |
| TC-CBR-02 | A/B test creation across browsers | Chrome, FF, Edge, Safari | Selenium Grid |
| TC-CBR-03 | Heatmap rendering across browsers | Chrome, FF, Edge, Safari | Selenium Grid |
| TC-CBR-04 | Dashboard layout at 1920x1080 | All | Viewport validation |
| TC-CBR-05 | Dashboard layout at 768x1024 (Tablet) | Chrome, Safari | Viewport validation |
| TC-CBR-06 | Dashboard layout at 375x812 (Mobile) | Chrome, Safari | Viewport validation |

### 16.4 Accessibility (NFR – WCAG 2.1 AA)

| # | Check | Criteria | Tool |
|---|-------|----------|:----:|
| TC-ACC-01 | All images have alt text | `alt` attribute present | axe-core |
| TC-ACC-02 | Color contrast ratio | ≥ 4.5:1 for normal text | axe-core |
| TC-ACC-03 | Keyboard navigation | All interactive elements reachable | Tab key test |
| TC-ACC-04 | Form labels associated | `label[for]` matches input `id` | axe-core |
| TC-ACC-05 | ARIA landmarks present | `role="navigation"`, `role="main"` | axe-core |

---

## 17. Regression Testing Approach

### 17.1 Regression Test Selection

```
                           ┌──────────────────┐
                           │  Commit / PR      │
                           └────────┬─────────┘
                                    │
                                    ▼
                    ┌───────────────────────────────┐
                    │  Impact Analysis              │
                    │  - Changed files               │
                    │  - Affected modules            │
                    │  - Risk level (Low/Med/High)   │
                    └───────────────┬───────────────┘
                                    │
                    ┌───────────────┴───────────────┐
                    │                               │
                    ▼                               ▼
        ┌─────────────────────┐         ┌─────────────────────┐
        │   Critical Path     │         │   Full Regression   │
        │   Smoke Suite       │         │   Suite (Weekend)   │
        │   (15 min)          │         │   (45 min)          │
        └─────────────────────┘         └─────────────────────┘
```

| Regression Type | Scope | Frequency | Duration |
|-----------------|-------|:---------:|:--------:|
| **Smoke** | All P0 test cases | Every build | 15 min |
| **Mini Regression** | P0 + affected modules | Every PR merge | 25 min |
| **Full Regression** | All automated tests | Nightly / Pre-release | 45 min |
| **Manual Regression** | Non-automated + exploratory | Weekly | 2 hrs |

### 17.2 Regression Automation Suite

The full regression suite consists of **84 automated tests** across **15 test classes**:

```
Total Tests  : 84
Total Time   : ~45 minutes (parallel=classes, 4 threads)
Success Rate : Target ≥ 95%
```

| Suite | Tests | Time (min) | Frequency |
|-------|:-----:|:----------:|:---------:|
| Smoke (P0) | 30 | 15 | Every build |
| Login + Dashboard | 14 | 8 | Every PR |
| Experiments | 19 | 12 | Nightly |
| Insights | 14 | 10 | Nightly |
| Personalization | 8 | 6 | Nightly |
| Reports | 10 | 7 | Nightly |
| Integrations + Plan | 4 | 3 | Weekly |

---

## 18. Test Execution & Reporting

### 18.1 Execution Workflow

```
Day Start
    ↓
Pick tests from Test Suite (priority order)
    ↓
Execute tests (Manual / Automated)
    ↓
Pass → Log in Test Management (Jira/Zephyr)
Fail → Log defect in Jira → Assign to Dev
    ↓
Dev fixes → Re-run test → Verify → Close
    ↓
Daily Status Report to Stakeholders
    ↓
End of Day → Update Execution Dashboard
```

### 18.2 Reporting Cadence

| Report | Audience | Frequency | Format |
|--------|----------|:---------:|:------:|
| Daily Execution Summary | QA Team + Lead | Daily | Slack + Dashboard |
| Defect Report | Dev + QA | Daily | Jira Dashboard |
| Test Cycle Report | QA Lead + PM | Per release | Extent Report (HTML) |
| Automation Coverage | QA Lead | Weekly | Dashboard |
| Phase Completion Report | All Stakeholders | Per phase | PDF |

---

## 19. Approvals

| Role | Name | Signature | Date |
|------|------|:---------:|:----:|
| QA Lead | | | |
| Product Owner | | | |
| Engineering Lead | | | |
| Project Manager | | | |

---

## 20. API Testing – Booking Engine

### 20.1 Module Overview

| **Module** | Booking Engine REST API |
|---|---|
| **Base URL** | `https://restful-booker.herokuapp.com` |
| **Protocol** | REST over HTTPS |
| **Content-Type** | `application/json` |
| **Auth** | Basic Auth (username: `admin`, password: `password123`) |
| **Tools** | REST Assured + TestNG / Postman |

### 20.2 Coverage Summary

| Endpoint | Method | Test Cases | P0 | P1 | Est. Time (min) |
|----------|:------:|:----------:|:--:|:--:|:---------------:|
| Health Check | `GET /ping` | 6 | 4 | 2 | 10 |
| Get All Booking | `GET /booking` | 10 | 4 | 6 | 10 |
| Get Single Booking | `GET /booking/{id}` | 12 | 8 | 4 | 15 |
| Full Update | `PUT /booking/{id}` | 10 | 4 | 6 | 15 |
| Partial Update | `PATCH /booking/{id}` | 10 | 6 | 4 | 10 |
| Delete Booking | `DELETE /booking/{id}` | 8 | 6 | 2 | 5 |
| **TOTAL** | | **56** | **32** | **24** | **65** |

### 20.3 Test Case Preconditions

- Booking IDs 1–10 are pre-seeded in the test environment
- Auth token obtained via `POST /auth` for write operations
- Test data isolation: each destructive test creates its own resource

### 20.4 Ping / Health Check — `GET /ping`

| # | Scenario | Priority | Automation | API Ref |
|---|----------|:--------:|:----------:|:--------:|
| TC-PNG-001 | Verify health check returns `201 Created` with no body | P0 | ✅ | `GET /ping` |
| TC-PNG-002 | Verify response time is under 500ms | P0 | ✅ | `GET /ping` |
| TC-PNG-003 | Verify endpoint responds without authentication | P0 | ✅ | `GET /ping` |
| TC-PNG-004 | Verify endpoint does not accept `POST` / `PUT` / `DELETE` (405) | P1 | ✅ | `GET /ping` |
| TC-PNG-005 | Verify health check after consecutive 10 rapid calls | P1 | ✅ | `GET /ping` |
| TC-PNG-006 | Verify response headers (`Content-Type`, `Server`, `Date`) are present | P1 | ✅ | `GET /ping` |

### 20.5 Get All Booking — `GET /booking`

| # | Scenario | Priority | Automation | API Ref |
|---|----------|:--------:|:----------:|:--------:|
| TC-BOK-001 | Verify endpoint returns `200 OK` with array of booking IDs | P0 | ✅ | `GET /booking` |
| TC-BOK-002 | Verify response body is a non-empty JSON array | P0 | ✅ | `GET /booking` |
| TC-BOK-003 | Verify each object has `bookingid` as integer | P0 | ✅ | `GET /booking` |
| TC-BOK-004 | Verify `bookingid` values are unique and sequential | P1 | ✅ | `GET /booking` |
| TC-BOK-005 | Verify filtering by `firstname` returns matching results | P1 | ✅ | `GET /booking?firstname=John` |
| TC-BOK-006 | Verify filtering by `lastname` returns matching results | P1 | ✅ | `GET /booking?lastname=Smith` |
| TC-BOK-007 | Verify filtering by `checkin` date returns correct bookings | P1 | ✅ | `GET /booking?checkin=2026-01-01` |
| TC-BOK-008 | Verify filtering by `checkout` date returns correct bookings | P1 | ✅ | `GET /booking?checkout=2026-02-01` |
| TC-BOK-009 | Verify combined filters (`firstname` + `checkin`) work correctly | P1 | ✅ | `GET /booking?firstname=John&checkin=2026-01-01` |
| TC-BOK-010 | Verify invalid filter value returns empty array `[]` | P1 | ✅ | `GET /booking?firstname=NonExistent` |

### 20.6 Get Single Booking — `GET /booking/{id}`

| # | Scenario | Priority | Automation | API Ref |
|---|----------|:--------:|:----------:|:--------:|
| TC-BOK-011 | Verify valid booking id returns `200 OK` with full booking details | P0 | ✅ | `GET /booking/1` |
| TC-BOK-012 | Verify response includes all required fields (`firstname`, `lastname`, `totalprice`, `depositpaid`, `bookingdates`, `additionalneeds`) | P0 | ✅ | `GET /booking/1` |
| TC-BOK-013 | Verify `firstname` and `lastname` are non-empty strings | P0 | ✅ | `GET /booking/1` |
| TC-BOK-014 | Verify `totalprice` is a positive number | P0 | ✅ | `GET /booking/1` |
| TC-BOK-015 | Verify `depositpaid` is a boolean | P0 | ✅ | `GET /booking/1` |
| TC-BOK-016 | Verify `bookingdates` contains `checkin` and `checkout` in `YYYY-MM-DD` format | P0 | ✅ | `GET /booking/1` |
| TC-BOK-017 | Verify `checkout` date is >= `checkin` date | P0 | ✅ | `GET /booking/1` |
| TC-BOK-018 | Verify non-existent id returns `404 Not Found` | P0 | ✅ | `GET /booking/99999` |
| TC-BOK-019 | Verify negative id returns `400 Bad Request` or `404 Not Found` | P1 | ✅ | `GET /booking/-1` |
| TC-BOK-020 | Verify string id returns `400 Bad Request` | P1 | ✅ | `GET /booking/abc` |
| TC-BOK-021 | Verify special character id returns `400 Bad Request` | P1 | ✅ | `GET /booking/@#$` |
| TC-BOK-022 | Verify response without `Accept` header defaults to JSON | P1 | ✅ | `GET /booking/1` |

### 20.7 Full Update (PUT) — `PUT /booking/{id}`

| # | Scenario | Priority | Automation | API Ref |
|---|----------|:--------:|:----------:|:--------:|
| TC-PUT-001 | Verify full update with valid auth returns `200 OK` with updated body | P0 | ✅ | `PUT /booking/1` |
| TC-PUT-002 | Verify all fields are updated correctly after PUT | P0 | ✅ | `PUT /booking/1` |
| TC-PUT-003 | Verify response body matches request payload exactly (round-trip) | P0 | ✅ | `PUT /booking/1` |
| TC-PUT-004 | Verify update without auth header returns `403 Forbidden` | P0 | ✅ | `PUT /booking/1` |
| TC-PUT-005 | Verify update with invalid auth returns `403 Forbidden` | P1 | ✅ | `PUT /booking/1` |
| TC-PUT-006 | Verify update to non-existent id returns `405 Method Not Allowed` or `404` | P1 | ✅ | `PUT /booking/99999` |
| TC-PUT-007 | Verify update with missing required fields returns `400 Bad Request` | P1 | ✅ | `PUT /booking/1` |
| TC-PUT-008 | Verify update with invalid data types (string for `totalprice`) returns `400` | P1 | ✅ | `PUT /booking/1` |
| TC-PUT-009 | Verify update with extra unexpected fields is accepted (backward compatible) | P1 | ✅ | `PUT /booking/1` |
| TC-PUT-010 | Verify `checkout` before `checkin` in update returns `400 Bad Request` | P1 | ✅ | `PUT /booking/1` |

### 20.8 Partial Update (PATCH) — `PATCH /booking/{id}`

| # | Scenario | Priority | Automation | API Ref |
|---|----------|:--------:|:----------:|:--------:|
| TC-PTC-001 | Verify partial update with single field (`firstname`) returns `200 OK` | P0 | ✅ | `PATCH /booking/1` |
| TC-PTC-002 | Verify only the patched field changes, others remain intact | P0 | ✅ | `PATCH /booking/1` |
| TC-PTC-003 | Verify patch with `totalprice` updates correctly | P0 | ✅ | `PATCH /booking/1` |
| TC-PTC-004 | Verify patch with `bookingdates.checkin` updates correctly | P0 | ✅ | `PATCH /booking/1` |
| TC-PTC-005 | Verify patch without auth returns `403 Forbidden` | P0 | ✅ | `PATCH /booking/1` |
| TC-PTC-006 | Verify patch with empty body returns `400 Bad Request` | P1 | ✅ | `PATCH /booking/1` |
| TC-PTC-007 | Verify patch to non-existent id returns `404 Not Found` | P1 | ✅ | `PATCH /booking/99999` |
| TC-PTC-008 | Verify patch with invalid field name ignores unknown field | P1 | ✅ | `PATCH /booking/1` |
| TC-PTC-009 | Verify patch with null value for required field returns `400 Bad Request` | P1 | ✅ | `PATCH /booking/1` |
| TC-PTC-010 | Verify patch with `additionalneeds` set to empty string works | P1 | ✅ | `PATCH /booking/1` |

### 20.9 Delete Booking — `DELETE /booking/{id}`

| # | Scenario | Priority | Automation | API Ref |
|---|----------|:--------:|:----------:|:--------:|
| TC-DEL-001 | Verify delete with valid auth returns `201 Created` | P0 | ✅ | `DELETE /booking/1` |
| TC-DEL-002 | Verify deleted booking returns `404 Not Found` on subsequent GET | P0 | ✅ | `GET /booking/1` after `DELETE` |
| TC-DEL-003 | Verify delete without auth header returns `403 Forbidden` | P0 | ✅ | `DELETE /booking/1` |
| TC-DEL-004 | Verify delete with invalid auth returns `403 Forbidden` | P0 | ✅ | `DELETE /booking/1` |
| TC-DEL-005 | Verify delete of non-existent id returns `405 Method Not Allowed` | P1 | ✅ | `DELETE /booking/99999` |
| TC-DEL-006 | Verify double delete of same id returns `405 Method Not Allowed` | P1 | ✅ | `DELETE /booking/1` (x2) |
| TC-DEL-007 | Verify `DELETE` with `GET` method returns `405` | P0 | ✅ | `GET /booking/1` with del intent |
| TC-DEL-008 | Verify delete booking created mid-test (self-healing data) | P1 | ✅ | Create → Delete → Verify |

### 20.10 Authorization — `POST /auth`

| # | Scenario | Priority | Automation | API Ref |
|---|----------|:--------:|:----------:|:--------:|
| TC-AUTH-020 | Verify valid credentials return `200 OK` with token | P0 | ✅ | `POST /auth` |
| TC-AUTH-021 | Verify token is a non-empty string | P0 | ✅ | `POST /auth` |
| TC-AUTH-022 | Verify invalid username returns `200 OK` but may fail on subsequent write | P1 | ✅ | `POST /auth` |
| TC-AUTH-023 | Verify empty credentials return `400 Bad Request` | P1 | ✅ | `POST /auth` |
| TC-AUTH-024 | Verify missing `Content-Type` header returns `400 Bad Request` | P1 | ✅ | `POST /auth` |

### 20.11 Automation Implementation

```java
// Example: REST Assured test for GET /booking/{id}
@Test(description = "TC-BOK-011: Verify valid booking id returns 200 OK")
public void testGetSingleBookingValid() {
    Booking response = given()
        .spec(requestSpec)
    .when()
        .get("/booking/1")
    .then()
        .statusCode(200)
        .extract()
        .as(Booking.class);

    assertThat(response.getFirstname()).isNotEmpty();
    assertThat(response.getTotalprice()).isPositive();
    assertThat(response.getBookingdates().getCheckout())
        .isAfterOrEqualTo(response.getBookingdates().getCheckin());
}
```

### 20.12 Booking API — Test Data Model

```json
{
  "bookingid": 1,
  "booking": {
    "firstname": "John",
    "lastname": "Smith",
    "totalprice": 250,
    "depositpaid": true,
    "bookingdates": {
      "checkin": "2026-01-01",
      "checkout": "2026-01-10"
    },
    "additionalneeds": "Breakfast"
  }
}
```

---

## Appendix A: Test Environment Configuration

| Component | Version | Port | Notes |
|-----------|:-------:|:----:|-------|
| Selenium Server | 4.30.0 | 4444 | Grid Hub |
| ChromeDriver | Latest | — | WebDriverManager managed |
| GeckoDriver | Latest | — | WebDriverManager managed |
| JDK | 17 | — | LTS |
| Maven | 3.9+ | — | Build tool |
| TestNG | 7.11.0 | — | Test framework |
| Extent Reports | 5.1.2 | — | Reporting |

## Appendix B: Glossary

| Term | Definition |
|------|------------|
| **CRO** | Conversion Rate Optimization |
| **DXO** | Digital Experience Optimization |
| **A/B Test** | Controlled experiment comparing two or more variants |
| **MVT** | Multivariate Testing – testing multiple variable combinations |
| **SmartStats** | VWO's Bayesian-powered statistical engine |
| **RBAC** | Role-Based Access Control |
| **2FA** | Two-Factor Authentication |
| **WCAG** | Web Content Accessibility Guidelines |
| **WYSIWYG** | What You See Is What You Get (Visual Editor) |

## Appendix C: Defect Severity Examples

| Severity | Examples |
|:--------:|----------|
| **S1 – Blocker** | Login failure for all users; experiment cannot be launched; 2FA completely broken |
| **S2 – Major** | SmartStats showing incorrect confidence score; heatmap not rendering; report export fails |
| **S3 – Minor** | UI misalignment on specific browser; tooltip missing; pagination off by one |
| **S4 – Trivial** | Typo in help text; color slightly off from design spec; cosmetic only |
