package com.hurix.api.externalAPIs;

import static io.restassured.RestAssured.given;
import io.restassured.response.Response;

import com.hurix.automation.utility.Log;

public class GetUserBookDetails {
	public static Response getUserBookDetails(String clientUserID_Instructor,String consumerKey, String consumerSecret)
	{
		Log.startTestCase("GetUserBookDetails");
		Log.info("ClientUserID_Instructor : " +clientUserID_Instructor);
		Log.info("consumerKey : " +consumerKey);
		Log.info("consumerSecret : " +consumerSecret);		
		Log.info("URL : " +"/DistributionServices/ext/api/"+clientUserID_Instructor+"/getUserBookDetails");
		Response jsonResponse = null;
		try {
			//System.out.println("RequestURL:" +GETclientUserID_booksPath);
			jsonResponse = given()
					.auth()
					.oauth(consumerKey, consumerSecret, "", "")
					.header("Content-Type","application/json")								
					.get("/DistributionServices/ext/api/"+clientUserID_Instructor+"/getUserBookDetails");					
							
			Log.info("GetUserBookDetails Response: "+jsonResponse.then().extract().response().prettyPrint());
		} catch (Exception exp) 
		{
			Log.fail(exp.getMessage());
			exp.printStackTrace();
		}
		Log.endTestCase("End");
		return jsonResponse;
	}
}
