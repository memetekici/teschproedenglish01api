package techproedenglish01.techproedenglish01api;
import static io.restassured.RestAssured.*;

import static org.hamcrest.Matchers.*;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;
import org.testng.asserts.SoftAssert;

import io.restassured.response.Response;

public class Deneme2 extends DenemeTestBase{
	/*
	1)Create class and name it as "Delete02"                      
	2)When 
	I send DELETE Request to http://dummy.restapiexample.com/api/v1/delete/719
	Then 
	Status code should be 200
	The value of "status" key in response body should be "success"  
	The value of "message" key in response body should be "Successfully! Record has been deleted"
	Note 1: Use hard assertion
	Note 2: After given() please use contentType(ContentType.JSON)
	*/
	@Test
	public void delete() {
		spec04.pathParams("delete","delete","id",719);
		Response response = given().spec(spec04).auth().basic("admin", "password123").when().delete("/{delete}/{id}");
		response.prettyPrint();
		
		String reqBody1 = "success";
		String reqBody2 = "Successfully! Record has been deleted";
		
		response.then().assertThat().statusCode(200).body("status", equalTo(reqBody1),
				                                          "message", equalTo(reqBody2));
		
		SoftAssert softAssert = new SoftAssert();
		
		Map<String, String> actualReqBody = response.as(HashMap.class);
		softAssert.assertEquals(actualReqBody.get("status"), reqBody1);
		softAssert.assertTrue(actualReqBody.get("message").equals(reqBody2));
		
		
		softAssert.assertAll();
		
	}

}
