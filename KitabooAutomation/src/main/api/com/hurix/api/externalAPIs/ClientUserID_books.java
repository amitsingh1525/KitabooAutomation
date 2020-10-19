package com.hurix.api.externalAPIs;

import static io.restassured.RestAssured.given;
import io.restassured.response.Response;

import com.hurix.automation.utility.Log;

public class ClientUserID_books {	
	
	public static Response clientUserID_books(String consumerKey, String consumerSecret, String search,String clientUserID)
	{
		//String GETclientUserID_booksPath=""+com.hurix.api.utility.ExcelUtils.getbaseURI()+"/DistributionServices/ext/api/"+com.hurix.api.runner.RestAssured.clientUserID+"/books?search="+search+"";
		Log.startTestCase("Books/clientUserID = "+clientUserID+"");
		Log.info("clientUserID : " +clientUserID);
		Log.info("consumerKey : " +consumerKey);
		Log.info("consumerSecret : " +consumerSecret);
		Log.info("search : " +search);
		Log.info("search : " +"/DistributionServices/ext/api/"+clientUserID+"/books?search="+search+"");
		Response jsonResponse = null;
		try {
			//System.out.println("RequestURL:" +GETclientUserID_booksPath);
			jsonResponse = given()
					.auth()
					.oauth(consumerKey, consumerSecret, "", "")
					.header("Content-Type","application/json")								
					.get("/DistributionServices/ext/api/"+clientUserID+"/books?search="+search+"");					
							
			Log.info("Books/clientUserID = "+clientUserID+" Response: "+jsonResponse.then().extract().response().prettyPrint());
		} catch (Exception exp) 
		{
			Log.fail(exp.getMessage());
			exp.printStackTrace();
		}
		Log.endTestCase("End");
		return jsonResponse;
	}
}
