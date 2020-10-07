package com.hurix.api.readerAPIs;

import static io.restassured.RestAssured.given;
import io.restassured.response.Response;

import com.hurix.automation.utility.Log;

public class ValidatePin {
	public static Response validatePin(String clientID,String pinKey,String pinPair,String deviceID,String deviceType)
	{
		Response jsonResponse = null;
		try {
			Log.startTestCase("ValidatePin.device:"+deviceType+"");
			
			Log.info("BODY VALIDATEPIN : "+"{\"pinKey\":\""+pinKey+"\",\"pinPair\":\""+pinPair+"\"}");
			Log.info("URL : "+"/DistributionServices/services/api/reader/user/"+deviceID+"/"+deviceType+"/validatePin?clientID="+clientID+"");
			Log.info("deviceType : "+deviceType);
			Log.info("clientID : "+clientID);
			jsonResponse = given()
					.header("Content-Type","application/json")
					//.queryParam("usertoken", usertoken)	
					.body("{\"pinKey\":\""+pinKey+"\",\"pinPair\":\""+pinPair+"\"}")
					
					.post("/DistributionServices/services/api/reader/user/"+deviceID+"/"+deviceType+"/validatePin?clientID="+clientID+"");
			Log.info("ValidatePin.device:"+deviceType+" Response: "+jsonResponse.then().extract().response().prettyPrint());

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
