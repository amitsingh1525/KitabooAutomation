package com.hurix.api.readerAPIs;

import static io.restassured.RestAssured.given;
import com.hurix.automation.utility.Log;
import io.restassured.response.Response;

public class MobileSession {
	
	public static Response mobileSession(String clientID, String keyClockSessionToken,String deviceID,String deviceType)
	{
		Response jsonResponse = null;
		Log.startTestCase("MobileSession");
		Log.info("URL : "+"/millsAndBoonAdapter/user/"+deviceID+"/"+deviceType+"/mobileSession");
		Log.info("clientID : "+clientID);
		Log.info("keyClockSessionToken : "+keyClockSessionToken);
		try {

			jsonResponse = given()
					.header("Content-Type","application/json")
					.queryParam("clientID", clientID)
					.header("keyClockSessionToken",keyClockSessionToken)					
					.post("/DistributionServices/services/api/reader/user/"+deviceID+"/"+deviceType+"/mobileSession");
			
			Log.info("MobileSession Response : "+jsonResponse.then().extract().response().prettyPrint());
		} catch (Exception exp) 
		{
			Log.fail(exp.getMessage());			
			Log.fail("Fails due to : "+exp.getCause());
		}
		Log.endTestCase("End");
		return jsonResponse;
	}
}

