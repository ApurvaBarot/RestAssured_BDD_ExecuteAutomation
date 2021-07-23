package utilities;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.http.Header;
import io.restassured.response.Response;
import io.restassured.response.ResponseOptions;
import io.restassured.specification.RequestSpecification;

public class RestAssuredExtension {

	public static RequestSpecification Request;

	public RestAssuredExtension() {

		// Arrange
		// RequestSpecBuilder is used to club multiple common request like URI which
		// remains same throughout, content type is always JSON
		RequestSpecBuilder builder = new RequestSpecBuilder();
		builder.setBaseUri("http://localhost:3000");
		builder.setContentType(ContentType.JSON);
		var requestSpec = builder.build();
		Request = RestAssured.given().spec(requestSpec);
	}

	public static void getOpsWithPathParameter(String url, Map<String, String> pathParams) {
		// Act
		Request.pathParams(pathParams);
		try {
			Request.get(new URI(url)); // here new URI = exisitng URI + url received as parameter
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
	}

	public static ResponseOptions<Response> getOps(String url) {
		// Act
		try {
			return Request.get(new URI(url));
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static ResponseOptions<Response> getOpsWithToken(String url, String token) {
		// Act
		try {
			Request.header(new Header("Authorization", "Bearer "+token));	// space after Bearer is imp
			return Request.get(new URI(url));
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
		return null;
	}

	
	public static ResponseOptions<Response> postOpsWithPathAndBodyParams(String url, Map<String, String> pathParams,
			Map<String, String> body) {
		Request.pathParams(pathParams);
		Request.body(body);
		return Request.post(url);
	}

	public static ResponseOptions<Response> postOpswithBody(String url, Map<String, String> body) {

		Request.body(body);
		return Request.post(url);
	}

	public static ResponseOptions<Response> DeleteOpswithPathParams(String url, Map<String, String> pathParams) {

		Request.pathParams(pathParams);
		return Request.delete(url);
	}

	public static ResponseOptions<Response> getOpswithPathParams(String url, Map<String, String> pathParams) {

		Request.pathParams(pathParams);
		return Request.get(url);
	}

	public static ResponseOptions<Response> putOpsWithPathAndBodyParams(String url, HashMap<String, String> pathParams,
			HashMap<String, String> body) {
		
		Request.body(body);
		Request.pathParams(pathParams);
		return Request.put(url);
		
	}
	
	public static ResponseOptions<Response> getOpswithQueryParamsAndToken(String url, Map<String, String> queryParams, String token) {
		Request.header(new Header("Authorization", "Bearer "+token));
		Request.queryParams(queryParams);
		return Request.get(url);
	}

}











