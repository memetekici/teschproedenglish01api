package apipracticedt;
import static io.restassured.RestAssured.*;

import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.Matchers.*;//Aşağıda matcherları siliyoruz
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;


import org.hamcrest.Matchers.*;
import org.json.JSONObject;
import org.junit.Test;
import org.testng.asserts.SoftAssert;

import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import techproedenglish01.techproedenglish01api.TestBasedt;
import utilities.JsonUtil;

public class Practice02 extends TestBasedt{
	/*
	When 
	I send a GET request to REST API URL 
	https://restful-booker.herokuapp.com/booking/1   
	Then 
	HTTP Status Code should be 200
	And Response format should be "application/JSON"
	And first name should be "Sally"
	And lastname should be "Smith"
	And totalprice should be 705
	And checkin date should be "2015-02-16"
	And checkout date should be "2017-06-20"
	
	Note: For Base URL use spec02
	Note: For actual data use JsonPath
	Note: For expected data use Map
	Note: Use Hard Assertion and Soft Assertion
	*/
	@Test
	public void test1() {
		spec02.pathParam("id", 1);
		
		JSONObject expectedData = new JSONObject();
		expectedData.put("statuscode", 200);
		expectedData.put("firstname", "Jim");
		expectedData.put("lastname", "Wilson");
		expectedData.put("totalprice", 799);
		expectedData.put("checkin", "2015-07-11");
		expectedData.put("checkout", "2018-02-18");
		System.out.println(expectedData);
		Response response = given().spec(spec02).when().get("/{id}");
		response.prettyPrint();
		
		//Assertion by using body()
		response.
		       then().
		       assertThat().
		       statusCode(200).
		       contentType(ContentType.JSON).body("firstname", equalTo(expectedData.getString("firstname")),
		    		                              "lastname", equalTo(expectedData.getString("lastname")),
		    		                              "totalprice", equalTo(expectedData.getInt("totalprice")),
		    		                              "bookingdates.checkin",equalTo(expectedData.getString("checkin")),
		    		                              "bookingdates.checkout", equalTo(expectedData.getString("checkout")));
		
		//Assertion by using assertEquals,assertTrue,AssertFalse
		JsonPath json = response.jsonPath();
		assertEquals(expectedData.getString("firstname"), json.getString("firstname"));
		assertEquals(expectedData.getString("lastname"), json.getString("lastname"));
		assertEquals(expectedData.getInt("totalprice"), json.getInt("totalprice"));
		assertEquals(expectedData.getString("checkin"), json.getString("bookingdates.checkin"));
		assertEquals(expectedData.getString("checkout"), json.getString("bookingdates.checkout"));
		
		//Use De-Serilization with GSon
		
		//Map<String, Object> booking = response.as(HashMap.class);
		//System.out.println(booking);
		
		
		//Use De-Serialization with ObjectMapper
		Map<String, Object> booking = JsonUtil.convertJsonToJava(response.asString(), HashMap.class);
		
		assertEquals(expectedData.getString("firstname"), booking.get("firstname"));
		assertEquals(expectedData.getString("lastname"), booking.get("lastname"));
		assertEquals(expectedData.getInt("totalprice"), booking.get("totalprice"));
		assertTrue(booking.get("bookingdates").toString().contains(expectedData.getString("checkin")));
		assertTrue(booking.get("bookingdates").toString().contains(expectedData.getString("checkout")));
		
		//Note:
		//asString() is used to convert Response object to String
		//toString is used to convert Object objects to String
		
		//Soft Assertion
		//To get actual data from responce body we use JsonPath
		SoftAssert softAssert = new SoftAssert();
		
		softAssert.assertEquals(json.getString("firstname"), expectedData.getString("firstname"));
		softAssert.assertEquals(json.getString("lastname"), expectedData.getString("lastname"));
		softAssert.assertEquals(json.getInt("totalprice"), expectedData.getInt("totalprice"));
		softAssert.assertEquals(json.getString("bookingdates.checkin"), expectedData.getString("checkin"));
		softAssert.assertEquals(json.getString("bookingdates.checkout"), expectedData.getString("checkout"));
		
		//Use De-Serilization with ObjectMapper
		
		softAssert.assertEquals(booking.get("firstname"), expectedData.getString("firstname"));
		softAssert.assertEquals(booking.get("lastname"), expectedData.getString("lastname"));
		softAssert.assertEquals(booking.get("totalprice"), expectedData.getInt("totalprice"));
		softAssert.assertTrue(booking.get("bookingdates").toString().contains(expectedData.getString("checkin")));
		softAssert.assertTrue(booking.get("bookingdates").toString().contains(expectedData.getString("checkout")));
		
		softAssert.assertAll();
		
		
		
		
		
		
		    		                              
		
	
	}
}
