package genericUtilities;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class RetryAnaliserImplementationClass implements IRetryAnalyzer{

	int count=0;        //after doing manually we get to know how many times it will fail.
	int retrycount=3;     //Then after it will pass based on that we have to give count
	public boolean retry(ITestResult result) {
		//count is increment from 0 upto 3
		//0<3-1st iteration
		//1<3-2nd iteration
		//2<3-3rd iteration
		//3<3-false stops
		while(count<retrycount)  
		{
			count++;  //1,2,3 
			return true;
		}
		return false;
	} 

	
	
}
