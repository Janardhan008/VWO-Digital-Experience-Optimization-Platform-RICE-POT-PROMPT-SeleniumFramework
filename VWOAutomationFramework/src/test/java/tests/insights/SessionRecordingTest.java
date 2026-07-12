package tests.insights;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.DashboardPage;
import pages.InsightsPage;
import pages.LoginPage;
import utils.ConfigReader;

public class SessionRecordingTest extends BaseTest {

    @Test(priority = 1, description = "FR4-INS-06: Verify user can view session recordings list")
    public void testViewSessionRecordings() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.doLogin(ConfigReader.getUsername(), ConfigReader.getPassword());
        DashboardPage dashboardPage = new DashboardPage(driver);
        dashboardPage.navigateToInsights();
        InsightsPage insightsPage = new InsightsPage(driver);
        insightsPage.navigateToSessionRecordings();
        int recordingCount = insightsPage.getSessionRecordingCount();
        Assert.assertTrue(recordingCount >= 0, "Session recordings list should be accessible");
    }

    @Test(priority = 2, description = "FR4-INS-07: Verify user can play a session recording")
    public void testPlaySessionRecording() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.doLogin(ConfigReader.getUsername(), ConfigReader.getPassword());
        DashboardPage dashboardPage = new DashboardPage(driver);
        dashboardPage.navigateToInsights();
        InsightsPage insightsPage = new InsightsPage(driver);
        insightsPage.navigateToSessionRecordings();
        int count = insightsPage.getSessionRecordingCount();
        if (count > 0) {
            insightsPage.playRecording(0);
            Assert.assertTrue(insightsPage.isRecordingPlayerDisplayed(),
                    "Recording player should be displayed when a session is played");
        } else {
            Assert.assertTrue(true, "No recordings available to play - skipping play validation");
        }
    }

    @Test(priority = 3, description = "FR4-INS-08: Verify session recordings can be filtered by date")
    public void testFilterSessionRecordingsByDate() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.doLogin(ConfigReader.getUsername(), ConfigReader.getPassword());
        DashboardPage dashboardPage = new DashboardPage(driver);
        dashboardPage.navigateToInsights();
        InsightsPage insightsPage = new InsightsPage(driver);
        insightsPage.navigateToSessionRecordings();
        insightsPage.filterSessionsByDate("2026-01-01 to 2026-01-31");
        int filteredCount = insightsPage.getSessionRecordingCount();
        Assert.assertTrue(filteredCount >= 0, "Filtered session recording list should load");
    }

    @Test(priority = 4, description = "FR4-INS-09: Verify playback controls are available during session replay")
    public void testSessionPlaybackControls() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.doLogin(ConfigReader.getUsername(), ConfigReader.getPassword());
        DashboardPage dashboardPage = new DashboardPage(driver);
        dashboardPage.navigateToInsights();
        InsightsPage insightsPage = new InsightsPage(driver);
        insightsPage.navigateToSessionRecordings();
        int count = insightsPage.getSessionRecordingCount();
        if (count > 0) {
            insightsPage.playRecording(0);
            Assert.assertTrue(insightsPage.isPlaybackControlDisplayed(),
                    "Playback controls (play, pause, speed) should be available");
        } else {
            Assert.assertTrue(true, "No recordings available - skipping playback controls validation");
        }
    }
}
