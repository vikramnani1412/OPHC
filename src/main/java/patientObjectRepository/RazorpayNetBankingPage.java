package patientObjectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import genericUtilities.WebDriverUtility;

public class RazorpayNetBankingPage {

    //Rule-1 :  Finding WebElements Using @FindBy Annotations
	
	@FindBy(xpath="//div[@data-value='SBIN']")private WebElement SBILnk;
	
	@FindBy(xpath="//div[@data-value='HDFC']")private WebElement HDFCLnk;
	
    @FindBy(xpath="//div[@data-value='ICIC']")private WebElement ICICILnk;
    
    @FindBy(xpath="//div[@data-value='UTIB']")private WebElement AxisLnk;
    
    @FindBy(xpath="//div[@data-value='BARB_R']")private WebElement BOBLnk;
    
    @FindBy(xpath="//span[@data-testid='More Banks']")private WebElement MoreBanksLnk;
    
    @FindBy(xpath="//button[.=' Continue']")private WebElement ContinueBtn;
    
    @FindBy(xpath="//button[.='Success']")private WebElement SuccessBtn;
    
    @FindBy(xpath="//button[.='Failure']")private WebElement FailureBtn;
  
	//Rule-2 : Create a constructor to initilise these elements    
    
	public RazorpayNetBankingPage(WebDriver driver)
	{
		PageFactory.initElements(driver, this);
	}

	
	//Rule-3 : Provide getters to access these variables
	
	public void bookSlotUsingSBIbank(WebDriver driver) throws Exception
	{
		WebDriverUtility wUtil = new WebDriverUtility();
		
		SBILnk.click();
		Thread.sleep(2000);
		wUtil.waitForElementToBeClickable(driver, SuccessBtn);
		SuccessBtn.click();
		Thread.sleep(2000);
		
	}
	
	
}
