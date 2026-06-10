package patientObjectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class VerifyCodePage {

    // Finding WebElements Using @FindBy Annotations
    
    @FindBy(xpath="(//input[@inputmode='numeric'])[1]")private WebElement OtpFirstBox;
    
    @FindBy(xpath="//a[.='Resend']")private WebElement ResendLnk;
    
    @FindBy(xpath="//button[.='Verify']")private WebElement VerifyBtn;
  
	//Rule-3:Create a constructor to initilise these elements    
    
	public VerifyCodePage(WebDriver driver)
	{
		PageFactory.initElements(driver, this);
	}
	
	//Rule-4:Provide getters to access these variables
	
	public WebElement getOtpFirstBox() {
		return OtpFirstBox;
	}

	public WebElement getResendLnk() {
		return ResendLnk;
	}

	public WebElement getVerifyBtn() {
		return VerifyBtn;
	}
	
	//Business Library
	
	public void enterOtp(String OTP) throws Exception
	{
		Thread.sleep(1000);
		OtpFirstBox.sendKeys(OTP);
		Thread.sleep(1000);
		VerifyBtn.click();
	}
	
	
}
