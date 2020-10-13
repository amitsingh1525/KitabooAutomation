package com.hurix.api.readerAPIs;

import static io.restassured.RestAssured.given;
import io.restassured.response.Response;

import com.hurix.automation.utility.Log;

public class FetchMarkupSetting {
	public static Response fetchMarkupSetting(String userToken,String deviceID, String deviceType)
	{		
		Response jsonResponse = null;
		try {
			Log.startTestCase("FetchMarkupSetting");
			jsonResponse = given()
					.header("usertoken",userToken)						
					.get("/DistributionServices/services/api/reader/user/"+deviceID+"/"+deviceType+"/fetchMarkupSetting");
			
			Log.info("FetchMarkupSetting  Response: "+jsonResponse.then().extract().response().prettyPrint());
		} catch (Exception exp) 
		{
			Log.fail(exp.getMessage());
			exp.printStackTrace();
		}
		Log.endTestCase("End");
		return jsonResponse;
	}	
}
