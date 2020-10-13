package com.hurix.api.externalAPIs;

import static io.restassured.RestAssured.given;
import io.restassured.response.Response;
import com.hurix.automation.utility.Log;

public class ResetDevice{
	
	public static Response resetDevices_clientUserID(String consumerKey, String consumerSecret,String clientUserID)
	{
		Response jsonResponse = null;
		try {
			Log.startTestCase("resetDevices_clientUserID");
			System.out.println("ck="+consumerKey);
			jsonResponse = given()
					.auth()
					.oauth(consumerKey, consumerSecret, "", "")
					.post("/DistributionServices/ext/api/resetDevices?clientUserID="+clientUserID+"");
			/*Validation.responseHeaderCodeValidation(jsonResponse, 200);
			Validation.responseCodeValidation1(jsonResponse, 200);
			Validation.responseTimeValidation(jsonResponse);*/
			
			Log.info("ResetDevices_clientUserID Response: "+jsonResponse.then().extract().response().prettyPrint());
		} catch (Exception exp) 
		{
			Log.fail(exp.getMessage());
			Log.fail("fails due to"+ exp.getCause());
			//exp.printStackTrace();
		}
		Log.endTestCase("End");
		return jsonResponse;
		
	}
	public static Response resetDevices_userName(String consumerKey, String consumerSecret,String userName)
	{
		Response jsonResponse = null;
		try {
			Log.startTestCase("resetDevices_userName");
			System.out.println("ck="+consumerKey);
			jsonResponse = given()
					.auth()
					.oauth(consumerKey, consumerSecret, "", "")
					.post("/DistributionServices/ext/api/resetDevices?username="+userName+"");
			
			
			Log.info("resetDevices_userName Response: "+jsonResponse.then().extract().response().prettyPrint());
		} catch (Exception exp) 
		{
			Log.fail(exp.getMessage());
			Log.fail("fails due to"+ exp.getCause());
			//exp.printStackTrace();
		}
		Log.endTestCase("End");
		return jsonResponse;
		
	}
}
