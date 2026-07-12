package pages;

import base.BasePage;
import enums.InsightType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class InsightsPage extends BasePage {

    @FindBy(xpath = "//h1[contains(text(), 'Insights')]")
    private WebElement insightsHeader;

    @FindBy(xpath = "//button[contains(text(), 'Heatmap')]")
    private WebElement heatmapTab;

    @FindBy(xpath = "//button[contains(text(), 'Session Recordings')]")
    private WebElement sessionRecordingsTab;

    @FindBy(xpath = "//button[contains(text(), 'Surveys')]")
    private WebElement surveysTab;

    @FindBy(xpath = "//button[contains(text(), 'Funnels')]")
    private WebElement funnelsTab;

    @FindBy(id = "heatmap-url-input")
    private WebElement heatmapUrlInput;

    @FindBy(id = "generate-heatmap-btn")
    private WebElement generateHeatmapBtn;

    @FindBy(xpath = "//div[contains(@class, 'heatmap-container')]")
    private WebElement heatmapContainer;

    @FindBy(xpath = "//div[contains(@class, 'click-heatmap-overlay')]")
    private WebElement clickHeatmapOverlay;

    @FindBy(xpath = "//div[contains(@class, 'scroll-heatmap-overlay')]")
    private WebElement scrollHeatmapOverlay;

    @FindBy(xpath = "//select[contains(@class, 'heatmap-type-select')]")
    private WebElement heatmapTypeSelect;

    @FindBy(xpath = "//select[contains(@class, 'device-filter')]")
    private WebElement deviceFilter;

    @FindBy(xpath = "//div[contains(@class, 'recording-list')]/div")
    private List<WebElement> recordingList;

    @FindBy(id = "play-recording-btn")
    private WebElement playRecordingBtn;

    @FindBy(xpath = "//div[contains(@class, 'recording-player')]")
    private WebElement recordingPlayer;

    @FindBy(xpath = "//button[contains(@class, 'playback-control')]")
    private List<WebElement> playbackControls;

    @FindBy(id = "session-filter-date")
    private WebElement sessionFilterDate;

    @FindBy(id = "session-filter-user")
    private WebElement sessionFilterUser;

    @FindBy(xpath = "//button[contains(text(), 'Apply Filter')]")
    private WebElement applyFilterBtn;

    @FindBy(xpath = "//div[contains(@class, 'funnel-step')]")
    private List<WebElement> funnelSteps;

    @FindBy(id = "add-funnel-step-btn")
    private WebElement addFunnelStepBtn;

    @FindBy(id = "funnel-step-url")
    private WebElement funnelStepUrl;

    @FindBy(xpath = "//div[contains(@class, 'funnel-dropoff')]")
    private List<WebElement> funnelDropOffs;

    @FindBy(xpath = "//div[contains(@class, 'survey-list')]/div")
    private List<WebElement> surveyList;

    @FindBy(id = "create-survey-btn")
    private WebElement createSurveyBtn;

    @FindBy(id = "survey-question-input")
    private WebElement surveyQuestionInput;

    @FindBy(id = "publish-survey-btn")
    private WebElement publishSurveyBtn;

    @FindBy(xpath = "//div[contains(@class, 'insight-card')]")
    private List<WebElement> insightCards;

    @FindBy(xpath = "//input[contains(@class, 'date-range-picker')]")
    private WebElement dateRangePicker;

    @FindBy(id = "export-insights-btn")
    private WebElement exportInsightsBtn;

    @FindBy(xpath = "//div[contains(@class, 'insight-summary-stat')]")
    private List<WebElement> insightSummaryStats;

    public InsightsPage(WebDriver driver) {
        super(driver);
    }

    public boolean isInsightsPageDisplayed() {
        return isElementDisplayed(insightsHeader);
    }

    public void navigateToHeatmap() {
        clickElement(heatmapTab);
    }

    public void navigateToSessionRecordings() {
        clickElement(sessionRecordingsTab);
    }

    public void navigateToSurveys() {
        clickElement(surveysTab);
    }

    public void navigateToFunnels() {
        clickElement(funnelsTab);
    }

    public void generateHeatmap(String url, InsightType type) {
        sendKeys(heatmapUrlInput, url);
        selectByVisibleText(heatmapTypeSelect, type.getDisplayName());
        clickElement(generateHeatmapBtn);
        waitForPageLoad();
    }

    public boolean isHeatmapDisplayed() {
        return isElementDisplayed(heatmapContainer);
    }

    public boolean isClickHeatmapOverlayDisplayed() {
        return isElementDisplayed(clickHeatmapOverlay);
    }

    public boolean isScrollHeatmapOverlayDisplayed() {
        return isElementDisplayed(scrollHeatmapOverlay);
    }

    public void filterByDevice(String deviceType) {
        selectByVisibleText(deviceFilter, deviceType);
    }

    public int getSessionRecordingCount() {
        return recordingList.size();
    }

    public void playRecording(int index) {
        if (index < recordingList.size()) {
            clickElement(recordingList.get(index));
            clickElement(playRecordingBtn);
        }
    }

    public boolean isRecordingPlayerDisplayed() {
        return isElementDisplayed(recordingPlayer);
    }

    public void filterSessionsByDate(String date) {
        sendKeys(sessionFilterDate, date);
        clickElement(applyFilterBtn);
    }

    public void filterSessionsByUser(String userId) {
        sendKeys(sessionFilterUser, userId);
        clickElement(applyFilterBtn);
    }

    public void addFunnelStep(String pageUrl) {
        clickElement(addFunnelStepBtn);
        waitForVisibility(funnelStepUrl);
        sendKeys(funnelStepUrl, pageUrl);
    }

    public int getFunnelStepCount() {
        return funnelSteps.size();
    }

    public List<String> getFunnelDropOffPercentages() {
        return funnelDropOffs.stream().map(this::getText).toList();
    }

    public void createSurvey(String question) {
        clickElement(createSurveyBtn);
        sendKeys(surveyQuestionInput, question);
        clickElement(publishSurveyBtn);
    }

    public int getSurveyCount() {
        return surveyList.size();
    }

    public int getInsightCardCount() {
        return insightCards.size();
    }

    public void setDateRange(String dateRange) {
        sendKeys(dateRangePicker, dateRange);
    }

    public void exportInsights() {
        clickElement(exportInsightsBtn);
    }

    public boolean isPlaybackControlDisplayed() {
        return !playbackControls.isEmpty();
    }
}
