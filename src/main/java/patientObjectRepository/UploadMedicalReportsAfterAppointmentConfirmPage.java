package patientObjectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class UploadMedicalReportsAfterAppointmentConfirmPage {

    //Rule-1 :  Finding WebElements Using @FindBy Annotations
    
    @FindBy(xpath="//button[@class='back-btn']")private WebElement BackBtn;
    
    @FindBy(xpath="//div[@class='upload-zone']")private WebElement UploadZone;
    
    @FindBy(xpath="//button[@class='submit-btn']")private WebElement SubmitBtn;
    
  
	//Rule-2 : Create a constructor to initilise these elements    
    
	public UploadMedicalReportsAfterAppointmentConfirmPage(WebDriver driver)
	{
		PageFactory.initElements(driver, this);
	}
	
	//Rule-3 : Provide getters to access these variables


	public WebElement getBackBtn() {
		return BackBtn;
	}


	public WebElement getUploadZone() {
		return UploadZone;
	}


	public WebElement getSubmitBtn() {
		return SubmitBtn;
	}
}
