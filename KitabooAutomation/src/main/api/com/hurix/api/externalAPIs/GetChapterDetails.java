package com.hurix.api.externalAPIs;

import static io.restassured.RestAssured.given;
import io.restassured.response.Response;

import com.hurix.automation.utility.Log;

public class GetChapterDetails {
	
	public static Response getChapterDetails(String clientBookId,String consumerKey, String consumerSecret)
	{
		Log.startTestCase("GetChapterDetails");
		Log.info("clientBookId : " +clientBookId);
		Log.info("consumerKey : " +consumerKey);
		Log.info("consumerSecret : " +consumerSecret);		
		Log.info("URL : " +"/DistributionServices/ext/api/"+clientBookId+"/chapters");
		Response jsonResponse = null;
		try {
			//System.out.println("RequestURL:" +GETclientUserID_booksPath);
			jsonResponse = given()
					.auth()
					.oauth(consumerKey, consumerSecret, "", "")
					.header("Content-Type","application/json")								
					.get("/DistributionServices/ext/api/"+clientBookId+"/chapters");					
							
			Log.info("GetChapterDetails Response: "+jsonResponse.then().extract().response().prettyPrint());
		} catch (Exception exp) 
		{
			Log.fail(exp.getMessage());
			exp.printStackTrace();
		}
		Log.endTestCase("End");
		return jsonResponse;
	}
}