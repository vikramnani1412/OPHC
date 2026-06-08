package Doctor;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import adminObjectRepository.AdminDashboardPage;
import adminObjectRepository.AdminLoginPage;
import adminObjectRepository.DrIdentityProofPage;
import adminObjectRepository.DrKycManagementPage;
import doctorObjectRepository.ApplicationFormPage;
import doctorObjectRepository.DocumentUploadPage;
import doctorObjectRepository.LoginPage;
import doctorObjectRepository.ProfileUnderVerificationPage;
import doctorObjectRepository.RegisterPage;
import doctorObjectRepository.VerifyCodePage;
import genericUtilities.DataStore;
import genericUtilities.ExcelFileUtility;
import genericUtilities.JavaUtility;
import genericUtilities.PropertyFileUtility;
import genericUtilities.WebDriverUtility;
import io.github.bonigarcia.wdm.WebDriverManager;

public class Registration {

    WebDriverUtility wUtil = new WebDriverUtility();
    JavaUtility jUtil = new JavaUtility();
    ExcelFileUtility eUtil = new ExcelFileUtility();
    PropertyFileUtility pUtil = new PropertyFileUtility();

    
    String mobileNumber = jUtil.getRandomMobileNum();
    private String fakeName;

    @Test(priority = 1)
    public void DoctorRegistration() throws Exception {

        String URL = pUtil.readDataFromPropertyFile("doctorurl");

        String ImagePath = eUtil.readDataFromExcel("Doctor", 4, 1);
        String MedicalCertificate = eUtil.readDataFromExcel("Doctor", 5, 1);
        String NMCcertificate = eUtil.readDataFromExcel("Doctor", 6, 1);
        String Aadhar = eUtil.readDataFromExcel("Doctor", 7, 1);
        String Pan = eUtil.readDataFromExcel("Doctor", 8, 1);
        String Experiance = eUtil.readDataFromExcel("Doctor", 9, 1);
        String AffiliationProof = eUtil.readDataFromExcel("Doctor", 10, 1);

        fakeName = jUtil.getRandomSingleName().trim().split(" ")[0];
        String firstName = jUtil.getRandomSingleName().trim().split(" ")[0];

//        String mobileNumber = jUtil.getRandomMobileNum();

        DataStore.doctorName = fakeName;
        DataStore.mobileNumber = mobileNumber;
        DataStore.email = firstName + "@gmail.com";

        WebDriverManager.chromedriver().setup();

        WebDriver driver = new ChromeDriver();

        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));

        driver.get(URL);

        LoginPage lPage = new LoginPage(driver);

        wUtil.waitForElementToBeClickable(driver, lPage.getRegisterLnk());

        lPage.getRegisterLnk().click();

        RegisterPage rPage = new RegisterPage(driver);

        rPage.RegisterToDoctorApplication(driver, fakeName, firstName + "@gmail.com", mobileNumber);

        System.out.println("Doctor = " + fakeName);

        VerifyCodePage vPage = new VerifyCodePage(driver);
        vPage.enteringOtpAndClickOnVerifyBtn();

        ApplicationFormPage afPage = new ApplicationFormPage(driver);
        afPage.uploadDoctorDetails(driver, ImagePath);

        Thread.sleep(2000);

        wUtil.scrollPageUp(2);

        driver.findElement(By.xpath("//span[.='Medical Degree  Certificate']/../preceding-sibling::input")).sendKeys(MedicalCertificate);

        Thread.sleep(1000);

        driver.findElement(By.xpath("//span[.='NMC / State Medical Council Certificate']/../preceding-sibling::input")).sendKeys(NMCcertificate);

        Thread.sleep(1000);

        driver.findElement(By.xpath("//span[.='Aadhaar Card']/../preceding-sibling::input")).sendKeys(Aadhar);

        Thread.sleep(1000);

        driver.findElement(By.xpath("//span[.='PAN Card']/../preceding-sibling::input")).sendKeys(Pan);

        Thread.sleep(1000);

        driver.findElement(By.xpath("//span[.='Experience  Certificate']/../preceding-sibling::input")).sendKeys(Experiance);

        Thread.sleep(1000);

        driver.findElement(By.xpath("//span[.='Clinic / Hospital  Affiliation Proof']/../preceding-sibling::input")).sendKeys(AffiliationProof);

        Thread.sleep(1000);

        DocumentUploadPage duPage = new DocumentUploadPage(driver);

        duPage.documentsUploading(driver, MedicalCertificate, NMCcertificate, Aadhar, Pan, Experiance, AffiliationProof);

        ProfileUnderVerificationPage pufPage = new ProfileUnderVerificationPage(driver);

        pufPage.clickOnLogoutBtn(driver);

        driver.quit();
    }

    @Test(priority = 2)
    public void LoginToAdminAndApproveNewlyAddedDoctor() throws Throwable {

        String URL = pUtil.readDataFromPropertyFile("adminurl");
        String USERNAME = pUtil.readDataFromPropertyFile("username");
        String PASSWORD = pUtil.readDataFromPropertyFile("password");
        
        String FrstRating = eUtil.readDataFromExcel("Doctor", 11, 1);
        String ConsultancyFee = eUtil.readDataFromExcel("Doctor", 12, 1);
        
        String EditFrstRating = eUtil.readDataFromExcel("Doctor", 11, 2);
        String EditConsultancyFee = eUtil.readDataFromExcel("Doctor", 12, 2);
        
        String ReasonForRejection = eUtil.readDataFromExcel("Doctor", 3, 3);
        
        int DoctorNumber=1;

        WebDriverManager.chromedriver().setup();

        WebDriver driver = new ChromeDriver();

        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));

        driver.get(URL);

//        System.out.println("Admin = " + DataStore.doctorName);

        AdminLoginPage alPage = new AdminLoginPage(driver);

        alPage.loginToAdmin(USERNAME, PASSWORD);

        AdminDashboardPage adPage = new AdminDashboardPage(driver);

        adPage.clickOnDoctorIcon(driver);

        DrKycManagementPage kycmngPage = new DrKycManagementPage(driver);
        
        kycmngPage.ComparingNewlyRegisteredDoctorAndFirstDoctorInAdminPannelAndClickPreviewBtn(driver, DataStore.doctorName, DoctorNumber);

        DrIdentityProofPage dipPage = new DrIdentityProofPage(driver);
        dipPage.checkingEveryDocumentDoctorUploadedAndGivingFeeAndRating(driver, FrstRating, ConsultancyFee);
        
        // Editing Given Price and Rating
        Thread.sleep(3000);
        
        kycmngPage.clickOnFrstDoctorPreviewButton(driver);
        
        Thread.sleep(2000);
        
        dipPage.editDocumentDoctorUploadedAndGivingFeeAndRating(driver, EditFrstRating, EditConsultancyFee);
        
        Thread.sleep(2000);
        
        kycmngPage.clickOnFrstDoctorPreviewButton(driver);
        
        Thread.sleep(2000);
        
        dipPage.doctorRejecting(driver, ReasonForRejection);
        
        
        
//        driver.quit();
    }
    
    
}
