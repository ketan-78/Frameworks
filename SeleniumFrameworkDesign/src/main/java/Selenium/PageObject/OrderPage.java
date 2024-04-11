
package Selenium.PageObject;

import java.util.List;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import Selenium.ReusableComponents.Wait;

public class OrderPage extends Wait{
	WebDriver driver;
	public OrderPage(WebDriver driver)//constructor, it initialize first before anything in the class
	{
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css="[routerlink*='myorders']")
	WebElement orderpage;
	@FindBy(xpath="//table/tbody //td[2]")
	List<WebElement> Names;
	
	public boolean OrderPageInfoCheck(String P1) throws InterruptedException 
	{
		orderpage.click();
		Thread.sleep(1000);
		boolean match=Names.stream().anyMatch(s->s.getText().contains(P1));
		return match;
	}
}
