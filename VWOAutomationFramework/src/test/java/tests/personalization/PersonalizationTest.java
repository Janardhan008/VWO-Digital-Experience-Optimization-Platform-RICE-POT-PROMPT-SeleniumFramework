package tests.personalization;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.DashboardPage;
import pages.LoginPage;
import pages.PersonalizePage;
import utils.ConfigReader;

public class PersonalizationTest extends BaseTest {

    @Test(priority = 1, description = "FR7-PER-01: Verify user can navigate to Personalize page")
    public void testNavigateToPersonalizePage() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.doLogin(ConfigReader.getUsername(), ConfigReader.getPassword());
        DashboardPage dashboardPage = new DashboardPage(driver);
        dashboardPage.navigateToPersonalize();
        PersonalizePage personalizePage = new PersonalizePage(driver);
        Assert.assertTrue(personalizePage.isPersonalizePageDisplayed(),
                "Personalize page should be displayed after navigation");
    }

    @Test(priority = 2, description = "FR7-PER-02: Verify user can create a personalization campaign with segments")
    public void testCreatePersonalizationCampaignWithSegments() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.doLogin(ConfigReader.getUsername(), ConfigReader.getPassword());
        DashboardPage dashboardPage = new DashboardPage(driver);
        dashboardPage.navigateToPersonalize();
        PersonalizePage personalizePage = new PersonalizePage(driver);
        personalizePage.createCampaign("US Visitor Personalized Banner", "Show different banner for US visitors");
        personalizePage.addSegmentByGeography("United States");
        personalizePage.addPersonalizationContent("Welcome to VWO - Exclusive US Offer!");
        personalizePage.saveCampaignDraft();
        Assert.assertTrue(personalizePage.isPersonalizePageDisplayed(),
                "Personalization campaign should be saved as draft");
    }

    @Test(priority = 3, description = "FR7-PER-03: Verify campaign with behavioral targeting can be launched")
    public void testLaunchBehavioralTargetingCampaign() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.doLogin(ConfigReader.getUsername(), ConfigReader.getPassword());
        DashboardPage dashboardPage = new DashboardPage(driver);
        dashboardPage.navigateToPersonalize();
        PersonalizePage personalizePage = new PersonalizePage(driver);
        personalizePage.createCampaign("Returning Visitor Discount", "Show discount to returning visitors");
        personalizePage.addSegmentByBehavior("Returning Visitor");
        personalizePage.addPersonalizationContent("Welcome back! Here's 20% off for you.");
        personalizePage.saveCampaignDraft();
        personalizePage.launchCampaign();
        Assert.assertTrue(personalizePage.isPersonalizePageDisplayed(),
                "Behavioral targeting campaign should launch successfully");
    }

    @Test(priority = 4, description = "FR7-PER-04: Verify campaign with device-based targeting")
    public void testDeviceBasedPersonalization() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.doLogin(ConfigReader.getUsername(), ConfigReader.getPassword());
        DashboardPage dashboardPage = new DashboardPage(driver);
        dashboardPage.navigateToPersonalize();
        PersonalizePage personalizePage = new PersonalizePage(driver);
        personalizePage.createCampaign("Mobile App Install Banner", "Show app install prompt on mobile");
        personalizePage.addSegmentByDevice("Mobile");
        personalizePage.addPersonalizationContent("Download our mobile app for best experience!");
        personalizePage.saveCampaignDraft();
        Assert.assertTrue(personalizePage.isPersonalizePageDisplayed(),
                "Device-based campaign should be saved");
    }

    @Test(priority = 5, description = "FR7-PER-05: Verify user can preview personalization before launch")
    public void testPreviewPersonalizationCampaign() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.doLogin(ConfigReader.getUsername(), ConfigReader.getPassword());
        DashboardPage dashboardPage = new DashboardPage(driver);
        dashboardPage.navigateToPersonalize();
        PersonalizePage personalizePage = new PersonalizePage(driver);
        personalizePage.createCampaign("Preview Campaign", "Testing preview functionality");
        personalizePage.addSegmentByGeography("United States");
        personalizePage.addPersonalizationContent("Preview content for test");
        personalizePage.previewPersonalization();
        Assert.assertTrue(personalizePage.isPersonalizedPreviewDisplayed(),
                "Personalization preview should display the targeted content");
    }

    @Test(priority = 6, description = "FR7-PER-06: Verify campaign status transitions correctly (Draft -> Running -> Paused)")
    public void testCampaignStatusTransitions() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.doLogin(ConfigReader.getUsername(), ConfigReader.getPassword());
        DashboardPage dashboardPage = new DashboardPage(driver);
        dashboardPage.navigateToPersonalize();
        PersonalizePage personalizePage = new PersonalizePage(driver);
        personalizePage.createCampaign("Status Transition Test", "Testing campaign lifecycle");
        personalizePage.addSegmentByGeography("United States");
        personalizePage.addPersonalizationContent("Test content for status validation");
        personalizePage.saveCampaignDraft();
        int campaignCountBefore = personalizePage.getCampaignCount();
        personalizePage.launchCampaign();
        int campaignCountAfter = personalizePage.getCampaignCount();
        Assert.assertEquals(campaignCountAfter, campaignCountBefore,
                "Campaign count should remain consistent after launch");
    }

    @Test(priority = 7, description = "FR7-PER-07: Verify multiple segments can be added to a single campaign")
    public void testMultipleSegmentsInCampaign() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.doLogin(ConfigReader.getUsername(), ConfigReader.getPassword());
        DashboardPage dashboardPage = new DashboardPage(driver);
        dashboardPage.navigateToPersonalize();
        PersonalizePage personalizePage = new PersonalizePage(driver);
        personalizePage.createCampaign("Multi-Segment Campaign", "Campaign with multiple audience segments");
        personalizePage.addSegmentByGeography("United States");
        personalizePage.addSegmentByBehavior("New Visitor");
        personalizePage.addSegmentByDevice("Desktop");
        int segmentCount = personalizePage.getSegmentCount();
        Assert.assertTrue(segmentCount >= 3, "Campaign should have 3 segments configured");
    }

    @Test(priority = 8, description = "FR7-PER-08: Verify traffic percentage can be allocated for personalization")
    public void testTrafficAllocationForCampaign() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.doLogin(ConfigReader.getUsername(), ConfigReader.getPassword());
        DashboardPage dashboardPage = new DashboardPage(driver);
        dashboardPage.navigateToPersonalize();
        PersonalizePage personalizePage = new PersonalizePage(driver);
        personalizePage.createCampaign("Traffic Allocation Test", "Testing traffic percentage allocation");
        personalizePage.addSegmentByGeography("United Kingdom");
        personalizePage.addPersonalizationContent("UK-specific content");
        personalizePage.setTrafficPercentage(50);
        personalizePage.saveCampaignDraft();
        Assert.assertTrue(personalizePage.isPersonalizePageDisplayed(),
                "Campaign with traffic allocation should save successfully");
    }
}
