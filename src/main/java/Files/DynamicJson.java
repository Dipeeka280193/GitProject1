package Files;

import static io.restassured.RestAssured.given;

import java.nio.file.Files;
import java.nio.file.Paths;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class DynamicJson {
	
	@Test(dataProvider="BooksData")

	public void addBook(String isbn,String aisle)

	{

		RestAssured.baseURI = "http://216.10.245.166";

		Response resp = given().

				header("Content-Type", "application/json").

				body(Payload.AddBook( isbn  ,  aisle )).

				when().

				post("/Library/Addbook.php").

				then().assertThat().statusCode(200).

				extract().response();

		JsonPath js = ReusableMethod.rawToJson(resp);

		String id = js.get("ID");

		System.out.println(id);

	}

	// deleteBOok

	@DataProvider(name = "BooksData")

	public Object[][] getData()

	{

		// array=collection of elements

		// multidimensional array= collection of arrays

		return new Object[][] { { "odsnuy", "2435" }, { "ytyugf", "1239" }, { "eyfght", "9745" } };

	}

}
