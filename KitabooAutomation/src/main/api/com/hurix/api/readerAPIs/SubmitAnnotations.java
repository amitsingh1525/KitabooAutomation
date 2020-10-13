package com.hurix.api.readerAPIs;

import static io.restassured.RestAssured.given;
import io.restassured.response.Response;

import com.hurix.automation.utility.Log;

public class SubmitAnnotations {
	public static Response submitAnnotations(int bookID1,String userToken,String deviceID, String deviceType)
	{		
		Response jsonResponse = null;
		try {
			Log.startTestCase("SubmitAnnotations");
			jsonResponse = given()
					.header("usertoken",userToken)	
					.header("Content-Type","application/json")
					.get("/DistributionServices/services/api/reader/collab/"+deviceID+"/"+deviceType+"/"+bookID1+"/submitAnnotations");
			
			Log.info("SubmitAnnotations Response: "+jsonResponse.then().extract().response().prettyPrint());
		} catch (Exception exp) 
		{
			Log.fail(exp.getMessage());
			exp.printStackTrace();
		}
		Log.endTestCase("End");
		return jsonResponse;
	}

}
