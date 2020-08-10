package koneksatests;


import java.io.*;
import org.json.simple.*;
import org.json.simple.parser.*;

import demopages.Variables;

public class TestUtils {

	
	//Below function reads the content from a json file and return as JSONObject
	static public JSONObject  readJsonFile(String fileNameWithPath) {
	    JSONObject jsonObject = new JSONObject();
	    
		JSONParser parser = new JSONParser();
	      try {
	         Object obj = parser.parse(new FileReader(fileNameWithPath));
	         jsonObject = (JSONObject)obj;
	         
	      } catch(Exception e) {
	         e.printStackTrace();
	      }

		return jsonObject;
	   }
	
	public static void readBrowserAndDriverInfo() {
		JSONObject jsonObject= TestUtils.readJsonFile(Variables.browserJsonFilePath);
		
		if(! jsonObject.isEmpty()) {
			System.out.println((String) jsonObject.get("browser"));
			System.out.println((String) jsonObject.get("driver"));
			
			Variables.browser = (String) jsonObject.get("browser");
			Variables.driverPath = (String) jsonObject.get("driver");
		}
	}
}
