# VWO Automation Framework

Enterprise-grade Selenium automation framework for VWO (Visual Website Optimizer) Digital Experience Optimization Platform.

## Tech Stack

- **Language**: Java 17
- **Build Tool**: Maven
- **Test Framework**: TestNG
- **Automation**: Selenium 4 WebDriver
- **Driver Management**: WebDriverManager
- **Reporting**: Extent Reports 5
- **Data Driven**: Apache POI
- **Logging**: Log4j2
- **Assertions**: AssertJ + TestNG Assertions

## Framework Structure

```
VWOAutomationFramework/
├── pom.xml
├── testng.xml
├── .gitignore
├── resources/
│   ├── config.properties          # Environment configuration
│   ├── log4j2.xml                 # Logging configuration
│   └── testdata/                  # Excel test data files
├── src/
│   ├── main/java/
│   │   ├── base/                  # BaseTest, BasePage
│   │   ├── pages/                 # Page Object classes
│   │   ├── utils/                 # ConfigReader, ExcelReader, ScreenshotUtil, TestDataProvider
│   │   ├── listeners/             # TestListener (Extent Reports)
│   │   ├── constants/             # FrameworkConstants
│   │   └── enums/                 # ExperimentType, ExperimentStatus, AudienceType, InsightType
│   └── test/java/tests/
│       ├── login/                 # Authentication flows
│       ├── dashboard/             # Dashboard & navigation
│       ├── experiments/           # A/B, Split URL, MVT, SmartStats, Code Editor, Audience
│       ├── insights/              # Heatmaps, Recordings, Funnels, Surveys
│       ├── personalization/       # Campaign management
│       ├── reports/               # Dashboards & reporting
│       ├── integrations/          # Third-party integrations
│       └── plan/                  # Program management
├── logs/
├── screenshots/
└── reports/
```

## Business Flows Covered

| Module | Test Classes | Scenarios |
|--------|-------------|-----------|
| Authentication | LoginTest | Valid login, invalid credentials, 2FA, forgot password, logout |
| Dashboard | DashboardTest | Dashboard elements, navigation, search, quick start guide |
| A/B Testing | CreateABTestTest | Create, variations, launch, pause, stop, schedule |
| Split URL | CreateSplitURLTest | Multi-URL tests, traffic distribution, duplicate validation |
| Multivariate | CreateMVTestTest | Multi-combination, preview, multi-goal |
| SmartStats | SmartStatsTest | Bayesian analysis, confidence score, winner declaration |
| Code Editor | CodeEditorTest | CSS/JS injection, editor toggle |
| Audience | AudienceTargetingTest | Geo, device, behavior, combined conditions |
| Heatmaps | HeatmapTest | Click, scroll, move, device filtering |
| Recordings | SessionRecordingTest | Playback, filtering, controls |
| Funnels/Surveys | FunnelSurveyTest | Funnel creation, drop-off, surveys |
| Personalization | PersonalizationTest | Segments, campaigns, preview, traffic allocation |
| Reports | ReportsTest | Dashboards, filters, export, scheduling |
| Integrations | IntegrationsTest | Navigation, project selector |
| Plan Management | PlanManagementTest | Program management, notifications |
| Booking API – Health Check | HealthCheckTest | 201 status, response time, consecutive calls, headers, method validation |
| Booking API – Auth | AuthTest | Valid/invalid credentials, token generation, missing headers |
| Booking API – Get All | GetAllBookingTest | List all, filter by name/date, combined filters, empty results |
| Booking API – Get Single | GetSingleBookingTest | Valid ID, non-existent, negative, string ID, field validation |
| Booking API – Full Update | UpdateBookingTest | PUT with/without auth, round-trip, invalid data, extra fields |
| Booking API – Partial Update | PartialUpdateBookingTest | PATCH single/multiple fields, preserve others, null values |
| Booking API – Delete | DeleteBookingTest | Valid delete, double delete, unauth, create-verify-delete cycle |

## How to Run

```bash
# Run all tests
mvn clean test

# Run specific test class
mvn -Dtest=CreateABTestTest test

# Run with specific browser
mvn test -Dbrowser=firefox

# Run in headless mode
mvn test -Dheadless=true

# Run specific testng group
mvn -Dgroups=smoke test
```

## Configuration

Edit `resources/config.properties`:

- `app.url` - VWO application URL
- `browser` - chrome / firefox / edge
- `headless` - true/false
- `remote.execution` - enable Selenium Grid
- `app.username` / `app.password` - Test credentials
