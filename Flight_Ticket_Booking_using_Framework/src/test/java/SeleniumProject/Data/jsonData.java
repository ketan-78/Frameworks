package SeleniumProject.Data;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class jsonData {

	public List<HashMap<String, String>> getData() throws IOException
	{
		//json to String
		File file =new File(System.getProperty("user.dir")+"\\src\\test\\java\\SeleniumProject\\Data\\FlightDetails.json");
		String jsonfile=FileUtils.readFileToString(file,StandardCharsets.UTF_8);
		//String to HashMap---->Jackson databind dependency 
		ObjectMapper mapper=new ObjectMapper(); 
		List<HashMap<String,String>> data=mapper.readValue(jsonfile, new TypeReference<List<HashMap<String,String>>>(){
		});
		return data;
	}
	public List<HashMap<String, String>> getMulticityData() throws IOException
	{
		//json to String
		File file =new File(System.getProperty("user.dir")+"\\src\\test\\java\\SeleniumProject\\Data\\Multicity.json");
		String jsonfile=FileUtils.readFileToString(file,StandardCharsets.UTF_8);
		//String to HashMap---->Jackson databind dependency 
		ObjectMapper mapper=new ObjectMapper(); 
		List<HashMap<String,String>> data=mapper.readValue(jsonfile, new TypeReference<List<HashMap<String,String>>>(){
		});
		return data;
	}
}
