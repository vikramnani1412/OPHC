package patientObjectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AppointmentsPage {

    //Rule-1: Finding WebElements Using @FindBy Annotations
    
    @FindBy(xpath="//button[.='Upcoming']")private WebElement UpcomingBtn;
    
    @FindBy(xpath="//button[.='Past']")private WebElement PastBtn;
    
    @FindBy(xpath="//div[@class='appointment-list ng-star-inserted']")private WebElement UpcomingTotalAppointmentsList;
    
    @FindBy(xpath="//div[@class='appointments-container']")private WebElement PastTotalAppointmentsList;
    
  
	//Rule-2: Create a constructor to initilise these elements    
    
	public AppointmentsPage(WebDriver driver)
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


	public WebElement getUpcomingTotalAppointmentsList() {
		return UpcomingTotalAppointmentsList;
	}


	public WebElement getPastTotalAppointmentsList() {
		return PastTotalAppointmentsList;
	}

	
}
