package com.hurix.api.externalAPIs;

import static io.restassured.RestAssured.given;
import io.restassured.response.Response;

import com.hurix.automation.utility.Log;

public class GetBookMetadata {
	
	
	//public static String GETgetBookMetadataPath=""+com.hurix.api.utility.ExcelUtils.getbaseURI()+"/DistributionServices/ext/api/"+com.hurix.api.runner.RestAssured.clientBookID+"/getBookMetadata";

	public static Response getBookMetadata(String consumerKey, String consumerSecret)
	{
		Response jsonResponse = null;
		try {
			
			Log.startTestCase("getBookMetadata");
			//System.out.println("RequestURL:" +GETgetBookMetadataPath);
			jsonResponse = given()
					.auth()
					.oauth(consumerKey, consumerSecret, "", "")
					.header("Content-Type","application/json")								
					.get("/DistributionServices/ext/api/"+com.hurix.api.runner.RestAssured.clientBookID+"/getBookMetadata");
					
							
			Log.info("GetBookMetadata Response: "+jsonResponse.then().extract().response().prettyPrint());
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
