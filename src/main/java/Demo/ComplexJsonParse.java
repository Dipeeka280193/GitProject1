package Demo;

import Files.Payload;
import io.restassured.path.json.JsonPath;

public class ComplexJsonParse {
	
	public static void main(String[] args) {
		
		JsonPath js = new JsonPath(Payload.CoursePrice());
		
		///print no of courses returned by API
		
		int count = js.getInt("courses.size()");
		System.out.println("Number of courses are: " +count);
		
		//print purchase amount
		int totalAmount = js.getInt("dashboard.purchaseAmount");
		System.out.println("Tota purchase amount is: "+totalAmount);
		
		
		//print title of first course
		String title = js.get("courses[1].title");
		System.out.println("Title of first course is: "+title);
		
		
		//print all courses title and their respective prices
		
		for(int i=0 ; i<count ; i++)
		{
			String courseTitle = js.get("courses["+i+"].title");
			System.out.println(js.get("courses["+i+"].price").toString());
			
			System.out.println("course Title are :" +courseTitle);
			
		}
		
		//print no of copies sold by RPA
		
		System.out.println("Print No of copies sold by RPA courses:-");
		
		for(int i=0 ; i<count ; i++)
		{
			String courseTitle = js.get("courses["+i+"].title");
			if(courseTitle.equalsIgnoreCase("RPA"))
			{
				int copies = js.get("courses["+i+"].copies");
				System.out.println(copies);
				break;
			}
			
			System.out.println("course Title are :" +courseTitle);
			
		}
		
	}
	
	

}
