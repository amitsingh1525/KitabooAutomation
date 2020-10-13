package com.hurix.api.readerAPIs;

import static io.restassured.RestAssured.given;
import io.restassured.response.Response;

import com.hurix.automation.utility.Log;

public class SavePreferredLocale {
	
	public static Response savePreferredLocale(String userToken,String deviceID,String deviceType)
	{
		Response jsonResponse = null;
		try {			
			//savePreferredLocaleBody= "{\"locale\":\"en\"}";
					
			Log.startTestCase("SavePreferredLocale");
			Log.info("Body : "+"{\"locale\":\"en\"}");
			Log.info("userToken : "+userToken);
			Log.info("URL : "+"/DistributionServices/services/api/reader/user/"+deviceID+"/"+deviceType+"/savePreferredLocale");
			
			jsonResponse = given()
					.header("Content-Type","application/json")
					.header("usertoken",userToken)
					.body("{\"locale\":\"en\"}")				
					.post("/DistributionServices/services/api/reader/user/"+deviceID+"/"+deviceType+"/savePreferredLocale");
			
			Log.info("SavePreferredLocale Response: "+jsonResponse.then().extract().response().prettyPrint());
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
