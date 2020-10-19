package com.hurix.api.readerAPIs;

import static io.restassured.RestAssured.given;
import io.restassured.response.Response;

import com.hurix.automation.utility.Log;

public class SaveHighlightSetting {
	
	public static Response saveHighlightSetting(int bookID1,int userID,String userToken,String deviceID,String deviceType)
	{
		Response jsonResponse = null;
		try {
			String saveSessionHistoryForMutipleBooksBody = "{\"recieveList\":["+userID+"],\"shareList\":["+userID+"]}";
			//System.out.println("POSTsaveSessionHistoryRequestURL:" +POSTsaveSessionHistoryPath);
			Log.startTestCase("SaveHighlightSetting");
			Log.info("SaveHighlightSettingBody: "+saveSessionHistoryForMutipleBooksBody);
			Log.info("URL: "+"/DistributionServices/services/api/reader/user/"+deviceID+"/"+deviceType+"/"+bookID1+"/saveHighlightSetting");
			jsonResponse = given()
					.header("Content-Type","application/json")
					.header("usertoken",userToken)
					.body(saveSessionHistoryForMutipleBooksBody)					
					.post("/DistributionServices/services/api/reader/user/"+deviceID+"/"+deviceType+"/"+bookID1+"/saveHighlightSetting");
					
							
			Log.info("SaveHighlightSetting Response: "+jsonResponse.then().extract().response().prettyPrint());
		} catch (Exception exp) 
		{
			Log.fail(exp.getMessage());
			Log.fail("fails due to"+ exp.getCause());
			exp.printStackTrace();
		}
		Log.endTestCase("End");
		return jsonResponse;		
	}
}
