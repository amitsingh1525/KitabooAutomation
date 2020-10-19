package com.hurix.api.readerAPIs;

import static io.restassured.RestAssured.given;
import io.restassured.response.Response;

import com.hurix.automation.utility.Log;

public class SaveTeacherAnnotations {
	
	public static String saveTeacherAnnotationsBODY;
	public static Response saveTeacherAnnotations(int bookID1,int userID,String userToken,String deviceID,String deviceType)
	{Response jsonResponse = null;
	try {
		saveTeacherAnnotationsBODY= "{\"bookVersion\":\"1.0\",\"ugcList\":[{\"id\":0,\"localId\":\"1494926168494\",\"pageId\":7,"
				+ "\"folioID\":\"5\",\"type\":3,\"ugcData\":\"%7B%7D\",\"metadata\":\"%7B%22ChapterName%22%3A%22Chapter%201.%20Moving%20Energy%22%2C%22HighlightedText%22%3A%22You%20need%20%22%2C%22StartWordIndex%22%3A%2270001%22%2C%22EndWordIndex%22%3A%2270002%22%2C%22IsImportant%22%3A1%2C%22createdOn%22%3A%222017-05-16%2009%3A16%3A08%22%7D\","
				+ "\"createdOn\":\"2017-05-16 14:46:08\",\"status\":\"new\"}]}";
		Log.startTestCase("SaveTeacherAnnotations");
		Log.info("saveTeacherAnnotationsBODYBody : "+saveTeacherAnnotationsBODY);
		Log.info("URL : "+"/DistributionServices/services/api/reader/collab/"+deviceID+"/"+deviceType+"/"+bookID1+"/"+userID+"/saveTeacherAnnotations");
		Log.info("userToken : "+userToken);
		jsonResponse = given()
				.header("usertoken",userToken)
				.header("Content-Type","application/json")
				.body(saveTeacherAnnotationsBODY)
				.post("/DistributionServices/services/api/reader/collab/"+deviceID+"/"+deviceType+"/"+bookID1+"/"+userID+"/saveTeacherAnnotations");
		
		Log.info("SaveTeacherAnnotations Response: "+jsonResponse.then().extract().response().prettyPrint());
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
