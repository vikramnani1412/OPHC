package patientObjectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class FindDoctorsPage {

    // Finding WebElements Using @FindBy Annotations
    
    @FindBy(xpath="//input[@placeholder='Search doctors']")private WebElement SearchEdt;
    
    @FindBy(xpath="//img[@alt='filter']")private WebElement FilterIcon;
    
    @FindBy(xpath="//div[@class='header-actions']")private WebElement ProfileIcon;
    
    @FindBy(xpath="//button[.='New']")private WebElement NewBtn;
    
    @FindBy(xpath="//button[.='Follow up']")private WebElement FollowUpBtn;
        
    @FindBy(xpath="//button[.='Follow up']")private WebElement BookNowBtnBasedOnIndex;
    
    @FindBy(xpath="//h6[.='Dr. jayant']/../../following-sibling::div/div/div/following-sibling::button[.='Book Now']")private WebElement BookNowBtnBasedOnDoctorName;
  
  
	//Rule-3:Create a constructor to initilise these elements    
    
	public FindDoctorsPage(WebDriver driver)
	{
		PageFactory.initElements(driver, this);
	}
	
	//Rule-4:Provide getters to access these variables
	
	
	public WebElement getSearchEdt() {
		return SearchEdt;
	}

	public WebElement getFilterIcon() {
		return FilterIcon;
	}

	public WebElement getProfileIcon() {
		return ProfileIcon;
	}

	public WebElement getNewBtn() {
		return NewBtn;
	}

	public WebElement getFollowUpBtn() {
		return FollowUpBtn;
	}

	public WebElement getBookNowBtnBasedOnIndex() {
		return BookNowBtnBasedOnIndex;
	}

	public WebElement getBookNowBtnBasedOnDoctorName() {
		return BookNowBtnBasedOnDoctorName;
	}

	

	
	
	//Business Library
	
}
