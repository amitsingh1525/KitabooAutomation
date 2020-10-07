package com.hurix.api.readerAPIs;

import static io.restassured.RestAssured.given;
import io.restassured.response.Response;

import com.hurix.automation.utility.Log;

public class FetchCollabData {
	
	public static Response fetchCollabData(String version,int bookID1,String userToken,String deviceID,String deviceType)
	{		
		Response jsonResponse = null;
		try {
			
			Log.startTestCase("FetchCollabData");
			Log.info("userToken : "+userToken);
			Log.info("URL : "+"/DistributionServices/services/api/reader/collab/"+deviceID+"/"+deviceType+"/"+bookID1+"/fetchCollabData?bookVersion="+version+"");
			
			jsonResponse = given()
					.header("usertoken",userToken)						
					.get("/DistributionServices/services/api/reader/collab/"+deviceID+"/"+deviceType+"/"+bookID1+"/fetchCollabData?bookVersion="+version+"");
			
			Log.info("FetchCollabData Response: "+jsonResponse.then().extract().response().prettyPrint());
		} catch (Exception exp) 
		{
			Log.fail(exp.getMessage());
			Log.fail("fails due to"+ exp.getCause());
		}
		Log.endTestCase("End");
		return jsonResponse;
	}
	
	public static Response fetchCollabData_v3(String version,int bookID1,String userToken,String deviceID,String deviceType)
	{		
		Response jsonResponse = null;
		try {
			
			Log.startTestCase("FetchCollabData");
			Log.info("userToken : "+userToken);
			Log.info("URL : "+"/DistributionServices/services/api/reader/collab/"+deviceID+"/"+deviceType+"/"+bookID1+"/v3.9.4/fetchCollabData?bookVersion="+version+"");
			
			jsonResponse = given()
					.header("usertoken",userToken)						
					.get("DistributionServices/services/api/reader/collab/"+deviceID+"/"+deviceType+"/"+bookID1+"/v3.9.4/fetchCollabData?bookVersion="+version+"");
			
			Log.info("FetchCollabData Response: "+jsonResponse.then().extract().response().prettyPrint());
		} catch (Exception exp) 
		{
			Log.fail(exp.getMessage());
			Log.fail("fails due to"+ exp.getCause());
		}
		Log.endTestCase("End");
		return jsonResponse;
	}
}
