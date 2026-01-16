package shoppersStack_BackEnd;
import static io.restassured.RestAssured.*;

import java.util.HashMap;
import java.util.Random;

import org.hamcrest.Matchers;
import org.testng.annotations.Test;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class PostRequest_RegisterUserTest {
	int userId;
	String token;
	Random rand=new Random();
	int randNum=rand.nextInt(10000);
	String baseUri="https://www.shoppersstack.com/shopping";
	
	
	
@Test(priority=0) 
public void postRequestRegisterUser_Test() {
	HashMap<String,Object> hash=new HashMap<>();
	hash.put("city", "bhopal");
	hash.put("country", "india");
	hash.put("email", "sumitshukla"+randNum+"@gmail.com");
	hash.put("firstName", "gopal");
	hash.put("gender", "MALE");
	hash.put("lastName", "shukla");
	hash.put("password", "gopal123@@");
	hash.put("phone", 9874563210l);
	hash.put("state", "mp");
	hash.put("zoneId", "ALPHA");
	
	Response resp = given()
	.relaxedHTTPSValidation()
	.baseUri(baseUri)
	 //.queryParam("zoneId", "ALPHA")
	 .contentType(ContentType.JSON)
	 .body(hash)
	 
	.when()
	     .post("/shoppers");
	     
	resp .then().log().all();
//	       .assertThat().statusCode(201).log().all()
//	       .assertThat().contentType(ContentType.JSON)
//	       .assertThat().body("data.firstName",Matchers.equalTo("gopal"));
	
	/*fetching the shopper id*/
	userId=resp.jsonPath().get("data.userId");
	
	/*fetch the token */
	token=resp.jsonPath().get("data.jwtToken");
}

@Test(priority=1)
public void getShopper() {
	Response resp = given().auth().oauth2(token)
	  .relaxedHTTPSValidation()
	  .baseUri(baseUri)
	  .contentType(ContentType.JSON)
	  
	 .when()
	     .get("/shoppers/"+userId);
	
	resp .then()
	        .log().all()
	        .assertThat().statusCode(200)
	        .assertThat().body("data.firstName",Matchers.equalTo("gopal"));
	 
	
}

}
