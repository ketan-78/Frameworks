package Selenium.Tests;

import java.io.IOException;
import org.testng.Assert;
import org.testng.annotations.Test;

import Selenium.TestComponents.BaseTest;
import Selenium.TestComponents.RetryMechanism;

public class error extends BaseTest{

	@Test(retryAnalyzer=RetryMechanism.class)//it will rerun if it fails
	public void WrongCredentials() throws IOException, InterruptedException 
	{
		login.details("ketan@gmaill.com", "C_12345");
		String val=login.errorMessage();
		if(val.equalsIgnoreCase("Incorrect email or password."))
			{
				System.out.println("Incorrect Credentials"); 
				Assert.assertTrue(false);
			}
	
	}
}