package com.hurix.api.readerAPIs;

import static io.restassured.RestAssured.given;
import io.restassured.response.Response;

import com.hurix.automation.utility.Log;

public class BookshelfStateData {
	
	public static Response bookshelfStateData(String userToken,String deviceID,String deviceType)
	{
		
		Response jsonResponse = null;
		try {
			
			Log.startTestCase("BookshelfStateData");
			jsonResponse = given()
					.header("usertoken",userToken)						
					.get("/DistributionServices/services/api/reader/distribution/"+deviceID+"/"+deviceType+"/bookshelfStateData");
			
			Log.info("BookshelfStateData Response: "+jsonResponse.then().extract().response().prettyPrint());
		} catch (Exception exp) 
		{
			Log.fail(exp.getMessage());
			Log.fail("fails due to"+ exp.getCause());
		}
		Log.endTestCase("End");
		return jsonResponse;
	}

}
