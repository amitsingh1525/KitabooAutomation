package com.hurix.api.readerAPIs;

import static io.restassured.RestAssured.given;
import io.restassured.response.Response;

import com.hurix.automation.utility.Log;

public class Books {
	public static Response books(String userToken,String deviceID,String deviceType)
	{		
		Response jsonResponse = null;
		try {
			
			Log.startTestCase("Books");
			Log.info("userToken : "+userToken);
			Log.info("URL : "+"/DistributionServices/services/api/reader/elastic/"+deviceID+"/"+deviceType+"/books");
			
			jsonResponse = given()
					.header("usertoken",userToken)						
					.get("/DistributionServices/services/api/reader/elastic/"+deviceID+"/"+deviceType+"/books");
			
			Log.info("Books Response: "+jsonResponse.then().extract().response().prettyPrint());
		} catch (Exception exp) 
		{
			Log.fail(exp.getMessage());
			Log.fail("fails due to"+ exp.getCause());
		}
		Log.endTestCase("End");
		return jsonResponse;
	}

}
