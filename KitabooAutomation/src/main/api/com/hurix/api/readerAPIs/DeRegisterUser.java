package com.hurix.api.readerAPIs;

import static io.restassured.RestAssured.given;
import io.restassured.response.Response;
import com.hurix.automation.utility.Log;

public class DeRegisterUser {
	
	public static Response deRegisterUser(String userToken,String deviceID,String deviceType)
	{
		Response jsonResponse = null;
		try {
			Log.startTestCase("De-RegisterUser");
			
			Log.info("URL : "+"/DistributionServices/services/api/reader/license/"+deviceID+"/"+deviceType+"/deregister");
			Log.info("userToken : "+userToken);
					
			jsonResponse = given()
					.header("usertoken",userToken)						
					.get("/DistributionServices/services/api/reader/license/"+deviceID+"/"+deviceType+"/deregister");
			
			Log.info("De-RegisterUser Response: "+jsonResponse.then().extract().response().prettyPrint());
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

