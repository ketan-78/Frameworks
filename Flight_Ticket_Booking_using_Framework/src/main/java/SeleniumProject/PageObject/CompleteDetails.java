package SeleniumProject.PageObject;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import SeleniumProject.ReuseableComponents.wait;

public class CompleteDetails extends wait {
	WebDriver driver;
	public CompleteDetails(WebDriver driver)//constructor, it initialize first before anything in the class
	{
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	} 
	@FindBy(css="#ui-id-1 a") 
	List<WebElement> options;
	@FindBy(css="#travelOptions td") 
	List<WebElement> trip;
	@FindBy(css="#travelOptions td:nth-child(1)") //for one way condition
	WebElement TripOption;
	@FindBy(css="[class*='home-Discount']") 
	List<WebElement> Discount;
	@FindBy(id="autosuggest") 
	WebElement autosuggest;
	@FindBy(css="[name*='originStation1_CTXT']")
	WebElement Departure;
	@FindBy(xpath="//div[@id='ctl00_mainContent_ddl_originStation1_CTNR'] //li/a")
	List<WebElement> DepartureCities;
	@FindBy(xpath="//div[@id='glsctl00_mainContent_ddl_destinationStation1_CTNR'] //li/a")
	List<WebElement> ArrivalCities;
	
	
	public void BasicOPtions(String countryIntials, String country,String tripRoute, String DiscountOffer) throws InterruptedException
	{
		autosuggest.sendKeys(countryIntials);
		waitHere();
		options.stream().filter(s->s.getText().trim().equalsIgnoreCase(country)).forEach(s->s.click());
		trip.stream().filter(s->s.getText().contains(tripRoute)).forEach(s->s.click());
		Discount.stream().filter(s->s.getText().trim().contains(DiscountOffer)).limit(1).forEach(s->s.click());
	}
	public void Journey(String DC,String AC)
	{
		Departure.click();
		DepartureCities.stream().filter(s->s.getText().contains(DC)).limit(1).forEach(s->s.click());
		ArrivalCities.stream().filter(s->s.getText().contains(AC)).limit(1).forEach(s->s.click());
	}
	@FindBy(xpath="//div[@class='ui-datepicker-title']")
	List<WebElement> loopS;
	@FindBy(id="ctl00_mainContent_view_date2")
	WebElement ReturnDate;
	@FindBy(css="div[style*='opacity: 1']")
	WebElement RoundTrip;
	
	public void Dates(String Dmonth, String Amonth,String Ddate,String Adate) throws InterruptedException
	{
		int k =0;
		for(int i=0;i<loopS.size();i++)
		{
			List<WebElement> Months=driver.findElements(By.xpath("//div[@class='ui-datepicker-title']"));
			List<WebElement> dates1=driver.findElements(By.cssSelector("div[class*='group-first'] [data-handler='selectDay']"));
			List<WebElement> dates2 = driver.findElements(By.cssSelector("div[class*='group-last'] [data-handler='selectDay']"));
			
			WebElement firstgp=driver.findElement(By.cssSelector("div[class*='group-first'] [class='ui-datepicker-title']"));
			boolean present=Months.stream().anyMatch(s->s.getText().equals(Dmonth));
			if(present)
			{
				if(firstgp.getText().contains(Dmonth))
				{
					dates1.stream().filter(s->s.getText().contains(Ddate)).limit(1).forEach(s->s.click());
					break;
				}
				else
				{
					dates2.stream().filter(s->s.getText().contains(Ddate)).limit(1).forEach(s->s.click());
					break;
				}
			}
			k++;
			if(k==2)
			{
				for(int l =0;l<2;l++)
					driver.findElement(By.xpath("//a[@data-handler='next']")).click();
				i=-1;
				k=0;
			}
		}
	    if(driver.findElement(By.id("Div1")).getAttribute("style").contains("opacity: 1"))
		{
	    	k=0;
	    	waitHere();
			ReturnDate.click();
			for(int i=0;i<loopS.size();i++)
			{
				List<WebElement> Months=driver.findElements(By.xpath("//div[@class='ui-datepicker-title']"));
				List<WebElement> dates1=driver.findElements(By.cssSelector("div[class*='group-first'] [data-handler='selectDay']"));
				List<WebElement> dates2 = driver.findElements(By.cssSelector("div[class*='group-last'] [data-handler='selectDay']"));
				WebElement firstgp=driver.findElement(By.cssSelector("div[class*='group-first'] [class='ui-datepicker-title']"));
				boolean present=Months.stream().anyMatch(s->s.getText().equals(Amonth));
				if(present)
				{
					if(firstgp.getText().contains(Amonth))
					{
						dates1.stream().filter(s->s.getText().contains(Adate)).limit(1).forEach(s->s.click());
						break;
					}
					else
					{
						dates2.stream().filter(s->s.getText().contains(Adate)).limit(1).forEach(s->s.click());
						break;
					}
				}
				k++;
				if(k==2)
				{
					for(int l =0;l<2;l++)
						driver.findElement(By.xpath("//a[@data-handler='next']")).click();
					i=-1;
					k=0;
				}
			}
		}
	    
	}
	@FindBy(id="divpaxinfo")
	WebElement psgn;
	@FindBy(id="hrefIncAdt")
	WebElement Adults;
	@FindBy(id="btnclosepaxoption")
	WebElement Done;
	@FindBy(id="ctl00_mainContent_DropDownListCurrency")
	WebElement currencyUnit;
	@FindBy(id="ctl00_mainContent_btn_FindFlights")
	WebElement Search;
	
	public void Passengers(int Ad) 
	{
		psgn.click();
		for(int i=1;i<Ad;i++)
			Adults.click();
		Done.click();
		System.out.println(psgn.getText());
	}
	public void Currency(String Initials)
	{
		Select currency=new Select(currencyUnit);
		currency.selectByVisibleText(Initials);
		Search.click();
	}
	
}
