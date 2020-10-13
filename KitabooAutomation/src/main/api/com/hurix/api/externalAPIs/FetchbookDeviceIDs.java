package com.hurix.api.externalAPIs;

import static io.restassured.RestAssured.given;
import io.restassured.response.Response;

import com.hurix.automation.utility.Log;

public class FetchbookDeviceIDs {
	
	public static Response fetchbookDeviceIDs(String clientUserID,int UserBookID,String consumerKey, String consumerSecret)
	{
		Response jsonResponse = null;
		try {
			
			Log.startTestCase("FetchbookDeviceIDs");		
		
			Log.info("URL : "+"/DistributionServices/ext/api/books/deviceIds/"+clientUserID+"?bookId="+UserBookID+"");
			Log.info("consumerKey : "+consumerKey);
			Log.info("consumerSecret : "+consumerSecret);
			Log.info("clientUserID : "+clientUserID);
			Log.info("UserBookID : "+UserBookID);
			jsonResponse = given()
					.auth()
					.oauth(consumerKey, consumerSecret, "", "")
					.header("Content-Type","application/json")
					//.formParam("bookId", UserBookID)
					.get("/DistributionServices/ext/api/books/deviceIds/"+clientUserID+"?bookId="+UserBookID+"");
										
			Log.info("FetchbookDeviceIDs Response: "+jsonResponse.then().extract().response().prettyPrint());
		} catch (Exception exp) 
		{
			Log.fail(exp.getMessage());
			Log.fail("fails due to"+ exp.getCause());
		}
		Log.endTestCase("End");
		return jsonResponse;
		}
}
