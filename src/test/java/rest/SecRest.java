package rest;
import org.junit.*;
import static io.restassured.RestAssured.*;
//import static io.restassured.RestAssured.given;

public class SecRest {

	public String baseURI = "http://restapi.demoqa.com/utilities/weather/city/";
	public String city = "Hyderabad";
	
	@Before
	public void setup() {
//		public String baseURI = "http://restapi.demoqa.com/utilities/weather/city";
//		public String basePath = "";
		
		
	}
	
	@Test
	public void test_URI_correct() {
		given().
		when().
			get(baseURI+city).
		then().
			statusCode(200);

	}
	
	@Test
	public void test_content_type() {
		given().
		when().
			get(baseURI+city).
		then().
			contentType("application/json");
	}
	

}
