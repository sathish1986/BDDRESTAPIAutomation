package resources;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Properties;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class Utils {
	
	public static RequestSpecification req;
	
	public RequestSpecification requestSpecification() throws IOException {
		if(req == null) {
		PrintStream log = new PrintStream(new FileOutputStream("logging.txt"));
	//	RestAssured.baseURI= "https://rahulshettyacademy.com";
		    req = new RequestSpecBuilder()				
				.setContentType(ContentType.JSON)
				.setBaseUri(getGlobalValue("baseURI"))
				.addQueryParam("key", "qaclick123")
			    .addFilter(RequestLoggingFilter.logRequestTo(log)) 
			    .addFilter(ResponseLoggingFilter.logResponseTo(log))
				.build();
		    
		    
		    return req;
		}
		return req;
		
	}
	
	public  ResponseSpecification responseSpecification() {
		ResponseSpecification resp= new ResponseSpecBuilder()
				.expectStatusCode(200)
				.expectContentType(ContentType.JSON)
				.build();
		return resp;
	}
	
	
	public static String getGlobalValue(String key) throws IOException {
		
		Properties prop= new Properties();
		FileInputStream fis = new FileInputStream("E:\\Selenium Projects\\BDDRESTAPIAutomation\\src\\test\\java\\resources\\global.properties");
	    prop.load(fis);
	    return prop.getProperty(key);
	     
	}
	
	public String getJsonPath(Response response, String key) {
		  String respon= response.asString();
		  JsonPath jp= new JsonPath(respon);
		  return jp.get(key).toString();

	}
	
	
	

}
