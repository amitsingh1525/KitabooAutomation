package com.hurix.api.readerAPIs;

import static io.restassured.RestAssured.given;
import io.restassured.response.Response;

import com.hurix.automation.utility.Log;

public class FetchBookClassInfo {
	
	public static Response fetchBookClassInfo(int bookID1,String userToken,String deviceID,String deviceType)
	{
		Response jsonResponse = null;
		try {
			Log.startTestCase("FetchBookClassInfo");
			Log.info("userToken :" +userToken);
			Log.info("FetchBookClassUserExpandedAnalytics_URL :" +"/DistributionServices/services/api/reader/distribution/"+deviceID+"/"+deviceType+"/"+bookID1+"/fetchBookClassInfo");
			jsonResponse = given()
					.header("usertoken", userToken)
					.get("/DistributionServices/services/api/reader/distribution/"+deviceID+"/"+deviceType+"/"+bookID1+"/fetchBookClassInfo");
			
			Log.info("FetchBookClassInfo Response: "+jsonResponse.then().extract().response().prettyPrint());
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
