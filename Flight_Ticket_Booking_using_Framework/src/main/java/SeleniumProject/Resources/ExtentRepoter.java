package SeleniumProject.Resources;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentRepoter {
	public static ExtentReports getReportObject()
	{
		String path=System.getProperty("user.dir")+"\\reports\\index.html";
		ExtentSparkReporter reporter=new ExtentSparkReporter(path);
		reporter.config().setReportName("Flight Booking Report");
		reporter.config().setDocumentTitle("Booking Results");
		ExtentReports reports=new ExtentReports();
		reports.attachReporter(reporter); 
		reports.setSystemInfo("Tester", "Ketan Chauhan");
		return reports;
	}

}
