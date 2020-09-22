package com.hurix.api.readerAPIs;

import static io.restassured.RestAssured.given;
import io.restassured.response.Response;

import com.hurix.automation.utility.Log;

public class ValidatePin {
	public static Response validatePin(String clientID,String pinKey,String pinPair, String usertoken,String deviceID,String deviceType)
	{
		Response jsonResponse = null;
		try {
			Log.startTestCase("ValidatePin.device:"+deviceType+"");
			jsonResponse = given()
					.header("Content-Type","application/json")
					.queryParam("usertoken", usertoken)	
					.body("{\"pinKey\":\""+pinKey+"\",\"pinPair\":\""+pinPair+"\"}")
					.post("/DistributionServices/services/api/reader/user/15331/IPAD/validatePin?clientID="+clientID+"");
			Log.info("ValidatePin.device:"+deviceType+" Response: "+jsonResponse.then().extract().response().prettyPrint());

		} catch (Exception exp)
		{
			System.out.println(exp.getMessage());
			System.out.println(exp.getCause());

			exp.printStackTrace();
		}
		Log.endTestCase("End");
		return jsonResponse;
	}

}
