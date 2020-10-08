package com.hurix.api.runner;

import static io.restassured.RestAssured.given;
import io.restassured.response.Response;
import com.hurix.automation.utility.Log;

public class ValidateAccessCode {
	
private static String validateAccessCode;
	
	public static Response validateAccessCode(String accessToken, String userToken,String deviceID,String deviceType)
	{
		//firstName+EpochTime.current()+""@yopmail.com"
		Response jsonResponse = null;
		try {			
			validateAccessCode= "{\"accessCode\":\""+accessToken+"\"}";					
					
			Log.startTestCase("ValidateAccessCode");
			Log.info("validateAccessCodeBody : "+validateAccessCode);
			
			Log.info("RegisterUserURL : "+"/DistributionServices/services/api/reader/license/"+deviceID+"/"+deviceType+"/validateAccessCode");
			
			jsonResponse = given()
					.header("Content-Type","application/json")
					//.header("usertoken",userToken)
					.body(validateAccessCode)					
					.post("/DistributionServices/services/api/reader/license/"+deviceID+"/"+deviceType+"/validateAccessCode");
					

			Log.info("ValidateAccessCode Response: "+jsonResponse.then().extract().response().prettyPrint());
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
