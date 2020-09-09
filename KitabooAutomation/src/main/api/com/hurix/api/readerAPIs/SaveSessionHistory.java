package com.hurix.api.readerAPIs;

import static io.restassured.RestAssured.given;
import io.restassured.response.Response;

import com.hurix.automation.utility.Log;



public class SaveSessionHistory {
	
	//public static String POSTsaveSessionHistoryPath=""+com.hurix.api.utility.ExcelUtils.getbaseURI()+"/DistributionServices/services/api/reader/distribution/123234234/PC/"+com.hurix.api.runner.RestAssured.bookID1+"/saveSessionHistory";

	private static String POSTsaveSessionHistoryBody = "{\"sessionHistory\":[{\"collection_book_id\":\""+com.hurix.api.runner.RestAssured.bookID1+"\",\"time\":\"2017-12-28 18:43:20\"}]}";

	public static Response saveSessionHistory(String userToken,String deviceID,String deviceType,int bookID1)
	{
		Response jsonResponse = null;
		try {
			
			Log.startTestCase("saveSessionHistory");
			//System.out.println("POSTsaveSessionHistoryRequestURL:" +POSTsaveSessionHistoryPath);
			System.out.println("POSTsaveSessionHistoryBody: "+POSTsaveSessionHistoryBody);
			jsonResponse = given()
					.header("Content-Type","application/json")
					.header("usertoken",userToken)
					.body(POSTsaveSessionHistoryBody)				
					.post("/DistributionServices/services/api/reader/distribution/"+deviceID+"/"+deviceType+"/"+bookID1+"/saveSessionHistory");
					
							
			Log.info("SaveSessionHistory Response: "+jsonResponse.then().extract().response().prettyPrint());
		} catch (Exception exp) 
		{
			System.out.println(exp.getMessage());
			System.out.println(exp.getCause());
			exp.printStackTrace();
		}
		Log.endTestCase("End");
		return jsonResponse;
		
	}
	
	
}
