package Demo;

import static io.restassured.RestAssured.given;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.testng.Assert;

import Pojo.LoginRequest;
import Pojo.LoginResponse;
import Pojo.OrderDetail;
import Pojo.Orders;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.specification.RequestSpecification;

public class EcommerceAPITest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		RequestSpecification req = new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com")
				.setContentType(ContentType.JSON).build();
		
		LoginRequest loginrequest = new LoginRequest();
		loginrequest.setUserEmail("postman28@gmail.com");
		loginrequest.setUserPassword("Pass@123");
		
	
		RequestSpecification reqLogin = given().log().all().spec(req)
				.body(loginrequest);
		LoginResponse loginrespose = reqLogin.when().post("/api/ecom/auth/login").then().extract().response()
				.as(LoginResponse.class);
		
		System.out.println(loginrespose.getToken());
		String token = loginrespose.getToken();
		System.out.println(loginrespose.getUserId());
		String userId =loginrespose.getUserId();
		System.out.println(loginrespose.getMessage());
		
		//Add product

	RequestSpecification addProductBaseReq = new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com")
				.addParam("authorization", token).build();
		
		RequestSpecification reqAddProduct = given().log().all().spec(addProductBaseReq).param("productName", "Laptop")
				.param("productAddedBy", userId).param("productCategory", "fashion")
				.param("productSubCategory", "shirts").param("productPrice", "11500")
				.param("productDescription", "Lenova").param("productFor", "men")
				.multiPart("productImage",new File("C:\\Users\\Dipeeka Mendhe\\Downloads\\Laptop.png"));
				
				String addProductResponse =reqAddProduct.when().post("/api/ecom/product/add-product").
				then().log().all().extract().response().asString();
				JsonPath js = new JsonPath(addProductResponse);
				String productId =js.get("productId");
				
				
				
				//Create Order
				RequestSpecification createOrderBaseReq=	new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com")
						.addHeader("authorization", token).setContentType(ContentType.JSON)
						.build();
				OrderDetail orderDetail = new OrderDetail();
				orderDetail.setCountry("India");
				orderDetail.setProductOrderedId(productId);
				
				List<OrderDetail> orderDetailList = new ArrayList<OrderDetail> ();
				orderDetailList.add(orderDetail);	
				Orders orders = new Orders();
				orders.setOrders(orderDetailList);
				
			RequestSpecification createOrderReq=given().log().all().spec(createOrderBaseReq).body(orders);

			String responseAddOrder = createOrderReq.when().post("/api/ecom/order/create-order").then().log().all().extract().response().asString();
			System.out.println(responseAddOrder);



			//Delete Product

			RequestSpecification deleteProdBaseReq=	new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com")
			.addHeader("authorization", token).setContentType(ContentType.JSON)
			.build();

			RequestSpecification deleteProdReq =given().log().all().spec(deleteProdBaseReq).pathParam("productId",productId);

			String deleteProductResponse = deleteProdReq.when().delete("/api/ecom/product/delete-product/{productId}").then().log().all().
			extract().response().asString();

			JsonPath js1 = new JsonPath(deleteProductResponse);

			Assert.assertEquals("Product Deleted Successfully",js1.get("message"));




		
}
}
