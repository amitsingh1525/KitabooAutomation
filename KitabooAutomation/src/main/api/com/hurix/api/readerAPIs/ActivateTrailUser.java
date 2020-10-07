package com.hurix.api.readerAPIs;

import static io.restassured.RestAssured.given;
import io.restassured.response.Response;

import com.hurix.automation.utility.Log;

public class ActivateTrailUser {
	
	public static Response activateTrailUser(String userToken,String deviceID,String deviceType,String clientUserID,String clientID)
	{
		Response jsonResponse = null;
		try {
			Log.startTestCase("ActivateTrailUser");
			jsonResponse = given()
					.header("usertoken",userToken)
					.formParam("clientUserId", clientUserID)
					.formParam("readerKey", clientID)
					.get("/DistributionServices/services/api/reader/user/"+deviceID+"/"+deviceType+"/activateTrailUser");

			Log.info("ActivateTrailUser Response : "+jsonResponse.then().extract().response().prettyPrint());
			
		} catch (Exception exp) 
		{
			Log.fail(exp.getMessage());
			Log.fail("fails due to"+ exp.getCause());		
		}
		Log.endTestCase("End");
		return jsonResponse;
	}

}
