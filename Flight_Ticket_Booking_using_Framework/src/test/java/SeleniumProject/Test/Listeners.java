package SeleniumProject.Test;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import SeleniumProject.Resources.ExtentRepoter;
import SeleniumProject.main.core;

public class Listeners extends core implements ITestListener {
	
	ExtentTest test;
	ExtentReports reports=ExtentRepoter.getReportObject();
	ThreadLocal<ExtentTest> reportTest=new ThreadLocal<ExtentTest>();
	@Override
	public void onTestStart(ITestResult result) {
		// TODO Auto-generated method stub
		test=reports.createTest(result.getMethod().getMethodName());
		reportTest.set(test);
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		// TODO Auto-generated method stub
		 test.log(Status.PASS, "Test Complete");
	}

	@Override
	public void onTestFailure(ITestResult result) {
		// TODO Auto-generated method stub
		reportTest.get().fail(result.getThrowable());//This will print the error message.
		try {
			driver=(WebDriver)result.getTestClass().getRealClass().getField("driver").get(result.getInstance());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String filePath = null;
		try {
			filePath = takeScreenshot(result.getMethod().getMethodName(),driver);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		reportTest.get().addScreenCaptureFromPath(filePath, result.getMethod().getMethodName());
		
		
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		// TODO Auto-generated method stub
		 
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub
		 
	}

	@Override
	public void onTestFailedWithTimeout(ITestResult result) {
		// TODO Auto-generated method stub
		 
	}

	@Override
	public void onStart(ITestContext context) {
		// TODO Auto-generated method stub
 
	}

	@Override
	public void onFinish(ITestContext context) {
		// TODO Auto-generated method stub
		reports.flush(); 
	}


}
