package pages;

import base.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class DashboardPage extends BasePage {

    @FindBy(xpath = "//h1[contains(text(), 'Dashboard')]")
    private WebElement dashboardHeader;

    @FindBy(xpath = "//a[contains(text(), 'Create Experiment')]")
    private WebElement createExperimentBtn;

    @FindBy(xpath = "//a[contains(text(), 'New A/B Test')]")
    private WebElement newABTestBtn;

    @FindBy(xpath = "//a[contains(text(), 'New Split URL Test')]")
    private WebElement newSplitURLBtn;

    @FindBy(xpath = "//a[contains(text(), 'New Multivariate Test')]")
    private WebElement newMultivariateBtn;

    @FindBy(xpath = "//a[contains(text(), 'Insights')]")
    private WebElement insightsNav;

    @FindBy(xpath = "//a[contains(text(), 'Personalize')]")
    private WebElement personalizeNav;

    @FindBy(xpath = "//a[contains(text(), 'Reports')]")
    private WebElement reportsNav;

    @FindBy(xpath = "//a[contains(text(), 'Plan')]")
    private WebElement planNav;

    @FindBy(xpath = "//a[contains(text(), 'Integrations')]")
    private WebElement integrationsNav;

    @FindBy(xpath = "//a[contains(text(), 'Settings')]")
    private WebElement settingsNav;

    @FindBy(xpath = "//div[contains(@class, 'experiment-card')]")
    private List<WebElement> experimentCards;

    @FindBy(xpath = "//div[contains(@class, 'active-experiments-count')]")
    private WebElement activeExperimentsCount;

    @FindBy(xpath = "//div[contains(@class, 'total-visitors-count')]")
    private WebElement totalVisitorsCount;

    @FindBy(xpath = "//div[contains(@class, 'conversion-rate')]")
    private WebElement conversionRate;

    @FindBy(xpath = "//div[contains(@class, 'recent-activity-panel')]")
    private WebElement recentActivityPanel;

    @FindBy(xpath = "//div[contains(@class, 'search-bar')]//input")
    private WebElement searchInput;

    @FindBy(xpath = "//div[contains(@class, 'notification-bell')]")
    private WebElement notificationBell;

    @FindBy(xpath = "//div[contains(@class, 'quick-start-guide')]")
    private WebElement quickStartGuide;

    @FindBy(xpath = "//select[contains(@class, 'project-selector')]")
    private WebElement projectSelector;

    @FindBy(xpath = "//div[contains(@class, 'sidebar-menu')]//a")
    private List<WebElement> sidebarMenuItems;

    public DashboardPage(WebDriver driver) {
        super(driver);
    }

    public boolean isDashboardDisplayed() {
        return isElementDisplayed(dashboardHeader);
    }

    public String getDashboardPageTitle() {
        return getPageTitle();
    }

    public void clickCreateExperiment() {
        clickElement(createExperimentBtn);
    }

    public void navigateToABTest() {
        clickElement(createExperimentBtn);
        clickElement(newABTestBtn);
    }

    public void navigateToSplitURLTest() {
        clickElement(createExperimentBtn);
        clickElement(newSplitURLBtn);
    }

    public void navigateToMultivariateTest() {
        clickElement(createExperimentBtn);
        clickElement(newMultivariateBtn);
    }

    public void navigateToInsights() {
        clickElement(insightsNav);
    }

    public void navigateToPersonalize() {
        clickElement(personalizeNav);
    }

    public void navigateToReports() {
        clickElement(reportsNav);
    }

    public void navigateToPlan() {
        clickElement(planNav);
    }

    public void navigateToIntegrations() {
        clickElement(integrationsNav);
    }

    public int getActiveExperimentCount() {
        return isElementDisplayed(activeExperimentsCount)
                ? Integer.parseInt(getText(activeExperimentsCount)) : 0;
    }

    public int getExperimentCardCount() {
        return experimentCards.size();
    }

    public boolean isRecentActivityVisible() {
        return isElementDisplayed(recentActivityPanel);
    }

    public boolean isQuickStartGuideVisible() {
        return isElementDisplayed(quickStartGuide);
    }

    public void searchExperiment(String searchTerm) {
        sendKeys(searchInput, searchTerm);
    }

    public void selectProject(String projectName) {
        selectByVisibleText(projectSelector, projectName);
    }

    public boolean isNotificationVisible() {
        return isElementDisplayed(notificationBell);
    }

    public void clickSidebarMenuItem(String itemName) {
        for (WebElement item : sidebarMenuItems) {
            if (getText(item).equalsIgnoreCase(itemName)) {
                clickElement(item);
                break;
            }
        }
    }
}
