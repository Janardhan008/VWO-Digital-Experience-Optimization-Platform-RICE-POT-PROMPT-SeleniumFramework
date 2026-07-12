package base;

import constants.FrameworkConstants;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.Set;

public class BasePage {

    protected final WebDriver driver;
    protected final WebDriverWait wait;
    protected final WebDriverWait shortWait;
    protected final Actions actions;
    protected final JavascriptExecutor js;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(FrameworkConstants.EXPLICIT_WAIT));
        this.shortWait = new WebDriverWait(driver, Duration.ofSeconds(FrameworkConstants.IMPLICIT_WAIT));
        this.actions = new Actions(driver);
        this.js = (JavascriptExecutor) driver;
        PageFactory.initElements(driver, this);
    }

    protected void clickElement(WebElement element) {
        try {
            wait.until(ExpectedConditions.elementToBeClickable(element)).click();
        } catch (StaleElementReferenceException e) {
            wait.until(ExpectedConditions.elementToBeClickable(element)).click();
        }
    }

    protected void clickElementUsingJS(WebElement element) {
        js.executeScript("arguments[0].click();", element);
    }

    protected void sendKeys(WebElement element, String text) {
        WebElement el = wait.until(ExpectedConditions.visibilityOf(element));
        el.clear();
        el.sendKeys(text);
    }

    protected String getText(WebElement element) {
        return wait.until(ExpectedConditions.visibilityOf(element)).getText();
    }

    protected String getAttributeValue(WebElement element, String attribute) {
        return wait.until(ExpectedConditions.visibilityOf(element)).getAttribute(attribute);
    }

    protected boolean isElementDisplayed(WebElement element) {
        try {
            return wait.until(ExpectedConditions.visibilityOf(element)).isDisplayed();
        } catch (TimeoutException | NoSuchElementException e) {
            return false;
        }
    }

    protected boolean isElementEnabled(WebElement element) {
        return wait.until(ExpectedConditions.visibilityOf(element)).isEnabled();
    }

    protected boolean isElementSelected(WebElement element) {
        return wait.until(ExpectedConditions.visibilityOf(element)).isSelected();
    }

    protected void scrollToElement(WebElement element) {
        js.executeScript("arguments[0].scrollIntoView({behavior: 'instant', block: 'center'});", element);
    }

    protected void selectByVisibleText(WebElement element, String text) {
        new Select(wait.until(ExpectedConditions.visibilityOf(element))).selectByVisibleText(text);
    }

    protected void selectByValue(WebElement element, String value) {
        new Select(wait.until(ExpectedConditions.visibilityOf(element))).selectByValue(value);
    }

    protected void selectByIndex(WebElement element, int index) {
        new Select(wait.until(ExpectedConditions.visibilityOf(element))).selectByIndex(index);
    }

    protected void waitForPageLoad() {
        wait.until(driver -> js.executeScript("return document.readyState").equals("complete"));
    }

    protected void waitForAjaxCompletion() {
        wait.until(driver -> (Boolean) js.executeScript("return jQuery.active == 0"));
    }

    protected void waitForVisibility(WebElement element) {
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    protected void waitForInvisibility(WebElement element) {
        wait.until(ExpectedConditions.invisibilityOf(element));
    }

    protected void moveToElement(WebElement element) {
        actions.moveToElement(wait.until(ExpectedConditions.visibilityOf(element))).perform();
    }

    protected void doubleClick(WebElement element) {
        actions.doubleClick(wait.until(ExpectedConditions.elementToBeClickable(element))).perform();
    }

    protected void rightClick(WebElement element) {
        actions.contextClick(wait.until(ExpectedConditions.elementToBeClickable(element))).perform();
    }

    protected void dragAndDrop(WebElement source, WebElement target) {
        actions.dragAndDrop(
                wait.until(ExpectedConditions.visibilityOf(source)),
                wait.until(ExpectedConditions.visibilityOf(target))
        ).perform();
    }

    protected void acceptAlert() {
        wait.until(ExpectedConditions.alertIsPresent()).accept();
    }

    protected void dismissAlert() {
        wait.until(ExpectedConditions.alertIsPresent()).dismiss();
    }

    protected String getAlertText() {
        return wait.until(ExpectedConditions.alertIsPresent()).getText();
    }

    protected void switchToFrame(String frameNameOrId) {
        wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(frameNameOrId));
    }

    protected void switchToDefaultContent() {
        driver.switchTo().defaultContent();
    }

    protected void switchToWindow(String windowHandle) {
        driver.switchTo().window(windowHandle);
    }

    protected Set<String> getWindowHandles() {
        return driver.getWindowHandles();
    }

    protected String getCurrentWindowHandle() {
        return driver.getWindowHandle();
    }

    protected void switchToNewWindow() {
        String currentHandle = getCurrentWindowHandle();
        for (String handle : getWindowHandles()) {
            if (!handle.equals(currentHandle)) {
                switchToWindow(handle);
                break;
            }
        }
    }

    protected void closeCurrentWindow() {
        driver.close();
    }

    protected boolean isElementPresent(By locator) {
        try {
            driver.findElement(locator);
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    protected List<WebElement> getElements(By locator) {
        return driver.findElements(locator);
    }

    protected String getPageTitle() {
        return driver.getTitle();
    }

    protected String getCurrentUrl() {
        return driver.getCurrentUrl();
    }

    protected void refreshPage() {
        driver.navigate().refresh();
        waitForPageLoad();
    }

    protected void navigateTo(String url) {
        driver.navigate().to(url);
        waitForPageLoad();
    }

    protected void highlightElement(WebElement element) {
        js.executeScript("arguments[0].style.border='3px solid red'; arguments[0].style.backgroundColor='yellow';", element);
    }
}
