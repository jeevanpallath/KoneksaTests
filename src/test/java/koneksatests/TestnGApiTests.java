package koneksatests;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReporter;

import Reports.ExtReporter;
import demopages.Variables;

import static io.restassured.RestAssured.*;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import static org.hamcrest.Matchers.*;

import org.apache.http.HttpStatus;
import org.json.simple.JSONObject;

public class TestnGApiTests {
	private String nameValue = "Arnold";
	private String nameParam = "name";
	private String baseURL = "https://reqres.in/api/users";
	private String id = "";
	static ExtReporter extentReporter ;
	
	@BeforeClass
	public static void setup() {
		System.out.println("\nStarting REST API Tests\n");
		extentReporter = new ExtReporter(Variables.APITestReportFile);
	}

	@Test
	(priority = 0)
	void samplePostBddTest() {
		System.out.println("\nStarting POST Test\n");
		
		String jobValue = "Actor";
		String jobParam = "job";

		JSONObject request = new JSONObject();
		request.put(nameParam, nameValue);
		request.put(jobParam, jobValue);
		id =
			given()
				.header("Content-Type","application/json")
				.accept(ContentType.JSON)
				.body(request.toJSONString()).
			and()
				.log().all().
			when()
				.post(baseURL).
			then()
				.statusCode(HttpStatus.SC_CREATED)
				.body(nameParam, equalTo(nameValue))
				.body(jobParam, equalTo(jobValue))
			.and()
				.log().everything().extract().path("id");

		System.out.println("New Id = "+id);
		extentReporter.pass("samplePostBddTest", "POST executed with New Id = "+id);
	}

	@Test
	(priority = 1)
	void sampleGetBddTest() {
		long index = 2;
		System.out.println("\nStarting GETT Test\n");
		
		given()
			.param("page", 2).
		and()
			.log().all().
		when()
			.get(baseURL)
		.then()
			.statusCode(HttpStatus.SC_OK)
			.body("data[" + index + "].id", equalTo( 9))
			.body("data.first_name", hasItems("Michael" , "Lindsay"))
		.and()
			.log().all();
		extentReporter.pass("sampleGetBddTest", "Executed successfully");
	}
	
	@Test
	(priority = 2)
	void sampleDeleteBddTest() {		
		System.out.println("\nStarting DELETE Test\n");
		
		JSONObject request = new JSONObject();
		request.put("id",  id);

		given()
			.param("id",  id)
			.header("Content-Type","application/json")
			.accept(ContentType.JSON)
		.and()
			.log().all().
		when()
			.delete(baseURL).
		then()
			.statusCode(HttpStatus.SC_NO_CONTENT)
		.and()
			.log().everything();
		extentReporter.pass("sampleDeleteBddTest", "Executed successfully");
		
	}	

	
	@Test
	(priority = 3)
	void sampleOldStyleTest() {
		System.out.println("\nStarting GET Test\n");
		
		RequestSpecification request = given().param("page", 1);		
		Response resp = request.log().all().get(baseURL);
		
		Assert.assertEquals(resp.getStatusCode(), HttpStatus.SC_OK);		
		resp.then().log().all();
		extentReporter.pass("sampleOldStyleTest", "Executed successfully");
		
		extentReporter.closeReport();
	}
	
	@AfterClass
	public void tearDown() {
		System.out.println("\nREST API Tests Finished\n");
	}

}
