package techproedenglish01.techproedenglish01api;
import static io.restassured.RestAssured.*;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class Patch01 extends TestBasedt{
	/*
	 * For Patch Request you need;
	 * 1)EndPoint ==> Mandatory
	 * 2)Request Body ==> Mandatory
	 * 3)Authorization ==> It depends on the API
	 * 4)Headers ==> It depends on the method
	 */
	
	/*
	 {
    "userId": 10,
    "id": 198,
    "title": "quis eius est sint explicabo",
    "completed": true
}
	 */
	@Test
	public void patch() {
		spec01.pathParam("id", 11);
		
		Map<String, String> reqBody = new HashMap<String, String>();
		reqBody.put("title", "Memet");
		
		//Normally after running the code I shuld see Memet as title on the console
		//But that Apı does not let us to update data.Because of that I just asserted status code and content type.
		Response response = given().spec(spec01).body(reqBody).when().patch("/{id}");
		response.prettyPrint();
		
		response.then().statusCode(200).contentType(ContentType.JSON);
		
		
	}

}
