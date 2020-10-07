package com.hurix.api.readerAPIs;

import static io.restassured.RestAssured.given;
import io.restassured.response.Response;
import com.hurix.automation.utility.Log;

public class GeneratePin {
	
	public static Response generatePin(String usertoken,String deviceID,String deviceType)
	{
		Response jsonResponse = null;
		try {
			
			Log.info("usertoken : "+usertoken);
			Log.info("URL generete Pin: "+"/DistributionServices/services/api/reader/user/"+deviceID+"/"+deviceType+"/generatePin");
			jsonResponse = given()
				    .header("Content-Type","application/json")
					.header("usertoken", usertoken)					
					.post("/DistributionServices/services/api/reader/user/"+deviceID+"/"+deviceType+"/generatePin");
			Log.info("GeneratePin.device:"+deviceType+" Response: "+jsonResponse.then().extract().response().prettyPrint());
			
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
