package com.hurix.api.readerAPIs;

import static io.restassured.RestAssured.given;
import io.restassured.response.Response;

import com.hurix.automation.utility.Log;

public class SaveSessionHistory {
	
	//public static String POSTsaveSessionHistoryPath=""+com.hurix.api.utility.ExcelUtils.getbaseURI()+"/DistributionServices/services/api/reader/distribution/123234234/PC/"+com.hurix.api.runner.RestAssured.bookID1+"/saveSessionHistory";

	private static String POSTsaveSessionHistoryBody;
	public static Response saveSessionHistory(String userToken,String deviceID,String deviceType,int bookID1,String time)
	{
		Response jsonResponse = null;
		try {
			POSTsaveSessionHistoryBody = "{\"sessionHistory\":[{\"collection_book_id\":\""+bookID1+"\",\"time\":\""+time+"\"}]}";
			Log.startTestCase("saveSessionHistory");
			//System.out.println("POSTsaveSessionHistoryRequestURL:" +POSTsaveSessionHistoryPath);
			Log.info("POSTsaveSessionHistoryBody: "+POSTsaveSessionHistoryBody);
			jsonResponse = given()
					.header("Content-Type","application/json")
					.header("usertoken",userToken)
					.body(POSTsaveSessionHistoryBody)				
					.post("/DistributionServices/services/api/reader/distribution/"+deviceID+"/"+deviceType+"/"+bookID1+"/saveSessionHistory");
					
							
			Log.info("SaveSessionHistory Response: "+jsonResponse.then().extract().response().prettyPrint());
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
