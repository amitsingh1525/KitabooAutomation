package com.hurix.api.readerAPIs;

import static io.restassured.RestAssured.given;
import io.restassured.response.Response;

import com.hurix.automation.utility.Log;

public class SaveSessionHistoryForMutipleBooks {
	private static String saveSessionHistoryForMutipleBooksBody;
	public static Response saveSessionHistoryForMutipleBooks(int bookID1,int bookID2,String time,String userToken,String deviceID,String deviceType)
	{
		Response jsonResponse = null;
		try {//2020-08-07 08:43:20
			saveSessionHistoryForMutipleBooksBody = "{\"sessionHistory\":[{\"collection_book_id\":\""+bookID1+"\","
					+ "\"time\":\"2020-08-07 08:43:20\"},{\"collection_book_id\":\""+bookID2+"\",\"time\":"
					+ "\"2020-08-07 04:43:20\"}]}";
			//System.out.println("POSTsaveSessionHistoryRequestURL:" +POSTsaveSessionHistoryPath);
			Log.startTestCase("SaveSessionHistoryForMutipleBooks");
			Log.info("saveSessionHistoryForMutipleBooksBody: "+saveSessionHistoryForMutipleBooksBody);
			jsonResponse = given()
					.header("Content-Type","application/json")
					.header("usertoken",userToken)
					.body(saveSessionHistoryForMutipleBooksBody)					
					.post("/DistributionServices/services/api/reader/distribution/"+deviceID+"/"+deviceType+"/saveSessionHistoryForMutipleBooks");
					
							
			Log.info("SaveSessionHistoryForMutipleBooks Response: "+jsonResponse.then().extract().response().prettyPrint());
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
