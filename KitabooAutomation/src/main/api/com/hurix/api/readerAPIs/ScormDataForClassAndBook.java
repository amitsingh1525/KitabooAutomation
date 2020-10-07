package com.hurix.api.readerAPIs;

import static io.restassured.RestAssured.given;
import io.restassured.response.Response;

import com.hurix.automation.utility.Log;

public class ScormDataForClassAndBook {
	public static Response scormDataForClassAndBook(int bookID1,String classID,String userToken,String deviceID, String deviceType)
	{		
		Response jsonResponse = null;
		try {
			Log.startTestCase("ScormDataForClassAndBook");
			jsonResponse = given()
					.header("usertoken",userToken)	
					.header("Content-Type","application/json")
					.get("/DistributionServices/services/api/reader/user/"+deviceID+"/"+deviceType+"/"+bookID1+"/"+classID+"/scormDataForClassAndBook");
			
			Log.info("ScormDataForClassAndBook Response: "+jsonResponse.then().extract().response().prettyPrint());
		} catch (Exception exp) 
		{
			Log.fail(exp.getMessage());
			exp.printStackTrace();
		}
		Log.endTestCase("End");
		return jsonResponse;
	}	
}
