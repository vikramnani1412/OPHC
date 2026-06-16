package doctorObjectRepository;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import genericUtilities.JavaUtility;
import genericUtilities.WebDriverUtility;

public class ApplicationFormPage {

	//Finding WebElements Using @FindBy Annotations
	
    @FindBy(xpath="//button[.='Choose File']")private WebElement ChooseFileBtn;
    
    @FindBy(xpath="//input[@type='file']")private WebElement InputFile;
    
    @FindBy(xpath="//button[.=' Preview AI Look ']")private WebElement PreviewAILookBtn;
    
    @FindBy(xpath="//i[@class='fa-regular fa-trash-can']")private WebElement ProfilePicDeleteBtn;
    
    @FindBy(xpath="//h6[.='Doctor_profile_jpg']")private WebElement DrProfileNameScrolling;
    
    @FindBy(xpath="//input[@formcontrolname='fullName']")private WebElement FullNameEdt;
    
    @FindBy(xpath="//input[@formcontrolname='nmcNumber']")private WebElement NmcNumberEdt;
    
    @FindBy(xpath="(//div/div/div[.=' Select Specialization '])[2]")private WebElement SpecializationDrpdwn;
    
    @FindBy(xpath="(//div[.=' Select Experience '])[2]")private WebElement ExperianceDrpDwn;
    
    @FindBy(xpath="(//div[.=' Select Qualification '])[2]")private WebElement QualificationDrpdwn;
    
    @FindBy(xpath="(//div[.=' Select State Council '])[2]")private WebElement StateCouncilDrpdwn;
    
    @FindBy(xpath="(//div[.=' Select Year '])[2]")private WebElement YearOfAdmissionDrpdwn;
    
    @FindBy(xpath="//input[@formcontrolname='hospital']")private WebElement CurrentHospitalOrClinicEdt;
    
    @FindBy(xpath="//h6[.='After (AI Enhanced)']/following-sibling::img")private WebElement AiImage;
  
    @FindBy(xpath="//div[.=' Submit ']")private WebElement SubmitBtn;
    
//    @FindBy(xpath="//a[.=' Login ']")private WebElement LoginLnk;
  
	//Rule-3:Create a constructor to initilise these elements    
    
	public ApplicationFormPage(WebDriver driver)
	{
		PageFactory.initElements(driver, this);
	}


	//Rule-4:Provide getters to access these variables
	
	public WebElement getChooseFileBtn() {
		return ChooseFileBtn;
	}
	
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


	public WebElement getAiImage() {
		return AiImage;
	}


	public WebElement getSubmitBtn() {
		return SubmitBtn;
	}
	
	
	//Business Library
	
   	public void uploadDoctorDetails(WebDriver driver, String ImagePath) throws Exception
    {
    	WebDriverUtility wUtil = new WebDriverUtility();
    	JavaUtility jUtil = new JavaUtility();
    	
        driver.findElement(By.xpath("//button[.='Choose File']")).click();
        
        Thread.sleep(2000);
        
        driver.findElement(By.xpath("//input[@type='file']")).sendKeys(ImagePath);
        
        Thread.sleep(1000);
    	
    	wUtil.clickOnEscapeButton();
		
    	Thread.sleep(1000);
    	
    	wUtil.scrollToParticularWebElement(driver, DrProfileNameScrolling);
    	
    	Thread.sleep(1000);
    	
    	String str = String.valueOf(jUtil.getRandomNum());
    	
    	NmcNumberEdt.sendKeys(str);
    	
    	Thread.sleep(1000);
    	    	
    	SpecializationDrpdwn.click();
    	Thread.sleep(1000);
    	driver.findElement(By.xpath("//li[.=' two ']")).click();
    	
    	ExperianceDrpDwn.click();
    	Thread.sleep(1000);
    	driver.findElement(By.xpath("//li[.=' 3 years ']")).click();
    	
    	QualificationDrpdwn.click();
    	Thread.sleep(1000);
    	driver.findElement(By.xpath("//li[.=' MS - Robotic Surgery ']")).click();
    	
    	StateCouncilDrpdwn.click();
    	Thread.sleep(1000);
    	driver.findElement(By.xpath("//li[.=' Uttarakhand Medical Council (UKMC) ']")).click();
    	
    	YearOfAdmissionDrpdwn.click();
    	Thread.sleep(1000);
    	driver.findElement(By.xpath("//li[.=' 2020 ']")).click();
    	
    	Thread.sleep(1000);
    	CurrentHospitalOrClinicEdt.sendKeys("abcdef");
    	Thread.sleep(1000);
    	
    	wUtil.waitForElementToBeVisible(driver, AiImage);
    	if(AiImage.isDisplayed())
    	{
    		wUtil.waitForElementToBeClickable(driver, SubmitBtn);
    		SubmitBtn.click();
    	}
    	
    	    
    }
}
