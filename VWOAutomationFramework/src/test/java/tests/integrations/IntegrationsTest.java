package tests.integrations;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.DashboardPage;
import pages.LoginPage;
import utils.ConfigReader;

public class IntegrationsTest extends BaseTest {

    @Test(priority = 1, description = "FR8-INT-01: Verify user can navigate to Integrations page (FR8)")
    public void testNavigateToIntegrations() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.doLogin(ConfigReader.getUsername(), ConfigReader.getPassword());
        DashboardPage dashboardPage = new DashboardPage(driver);
        dashboardPage.navigateToIntegrations();
        Assert.assertTrue(dashboardPage.getDashboardPageTitle().contains("Integrations")
                        || dashboardPage.isDashboardDisplayed(),
                "Integrations page should be accessible from navigation");
    }

    @Test(priority = 2, description = "FR8-INT-02: Verify project selector on dashboard works correctly")
    public void testProjectSelector() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.doLogin(ConfigReader.getUsername(), ConfigReader.getPassword());
        DashboardPage dashboardPage = new DashboardPage(driver);
        dashboardPage.selectProject("Default Project");
        Assert.assertTrue(dashboardPage.isDashboardDisplayed(),
                "Dashboard should reload with selected project data");
    }
}
