package com.hurix.api.readerAPIs;

import static io.restassured.RestAssured.given;
import io.restassured.response.Response;

import com.hurix.automation.utility.Log;

public class FetchUserSetting {
	
	public static Response fetchUserSetting(String userToken,int userID,String deviceType)
	{
		Response jsonResponse = null;
		try {
			Log.startTestCase("FetchUserSetting");
			jsonResponse = given()
					.header("usertoken",userToken)
					.get("/DistributionServices/services/api/reader/user/"+userID+"/"+deviceType+"/fetchUserSetting");

			Log.info("FetchUserSetting Response : "+jsonResponse.then().extract().response().prettyPrint());
			
		} catch (Exception exp) 
		{
			Log.fail(exp.getMessage());
			Log.fail("fails due to"+ exp.getCause());		
		}
		Log.endTestCase("End");
		return jsonResponse;
	}
}
