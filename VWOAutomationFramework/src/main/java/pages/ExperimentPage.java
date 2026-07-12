package pages;

import base.BasePage;
import enums.AudienceType;
import enums.ExperimentType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class ExperimentPage extends BasePage {

    @FindBy(id = "experiment-name")
    private WebElement experimentNameField;

    @FindBy(id = "experiment-description")
    private WebElement experimentDescriptionField;

    @FindBy(id = "experiment-url")
    private WebElement experimentUrlField;

    @FindBy(xpath = "//input[@name='hypothesis']")
    private WebElement hypothesisField;

    @FindBy(id = "save-draft-btn")
    private WebElement saveDraftBtn;

    @FindBy(id = "launch-experiment-btn")
    private WebElement launchExperimentBtn;

    @FindBy(id = "pause-experiment-btn")
    private WebElement pauseExperimentBtn;

    @FindBy(id = "stop-experiment-btn")
    private WebElement stopExperimentBtn;

    @FindBy(id = "declare-winner-btn")
    private WebElement declareWinnerBtn;

    @FindBy(xpath = "//button[contains(text(), 'Add Variation')]")
    private WebElement addVariationBtn;

    @FindBy(xpath = "//div[contains(@class, 'variation-block')]")
    private List<WebElement> variationBlocks;

    @FindBy(xpath = "//input[contains(@id, 'variation-name')]")
    private List<WebElement> variationNameFields;

    @FindBy(xpath = "//div[contains(@class, 'variation-url')]//input")
    private List<WebElement> variationUrlFields;

    @FindBy(id = "traffic-allocation-slider")
    private WebElement trafficAllocationSlider;

    @FindBy(id = "primary-metric-select")
    private WebElement primaryMetricSelect;

    @FindBy(xpath = "//input[@name='goal-name']")
    private WebElement goalNameField;

    @FindBy(id = "add-goal-btn")
    private WebElement addGoalBtn;

    @FindBy(xpath = "//div[contains(@class, 'goal-item')]")
    private List<WebElement> goalItems;

    @FindBy(xpath = "//div[contains(@class, 'audience-section')]//button[contains(text(), 'Add Condition')]")
    private WebElement addAudienceConditionBtn;

    @FindBy(xpath = "//select[contains(@class, 'audience-type-select')]")
    private WebElement audienceTypeSelect;

    @FindBy(xpath = "//input[contains(@class, 'audience-value-input')]")
    private WebElement audienceValueInput;

    @FindBy(xpath = "//div[contains(@class, 'audience-preview')]")
    private WebElement audiencePreview;

    @FindBy(id = "experiment-status-badge")
    private WebElement experimentStatusBadge;

    @FindBy(xpath = "//div[contains(@class, 'experiment-summary')]")
    private WebElement experimentSummary;

    @FindBy(xpath = "//div[contains(@class, 'smartstats-panel')]")
    private WebElement smartStatsPanel;

    @FindBy(xpath = "//div[contains(@class, 'confidence-score')]")
    private WebElement confidenceScore;

    @FindBy(xpath = "//div[contains(@class, 'winner-variation')]")
    private WebElement winnerVariation;

    @FindBy(xpath = "//span[contains(@class, 'visitors-split')]")
    private List<WebElement> visitorsSplitLabels;

    @FindBy(xpath = "//button[contains(text(), 'Preview')]")
    private WebElement previewBtn;

    @FindBy(xpath = "//iframe[contains(@id, 'preview-frame')]")
    private WebElement previewFrame;

    @FindBy(xpath = "//button[contains(text(), 'Schedule')]")
    private WebElement scheduleBtn;

    @FindBy(id = "schedule-start-date")
    private WebElement scheduleStartDate;

    @FindBy(id = "schedule-end-date")
    private WebElement scheduleEndDate;

    @FindBy(id = "confirm-schedule-btn")
    private WebElement confirmScheduleBtn;

    @FindBy(xpath = "//div[contains(@class, 'error-toast')]")
    private WebElement errorToast;

    @FindBy(xpath = "//div[contains(@class, 'success-toast')]")
    private WebElement successToast;

    @FindBy(xpath = "//button[contains(text(), 'Visual Editor')]")
    private WebElement visualEditorTab;

    @FindBy(xpath = "//button[contains(text(), 'Code Editor')]")
    private WebElement codeEditorTab;

    @FindBy(xpath = "//div[contains(@class, 'code-editor')]//textarea")
    private WebElement codeEditorTextarea;

    @FindBy(id = "apply-changes-btn")
    private WebElement applyChangesBtn;

    public ExperimentPage(WebDriver driver) {
        super(driver);
    }

    public void createABTest(String name, String description, String url, String hypothesis) {
        sendKeys(experimentNameField, name);
        sendKeys(experimentDescriptionField, description);
        sendKeys(experimentUrlField, url);
        sendKeys(hypothesisField, hypothesis);
    }

    public void addVariation(String variationName) {
        clickElement(addVariationBtn);
        if (!variationNameFields.isEmpty()) {
            WebElement lastField = variationNameFields.get(variationNameFields.size() - 1);
            sendKeys(lastField, variationName);
        }
    }

    public void addVariationWithUrl(String variationName, String url) {
        clickElement(addVariationBtn);
        if (!variationNameFields.isEmpty()) {
            sendKeys(variationNameFields.get(variationNameFields.size() - 1), variationName);
        }
        if (!variationUrlFields.isEmpty()) {
            sendKeys(variationUrlFields.get(variationUrlFields.size() - 1), url);
        }
    }

    public void selectPrimaryMetric(String metricName) {
        selectByVisibleText(primaryMetricSelect, metricName);
    }

    public void addGoal(String goalName) {
        clickElement(addGoalBtn);
        sendKeys(goalNameField, goalName);
    }

    public int getGoalCount() {
        return goalItems.size();
    }

    public void addAudienceCondition(AudienceType type, String value) {
        clickElement(addAudienceConditionBtn);
        selectByVisibleText(audienceTypeSelect, type.getDisplayName());
        sendKeys(audienceValueInput, value);
    }

    public boolean isAudiencePreviewDisplayed() {
        return isElementDisplayed(audiencePreview);
    }

    public void setTrafficAllocation(int percentage) {
        js.executeScript("arguments[0].value = arguments[1];", trafficAllocationSlider, percentage);
    }

    public void saveDraft() {
        clickElement(saveDraftBtn);
    }

    public void launchExperiment() {
        clickElement(launchExperimentBtn);
        waitForPageLoad();
    }

    public void pauseExperiment() {
        clickElement(pauseExperimentBtn);
    }

    public void stopExperiment() {
        clickElement(stopExperimentBtn);
    }

    public void declareWinner(String variationName) {
        clickElement(declareWinnerBtn);
    }

    public String getExperimentStatus() {
        return isElementDisplayed(experimentStatusBadge) ? getText(experimentStatusBadge) : "";
    }

    public boolean isSmartStatsPanelDisplayed() {
        return isElementDisplayed(smartStatsPanel);
    }

    public String getConfidenceScore() {
        return isElementDisplayed(confidenceScore) ? getText(confidenceScore) : "";
    }

    public String getWinnerVariation() {
        return isElementDisplayed(winnerVariation) ? getText(winnerVariation) : "";
    }

    public void openPreview() {
        clickElement(previewBtn);
    }

    public boolean isVariationDisplayedInPreview(String variationName) {
        switchToFrame(previewFrame);
        boolean displayed = driver.getPageSource().contains(variationName);
        switchToDefaultContent();
        return displayed;
    }

    public void scheduleExperiment(String startDate, String endDate) {
        clickElement(scheduleBtn);
        sendKeys(scheduleStartDate, startDate);
        sendKeys(scheduleEndDate, endDate);
        clickElement(confirmScheduleBtn);
    }

    public boolean isErrorDisplayed() {
        return isElementDisplayed(errorToast);
    }

    public String getErrorMessage() {
        return isElementDisplayed(errorToast) ? getText(errorToast) : "";
    }

    public boolean isSuccessDisplayed() {
        return isElementDisplayed(successToast);
    }

    public void useVisualEditor() {
        clickElement(visualEditorTab);
    }

    public void useCodeEditor(String code) {
        clickElement(codeEditorTab);
        sendKeys(codeEditorTextarea, code);
        clickElement(applyChangesBtn);
    }

    public int getVariationCount() {
        return variationBlocks.size();
    }

    public boolean isExperimentSummaryDisplayed() {
        return isElementDisplayed(experimentSummary);
    }
}
