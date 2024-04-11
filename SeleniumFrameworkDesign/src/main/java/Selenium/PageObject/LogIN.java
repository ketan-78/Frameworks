
package Selenium.PageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import Selenium.ReusableComponents.Wait;

public class LogIN extends Wait{
	WebDriver driver;
	public LogIN(WebDriver driver)//constructor, it initialize first before anything in the class
	{
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	} 
	//WebElement email=driver.findElement(By.id("userEmail"));
	//PageFactoryc-design method
	@FindBy(id="userEmail")
	WebElement email;
	@FindBy(id="userPassword")
	WebElement pw;
	@FindBy(id="login") 
	WebElement login;
	@FindBy(css="[class*='toast-error']")
	WebElement Error;
	@FindBy(css="[class*='toast-success")
	WebElement success;
	
	public Product_Selection details(String Email,String pswd)
	{
		email.sendKeys(Email);
		pw.sendKeys(pswd);
		login.click();
		Product_Selection select=new Product_Selection(driver);
		return select;
	}
	public String errorMessage() 
	{
		errorWait(Error);
		return Error.getText();
	}

}
																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																			