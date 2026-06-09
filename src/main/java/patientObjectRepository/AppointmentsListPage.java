package patientObjectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AppointmentsListPage {

    //Rule-1: Finding WebElements Using @FindBy Annotations
    
    @FindBy(xpath="//button[.='Upcoming']")private WebElement UpcomingBtn;
    
    @FindBy(xpath="//button[.='Past']")private WebElement PastBtn;
    
    @FindBy(xpath="//div[@class='appointments-container']")private WebElement AppointmentsContainer;
    
    @FindBy(xpath="//p[contains(.,'Booking ID: ')]")private WebElement AllBookingIds;
    
    @FindBy(xpath="//button[.=' Join Video ']")private WebElement JoinVideoBtn;
    
    @FindBy(xpath="//button[.='Cancel']")private WebElement CancelBtn;
    
    @FindBy(xpath="//i[@class='bi bi-three-dots-vertical']")private WebElement MoreOptionThreeDots;
    
  
	//Rule-2: Create a constructor to initilise these elements    
    
	public AppointmentsListPage(WebDriver driver)
	{
		PageFactory.initElements(driver, this);
	}

	
	//Rule-3: Provide getters to access these variables
	

	public WebElement getUpcomingBtn() {
		return UpcomingBtn;
	}


	public WebElement getPastBtn() {
		return PastBtn;
	}


	public WebElement getAppointmentsContainer() {
		return AppointmentsContainer;
	}


	public WebElement getAllBookingIds() {
		return AllBookingIds;
	}


	public WebElement getJoinVideoBtn() {
		return JoinVideoBtn;
	}


	public WebElement getCancelBtn() {
		return CancelBtn;
	}


	public WebElement getMoreOptionThreeDots() {
		return MoreOptionThreeDots;
	}
	
}
