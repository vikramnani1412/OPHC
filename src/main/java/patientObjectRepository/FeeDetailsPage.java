package patientObjectRepository;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class FeeDetailsPage {

    //Rule-1: Finding WebElements Using @FindBy Annotations
    
    @FindBy(xpath="//img[@alt='close']")private WebElement CloseBtn;
    
    @FindBy(xpath="//span[contains(.,'')]/following-sibling::span[.='22']")private WebElement TodaysDateBasedOnDate;
    
    @FindBy(xpath="//div[@class='time-slot-card booked ng-star-inserted']")private WebElement Slot;
  
    
    @FindBy(xpath="(//span[@class='day-date'])[1]")private WebElement TodaysDateBasedOnIndex;
    
    @FindBy(xpath="Available")private WebElement AvailablityBasedOnIndex;
    
    @FindBy(xpath="//div[@class='slots-legend']/following-sibling::button[contains(.,' Book Now')]")private WebElement BooknowBtn;
        
    @FindBy(xpath="//button[.='Follow up']")private WebElement BookNowBtnBasedOnIndex;
    
    @FindBy(xpath="//h6[.='Dr. jayant']/../../following-sibling::div/div/div/following-sibling::button[.='Book Now']")private WebElement BookNowBtnBasedOnDoctorName;
  
  
	//Rule-2: Create a constructor to initilise these elements    
    
	public FeeDetailsPage(WebDriver driver)
	{
		PageFactory.initElements(driver, this);
	}

	
	//Rule-3: Provide getters to access these variables
	
	public WebElement getCloseBtn() {
		return CloseBtn;
	}


	public WebElement getTodaysDateBasedOnDate() {
		return TodaysDateBasedOnDate;
	}


	public WebElement getTodaysDateBasedOnIndex() {
		return TodaysDateBasedOnIndex;
	}


	public WebElement getAvailablityBasedOnIndex() {
		return AvailablityBasedOnIndex;
	}


	public WebElement getBooknowBtn() {
		return BooknowBtn;
	}


	public WebElement getBookNowBtnBasedOnIndex() {
		return BookNowBtnBasedOnIndex;
	}


	public WebElement getBookNowBtnBasedOnDoctorName() {
		return BookNowBtnBasedOnDoctorName;
	}
	
	
	// Business Library
	
	public void bookingSlot(WebDriver driver)
	{
		List<WebElement> slots = driver.findElements(By.xpath("//div[@class='time-slot-card booked ng-star-inserted']"));

			WebElement firstSlot = slots.get(0);
			WebElement lastSlot = slots.get(slots.size() - 1);

			System.out.println("Total Slots: " + slots.size());
	}
	
	
	
	
}
