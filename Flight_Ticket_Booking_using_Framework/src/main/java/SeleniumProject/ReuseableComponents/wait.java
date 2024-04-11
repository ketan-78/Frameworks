package SeleniumProject.ReuseableComponents;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class wait {
	WebDriver driver;
	public wait(WebDriver driver)
	{
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	public void waitHere() throws InterruptedException
	{
		Thread.sleep(1000);
	}
}
