package com.hurix.api.readerAPIs;

import static io.restassured.RestAssured.given;
import io.restassured.response.Response;

import com.hurix.automation.utility.Log;

public class ReleaseCollection {
	public static Response releaseCollection(int kitabooCollectionId,String userToken,String deviceID,String deviceType)
	{		
		Response jsonResponse = null;
		try {
			Log.startTestCase("ReleaseCollection");
			
			jsonResponse = given()
					.header("userToken",userToken)
					.header("Content-Type","application/json")
					.get("/DistributionServices/services/api/reader/license/"+deviceID+"/"+deviceType+"/"+kitabooCollectionId+"/releaseCollection");
		
			
			Log.info("ReleaseCollection Response: "+jsonResponse.then().extract().response().prettyPrint());
		} catch (Exception exp) 
		{
			Log.fail(exp.getMessage());
			Log.fail("fails due to"+ exp.getCause());
		}
		Log.endTestCase("End");
		return jsonResponse;		
	}
}
