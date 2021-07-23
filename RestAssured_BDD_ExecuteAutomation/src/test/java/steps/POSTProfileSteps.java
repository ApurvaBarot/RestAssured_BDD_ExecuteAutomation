package steps;

import static org.junit.Assert.assertThat;

import java.util.HashMap;

import org.junit.Assert;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import io.restassured.response.ResponseOptions;
import utilities.RestAssuredExtension;

public class POSTProfileSteps {

	public static ResponseOptions<Response> response;

	@Given("I perform POST operation for {string} with body")
	public void i_perform_post_operation_for_with_body(String url, DataTable table) {

		// set body
		HashMap<String, String> body = new HashMap<>();
		body.put(table.cell(0, 0), table.cell(1, 0)); // set name as Barot

		// path params
		HashMap<String, String> params = new HashMap<>();
		params.put(table.cell(0, 1), table.cell(1, 1));

		// perform POST operation
		response = RestAssuredExtension.postOpsWithPathAndBodyParams(url, params, body);
	}

	@Then("I should see the body has name as {string}")
	public void i_should_see_the_body_has_name_as(String expectedName) {

//		assertThat(response.getBody().jsonPath().get("name"), equals(expectedName));
		Assert.assertEquals(response.getBody().jsonPath().get("name"), expectedName);
	}

	@Given("I ensure to perform  POST operation for {string} with body as")
	public void i_ensure_to_perform_post_operation_for_with_body_as(String url, DataTable table) {

		HashMap<String, String> body = new HashMap<>();
		body.put(table.cell(0, 0), table.cell(1, 0)); // id
		body.put(table.cell(0, 1), table.cell(1, 1)); // title
		body.put(table.cell(0, 2), table.cell(1, 2)); // author
		
		//perform Post operation
		RestAssuredExtension.postOpswithBody(url, body);
	}

	@When("I perform DELETE operation for {string}")
	public void i_perform_delete_operation_for(String url, DataTable table) {
		HashMap<String, String> pathParams = new HashMap<>();
		pathParams.put(table.cell(0, 0), table.cell(1, 0)); // id
		
		//perform Delete operation
		RestAssuredExtension.DeleteOpswithPathParams(url, pathParams);
	}

	@When("I perform GET operation with path parameter for {string}")
	public void i_perform_get_operation_with_path_parameter_for(String url,
			DataTable table) {
		HashMap<String, String> pathParams = new HashMap<>();
		pathParams.put(table.cell(0, 0), table.cell(1, 0)); // id
		
		//perform Get operation
		response = RestAssuredExtension.getOpswithPathParams(url, pathParams);
	}

	@Then("I {string} see the body with title as {string}")
	public void i_should_not_see_the_body_with_title_as(String conditions, String expectedtitle) {
		
		if (conditions.equalsIgnoreCase("should not")) {
			Assert.assertEquals(response.getStatusCode(), 404);
		}else
		{
			Assert.assertTrue(response.getBody().jsonPath().get("title").equals(expectedtitle));
		}
		
		
	}
	
	@Given("I perform PUT operation for {string}")
	public void i_perform_put_operation_for(String url, DataTable table) {
		// set body
		HashMap<String, String> body = new HashMap<>();
		body.put(table.cell(0, 0), table.cell(1, 0)); // id
		body.put(table.cell(0, 1), table.cell(1, 1)); // title
		body.put(table.cell(0, 2), table.cell(1, 2)); // author

		// path params
		HashMap<String, String> params = new HashMap<>();
		params.put("postId", table.cell(1, 0));

		//perform PUT operation
		response = RestAssuredExtension.putOpsWithPathAndBodyParams(url, params, body);
	}


}
