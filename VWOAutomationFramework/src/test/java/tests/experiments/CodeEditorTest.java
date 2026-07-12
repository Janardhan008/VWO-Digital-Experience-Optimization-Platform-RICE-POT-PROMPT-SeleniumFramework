package tests.experiments;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.DashboardPage;
import pages.ExperimentPage;
import pages.LoginPage;
import utils.ConfigReader;

public class CodeEditorTest extends BaseTest {

    @Test(priority = 1, description = "FR3-EXP-17: Verify user can use Code Editor for variation changes (FR3)")
    public void testCodeEditorForVariations() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.doLogin(ConfigReader.getUsername(), ConfigReader.getPassword());
        DashboardPage dashboardPage = new DashboardPage(driver);
        dashboardPage.navigateToABTest();
        ExperimentPage experimentPage = new ExperimentPage(driver);
        experimentPage.createABTest(
                "Code Editor - Custom CSS Test",
                "Testing code editor for custom CSS injection",
                "https://app.vwo.com/",
                "Custom CSS will improve visual appeal and conversion"
        );
        experimentPage.addVariation("Custom CSS Variation");
        String customCSS = "body { background-color: #f5f5f5; } .cta-button { color: #ffffff; background-color: #28a745; }";
        experimentPage.useCodeEditor(customCSS);
        experimentPage.saveDraft();
        Assert.assertTrue(experimentPage.isSuccessDisplayed(),
                "Code editor changes should be saved successfully");
    }

    @Test(priority = 2, description = "FR3-EXP-18: Verify Code Editor accepts JavaScript for advanced modifications")
    public void testCodeEditorWithJavaScript() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.doLogin(ConfigReader.getUsername(), ConfigReader.getPassword());
        DashboardPage dashboardPage = new DashboardPage(driver);
        dashboardPage.navigateToABTest();
        ExperimentPage experimentPage = new ExperimentPage(driver);
        experimentPage.createABTest(
                "Code Editor - JS Injection",
                "Testing JS injection for dynamic changes",
                "https://app.vwo.com/",
                "JavaScript changes will enhance interactivity"
        );
        experimentPage.addVariation("JS Enhanced Variation");
        String customJS = "document.querySelector('.hero-title').innerText = 'Welcome to Optimized VWO';";
        experimentPage.useCodeEditor(customJS);
        experimentPage.saveDraft();
        Assert.assertTrue(experimentPage.isSuccessDisplayed(),
                "JavaScript changes via code editor should save successfully");
    }

    @Test(priority = 3, description = "FR3-EXP-19: Verify toggle between Visual and Code Editor works")
    public void testToggleBetweenVisualAndCodeEditor() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.doLogin(ConfigReader.getUsername(), ConfigReader.getPassword());
        DashboardPage dashboardPage = new DashboardPage(driver);
        dashboardPage.navigateToABTest();
        ExperimentPage experimentPage = new ExperimentPage(driver);
        experimentPage.createABTest(
                "Editor Toggle Test",
                "Testing toggle between editor modes",
                "https://app.vwo.com/",
                "Toggle should work seamlessly"
        );
        experimentPage.addVariation("Toggled Variation");
        experimentPage.useVisualEditor();
        experimentPage.useCodeEditor("/* CSS Comment */ body { margin: 0; }");
        experimentPage.saveDraft();
        Assert.assertTrue(experimentPage.isSuccessDisplayed(),
                "Toggle between Visual and Code Editors should work correctly");
    }
}
