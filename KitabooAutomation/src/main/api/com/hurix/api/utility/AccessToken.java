package com.hurix.api.utility;

import static io.restassured.RestAssured.given;
import io.restassured.response.Response;

import com.hurix.automation.utility.Log;

public class AccessToken {
	
	public static Response accessToken(String consumerKey, String consumerSecret)
	{		
		Response jsonResponse = null;
		try {
			Log.startTestCase("AccessToken");
			Log.info("consumerKey : "+consumerKey);
			Log.info("consumerSecret : "+consumerSecret);
			jsonResponse = given()
					.formParam("client_id", consumerKey)
					.formParam("client_secret", consumerSecret)
					.formParam("grant_type", "client_credentials")
					.post("/DistributionServices/oauth2/authToken");
						
			Log.info("AccessToken Response: "+jsonResponse.then().extract().response().prettyPrint());
		} catch (Exception exp) 
		{
			Log.fail(exp.getMessage());
			Log.fail("fails due to"+ exp.getCause());
		}
		Log.endTestCase("End");
		return jsonResponse;		
	}
}
