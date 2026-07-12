package pages;

import base.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class ReportsPage extends BasePage {

    @FindBy(xpath = "//h1[contains(text(), 'Reports')]")
    private WebElement reportsHeader;

    @FindBy(xpath = "//div[contains(@class, 'report-card')]")
    private List<WebElement> reportCards;

    @FindBy(id = "date-range-picker")
    private WebElement dateRangePicker;

    @FindBy(id = "export-csv-btn")
    private WebElement exportCsvBtn;

    @FindBy(id = "export-pdf-btn")
    private WebElement exportPdfBtn;

    @FindBy(xpath = "//div[contains(@class, 'conversion-summary')]")
    private WebElement conversionSummary;

    @FindBy(xpath = "//div[contains(@class, 'experiment-performance')]")
    private WebElement experimentPerformance;

    @FindBy(xpath = "//table[contains(@class, 'experiments-table')]/tbody/tr")
    private List<WebElement> experimentsTableRows;

    @FindBy(xpath = "//table[contains(@class, 'experiments-table')]/thead/tr/th")
    private List<WebElement> experimentsTableHeaders;

    @FindBy(xpath = "//div[contains(@class, 'filter-dropdown')]//select")
    private WebElement reportFilterSelect;

    @FindBy(xpath = "//div[contains(@class, 'status-filter')]//select")
    private WebElement statusFilterSelect;

    @FindBy(xpath = "//div[contains(@class, 'project-filter')]//select")
    private WebElement projectFilterSelect;

    @FindBy(id = "apply-filters-btn")
    private WebElement applyFiltersBtn;

    @FindBy(xpath = "//div[contains(@class, 'chart-container')]//canvas")
    private WebElement chartCanvas;

    @FindBy(xpath = "//div[contains(@class, 'summary-stat')]")
    private List<WebElement> summaryStats;

    @FindBy(xpath = "//div[contains(@class, 'experiment-name-cell')]")
    private List<WebElement> experimentNameCells;

    @FindBy(xpath = "//div[contains(@class, 'variation-cell')]")
    private List<WebElement> variationCells;

    @FindBy(xpath = "//div[contains(@class, 'conversion-cell')]")
    private List<WebElement> conversionCells;

    @FindBy(xpath = "//div[contains(@class, 'confidence-cell')]")
    private List<WebElement> confidenceCells;

    @FindBy(xpath = "//div[contains(@class, 'visitors-cell')]")
    private List<WebElement> visitorsCells;

    @FindBy(xpath = "//div[contains(@class, 'revenue-cell')]")
    private List<WebElement> revenueCells;

    @FindBy(xpath = "//div[contains(@class, 'schedule-report-btn')]")
    private WebElement scheduleReportBtn;

    @FindBy(id = "email-recipients-input")
    private WebElement emailRecipientsInput;

    @FindBy(id = "schedule-frequency-select")
    private WebElement scheduleFrequencySelect;

    @FindBy(id = "confirm-schedule-btn")
    private WebElement confirmScheduleBtn;

    @FindBy(xpath = "//div[contains(@class, 'report-error')]")
    private WebElement reportError;

    @FindBy(xpath = "//div[contains(@class, 'loading-spinner')]")
    private WebElement loadingSpinner;

    public ReportsPage(WebDriver driver) {
        super(driver);
    }

    public boolean isReportsPageDisplayed() {
        return isElementDisplayed(reportsHeader);
    }

    public int getReportCardCount() {
        return reportCards.size();
    }

    public void setDateRange(String dateRange) {
        sendKeys(dateRangePicker, dateRange);
    }

    public void exportToCSV() {
        clickElement(exportCsvBtn);
    }

    public void exportToPDF() {
        clickElement(exportPdfBtn);
    }

    public boolean isConversionSummaryDisplayed() {
        return isElementDisplayed(conversionSummary);
    }

    public boolean isExperimentPerformanceDisplayed() {
        return isElementDisplayed(experimentPerformance);
    }

    public int getExperimentsTableRowCount() {
        return experimentsTableRows.size();
    }

    public List<String> getTableHeaders() {
        return experimentsTableHeaders.stream().map(this::getText).toList();
    }

    public void filterByReportType(String reportType) {
        selectByVisibleText(reportFilterSelect, reportType);
    }

    public void filterByStatus(String status) {
        selectByVisibleText(statusFilterSelect, status);
    }

    public void filterByProject(String projectName) {
        selectByVisibleText(projectFilterSelect, projectName);
    }

    public void applyFilters() {
        clickElement(applyFiltersBtn);
        waitForAjaxCompletion();
    }

    public boolean isChartDisplayed() {
        return isElementDisplayed(chartCanvas);
    }

    public String getExperimentNameFromRow(int rowIndex) {
        if (rowIndex < experimentNameCells.size()) {
            return getText(experimentNameCells.get(rowIndex));
        }
        return "";
    }

    public String getVariationFromRow(int rowIndex) {
        if (rowIndex < variationCells.size()) {
            return getText(variationCells.get(rowIndex));
        }
        return "";
    }

    public String getConversionRateFromRow(int rowIndex) {
        if (rowIndex < conversionCells.size()) {
            return getText(conversionCells.get(rowIndex));
        }
        return "";
    }

    public String getConfidenceFromRow(int rowIndex) {
        if (rowIndex < confidenceCells.size()) {
            return getText(confidenceCells.get(rowIndex));
        }
        return "";
    }

    public String getVisitorsFromRow(int rowIndex) {
        if (rowIndex < visitorsCells.size()) {
            return getText(visitorsCells.get(rowIndex));
        }
        return "";
    }

    public void scheduleReport(String recipients, String frequency) {
        clickElement(scheduleReportBtn);
        sendKeys(emailRecipientsInput, recipients);
        selectByVisibleText(scheduleFrequencySelect, frequency);
        clickElement(confirmScheduleBtn);
    }

    public boolean isReportErrorDisplayed() {
        return isElementDisplayed(reportError);
    }

    public boolean isLoadingComplete() {
        return !isElementDisplayed(loadingSpinner);
    }

    public int getSummaryStatCount() {
        return summaryStats.size();
    }
}
