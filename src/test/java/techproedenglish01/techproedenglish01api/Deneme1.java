package techproedenglish01.techproedenglish01api;
import static io.restassured.RestAssured.given;

import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.Matchers.*;
import org.junit.Test;
import org.testng.asserts.SoftAssert;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class Deneme1 extends DenemeTestBase{
	
	@Test
	public void post() {
		
		Map<String, Object> reqBody = new HashMap<String, Object>();
		reqBody.put("firstname", "Ali");
		reqBody.put("lastname", "Can");
		reqBody.put("totalprice", 111);
		reqBody.put("depositpaid", true);
		Map<String, String> reqBody2 = new HashMap<String, String>();
		reqBody2.put("checkin", "2020-09-16");
		reqBody2.put("checkout", "2020-09-18");
		reqBody.put("bookingdates", reqBody2);
		reqBody.put("additionalneeds", "Wifi");
		System.out.println(reqBody);
		
		Response response = given().contentType(ContentType.JSON).
				            auth().
				            basic("admin", "password123").
				            spec(spec02).
				            body(reqBody).
				            when().
				            post();
		response.prettyPrint();
		
		response.
		      then().
		      assertThat().
		      statusCode(200).
		      contentType(ContentType.JSON).body("booking.firstname", equalTo(reqBody.get("firstname")),
		    		                             "booking.lastname", equalTo(reqBody.get("lastname")),
		    		                             "booking.totalprice", equalTo(reqBody.get("totalprice")));
		
		SoftAssert softAssert = new SoftAssert();
		Map <String, Object> actualData= response.as(HashMap.class);
		System.out.println(actualData);
		
		softAssert.assertTrue(actualData.get("booking").toString().contains(reqBody.get("firstname").toString()));
		
		
		softAssert.assertAll();
		
		
		
	}

}
