package steps;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import java.util.HashMap;
import java.util.Map;

import org.junit.Assert;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.restassured.response.Response;
import io.restassured.response.ResponseOptions;
import pojo.Address;
import pojo.Location;
import pojo.LoginBody;
import pojo.Posts;
import utilities.APIConstants;
import utilities.RestAssuredExtension;
import utilities.RestAssuredExtensionv2;


public class GET_PostSteps {

	public static ResponseOptions<Response> response;
		public static String token;
	
	@Given("I perform GET operation for the {string}")
	public void i_perform_get_operation_for_the(String url) {
		response = RestAssuredExtension.getOpsWithToken(url, token);
	}

	@Then("I should see the author name as {string}")
	public void i_should_see_the_author_name_as(String authorName) {

		// with Builder pattern (Posts.java) pojo class
		var posts = new Posts.Builder().build();
		var post = response.getBody().as(posts.getClass());
		Assert.assertTrue(post.getAuthor().equals(authorName));
		
		// w/o builder pattern (Posts.java)
		/*
		 * var posts = response.getBody().as(Posts.class);
		 * Assert.assertTrue(posts.getAuthor().equals(authorName));
		 */

		// assertThat(response.getBody().jsonPath().get("author"), hasItem(authorName));
	}

	@Then("I should see the author names")
	public void i_should_see_the_author_names() {
		BDDStyled_Methods.performContainCollection();
		;
	}

	@Then("I should verify parameters")
	public void i_should_verify_parameters() {
		BDDStyled_Methods.performPathParameter();
//		BDDStyled_Methods.performQueryParameter();
	}

	@Given("I perform authentication operation for {string} with body")
	public void i_perform_authentication_operation_for_with_body(String uri, DataTable table) {

		/*
		 * HashMap<String, String> body = new HashMap(); body.put(table.cell(0, 0),
		 * table.cell(1, 0)); body.put(table.cell(0, 1), table.cell(1, 1));
		 */
		
		LoginBody loginBody = new LoginBody();
		loginBody.setEmail(table.cell(1, 0));
		loginBody.setPassword(table.cell(1, 1));
		
		//old code
//		response = RestAssuredExtension.postOpswithBody(url, body);
		
		//new code
		RestAssuredExtensionv2 restAssuredExtensionv2 = 
				new RestAssuredExtensionv2(uri, APIConstants.ApiMethods.POST, null);
		token = restAssuredExtensionv2.Authenticate(loginBody);
	}

	@Given("I perform GET operation with path paramater for address {string}")
	public void i_perform_get_operation_with_path_paramater_for_address(String uri, DataTable table) {

		Map<String, String> queryParams = new HashMap();
		queryParams.put(table.cell(0, 0), table.cell(1, 0));

		RestAssuredExtensionv2 restAssuredExtensionv2 = new RestAssuredExtensionv2(uri, APIConstants.ApiMethods.GET, token);
		response = restAssuredExtensionv2.ExecuteWithQueryParams(queryParams);
	}

	@Then("I should see the street name as {string} for the {string} address")
	public void i_should_see_the_street_name_as_for_the_address(String streetName, String type) {

		var location = response.getBody().as(Location[].class);
		//here we can get multiple data like primary & secondary address and hence we used this approach to filter records
		Address addres = location[0].getAddress().stream().filter(x -> x.getType().equalsIgnoreCase(type))
															.findFirst().orElse(null);	
		
		System.out.println(addres.getStreet());
		Assert.assertEquals(streetName, addres.getStreet());

	}
	
	@Then("I should see the author name as {string} with JSON validation")
	public void i_should_see_the_author_name_as_with_json_validation(String string) {
	    var responseBody = response.getBody().asString();
	    System.out.println("Response body is ---> " + responseBody);
	    Assert.assertEquals(matchesJsonSchemaInClasspath("json/file/POSTS.json"), responseBody);
	    
	    
	}

}
