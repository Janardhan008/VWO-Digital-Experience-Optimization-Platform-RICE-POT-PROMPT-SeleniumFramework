package constants;

public final class FrameworkConstants {

    private FrameworkConstants() {}

    public static final String PROJECT_NAME = "VWO Automation Framework";
    public static final String PROJECT_VERSION = "2.0.0";

    public static final String CONFIG_FILE_PATH = System.getProperty("configPath", "resources/config.properties");

    public static final String EXTENT_REPORT_PATH = "reports/extent-report.html";
    public static final String SCREENSHOT_DIR = "screenshots";
    public static final String LOG_DIR = "logs";

    public static final String TEST_DATA_RESOURCES = "src/main/resources/testdata";

    public static final int PAGE_LOAD_TIMEOUT = 60;
    public static final int IMPLICIT_WAIT = 10;
    public static final int EXPLICIT_WAIT = 30;
    public static final int POLLING_INTERVAL_MS = 500;

    public static final String SCREENSHOT_FORMAT = ".png";
    public static final String DATE_FORMAT = "yyyy-MM-dd_HH-mm-ss";
    public static final String REPORT_DATE_FORMAT = "dd-MMM-yyyy HH:mm:ss";

    public static final String[] BROWSER_SUPPORTED = {"chrome", "firefox", "edge"};
}
