package Framework.PageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class IFrames {
	WebDriver driver;
	public IFrames(WebDriver driver)
	{
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	@FindBy(id="mce_0_ifr")
	WebElement Frame1;
	@FindBy(id="tinymce")
	WebElement Element;
	public void IFramess()
	{
		driver.switchTo().frame(Frame1);
		Element.clear();
		Element.sendKeys("Hello");
		driver.switchTo().defaultContent();
	}

}
