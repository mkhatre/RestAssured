package rest;
import org.testng.Assert;
import org.testng.annotations.Test;
import io.restassured.RestAssured;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.http.Method;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;


public class FirstRest {
	
	
	@Test
	public void GetWeatherDetailsInvalidCity() {
		
		RestAssured.baseURI = "http://restapi.demoqa.com/utilities/weather/city";
		RequestSpecification httpRequest = RestAssured.given();
		Response response = httpRequest.request(Method.GET, "/Hyd");
		
		String responseBody = response.getBody().asString();
		System.out.println(responseBody);
		
		//Response Code
		int statusCode = response.getStatusCode();
		System.out.println("statusCode: " + statusCode);
		Assert.assertEquals(statusCode /*actual value*/, 400 /*expected value*/);
		
		//Response message
		String statusLine = response.getStatusLine();
		System.out.println("statusLine: " + statusLine);
		Assert.assertEquals(statusLine /*actual value*/, "HTTP/1.1 400 Bad Request" /*expected value*/);
		
		
	}
	
	@Test
	public void GetWeatherDetails() {
		
		RestAssured.baseURI = "http://restapi.demoqa.com/utilities/weather/city";
		RequestSpecification httpRequest = RestAssured.given();
		Response response = httpRequest.request(Method.GET, "/Hyderabad");
		
		//Response Body
		String responseBody = response.getBody().asString();
		System.out.println(responseBody);
		Assert.assertEquals(responseBody.contains("Hyderabad") /* actual value */, true /* expected value */);
		
		JsonPath jsonPathEval = response.jsonPath();
		String city = jsonPathEval.get("City");
		System.out.println("City is: "+ city);
		Assert.assertEquals(city, "Hyderabad");
		
		int statusCode = response.getStatusCode();
		System.out.println("statusCode: " + statusCode);
		Assert.assertEquals(statusCode /*actual value*/, 200 /*expected value*/);

		String statusLine = response.getStatusLine();
		System.out.println("statusLine: " + statusLine);
		Assert.assertEquals(statusLine /*actual value*/, "HTTP/1.1 200 OK" /*expected value*/);
		System.out.println("----------------");
	
	    //Response Header:
		//Content-Type
		String contentType = response.header("Content-Type");
		System.out.println("Content-Type : " + contentType );
		
		//Server
		String serverType = response.header("Server");
		System.out.println("Server : " + serverType);
		
		//Content-Encoding
		String contentEncoding = response.header("Content-Encoding");
		System.out.println("Content-Encoding : " + contentEncoding);
		System.out.println("----------------");
		
		//Headers allHeaders = response.headers();
		Headers allHeaders = response.getHeaders();
		
		for(Header header : allHeaders) {
			
			System.out.println(header.getName() + " | " + header.getValue());
		}
		
		System.out.println("----------------");
		

	
	}

}
