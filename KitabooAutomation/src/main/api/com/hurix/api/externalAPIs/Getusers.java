package com.hurix.api.externalAPIs;

import static io.restassured.RestAssured.given;
import io.restassured.response.Response;

import com.hurix.automation.utility.Log;

public class Getusers {
	
	public static Response getusers(String consumerKey, String consumerSecret,String clientUserID)
	{
		Response jsonResponse = null;
		try {
			
			Log.startTestCase("Getusers");
			Log.info("clientUserID:" +clientUserID);
			Log.info("consumerKey:" +consumerKey);
			Log.info("consumerSecret:" +consumerSecret);
			jsonResponse = given()
					.auth()
					.oauth(consumerKey, consumerSecret, "", "")
					.header("Content-Type","application/json")								
					.get("/DistributionServices/ext/api/getClientUsers?clientUserID="+clientUserID+"");					
							
			Log.info("Getusers Response: "+jsonResponse.then().extract().response().prettyPrint());
		} catch (Exception exp) 
		{
			Log.fail(exp.getMessage());
			exp.printStackTrace();
		}
		Log.endTestCase("End");
		return jsonResponse;
	}
	public static Response getusers_pagi(int startIndex,int endIndex,String consumerKey, String consumerSecret,String clientUserID)
	{
		Response jsonResponse = null;
		try {
			
			Log.startTestCase("Getusers");
			Log.info("clientUserID:" +clientUserID);
			Log.info("consumerKey:" +consumerKey);
			Log.info("consumerSecret:" +consumerSecret);
			Log.info("startIndex :" +startIndex);
			Log.info("endIndex :" +endIndex);
			jsonResponse = given()
					.auth()
					.oauth(consumerKey, consumerSecret, "", "")
					.header("Content-Type","application/json")	
					.header("startIndex",startIndex)
					.header("endindex", endIndex)
					.get("/DistributionServices/ext/api/getClientUsers?clientUserID="+clientUserID+"");					
							
			Log.info("Getusers Response: "+jsonResponse.then().extract().response().prettyPrint());
		} catch (Exception exp) 
		{
			Log.fail(exp.getMessage());
			exp.printStackTrace();
		}
		Log.endTestCase("End");
		return jsonResponse;
	}


}
