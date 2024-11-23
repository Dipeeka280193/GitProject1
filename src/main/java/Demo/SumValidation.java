package Demo;

import org.testng.Assert;
import org.testng.annotations.Test;

import Files.Payload;
import io.restassured.path.json.JsonPath;

public class SumValidation {
	
	@Test
	public void SumOfCourses()
	{
		int sum =0;
		JsonPath js = new JsonPath(Payload.CoursePrice());
		int count = js.getInt("courses.size()");
		
		for(int i=0 ; i<count ; i++)
		{
			
			int price =js.getInt("courses["+i+"].price");
			int copies = js.get("courses["+i+"].copies");
			int amount = copies * price;
			
			System.out.println("Amount are :" +amount);
			sum = sum+amount;
			
		}
		System.out.println(sum);
		int purchaseamount = js.getInt("dashboard.purchaseAmount");
		Assert.assertEquals(sum, purchaseamount);
	}

}
