package com.hurix.api.externalAPIs;

import static io.restassured.RestAssured.given;
import io.restassured.response.Response;

import com.hurix.automation.utility.Log;

public class UpdateNotification {

	public static Response updateNotification(int userID, int userDeleted,String consumerKey, String consumerSecret)
	{
		Response jsonResponse = null;
		try {
			
			Log.startTestCase("UpdateNotification");		
		
			Log.info("URL : "+"/DistributionServices/ext/api/UpdateNotification?userID="+userID+"&userDeleted="+userDeleted+"&Content-Type=application/x-www-form-urlencoded");
			Log.info("consumerKey : "+consumerKey);
			Log.info("consumerSecret : "+consumerSecret);
			
			jsonResponse = given()
					.auth()
					.oauth(consumerKey, consumerSecret, "", "")
					.queryParam("Content-Type","application/x-www-form-urlencoded")
					.queryParam("userID",userID)	
					.queryParam("userDeleted",userDeleted)	
					.post("/DistributionServices/ext/api/UpdateNotification");
										
			Log.info("UpdateNotification Response: "+jsonResponse.then().extract().response().prettyPrint());
		} catch (Exception exp) 
		{
			Log.fail(exp.getMessage());
			exp.printStackTrace();
		}
		Log.endTestCase("End");
		return jsonResponse;
	}
}
