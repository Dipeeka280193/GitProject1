package Demo;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.equalTo;

import Files.ReusableMethod;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;


public class StaticJson {
	
	
	@Test

	public void addBook() throws IOException

	{

	RestAssured.baseURI="https://rahulshettyacademy.com/";

	/*
	 * String resp= given().
	 * 
	 * header("Content-Type","application/json").
	 * 
	 * body(
	 * GenerateStringFromResource("C:\\Users\\Dipeeka Mendhe\\Desktop\\documents.json"
	 * )).
	 * 
	 * when().
	 * 
	 * post("/Library/Addbook.php").
	 * 
	 * then().assertThat().statusCode(200).
	 * 
	 * extract().response().asString();
	 * 
	 * JsonPath js= ReusableMethod.rawToJson1(resp);
	 * 
	 * String id=js.get("ID");
	 * 
	 * System.out.println(id);
	 */
	
	String response = given().log().all().queryParam("key", "qaclick123").header("Content-Type", "application/json")
			.body(GenerateStringFromResource("C:\\Users\\Dipeeka Mendhe\\Desktop\\documents.json")).when().post("maps/api/place/add/json").then().assertThat().statusCode(200)
			.body("scope", equalTo("APP")).header("server", "Apache/2.4.52 (Ubuntu)").extract().response()
			.asString();

	System.out.println(response);
	JsonPath js = new JsonPath(response); // for parsing Json
	String placeId = js.getString("place_id");

	System.out.println(placeId);

	   

	   //deleteBOok

	}

	public static String GenerateStringFromResource(String path) throws IOException {



	    return new String(Files.readAllBytes(Paths.get(path)));



	}

	

}
