package techproedenglish01.techproedenglish01api;
import static io.restassured.RestAssured.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;
import org.testng.asserts.SoftAssert;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import utilities.JsonUtil;

public class ObjectMapperTestWithMap02 extends TestBasedt{
	/*
	When 
	I send a GET request to REST API URL
	https://restful-booker.herokuapp.com/booking/5 
	Then 
	HTTP Status Code should be 200
	And response content type is “application/JSON” 
	And response body should be like; 
	{ "firstname": "Sally", 
	"lastname": "Ericsson", 
	"totalprice": 111,
	"depositpaid": false, 
	"bookingdates": { "checkin": "2017-05-23", 
	                  "checkout":"2019-07-02" }
	}
	*/
	@Test
	public void JsonFromApiToJava() {
		//1.Set the URL
		spec02.pathParam("id", 5);
		
		//2.Put the expected values into Java Object(map)
		Map<String, Object> expectedMap = new HashMap<String, Object>();
		expectedMap.put("firstname", "Sally");
		expectedMap.put("lastname", "Jackson");
		expectedMap.put("totalprice", 440);
		expectedMap.put("depositpaid", true);
		expectedMap.put("checkin", "2020-06-21");
		expectedMap.put("checkout", "2020-09-29");
		
		//3.Get the actual data as a Map
		Response response = given().spec(spec02).when().get("/{id}");
		response.prettyPrint();
		
		@SuppressWarnings("unchecked")
		Map<String, Object> actualMap = JsonUtil.convertJsonToJava(response.asString(), HashMap.class);
		System.out.println(actualMap);
		
		//4.Step Assertion (Hard Assertion)
		response.
		      then().
		      assertThat().
		      statusCode(200).
		      contentType(ContentType.JSON);
		
		assertEquals(expectedMap.get("firstname"), actualMap.get("firstname"));
		assertEquals(expectedMap.get("lastname"), actualMap.get("lastname"));
		assertEquals(expectedMap.get("totalprice"), actualMap.get("totalprice"));
		assertEquals(expectedMap.get("depositpaid"), actualMap.get("depositpaid"));
		assertTrue(actualMap.get("bookingdates").toString().contains(expectedMap.get("checkin").toString()));
		assertTrue(actualMap.get("bookingdates").toString().contains(expectedMap.get("checkout").toString()));
		
		//5.Soft Assertion
		SoftAssert softAssert = new SoftAssert();
		softAssert.assertEquals(actualMap.get("firstname"), expectedMap.get("firstname"));
		softAssert.assertEquals(actualMap.get("lastname"), expectedMap.get("lastname"));
		softAssert.assertEquals(actualMap.get("toatlprice"), expectedMap.get("totalprice"));
		softAssert.assertEquals(actualMap.get("depositpaid"), expectedMap.get("depositpaid"));
		softAssert.assertTrue(actualMap.get("bookingdates").toString().contains(expectedMap.get("checkin").toString()));
		softAssert.assertTrue(actualMap.get("bookingdates").toString().contains(expectedMap.get("checkout").toString()));
		
		softAssert.assertAll();		
	}
	/*
	 1)ObjectMapper Class is used for Serialization(Java ==> Json) and De-Serialization(Json ==> Java)
	 2)ObjectMapper does the same with GSON.
	 3)We created a class in Utilities package, its name is JsonUtil then I created 2 methods in the class.
	  First is for serialization and second one is for de-serialization
	  In the first method to convert Java Object to Json I used writeValueAsString() method.
	  In the second method, to convert Json Data to JAva object I used readValue () method.
	  I made the second method "generic" because I want my method to return different Java Objects.
	  I made the methods static, so I am able to access them just by using the class name which is JsonUtil.
	 */

}
