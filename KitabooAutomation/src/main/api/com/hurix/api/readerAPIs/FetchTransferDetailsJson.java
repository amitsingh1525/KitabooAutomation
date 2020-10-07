package com.hurix.api.readerAPIs;

import static io.restassured.RestAssured.given;
import io.restassured.response.Response;

import com.hurix.automation.utility.Log;

public class FetchTransferDetailsJson {
	
	public static Response fetchTransferDetailsJson(String userToken,String deviceID,String deviceType,String true1)
	{
		Response jsonResponse = null;
		try {
			Log.startTestCase("FetchTransferDetailsJson");
			jsonResponse = given()
					.formParam("usertoken", userToken)
					.formParam("ka", true1)
					.get("/DistributionServices/services/api/reader/user/"+deviceID+"/"+deviceType+"/fetchTransferDetailsJson");

			Log.info("FetchTransferDetailsJson Response : "+jsonResponse.then().extract().response().prettyPrint());
			Log.info("FetchTransferDetailsJson Response: "+jsonResponse.then().extract().response().xmlPath());
		} catch (Exception exp) 
		{
			Log.fail(exp.getMessage());
			Log.fail("fails due to"+ exp.getCause());		
		}
		Log.endTestCase("End");
		return jsonResponse;
	}
}
