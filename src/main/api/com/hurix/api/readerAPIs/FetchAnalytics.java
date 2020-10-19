package com.hurix.api.readerAPIs;

import static io.restassured.RestAssured.given;
import io.restassured.response.Response;

import com.hurix.automation.utility.Log;

public class FetchAnalytics {
	
	public static Response fetchAnalytics(int bookID1,String classID,String userToken,String deviceID,String deviceType)
	{		
		Response jsonResponse = null;
		try {
			
			Log.startTestCase("FetchAnalytics");
			Log.info("userToken : "+userToken);
			Log.info("URL : "+"/DistributionServices/services/api/reader/analytics/"+deviceID+"/"+deviceType+"/"+bookID1+"/"+classID+"/fetchAnalytics");
			
			jsonResponse = given()
					.header("usertoken",userToken)						
					.get("/DistributionServices/services/api/reader/analytics/"+deviceID+"/"+deviceType+"/"+bookID1+"/"+classID+"/fetchAnalytics");
			
			Log.info("FetchAnalytics Response: "+jsonResponse.then().extract().response().prettyPrint());
		} catch (Exception exp) 
		{
			Log.fail(exp.getMessage());
			Log.fail("fails due to"+ exp.getCause());
		}
		Log.endTestCase("End");
		return jsonResponse;
	}

}
