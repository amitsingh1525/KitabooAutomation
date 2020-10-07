package com.hurix.api.readerAPIs;

import static io.restassured.RestAssured.given;
import io.restassured.response.Response;

import com.hurix.automation.utility.Log;

public class ReleaseBookLicense {
	
	public static Response releaseBookLicense(String collectionID,String userToken,String deviceID,String deviceType)
	{		
		Response jsonResponse = null;
		try {
			
			Log.startTestCase("ReleaseBookLicense");
			Log.info("userToken : "+userToken);
			Log.info("URL : "+"/DistributionServices/services/api/reader/license/"+deviceID+"/"+deviceType+"/releaseBookLicense");
			Log.info("body : "+"{\"bookId\":"+collectionID+"}");
			jsonResponse = given()
					.header("usertoken",userToken)	
					.header("Content-Type","application/json")
					.body("{\"bookId\":"+collectionID+"}")
					.post("/DistributionServices/services/api/reader/license/"+deviceID+"/"+deviceType+"/releaseBookLicense");
			
			Log.info("ReleaseBookLicense Response: "+jsonResponse.then().extract().response().prettyPrint());
		} catch (Exception exp) 
		{
			Log.fail(exp.getMessage());
			Log.fail("fails due to"+ exp.getCause());
		}
		Log.endTestCase("End");
		return jsonResponse;
	}
}
