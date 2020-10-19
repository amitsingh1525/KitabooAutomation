package com.hurix.api.runner;

import static io.restassured.RestAssured.given;
import io.restassured.response.Response;

import com.hurix.automation.utility.Log;

public class FetchBookClassUserExpandedAnalytics {
	
	public static Response fetchBookClassUserExpandedAnalytics(int bookID1,String classID,int userID,String userToken,String deviceID,String deviceType)
	{
		Response jsonResponse = null;
		try {
			Log.startTestCase("FetchBookClassUserExpandedAnalytics");
			Log.info("userToken :" +userToken);
			Log.info("FetchBookClassUserExpandedAnalytics_URL :" +"/DistributionServices/services/api/reader/analytics/"+deviceID+"/"+deviceType+"/"+bookID1+"/"+classID+"/"+userID+"/avgPagesReadPerSession/fetchBookClassUserExpandedAnalytics");
			jsonResponse = given()
					.header("usertoken", userToken)
					.get("/DistributionServices/services/api/reader/analytics/"+deviceID+"/"+deviceType+"/"+bookID1+"/"+classID+"/"+userID+"/avgPagesReadPerSession/fetchBookClassUserExpandedAnalytics");
			
			Log.info("FetchBookClassUserExpandedAnalytics Response: "+jsonResponse.then().extract().response().prettyPrint());
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
