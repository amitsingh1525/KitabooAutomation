package com.hurix.api.readerAPIs;

import static io.restassured.RestAssured.given;
import io.restassured.response.Response;

import com.hurix.automation.utility.Log;

public class SaveTrackingData {

	public static String savetrckingBody;
	public static Response saveTrackingData(int bookID1,String classID,String accessTime,String bookOpenTime,int pageID,int lastPage,int chapterID,String userToken,String deviceID,String deviceType)
	{Response jsonResponse = null;
	try {
		savetrckingBody="{\"trackings\":[{\"accessTimestamp\":\""+accessTime+"\",\"bookOpenTimestamp\":"
				+ "\""+accessTime+"\",\"bookID\":\""+bookID1+"\",\"pageTracking\":[{\"resources\":[],\"ugc\":"
				+ "[{\"type\":\"note\",\"created\":1,\"shared\":0,\"received\":0,\"deleted\":0,\""
				+ "shareDeleted\":0},{\"type\":\"important-highlight\",\"created\":1,\"shared\":0,\""
				+ "received\":0,\"deleted\":0,\"shareDeleted\":0},{\"type\":\"normal-highlight\","
				+ "\"created\":1,\"shared\":0,\"received\":0,\"deleted\":0,\"shareDeleted\":0}],\"totalNotesCreated\":1,"
				+ "\"totalImpHightlightCreated\":0,\"totalNormalHightlightCreated\":1,\"totalNotesShared\":0,"
				+ "\"totalNotesReceived\":0,\"totalHighlightsShared\":0,\"totalHighlightsReceived\":0,"
				+ "\"pageID\":\""+pageID+"\",\"timeSpent\":\"104\",\"chapterID\":"+chapterID+",\"chapterName\":\"Chapter1\"}],"
				+ "\"lastPageFolio\":\""+lastPage+"\",\"classID\":"+classID+",\"SuspendData\":\"0.1,#000000\"}]}";
		Log.startTestCase("SaveTrackingData");
		Log.info("savetrckingBody : "+savetrckingBody);
		jsonResponse = given()
				.header("usertoken",userToken)
				.header("Content-Type","application/json")
				.body(savetrckingBody)
				.post("DistributionServices/services/api/reader/analytics/"+deviceID+"/"+deviceType+"/saveTrackingData");
		
		Log.info("SaveTrackingData Response: "+jsonResponse.then().extract().response().prettyPrint());
	} catch (Exception exp) 
	{
		Log.fail(exp.getMessage());
		Log.fail("fails due to"+ exp.getCause());
		exp.printStackTrace();
	}
	Log.endTestCase("End");
	return jsonResponse;
	}
	
	public static Response saveTrackingDataV1(int bookID1,String classID,String accessTime,String bookOpenTime,int pageID,int lastPage,int chapterID,String userToken,String deviceID,String deviceType)
	{Response jsonResponse = null;
	try {
		savetrckingBody="{\"trackings\":[{\"accessTimestamp\":\""+accessTime+"\",\"bookOpenTimestamp\":"
				+ "\""+accessTime+"\",\"bookID\":\""+bookID1+"\",\"pageTracking\":[{\"resources\":[],\"ugc\":"
				+ "[{\"type\":\"note\",\"created\":1,\"shared\":0,\"received\":0,\"deleted\":0,\""
				+ "shareDeleted\":0},{\"type\":\"important-highlight\",\"created\":0,\"shared\":0,\""
				+ "received\":0,\"deleted\":0,\"shareDeleted\":0},{\"type\":\"normal-highlight\","
				+ "\"created\":1,\"shared\":0,\"received\":0,\"deleted\":0,\"shareDeleted\":0}],\"totalNotesCreated\":1,"
				+ "\"totalImpHightlightCreated\":0,\"totalNormalHightlightCreated\":1,\"totalNotesShared\":0,"
				+ "\"totalNotesReceived\":0,\"totalHighlightsShared\":0,\"totalHighlightsReceived\":0,"
				+ "\"pageID\":\""+pageID+"\",\"timeSpent\":\"104\",\"chapterID\":"+chapterID+",\"chapterName\":\"Front Matter\"}],"
				+ "\"lastPageFolio\":\""+lastPage+"\",\"SuspendData\":\"0.1,#000000\",\"classID\":"+classID+"}]}";
		Log.startTestCase("saveTrackingDataV1");
		Log.info("savetrckingBody : "+savetrckingBody);
		jsonResponse = given()
				.header("usertoken",userToken)
				.header("Content-Type","application/json")
				.body(savetrckingBody)
				.post("DistributionServices/services/api/reader/analytics/"+deviceID+"/"+deviceType+"/v1/saveTrackingData");
		
		Log.info("saveTrackingDataV1 Response: "+jsonResponse.then().extract().response().prettyPrint());
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
