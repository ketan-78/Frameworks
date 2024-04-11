package Framework.PageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class DragAndDropFrames {
	WebDriver driver;
	public DragAndDropFrames(WebDriver driver)
	{
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	@FindBy(xpath="//iframe[@class='demo-frame']")
	WebElement FrameElement;
	@FindBy(id="draggable")
	WebElement source;
	@FindBy(id="droppable")
	WebElement target;
	public void FrameDragNDrop()
	{
		driver.switchTo().frame(FrameElement);
		Actions a = new Actions(driver);
		a.dragAndDrop(source,target).build().perform();
		driver.switchTo().defaultContent();
	}
	
	
	
}
