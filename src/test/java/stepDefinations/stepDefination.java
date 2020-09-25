package stepDefinations;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;
import static org.hamcrest.Matchers.*;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import pojo.AddPlace;
import pojo.Location;
import resources.APIResources;
import resources.TestDataBuild;
import resources.Utils;

public class stepDefination  extends Utils {
	
	RequestSpecification res;
	ResponseSpecification resp;
	Response response;
	
	TestDataBuild data= new TestDataBuild();

	

@Given("Add place Payload with {string} {string} {string}")
public void add_place_Payload_with(String name, String language, String address) throws IOException {
		// Request Spec Builder-- reusable steps for all HTTPs methods		
		// Response Spec Builder	reusable steps for all HTTPs methods
		         res=given().spec(requestSpecification())
				 .body(data.addPlacePayLoad( name,  language,  address));
	}

@When("user calls {string} with {string} HTTP request")
public void user_calls_with_HTTP_request(String resource, String method) {
	
		APIResources apiResource= APIResources.valueOf(resource);
		
		System.out.println(apiResource.getResource());
		if (method.equalsIgnoreCase("POST")) 
		response=res.when().post(apiResource.getResource());
		else if(method.equalsIgnoreCase("GET"))
			response=res.when().post(apiResource.getResource());
			    			  
	}

	@Then("the API call is success with status code {int}")
	public void the_API_call_is_success_with_status_code(Integer int1) {
		 //  .then().spec(responseSpecification()).
	      // 	  extract().response();	
		assertEquals(response.statusCode(), 200);

	}

	@Then("{string} in response body is {string}")
	public void in_response_body_is(String key, String expectedValue) {
	
	  assertEquals(getJsonPath(response, key),expectedValue);
	}
	
	@Then("Verify place id created maps to {string} using {string}")
	public void verify_place_id_created_maps_to_using(String expectedName, String resource) throws IOException {
	    // prep request spec		
		String place=getJsonPath(response, "place_id");
		res=given().spec(requestSpecification()).queryParam("place_id",place );
		
		user_calls_with_HTTP_request(resource,  "GET");
		String actualName =getJsonPath(response, "name");	
		assertEquals(actualName, expectedName);		
	}
	
	
	
	
	
}
