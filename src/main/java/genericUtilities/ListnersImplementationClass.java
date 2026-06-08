package genericUtilities;

import java.io.IOException;
import org.testng.ITestContext;
import org.testng.ITestListener; 
import org.testng.ITestResult;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

//import io.netty.handler.codec.http.HttpContentEncoder.Result;
/*import lombok.Generated;*/


/**
 * This class will provide implementation to iTest Listners Interface of TestNG
 * @author vikra
 *
 */
public class ListnersImplementationClass implements ITestListener
{
	ExtentReports report;
	ExtentTest test;
	public void onTestStart(ITestResult result) 
	{
		String methodName=result.getMethod().getMethodName();
	
		test=report.createTest(methodName);
		
		test.log(Status.INFO,"--"+methodName+"-->Test Execution Started<--");
		test.log(Status.INFO, "--"+methodName+"-->Test Exe Started<--");
	}

	public void onTestSuccess(ITestResult result) 
	{
		String methodName=result.getMethod().getMethodName();
		test.log(Status.PASS, "--"+methodName+"-->Test Execution Passed<--");
	}

	public void onTestFailure(ITestResult result) 
	{
		String methodName=result.getMethod().getMethodName();
		test.log(Status.FAIL, "---"+methodName+"--->Test is Failed<---");
		test.log(Status.WARNING, result.getThrowable());
	    
		//Taking ScreenShot for failed test scripts
		String screenshotName=methodName+"-"+new JavaUtility().getSystemDateInFormat();
		 
		WebDriverUtility wUtility=new WebDriverUtility();
		try 
		{
			String path = wUtility.takeScreenShot(BaseClass.sDriver, screenshotName);
			test.addScreenCaptureFromPath(path);  //Attach Screen shot to the Report
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
		
	}

	public void onTestSkipped(ITestResult result) 
	{
		String methodName=result.getMethod().getMethodName();
	//	System.out.println("--"+methodName+"-->Test Execution Skipped<--");
		test.log(Status.SKIP, "--->"+methodName+"->Test is Skipped<-");
		test.log(Status.WARNING,result.getThrowable());
	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult result) 
	{
		
	}

	public void onTestFailedWithTimeout(ITestResult result) 
	{
		
	}

	public void onStart(ITestContext context) 
	{
		System.out.println("-->Suite Execution Started<--");
		
		//Configure the extent Reports here
		// Report getReport Generated likeGenerated this format--->//Report-20 Aprl 2023-08-23--56.html
		
		//This is to store reports in particular folder
//		ExtentSparkReporter htmlReport=new ExtentSparkReporter("\\C:\\Users\\vikra\\OneDrive\\Desktop\\X-YUG All Files\\Eclipse Workspace X_Yug\\Goldsikka Extent Reports\\Report-"+new JavaUtility().getSystemDateInFormat()+".html");
//		C:\Users\vikra\OneDrive\Desktop\X-YUG All Files\Eclipse Workspace X_Yug\Goldsikka Extent Reports
		
		//This is to store reports in eclipse
    	ExtentSparkReporter htmlReport=new ExtentSparkReporter(".\\Extent Reports\\Report-"+new JavaUtility().getSystemDateInFormat()+".html");	
		htmlReport.config().setDocumentTitle("Goldsikka Execution Report");
		htmlReport.config().setReportName("Goldsikka-Automation Report");
		htmlReport.config().setTheme(Theme.STANDARD);
		
		report=new ExtentReports();
		report.attachReporter(htmlReport);
		report.setSystemInfo("Base Browser:","Chrome");
		report.setSystemInfo("Base URL:", "http://stg-fms.goldbox.gold/signin");
		report.setSystemInfo("Base Platform:", "Windows 11");
		report.setSystemInfo("Reporter Name:", "Vikram Gangavarapu");
	}

	public void onFinish(ITestContext context) 
	{
		System.out.println("-->Suite Execution Finished<--");
		
		//Extent Report Should Get Generated
		
		report.flush();
		
		
	}
   
/*	
	ExtentReports report;
	ExtentTest test;
	public void onTestStart(ITestResult result) 
	{
		String methodName=result.getMethod().getMethodName();
	
		test=report.createTest(methodName);
		
		test.log(Status.INFO,"--"+methodName+"-->Test Execution Started<--");
		test.log(Status.INFO, "--"+methodName+"-->Test Exe Started<--");
	}

	public void onTestSuccess(ITestResult result) 
	{
		String methodName=result.getMethod().getMethodName();
		test.log(Status.PASS, "--"+methodName+"-->Test Execution Passed<--");
	}

	public void onTestFailure(ITestResult result) 
	{
		String methodName=result.getMethod().getMethodName();
		test.log(Status.FAIL, "---"+methodName+"--->Test is Failed<---");
		test.log(Status.WARNING, result.getThrowable());
	
		//Taking ScreenShot for failed test scripts
		String screenshotName=methodName+"-"+new JavaUtility().getSystemDateInFormat();
		 
		WebDriverUtility wUtility=new WebDriverUtility();
		try {
			String path = wUtility.takeScreenShot(BaseClass.sDriver, screenshotName);
			test.addScreenCaptureFromPath(path);  //Attach Screen shot to the Report
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		
	}

	public void onTestSkipped(ITestResult result) 
	{
		String methodName=result.getMethod().getMethodName();
	//	System.out.println("--"+methodName+"-->Test Execution Skipped<--");
		test.log(Status.SKIP, "--->"+methodName+"->Test is Skipped<-");
		test.log(Status.WARNING,result.getThrowable());
	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult result) 
	{
		
	}

	public void onTestFailedWithTimeout(ITestResult result) 
	{
		
	}

	public void onStart(ITestContext context) 
	{
		System.out.println("-->Suite Execution Started<--");
		
		  Configure the extent Reports here
		                // Report getReport Generated likeGenerated this format--->//Report-20 Aprl 2023-08-23--56.html
      	ExtentSparkReporter htmlReport=new ExtentSparkReporter(".\\Extent Reports\\Report-"+new JavaUtility().getSystemDateInFormat()+".html");	
		htmlReport.config().setDocumentTitle("vtiger Execution Report");
		htmlReport.config().setReportName("QCO-SJEADO-M1-Automation Report");
		htmlReport.config().setTheme(Theme.DARK);
		
		report=new ExtentReports();
		report.attachReporter(htmlReport);
		report.setSystemInfo("Base Browser:","Chrome");
		report.setSystemInfo("Base URL:", "http://localhost:8888");
		report.setSystemInfo("Base Platform:", "Windows 11");
		report.setSystemInfo("Reporter Name:", "Vicky");
		
	}

	public void onFinish(ITestContext context) 
	{
		System.out.println("-->Suite Execution Finished<--");
		
		Extent Report Should Get Generated
		
		report.flush();
		
		
	}
	*/
}
