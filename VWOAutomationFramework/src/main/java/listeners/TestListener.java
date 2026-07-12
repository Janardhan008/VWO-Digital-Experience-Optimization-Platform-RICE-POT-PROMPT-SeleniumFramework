package listeners;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import constants.FrameworkConstants;
import org.testng.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class TestListener implements ITestListener, ISuiteListener {

    private static ExtentReports extent;
    private static final ThreadLocal<ExtentTest> extentTestThreadLocal = new ThreadLocal<>();

    @Override
    public void onStart(ISuite suite) {
        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss"));
        String reportPath = FrameworkConstants.EXTENT_REPORT_PATH.replace(".html", "_" + timestamp + ".html");

        ExtentSparkReporter sparkReporter = new ExtentSparkReporter(reportPath);
        sparkReporter.config().setTheme(Theme.DARK);
        sparkReporter.config().setDocumentTitle("VWO Automation Report");
        sparkReporter.config().setReportName("VWO - Digital Experience Optimization Platform");
        sparkReporter.config().setTimeStampFormat(FrameworkConstants.REPORT_DATE_FORMAT);

        extent = new ExtentReports();
        extent.attachReporter(sparkReporter);
        extent.setSystemInfo("Application", "VWO");
        extent.setSystemInfo("Environment", System.getProperty("env", "QA"));
        extent.setSystemInfo("Browser", System.getProperty("browser", "Chrome"));
        extent.setSystemInfo("OS", System.getProperty("os.name"));
        extent.setSystemInfo("Java Version", System.getProperty("java.version"));
        extent.setSystemInfo("User", System.getProperty("user.name"));
    }

    @Override
    public void onFinish(ISuite suite) {
        if (extent != null) {
            extent.flush();
        }
    }

    @Override
    public void onTestStart(ITestResult result) {
        ExtentTest test = extent.createTest(result.getMethod().getDescription() != null
                ? result.getMethod().getDescription()
                : result.getMethod().getMethodName());
        test.assignCategory(result.getTestClass().getName());
        extentTestThreadLocal.set(test);
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        ExtentTest test = extentTestThreadLocal.get();
        if (test != null) {
            test.log(Status.PASS, "Test Passed: " + result.getMethod().getMethodName());
        }
    }

    @Override
    public void onTestFailure(ITestResult result) {
        ExtentTest test = extentTestThreadLocal.get();
        if (test != null) {
            test.log(Status.FAIL, "Test Failed: " + result.getMethod().getMethodName());
            test.log(Status.FAIL, result.getThrowable());
        }
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        ExtentTest test = extentTestThreadLocal.get();
        if (test != null) {
            test.log(Status.SKIP, "Test Skipped: " + result.getMethod().getMethodName());
        }
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
        // Not implemented
    }
}
