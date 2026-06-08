package genericUtilities;

import java.awt.AWTException;import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Calendar;
import java.util.Date;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * This class contains all Generic methods related to WebDriver Actions.
 * @author vikra
 *
 */
public class WebDriverUtility {

	
	/**
	 * This method will maximize the window
	 * @param driver
	 */
	public void maximizeTheWindow(WebDriver driver)
	{
		driver.manage().window().maximize();
	}
	/**
	 * This method will minimize the window 
	 * @param driver
	 */
	public void minimizeTheWindow(WebDriver driver)
	{
		driver.manage().window().minimize();
	}
	/**
	 * This method will wait until webpage loaded
	 * @param driver
	 */
	public void waitUntilPageLoad(WebDriver driver)
	{
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
	}
	/**
	 * This method will wait until element is clickable
	 * @param driver
	 */
	public void waitForElementToBeClickable(WebDriver driver,WebElement element)
	{
		WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(10));
		wait.until(ExpectedConditions.elementToBeClickable(element));
	}
	/**
	 * This method will wait until element is visible
	 * @param driver
	 */
	public WebElement waitForElementToBeVisible(WebDriver driver, WebElement element) 
	{
		System.out.println("Driver = " + driver);
	    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	    return wait.until(ExpectedConditions.visibilityOf(element));
	}
	
	
	/**
	 * This method will wait until alert is present
	 * @param driver
	 */
	public void waitUntilAlertIsPresent(WebDriver driver)
	{
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.alertIsPresent());
	}
	/**
	 * This method will handle dropdown by its index 
	 * @param driver
	 * @param index
	 */
	public void handleDropdownByIndex(WebElement element,int index)
	{
		Select s=new Select(element);
		s.selectByIndex(index);
	}
	/**
	 * This method will handle dropdown by its value 
	 * @param element
	 * @param value
	 */
	 public void handleDropdownByValue(WebElement element,String value)
	{
		Select s=new Select(element);
		s.selectByValue(value);
	}
	 
	 /**
	  * 
	  * @param element
	  * @param value
	  */
	 public void handleDropdownBy2Values(WebElement element,String value,String value2)
		{
			Select s=new Select(element);
			s.selectByValue(value);
			
		    Select s1=new Select(element);
			s1.selectByValue(value2);
		}
	 
	/**
	 * This method will handle dropdown by its visibleText
	 * @param text
	 * @param element
	 */
	public void handleDropdownByVisibleText(String text,WebElement element)
	{
		Select s=new Select(element);
		s.selectByVisibleText(text);
	}
	/**
	 * This method will perform mouse hover actions on a web element
	 * @param driver
	 * @param element
	 */
	public void mouseHoverAction(WebDriver driver,WebElement element)
	{
		Actions act=new Actions(driver);
		act.moveToElement(element).perform();
	}
	
	/**
	 * This method will perform right click action anywhere on the web page
	 * @param driver
	 */
	public void rightClickAction(WebDriver driver)
	{
		Actions act=new Actions(driver);
		act.contextClick().perform();
	}
	
	/**
	 * This method will perform right click operation on a particular element
	 * @param driver
	 * @param element
	 */
	public void rightClickAction(WebDriver driver,WebElement element)
	{
		Actions act=new Actions(driver);
		act.contextClick(element).perform();
	}
	
	/**
	 * This method will perform double click operation on anywhere on the web page
	 * @param driver
	 */
	public void doubleClickAction(WebDriver driver)
	{
		Actions act=new Actions(driver);
		act.doubleClick().perform();
	}
	
	/**
	 * This method will perform double click operation on a particular web element
	 * @param driver
	 * @param element
	 */
	public void doubleClickAction(WebDriver driver,WebElement element)
	{
		Actions act=new Actions(driver);
		act.doubleClick(element).perform();
	}
	
	/**
	 * This method will perform drag and drop operation
	 * @param driver
	 * @param srcEle
	 * @param dstEle
	 */
	public void dragAndDrop(WebDriver driver,WebElement srcEle,WebElement dstEle)
	{
		Actions act=new Actions(driver);
		act.dragAndDrop(srcEle, dstEle).perform();
	}
	
	/**
	 * This method will press and release enter key
	 * @throws AWTException
	 */
	public void pressEnterKey() throws AWTException
	{
		Robot r=new Robot();
		r.keyPress(KeyEvent.VK_ENTER);
		r.keyRelease(KeyEvent.VK_ENTER);
	}
	
	/**
	 * This method will handle frame based on index
	 * @param driver
	 * @param index
	 */
	public void switchToFrame(WebDriver driver,int index)
	{
		driver.switchTo().frame(index);
	}
	
	/**
	 * This method will handle frame based on name or id
	 * @param driver
	 * @param nameOrId
	 */
	public void switchToFrame(WebDriver driver,String nameOrId)
	{
		driver.switchTo().frame(nameOrId);
	}
	
	/**
	 * This method will handle frame based on web element
	  * @param driver
	 * @param element
	 */
	public void switchToFrame(WebDriver driver,WebElement element)
	{
		driver.switchTo().frame(element);
	}
	
	/**
	 * This method will switch control to immediate parent
	 * @param driver
	 */
	public void switchToParentFrame(WebDriver driver)
	{
		driver.switchTo().parentFrame();
	}
	
	/**
	 * This method will switch control to default frame
	 * @param driver
	 */
	public void switchToDefaultFrame(WebDriver driver)
	{
		driver.switchTo().defaultContent();
	}
	
	/**
	 * This method will switch control to default frame
	 * @param driver
	 */
	public void handlingFrame(WebDriver driver)
	{
	//	JavascriptExecutor jse = new JavascriptExecutor();
	}
	
	
	/**
	 * This method will accept alert popup
	 * @param driver
	 */
	public void acceptAlert(WebDriver driver) 
	{
		driver.switchTo().alert().accept();
	}
	
	/**
	 * This method will dismiss alert popup
	 * @param driver
	 */
	public void dismissAlert(WebDriver driver)
	{
		driver.switchTo().alert().dismiss();
	}
	
	/**
	 * This method will capture the alert text
	 * @param driver
	 * @return
	 */
	public String getAlertText(WebDriver driver)
	{
		return driver.switchTo().alert().getText();
	}
	
	/**
	 * This method will take screenshot and store it in screenshot folder
	 * @param driver
	 * @param methodName
	 * @return
	 * @throws IOException
	 */
	public String takeScreenShot(WebDriver driver, String methodName) throws IOException
	{
		TakesScreenshot ts = (TakesScreenshot)driver;
		File sshotType = ts.getScreenshotAs(OutputType.FILE);
		File storingFolder = new File(".\\Screenshots\\"+methodName+".png");   //This is to store in eclipse
		
//		File storingFolder=new File("\\C:\\Users\\vikra\\OneDrive\\Desktop\\X-YUG All Files\\Eclipse Workspace X_Yug\\Goldsikka  Screenshots\\"+methodName+".png");   //This is to store inside local system
		
		FileUtils.copyFile(sshotType, storingFolder);
		return storingFolder.getAbsolutePath();
	}
	
	
	
	
	/**
	 * 
	 * @param driver
	 * @param modalPopupElement
	 * @return
	 */
	public byte[] takeModelPopupScreenshot(WebDriver driver, WebElement modalPopupElement) 
	{
        // Locate and interact with the modal popup to make it visible
        WebElement modalPopup = driver.findElement(By.id("modalPopupElement")); // Replace with the actual locator
        modalPopup.click(); // You might need to trigger the popup

        // Take a screenshot of the entire page (including the modal)
        TakesScreenshot screenshotDriver = (TakesScreenshot) driver;
        byte[] screenshot = screenshotDriver.getScreenshotAs(OutputType.BYTES);

        // Save the screenshot to a file
        try {
            java.nio.file.Files.write(java.nio.file.Paths.get("modal_popup_screenshot.png"), screenshot);
        } catch (java.io.IOException e) {
            e.printStackTrace();
        }
        return screenshot;
    }
	
	
	
	
	
	/**
	 * This method will switch to window based on (partial(parent)) window title
	 * @param driver
	 * @param partialWinTitle
	 */
	public void switchToWindow(WebDriver driver,String partialWinTitle)
	{
		//Step-1:Capture all window ids
		Set<String> windIds=driver.getWindowHandles();
		//Step-2:Create a For each Loop and navigate to each window
		for(String Windows:windIds)
		{
			//Step-3:Capture the title of each window
			String currentTitle=driver.switchTo().window(Windows).getTitle();
			//Step-4:Compare the current title with parent window title
			if(currentTitle.contains(partialWinTitle))
			{
				break;
			}
		}	
	}
	
	/**
	 * This method will scroll randomly downwards
	 * @param driver
	 * @param element
	 */
	public void scrollActionx(WebDriver driver,WebElement element)
	{
		JavascriptExecutor jse=(JavascriptExecutor)driver;
		int x=element.getLocation().getY();
		jse.executeScript("window.scrollBy("+x+",0)");
	}
	
	/**
	 * This method will scroll page to side
	 * @param driver
	 * @param element
	 */
	public void scrollActiony(WebDriver driver,WebElement element)
	{
		JavascriptExecutor jse=(JavascriptExecutor)driver;
		int y=element.getLocation().getY();
		jse.executeScript("window.scrollBy(0,"+y+")");
	}
	
	/**
	 * This method will return current date in format
	 * @return
	 */
	public String calendar()
	{
		Calendar cal=Calendar.getInstance();
		Date d=cal.getTime();
		SimpleDateFormat sdf=new SimpleDateFormat("DD/MM/YYYY");
		String date=sdf.format(d);
		return date;
	}
	
	/**
	 * This method will return the customized date with respect to day
	 * @param addDay
	 * @return
	 */
	public String calendarDay(int addDay)
	{
		Calendar cal=Calendar.getInstance();
		cal.add(Calendar.DAY_OF_YEAR, addDay);
		Date d=cal.getTime();
		SimpleDateFormat sdf=new SimpleDateFormat("DD/MM/YYYY");
		String date=sdf.format(d);
		return date;
	}
	
	
	public String calendarYear(int addYear)
	{
		Calendar cal=Calendar.getInstance();
		cal.add(Calendar.YEAR, addYear);
		Date d=cal.getTime();
		SimpleDateFormat sdf=new SimpleDateFormat("DD/MM/YYYY");
		String date=sdf.format(d);
		return date;
	}
	
	
	public void logOutAction(WebDriver driver,WebElement element)
	{
		Actions act=new Actions(driver);
		act.moveToElement(element).perform();
		
	}
	
	/**
	 * This method is used to click on choose file option
	 * @param driver
	 * @param element
	 */
	public void clickOnChooseFileOption(WebDriver driver, WebElement element, String filePath)
	{
		//1st Method
		// Locate the file input element
//        WebElement fileInput = driver.findElement(By.xpath("//h1[.='Add Main Banners']/../following-sibling::div//input[@formcontrolname='image_uri']"));
        // Provide the path of the file to be uploaded
//        String filePath = "C:\\Users\\vikra\\OneDrive\\Desktop\\One Drive Folder\\OneDrive\\Pictures\\Saved Pictures\\download.jpeg";
        // Send the file path to the file input element
		
        element.sendKeys(filePath);
		
		//2nd Method
//		JavascriptExecutor js = (JavascriptExecutor)driver;
//		js.executeScript("arguments[0].style.display='block';",element);
	}
	
	public void scrollPageDown(int NoOfTimes) throws Exception
	{
		Robot r = new Robot();
		for(int i=1;i<=NoOfTimes;i++)
		{
			r.keyPress(KeyEvent.VK_PAGE_DOWN);
			r.keyRelease(KeyEvent.VK_PAGE_DOWN);
		}
	}
	
	public void scrollPageUp(int NoOfTimes) throws Exception
	{
		Robot r = new Robot();
		for(int i=1;i<=NoOfTimes;i++)
		{
			r.keyPress(KeyEvent.VK_PAGE_UP);
			r.keyRelease(KeyEvent.VK_PAGE_UP);
		}
	}
	
	public void clickOnEscapeButton() throws Exception
	{
		Robot robot = new Robot();
		robot.keyPress(KeyEvent.VK_ESCAPE);
		robot.keyRelease(KeyEvent.VK_ESCAPE);
	}
	
	public void scrollToParticularWebElement(WebDriver driver, WebElement element) {
	    JavascriptExecutor js = (JavascriptExecutor) driver;
	    js.executeScript("arguments[0].scrollIntoView(true);", element);
	}
	
	
}