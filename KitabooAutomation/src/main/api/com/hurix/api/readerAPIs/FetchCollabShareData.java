package com.hurix.api.readerAPIs;

import static io.restassured.RestAssured.given;
import io.restassured.response.Response;

import com.hurix.automation.utility.Log;

public class FetchCollabShareData {
	
	public static Response fetchCollabShareData(String userToken,String deviceID,String deviceType,int bookID1)
	{		
		Response jsonResponse = null;
		try {
			
			Log.startTestCase("FetchCollabShareData");
			jsonResponse = given()
					.header("usertoken",userToken)						
					.get("/DistributionServices/services/api/reader/user/"+deviceID+"/"+deviceType+"/"+bookID1+"/fetchCollabShareData");
			
			Log.info("FetchCollabShareData Response: "+jsonResponse.then().extract().response().prettyPrint());
		} catch (Exception exp) 
		{
			Log.fail(exp.getMessage());
			Log.fail("fails due to"+ exp.getCause());
		}
		Log.endTestCase("End");
		return jsonResponse;
	}
}
