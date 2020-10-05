package com.hurix.api.externalAPIs;

import static io.restassured.RestAssured.given;
import io.restassured.response.Response;
import com.hurix.automation.utility.Log;

public class GetClientUsers {
	
	public static Response getClientUsers(String consumerKey, String consumerSecret)
	{
		Response jsonResponse = null;
		try {
			
			Log.startTestCase("GetClientUsers");
			//System.out.println("RequestURL:" +GETgetBookMetadataPath);
			jsonResponse = given()
					.auth()
					.oauth(consumerKey, consumerSecret, "", "")
					.header("Content-Type","application/json")								
					.get("/DistributionServices/ext/api/getClientUsers");					
							
			Log.info("GetClientUsers Response: "+jsonResponse.then().extract().response().prettyPrint());
		} catch (Exception exp) 
		{
			Log.fail(exp.getMessage());
			exp.printStackTrace();
		}
		Log.endTestCase("End");
		return jsonResponse;
	}
	
	public static Response getClientUsers_Pagi(int startIndex,int endindex,String consumerKey, String consumerSecret)
	{
		Response jsonResponse = null;
		try {			
			Log.startTestCase("GetClientUsers");
			//System.out.println("RequestURL:" +GETgetBookMetadataPath);
			jsonResponse = given()
					.auth()
					.oauth(consumerKey, consumerSecret, "", "")
					.header("Content-Type","application/json")
					.header("startIndex",startIndex)
					.header("endindex", endindex)
					.get("/DistributionServices/ext/api/getClientUsers");					
							
			Log.info("GetClientUsers Response: "+jsonResponse.then().extract().response().prettyPrint());
		} catch (Exception exp) 
		{
			System.out.println(exp.getMessage());
			System.out.println(exp.getCause());
			exp.printStackTrace();
		}
		Log.endTestCase("End");
		return jsonResponse;
	}

}
