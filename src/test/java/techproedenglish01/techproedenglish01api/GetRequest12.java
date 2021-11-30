package techproedenglish01.techproedenglish01api;
import static io.restassured.RestAssured.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.junit.Test;
import org.testng.asserts.SoftAssert;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class GetRequest12 extends TestBasedt {
	/*
     * When I send GET Request to URL
     * http://dummy.restapiexample.com/api/v1/employees
     * Then
     * Status code is 200
     *  1)Print all ids greater than 10 on the console
     *  Assert that there are 14 ids greater than 10
     *  2)Print all ages less than 30 on the console
     *  Assert that maximum age is 23
     *  3)Print all employee names whose salaries are greater than 350000 
     *  Assert that Charde Marshall is one of the employees whose salary is greater than 350,000
     *  4)Print all employee salaries whose ids are less than 11
     *  Assert that max salary is 433060
     */
	@Test
	public void get01() {
		
		Response response = given().
				                spec(spec03).
				            when().
				                get();
		response.prettyPrint();
		
		response.
		      then().
		      assertThat().
		      statusCode(200);
		
		JsonPath json = response.jsonPath();
		SoftAssert softAssert = new SoftAssert();
		
		//1)Print all ids greater than 10 on the console
		List<String> idList = json.getList("data.findAll{Integer.valueOf(it.id)>10}.id");//Filtre yaptık.id si 10 dan büyük olanları yazdık.
		System.out.println(idList);                                                      //"it" burada reponce body dir."it.id" ise responce body deki idlerdir
		//Assert that there are 14 ids greater than 10
		softAssert.assertEquals(idList.size(), 14);
		
		//2)Print all ages less than 30 on the console
		List <String> ageList = json.getList("data.findAll{Integer.valueOf(it.employee_age)<30}.employee_age");
		System.out.println(ageList);
		//Assert that maximum age is 23
		List<Integer>ageListInt = new ArrayList<Integer>();
		for(String w : ageList) {
			ageListInt.add(Integer.valueOf(w));//Bu kod elementleri tek tek integere çeviriyor ve yeni liste atıyor.
		}
		System.out.println(ageListInt);
		
		Collections.sort(ageListInt);
		System.out.println(ageListInt);
		
		softAssert.assertTrue(ageListInt.get(ageListInt.size() - 1) == 23);
		
		//3)Print all employee names whose salaries are greater than 350000
		List<String> nameList = json.getList("data.findAll{Integer.valueOf(it.employee_salary) > 350000}.employee_name");
		System.out.println(nameList);
		//Assert that Charde Marshall is one of the employees whose salary is greater than 350,000
		softAssert.assertTrue(nameList.contains("Charde Marshall"));
		
		// 4)Print all employee salaries whose ids are less than 11
		List<String> employeeSalaryList = json.getList("data.findAll{Integer.valueOf(it.id)<11}.employee_salary");
		System.out.println(employeeSalaryList);
	    // Assert that max salary is 433060
		List<Integer> ıntEmployeeSalaryList = new ArrayList<Integer>();
		for(String w : employeeSalaryList) {
			ıntEmployeeSalaryList.add(Integer.valueOf(w));
		}
		System.out.println(ıntEmployeeSalaryList);
		Collections.sort(ıntEmployeeSalaryList);
		System.out.println(ıntEmployeeSalaryList);
		softAssert.assertTrue(ıntEmployeeSalaryList.get(ıntEmployeeSalaryList.size() - 1) == 433060);
		
		
			
		softAssert.assertAll();
		
	}

}
