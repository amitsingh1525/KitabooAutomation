package com.hurix.api.externalAPIs;

import static io.restassured.RestAssured.given;

import com.hurix.automation.utility.Log;

import io.restassured.response.Response;

public class SearchData {
	
	public static Response searchData(String consumerKey, String consumerSecret)
	{
		Response jsonResponse = null;
		try {
			
			Log.startTestCase("SearchData");
			Log.info("URL : "+"/DistributionServices/ext/api/searchData");
			Log.info("consumerKey : "+consumerKey);
			Log.info("consumerSecret : "+consumerSecret);
			jsonResponse = given()
					.auth()
					.oauth(consumerKey, consumerSecret, "", "")
					.header("Content-Type","application/json")
					.get("/DistributionServices/ext/api/searchData");
										
			Log.info("SearchData Response: "+jsonResponse.then().extract().response().prettyPrint());
		} catch (Exception exp) 
		{
			Log.fail(exp.getMessage());
			Log.fail("fails due to"+ exp.getCause());
		}
		Log.endTestCase("End");
		return jsonResponse;
	}
}
