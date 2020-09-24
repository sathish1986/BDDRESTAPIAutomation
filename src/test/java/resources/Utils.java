package resources;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class Utils {
	
	RequestSpecification req;
	
	public RequestSpecification requestSpecification() {
		RestAssured.baseURI= "https://rahulshettyacademy.com";
		    req = new RequestSpecBuilder()				
				.setContentType(ContentType.JSON)
				.setBaseUri("https://rahulshettyacademy.com")
				.addQueryParam("key", "qaclick123")
				.build();
		    
		    return req;
		
		
	}
	
	public  ResponseSpecification responseSpecification() {
		ResponseSpecification resp= new ResponseSpecBuilder()
				.expectStatusCode(200)
				.expectContentType(ContentType.JSON)
				.build();
		return resp;
	}

}
