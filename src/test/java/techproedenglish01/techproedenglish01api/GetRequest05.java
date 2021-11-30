package techproedenglish01.techproedenglish01api;
import static io.restassured.RestAssured.*;

import org.hamcrest.Matchers;//import static org.hamcrest.Matchers.*yazarsak aşağıdaki matchers ları silebiliriz
import org.junit.Test;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class GetRequest05 {
	/*
	 When I send a GET request to REST API URL
		https://restful-booker.herokuapp.com/booking/1
	    And Accept type is “application/JSON”
	    Then
	    HTTP Status Code should be 200
	    And Response format should be "application/JSON"
	    And first name should be "Susan"
	    And lastname should be "Brown"
	    And checkin date should be "2015-02-16"
	    And checkout date should be "2017-06-20"
	 */
	@Test
	public void get01() {
		Response response = given().
				//accept(ContentType.JSON).
				when().get("https://restful-booker.herokuapp.com/booking/1");
		response.prettyPrint();
		
		response.
		       then().
		       assertThat().
		       statusCode(200).//HTTP Status Code should be 200
		       contentType(ContentType.JSON).//And Response format should be "application/JSON"
		       body("firstname", Matchers.equalTo("Susan")).//And first name should be "Susan"
		       body("lastname", Matchers.equalTo("Jackson")).//And lastname should be "Brown"
		       body("totalprice", Matchers.equalTo(311)).and().
		       body("depositpaid", Matchers.equalTo(false)).
		       body("bookingdates.checkin", Matchers.equalTo("2018-06-04")).
		       body("bookingdates.checkout", Matchers.equalTo("2019-11-18")).
		       body("additionalneeds", Matchers.equalTo("We are 16 people in Batch3"));
		
	}
	//Body leri silmek istiyoruz.Onun için bu methodu yazıyoruz
	@Test
	public void get02() {
		Response response = given().
				//accept(ContentType.JSON).
				when().get("https://restful-booker.herokuapp.com/booking/1");
		response.prettyPrint();
		
		response.
		       then().
		       assertThat().
		       statusCode(200).//HTTP Status Code should be 200
		       contentType(ContentType.JSON).//And Response format should be "application/JSON"
		       body("firstname", Matchers.equalTo("Team 1 is the best team"),//Bodyleri silmiş olduk
		    		"lastname", Matchers.equalTo("Techproed"),
		    		"totalprice", Matchers.equalTo(16),
		    		"depositpaid", Matchers.equalTo(false),
		    		"bookingdates.checkin", Matchers.equalTo("2018-06-04"),
		    		"bookingdates.checkout", Matchers.equalTo("2019-11-18"),
		    		"additionalneeds", Matchers.equalTo("We are 16 people in Batch3"));
	}

}
