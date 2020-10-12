package com.hurix.api.externalAPIs;

import static io.restassured.RestAssured.given;
import io.restassured.response.Response;
import com.hurix.automation.utility.Log;

public class RegisterUser_ext {

	static String registerUserBody=null;
	public static Response registerUser_ext(String consumerKey, String consumerSecret,String email,String firstName,String lastName,String userName,long clientUserID)
	{	
		registerUserBody ="{\"user\":{\"firstName\":\""+firstName+"\",\"lastName\":\""+lastName+"\",\"userName\":\""+userName+"\","
				+ "\"password\":\"kitaboo@123\",\"clientUserID\":\""+clientUserID+"\",\"email\":\""+email+"\"}}";

		Response jsonResponse = null;
		try {
			Log.startTestCase("RegisterUser");
			Log.info("consumerKey : "+consumerKey);
			Log.info("consumerSecret : "+consumerSecret);
			Log.info("email : "+email);
			Log.info("registerUserBody : "+registerUserBody);
			jsonResponse = given()
					.auth()
					.oauth(consumerKey, consumerSecret, "", "")
					.header("Content-Type","application/json")			
					.body(registerUserBody)
					.post("/DistributionServices/ext/api/registerUser");

			Log.info("RegisterUser Response: "+jsonResponse.then().extract().response().prettyPrint());
		} catch (Exception exp) 
		{
			Log.fail(exp.getMessage());
		}
		Log.endTestCase("End");
		return jsonResponse;

	}

}
