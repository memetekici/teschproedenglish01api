package techproedenglish01.techproedenglish01api;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.Test;
import org.testng.asserts.SoftAssert;

import io.restassured.path.json.JsonPath;

public class GetRequest13 {
	   //How to work with local Json Data
	   //Path of Json File C:\Users\lenovo\Desktop\Employee.json
	
	@Test
	public void get01() {
		
		JsonPath json = new JsonPath(new File("C:\\Users\\lenovo\\Desktop\\Employee.json"));//Bilgisayarımızdaki(Local) veriye ulaşıyoruz
		SoftAssert softAssert = new SoftAssert();
		
		//Get all employee names whose salaries are greater than 150000
		List<String> nameList = json.getList("data.findAll{Integer.valueOf(it.employee_salary) > 150000}.employee_name");
		System.out.println(nameList);
		//Assert that 17 employees are earning greater than 150000
		softAssert.assertEquals(nameList.size(), 17);
		
		//Assert that maximum salary is 725000
		List<String> salaryList = json.getList("data.employee_salary");
		System.out.println(salaryList);
		List<Integer> salaryListInt = new ArrayList<>();
		for(String w : salaryList) {
			salaryListInt.add(Integer.valueOf(w));
		}
		System.out.println(salaryListInt);
		Collections.sort(salaryListInt);
		System.out.println(salaryListInt);
		softAssert.assertTrue(salaryListInt.get(salaryListInt.size() - 1) == 725000);
		
		
		softAssert.assertAll();
		
	}

}
