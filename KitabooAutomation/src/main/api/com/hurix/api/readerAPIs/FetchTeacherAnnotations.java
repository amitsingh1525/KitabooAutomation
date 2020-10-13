package com.hurix.api.readerAPIs;

import static io.restassured.RestAssured.given;
import io.restassured.response.Response;

import com.hurix.automation.utility.Log;

public class FetchTeacherAnnotations {
	public static Response fetchTeacherAnnotations(int bookID1,int userID,String version,String userToken,String deviceID, String deviceType)
	{		
		Response jsonResponse = null;
		try {
			Log.startTestCase("FetchTeacherAnnotations");
			jsonResponse = given()
					.header("usertoken",userToken)	
					.header("Content-Type","application/json")
					.get("/DistributionServices/services/api/reader/collab/"+deviceID+"/"+deviceType+"/"+bookID1+"/fetchTeacherAnnotations?bookVersion="+version+"");
			
			Log.info("FetchTeacherAnnotations Response: "+jsonResponse.then().extract().response().prettyPrint());
		} catch (Exception exp) 
		{
			Log.fail(exp.getMessage());
			exp.printStackTrace();
		}
		Log.endTestCase("End");
		return jsonResponse;
	}

}
