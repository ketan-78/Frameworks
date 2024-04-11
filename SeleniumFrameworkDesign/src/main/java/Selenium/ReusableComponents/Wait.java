package Selenium.ReusableComponents;

import java.time.Duration; 

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import Selenium.PageObject.OrderPage;

public class Wait {
	WebDriver driver;			 
	public Wait(WebDriver driver) 
	{
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}

	public void until(By locator)
	{
		WebDriverWait w = new WebDriverWait(driver, Duration.ofSeconds(4));
		w.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector(".ng-animating"))));
		w.until(ExpectedConditions.visibilityOfElementLocated(locator));
	}

	public OrderPage orderpageTrigger()
	{
		OrderPage OP=new OrderPage(driver);
		return OP;
	}
	
	public void successWait(WebElement success)
	{
		WebDriverWait w = new WebDriverWait(driver, Duration.ofSeconds(4));
		w.until(ExpectedConditions.visibilityOf(success));
	}
	public void errorWait(WebElement error)
	{
		WebDriverWait w = new WebDriverWait(driver, Duration.ofSeconds(4));
		w.until(ExpectedConditions.visibilityOf(error));
	}
	public void CheckoutButton(By locator)
	
	{
		WebDriverWait w = new WebDriverWait(driver, Duration.ofSeconds(3));
		w.until(ExpectedConditions.elementToBeClickable(locator));
	}
	public void placeorderButtton(By locator)
	{
		WebDriverWait w = new WebDriverWait(driver, Duration.ofSeconds(3));
		w.until(ExpectedConditions.elementToBeClickable(locator));
		
		
	}
}
	
