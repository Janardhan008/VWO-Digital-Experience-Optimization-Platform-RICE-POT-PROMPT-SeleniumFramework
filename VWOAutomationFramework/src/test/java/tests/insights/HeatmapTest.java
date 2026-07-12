package tests.insights;

import base.BaseTest;
import enums.InsightType;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.DashboardPage;
import pages.InsightsPage;
import pages.LoginPage;
import utils.ConfigReader;

public class HeatmapTest extends BaseTest {

    private static final String TEST_PAGE_URL = "https://app.vwo.com/";

    @Test(priority = 1, description = "FR4-INS-01: Verify user can generate click heatmap for a page (FR4)")
    public void testGenerateClickHeatmap() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.doLogin(ConfigReader.getUsername(), ConfigReader.getPassword());
        DashboardPage dashboardPage = new DashboardPage(driver);
        dashboardPage.navigateToInsights();
        InsightsPage insightsPage = new InsightsPage(driver);
        Assert.assertTrue(insightsPage.isInsightsPageDisplayed(), "Insights page should be displayed");
        insightsPage.navigateToHeatmap();
        insightsPage.generateHeatmap(TEST_PAGE_URL, InsightType.CLICK_HEATMAP);
        Assert.assertTrue(insightsPage.isHeatmapDisplayed(), "Click heatmap should be generated and displayed");
    }

    @Test(priority = 2, description = "FR4-INS-02: Verify user can generate scroll heatmap for a page")
    public void testGenerateScrollHeatmap() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.doLogin(ConfigReader.getUsername(), ConfigReader.getPassword());
        DashboardPage dashboardPage = new DashboardPage(driver);
        dashboardPage.navigateToInsights();
        InsightsPage insightsPage = new InsightsPage(driver);
        insightsPage.navigateToHeatmap();
        insightsPage.generateHeatmap(TEST_PAGE_URL, InsightType.SCROLL_HEATMAP);
        Assert.assertTrue(insightsPage.isScrollHeatmapOverlayDisplayed(),
                "Scroll heatmap overlay should be displayed");
    }

    @Test(priority = 3, description = "FR4-INS-03: Verify heatmap can be filtered by device type")
    public void testHeatmapDeviceFiltering() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.doLogin(ConfigReader.getUsername(), ConfigReader.getPassword());
        DashboardPage dashboardPage = new DashboardPage(driver);
        dashboardPage.navigateToInsights();
        InsightsPage insightsPage = new InsightsPage(driver);
        insightsPage.navigateToHeatmap();
        insightsPage.generateHeatmap(TEST_PAGE_URL, InsightType.CLICK_HEATMAP);
        insightsPage.filterByDevice("Mobile");
        Assert.assertTrue(insightsPage.isHeatmapDisplayed(),
                "Heatmap should refresh based on selected device filter");
    }

    @Test(priority = 4, description = "FR4-INS-04: Verify heatmap generation fails gracefully with invalid URL")
    public void testHeatmapWithInvalidUrl() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.doLogin(ConfigReader.getUsername(), ConfigReader.getPassword());
        DashboardPage dashboardPage = new DashboardPage(driver);
        dashboardPage.navigateToInsights();
        InsightsPage insightsPage = new InsightsPage(driver);
        insightsPage.navigateToHeatmap();
        insightsPage.generateHeatmap("https://invalid-page-12345.com", InsightType.CLICK_HEATMAP);
        Assert.assertFalse(insightsPage.isHeatmapDisplayed(),
                "Heatmap should not be generated for inaccessible URL");
    }

    @Test(priority = 5, description = "FR4-INS-05: Verify move heatmap can be generated alongside click heatmap")
    public void testMoveHeatmapGeneration() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.doLogin(ConfigReader.getUsername(), ConfigReader.getPassword());
        DashboardPage dashboardPage = new DashboardPage(driver);
        dashboardPage.navigateToInsights();
        InsightsPage insightsPage = new InsightsPage(driver);
        insightsPage.navigateToHeatmap();
        insightsPage.generateHeatmap(TEST_PAGE_URL, InsightType.MOVE_HEATMAP);
        Assert.assertTrue(insightsPage.isHeatmapDisplayed(),
                "Move heatmap should be generated successfully");
    }
}
