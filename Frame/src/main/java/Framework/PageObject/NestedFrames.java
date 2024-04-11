package Framework.PageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class NestedFrames {
	WebDriver driver;
	public NestedFrames(WebDriver driver)
	{
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	@FindBy(name="frame-top")
	WebElement Frame1;
	@FindBy(name="frame-middle")
	WebElement Frame2;
	@FindBy(id="content")
	WebElement context;
	
	public void nestedFrame()
	{
		driver.switchTo().frame(Frame1);
		driver.switchTo().frame(Frame2);
		System.out.println(context.getText());
		driver.switchTo().defaultContent();
	}

}
