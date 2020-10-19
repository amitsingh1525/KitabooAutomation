package com.hurix.api.readerAPIs;

import static io.restassured.RestAssured.given;
import io.restassured.response.Response;

import com.hurix.automation.utility.Log;

public class ReleaseDevice {
	
	public static Response releaseDevice(String userToken,String deviceID,String deviceType,int bookID1)
	{
		
		Response jsonResponse = null;
		try {
			
			Log.startTestCase("ReleaseDevice");
			jsonResponse = given()
					.header("usertoken",userToken)						
					.get("/DistributionServices/services/api/reader/license/"+deviceID+"/"+deviceType+"/"+bookID1+"/releaseDevice");
			
			Log.info("ReleaseDevice Response: "+jsonResponse.then().extract().response().prettyPrint());
		} catch (Exception exp) 
		{
			Log.fail(exp.getMessage());
			Log.fail("fails due to"+ exp.getCause());
		}
		Log.endTestCase("End");
		return jsonResponse;
	}
}
