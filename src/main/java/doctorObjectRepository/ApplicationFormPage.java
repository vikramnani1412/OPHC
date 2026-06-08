package doctorObjectRepository;

import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.awt.font.GlyphJustificationInfo;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import genericUtilities.JavaUtility;
import genericUtilities.WebDriverUtility;

public class ApplicationFormPage {

	//Finding WebElements Using @FindBy Annotations
	
    @FindBy(xpath="//input[@type='file']")private WebElement InputFile;
    
    @FindBy(xpath="//button[.=' Preview AI Look ']")private WebElement PreviewAILookBtn;
    
    @FindBy(xpath="//i[@class='fa-regular fa-trash-can']")private WebElement ProfilePicDeleteBtn;
    
    @FindBy(xpath="//h6[.='Doctor_profile_jpg']")private WebElement DrProfileNameScrolling;
    
    @FindBy(xpath="//input[@formcontrolname='fullName']")private WebElement FullNameEdt;
    
    @FindBy(xpath="//input[@formcontrolname='nmcNumber']")private WebElement NmcNumberEdt;
    
    @FindBy(xpath="//select[@formcontrolname='specialization']")private WebElement SpecializationDrpdwn;
    
    @FindBy(xpath="//select[@formcontrolname='experience']")private WebElement ExperianceDrpDwn;
    
    @FindBy(xpath="//select[@formcontrolname='qualification']")private WebElement QualificationDrpdwn;
    
    @FindBy(xpath="//select[@formcontrolname='stateCouncil']")private WebElement StateCouncilDrpdwn;
    
    @FindBy(xpath="//select[@formcontrolname='yearOfAdmission']")private WebElement YearOfAdmissionDrpdwn;
    
    @FindBy(xpath="//input[@formcontrolname='hospital']")private WebElement CurrentHospitalOrClinicEdt;
    
    @FindBy(xpath="//div[.=' Submit ']")private WebElement SubmitBtn;
    
//    @FindBy(xpath="//a[.=' Login ']")private WebElement LoginLnk;
  
	//Rule-3:Create a constructor to initilise these elements    
    
	public ApplicationFormPage(WebDriver driver)
	{
		PageFactory.initElements(driver, this);
	}


	//Rule-4:Provide getters to access these variables
	
	public WebElement getInputFile() {
		return InputFile;
	}

	public WebElement getPreviewAILookBtn() {
		return PreviewAILookBtn;
	}

	public WebElement getDrProfileNameScrolling() {
		return DrProfileNameScrolling;
	}
	
	public WebElement getProfilePicDeleteBtn() {
		return ProfilePicDeleteBtn;
	}

	public WebElement getFullNameEdt() {
		return FullNameEdt;
	}

	public WebElement getNmcNumberEdt() {
		return NmcNumberEdt;
	}

	public WebElement getSpecializationDrpdwn() {
		return SpecializationDrpdwn;
	}

	public WebElement getExperianceDrpDwn() {
		return ExperianceDrpDwn;
	}

	public WebElement getQualificationDrpdwn() {
		return QualificationDrpdwn;
	}

	public WebElement getStateCouncilDrpdwn() {
		return StateCouncilDrpdwn;
	}

	public WebElement getYearOfAdmissionDrpdwn() {
		return YearOfAdmissionDrpdwn;
	}

	public WebElement getCurrentHospitalOrClinicEdt() {
		return CurrentHospitalOrClinicEdt;
	}


	public WebElement getSubmitBtn() {
		return SubmitBtn;
	}
	
	
	
	//Business Library
	
   	public void uploadDoctorDetails(WebDriver driver, String ImagePath) throws Exception
    {
    	WebDriverUtility wUtil = new WebDriverUtility();
    	JavaUtility jUtil = new JavaUtility();
    	
    	InputFile.sendKeys(ImagePath);
    	Thread.sleep(1000);
    	
    	wUtil.clickOnEscapeButton();
		
    	Thread.sleep(1000);
    	
    	wUtil.scrollToParticularWebElement(driver, DrProfileNameScrolling);
    	
    	Thread.sleep(1000);
    	
    	String str = String.valueOf(jUtil.getRandomNum());
    	
    	NmcNumberEdt.sendKeys(str);
    	
    	Thread.sleep(1000);
    	
    	wUtil.handleDropdownByIndex(SpecializationDrpdwn, 2);
    	
    	Thread.sleep(1000);
    	
    	wUtil.handleDropdownByIndex(ExperianceDrpDwn, 3);
    	
    	Thread.sleep(1000);
    	wUtil.handleDropdownByIndex(QualificationDrpdwn, 4);
    	Thread.sleep(1000);
    	wUtil.handleDropdownByIndex(StateCouncilDrpdwn, 5);
    	Thread.sleep(1000);
    	wUtil.handleDropdownByIndex(YearOfAdmissionDrpdwn, 6);
    	Thread.sleep(1000);
    	CurrentHospitalOrClinicEdt.sendKeys("abcdef");
    	Thread.sleep(1000);
    	SubmitBtn.click();
//    	wUtil.scrollToParticularWebElement(driver, );
    
    }
}
