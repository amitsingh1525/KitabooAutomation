package com.hurix.api.readerAPIs;

import static io.restassured.RestAssured.given;
import io.restassured.response.Response;

import com.hurix.automation.utility.Log;

public class SaveTrackingData {

	public static String savetrckingBody;
	public static Response saveTrackingData(int bookID1,int endIndex,String userToken,String DeviceID,String DeviceType)
	{
		Response jsonResponse = null;
		try {
			savetrckingBody="{\"trackings\":[{\"accessTimestamp\":\"2020-02-01 18:18:25\",\"bookOpenTimestamp\":\"2020-02-01 "
					+ "18:17:46\",\"bookID\":\""+bookID1+"\",\"pageTracking\":[{\"resources\":[],\"ugc\":[{\"type\":\"note\","
					+ "\"created\":1,\"shared\":1,\"received\":0,\"deleted\":0,\"shareDeleted\":0},{\"type\":\"important-highlight"
					+ "\",\"created\":0,\"shared\":0,\"received\":0,\"deleted\":0,\"shareDeleted\":0},{\"type\":\"normal-highlight\","
					+ "\"created\":0,\"shared\":0,\"received\":0,\"deleted\":0,\"shareDeleted\":0}],\"totalNotesCreated\":1,"
					+ "\"totalImpHightlightCreated\":0,\"totalNormalHightlightCreated\":0,\"totalNotesShared\":1,"
					+ "\"totalNotesReceived\":0,\"totalHighlightsShared\":0,\"totalHighlightsReceived\":0,\"pageID\":\"7\","
					+ "\"timeSpent\":\"10\",\"chapterID\":\"11\",\"chapterName\":\"Front Matter\"}],\"lastPageFolio\":\"7\","
					+ "\"classID\":23454323,\"SuspendData\":\"0.1,#000000\"}]}";
			Log.startTestCase("SaveTrackingData");
			jsonResponse = given()
					.header("usertoken",userToken)
					.post("DistributionServices/services/api/reader/analytics/"+DeviceID+"/"+DeviceType+"/saveTrackingData");
			/*Validation.responseCodeValidation1(jsonResponse, HttpStatus.SC_OK);
		Validation.responseHeaderCodeValidation(jsonResponse, HttpStatus.SC_OK);
		Validation.responseTimeValidation(jsonResponse);*/
			Log.info("SaveTrackingData Response: "+jsonResponse.then().extract().response().prettyPrint());
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
