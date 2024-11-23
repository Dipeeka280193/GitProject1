package Files;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class ReusableMethod {
	
	
  
	public static JsonPath rawToJson(Response resp) {
		// TODO Auto-generated method stub
		
		JsonPath js1 = resp.jsonPath();
		return js1;
		
	}
	
	public static JsonPath rawToJson1(String response1) {
		// TODO Auto-generated method stub
		
		JsonPath js1 = new JsonPath(response1);
		return js1;
		
	}

}
