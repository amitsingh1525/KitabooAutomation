package com.hurix.api.readerAPIs;

import static io.restassured.RestAssured.given;
import io.restassured.response.Response;
import com.hurix.automation.utility.Log;

public class Logout {
	
	public static Response logout(String userToken,String deviceID,String deviceType)
	{
		Response jsonResponse = null;
		try {
			Log.startTestCase("Logout");
			Log.info("userToken : "+userToken);
			jsonResponse = given()
					.header("Content-Type","application/json")
					.header("usertoken",userToken)								
					.get("/DistributionServices/services/api/reader/user/"+deviceID+"/"+deviceType+"/logout");
										
			Log.info("Logout Response: "+jsonResponse.then().extract().response().prettyPrint());
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
