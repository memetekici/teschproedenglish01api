package techproedenglish01.techproedenglish01api;
import static io.restassured.RestAssured.*;
import static org.junit.Assert.assertEquals;

import java.util.HashMap;

import org.junit.Test;
import org.testng.asserts.SoftAssert;

import io.restassured.response.Response;

public class GetRequest14 extends TestBasedt{
	
	//GSON is a converter
	//GSon is used to convert Json Format data to Java Object ==> De-serialization(WE will use that)
	//GSon is used to convert JavaObject  to Json Format ==> Serialization
	
	@Test
	public void get01() {
		spec01.pathParam("id", 2);
		Response response = given().spec(spec01).when().get("/{id}");
		
		//Response response = given().spec(spec01).when().get("/2");
		response.prettyPrint();
		
		//Let's convert Json Data to a HashMap
		HashMap<String, Object> hMap = response.as(HashMap.class);
		System.out.println(hMap);
		//Print all keys from json data on the console
		System.out.println(hMap.keySet());
		//Print all value from json data on the console
		System.out.println(hMap.values());
		
		//Hard Assertion
		//Assert that "completed" is false
		assertEquals(false, hMap.get("completed"));
		
		//Assert that "title" is "quis ut nam facilis et officia qui"
		assertEquals("quis ut nam facilis et officia qui", hMap.get("title"));
		
		//Assert that "userId" is 1
		assertEquals(1, hMap.get("userId"));
		
		//Soft Assertion
		SoftAssert softAssert = new SoftAssert();
		softAssert.assertTrue(hMap.get("completed").equals(false));
		softAssert.assertTrue(hMap.get("title").equals("quis ut nam facilis et officia qui"));
		softAssert.assertTrue(hMap.get("userId").equals(1));
		
		
		softAssert.assertAll();
		
	}

}
