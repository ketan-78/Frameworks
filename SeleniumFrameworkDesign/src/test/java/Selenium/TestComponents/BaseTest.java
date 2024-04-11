package Selenium.TestComponents;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import Selenium.PageObject.LogIN;
import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseTest 
{
	public WebDriver driver;
	public LogIN login;
	public WebDriver intialization() throws IOException
	{	//properties class
		Properties prop=new Properties();//to access properties file that we created in main java
		//FileInputStream is used to convert properties file into stream by giving the path of the file.
		FileInputStream file=new FileInputStream(System.getProperty("user.dir")+"\\src\\main\\java\\Selenium\\Resources\\GlobalData.properties");
		prop.load(file);//load take only stream input
		//In below command we are using java ternary operator i.e.,
		//variable=expression1 ? expression2: expression3
		//If condition is true then the result is expression2
		//If condition is not true then the result is expression3
		String BrowserName=System.getProperty("browser")!=null ? System.getProperty("browser") :prop.getProperty("browser");
		//If not null then use browser from mvn-cmd other wise use property from global data properties.
		//String BrowserName=prop.getProperty("browser");
		if(BrowserName.contains("chrome"))
		{
			ChromeOptions options=new ChromeOptions();
			if(BrowserName.contains("headless"))
				options.addArguments("headless");
			driver=new ChromeDriver(options);
			driver.manage().window().setSize(new Dimension(1440,900));//to run browser in full screen in headless
		}
		else if(BrowserName.contains("firefox"))
		{
			FirefoxOptions options=new FirefoxOptions();
			if(BrowserName.contains("headless"))
				options.addArguments("headless");
			System.setProperty("webdriver.gecko.driver", "C:\\Users\\ketan\\Downloads\\geckodriver-v0.34.0-win-aarch64");
			driver=new FirefoxDriver(options);
			driver.manage().window().setSize(new Dimension(1440,900));
		}
		else if(BrowserName.contains("edge"))
		{
			EdgeOptions options=new EdgeOptions();
			if(BrowserName.contains("headless"))
				options.addArguments("headless");
			driver=new EdgeDriver(options);
			driver.manage().window().setSize(new Dimension(1440,900));
		}
		else if(BrowserName.equalsIgnoreCase("opera"))
		{ 
			driver=WebDriverManager.operadriver().create();
		}
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		driver.manage().window().maximize();
		return driver; 
	}
	
	public List<HashMap<String, String>> getData(String filepath) throws IOException
	{
		//json to String
		File file =new File(filepath);
		String jsonfile=FileUtils.readFileToString(file,StandardCharsets.UTF_8);
		//String to HashMap---->Jackson databind dependency 
		ObjectMapper mapper=new ObjectMapper(); 
		List<HashMap<String,String>> data=mapper.readValue(jsonfile, new TypeReference<List<HashMap<String,String>>>(){
		});
		return data;
	}
	
	@BeforeMethod(alwaysRun=true)
	public LogIN Launch() throws IOException
	{
		driver=intialization();
		driver.get("https://rahulshettyacademy.com/client");
		login=new LogIN(driver);
		return login;
	}
	
	public String takeScreenshot(String ErrorReason,WebDriver driver) throws IOException
	{
		TakesScreenshot ss=(TakesScreenshot)driver; 
		File source =ss.getScreenshotAs(OutputType.FILE);
		File file=new File(System.getProperty("user.dir")+"\\reports\\"+ErrorReason+".png");
		FileUtils.copyFile(source, file);
		return System.getProperty("user.dir")+"\\reports\\"+ErrorReason+".png";
		
	}
	
	@AfterMethod(alwaysRun=true)
	public void closure()
	{
		driver.close();
	}
}
