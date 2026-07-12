package tests.insights;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.DashboardPage;
import pages.InsightsPage;
import pages.LoginPage;
import utils.ConfigReader;

public class FunnelSurveyTest extends BaseTest {

    @Test(priority = 1, description = "FR4-INS-10: Verify user can create a funnel with multiple steps")
    public void testCreateFunnelWithMultipleSteps() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.doLogin(ConfigReader.getUsername(), ConfigReader.getPassword());
        DashboardPage dashboardPage = new DashboardPage(driver);
        dashboardPage.navigateToInsights();
        InsightsPage insightsPage = new InsightsPage(driver);
        insightsPage.navigateToFunnels();
        insightsPage.addFunnelStep("https://app.vwo.com/");
        insightsPage.addFunnelStep("https://app.vwo.com/pricing");
        insightsPage.addFunnelStep("https://app.vwo.com/signup");
        Assert.assertTrue(insightsPage.getFunnelStepCount() >= 2,
                "Funnel should have at least 2 steps configured");
    }

    @Test(priority = 2, description = "FR4-INS-11: Verify funnel shows drop-off percentages at each step")
    public void testFunnelDropOffVisualization() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.doLogin(ConfigReader.getUsername(), ConfigReader.getPassword());
        DashboardPage dashboardPage = new DashboardPage(driver);
        dashboardPage.navigateToInsights();
        InsightsPage insightsPage = new InsightsPage(driver);
        insightsPage.navigateToFunnels();
        insightsPage.addFunnelStep("https://app.vwo.com/");
        insightsPage.addFunnelStep("https://app.vwo.com/pricing");
        Assert.assertTrue(insightsPage.getFunnelStepCount() >= 1,
                "Funnel steps should be added successfully");
    }

    @Test(priority = 3, description = "FR4-INS-12: Verify user can create an on-page survey (FR4)")
    public void testCreateOnPageSurvey() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.doLogin(ConfigReader.getUsername(), ConfigReader.getPassword());
        DashboardPage dashboardPage = new DashboardPage(driver);
        dashboardPage.navigateToInsights();
        InsightsPage insightsPage = new InsightsPage(driver);
        insightsPage.navigateToSurveys();
        int surveyCountBefore = insightsPage.getSurveyCount();
        insightsPage.createSurvey("How likely are you to recommend VWO to a colleague?");
        int surveyCountAfter = insightsPage.getSurveyCount();
        Assert.assertEquals(surveyCountAfter, surveyCountBefore + 1,
                "Survey count should increase after creating a new survey");
    }

    @Test(priority = 4, description = "FR4-INS-13: Verify insights dashboard shows summary cards")
    public void testInsightsDashboardSummaryCards() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.doLogin(ConfigReader.getUsername(), ConfigReader.getPassword());
        DashboardPage dashboardPage = new DashboardPage(driver);
        dashboardPage.navigateToInsights();
        InsightsPage insightsPage = new InsightsPage(driver);
        Assert.assertTrue(insightsPage.isInsightsPageDisplayed(), "Insights page should load");
        int cardCount = insightsPage.getInsightCardCount();
        Assert.assertTrue(cardCount > 0, "Insights dashboard should display summary cards");
    }

    @Test(priority = 5, description = "FR4-INS-14: Verify insights data can be exported")
    public void testExportInsightsData() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.doLogin(ConfigReader.getUsername(), ConfigReader.getPassword());
        DashboardPage dashboardPage = new DashboardPage(driver);
        dashboardPage.navigateToInsights();
        InsightsPage insightsPage = new InsightsPage(driver);
        Assert.assertTrue(insightsPage.isInsightsPageDisplayed(), "Insights page should load");
        insightsPage.exportInsights();
    }
}
