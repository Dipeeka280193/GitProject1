package Demo;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import  static io.restassured.RestAssured.*;

import java.util.ArrayList;
import java.util.List;

import Pojo.AddPlace;
import Pojo.Location;

public class SpecBuilderTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		RestAssured.baseURI = "https://rahulshettyacademy.com";
		
		AddPlace p = new AddPlace();
		
		p.setAccuracy(50);
		p.setAdress("29, side layout, cohen 09");
		p.setLanguage("French-IN");
		p.setName("Frontline house");
		p.setPhone_number("(+91) 983 893 3937");
		p.setWebsite("http://google.com");
		List<String> myList =new ArrayList<String>();
		myList.add("shoe park");
		myList.add("shop");

		p.setTypes(myList);
		
		Location l =new Location();
		l.setLat(-38.383494);
		l.setLng(33.427362);

		p.setLocation(l);

		
		RequestSpecification req = new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com").addQueryParam("key", "qaclick123")
		.setContentType(ContentType.JSON).build();
		
		ResponseSpecification resspec=new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();
		RequestSpecification res = given().spec(req)
				.body(p);
		
		

		
				Response response = res.when().post("/maps/api/place/add/json").
				then().spec(resspec).extract().response();

				String responseString=response.asString();
				System.out.println(responseString);

	}

}
