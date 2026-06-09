package Admin;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

import adminObjectRepository.AdminDashboardPage;
import adminObjectRepository.AdminLoginPage;
import genericUtilities.PropertyFileUtility;
import io.github.bonigarcia.wdm.WebDriverManager;

public class justLogin {

	@Test
	public void AdminLogin() throws Throwable
	{
        PropertyFileUtility pUtil = new PropertyFileUtility();
		
		String URL = pUtil.readDataFromPropertyFile("adminurl");
        String USERNAME = pUtil.readDataFromPropertyFile("username");
        String PASSWORD = pUtil.readDataFromPropertyFile("password");
		
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3000));
		driver.get(URL);
		AdminLoginPage lPage = new AdminLoginPage(driver);
		lPage.loginToAdmin(USERNAME, PASSWORD);
		
		
	}
	
}
