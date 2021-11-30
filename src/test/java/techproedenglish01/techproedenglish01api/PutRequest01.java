package techproedenglish01.techproedenglish01api;
import static io.restassured.RestAssured.*;

import java.util.HashMap;
import java.util.Map;

import org.hamcrest.Matchers;
import org.junit.Test;
import org.testng.asserts.SoftAssert;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class PutRequest01 extends TestBasedt{
	/*
	 For PutRequest(Full Update) we need;
	 1)EndPoint ==>Mandatory
	 2)Request Body ==> MAndatory
	 3)Authorization ==> It depend on the API
	 4)Headers ==> It depends on the Apı
	 */
	/*
    When 
      I send PUT Request to http://dummy.restapiexample.com/api/v1/update/2  PathParams la bunu Testbasedt eklemiş oluyoruz
    Then 
      Status code is 200
      Content Type is "application/json"
      "status" key has value "success"
      "message" key has value "Successfully! Record has been updated."
      
    Note: Create Request Body in 3 different ways  
    */
	@Test
	public void put01() {
		spec04.pathParams("function", "update",
				          "id", 11);
		
		//Use Map to create Request Body
		Map<String, Object> reqBody = new HashMap<String, Object>();
		
		reqBody.put("name", "Husne Ceren");
		reqBody.put("salary", "4444777");
		reqBody.put("age", "87");
		Response response = given().spec(spec04).body(reqBody).when().put("/{function}/{id}");//Birden çok pathParam olduğu için bu şekilde yazdık
		response.prettyPrint();
		
		response.
		       then().
		       assertThat().
		       statusCode(200).
		       contentType(ContentType.JSON).
		       body("status", Matchers.equalTo("success"),
		    	    "message", Matchers.equalTo("Successfully! Record has been updated."));	  
		
		
		HashMap<String, Object> map = response.as(HashMap.class);
		SoftAssert softAssert = new SoftAssert();
		softAssert.assertEquals(map.get("status"), "success");
		softAssert.assertEquals(map.get("message"), "Successfully! Record has been updated.");
		
		softAssert.assertAll();
	}
	

	
	

}
