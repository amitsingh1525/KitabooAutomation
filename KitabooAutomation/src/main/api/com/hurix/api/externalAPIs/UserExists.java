package com.hurix.api.externalAPIs;

import static io.restassured.RestAssured.given;
import io.restassured.response.Response;
import com.hurix.automation.utility.Log;

public class UserExists {
	
	public static Response userExists(String username,String consumerKey, String consumerSecret)
	{
		Response jsonResponse = null;
		try {			
			Log.startTestCase("UserExists");		
		
			Log.info("URL : "+"/DistributionServices/ext/api/userExists");
			Log.info("consumerKey : "+consumerKey);
			Log.info("consumerSecret : "+consumerSecret);
			Log.info("username : "+username);
			jsonResponse = given()
					.auth()
					.oauth(consumerKey, consumerSecret, "", "")
					.header("userName",username)
					.get("/DistributionServices/ext/api/userExists");
										
			Log.info("UserExists Response: "+jsonResponse.then().extract().response().prettyPrint());
		} catch (Exception exp) 
		{
			Log.fail(exp.getMessage());
			exp.printStackTrace();
		}
		Log.endTestCase("End");
		return jsonResponse;
	}
}
