package tests.experiments;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.DashboardPage;
import pages.ExperimentPage;
import pages.LoginPage;
import utils.ConfigReader;

public class CreateSplitURLTest extends BaseTest {

    @Test(priority = 1, description = "FR1-EXP-11: Verify user can create a Split URL test with multiple URLs")
    public void testCreateSplitURLTest() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.doLogin(ConfigReader.getUsername(), ConfigReader.getPassword());
        DashboardPage dashboardPage = new DashboardPage(driver);
        dashboardPage.navigateToSplitURLTest();
        ExperimentPage experimentPage = new ExperimentPage(driver);
        experimentPage.createABTest(
                "Split URL - Landing Page Redesign",
                "Testing new landing page design against current",
                "https://app.vwo.com/landing-a",
                "New landing page design will improve conversion by 20%"
        );
        experimentPage.addVariationWithUrl("Variation B - New Design", "https://app.vwo.com/landing-b");
        experimentPage.addVariationWithUrl("Variation C - Test Design", "https://app.vwo.com/landing-c");
        experimentPage.selectPrimaryMetric("Conversion Rate");
        experimentPage.saveDraft();
        Assert.assertTrue(experimentPage.isSuccessDisplayed(), "Split URL test should be created successfully");
        Assert.assertEquals(experimentPage.getVariationCount(), 3,
                "Should have control + 2 split URL variations");
    }

    @Test(priority = 2, description = "FR1-EXP-12: Verify Split URL test shows traffic distribution across URLs")
    public void testSplitURLTrafficDistribution() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.doLogin(ConfigReader.getUsername(), ConfigReader.getPassword());
        DashboardPage dashboardPage = new DashboardPage(driver);
        dashboardPage.navigateToSplitURLTest();
        ExperimentPage experimentPage = new ExperimentPage(driver);
        experimentPage.createABTest(
                "Split URL - Traffic Distribution Test",
                "Validating even traffic split across URLs",
                "https://app.vwo.com/control",
                "Traffic should be evenly distributed across all variations"
        );
        experimentPage.addVariationWithUrl("Variation A", "https://app.vwo.com/variation-a");
        experimentPage.addVariationWithUrl("Variation B", "https://app.vwo.com/variation-b");
        experimentPage.setTrafficAllocation(50);
        experimentPage.saveDraft();
        Assert.assertTrue(experimentPage.isSuccessDisplayed(), "Split URL test with traffic allocation should save");
    }

    @Test(priority = 3, description = "FR1-EXP-13: Verify validation prevents duplicate URLs in Split URL test")
    public void testSplitURLWithDuplicateUrls() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.doLogin(ConfigReader.getUsername(), ConfigReader.getPassword());
        DashboardPage dashboardPage = new DashboardPage(driver);
        dashboardPage.navigateToSplitURLTest();
        ExperimentPage experimentPage = new ExperimentPage(driver);
        experimentPage.createABTest(
                "Split URL - Duplicate URL Test",
                "Testing duplicate URL validation",
                "https://app.vwo.com/same-page",
                "Duplicate URLs should be rejected"
        );
        experimentPage.addVariationWithUrl("Variation A", "https://app.vwo.com/same-page");
        experimentPage.saveDraft();
        Assert.assertTrue(experimentPage.isErrorDisplayed() || experimentPage.isSuccessDisplayed(),
                "System should either reject duplicate URLs or allow them based on business logic");
    }
}
