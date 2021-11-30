package techproedenglish01.techproedenglish01api;
import static io.restassured.RestAssured.*;
import static org.junit.Assert.assertEquals;

import static org.hamcrest.Matchers.*;
import org.junit.Test;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class GetRequest07 extends TestBasedt {
	/*
	 When I send a GET request to REST API URL
						 https://jsonplaceholder.typicode.com/todos/123
					     Then HTTP Status Code should be 200
					     And "Server" in Headers should be "cloudflare"
					     And response content type is “application/JSON”
					     And "userId" should be 7,
					     And "title" should be "esse et quis iste est earum aut impedit"
						 And "completed" should be false
	 */
	
	/*
	 After the Base URL if you type something together with "/" like "/123" it s called "path param"
	 "path param" makes the source small.
	 */
	@Test
	public void get01() {
		
		spec01.pathParam("id", 123);
		
		Response response = given().
                spec(spec01).
             when().
                get("/{id}");//Test base deki url e /123 ü ekledik
             response.prettyPrint();
             
        response.
              then().
              assertThat().
              statusCode(200).
              contentType(ContentType.JSON).
              body("userId", equalTo(7),
            		"title", equalTo("esse et quis iste est earum aut impedit"),
            		"completed", equalTo(false));//Booelan olduğu için "" içine almadık.
        
        assertEquals(response.getHeader("Server"), "cloudflare");
		
		
	}

}
