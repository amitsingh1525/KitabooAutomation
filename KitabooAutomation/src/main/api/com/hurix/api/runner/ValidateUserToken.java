package com.hurix.api.runner;

import static io.restassured.RestAssured.given;
import io.restassured.response.Response;

import com.hurix.automation.utility.Log;

public class ValidateUserToken {
	
	public static Response validateUserToken(String userToken,String deviceID,String deviceType)
	{
		Response jsonResponse = null;
		try {
			Log.startTestCase("ValidateUserToken");
			Log.info("userToken :" +userToken);
			
			jsonResponse = given()
					.get("/DistributionServices/services/api/reader/user/"+deviceID+"/"+deviceType+"/validateUserToken?usertoken="+userToken+"");
			
			Log.info("ValidateUserToken Response: "+jsonResponse.then().extract().response().prettyPrint());
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
