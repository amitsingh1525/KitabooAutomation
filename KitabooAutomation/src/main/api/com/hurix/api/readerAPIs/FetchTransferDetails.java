	package com.hurix.api.readerAPIs;

import static io.restassured.RestAssured.given;
import io.restassured.response.Response;

import com.hurix.automation.utility.Log;

public class FetchTransferDetails {
	
	public static Response fetchTransferDetails(String userToken,String deviceID,String deviceType)
	{

		Response jsonResponse = null;
		try {
			Log.startTestCase("FetchTransferDetails");
			jsonResponse = given()
					.header("usertoken",userToken)									
					.get("/DistributionServices/services/api/reader/user/"+deviceID+"/"+deviceType+"/fetchTransferDetails");

			Log.info("FetchTransferDetails Response : "+jsonResponse.then().extract().response().prettyPrint());
			Log.info("FetchTransferDetails Response: "+jsonResponse.then().extract().response().xmlPath());
		} catch (Exception exp) 
		{
			Log.fail(exp.getMessage());
			Log.fail("fails due to"+ exp.getCause());
		
		}
		Log.endTestCase("End");
		return jsonResponse;
	}
}
