package techproedenglish01.techproedenglish01api;
import static io.restassured.RestAssured.*;

import java.util.HashMap;
import java.util.Map;

import org.hamcrest.Matchers;
import org.json.JSONObject;
import org.junit.Test;
import org.testng.asserts.SoftAssert;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class PostRequest01 extends TestBasedt{
	
	/*
	 For post Request,You need;
	 1)Endpoint ==>Mandatory(Nereye yollayacağımızın adresi)
	 2)Request Body ==> Mandatory
	 3)Authorization ==> Optional,It depends on the API
	 4)Headers ==> It depends on the API
	 */
	
	/*
    When 
      I send POST Request to http://dummy.restapiexample.com/api/v1/create
    Then 
      Status code is 200
      Content Type is "application/json"
      "status" key has value "success"
      "message" key has value "Successfully! Record has been added."
      
    Note: Create Request Body in 3 different ways  
    */
	@Test
	public void post01() {
		spec04.pathParam("create", "create");
		//1.way to request body
		//String reqBody = "{\"name\":\"Suleyman\",\"salary\":\"4444\",\"age\":\"33\"}";
		
		//2.way to create Request Body by using JSONObject Class ==>Better than 1.way
		//JSONObject reqBody = new JSONObject();
		//reqBody.put("name", "Memet");
		//reqBody.put("salary", "4444");
		//reqBody.put("age", "33");
		
		//3.way to create Request Body by using Map==> The best way to use
		Map<String, String> reqBody = new HashMap<>();
		reqBody.put("name", "Memet");
		reqBody.put("salary", "4444");
		reqBody.put("age", "33");
		System.out.println(reqBody);
		
		//For basic authorization after spec() use ==>auth().basic("admin", "password123").
		//For Bearer Token Authorization use ==> auth().oauth2("Token will be typed over here")
		Response response = given().
				               spec(spec04).
				               auth().
				               basic("admin", "password123").
				               body(reqBody).
				            when().
				               post("/{create}");
		response.prettyPrint();
		
		response.
		       then().
		       assertThat().
		       statusCode(200).
		       contentType(ContentType.JSON).
		       body("status", Matchers.equalTo("success"),
		    		"message", Matchers.equalTo("Successfully! Record has been added."));
		
		//De_serilization ile soft assertion yapıyoruz.
		HashMap<String, Object> map = response.as(HashMap.class);
		System.out.println(map);
		
		SoftAssert softAssert = new SoftAssert();
		softAssert.assertEquals(map.get("message"), "Successfully! Record has been added.");
		softAssert.assertEquals(map.get("status"), "success");
		
		softAssert.assertAll();
		
		
		
	}

}
