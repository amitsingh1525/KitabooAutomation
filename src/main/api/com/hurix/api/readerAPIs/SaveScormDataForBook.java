package com.hurix.api.readerAPIs;

import static io.restassured.RestAssured.given;
import io.restassured.response.Response;
import com.hurix.automation.utility.Log;

public class SaveScormDataForBook {
	public static Response saveScormDataForBook(int bookID1,String classID,String userToken,String deviceID,String deviceType)
	{
		Response jsonResponse = null;
		try {			
			String saveScormDataForBookBody="{\"scormDataList\":[{\"scoId\":\"40\",\"cmi.core\":{\"cmi.core.lesson_status\":"
					+ "\"complete\",\"cmi.suspend_data\":\"[1,1,0,-1,-1,-1]\",\"cmi.core.score.raw\":33,\"cmi.core.session_time\":\"00:00:13\"},"
					+ "\"classID\":\""+classID+"\"}]}";
					
					
			Log.startTestCase("SaveScormDataForBook");
			Log.info("Body : "+saveScormDataForBookBody);
			Log.info("userToken : "+userToken);
			Log.info("URL : "+"/DistributionServices/services/api/reader/user/"+deviceID+"/"+deviceType+"/"+bookID1+"/saveScormDataForBook");
			
			jsonResponse = given()
					.header("Content-Type","application/json")
					.header("usertoken",userToken)
					.body(saveScormDataForBookBody)					
					.post("/DistributionServices/services/api/reader/user/"+deviceID+"/"+deviceType+"/"+bookID1+"/saveScormDataForBook");
			
			Log.info("SaveScormDataForBook Response: "+jsonResponse.then().extract().response().prettyPrint());
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
