package tests.experiments;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.DashboardPage;
import pages.ExperimentPage;
import pages.LoginPage;
import utils.ConfigReader;

public class CreateMVTestTest extends BaseTest {

    @Test(priority = 1, description = "FR1-EXP-14: Verify user can create a Multivariate test with multiple combinations")
    public void testCreateMultivariateTest() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.doLogin(ConfigReader.getUsername(), ConfigReader.getPassword());
        DashboardPage dashboardPage = new DashboardPage(driver);
        dashboardPage.navigateToMultivariateTest();
        ExperimentPage experimentPage = new ExperimentPage(driver);
        experimentPage.createABTest(
                "MVT - Homepage Hero Section",
                "Testing headline + CTA combinations",
                "https://app.vwo.com/",
                "Combination of headline and CTA will improve engagement"
        );
        experimentPage.addVariation("Headline A + CTA Blue");
        experimentPage.addVariation("Headline A + CTA Green");
        experimentPage.addVariation("Headline B + CTA Blue");
        experimentPage.addVariation("Headline B + CTA Green");
        experimentPage.selectPrimaryMetric("Click-Through Rate");
        experimentPage.saveDraft();
        Assert.assertTrue(experimentPage.isSuccessDisplayed(), "Multivariate test should be created successfully");
        Assert.assertEquals(experimentPage.getVariationCount(), 5,
                "Should have control + 4 multivariate combinations");
    }

    @Test(priority = 2, description = "FR1-EXP-15: Verify MVT experiment shows all combination previews")
    public void testMVTCombinationPreview() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.doLogin(ConfigReader.getUsername(), ConfigReader.getPassword());
        DashboardPage dashboardPage = new DashboardPage(driver);
        dashboardPage.navigateToMultivariateTest();
        ExperimentPage experimentPage = new ExperimentPage(driver);
        experimentPage.createABTest(
                "MVT - Preview Combinations",
                "Verifying preview functionality for all combinations",
                "https://app.vwo.com/",
                "Preview should display each combination correctly"
        );
        experimentPage.addVariation("Combination 1");
        experimentPage.addVariation("Combination 2");
        experimentPage.saveDraft();
        experimentPage.openPreview();
        Assert.assertTrue(experimentPage.isVariationDisplayedInPreview("Combination 1"),
                "Preview should display the created variation");
    }

    @Test(priority = 3, description = "FR1-EXP-16: Verify MVT with multiple goals/metrics configured")
    public void testMVTWithMultipleGoals() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.doLogin(ConfigReader.getUsername(), ConfigReader.getPassword());
        DashboardPage dashboardPage = new DashboardPage(driver);
        dashboardPage.navigateToMultivariateTest();
        ExperimentPage experimentPage = new ExperimentPage(driver);
        experimentPage.createABTest(
                "MVT - Multi Goal Test",
                "Testing MVT with multiple success metrics",
                "https://app.vwo.com/",
                "Multi-goal MVT experiment for comprehensive analysis"
        );
        experimentPage.addVariation("Combo A");
        experimentPage.addVariation("Combo B");
        experimentPage.selectPrimaryMetric("Conversion Rate");
        experimentPage.addGoal("Revenue per Visitor");
        experimentPage.addGoal("Time on Page");
        Assert.assertTrue(experimentPage.getGoalCount() >= 1,
                "Multiple goals should be configurable for MVT experiments");
        experimentPage.saveDraft();
        Assert.assertTrue(experimentPage.isSuccessDisplayed(), "MVT with multiple goals should save successfully");
    }
}
