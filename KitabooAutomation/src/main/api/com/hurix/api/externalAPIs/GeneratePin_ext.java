package com.hurix.api.externalAPIs;

import static io.restassured.RestAssured.given;
import io.restassured.response.Response;

import com.hurix.automation.utility.Log;

public class GeneratePin_ext {

	//static String generatepinBody;
	public static Response generatePin_ext(String consumerKey, String consumerSecret,String clientUserID)
	{
		Response jsonResponse = null;
		try {

			
			Log.info("URL : "+"/DistributionServices/ext/api/generatePin?clientUserID="+clientUserID+"");
			Log.info("consumerKey : "+consumerKey);
			Log.info("consumerSecret : "+consumerSecret);
			
			jsonResponse = given()
					.auth()
					.oauth(consumerKey, consumerSecret, "", "")
					.header("Content-Type","application/json")
					//.body(generatepinBody)					
					.post("/DistributionServices/ext/api/generatePin?clientUserID="+clientUserID+"");

			Log.info("GeneratePin_ext Response: "+jsonResponse.then().extract().response().prettyPrint());
		} catch (Exception exp) 
		{
			Log.fail(exp.getMessage());
			Log.fail("fails due to"+ exp.getCause());
		}
		Log.endTestCase("End");
		return jsonResponse;
	}
}
