package genericUtilities;

/**
 * ConstantsUtility — Single source of truth for all hardcoded values.
 *
 * BaseClass uses this as: Constants.SCREENSHOT_PATH, Constants.IMPLICIT_WAIT etc.
 * So either:
 *   (a) Rename this class to "Constants"  ← matches BaseClass as-is
 *   (b) OR do a find+replace in BaseClass: Constants → ConstantsUtility
 */
public class ConstantsUtility {

    // ═══════════════════════════════════════════════
    // FILE PATHS  — used in BaseClass @BeforeSuite
    // ═══════════════════════════════════════════════

    /** Property file — browser, url, credentials */
    public static final String PROPERTY_FILE_PATH   = ".\\src\\test\\resources\\CommonData.properties";

    /** Excel file — all test data sheets */
    public static final String EXCEL_FILE_PATH      = ".\\src\\test\\resources\\OPHC Automation Excel.xlsx";

    /**
     * Screenshots folder.
     * BaseClass uses: new File(Constants.SCREENSHOT_PATH)
     * and: wUtility.takeScreenshot(driver, screenshotName)
     */
    public static final String SCREENSHOT_PATH      = ".\\Screenshots\\";

    /**
     * Reports folder.
     * BaseClass opens: Constants.REPORTS_PATH + "index.html"
     */
    public static final String REPORTS_PATH         = ".\\Reports\\";

    /** Log4j logs folder */
    public static final String LOG_PATH             = ".\\Logs\\";

    // ═══════════════════════════════════════════════
    // TIMEOUT VALUES  (seconds)
    // BaseClass uses: Constants.IMPLICIT_WAIT, Constants.PAGE_LOAD_TIMEOUT
    // ═══════════════════════════════════════════════

    /** driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(IMPLICIT_WAIT)) */
    public static final int IMPLICIT_WAIT           = 10;

    /** WebDriverWait duration */
    public static final int EXPLICIT_WAIT           = 15;

    /** driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(PAGE_LOAD_TIMEOUT)) */
    public static final int PAGE_LOAD_TIMEOUT       = 30;

    public static final int SCRIPT_TIMEOUT          = 20;

    // ═══════════════════════════════════════════════
    // BROWSER NAMES  — match values in property file
    // BaseClass switch: case "chrome", "firefox", "edge"
    // ═══════════════════════════════════════════════

    public static final String CHROME               = "chrome";
    public static final String FIREFOX              = "firefox";
    public static final String EDGE                 = "edge";

    // ═══════════════════════════════════════════════
    // PROPERTY FILE KEYS
    // BaseClass reads: pUtility.readDataFromPropertyFile("browser")
    //                  pUtility.readDataFromPropertyFile("url")
    //                  pUtility.readDataFromPropertyFile("mobilenumber")
    // ═══════════════════════════════════════════════

    public static final String KEY_BROWSER          = "browser";
    public static final String KEY_URL              = "url";
    public static final String KEY_DOCTOR_URL       = "doctorurl";
    public static final String KEY_ADMIN_URL        = "adminurl";
    public static final String KEY_PATIENT_URL      = "patienturl";
    public static final String KEY_MOBILE_NUMBER    = "mobilenumber";
    public static final String KEY_VALID_OTP        = "validotp";

    // ═══════════════════════════════════════════════
    // APPLICATION URLs  (fallback if property file missing)
    // ═══════════════════════════════════════════════

    public static final String STAGING_URL          = "https://stg-doctor.ophc.in/landing/Homepage";
    public static final String ADMIN_URL            = "https://stg-admin.ophc.in";
    public static final String PATIENT_URL          = "https://stg-patient.ophc.in";

    // ═══════════════════════════════════════════════
    // EXCEL SHEET NAMES
    // ═══════════════════════════════════════════════

    public static final String LOGIN_SHEET          = "LoginData";
    public static final String DOCTOR_SHEET         = "DoctorData";
    public static final String PATIENT_SHEET        = "PatientData";
    public static final String APPOINTMENT_SHEET    = "AppointmentData";
    public static final String REGISTRATION_SHEET   = "RegistrationData";

    // ═══════════════════════════════════════════════
    // EXCEL COLUMN INDEX  (0-based)
    // ═══════════════════════════════════════════════

    public static final int COL_USERNAME            = 0;
    public static final int COL_PASSWORD            = 1;
    public static final int COL_MOBILE              = 2;
    public static final int COL_OTP                 = 3;
    public static final int COL_EMAIL               = 4;
    public static final int COL_EXPECTED_RESULT     = 5;

    // ═══════════════════════════════════════════════
    // EXCEL ROW INDEX  (0-based; row 0 = header)
    // ═══════════════════════════════════════════════

    public static final int HEADER_ROW              = 0;
    public static final int DATA_ROW_START          = 1;

    // ═══════════════════════════════════════════════
    // TEST DATA  — OTP values used in VerifyCodePage
    // BaseClass calls: vcPage.enteringOtpAndClickOnVerifyBtn()
    // ═══════════════════════════════════════════════

    public static final String VALID_OTP            = "123456";   // staging OTP only
    public static final String INVALID_OTP          = "0000";
    public static final String INVALID_MOBILE       = "9999999999";
    public static final String EMPTY_STRING         = "";

    // ═══════════════════════════════════════════════
    // UI MESSAGES  — for assertion/wait text checks
    // ═══════════════════════════════════════════════

    public static final String LOGIN_SUCCESS_MSG    = "Welcome";
    public static final String LOGOUT_SUCCESS_MSG   = "Logged out successfully";
    public static final String OTP_SENT_MSG         = "OTP sent successfully";
    public static final String INVALID_OTP_MSG      = "Invalid OTP";
    public static final String SESSION_EXPIRED_MSG  = "Session expired";

    // ═══════════════════════════════════════════════
    // SCREENSHOT FORMAT
    // BaseClass builds: className + "_" + testName + "_" + timestamp
    // ═══════════════════════════════════════════════

    public static final String SCREENSHOT_EXT       = ".png";

    // ═══════════════════════════════════════════════
    // RETRY COUNT  (for RetryAnalyzer)
    // ═══════════════════════════════════════════════

    public static final int RETRY_COUNT             = 1;

    // ═══════════════════════════════════════════════
    // Prevent instantiation — utility class only
    // ═══════════════════════════════════════════════
    private ConstantsUtility() {}
}