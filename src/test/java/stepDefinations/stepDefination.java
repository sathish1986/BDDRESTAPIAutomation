package stepDefinations;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;
import static org.hamcrest.Matchers.*;
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
import resources.TestDataBuild;
import resources.Utils;

public class stepDefination  extends Utils {
	
	RequestSpecification res;
	ResponseSpecification resp;
	Response response;
	
	TestDataBuild data= new TestDataBuild();

	@Given("Add place Payload")
	public void add_place_Payload() {		
		// Request Spec Builder-- reusable steps for all HTTPs methods		
		// Response Spec Builder	reusable steps for all HTTPs methods
		         res=given().spec(requestSpecification())
				 .body(data.addPlacePayLoad());
	}

	@When("user calls {string} with POST HTTP request")
	public void user_calls_with_POST_HTTP_request(String string) {
		 response=res.when()
			     .post("maps/api/place/add/json")		       
			     .then().spec(responseSpecification()).
		       	  extract().response();	
	}

	@Then("the API call is success with status code {int}")
	public void the_API_call_is_success_with_status_code(Integer int1) {
		assertEquals(response.statusCode(), 200);

	}

	@Then("{string} in response body is {string}")
	public void in_response_body_is(String key, String expectedValue) {
	  String respon= response.asString();
	  JsonPath jp= new JsonPath(respon);
	  assertEquals(jp.get(key).toString(),expectedValue);
	  
		
		
	}
	
	
	
}
