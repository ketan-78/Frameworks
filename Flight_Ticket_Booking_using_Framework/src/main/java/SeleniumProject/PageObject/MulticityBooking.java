package SeleniumProject.PageObject;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import SeleniumProject.ReuseableComponents.wait;

public class MulticityBooking extends wait {
	WebDriver driver;
	public MulticityBooking(WebDriver driver)
	{
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	@FindBy(id="autosuggest") 
	WebElement autosuggest;
	@FindBy(css="#ui-id-1 a") 
	List<WebElement> options;
	@FindBy(css="#ctl00_mainContent_rbtnl_Trip_2") 
	WebElement trip;
	@FindBy(css="[class*='home-Discount']") 
	List<WebElement> Discount;
	@FindBy(id="MultiCityModelAlert")
	WebElement MultiAlert;
	
	
	//      MultiCityModelAlert
	public void MulticityOPtions(String countryIntials, String country, String DiscountOffer) throws InterruptedException
	{
		trip.click();
		MultiAlert.click();
		waitHere();
		autosuggest.sendKeys(countryIntials);
		options.stream().filter(s->s.getText().trim().equalsIgnoreCase(country)).forEach(s->s.click());
		Discount.stream().filter(s->s.getText().trim().contains(DiscountOffer)).limit(1).forEach(s->s.click());
	}
	
	@FindBy(xpath="//div[@id='ctl00_mainContent_ddl_originStation1_CTNR'] //li/a")
	List<WebElement> DepartureCities1;
	@FindBy(xpath="//div[@id='glsctl00_mainContent_ddl_destinationStation1_CTNR'] //li/a")
	List<WebElement> ArrivalCities1;
	@FindBy(css="[name*='originStation1_CTXT']")
	WebElement Departure1;
	@FindBy(css="#ctl00_mainContent_ddl_originStation2_CTXT")
	WebElement Departure2;
	@FindBy(css="#ctl00_mainContent_ddl_originStation3_CTXT")
	WebElement Departure3;
	@FindBy(css="#glsctl00_mainContent_ddl_originStation2_CTNR a")
	List<WebElement> DepartureCities2;
	@FindBy(css="#glsctl00_mainContent_ddl_destinationStation2_CTNR a")
	List<WebElement> ArrivalCities2;
	@FindBy(css="#glsctl00_mainContent_ddl_originStation3_CTNR a")
	List<WebElement> DepartureCities3;
	@FindBy(css="#glsctl00_mainContent_ddl_destinationStation3_CTNR a")
	List<WebElement> ArrivalCities3;
	@FindBy(css="#ctl00_mainContent_view_date1")
	WebElement DD1;

	
	public void MulticityFlightBooking(String DC1,String AC1,String DC2,String AC2,String DC3,String AC3)
	{
		Departure1.click();
		DepartureCities1.stream().filter(s->s.getText().contains(DC1)).limit(1).forEach(s->s.click());
		ArrivalCities1.stream().filter(s->s.getText().contains(AC1)).limit(1).forEach(s->s.click());
		Departure2.click();
		DepartureCities2.stream().filter(s->s.getText().contains(DC2)).limit(1).forEach(s->s.click());
		ArrivalCities2.stream().filter(s->s.getText().contains(AC2)).limit(1).forEach(s->s.click());
		Departure3.click();
		DepartureCities3.stream().filter(s->s.getText().contains(DC3)).limit(1).forEach(s->s.click());
		ArrivalCities3.stream().filter(s->s.getText().contains(AC3)).limit(1).forEach(s->s.click());
	
	}
	@FindBy(id="ctl00_mainContent_view_date1")
	WebElement calender;
	
	public void Dates(String Dmonth,String Ddate) throws InterruptedException
	{
		int k =0;
		calender.click();
		for(int i=0;i<2;i++)
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
