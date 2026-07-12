package tests.experiments;

import base.BaseTest;
import enums.AudienceType;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.DashboardPage;
import pages.ExperimentPage;
import pages.LoginPage;
import utils.ConfigReader;

public class CreateABTestTest extends BaseTest {

    private static final String TEST_NAME = "A/B Test - Homepage CTA Button";
    private static final String TEST_DESCRIPTION = "Testing CTA button color variation on homepage";
    private static final String TEST_URL = "https://app.vwo.com/";
    private static final String TEST_HYPOTHESIS = "Changing CTA button from blue to green will increase click-through rate by 15%";

    @Test(priority = 1, description = "FR1-EXP-01: Verify user can create a new A/B test with valid data")
    public void testCreateABTestWithValidData() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.doLogin(ConfigReader.getUsername(), ConfigReader.getPassword());
        DashboardPage dashboardPage = new DashboardPage(driver);
        dashboardPage.navigateToABTest();
        ExperimentPage experimentPage = new ExperimentPage(driver);
        experimentPage.createABTest(TEST_NAME, TEST_DESCRIPTION, TEST_URL, TEST_HYPOTHESIS);
        experimentPage.addVariation("Green CTA Button");
        experimentPage.selectPrimaryMetric("Click-Through Rate");
        experimentPage.addGoal("Newsletter Sign-ups");
        experimentPage.saveDraft();
        Assert.assertTrue(experimentPage.isSuccessDisplayed(), "A/B test should be saved as draft successfully");
    }

    @Test(priority = 2, description = "FR1-EXP-02: Verify A/B test variations can be added and configured")
    public void testAddMultipleVariationsToABTest() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.doLogin(ConfigReader.getUsername(), ConfigReader.getPassword());
        DashboardPage dashboardPage = new DashboardPage(driver);
        dashboardPage.navigateToABTest();
        ExperimentPage experimentPage = new ExperimentPage(driver);
        experimentPage.createABTest(TEST_NAME + " - Multi Variation", TEST_DESCRIPTION, TEST_URL, TEST_HYPOTHESIS);
        experimentPage.addVariation("Variation A - Green Button");
        experimentPage.addVariation("Variation B - Red Button");
        experimentPage.addVariation("Variation C - Yellow Button");
        Assert.assertEquals(experimentPage.getVariationCount(), 4,
                "Should have control + 3 variations = 4 total variations");
    }

    @Test(priority = 3, description = "FR1-EXP-03: Verify experiment can be launched and status changes to Running")
    public void testLaunchABTest() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.doLogin(ConfigReader.getUsername(), ConfigReader.getPassword());
        DashboardPage dashboardPage = new DashboardPage(driver);
        dashboardPage.navigateToABTest();
        ExperimentPage experimentPage = new ExperimentPage(driver);
        experimentPage.createABTest(TEST_NAME + " - Launch Test", TEST_DESCRIPTION, TEST_URL, TEST_HYPOTHESIS);
        experimentPage.addVariation("New Variation");
        experimentPage.selectPrimaryMetric("Conversion Rate");
        experimentPage.saveDraft();
        experimentPage.launchExperiment();
        String status = experimentPage.getExperimentStatus();
        Assert.assertEquals(status.toLowerCase(), "running", "Experiment status should be 'Running' after launch");
    }

    @Test(priority = 4, description = "FR1-EXP-04: Verify experiment can be paused after launch")
    public void testPauseRunningExperiment() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.doLogin(ConfigReader.getUsername(), ConfigReader.getPassword());
        DashboardPage dashboardPage = new DashboardPage(driver);
        dashboardPage.navigateToABTest();
        ExperimentPage experimentPage = new ExperimentPage(driver);
        experimentPage.createABTest(TEST_NAME + " - Pause Test", TEST_DESCRIPTION, TEST_URL, TEST_HYPOTHESIS);
        experimentPage.addVariation("Variation A");
        experimentPage.selectPrimaryMetric("Conversion Rate");
        experimentPage.saveDraft();
        experimentPage.launchExperiment();
        experimentPage.pauseExperiment();
        String status = experimentPage.getExperimentStatus();
        Assert.assertEquals(status.toLowerCase(), "paused", "Experiment status should be 'Paused' after pausing");
    }

    @Test(priority = 5, description = "FR1-EXP-05: Verify experiment can be stopped and winner declared")
    public void testStopExperimentAndDeclareWinner() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.doLogin(ConfigReader.getUsername(), ConfigReader.getPassword());
        DashboardPage dashboardPage = new DashboardPage(driver);
        dashboardPage.navigateToABTest();
        ExperimentPage experimentPage = new ExperimentPage(driver);
        experimentPage.createABTest(TEST_NAME + " - Stop Test", TEST_DESCRIPTION, TEST_URL, TEST_HYPOTHESIS);
        experimentPage.addVariation("Variation A");
        experimentPage.selectPrimaryMetric("Conversion Rate");
        experimentPage.saveDraft();
        experimentPage.launchExperiment();
        experimentPage.stopExperiment();
        String status = experimentPage.getExperimentStatus();
        Assert.assertEquals(status.toLowerCase(), "stopped", "Experiment status should be 'Stopped'");
    }

    @Test(priority = 6, description = "FR1-EXP-06: Verify user can set audience targeting for experiment (FR5)")
    public void testAudienceTargetingForExperiment() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.doLogin(ConfigReader.getUsername(), ConfigReader.getPassword());
        DashboardPage dashboardPage = new DashboardPage(driver);
        dashboardPage.navigateToABTest();
        ExperimentPage experimentPage = new ExperimentPage(driver);
        experimentPage.createABTest(TEST_NAME + " - Audience Targeting", TEST_DESCRIPTION, TEST_URL, TEST_HYPOTHESIS);
        experimentPage.addVariation("Variation A");
        experimentPage.addAudienceCondition(AudienceType.GEOGRAPHY, "United States");
        experimentPage.addAudienceCondition(AudienceType.DEVICE, "Desktop");
        experimentPage.saveDraft();
        Assert.assertTrue(experimentPage.isSuccessDisplayed(), "Experiment with audience targeting should save successfully");
    }

    @Test(priority = 7, description = "FR1-EXP-07: Verify Visual Editor mode can be used for variation editing (FR3)")
    public void testVisualEditorForVariations() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.doLogin(ConfigReader.getUsername(), ConfigReader.getPassword());
        DashboardPage dashboardPage = new DashboardPage(driver);
        dashboardPage.navigateToABTest();
        ExperimentPage experimentPage = new ExperimentPage(driver);
        experimentPage.createABTest(TEST_NAME + " - Visual Editor", TEST_DESCRIPTION, TEST_URL, TEST_HYPOTHESIS);
        experimentPage.addVariation("Edited Variation");
        experimentPage.useVisualEditor();
        experimentPage.saveDraft();
        Assert.assertTrue(experimentPage.isSuccessDisplayed(), "Visual editor changes should be saved successfully");
    }

    @Test(priority = 8, description = "FR1-EXP-08: Verify user can schedule experiment start/end dates")
    public void testExperimentScheduling() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.doLogin(ConfigReader.getUsername(), ConfigReader.getPassword());
        DashboardPage dashboardPage = new DashboardPage(driver);
        dashboardPage.navigateToABTest();
        ExperimentPage experimentPage = new ExperimentPage(driver);
        experimentPage.createABTest(TEST_NAME + " - Scheduled", TEST_DESCRIPTION, TEST_URL, TEST_HYPOTHESIS);
        experimentPage.addVariation("Variation A");
        experimentPage.selectPrimaryMetric("Conversion Rate");
        experimentPage.scheduleExperiment("2026-01-15", "2026-02-15");
        experimentPage.saveDraft();
        Assert.assertTrue(experimentPage.isSuccessDisplayed(), "Scheduled experiment should save successfully");
    }

    @Test(priority = 9, description = "FR1-EXP-09: Verify validation prevents creating experiment without name")
    public void testCreateExperimentWithoutName() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.doLogin(ConfigReader.getUsername(), ConfigReader.getPassword());
        DashboardPage dashboardPage = new DashboardPage(driver);
        dashboardPage.navigateToABTest();
        ExperimentPage experimentPage = new ExperimentPage(driver);
        experimentPage.createABTest("", TEST_DESCRIPTION, TEST_URL, TEST_HYPOTHESIS);
        experimentPage.saveDraft();
        Assert.assertTrue(experimentPage.isErrorDisplayed(), "Error should be shown when experiment name is empty");
    }

    @Test(priority = 10, description = "FR1-EXP-10: Verify SmartStats panel is available for launched experiments (FR2)")
    public void testSmartStatsPanelForExperiment() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.doLogin(ConfigReader.getUsername(), ConfigReader.getPassword());
        DashboardPage dashboardPage = new DashboardPage(driver);
        dashboardPage.navigateToABTest();
        ExperimentPage experimentPage = new ExperimentPage(driver);
        experimentPage.createABTest(TEST_NAME + " - SmartStats", TEST_DESCRIPTION, TEST_URL, TEST_HYPOTHESIS);
        experimentPage.addVariation("Variation A");
        experimentPage.selectPrimaryMetric("Conversion Rate");
        experimentPage.saveDraft();
        experimentPage.launchExperiment();
        Assert.assertTrue(experimentPage.isSmartStatsPanelDisplayed(),
                "SmartStats panel should be displayed for launched experiments");
    }
}
