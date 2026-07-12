package tests.experiments;

import base.BaseTest;
import enums.AudienceType;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.DashboardPage;
import pages.ExperimentPage;
import pages.LoginPage;
import utils.ConfigReader;

public class AudienceTargetingTest extends BaseTest {

    @Test(priority = 1, description = "FR5-AUD-01: Verify audience targeting by geography (FR5)")
    public void testAudienceByGeography() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.doLogin(ConfigReader.getUsername(), ConfigReader.getPassword());
        DashboardPage dashboardPage = new DashboardPage(driver);
        dashboardPage.navigateToABTest();
        ExperimentPage experimentPage = new ExperimentPage(driver);
        experimentPage.createABTest(
                "Audience - Geography Targeting",
                "Targeting users from specific countries",
                "https://app.vwo.com/",
                "Geo-targeted experiment will improve regional conversions"
        );
        experimentPage.addVariation("US Specific Content");
        experimentPage.addAudienceCondition(AudienceType.GEOGRAPHY, "United States");
        experimentPage.saveDraft();
        Assert.assertTrue(experimentPage.isSuccessDisplayed(),
                "Experiment with geographic audience targeting should save successfully");
    }

    @Test(priority = 2, description = "FR5-AUD-02: Verify audience targeting by device type")
    public void testAudienceByDeviceType() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.doLogin(ConfigReader.getUsername(), ConfigReader.getPassword());
        DashboardPage dashboardPage = new DashboardPage(driver);
        dashboardPage.navigateToABTest();
        ExperimentPage experimentPage = new ExperimentPage(driver);
        experimentPage.createABTest(
                "Audience - Device Targeting",
                "Targeting mobile users specifically",
                "https://app.vwo.com/",
                "Mobile-optimized experience will increase engagement"
        );
        experimentPage.addVariation("Mobile Optimized Layout");
        experimentPage.addAudienceCondition(AudienceType.DEVICE, "Mobile");
        experimentPage.saveDraft();
        Assert.assertTrue(experimentPage.isSuccessDisplayed(),
                "Experiment with device-based audience targeting should save successfully");
    }

    @Test(priority = 3, description = "FR5-AUD-03: Verify audience targeting by behavior (returning vs new)")
    public void testAudienceByBehavior() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.doLogin(ConfigReader.getUsername(), ConfigReader.getPassword());
        DashboardPage dashboardPage = new DashboardPage(driver);
        dashboardPage.navigateToABTest();
        ExperimentPage experimentPage = new ExperimentPage(driver);
        experimentPage.createABTest(
                "Audience - Behavior Targeting",
                "Targeting returning visitors",
                "https://app.vwo.com/",
                "Returning visitors will respond better to loyalty messaging"
        );
        experimentPage.addVariation("Returning Visitor Experience");
        experimentPage.addAudienceCondition(AudienceType.BEHAVIOR, "Returning Visitor");
        experimentPage.saveDraft();
        Assert.assertTrue(experimentPage.isSuccessDisplayed(),
                "Experiment with behavioral audience targeting should save successfully");
    }

    @Test(priority = 4, description = "FR5-AUD-04: Verify multiple audience conditions can be combined")
    public void testMultipleAudienceConditions() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.doLogin(ConfigReader.getUsername(), ConfigReader.getPassword());
        DashboardPage dashboardPage = new DashboardPage(driver);
        dashboardPage.navigateToABTest();
        ExperimentPage experimentPage = new ExperimentPage(driver);
        experimentPage.createABTest(
                "Audience - Combined Conditions",
                "Multiple audience conditions combined",
                "https://app.vwo.com/",
                "Combined targeting will narrow down audience precisely"
        );
        experimentPage.addVariation("Highly Targeted Content");
        experimentPage.addAudienceCondition(AudienceType.GEOGRAPHY, "United States");
        experimentPage.addAudienceCondition(AudienceType.DEVICE, "Desktop");
        experimentPage.addAudienceCondition(AudienceType.BEHAVIOR, "New Visitor");
        boolean previewDisplayed = experimentPage.isAudiencePreviewDisplayed();
        experimentPage.saveDraft();
        Assert.assertTrue(experimentPage.isSuccessDisplayed(),
                "Experiment with multiple combined audience conditions should save successfully");
    }

    @Test(priority = 5, description = "FR5-AUD-05: Verify audience preview shows estimated reach")
    public void testAudiencePreviewReachEstimate() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.doLogin(ConfigReader.getUsername(), ConfigReader.getPassword());
        DashboardPage dashboardPage = new DashboardPage(driver);
        dashboardPage.navigateToABTest();
        ExperimentPage experimentPage = new ExperimentPage(driver);
        experimentPage.createABTest(
                "Audience - Reach Preview",
                "Verifying audience reach estimation",
                "https://app.vwo.com/",
                "Audience preview should show estimated reach"
        );
        experimentPage.addVariation("Targeted Variation");
        experimentPage.addAudienceCondition(AudienceType.GEOGRAPHY, "United States");
        Assert.assertTrue(experimentPage.isAudiencePreviewDisplayed(),
                "Audience preview with reach estimate should be displayed after adding conditions");
    }
}
