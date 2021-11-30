package apipracticedt;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.testng.asserts.SoftAssert;

import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import techproedenglish01.techproedenglish01api.PojoPractice05;
import techproedenglish01.techproedenglish01api.TestBasedt;

public class Practice05 extends TestBasedt{
	/*
	When 
	I send a POST request to REST API URL 
	http://dummy.restapiexample.com/api/v1/create
	{
	"employee_name": "Ali Can",
	"employee_salary": "77000",
	"employee_age": "35",
	"profile_image": ""
	}  
	Then 
	HTTP Status Code should be 200
	And Response format should be "application/json"
	And "status" should be "success"
	And "message" should be "Successfully! Record has been updated."
	 
	​
	Note: For Base URL use spec04 and add path param "/create"
	Note: For actual data use De-Serialization
	Note: For expected data use Pojo Class
	Note: Use Hard Assertion(body()) and Soft Assertion
	*/
	@Test
	public void postPractice() {
		//Set the endpoint
		spec04.pathParam("create", "create");
		
		//Create POST Request body
		//1)Map 2)Pojo 3)List of Map 4)JSONObject
//		Map<String, String> map = new HashMap<String, String>();
//		map.put("employee_name", "Ali Can");
//		map.put("employee_salary", "77000");
//		map.put("employee_age", "35");
//		map.put("profile_image", "");
		
		PojoPractice05 expectedData = new PojoPractice05("Ali Can", "77000", "35", null);//Null u aşağıda görünce ekledik
		expectedData.setStatus("success");//Bunları sonradan ekledik
		expectedData.setMessage("Successfully! Record has been added.");//Sonradan ekledikÇünkü ilk başta pojo class da yoklardı.
		
		//Send Post Request
		Response response = given().
				                contentType(ContentType.JSON).
				                spec(spec04).
				                body(expectedData).
				            when().
				                post("/{create}");
		response.prettyPrint();
		
		//Hard Assertion
		response.
		      then().
		      assertThat().
		      statusCode(200).
		      contentType(ContentType.JSON).body("status", equalTo(expectedData.getStatus()),
		    		                             "data.employeeName",equalTo(expectedData.getEmployeeName()),
		    		                             "data.employeeSalary",equalTo(expectedData.getEmployeeSalary()),
		    		                             "data.employeeAge",equalTo(expectedData.getEmployeeAge()),
		    		                             "data.profileImage",equalTo(expectedData.getProfileImage()),
		    		                             "message", equalTo(expectedData.getMessage()));
		
		//HArd Assertion with assertEquals(), assertTrue(), assertFalse()
		//We have 2 options to convert response body
		//1)JsonPath 2)De-Serialization ==> a)GSON  b)ObjectMpper
		JsonPath json = response.jsonPath();
		
		assertEquals(expectedData.getStatus(), json.getString("status"));
		assertEquals(expectedData.getEmployeeSalary(), json.getString("data.employeeSalary"));
		assertEquals(expectedData.getEmployeeName(), json.getString("data.employeeName"));
		assertEquals(expectedData.getEmployeeAge(), json.getString("data.employeeAge"));
		assertEquals(expectedData.getProfileImage(), json.getString("data.profileImage"));
		assertEquals(expectedData.getMessage(), json.getString("message"));
		
		//Soft Assert
		SoftAssert softAssert = new SoftAssert();
		softAssert.assertEquals(json.getString("status"), expectedData.getStatus());
		softAssert.assertEquals(json.getString("data.employeeName"), expectedData.getEmployeeName());
		softAssert.assertEquals(json.getString("data.employeeSalary"), expectedData.getEmployeeSalary());
		softAssert.assertEquals(json.getString("employeeAge"), expectedData.getEmployeeAge());
		softAssert.assertEquals(json.getString("profileImage"), expectedData.getProfileImage());
		
		
	}

}
