package SeleniumProject.main;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import SeleniumProject.Test.BaseTest;

public class Multicity extends BaseTest{

	int AD=6;
	int ArrivalDate=15;
	@Test(dataProvider="Multicitydata")
	public void FlightBookingMultiCity(HashMap<String,String> Multicity) throws InterruptedException
	{
		Multi.MulticityOPtions(Multicity.get("countryIntials"), Multicity.get("country"), Multicity.get("DiscountOffer"));
		Multi.MulticityFlightBooking(Multicity.get("DC1"), Multicity.get("AC1"),Multicity.get("DC2"), Multicity.get("AC2"),Multicity.get("DC3"), Multicity.get("AC3"));
		Multi.Dates(Multicity.get("DepartureMonth"), Multicity.get("DepartureDate"));
		Multi.Passengers(AD);
		Thread.sleep(1000);
		Multi.Currency(Multicity.get("CurrencyUnit"));
	
	}
	@DataProvider
	@Test
	public Object[][] Multicitydata() throws IOException
	{
		List<HashMap<String,String>> data=getMulticityData(System.getProperty("user.dir")+"\\src\\test\\java\\SeleniumProject\\Data\\Multicity.json");
		return new Object[][] {{data.get(0)},{data.get(1)}};
	}

}
