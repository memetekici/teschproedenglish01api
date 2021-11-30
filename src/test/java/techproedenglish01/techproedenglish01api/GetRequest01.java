package techproedenglish01.techproedenglish01api;

import org.junit.Test;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;

public class GetRequest01 {
	
	@Test
	public void getMethod01() {
		
		Response response = given().
				            when().
				                get("https://restful-booker.herokuapp.com/booking");//responce objectde dataları store ediyoruz.
		response.prettyPrint();//Response u ancak bu şekilde print  yapıyoruz.
		
		System.out.println(response.getStatusCode());//status code muzu görmek için//200
		
		response.
		        then().
		        assertThat().
		        statusCode(200).
		        contentType("application/json");//Status kodumu kontrol ediyorum..Doğruysa yeşil çizgi,değilse kırmızı çicgi göreceğim
		//Yukarıda tekrarın önüne geçtik
		//Assert the format of response body; it should be in Json format
		response.then().assertThat().contentType(ContentType.JSON);//"application/json" yazmak da mümkün.
	}
	
	@Test
	public void getMethod02() {
		Response response = given().when().get("https://restful-booker.herokuapp.com/booking/3");
		response.
		        then().
		        assertThat().
		        contentType(ContentType.JSON);
		System.out.println(response.getStatusCode());
		response.prettyPrint();
		
		response.then().assertThat().statusCode(200).contentType("application/json");
	}

}
