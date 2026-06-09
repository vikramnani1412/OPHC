package doctorObjectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class WelcomePage {

	//Finding WebElements Using @FindBy Annotations

//    @FindBy(xpath="//a[.=' Back ']")private WebElement BackLnk;
    
    @FindBy(xpath="//img[@class='profile-img']")private WebElement ProfileIcon;
    
//    @FindBy(xpath="//a[.=' Register ']")private WebElement RegisterLnk;
  
    
	//Rule-2:Create a constructor to initilise these elements
    
	public WelcomePage(WebDriver driver)
	{
		PageFactory.initElements(driver, this);
	}
	
	//Rule-3:Provide getters to access these variables
    
	public WebElement getProfileIcon() {
		return ProfileIcon;
	}
	
	//Business Library
	
	public void clickOnProfileIcon() throws Exception
	{
		Thread.sleep(1000);
		ProfileIcon.click();
	}
}
