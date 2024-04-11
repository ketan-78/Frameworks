package SeleniumProject.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import SeleniumProject.PageObject.CompleteDetails;
import SeleniumProject.PageObject.MulticityBooking;
import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseTest 
{
	public WebDriver driver;
	public CompleteDetails com;
	public MulticityBooking Multi;
	public WebDriver intialization() throws IOException
	{	//properties class
		Properties prop=new Properties();
		FileInputStream file=new FileInputStream(System.getProperty("user.dir")+"\\src\\main\\java\\SeleniumProject\\Resources\\GlobalData.properties");
		prop.load(file);
		String BrowserName=System.getProperty("browser")!=null ? System.getProperty("browser") :prop.getProperty("browser");
		if(BrowserName.contains("chrome"))
			{
			driver=new ChromeDriver();
			}
		else if(BrowserName.contains("firefox"))
		{
			System.setProperty("webdriver.gecko.driver", "C:\\Users\\ketan\\Downloads\\geckodriver-v0.34.0-win-aarch64");
			driver=new FirefoxDriver();
		}
		else if(BrowserName.contains("edge"))
			driver=new EdgeDriver();
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
	public List<HashMap<String, String>> getMulticityData(String filepath) throws IOException
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
	public CompleteDetails Launch() throws IOException
	{
		driver=intialization();
		driver.get("https://rahulshettyacademy.com/dropdownsPractise/");
		com=new CompleteDetails(driver);
		Multi=new MulticityBooking(driver);
		return com;
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
