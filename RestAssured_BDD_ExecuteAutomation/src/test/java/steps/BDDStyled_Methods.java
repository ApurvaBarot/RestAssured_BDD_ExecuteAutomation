package steps;

import static io.restassured.RestAssured.*;
//import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.Matchers.*;

import java.util.HashMap;

import io.restassured.http.ContentType;

public class BDDStyled_Methods {

public static void simpleGETPost(String postNumber) {
		
		given()
			.contentType(ContentType.JSON)
		.when()
			.get(String.format("http://localhost:3000/posts/%s", postNumber))
		.then()
			.body("author", is("Apurva"));	
	}
	
public static void performContainCollection() {
		
		given()
			.contentType(ContentType.JSON)
		.when()
			.get("http://localhost:3000/posts")
		.then()
			.body("author", containsInAnyOrder("Apurva", "Sandeep", null,"Kriansh", "Pawan"))
			.statusCode(200);	
	}

public static void performPathParameter() {
	
		given()
			.contentType(ContentType.JSON)
		.with()
			.pathParams("post", 1)
		.when()
			.get("http://localhost:3000/posts/{post}")
		.then()
			.body("author", containsString("Apurva"))
			.statusCode(200);	
}

public static void performQueryParameter() {
	
		given()
			.contentType(ContentType.JSON)
		.with()
			.queryParams("id", 2)
		.when()
			.get("http://localhost:3000/posts/")
		.then()
			.body("author", contains("Sandeep"))
			.statusCode(200);	
}

public static void performPostWithBodyParameter() {
		
	HashMap<String, String> postContent = new HashMap<>();
	postContent.put("id", "7");
	postContent.put("title", "New_AI");
	postContent.put("author", "Artifical Intelligence");
	
	given()
		.contentType(ContentType.JSON)
	.with()
		.body(postContent)
	.when()
		.post("http://localhost:3000/posts/")
	.then()
		.body("author", containsStringIgnoringCase("Artifical Intelligence"));
}
}
