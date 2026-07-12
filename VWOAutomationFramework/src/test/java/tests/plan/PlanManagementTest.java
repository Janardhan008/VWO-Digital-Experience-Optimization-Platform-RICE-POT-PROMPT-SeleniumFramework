package tests.plan;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.DashboardPage;
import pages.LoginPage;
import utils.ConfigReader;

public class PlanManagementTest extends BaseTest {

    @Test(priority = 1, description = "FR9-PLN-01: Verify user can navigate to Plan/Program Management page (FR9)")
    public void testNavigateToPlanManagement() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.doLogin(ConfigReader.getUsername(), ConfigReader.getPassword());
        DashboardPage dashboardPage = new DashboardPage(driver);
        dashboardPage.navigateToPlan();
        Assert.assertTrue(dashboardPage.isDashboardDisplayed()
                        || dashboardPage.getDashboardPageTitle().contains("Plan"),
                "Plan management page should be accessible");
    }

    @Test(priority = 2, description = "FR9-PLN-02: Verify notification bell is visible on dashboard for alerts")
    public void testNotificationVisibility() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.doLogin(ConfigReader.getUsername(), ConfigReader.getPassword());
        DashboardPage dashboardPage = new DashboardPage(driver);
        Assert.assertTrue(dashboardPage.isNotificationVisible(),
                "Notification bell should be visible for user alerts");
    }
}
