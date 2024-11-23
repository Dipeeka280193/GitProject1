package Demo;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.testng.Assert;

import Files.Payload;
import Files.ReusableMethod;

public class Basics {

	public static void main(String[] args) throws Exception {

		RestAssured.baseURI = "https://rahulshettyacademy.com/";

		String response = given().log().all().queryParam("key", "qaclick123").header("Content-Type", "application/json")
				.body(Payload.AddPlace()).when().post("maps/api/place/add/json").then().assertThat().statusCode(200)
				.body("scope", equalTo("APP")).header("server", "Apache/2.4.52 (Ubuntu)").extract().response()
				.asString();

		/*
		 * String response = given().log().all().queryParam("key",
		 * "qaclick123").header("Content-Type", "application/json") .body(new
		 * String(Files.readAllBytes(Paths.
		 * get("C:\\Users\\Dipeeka Mendhe\\Desktop\\documents.json")))).when().post(
		 * "maps/api/place/add/json").then().assertThat().statusCode(200) .body("scope",
		 * equalTo("APP")).header("server",
		 * "Apache/2.4.52 (Ubuntu)").extract().response() .asString();
		 */

		System.out.println(response);
		JsonPath js = new JsonPath(response); // for parsing Json
		String placeId = js.getString("place_id");

		System.out.println(placeId);

		// Update place new address

		String newAddress = "winter walk, Africa";

		given().log().all().queryParam("key", "qaclick123").header("Content-Type", "application/json")
				.body("{\r\n" + "\"place_id\":\"" + placeId + "\",\r\n" + "\"address\":\"" + newAddress + "\",\r\n"
						+ "\"key\":\"qaclick123\"\r\n" + "}")
				.when().put("maps/api/place/update/json").then().assertThat().log().all().statusCode(200)
				.body("msg", equalTo("Address successfully updated"));

		// Get Palce

		String getPlaceResponse = given().log().all().queryParam("key", "qaclick123").queryParam("place_id", placeId)
				.when().get("maps/api/place/get/json").then().log().all().assertThat().statusCode(200).extract()
				.response().asString();

		JsonPath js1 = ReusableMethod.rawToJson1(getPlaceResponse);

		// JsonPath js1 = new JsonPath(getPlaceResponse); // for parsing Json
		String actualaddress = js1.getString("address");

		System.out.println(actualaddress);
		Assert.assertEquals(actualaddress, "pacific ocean");

	}

}
