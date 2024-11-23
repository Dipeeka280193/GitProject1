	package Demo;

import static io.restassured.RestAssured.given;

import java.io.File;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

public class BugTests {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		RestAssured.baseURI = "https://dipeekamendhe.atlassian.net/";
		
		String CreateIssueResponse = given()
				.header("Content-Type","application/json")
				.header("Authorization","Basic ZGlwZWVrYW1lbmRoZUBnbWFpbC5jb206QVRBVFQzeEZmR0YwZWR6V0xUdUtmMW5wcjJRNDUzRk4tU1ZqTkR0STBxVmZpblNuMmphMmc2dWRTQkZ1SGNEMFdtNTRES1JYRlNuVExGVnNZWHdfSWZJYWdTUWsxbDhGTzlGamlIYzNwUlJWU00xeUlsMjczVWxJaWNNNVhSbFFMVGswUGtFX0tNU28xSjZNYV9JaG5lLXQxb01ZTUpkMV93c2xBdGhGNFRETnFtbnp4RG82Qi1NPTU0OTYxMTgx")
				.body("{\r\n"
						+ "    \"fields\": {\r\n"
						+ "       \"project\":\r\n"
						+ "       {\r\n"
						+ "          \"key\": \"SCRUM\"\r\n"
						+ "       },\r\n"
						+ "       \"summary\": \"Websites are not working - Automation Rest Assuered\",\r\n"
						+ "       \r\n"
						+ "       \"issuetype\": {\r\n"
						+ "          \"name\": \"Bug\"\r\n"
						+ "       }\r\n"
						+ "   }\r\n"
						+ "}")
					.log().all()
					.post("rest/api/2/issue").then().log().all().assertThat().statusCode(201).contentType("application/json")
					.extract().response().asString();
		
		JsonPath js = new JsonPath(CreateIssueResponse);
		String issueId = js.getString("id");
		
		System.out.println(issueId);
		
		
		given()
		.pathParam("key", issueId)
		.header("X-Atlassian-Token","no-check")
		.header("Authorization","Basic ZGlwZWVrYW1lbmRoZUBnbWFpbC5jb206QVRBVFQzeEZmR0YwZWR6V0xUdUtmMW5wcjJRNDUzRk4tU1ZqTkR0STBxVmZpblNuMmphMmc2dWRTQkZ1SGNEMFdtNTRES1JYRlNuVExGVnNZWHdfSWZJYWdTUWsxbDhGTzlGamlIYzNwUlJWU00xeUlsMjczVWxJaWNNNVhSbFFMVGswUGtFX0tNU28xSjZNYV9JaG5lLXQxb01ZTUpkMV93c2xBdGhGNFRETnFtbnp4RG82Qi1NPTU0OTYxMTgx")
		.multiPart("file", new File("C:\\Users\\Dipeeka Mendhe\\Downloads\\Screenshot 2024-09-08 180350.png")).log().all()
		.post("rest/api/2/issue/{key}/attachments").then().log().all().assertThat().statusCode(200);

				

	}

}
