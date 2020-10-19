package com.hurix.api.externalAPIs;

import static io.restassured.RestAssured.given;
import io.restassured.response.Response;

import com.hurix.automation.utility.Log;

public class FetchClassAnalytics {
	
	public static Response fetchClassAnalytics(int clientClassID,String consumerKey, String consumerSecret)
	{
		//String GETclientUserID_booksPath=""+com.hurix.api.utility.ExcelUtils.getbaseURI()+"/DistributionServices/ext/api/"+com.hurix.api.runner.RestAssured.clientUserID+"/books?search="+search+"";
		Log.startTestCase("FetchClassAnalytics");
		Log.info("clientClassID : " +clientClassID);
		Log.info("consumerKey : " +consumerKey);
		Log.info("consumerSecret : " +consumerSecret);		
		Log.info("URL : " +"/DistributionServices/ext/api/analytics/"+clientClassID+"/fetchClassAnalytics");
		Response jsonResponse = null;
		try {
			//System.out.println("RequestURL:" +GETclientUserID_booksPath);
			jsonResponse = given()
					.auth()
					.oauth(consumerKey, consumerSecret, "", "")
					.header("Content-Type","application/json")								
					.get("/DistributionServices/ext/api/analytics/"+clientClassID+"/fetchClassAnalytics");					
							
			Log.info("FetchClassAnalytics Response: "+jsonResponse.then().extract().response().prettyPrint());
		} catch (Exception exp) 
		{
			Log.fail(exp.getMessage());
			Log.fail("fails due to"+ exp.getCause());
		}
		Log.endTestCase("End");
		return jsonResponse;
	}
}
