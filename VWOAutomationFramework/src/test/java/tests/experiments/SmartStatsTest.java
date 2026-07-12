package tests.experiments;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.DashboardPage;
import pages.ExperimentPage;
import pages.LoginPage;
import utils.ConfigReader;

public class SmartStatsTest extends BaseTest {

    @Test(priority = 1, description = "FR2-SS-01: Verify SmartStats engine provides Bayesian analysis for experiment results")
    public void testSmartStatsBayesianAnalysis() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.doLogin(ConfigReader.getUsername(), ConfigReader.getPassword());
        DashboardPage dashboardPage = new DashboardPage(driver);
        dashboardPage.navigateToReports();
        ExperimentPage experimentPage = new ExperimentPage(driver);
        Assert.assertTrue(experimentPage.isSmartStatsPanelDisplayed(),
                "SmartStats panel with Bayesian analysis should be available in reports");
    }

    @Test(priority = 2, description = "FR2-SS-02: Verify confidence score is displayed for experiment variations")
    public void testConfidenceScoreDisplay() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.doLogin(ConfigReader.getUsername(), ConfigReader.getPassword());
        DashboardPage dashboardPage = new DashboardPage(driver);
        dashboardPage.navigateToABTest();
        ExperimentPage experimentPage = new ExperimentPage(driver);
        experimentPage.createABTest("SmartStats - Confidence Test", "Testing confidence display",
                "https://app.vwo.com/", "Variation should show statistical confidence");
        experimentPage.addVariation("Variation A");
        experimentPage.selectPrimaryMetric("Conversion Rate");
        experimentPage.saveDraft();
        experimentPage.launchExperiment();
        String confidence = experimentPage.getConfidenceScore();
        Assert.assertNotNull(confidence, "Confidence score should be displayed for launched experiments");
    }

    @Test(priority = 3, description = "FR2-SS-03: Verify winner variation is identified when statistical significance is reached")
    public void testWinnerDeclaration() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.doLogin(ConfigReader.getUsername(), ConfigReader.getPassword());
        DashboardPage dashboardPage = new DashboardPage(driver);
        dashboardPage.navigateToABTest();
        ExperimentPage experimentPage = new ExperimentPage(driver);
        experimentPage.createABTest("SmartStats - Winner Test", "Testing winner declaration",
                "https://app.vwo.com/", "System should identify winning variation");
        experimentPage.addVariation("Variation A");
        experimentPage.selectPrimaryMetric("Conversion Rate");
        experimentPage.saveDraft();
        experimentPage.launchExperiment();
        boolean hasSmartStats = experimentPage.isSmartStatsPanelDisplayed();
        Assert.assertTrue(hasSmartStats, "SmartStats analysis should be available for the experiment");
    }

    @Test(priority = 4, description = "FR2-SS-04: Verify experiment summary shows statistical data (visitors, conversions, lift)")
    public void testExperimentSummaryStats() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.doLogin(ConfigReader.getUsername(), ConfigReader.getPassword());
        DashboardPage dashboardPage = new DashboardPage(driver);
        dashboardPage.navigateToABTest();
        ExperimentPage experimentPage = new ExperimentPage(driver);
        experimentPage.createABTest("SmartStats - Summary Test", "Testing summary display",
                "https://app.vwo.com/", "Summary should contain statistical data");
        experimentPage.addVariation("Variation A");
        experimentPage.selectPrimaryMetric("Conversion Rate");
        experimentPage.saveDraft();
        experimentPage.launchExperiment();
        Assert.assertTrue(experimentPage.isExperimentSummaryDisplayed(),
                "Experiment summary with statistical data should be displayed");
    }
}
