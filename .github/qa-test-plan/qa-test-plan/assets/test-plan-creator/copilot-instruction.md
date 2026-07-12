# QA conventions – loaded on every Copilot request

## Stack
Playwright + TypeScript @playwright/test pnpm

## Locators – strict
- Prefer getByRole / getByLabel / getByTestId.
- Never raw XPath or brittle CSS (add a "//" why if unavoidable).

## Waits
- No page.waitForTimeout(). Use auto-wait + expect.poll().

## Test data
- Build with @faker-js/faker. Never hard-code emails or PII.

## Assertions
- Assert user-visible outcomes, not implementation details.
