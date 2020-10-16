package com.hurix.api.runner;

import static io.restassured.RestAssured.given;
import io.restassured.response.Response;

import com.hurix.automation.utility.Log;

public class FetchBookClassExpandedAnalytics {
	
	public static Response fetchBookClassExpandedAnalytics(int bookID1,String classID,String userToken,String deviceID,String deviceType)
	{
		Response jsonResponse = null;
		try {
			Log.startTestCase("FetchBookClassExpandedAnalytics");
			Log.info("userToken :" +userToken);
			Log.info("classID :" +classID);
			Log.info("URL :" +"/DistributionServices/services/api/reader/analytics/"+deviceID+"/"+deviceType+"/"+bookID1+"/"+classID+"/bookOpen/fetchBookClassExpandedAnalytics");
			jsonResponse = given()
					.header("usertoken", userToken)
					.get("/DistributionServices/services/api/reader/analytics/"+deviceID+"/"+deviceType+"/"+bookID1+"/"+classID+"/bookOpen/fetchBookClassExpandedAnalytics");
			
			Log.info("FetchBookClassExpandedAnalytics Response: "+jsonResponse.then().extract().response().prettyPrint());
		} catch (Exception exp) 
		{
			Log.fail(exp.getMessage());
			Log.fail("fails due to"+ exp.getCause());
			exp.printStackTrace();
		}
		Log.endTestCase("End");
		return jsonResponse;
	}
}

