package com.hurix.api.readerAPIs;

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
					.formParam("usertoken", userToken)
					.get("/DistributionServices/services/api/reader/user/"+deviceID+"/"+deviceType+"/validateUserToken");
			
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
	
	public static Response validateUserTokenV1(String userToken,String deviceID,String deviceType)
	{
		Response jsonResponse = null;
		try {
			Log.startTestCase("validateUserTokenV1");
			Log.info("userToken :" +userToken);
			
			jsonResponse = given()
					.formParam("usertoken", userToken)
					.get("/DistributionServices/services/api/reader/user/"+deviceID+"/"+deviceType+"/v1/validateUserToken");
			
			Log.info("validateUserTokenV1 Response: "+jsonResponse.then().extract().response().prettyPrint());
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
