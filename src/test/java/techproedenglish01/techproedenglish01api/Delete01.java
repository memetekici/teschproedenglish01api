package techproedenglish01.techproedenglish01api;
import static io.restassured.RestAssured.*;

import org.junit.Test;

import io.restassured.response.Response;

public class Delete01 extends TestBasedt{
	/*
	 For DELETE Request we need just EndPoint like Get Request, we do not need Request Body
	 */
	@Test
	public void delete01() {
		//Get data before deleting
		Response responseGet = given().spec(spec01).when().get("/11");
		responseGet.prettyPrint();
		
		
		//The data after deleting
		Response response = given().spec(spec01).when().delete("/11");
		response.prettyPrint();
		
	}

}
