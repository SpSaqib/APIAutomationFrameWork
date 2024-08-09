package stepDefination;

import static io.restassured.RestAssured.given;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import pojo.Location;
import pojo.Pojo1JSON;
import resources.APIResources;
import resources.TestDataBuild;
import resources.Utils;

import static org.junit.Assert.*;

public class StepDefination extends Utils {

	// Global variables
	Response response;
	RequestSpecification res;
	ResponseSpecification resSpec;
	JsonPath js;
	static String place_id;

	TestDataBuild data = new TestDataBuild();

	@Given("Add Place Payload {string} {string} {string}")
	public void add_Place_Payload(String name, String language, String address) throws IOException {

		res = given().spec(requestSpecification()).body(data.addPlacePayload(name, language, address));

		resSpec = new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();
	}

	@When("user calls {string} with {string} http request")
	public void user_calls_with_http_request(String resource, String method) {
		// Write code here that turns the phrase above into concrete actions

		APIResources resouceAPI = APIResources.valueOf(resource);
		System.out.println(resouceAPI.getResource());

		if (method.equalsIgnoreCase("POST"))
			response = res.when().post(resouceAPI.getResource());

		else if (method.equalsIgnoreCase("GET"))
			response = res.when().post(resouceAPI.getResource());

	}

	@Then("the API call is success with status code {int}")
	public void the_API_call_is_success_with_status_code(Integer int1) {
		// Write code here that turns the phrase above into concrete actions

		assertEquals(response.getStatusCode(), 200);
	}

	@Then("{string} in response body is {string}")
	public void in_response_body_is(String keyValue, String Expectedvalue) {
		// Write code here that turns the phrase above into concrete actions

		assertEquals(getJsonPath(response, keyValue), Expectedvalue);

	}

	@Then("Verify place_id is created map to {string} using {string}")
	public void verify_place_id_is_created_map_to_using(String expectedName, String resource) throws IOException {
		// request Spec

		place_id = getJsonPath(response, "place_id");

		res = given().spec(requestSpecification()).queryParam("place_id", place_id);

		user_calls_with_http_request(resource, "GET");
		String actualNname = getJsonPath(response, "name");
		assertEquals(actualNname, expectedName);

	}

	@Given("DeletePlace Payload")
	public void deleteplace_Payload() throws IOException {

		res = given().spec(requestSpecification()).body(data.deletePlacePayload(place_id));

	}
	
	

}
