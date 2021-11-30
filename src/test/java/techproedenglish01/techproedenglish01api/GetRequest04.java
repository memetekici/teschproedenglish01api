package techproedenglish01.techproedenglish01api;
import static io.restassured.RestAssured.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import io.restassured.response.Response;

public class GetRequest04 {
	/*
	 1)How to get all Headers data
	 2)How to get a specific header
	 3)How to assert Headers one by one
	 */
	@Test
	public void get01() {
		Response response = given().when().get("https://restful-booker.herokuapp.com/booking");
		response.prettyPrint();//Response bodyi göreceğiz
		
		//How to get all Headers data
		System.out.println(response.getHeaders());//Headers leri göreceğiz
		
		//Assert if Headers contain"Expect-CT"
		assertEquals(response.getHeader("Expect-CT"),null);
		
	//2)How to get a specific header
		System.out.println(response.getHeader("Server"));//Cowboy
		
		//Assert that Via Header has the value "1.1 vegur"
		//System.out.println(response.getHeader("Via").contains("1.1 vegur"));
		assertEquals(response.getHeader("Via"), "1.1 vegur");
		
		
	}

}
