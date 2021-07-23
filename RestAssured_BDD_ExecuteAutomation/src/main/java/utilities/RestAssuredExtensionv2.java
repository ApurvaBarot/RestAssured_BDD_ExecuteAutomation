package utilities;

import java.util.Map;

import io.cucumber.java.it.Ma;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ResponseOptions;
import io.restassured.specification.RequestSpecification;

public class RestAssuredExtensionv2 {
	
	private RequestSpecBuilder builder = new RequestSpecBuilder();
	private String method;
	private String url;
	
	
	/**
	 * RestAssuredExtensionv2 constructor to pass the initial setting to the following methods.
	 * @param uri
	 * @param method
	 * @param token
	 */
	public RestAssuredExtensionv2(String uri, String method, String token) {
		
			//Formulate the API url
		this.url = "http://localhost:3000" +uri;
		this.method = method;
		if(token!=null) {
			builder.addHeader("Authorization", "Bearer "+token);
		}
	}
	
	
	/**
	 * ExecuteAPI to execute the API for GET, POST, DELETE
	 * @return ResponseOptions<Response>
	 */
	private ResponseOptions<Response> ExecuteAPI() {
		RequestSpecification requestSpecification = builder.build();
		RequestSpecification request = RestAssured.given();
		request.contentType(ContentType.JSON);
		request.spec(requestSpecification);
		
		if(this.method.equalsIgnoreCase(APIConstants.ApiMethods.POST))
			return request.post(this.url);
		else if(this.method.equalsIgnoreCase(APIConstants.ApiMethods.DELETE))
			return request.delete(this.url);
		else if(this.method.equalsIgnoreCase(APIConstants.ApiMethods.GET))
			return request.get(this.url);
		else return null;
	}
	
	/**
	 * Authenticate to get the token variable
	 * @param body
	 * @return string token
	 */
	public String Authenticate(Object loginBody) {
		builder.setBody(loginBody);
		return ExecuteAPI().getBody().jsonPath().get("access_token");
		
	}
	
	/**
	 * Execute API with queryParams being passed as the input of it 
	 * @param queryParams
	 * @return response
	 */
	public ResponseOptions<Response> ExecuteWithQueryParams(Map<String, String> queryParams) {
		builder.addQueryParams(queryParams);
		return ExecuteAPI();
	}
	
	/**
	 * Execute API with pathParams being passed as the input of it 
	 * @param pathParams
	 * @return response
	 */
	public ResponseOptions<Response> ExecuteWithPathParams(Map<String, String> pathParams) {
		builder.addPathParams(pathParams);
		return ExecuteAPI();
	}
	
	/**
	 * Execute API with pathParams & Body param being passed as the input of it 
	 * @param pathParams
	 * @param body
	 * @return response
	 */
	public ResponseOptions<Response> ExecuteWithPathParamsAndBody(Map<String, String> pathParams, Map<String, String> body) {
		builder.setBody(body);
		builder.addPathParams(pathParams);
		return ExecuteAPI();
	}
	
	
}
