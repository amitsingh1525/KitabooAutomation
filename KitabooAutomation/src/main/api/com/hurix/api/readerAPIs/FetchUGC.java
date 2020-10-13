package com.hurix.api.readerAPIs;

import static io.restassured.RestAssured.given;
import io.restassured.response.Response;

import com.hurix.automation.utility.Log;

public class FetchUGC {
	
	public static Response fetchUGC(int bookID1,String userToken,String deviceID,String deviceType)
	{
		Response jsonResponse = null;
		try {
			Log.startTestCase("FetchUGC");
			jsonResponse = given()
					.header("usertoken",userToken)
					.get("/DistributionServices/services/api/reader/ugc/"+deviceID+"/"+deviceType+"/"+bookID1+"/0/fetchUGC");

			Log.info("FetchUGC Response : "+jsonResponse.then().extract().response().prettyPrint());
			
		} catch (Exception exp) 
		{
			Log.fail(exp.getMessage());
			Log.fail("fails due to"+ exp.getCause());		
		}
		Log.endTestCase("End");
		return jsonResponse;
	}
}
