package Selenium.PageObject;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import Selenium.ReusableComponents.Wait;

public class PersonalDetails extends Wait{
	WebDriver driver;
	public PersonalDetails(WebDriver driver) 
	{
		super(driver); 
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	@FindBy(xpath="//input[@placeholder='Select Country']")
	WebElement selec;
	@FindBy(css="[class*='ta-item']")
	List<WebElement> options;
	@FindBy(css="[class*='action__submit']")
	WebElement submit;
	String country="India";
	By locator=By.cssSelector("[class*='btnn action__submit']");
	
	public void details(String initials)
	{ 
		selec.sendKeys(initials);
		options.stream().filter(s->s.getText().equals(country)).limit(1).forEach(s->s.click());
		placeorderButtton(locator);
		submit.click();
	}
	
	@FindBy(css=".em-spacer-1 .ng-star-inserted")
	List<WebElement> Oid;
	@FindBy (css="[class='hero-primary']")
	WebElement msg;
	
	public String orderID(String p1,String p2)
	{
		System.out.println("Order ID's of "+p1+" and "+p2+" products are:");
		Oid.stream().forEach(s->System.out.println(s.getText().split(" ")[1].trim()));
		String Actual=msg.getText().trim();
		return (Actual);
	}
}