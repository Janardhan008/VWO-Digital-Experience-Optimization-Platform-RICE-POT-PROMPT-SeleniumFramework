# VWO-Digital-Experience-Optimization-Platform-RICE-POT-PROMPT-SeleniumFramework
This is an enterprise-grade Selenium automation framework using Java, Maven, and TestNG that adheres to industry best practices.


Role:  
You are a QA Automation Tester with 5 years of experience, specializing in IT and Digital Experience Optimization projects.You are tasked with building an enterprise-grade Selenium automation framework using Java, Maven, and TestNG that adheres to industry best practices.

Instruction:

Generate a complete Selenium with Java automation script that follows enterprise-level standards.

Automate and validate the login functionality of https://login.salesforce.com/?locale=in.

Ensure UI validation with both valid and invalid test cases.

[Critical] Apply TestNG annotations (@Test, @BeforeTest, @AfterTest, etc.) with proper setup and teardown logic.

[Critical] Implement robust exception handling in both the Page Object Model (POM) and test scripts using structured try–catch blocks or explicit exception signatures.

[Mandatory] Use Page Object Model with PageFactory, including @FindBy, constructor initialization, and reusable action methods.

[Mandatory] Use only XPath locators (no CSS, ID, or name).

[Output] Provide only runnable code — no explanations, comments, dependencies, or extra text.

[Don’t] Avoid CSS selectors, ID, name locators, Thread.sleep(), and other bad practices.

[Generate] Deliver two scripts: one for valid login and one for invalid login.

[Do NOT use] Thread.sleep() — rely on WebDriverWait or implicit waits.

Maintain consistent structure, readability, and modularity across all scripts.


C — Context
You are creating Functional Test Cases for the A/B test with multiple variations and include Test Case ID, Preconditions, Steps, 
Expected Results, Priority, Severity, Automation Feasibility.


E — Example 
Example structure for Test Script & Corresponding PageFactory Method:

Test Script

@Test
public void verifyCreateABTest() {
    LoginPage login = new LoginPage(driver);
    login.login("admin@test.com", "password");

    ExperimentPage experiment = new ExperimentPage(driver);
    Assert.assertTrue(experiment.createABTest("Homepage Test"));
}


Corresponding PageFactory Method

@FindBy(id = "createBtn")
WebElement createButton;

@FindBy(id = "testName")
WebElement testName;

@FindBy(id = "saveBtn")
WebElement saveButton;

public boolean createABTest(String name) {
    createButton.click();
    testName.sendKeys(name);
    saveButton.click();
    return true;
}

P — PARAMETERS
Requirement, Acceptance Criteria, Preconditions, Test Data, Test Steps, Expected Result, Positive, Negative, Boundary, Validation, UI, Functional, Integration, API, Database, Security, Performance, Compatibility, Accessibility, Priority, Severity, and Automation Feasibility.


T — Tone 
Validate input fields, business rules, mandatory fields, data validation, UI behavior, functional flow, user permissions, error handling, integrations, API responses, database persistence, security, performance, compatibility, and expected business outcomes.

Please make sure you consider each detail in depth and create step by step process. Before writing any code, test cases, or framework components, perform a complete requirement analysis and share your implementation strategy. During implementation, proceed one step at a time. For every step, explain what you're building, why it's needed, the QA concepts or best practices involved, and how it fits into the overall framework. After completing each step, summarize the work, verify that it meets the requirements, and wait for my confirmation before continuing.

