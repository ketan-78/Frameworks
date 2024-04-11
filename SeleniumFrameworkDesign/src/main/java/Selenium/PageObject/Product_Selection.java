
package Selenium.PageObject;

import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import Selenium.ReusableComponents.Wait;

public class Product_Selection extends Wait{
	WebDriver driver;
	public Product_Selection(WebDriver driver)//constructor, it initialize first before anything in the class
	{
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath="//h5/b")
	List<WebElement> products;
	By waitLocator=By.xpath("//div[@aria-label='Product Added To Cart']");
	By productLocator=By.cssSelector("[class*='w-10']");
	By AddToCart=By.cssSelector("[routerlink*='cart']");
	@FindBy(css="[routerlink*='cart']")
	WebElement clk;
	
	public void selection(String p1,String p2) throws InterruptedException		
	{
		for(int i=0;i<products.size();i++)
		{	
			if(products.get(i).getText().equals(p1))
			{
				driver.findElements(productLocator).get(i).click();
				until(waitLocator); 
			}
			if(products.get(i).getText().equals(p2))
			{
				driver.findElements(productLocator).get(i).click();
				until(waitLocator); 
			}
		}
		Thread.sleep(1000);
		clk.click();
	} 
	
	By PlaceOrder=By.xpath("//li[@class='totalRow'] //button");
	@FindBy(xpath="//div/div/h3")
	List<WebElement> orders;
	@FindBy(xpath="//li[@class='totalRow'] //button")
	WebElement button; 
		
	public boolean ToCheck(String p1)
	{
		boolean yes = false;
		for(int i=0;i<orders.size();i++)
			if(orders.get(i).getText().equals(p1))
				yes=true;
		return yes; 
	}
	public PersonalDetails checkout() throws InterruptedException
	{
		JavascriptExecutor js=(JavascriptExecutor)driver;
		js.executeScript("window.scroll(0,900)");
		Thread.sleep(1600);
		CheckoutButton(PlaceOrder); //wait for the button to be click-able
		button.click();
		PersonalDetails pd =new PersonalDetails(driver);
		return pd;
	}	
}
