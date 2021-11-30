package techproedenglish01.techproedenglish01api;
import static io.restassured.RestAssured.*;

import java.util.HashMap;

import org.junit.Test;
import org.testng.asserts.SoftAssert;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class GetRequest16 extends TestBasedt{
	/*
	 When
                            I send GET Request to https://restful-booker.herokuapp.com/booking/3
                         Then
                                Status code is 200
                            And Content type is "application/json"
                            And Status line is "HTTP/1.1 200 OK"
                            And First name is Jim
                            And Total price is 623
                            And Deposit paid is true
                            And Checkin date is 2020-03-18
                            
                      Use De-Serialization to convert Json response body to a Map. 
                      Then by using the map and soft-assertion make assertions.  
	 */
	@Test
	public void get01() {
		spec02.pathParam("bookingid", 3);
		Response response = given().spec(spec02).when().get("/{bookingid}");
		response.prettyPrint();
		
		HashMap<String, Object> map = response.as(HashMap.class);
		System.out.println(map);
		
		response.
		      then().
		      assertThat().
		      statusCode(200).
		      contentType(ContentType.JSON).
		      statusLine("HTTP/1.1 200 OK");
		
		SoftAssert softAssert = new SoftAssert();
		softAssert.assertEquals(map.get("firstname"), "Mary");
		softAssert.assertEquals(map.get("totalprice"), 615);
		softAssert.assertEquals(map.get("depositpaid"), false);
		softAssert.assertTrue(map.get("bookingdates").toString().contains("checkin=2017-01-15"));
		
		softAssert.assertAll();
		
		
		
		
		
		
	}

}
