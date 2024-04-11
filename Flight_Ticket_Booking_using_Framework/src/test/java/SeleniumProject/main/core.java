package SeleniumProject.main;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import SeleniumProject.Test.BaseTest;

public class core extends BaseTest{
	int AD=6;
	@Test(dataProvider="data")
	public void FlightBooking(HashMap<String,String> data) throws InterruptedException
	{
		com.BasicOPtions(data.get("countryIntials"), data.get("country"), data.get("tripRoute"), data.get("DiscountOffer"));
		com.Journey(data.get("DC"), data.get("AC"));
		com.Dates(data.get("DepartureMonth"), data.get("ArrivalMonth"), data.get("DepartureDate"), data.get("ArrivalDate"));
		com.Passengers(AD);
		Thread.sleep(1000);
		com.Currency(data.get("CurrencyUnit"));
	
	}
	@DataProvider
	@Test
	public Object[][] data() throws IOException
	{
		List<HashMap<String,String>> data=getData(System.getProperty("user.dir")+"\\src\\test\\java\\SeleniumProject\\Data\\FlightDetails.json");
		return new Object[][] {{data.get(0)},{data.get(1)},{data.get(2)}};
	}

}
