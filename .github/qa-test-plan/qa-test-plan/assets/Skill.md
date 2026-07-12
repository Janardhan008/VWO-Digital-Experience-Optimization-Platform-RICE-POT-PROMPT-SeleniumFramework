---
name: Playwright_company1
description: Comprehensive Playwright end-to-end testing patterns with Page Object Model, fixtures, and best practices
version: 1.0.0
author: Janardhan
license: MIT
testingTypes: [e2e, visual]
frameworks: [playwright]
languages: [typescript, javascript]
domains: [web]
agents: [commandcode, claude]
---
## Core Principles

1. **Business-Critical Test Coverage**
   - Prioritize automation for high-value business workflows such as authentication, order placement, payments, account management, reporting, and user onboarding. Ensure automated tests validate end-to-end business scenarios that directly impact customers and release quality.

2. **Robust Locator Strategy**
   - Prefer stable and maintainable locators using `data-testid` attributes agreed upon with developers. Use semantic Playwright locators (`getByRole`, `getByLabel`, `getByPlaceholder`) where appropriate, and avoid brittle XPath or CSS selectors tied to UI implementation details.

3. **Reliable Synchronization**
   - Rely on Playwright's built-in auto-waiting capabilities whenever possible. Introduce explicit synchronization only for business events such as asynchronous API responses, background processing, database updates, file generation, or third-party integrations.

4. **Test Independence and Environment Isolation**
   - Design every test to execute independently without relying on execution order. Use isolated browser contexts, reusable fixtures, dedicated test accounts, API-based setup, and automated environment cleanup to ensure consistent execution across local and CI environments.

5. **Maintainable Test Architecture**
   - Implement a scalable framework using the Page Object Model (POM), reusable utility classes, fixtures, helper libraries, and centralized configuration. Keep test specifications concise, readable, and focused on business behavior rather than implementation details.

6. **CI/CD Integration**
   - Ensure tests execute reliably within continuous integration pipelines such as Jenkins, GitHub Actions, Azure DevOps, or GitLab CI. Support parallel execution, retry mechanisms for transient failures, environment-specific configuration, and comprehensive reporting suitable for automated deployments.

10. **Quality, Security, and Compliance**
    - Protect sensitive information by masking credentials, authentication tokens, API keys, and personally identifiable information (PII) from logs and reports. Ensure automation complies with organizational security standards, regulatory requirements, and data privacy policies.

11. **Flakiness Prevention**
    - Continuously identify and eliminate flaky tests by improving synchronization, isolating dependencies, stabilizing test data, and monitoring historical execution trends. Automation should produce consistent and trustworthy results across repeated executions.

12. **Continuous Improvement**
    - Treat the automation framework as a production-grade software project. Regularly refactor code, remove duplication, adopt modern Playwright capabilities, review framework design, and evolve testing practices alongside application development.


## Project Structure

tests/
e2e/
auth/
login.spec.ts
signup.spec.ts
dashboard/
dashboard.spec.ts
checkout/
cart.spec.ts
payment.spec.ts
api/
users.api.spec.ts
orders.api.spec.ts
fixtures/
auth.fixture.ts
db.fixture.ts
pages/
login.page.ts
dashboard.page.ts
base.page.ts
mocks/
products.mock.json
users.mock.json
utils/
test-data.ts
helpers.ts
reports/
html/
json/
screenshots/
traces/
config/
dev.config.ts
qa.config.ts
prod.config.ts
ci/
jenkinsfile
github-actions.yml
playwright.config.ts

# Test Plan – Playwright_company1

## 1. Objective
The objective of this test plan is to validate **end‑to‑end business workflows** (authentication, dashboard, checkout, API) using Playwright automation. The plan ensures:
- Functional correctness across browsers and devices.  
- Stability in CI/CD pipelines.  
- Early detection of defects before production release.  
- Alignment with business priorities and compliance standards.  

---

## 2. Scope
- **In‑scope:**  
  - UI flows (auth, dashboard, checkout).  
  - API endpoints (users, orders).  
  - Mock data validation.  
  - CI/CD pipeline execution.  
- **Out‑of‑scope:**  
  - Performance benchmarking beyond smoke checks.  
  - Third‑party services not under direct control.  

---

## 3. Inclusions
1. Authentication flows (login, signup, forgot password).  
2. Dashboard navigation and widgets.  
3. Checkout process (cart, payment).  
4. API validation (users, orders).  

---

## 4. Test Environments
- **Dev:** Local machines with mock data.  
- **QA:** Dedicated QA environment with seeded DB.  
- **Staging:** Pre‑production mirror with CI/CD integration.  
- **Prod (monitoring only):** Smoke checks only.  
- **Browsers:** Chromium, Firefox, WebKit, Mobile Chrome, Mobile Safari.  
- **Devices:** Desktop, Pixel 5, iPhone 13.  

---

## 5. Defect Reporting Procedure
- Defects logged in **JIRA MCP server**.  
- Attach screenshots, traces, logs, repro steps.  
- Severity levels: Critical, Major, Minor.  
- QA verifies fixes before closure.  

---

## 6. Test Strategy
- **Automation‑first:** Playwright for UI + API.  
- **Fixtures:** Auth state, DB setup.  
- **Mocks:** For external APIs.  
- **Tagging:** `@smoke`, `@regression`, `@critical`.  
- **Parallel execution:** Enabled with isolation.  
- **Reporting:** HTML + JSON + CI pipeline integration.  

---

## 7. Test Schedule
- **Daily:** Smoke tests.  
- **Weekly:** Regression suite.  
- **Release:** Full suite before deployment.  
- **CI/CD:** Triggered on PRs and merges.  

---

## 8. Test Deliverables
- Test cases/specs.  
- Execution reports (HTML, JSON).  
- Screenshots, traces, videos.  
- Defect logs in JIRA.  
- Final test summary report.  

---

## 9. Entry and Exit Criteria
**Entry Criteria:**  
- Stable build deployed.  
- Environment ready.  
- Test data available.  

**Exit Criteria:**  
- All critical tests passed.  
- No open critical defects.  
- Regression suite executed.  

---

## 10. Test Execution
**Entry Criteria:** Build deployed, environment accessible.  
**Exit Criteria:** Results published, defects logged, reports shared.  

---

## 11. Test Closure
**Entry Criteria:** All planned tests executed, defects triaged.  
**Exit Criteria:** Closure report signed off, lessons learned documented.  

---

## 12. Tools
- Playwright (UI + API).  
- JIRA MCP server (defect tracking).  
- Jenkins/GitHub Actions (CI/CD).  
- Atlassian Confluence (documentation).  

---

## 13. Risks and Mitigations
- **Flaky selectors** → Use resilient locators + `data-testid`.  
- **Environment instability** → Mock APIs, seeded DB.  
- **Pipeline delays** → Optimize parallel execution, retries.  
- **Sensitive data leaks** → Mask credentials, avoid logging PII.  

---

## 14. Approvals
- QA Lead  
- Dev Lead  
- Product Owner  

## Page Object Model

Always implement the Page Object Model (POM). Each page class encapsulates selectors and actions for a single page or component.

### Base Page Class

```typescript
import { Page, Locator } from '@playwright/test';

export abstract class BasePage {
  readonly page: Page;

  constructor(page: Page) {
    this.page = page;
  }

  async navigate(path: string): Promise<void> {
    await this.page.goto(path);
  }

  async waitForPageLoad(): Promise<void> {
    await this.page.waitForLoadState('networkidle');
  }

  async getTitle(): Promise<string> {
    return this.page.title();
  }

  async takeScreenshot(name: string): Promise<Buffer> {
    return this.page.screenshot({ path: `screenshots/${name}.png`, fullPage: true });
  }
}
```

### Concrete Page Class

```typescript
import { Page, Locator, expect } from '@playwright/test';
import { BasePage } from './base.page';

export class LoginPage extends BasePage {
  readonly emailInput: Locator;
  readonly passwordInput: Locator;
  readonly submitButton: Locator;
  readonly errorMessage: Locator;
  readonly forgotPasswordLink: Locator;

  constructor(page: Page) {
    super(page);
    this.emailInput = page.getByLabel('Email');
    this.passwordInput = page.getByLabel('Password');
    this.submitButton = page.getByRole('button', { name: 'Sign in' });
    this.errorMessage = page.getByRole('alert');
    this.forgotPasswordLink = page.getByRole('link', { name: 'Forgot password?' });
  }

  async goto(): Promise<void> {
    await this.navigate('/login');
  }

  async login(email: string, password: string): Promise<void> {
    await this.emailInput.fill(email);
    await this.passwordInput.fill(password);
    await this.submitButton.click();
  }

  async expectErrorMessage(message: string): Promise<void> {
    await expect(this.errorMessage).toBeVisible();
    await expect(this.errorMessage).toHaveText(message);
  }
}
```

## Writing Test Specs

### Basic Test Structure

```typescript
import { test, expect } from '@playwright/test';
import { LoginPage } from '../pages/login.page';

test.describe('Login functionality', () => {
  let loginPage: LoginPage;

  test.beforeEach(async ({ page }) => {
    loginPage = new LoginPage(page);
    await loginPage.goto();
  });

  test('should login with valid credentials', async ({ page }) => {
    await loginPage.login('user@example.com', 'SecurePass123!');
    await expect(page).toHaveURL('/dashboard');
    await expect(page.getByRole('heading', { name: 'Welcome' })).toBeVisible();
  });

  test('should show error for invalid credentials', async () => {
    await loginPage.login('user@example.com', 'wrongpassword');
    await loginPage.expectErrorMessage('Invalid email or password');
  });

  test('should navigate to forgot password page', async ({ page }) => {
    await loginPage.forgotPasswordLink.click();
    await expect(page).toHaveURL('/forgot-password');
  });
});
```

## Selectors -- Priority Order

Always choose selectors in this priority order:

1. **`getByRole`** -- Preferred. Matches the accessibility tree.
   ```typescript
   page.getByRole('button', { name: 'Submit' });
   page.getByRole('heading', { level: 1 });
   page.getByRole('link', { name: 'Read more' });
   page.getByRole('textbox', { name: 'Email' });
   ```

2. **`getByLabel`** -- For form inputs associated with labels.
   ```typescript
   page.getByLabel('Email address');
   page.getByLabel('Password');
   ```

3. **`getByPlaceholder`** -- When there is no label.
   ```typescript
   page.getByPlaceholder('Search...');
   ```

4. **`getByText`** -- For non-interactive elements with visible text.
   ```typescript
   page.getByText('Welcome back');
   page.getByText(/total: \$\d+/i);
   ```

5. **`getByTestId`** -- When semantic selectors are not feasible.
   ```typescript
   page.getByTestId('checkout-total');
   ```

6. **CSS/XPath** -- Last resort only. Document why other options failed.
   ```typescript
   // Avoid unless absolutely necessary
   page.locator('.legacy-widget >> nth=0');
   ```

## Assertions

Use Playwright's web-first assertions that auto-retry:

```typescript
// Visibility
await expect(locator).toBeVisible();
await expect(locator).toBeHidden();

// Text content
await expect(locator).toHaveText('Expected text');
await expect(locator).toContainText('partial');
await expect(locator).toHaveText(/regex pattern/);

// Input values
await expect(locator).toHaveValue('expected value');
await expect(locator).toBeChecked();
await expect(locator).toBeDisabled();

// Page-level
await expect(page).toHaveURL('/expected-path');
await expect(page).toHaveURL(/\/users\/\d+/);
await expect(page).toHaveTitle('Page Title');

// Count
await expect(page.getByRole('listitem')).toHaveCount(5);

// CSS
await expect(locator).toHaveCSS('color', 'rgb(255, 0, 0)');
await expect(locator).toHaveClass(/active/);

// Screenshot comparison
await expect(page).toHaveScreenshot('homepage.png');
await expect(locator).toHaveScreenshot('button-hover.png');
```

## Fixtures

Use custom fixtures to share setup logic and authenticated state:

```typescript
import { test as base, Page } from '@playwright/test';
import { LoginPage } from '../pages/login.page';
import { DashboardPage } from '../pages/dashboard.page';

type MyFixtures = {
  loginPage: LoginPage;
  dashboardPage: DashboardPage;
  authenticatedPage: Page;
};

export const test = base.extend<MyFixtures>({
  loginPage: async ({ page }, use) => {
    const loginPage = new LoginPage(page);
    await loginPage.goto();
    await use(loginPage);
  },

  dashboardPage: async ({ page }, use) => {
    await use(new DashboardPage(page));
  },

  authenticatedPage: async ({ browser }, use) => {
    const context = await browser.newContext({
      storageState: 'playwright/.auth/user.json',
    });
    const page = await context.newPage();
    await use(page);
    await context.close();
  },
});

export { expect } from '@playwright/test';
```

### Authentication State Reuse

```typescript
// auth.setup.ts -- run once to store auth state
import { test as setup, expect } from '@playwright/test';

setup('authenticate', async ({ page }) => {
  await page.goto('/login');
  await page.getByLabel('Email').fill('admin@example.com');
  await page.getByLabel('Password').fill('AdminPass123!');
  await page.getByRole('button', { name: 'Sign in' }).click();
  await expect(page).toHaveURL('/dashboard');
  await page.context().storageState({ path: 'playwright/.auth/user.json' });
});
```

## Configuration Best Practices

```typescript
import { defineConfig, devices } from '@playwright/test';

export default defineConfig({
  testDir: './tests/e2e',
  fullyParallel: true,
  forbidOnly: !!process.env.CI,
  retries: process.env.CI ? 2 : 0,
  workers: process.env.CI ? 1 : undefined,
  reporter: [
    ['html', { open: 'never' }],
    ['json', { outputFile: 'test-results/results.json' }],
    process.env.CI ? ['github'] : ['list'],
  ],
  use: {
    baseURL: process.env.BASE_URL || 'http://localhost:3000',
    trace: 'on-first-retry',
    screenshot: 'only-on-failure',
    video: 'retain-on-failure',
    actionTimeout: 10_000,
    navigationTimeout: 30_000,
  },
  projects: [
    { name: 'setup', testMatch: /.*\.setup\.ts/ },
    {
      name: 'chromium',
      use: { ...devices['Desktop Chrome'] },
      dependencies: ['setup'],
    },
    {
      name: 'firefox',
      use: { ...devices['Desktop Firefox'] },
      dependencies: ['setup'],
    },
    {
      name: 'webkit',
      use: { ...devices['Desktop Safari'] },
      dependencies: ['setup'],
    },
    {
      name: 'mobile-chrome',
      use: { ...devices['Pixel 5'] },
      dependencies: ['setup'],
    },
    {
      name: 'mobile-safari',
      use: { ...devices['iPhone 13'] },
      dependencies: ['setup'],
    },
  ],
  webServer: {
    command: 'npm run dev',
    url: 'http://localhost:3000',
    reuseExistingServer: !process.env.CI,
    timeout: 120_000,
  },
});
```

## Handling Common Scenarios

### Navigation and Routing

```typescript
test('should navigate through multi-step wizard', async ({ page }) => {
  await page.goto('/wizard');

  // Step 1
  await page.getByLabel('Full name').fill('Jane Doe');
  await page.getByRole('button', { name: 'Next' }).click();

  // Step 2
  await expect(page).toHaveURL('/wizard/step-2');
  await page.getByLabel('Email').fill('jane@example.com');
  await page.getByRole('button', { name: 'Next' }).click();

  // Step 3 -- confirmation
  await expect(page).toHaveURL('/wizard/step-3');
  await expect(page.getByText('Jane Doe')).toBeVisible();
  await expect(page.getByText('jane@example.com')).toBeVisible();
});
```

### Handling Dialogs

```typescript
test('should handle confirmation dialog', async ({ page }) => {
  page.on('dialog', async (dialog) => {
    expect(dialog.type()).toBe('confirm');
    expect(dialog.message()).toBe('Are you sure you want to delete?');
    await dialog.accept();
  });

  await page.getByRole('button', { name: 'Delete' }).click();
  await expect(page.getByText('Item deleted')).toBeVisible();
});
```

### File Upload

```typescript
test('should upload a file', async ({ page }) => {
  const fileInput = page.getByLabel('Upload document');
  await fileInput.setInputFiles('test-data/sample.pdf');
  await expect(page.getByText('sample.pdf')).toBeVisible();
  await page.getByRole('button', { name: 'Submit' }).click();
  await expect(page.getByText('Upload successful')).toBeVisible();
});
```

### Iframe Handling

```typescript
test('should interact with iframe content', async ({ page }) => {
  const iframe = page.frameLocator('#payment-iframe');
  await iframe.getByLabel('Card number').fill('4111111111111111');
  await iframe.getByLabel('Expiry').fill('12/25');
  await iframe.getByLabel('CVC').fill('123');
});
```

### Network Interception

```typescript
test('should mock API response', async ({ page }) => {
  await page.route('**/api/products', async (route) => {
    await route.fulfill({
      status: 200,
      contentType: 'application/json',
      body: JSON.stringify([
        { id: 1, name: 'Mocked Product', price: 9.99 },
      ]),
    });
  });

  await page.goto('/products');
  await expect(page.getByText('Mocked Product')).toBeVisible();
});

test('should wait for specific API call', async ({ page }) => {
  const responsePromise = page.waitForResponse('**/api/submit');
  await page.getByRole('button', { name: 'Submit' }).click();
  const response = await responsePromise;
  expect(response.status()).toBe(200);
});
```

### Handling Dropdowns and Select Elements

```typescript
// Native select
await page.getByLabel('Country').selectOption('US');
await page.getByLabel('Country').selectOption({ label: 'United States' });

// Custom dropdown
await page.getByRole('combobox', { name: 'Country' }).click();
await page.getByRole('option', { name: 'United States' }).click();
```
## Best Practices

1. **Avoid Static Waits**
   - Never use `page.waitForTimeout()` to synchronize tests. Prefer Playwright's built-in auto-waiting, assertions, network events, or application-specific conditions to eliminate flaky execution.

2. **Design Tests Around Business Scenarios**
   - Keep each test focused on a single business workflow with one clear responsibility. Group related scenarios using `test.describe()` to improve readability and maintainability.

3. **Minimize Test Setup**
   - Keep `beforeEach()` lightweight and only perform common initialization. Use fixtures, API setup, or reusable helper methods for complex test preparation instead of repeating UI actions.

4. **Implement a Consistent Tagging Strategy**
   - Categorize tests using standardized tags such as `@smoke`, `@sanity`, `@regression`, `@critical`, `@api`, and `@e2e` to support selective execution across different CI/CD stages.

   ```typescript
   test('Checkout workflow @smoke @critical', async ({ page }) => {
     // Test implementation
   });
   ```

5. **Use Assertions Strategically**
   - Use hard assertions for critical validations that must stop execution immediately. Apply soft assertions only when multiple independent validations should execute before reporting failures.

   ```typescript
   await expect.soft(orderSummary).toBeVisible();
   await expect.soft(orderTotal).toContainText('$250');
   ```

6. **Adopt Data-Driven Testing**
   - Avoid duplicating test logic. Execute the same business scenario against multiple datasets using parameterized tests, reusable fixtures, or external test data sources.

   ```typescript
   const users = [
     { role: 'Admin', canDelete: true },
     { role: 'Viewer', canDelete: false }
   ];

   for (const user of users) {
     test(`${user.role} permissions`, async ({ page }) => {
       // Test implementation
     });
   }
   ```

7. **Centralize Configuration**
   - Manage timeouts, retries, browser settings, base URLs, authentication, and environment-specific values through `playwright.config.ts` and environment variables. Avoid hardcoding configuration inside test files.

8. **Capture Actionable Failure Evidence**
   - Configure traces, screenshots, videos, browser console logs, and network logs to be collected automatically on failure, enabling faster root cause analysis without requiring test reruns.

9. **Optimize Parallel Execution**
   - Execute tests in parallel whenever possible while ensuring complete test isolation. Avoid shared state, shared test accounts, or execution-order dependencies that reduce reliability.

10. **Automate Test Data Lifecycle**
    - Create, manage, and clean test data using APIs, database scripts, or fixtures. Every test should leave the environment in a clean and reusable state to ensure repeatable execution.

11. **Use Stable Locator Strategies**
    - Prefer `data-testid` attributes for long-term stability. Use semantic Playwright locators such as `getByRole()` and `getByLabel()` before resorting to CSS selectors or XPath.

12. **Keep Page Objects Focused**
    - Encapsulate only UI interactions within Page Objects. Store assertions, business logic, and test validation inside test specifications or dedicated helper classes to maintain clear separation of responsibilities.

13. **Treat Automation as Production Code**
    - Follow clean coding principles, meaningful naming conventions, code reviews, linting, formatting, and continuous refactoring. Automation code should be held to the same quality standards as application code.

14. **Design for CI/CD**
    - Ensure the framework supports parallel execution, retries for transient failures, environment-specific execution, headless mode, reporting, and integration with Jenkins, GitHub Actions, Azure DevOps, or GitLab CI.

15. **Continuously Monitor Test Health**
    - Regularly review flaky tests, execution duration, failure trends, and coverage metrics. Remove obsolete tests, optimize slow scenarios, and continuously improve framework reliability as the application evolves.

## Anti-Patterns to Avoid

1. **Using Static Waits**
   - Avoid `page.waitForTimeout()` or arbitrary sleep statements. Static delays increase execution time and introduce flaky tests. Always synchronize using Playwright's auto-waiting, assertions, network events, or application-specific conditions.

2. **Creating Interdependent Tests**
   - Every test must execute independently without relying on the outcome, execution order, or shared state of another test. Tests should remain reliable regardless of parallel or isolated execution.

3. **Coupling Tests to UI Implementation**
   - Validate user behavior and business outcomes rather than HTML structure, CSS classes, or frontend implementation details. UI refactoring should not require widespread test changes.

4. **Using Fragile Locators**
   - Avoid XPath chains, positional selectors (`nth-child`), dynamically generated IDs, or deeply nested CSS selectors. Prefer stable `data-testid` attributes and semantic Playwright locators (`getByRole`, `getByLabel`, `getByTestId`).

5. **Building Large, Monolithic Test Suites**
   - Avoid oversized test files covering multiple business domains. Organize tests by feature, module, or business capability to improve maintainability and execution efficiency.

6. **Mixing Business Logic with Page Objects**
   - Keep Page Objects responsible only for UI interactions. Business validations, assertions, and workflow orchestration should remain within test specifications or dedicated service/helper layers.

7. **Hardcoding Environment Configuration**
   - Never hardcode URLs, credentials, API endpoints, browser settings, or environment-specific values. Manage configuration centrally using environment variables and `playwright.config.ts`.

8. **Ignoring Failure Diagnostics**
   - Do not leave failures without sufficient context. Capture traces, screenshots, videos, browser console logs, network activity, and meaningful assertion messages to simplify troubleshooting.

9. **Testing External Dependencies Directly**
   - Avoid relying on unstable third-party systems such as payment gateways, email providers, or external APIs during routine automation. Mock or stub external integrations whenever appropriate to improve reliability.

10. **Poor Test Data Management**
    - Avoid reusing shared test accounts or leaving residual test data in the environment. Create isolated test data through APIs, fixtures, or database utilities, and ensure proper cleanup after execution.

11. **Ignoring Flaky Test Indicators**
    - Never mask unstable tests with excessive retries or disabled assertions. Investigate synchronization issues, unstable environments, shared dependencies, or application defects to eliminate flakiness at its source.

12. **Treating Automation as Disposable Code**
    - Avoid duplicated logic, inconsistent naming conventions, poor project structure, and lack of code reviews. Automation frameworks should follow the same engineering standards, design principles, and quality expectations as production software.

## Debugging & Troubleshooting Best Practices

1. **Reproduce Failures Locally**
   - Re-run failed tests in headed mode or Playwright UI Mode to observe application behavior and verify whether the failure is reproducible outside the CI environment.

   ```bash
   npx playwright test --headed
   npx playwright test --ui
   ```

2. **Debug Individual Test Scenarios**
   - Isolate a failing test and execute it using Playwright's debugger to inspect execution flow, breakpoints, locator resolution, and application state.

   ```bash
   npx playwright test tests/login.spec.ts --debug
   ```

3. **Leverage Playwright Inspector**
   - Use `page.pause()` during development to launch Playwright Inspector for interactive debugging, locator validation, and step-by-step execution.

   ```typescript
   await page.pause();
   ```

4. **Analyze Execution Traces**
   - Always review Playwright Trace Viewer for failed executions. Inspect network requests, DOM snapshots, console logs, screenshots, and action timelines before modifying test code.

   ```bash
   npx playwright show-trace test-results/trace.zip
   ```

5. **Capture Diagnostic Artifacts**
   - Configure the framework to automatically collect traces, screenshots, videos, browser console logs, and network logs for failed tests to support rapid root cause analysis.

6. **Validate Locator Stability**
   - When a locator fails, verify its uniqueness using Playwright Inspector or Codegen. Prefer resilient locators such as `getByRole()`, `getByLabel()`, or `getByTestId()` instead of fragile CSS or XPath selectors.

7. **Debug Network and API Interactions**
   - Inspect failed API requests, response payloads, authentication tokens, and network timing. Confirm backend services return expected responses before investigating UI failures.

8. **Verify Test Data and Environment State**
   - Confirm prerequisite test data, user permissions, feature flags, environment configuration, and application state before assuming an automation defect.

9. **Isolate Failures During Development**
   - Use `test.only()` temporarily to focus on a single failing scenario. Ensure all temporary debugging statements are removed before committing code.

   ```typescript
   test.only('User login', async ({ page }) => {
       // Test implementation
   });
   ```

10. **Use Code Generation as a Starting Point**
    - Generate initial locators and interaction sequences using Playwright Codegen, then refactor the generated code to follow framework standards and reusable Page Object patterns.

    ```bash
    npx playwright codegen https://example.com
    ```

11. **Correlate CI and Local Failures**
    - Compare local execution with CI pipeline logs, browser versions, environment variables, feature flags, and execution artifacts to identify environment-specific issues.

12. **Investigate Root Causes, Not Symptoms**
    - Avoid fixing failures by adding arbitrary waits or retries. Identify whether the issue originates from synchronization, application defects, unstable test data, infrastructure, or incorrect test logic before implementing a solution.