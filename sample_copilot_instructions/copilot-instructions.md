# Copilot Instructions — Advanced Playwright Framework

## Project Overview

This is a Playwright end-to-end testing framework using the Page Object Model (POM) pattern with TypeScript. Tests cover authentication, dashboard, checkout, and API workflows across multiple browsers.

## Code Conventions

### Language & Frameworks
- TypeScript only (strict mode enabled)
- Playwright v1.52+ for UI and API testing
- CommonJS modules (`import`/`export`)

### Architecture — Page Object Model

Each page has a **Page Object class** that encapsulates selectors and actions. Never put assertions or business logic inside Page Objects — those belong in test specs.

```
pages/
  base.page.ts        — abstract base: navigate(), waitForPageLoad(), getTitle(), takeScreenshot()
  login.page.ts       — emailInput, passwordInput, submitButton, login(), expectErrorMessage()
  dashboard.page.ts   — welcomeHeading, logout(), expectWidgetVisible()
  cart.page.ts        — cartItems, checkoutButton, proceedToCheckout()
  payment.page.ts     — cardNumberInput, submitPayment(), expectOrderConfirmation()
```

### Page Object Template

```typescript
import { Page, Locator, expect } from '@playwright/test';
import { BasePage } from './base.page';

export class XPage extends BasePage {
  // Locators declared as readonly properties
  readonly submitButton: Locator;

  constructor(page: Page) {
    super(page);
    // Use semantic Playwright locators in priority order:
    // 1. getByRole  2. getByLabel  3. getByPlaceholder  4. getByText  5. getByTestId  6. CSS/XPath (last resort)
    this.submitButton = page.getByRole('button', { name: 'Submit' });
  }

  async someAction(): Promise<void> {
    await this.submitButton.click();
  }
}
```

### Locator Priority (in order)

1. `getByRole` — matches accessibility tree (`button`, `link`, `heading`, `textbox`, etc.)
2. `getByLabel` — form inputs with `<label>` elements
3. `getByPlaceholder` — when no label exists
4. `getByText` — non-interactive elements with visible text
5. `getByTestId` — when semantic selectors aren't feasible
6. CSS/XPath — absolute last resort; document why

### Test Spec Structure

```typescript
import { test, expect } from '@playwright/test';
import { XPage } from '../../pages/x.page';

test.describe('Feature description', () => {
  let pageObject: XPage;

  test.beforeEach(async ({ page }) => {
    pageObject = new XPage(page);
    await pageObject.goto();
  });

  test('should do something @tag', async ({ page }) => {
    // Arrange → Act → Assert
    await pageObject.someAction();
    await expect(page).toHaveURL('/expected-path');
  });
});
```

### Tags

- `@smoke` — critical path tests run on every PR
- `@regression` — full regression suite run nightly
- `@critical` — blocking tests for release
- `@api` — API-only tests (no browser)

### Fixtures

Custom fixtures live in `tests/fixtures/` for sharing auth state, DB setup, and page instances:

```typescript
import { test as base } from '@playwright/test';

type MyFixtures = { loginPage: LoginPage };

export const test = base.extend<MyFixtures>({
  loginPage: async ({ page }, use) => {
    const loginPage = new LoginPage(page);
    await loginPage.goto();
    await use(loginPage);
  },
});
```

### Assertions

Use Playwright's web-first auto-retrying assertions only:

- `await expect(locator).toBeVisible()`
- `await expect(locator).toHaveText('...')`
- `await expect(page).toHaveURL('/path')`
- `await expect(locator).toHaveCount(N)`
- `await expect(page).toHaveScreenshot('name.png')` — for visual regression
- `await expect.soft()` — for multiple independent validations

### Anti-Patterns to Avoid

- **Never** use `page.waitForTimeout()` — rely on auto-waiting
- **Never** create interdependent tests — each test must run independently
- **Never** hardcode URLs, credentials, or environment config — use env vars and `playwright.config.ts`
- **Never** put assertions inside Page Objects — they belong in test specs
- **Never** use CSS/XPath when a semantic locator is available

### Synchronization

- Rely on Playwright's built-in auto-waiting
- Use `waitForResponse()` for async API calls
- Use `waitForLoadState('networkidle')` only for business events
- No static sleeps anywhere

### Config

Centralized in `playwright.config.ts`:
- 5 projects: chromium, firefox, webkit, mobile-chrome, mobile-safari
- Parallel execution enabled
- Retries: 2 in CI, 0 locally
- Traces on first retry, screenshots on failure, video on failure
- Auth setup via `tests/auth.setup.ts` with storage state

### NPM Scripts

```bash
npm test                 # Full suite headless
npm run test:headed      # With browser UI
npm run test:ui          # Playwright UI Mode
npm run test:smoke       # @smoke tagged only
npm run test:chromium    # Single browser
npm run test:debug       # Debug mode
npm run report           # Open HTML report
npm run trace            # Open trace viewer
```

## Directory Structure Reference

```
AdvancedPlaywrightFramework/
├── pages/           — Page Object classes (base, login, dashboard, cart, payment, signup)
├── tests/
│   ├── e2e/
│   │   ├── auth/       — login.spec.ts, signup.spec.ts
│   │   ├── dashboard/  — dashboard.spec.ts
│   │   └── checkout/   — cart.spec.ts, payment.spec.ts
│   ├── api/            — users.api.spec.ts, orders.api.spec.ts
│   └── fixtures/       — auth.fixture.ts, db.fixture.ts
├── config/          — dev, qa, prod Playwright configs
├── mocks/           — products.mock.json, users.mock.json
├── utils/           — test-data.ts, helpers.ts
├── ci/              — GitHub Actions, Jenkinsfile
└── playwright.config.ts
```

## Code Review Rules

When generating or reviewing code:
1. Every new spec file must use the POM pattern if interacting with UI
2. Every new Page Object must extend `BasePage`
3. All selectors must follow the priority order (getByRole first)
4. All tests must be independent, with fresh browser context per test
5. Every test must have at least one tag
6. No hardcoded test data — use the constants in `utils/test-data.ts`
