package techproedenglish01.techproedenglish01api;
import static io.restassured.RestAssured.*;

import java.util.ArrayList;
import java.util.List;

import org.hamcrest.Matchers;
import org.junit.Test;
import org.testng.asserts.SoftAssert;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class GetRequest11 extends TestBasedt{
	/*
	 * When I send a GET Request to URL
	 * http://dummy.restapiexample.com/api/v1/employees
	 * Then status code is 200
	 * And the name of 5th employee is "Airi Satou"
	 * And the salary of 6th employee is "372000"
	 * And there are "24" employees
	 * And "Rhona Davidson" is one of the employees
	 * And "21","23","61" are among employee ages
	 */
	@Test
	public void get01() {
		Response response = given().
				                spec(spec03).
				            when().
				                get();
		response.prettyPrint();
		
		//Hard Assertion
		response.
		       then().
		       assertThat().
		       statusCode(200).
		       body("data[4].employee_name", Matchers.equalTo("Airi Satou"),
		    		"data[5].employee_salary",Matchers.equalTo(372000),
		    		"data.id", Matchers.hasSize(24),//And there are "24" employees
		    		"data.employee_name", Matchers.hasItem("Rhona Davidson"),//And "Rhona Davidson" is one of the employees
		    		"data.employee_age", Matchers.hasItems("21", "23", "61"));//And "21","23","61" are among employee ages
		
		/*
		 What is the difference between Hard assertion and Soft Assertion
		 In Hard assertion, if any assertion fails next ones do not executed.
		 In Soft Assertion, all assertions are executed then you get report for all assertions one by one. 
		 */
		
		/*
		 There are tree steps for Soft Assertion
		 1.You have to create an object from SoftAssert class
		 SoftAssert softAssert = new SoftAssert()
		 
		 2.You select assertion methods from SoftAssert Class by using softAssert object
		 3.Don't forget to type assertAll() method at the end.If you don't type assertAll() at the end
		 you will get green everytime but it is not meaningful 
		 */
		
		//Assert the same test case by using Soft Assertion and Json Path
		
		JsonPath json = response.jsonPath();
		SoftAssert softAssert = new SoftAssert();
		
		softAssert.assertEquals(json.getString("data[4].employee_name"), "Airi Satou");
		softAssert.assertEquals(json.getString("data[5].employee_salary"), "372000");
		softAssert.assertEquals(json.getList("data.id").size(), 24);//And there are "24" employees
		softAssert.assertTrue(json.getList("data.employee_name").contains("Rhona Davidson"));
		//softAssert.assertTrue(json.getList("data.employee_age").contains("21") && json.getList("data.employee_age").contains("23") && json.getList("data.employee_age").contains("61"));
		List<String> ageList = new ArrayList<>();//List oluşturmak zorundayız.Çünkü 3 value yi kontrol etmemizi istiyor.Collection olarak kontrol edeceğiz.
		ageList.add("21");
		ageList.add("23");
		ageList.add("61");
		softAssert.assertTrue(json.getList("data.employee_age").containsAll(ageList));
		softAssert.assertAll();
	}

}
