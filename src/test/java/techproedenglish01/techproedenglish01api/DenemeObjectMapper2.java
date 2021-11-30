package techproedenglish01.techproedenglish01api;
import static io.restassured.RestAssured.*;
import static org.junit.Assert.assertEquals;

import java.util.HashMap;
import java.util.Map;

import org.junit.Assert;
import org.junit.Test;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import utilities.JsonUtil;

public class DenemeObjectMapper2 extends DenemeTestBase{
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
	public void denemeObjMapper() {
		spec02.pathParam("id", 5);
		Response response = given().spec(spec02).when().get("/{id}");
		response.prettyPrint();
		
		response.then().assertThat().statusCode(200).contentType(ContentType.JSON);
		Map<String, Object> mapFromApiJson = JsonUtil.convertJsonToJava(response.asString(), HashMap.class);
		System.out.println(mapFromApiJson);
		
		Map<String, Object> expectedMap = new HashMap<String, Object>();
		Map<String, String> mapDates = new HashMap<String, String>();
		mapDates.put("checkin", "2015-05-19");
		mapDates.put("checkout", "2017-12-30");
		
		expectedMap.put("firstname", "Mark");
		expectedMap.put("lastname", "Jackson");
		expectedMap.put("totalprice", 242);
		expectedMap.put("depositpaid", true);
		expectedMap.put("bookingdates", mapDates);
		
		Assert.assertEquals(expectedMap.get("firstname"), mapFromApiJson.get("firstname"));
		assertEquals(expectedMap.get("bookingdates"), mapFromApiJson.get("bookingdates"));
	}

}
