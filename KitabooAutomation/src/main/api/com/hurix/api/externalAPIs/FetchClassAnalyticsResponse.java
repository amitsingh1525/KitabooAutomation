package com.hurix.api.externalAPIs;

import static io.restassured.RestAssured.given;
import io.restassured.response.Response;

import com.hurix.automation.utility.Log;

public class FetchClassAnalyticsResponse {
	public static Response fetchClassAnalyticsResponse(String tokenId,String consumerKey, String consumerSecret)
	{
		Log.startTestCase("FetchClassAnalyticsResponse");
		Log.info("tokenId : " +tokenId);
		Log.info("consumerKey : " +consumerKey);
		Log.info("consumerSecret : " +consumerSecret);		
		Log.info("URL : " +"/DistributionServices/ext/api/analytics/fetchClassAnalyticsResponse?tokenId="+tokenId+"");
		Response jsonResponse = null;
		try {
			//System.out.println("RequestURL:" +GETclientUserID_booksPath);
			jsonResponse = given()
					.auth()
					.oauth(consumerKey, consumerSecret, "", "")
					.header("Content-Type","application/json")								
					.get("/DistributionServices/ext/api/analytics/fetchClassAnalyticsResponse?tokenId="+tokenId+"");					
							
			Log.info("FetchClassAnalyticsResponse Response: "+jsonResponse.then().extract().response().prettyPrint());
		} catch (Exception exp) 
		{
			Log.fail(exp.getMessage());
			Log.fail("fails due to"+ exp.getCause());
		}
		Log.endTestCase("End");
		return jsonResponse;
	}
}
