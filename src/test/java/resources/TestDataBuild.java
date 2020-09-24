package resources;

import static io.restassured.RestAssured.given;

import java.util.ArrayList;
import java.util.List;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import pojo.AddPlace;
import pojo.Location;

public class TestDataBuild {
	
	public AddPlace addPlacePayLoad() {
		AddPlace p=new AddPlace();
		p.setAccuracy(50);
		p.setAddress("29, side layout, cohen 09");
		p.setLanguage("French-IN");
		p.setName("Frontline house");
		p.setPhone_number("(+91) 983 893 3937");
		p.setWebsite("http://google.com");
		
		List<String> al=new ArrayList<String>();
		al.add("shoe park");
		al.add("shop");
		p.setTypes(al);

		Location l= new Location();
		l.setLat(-38.383494);
		l.setLng( 33.427362);
		p.setLocation(l);
		
		return p;
	
	}

}
