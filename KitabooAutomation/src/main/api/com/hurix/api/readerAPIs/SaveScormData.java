package com.hurix.api.readerAPIs;

import static io.restassured.RestAssured.given;
import io.restassured.response.Response;

import com.hurix.api.utility.EpochTime;
import com.hurix.automation.utility.Log;

public class SaveScormData {
	
	public static Response saveScormData(String scoid,int bookID1,String userToken,String deviceID,String deviceType)
	{
		Response jsonResponse = null;
		try {			
			String saveScormDataBody="{\"cmi.core\":{\"cmi.core.lesson_status\":\"COMPLETED\",\"cmi.core.lesson_location\":"
					+ "\"12\",\"cmi.suspend_data\":\"data\",\"cmi.core.score\":\"1.0\",\"cmi.core.session_time\":\"765\","
					+ "\"cmi.attempt\":\"2\",\"cmi.completion_status\":\"1\",\"cmi.success_status\":\"0\",\"cmi.score.scaled\":"
					+ "\"1\",\"cmi.session_time\":\"233\",\"cmi.location\":\"200\",\"cmi.exit\":\"0\",\"cmi.entry\":\"122\","
					+ "\"cmi.mode\":\"12\",\"cmi.credit\":\"12\",\"Attempt\":\"2\",\"Score\":\"100\",\"Time\":\"10002\"},"
					+ "\"classID\":\""+EpochTime.current()+"\"}";
					
					
			Log.startTestCase("SaveScormData");
			Log.info("Body : "+saveScormDataBody);
			Log.info("userToken : "+userToken);
			Log.info("URL : "+"/DistributionServices/services/api/reader/user/"+deviceID+"/"+deviceType+"/"+bookID1+"/"+scoid+"/saveScormData");
			
			jsonResponse = given()
					.header("Content-Type","application/json")
					.header("usertoken",userToken)
					.body(saveScormDataBody)					
					.post("/DistributionServices/services/api/reader/user/"+deviceID+"/"+deviceType+"/"+bookID1+"/"+scoid+"/saveScormData");
			
			Log.info("saveScormData Response: "+jsonResponse.then().extract().response().prettyPrint());
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
