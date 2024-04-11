package Selenium.Tests;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import Selenium.PageObject.OrderPage;
import Selenium.PageObject.PersonalDetails;
import Selenium.PageObject.Product_Selection;
import Selenium.TestComponents.BaseTest;

public class end_to_end extends BaseTest{

	//String P1="IPHONE 13 PRO";
	//String P2="ADIDAS ORIGINAL";
	@Test(dataProvider="data",groups= {"purchase"})
	public void submitOrder(HashMap<String,String> data) throws IOException, InterruptedException 
	{
		//LogIN login=Launch();
		Product_Selection select=login.details(data.get("email"),data.get("psw"));
		select.selection(data.get("p1"),data.get("p2"));
		boolean yes=select.ToCheck(data.get("p1"));
		Assert.assertTrue(yes); 
		PersonalDetails pd=select.checkout();
		pd.details("IND");
		String Actual=pd.orderID(data.get("p1"),data.get("p2"));
		Assert.assertTrue(Actual.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
		//driver.close();                         
	}
	
	@Test(dependsOnMethods= {"submitOrder"},dataProvider="data")
	public void OrderHistory(HashMap<String,String> data) throws InterruptedException
	{
		Product_Selection select=login.details(data.get("email"),data.get("psw"));
		OrderPage op= select.orderpageTrigger();
		boolean match=op.OrderPageInfoCheck(data.get("p1"));
		Assert.assertTrue(match);
		System.out.println("Item present");
	}
	
	@DataProvider
	@Test
	public Object[][] data() throws IOException
	{
		List<HashMap<String,String>> data=getData(System.getProperty("user.dir")+"\\src\\test\\java\\Selenium\\Data\\PurchaseOrder.json");
		return new Object[][] {{data.get(0)},{data.get(1)}};
	}
	
	
//	@DataProvider
//	@Test
//	public Object[][] data()
//	{
//		HashMap<String,String> map=new HashMap<String,String>();
//		map.put("email", "ketan@gmaill.com");
//		map.put("psw", "Cc_12345");
//		map.put("p1", "IPHONE 13 PRO");
//		map.put("p2", "ADIDAS ORIGINAL");
//		HashMap<String,String> map1=new HashMap<String,String>();
//		map1.put("email", "ketan@gmaill.com");
//		map1.put("psw", "Cc_12345");
//		map1.put("p1", "ZARA COAT 3");
//		map1.put("p2","IPHONE 13 PRO");
//		return new Object[][] {{map},{map1}};
//	}
	/*other way
	 * @DataProvider
	 * @Test
	 * public Object[][] data()
	 * {
		 return new Object[][] {{"ketan@gmaill.com", "Cc_12345","IPHONE 13 PRO","ADIDAS ORIGINAL"},
							   {"ketan@gmaill.com", "Cc_12345","ZARA COAT 3","IPHONE 13 PRO"}};
	 * }
	 */
}

