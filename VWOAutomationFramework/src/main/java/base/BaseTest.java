package base;

import constants.FrameworkConstants;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import utils.ConfigReader;
import utils.ScreenshotUtil;

import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.Arrays;

public class BaseTest {

    protected WebDriver driver;
    private static final ThreadLocal<WebDriver> driverThreadLocal = new ThreadLocal<>();
    private static final ThreadLocal<String> testNameThreadLocal = new ThreadLocal<>();

    @BeforeSuite
    public void beforeSuite() {
        System.out.println("========================================");
        System.out.println("  " + FrameworkConstants.PROJECT_NAME);
        System.out.println("  Version: " + FrameworkConstants.PROJECT_VERSION);
        System.out.println("========================================");
    }

    @BeforeMethod
    public void setup(Method method) {
        testNameThreadLocal.set(method.getName());
        String browser = ConfigReader.getBrowser().toLowerCase();
        boolean headless = ConfigReader.isHeadless();
        boolean remote = ConfigReader.isRemoteExecution();

        if (remote) {
            driver = createRemoteDriver(browser, headless);
        } else {
            driver = createLocalDriver(browser, headless);
        }

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(ConfigReader.getImplicitWait()));
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(FrameworkConstants.PAGE_LOAD_TIMEOUT));
        driver.manage().window().maximize();
        driverThreadLocal.set(driver);

        driver.get(ConfigReader.getAppUrl());
    }

    @AfterMethod
    public void teardown(ITestResult result) {
        String testName = testNameThreadLocal.get();
        if (ConfigReader.isScreenshotOnFailure() && !result.isSuccess()) {
            ScreenshotUtil.captureScreenshot(driver, testName + "_FAILED");
        }
        ScreenshotUtil.captureScreenshot(driver, testName);
        if (driver != null) {
            driver.quit();
            driverThreadLocal.remove();
        }
    }

    @AfterSuite
    public void afterSuite() {
        System.out.println("========================================");
        System.out.println("  Suite Execution Completed");
        System.out.println("========================================");
    }

    private WebDriver createLocalDriver(String browser, boolean headless) {
        switch (browser) {
            case "chrome": {
                ChromeOptions options = new ChromeOptions();
                options.addArguments("--start-maximized");
                options.addArguments("--disable-notifications");
                options.addArguments("--disable-popup-blocking");
                options.addArguments("--disable-infobars");
                if (headless) {
                    options.addArguments("--headless=new");
                }
                return new ChromeDriver(options);
            }
            case "firefox": {
                FirefoxOptions options = new FirefoxOptions();
                options.addArguments("--start-maximized");
                if (headless) {
                    options.addArguments("--headless");
                }
                return new FirefoxDriver(options);
            }
            case "edge": {
                EdgeOptions options = new EdgeOptions();
                options.addArguments("--start-maximized");
                options.addArguments("--disable-notifications");
                if (headless) {
                    options.addArguments("--headless=new");
                }
                return new EdgeDriver(options);
            }
            default:
                throw new IllegalArgumentException("Unsupported browser: " + browser
                        + ". Supported: " + Arrays.toString(FrameworkConstants.BROWSER_SUPPORTED));
        }
    }

    private WebDriver createRemoteDriver(String browser, boolean headless) {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setBrowserName(browser);
        if (headless) {
            capabilities.setCapability("headless", true);
        }
        try {
            return new RemoteWebDriver(new URL(ConfigReader.getValue("selenium.grid.url")), capabilities);
        } catch (MalformedURLException e) {
            throw new RuntimeException("Invalid Selenium Grid URL", e);
        }
    }

    public static WebDriver getDriver() {
        return driverThreadLocal.get();
    }

    public static String getTestName() {
        return testNameThreadLocal.get();
    }

    protected void navigateToApp() {
        driver.get(ConfigReader.getAppUrl());
    }
}
