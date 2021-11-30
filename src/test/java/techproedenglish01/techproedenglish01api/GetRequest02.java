package techproedenglish01.techproedenglish01api;
import static io.restassured.RestAssured.*;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class GetRequest02 {
	
	/*
	 * TEST CASE
	 1.When I send a GET request to "https://restful-booker.herokuapp.com/booking"
	 2.and I accept type "application/json" ==> Means API is accepting data just in Json Format
	 3.then status code should be 200
	 4.and content type should be application json ==> Response body must be in Json format
	 */
	
	@Test
	public void getMethod1() {
		Response response = given().
				                 accept(ContentType.JSON).
				            when().
				                 get("https://restful-booker.herokuapp.com/booking");//İlk satır
		response.
		     then().
		     assertThat().
		     statusCode(200).
		     contentType(ContentType.JSON);
	}
	/*
	 * TEST CASE
	 1.When I send a GET request to "https://restful-booker.herokuapp.com/booking/5"
	 
	 3.then status code should be 200
	 4.and content type should be application json ==> Response body must be in Json format
	 */
	@Test
	public void getMethod02() {
		Response response = given().when().get("https://restful-booker.herokuapp.com/booking/5");
		response.then().assertThat().statusCode(200).contentType(ContentType.JSON);
	}
	/*
	 * TEST CASE
	 1.When I send a GET request to "https://restful-booker.herokuapp.com/booking/1001"
	 2.then status code should be 404
	 3.and Response body contains "Not Found"
	 and Response body does not contain "Suleyman"
	 */
	@Test
	public void getMethod03() {
	Response response = given().
			            when().
			            get("https://restful-booker.herokuapp.com/booking/1001");
	
	response.prettyPrint();//Responce body i görüyoruz..İçi boş
	response.then().assertThat().statusCode(404);
	
	assertTrue(response.asString().contains("Not Found"));//Contains methodu kullanmak için responce i String e Convert(dönüştürmek) ettik.
	assertFalse(response.asString().contains("Suleyman"));//Negatif senaryoyu kontrol ediyoruz ve test geçiyor
	
	}
	
	

}
