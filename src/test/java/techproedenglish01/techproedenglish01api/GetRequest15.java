package techproedenglish01.techproedenglish01api;
import static io.restassured.RestAssured.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.junit.Test;

import io.restassured.response.Response;

public class GetRequest15 extends TestBasedt{
	
    //GSON
	@Test
	public void get01() {
		
		Response response = given().spec(spec02).when().get();
		response.prettyPrint();
		
		List<Map<String, String>> listOfMaps = response.as(ArrayList.class);
		System.out.println(listOfMaps);
		
	}
}
