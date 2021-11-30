package techproedenglish01.techproedenglish01api;

import org.junit.Before;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;

public class DenemeTestBase2 {
	
	protected RequestSpecification spec01;
	protected RequestSpecification spec02;
	protected RequestSpecification spec03;
	
	@Before
	public void setup01() {
		spec01 = new RequestSpecBuilder().setBaseUri("https://restful-booker.herokuapp.com/booking").build();
	}
	@Before
	public void setup02() {
		spec02 = new RequestSpecBuilder().setBaseUri("http://dummy.restapiexample.com/api/v1/employee").build();
	}
	@Before
	public void setUp03() {
		spec03 = new RequestSpecBuilder().setBaseUri("http://api.openweathermap.org").build();
		
	}

}
