package com.hurix.api.externalAPIs;

import static io.restassured.RestAssured.given;
import io.restassured.response.Response;

import com.hurix.automation.utility.Log;

public class ListCollection {
	
	public static Response listCollection(String consumerKey, String consumerSecret)
	{		
		Response jsonResponse = null;
		try {
			Log.startTestCase("ListCollection");
			
			jsonResponse = given()
					.auth()
					.oauth(consumerKey, consumerSecret, "", "")
					.header("Content-Type","application/json")
					.get("/DistributionServices/ext/api/ListCollection");
		
			
			Log.info("ListCollection Response: "+jsonResponse.then().extract().response().prettyPrint());
		} catch (Exception exp) 
		{
			Log.fail(exp.getMessage());
			Log.fail("fails due to"+ exp.getCause());
		}
		Log.endTestCase("End");
		return jsonResponse;		
	}
}
