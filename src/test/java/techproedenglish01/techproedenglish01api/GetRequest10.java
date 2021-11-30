package techproedenglish01.techproedenglish01api;
import static io.restassured.RestAssured.*;

import org.junit.Test;
import org.testng.asserts.SoftAssert;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class GetRequest10 extends TestBasedt {
	/*
	 * When I send a GET request to REST API URL
	 * https://restful-booker.herokuapp.com/booking/5
	 * Then HTTP Status Code should be 200
	 * And response content type is “application/JSON”
	 * And response body should be like;
	 * {"firstname": "Sally",
	 *   "lastname": "Ericsson",
	 *   "totalprice": 111,
	 *   "depositpaid": false,
	 *   "bookingdates": { "checkin": "2017-05-23",
	 *                     "checkout":"2019-07-02" }
	 * }
	 */
	
	/*
	 JSONPATH: JsonPath is used to navigate in Json Data
	 */
	@Test
	public void get01() {
		spec02.pathParam("bookingid", 5);
		
		Response response = given().spec(spec02).when().get("/{bookingid}");
		response.prettyPrint();
		
		//Assert the following by using softAssert
		JsonPath json = response.jsonPath();
		SoftAssert softAssert = new SoftAssert();
		
		//Print the firstname on the console
		System.out.println(json.getString("firstname"));
		softAssert.assertEquals(json.getString("firstname"), "Jim");
		
		
		//Print the last name on the console
		System.out.println(json.getString("lastname"));
		softAssert.assertEquals(json.getString("lastname"), "Jackson");
		
		//Print Totalprice on the console
		System.out.println(json.getInt("totalprice"));
		softAssert.assertEquals(json.getInt("totalprice"), 547);
		
		//Print the depositpaid
		System.out.println(json.getBoolean("depositpaid"));
		softAssert.assertEquals(json.getBoolean("depositpaid"), false);
		
		
		//Print checkin
		System.out.println(json.getString("bookingsdate.checkin"));
		softAssert.assertEquals(json.getString("bookingdates.checkin"), "2019-07-14");
		
		//Print the additionalneeds
		System.out.println(json.getString("additionalneeds"));
		softAssert.assertEquals(json.getString("additionalneeds"), "Breakfast");
		
		softAssert.assertAll();
		
	}

}
