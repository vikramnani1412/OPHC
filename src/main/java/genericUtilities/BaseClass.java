package genericUtilities;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.testng.ITestResult;
import org.testng.annotations.*;

import doctorObjectRepository.LoginPage;
import doctorObjectRepository.LogoutPage;
import doctorObjectRepository.VerifyCodePage;
import doctorObjectRepository.WelcomePage;
import io.github.bonigarcia.wdm.WebDriverManager;

/**
 * BaseClass — Best-of-both merged framework base.
 *
 * Features included:
 *  1.  Property file driven config (browser, url, credentials)
 *  2.  @Parameters + @Optional fallback for browser/url override via testng.xml
 *  3.  Thread-safe driver via ThreadLocal (safe for parallel execution)
 *  4.  Static sDriver for cross-class access (e.g., custom listeners)
 *  5.  Rich browser options — Chrome (notifications off, remote origins),
 *      Firefox (geo profile), Edge (notifications off), headless support
 *  6.  alwaysRun = true on all lifecycle hooks
 *  7.  Timestamp on every run for reports and screenshots
 *  8.  Auto-create screenshots + reports folders if missing
 *  9.  Screenshot captured on test FAILURE with class+method+timestamp name
 * 10.  Console logging: PASSED / FAILED / SKIPPED with reason and time
 * 11.  App-specific login in @BeforeMethod, logout in @AfterMethod
 * 12.  Implicit wait + page load timeout configurable via Constants
 * 13.  Auto-open HTML report after suite completes
 * 14.  Graceful browser close with null check in @AfterClass
 * 15.  Clear suite start/end banners in console
 */
public class BaseClass {

    // ─────────────────────────────────────────────
    // Utilities — accessible by all child classes
    // ─────────────────────────────────────────────
    protected JavaUtility         jUtility = new JavaUtility();
    protected WebDriverUtility    wUtility = new WebDriverUtility();
    protected ExcelFileUtility    eUtility = new ExcelFileUtility();
    protected PropertyFileUtility pUtility = new PropertyFileUtility();

    // ─────────────────────────────────────────────
    // Driver — ThreadLocal for parallel safety
    // ─────────────────────────────────────────────
    private static ThreadLocal<WebDriver> tlDriver = new ThreadLocal<>();

    /** Use this in tests and page objects */
    public WebDriver driver;

    /** Static reference for listeners / utility classes that can't access instance */
    public static WebDriver sDriver;

    // ─────────────────────────────────────────────
    // Timestamp — used for screenshots and reports
    // ─────────────────────────────────────────────
    public String timestamp = LocalDateTime.now()
        .format(DateTimeFormatter.ofPattern("yyyy-MM-dd_HH-mm-ss"));

    // ══════════════════════════════════════════════════════════════
    // @BeforeSuite — Runs ONCE before the entire suite
    // ══════════════════════════════════════════════════════════════
    @BeforeSuite(alwaysRun = true)
    public void beforeSuite() {
        System.out.println("\n╔══════════════════════════════════════════╗");
        System.out.println(  "║        TEST SUITE EXECUTION START        ║");
        System.out.println(  "║        " + timestamp + "           ║");
        System.out.println(  "╚══════════════════════════════════════════╝\n");

        // Auto-create screenshots folder
        File screenshotDir = new File(ConstantsUtility.SCREENSHOT_PATH);
        if (!screenshotDir.exists()) {
            screenshotDir.mkdirs();
            System.out.println("📁 Screenshots folder created : " + ConstantsUtility.SCREENSHOT_PATH);
        }

        // Auto-create reports folder
        File reportsDir = new File(ConstantsUtility.REPORTS_PATH);
        if (!reportsDir.exists()) {
            reportsDir.mkdirs();
            System.out.println("📁 Reports folder created     : " + ConstantsUtility.REPORTS_PATH);
        }

        System.out.println("✅ BeforeSuite setup complete.\n");
    }

    // ══════════════════════════════════════════════════════════════
    // @BeforeClass — Browser launch (once per test class)
    // Reads browser + URL from property file.
    // testng.xml can override via <parameter> tags.
    // ══════════════════════════════════════════════════════════════
    @Parameters({"browser", "url"})
    @BeforeClass(alwaysRun = true)
    public void beforeClass(
        @Optional("") String browserParam,
        @Optional("") String urlParam
    ) throws Exception {

        // Property file is the default; testng.xml parameter overrides if provided
        String browser = browserParam.isEmpty()
            ? pUtility.readDataFromPropertyFile("browser")
            : browserParam;

        String url = urlParam.isEmpty()
            ? pUtility.readDataFromPropertyFile("url")
            : urlParam;

        // Launch and configure browser
        driver = initializeBrowser(browser);

        if (driver == null) {
            throw new RuntimeException("❌ Browser initialization failed for: " + browser);
        }

        // Store in ThreadLocal for parallel-safe access
        tlDriver.set(driver);
        sDriver = driver;

        driver.manage().window().maximize();
        driver.manage().deleteAllCookies();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(ConstantsUtility.IMPLICIT_WAIT));
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(ConstantsUtility.PAGE_LOAD_TIMEOUT));

        driver.get(url);

        System.out.println("✅ Browser launched  : " + browser.toUpperCase());
        System.out.println("✅ URL opened        : " + url);
        System.out.println("📌 Class started     : " + this.getClass().getSimpleName());
    }

    // ══════════════════════════════════════════════════════════════
    // @BeforeMethod — Login before each test
    // Override in child class to add app-specific login steps.
    // ══════════════════════════════════════════════════════════════
    @BeforeMethod(alwaysRun = true)
    public void beforeMethod(Method method) throws Exception {
        System.out.println("\n▶  Test started  : " + method.getName());
        System.out.println("   Time          : "
            + LocalDateTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss")));

        // ── App login steps ────────────────────────────────────────
        // Read credentials from property file
        String mobileNumber = pUtility.readDataFromPropertyFile("mobilenumber");

        // Step 1: Enter mobile number and request OTP
        LoginPage lPage = new LoginPage(driver);
        lPage.loginToDoctor(mobileNumber);

        // Step 2: Enter OTP and verify
        VerifyCodePage vcPage = new VerifyCodePage(driver);
        vcPage.enteringOtpAndClickOnVerifyBtn();

        System.out.println("✅ Login successful  : " + method.getName());
        // ──────────────────────────────────────────────────────────
    }

    // ══════════════════════════════════════════════════════════════
    // @AfterMethod — Screenshot on failure + Logout after each test
    // ══════════════════════════════════════════════════════════════
    @AfterMethod(alwaysRun = true)
    public void afterMethod(ITestResult result) throws Exception {

        String testName  = result.getName();
        String className = result.getTestClass().getRealClass().getSimpleName();

        // ── Result logging ────────────────────────────────────────
        switch (result.getStatus()) {

            case ITestResult.SUCCESS:
                System.out.println("✅ PASSED   : " + testName);
                break;

            case ITestResult.FAILURE:
                System.out.println("❌ FAILED   : " + testName);
                System.out.println("   Reason   : " + result.getThrowable().getMessage());

                // Capture screenshot on failure
                String screenshotName = className + "_" + testName + "_" + timestamp;
                wUtility.takeScreenShot(driver, screenshotName);
                System.out.println("📸 Screenshot saved : " + screenshotName);
                break;

            case ITestResult.SKIP:
                System.out.println("⚠️  SKIPPED  : " + testName);
                if (result.getThrowable() != null) {
                    System.out.println("   Reason   : " + result.getThrowable().getMessage());
                }
                break;
        }

        System.out.println("   Ended at : "
            + LocalDateTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss")));

        // ── App logout steps ──────────────────────────────────────
        try {
            WelcomePage wPage = new WelcomePage(driver);
            wPage.getProfileImg().click();
            Thread.sleep(800);

            LogoutPage logoutPage = new LogoutPage(driver);
            logoutPage.logoutOfApplication();

            System.out.println("✅ Logout successful : " + testName);

        } catch (Exception e) {
            System.out.println("⚠️  Logout failed    : " + e.getMessage());
            // Do not rethrow — allow @AfterClass to still close the browser
        }
        // ──────────────────────────────────────────────────────────
    }

    // ══════════════════════════════════════════════════════════════
    // @AfterClass — Close browser (once per test class)
    // ══════════════════════════════════════════════════════════════
    @AfterClass(alwaysRun = true)
    public void afterClass() {
        try {
            if (driver != null) {
                driver.quit();
                tlDriver.remove();  // prevent ThreadLocal memory leak
                System.out.println("✅ Browser closed   : " + this.getClass().getSimpleName());
            }
        } catch (Exception e) {
            System.out.println("⚠️  Error closing browser : " + e.getMessage());
        }
    }

    // ══════════════════════════════════════════════════════════════
    // @AfterSuite — Final banner + auto-open HTML report
    // ══════════════════════════════════════════════════════════════
    @AfterSuite(alwaysRun = true)
    public void afterSuite() {
        System.out.println("\n╔══════════════════════════════════════════╗");
        System.out.println(  "║        TEST SUITE EXECUTION END          ║");
        System.out.println(  "╚══════════════════════════════════════════╝");

        openReport();
    }

    // ══════════════════════════════════════════════════════════════
    // UTILITY: ThreadLocal getter
    // Use BaseClass.getDriver() from page objects if needed
    // ══════════════════════════════════════════════════════════════
    public static WebDriver getDriver() {
        return tlDriver.get();
    }

    // ══════════════════════════════════════════════════════════════
    // PRIVATE: Browser factory with rich options
    // ══════════════════════════════════════════════════════════════
    private WebDriver initializeBrowser(String browser) {

        WebDriver wd;

        switch (browser.toLowerCase()) {

            case "chrome":
                ChromeOptions chromeOptions = new ChromeOptions();
                chromeOptions.addArguments("--remote-allow-origins=*");
                chromeOptions.addArguments("--disable-notifications");
                chromeOptions.addArguments("--disable-popup-blocking");
                // chromeOptions.addArguments("--headless=new");   // uncomment for headless
                // chromeOptions.addArguments("--window-size=1920,1080");
                WebDriverManager.chromedriver().setup();
                wd = new ChromeDriver(chromeOptions);
                System.out.println("✅ Chrome browser launched.");
                break;

            case "firefox":
                FirefoxProfile profile = new FirefoxProfile();
                profile.setPreference("geo.prompt.testing", true);
                profile.setPreference("geo.prompt.testing.allow", false);
                profile.setPreference("dom.webnotifications.enabled", false);
                FirefoxOptions firefoxOptions = new FirefoxOptions();
                firefoxOptions.setProfile(profile);
                // firefoxOptions.addArguments("--headless");  // uncomment for headless
                WebDriverManager.firefoxdriver().setup();
                wd = new FirefoxDriver(firefoxOptions);
                System.out.println("✅ Firefox browser launched.");
                break;

            case "edge":
                EdgeOptions edgeOptions = new EdgeOptions();
                edgeOptions.addArguments("--disable-notifications");
                edgeOptions.addArguments("--remote-allow-origins=*");
                // edgeOptions.addArguments("--headless=new");  // uncomment for headless
                WebDriverManager.edgedriver().setup();
                wd = new EdgeDriver(edgeOptions);
                System.out.println("✅ Edge browser launched.");
                break;

            default:
                throw new IllegalArgumentException(
                    "❌ Unsupported browser: '" + browser
                    + "'. Valid options: chrome, firefox, edge");
        }

        return wd;
    }

    // ══════════════════════════════════════════════════════════════
    // PRIVATE: Auto-open HTML report after suite
    // ══════════════════════════════════════════════════════════════
    private void openReport() {
        try {
            File reportFile = new File(ConstantsUtility.REPORTS_PATH + "index.html");
            if (reportFile.exists() && Desktop.isDesktopSupported()) {
                Desktop.getDesktop().open(reportFile);
                System.out.println("📊 Report opened : " + reportFile.getAbsolutePath());
            } else {
                System.out.println("⚠️  Report file not found at : "
                    + reportFile.getAbsolutePath());
            }
        } catch (IOException e) {
            System.out.println("⚠️  Could not open report : " + e.getMessage());
        }
    }
}
