package pages;

import base.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class PersonalizePage extends BasePage {

    @FindBy(xpath = "//h1[contains(text(), 'Personalize')]")
    private WebElement personalizeHeader;

    @FindBy(id = "create-campaign-btn")
    private WebElement createCampaignBtn;

    @FindBy(id = "campaign-name")
    private WebElement campaignNameField;

    @FindBy(id = "campaign-description")
    private WebElement campaignDescriptionField;

    @FindBy(xpath = "//button[contains(text(), 'Add Segment')]")
    private WebElement addSegmentBtn;

    @FindBy(xpath = "//div[contains(@class, 'segment-block')]")
    private List<WebElement> segmentBlocks;

    @FindBy(xpath = "//select[contains(@class, 'segment-criteria-select')]")
    private List<WebElement> segmentCriteriaSelects;

    @FindBy(xpath = "//input[contains(@class, 'segment-value-input')]")
    private List<WebElement> segmentValueInputs;

    @FindBy(xpath = "//button[contains(text(), 'Add Personalization')]")
    private WebElement addPersonalizationBtn;

    @FindBy(xpath = "//div[contains(@class, 'personalization-block')]")
    private List<WebElement> personalizationBlocks;

    @FindBy(id = "personalized-content-editor")
    private WebElement personalizedContentEditor;

    @FindBy(id = "preview-personalization-btn")
    private WebElement previewPersonalizationBtn;

    @FindBy(xpath = "//div[contains(@class, 'personalized-preview')]")
    private WebElement personalizedPreviewPanel;

    @FindBy(id = "launch-campaign-btn")
    private WebElement launchCampaignBtn;

    @FindBy(id = "pause-campaign-btn")
    private WebElement pauseCampaignBtn;

    @FindBy(xpath = "//div[contains(@class, 'campaign-card')]")
    private List<WebElement> campaignCards;

    @FindBy(xpath = "//div[contains(@class, 'campaign-status')]")
    private List<WebElement> campaignStatuses;

    @FindBy(xpath = "//div[contains(@class, 'campaign-metrics')]")
    private List<WebElement> campaignMetrics;

    @FindBy(xpath = "//div[contains(@class, 'geography-selector')]//select")
    private WebElement geographySelector;

    @FindBy(xpath = "//div[contains(@class, 'behavior-selector')]//select")
    private WebElement behaviorSelector;

    @FindBy(xpath = "//div[contains(@class, 'device-selector')]//select")
    private WebElement deviceSelector;

    @FindBy(id = "save-campaign-draft-btn")
    private WebElement saveCampaignDraftBtn;

    @FindBy(xpath = "//div[contains(@class, 'conversion-lift')]")
    private WebElement conversionLift;

    @FindBy(xpath = "//div[contains(@class, 'engagement-rate')]")
    private WebElement engagementRate;

    @FindBy(id = "traffic-percentage-input")
    private WebElement trafficPercentageInput;

    public PersonalizePage(WebDriver driver) {
        super(driver);
    }

    public boolean isPersonalizePageDisplayed() {
        return isElementDisplayed(personalizeHeader);
    }

    public void clickCreateCampaign() {
        clickElement(createCampaignBtn);
    }

    public void createCampaign(String name, String description) {
        clickElement(createCampaignBtn);
        sendKeys(campaignNameField, name);
        sendKeys(campaignDescriptionField, description);
    }

    public void addSegmentByGeography(String geography) {
        clickElement(addSegmentBtn);
        if (!segmentCriteriaSelects.isEmpty()) {
            selectByVisibleText(segmentCriteriaSelects.get(segmentCriteriaSelects.size() - 1), "Geography");
        }
        if (!segmentValueInputs.isEmpty()) {
            sendKeys(segmentValueInputs.get(segmentValueInputs.size() - 1), geography);
        }
    }

    public void addSegmentByBehavior(String behavior) {
        clickElement(addSegmentBtn);
        if (!segmentCriteriaSelects.isEmpty()) {
            selectByVisibleText(segmentCriteriaSelects.get(segmentCriteriaSelects.size() - 1), "Behavior");
        }
        if (!segmentValueInputs.isEmpty()) {
            sendKeys(segmentValueInputs.get(segmentValueInputs.size() - 1), behavior);
        }
    }

    public void addSegmentByDevice(String deviceType) {
        clickElement(addSegmentBtn);
        if (!segmentCriteriaSelects.isEmpty()) {
            selectByVisibleText(segmentCriteriaSelects.get(segmentCriteriaSelects.size() - 1), "Device Type");
        }
        if (!segmentValueInputs.isEmpty()) {
            sendKeys(segmentValueInputs.get(segmentValueInputs.size() - 1), deviceType);
        }
    }

    public int getSegmentCount() {
        return segmentBlocks.size();
    }

    public void addPersonalizationContent(String content) {
        clickElement(addPersonalizationBtn);
        sendKeys(personalizedContentEditor, content);
    }

    public void previewPersonalization() {
        clickElement(previewPersonalizationBtn);
    }

    public boolean isPersonalizedPreviewDisplayed() {
        return isElementDisplayed(personalizedPreviewPanel);
    }

    public void launchCampaign() {
        clickElement(launchCampaignBtn);
        waitForPageLoad();
    }

    public void pauseCampaign() {
        clickElement(pauseCampaignBtn);
    }

    public int getCampaignCount() {
        return campaignCards.size();
    }

    public String getCampaignStatus(int index) {
        if (index < campaignStatuses.size()) {
            return getText(campaignStatuses.get(index));
        }
        return "";
    }

    public void saveCampaignDraft() {
        clickElement(saveCampaignDraftBtn);
    }

    public void setTrafficPercentage(int percentage) {
        sendKeys(trafficPercentageInput, String.valueOf(percentage));
    }

    public String getConversionLift() {
        return isElementDisplayed(conversionLift) ? getText(conversionLift) : "";
    }

    public String getEngagementRate() {
        return isElementDisplayed(engagementRate) ? getText(engagementRate) : "";
    }
}
