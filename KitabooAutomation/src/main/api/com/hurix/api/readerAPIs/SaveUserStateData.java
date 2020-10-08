package com.hurix.api.readerAPIs;

import static io.restassured.RestAssured.given;
import io.restassured.response.Response;

import com.hurix.automation.utility.Log;

public class SaveUserStateData {
	
	private static String saveUserStateData;// = "{\"from\":0,\"size\":50,\"searchText\":\"Native\",\"searchOn\":\"both\",\"searchType\":\"partial\",\"searchField\":[\"author\",\"ISBN\",\"description\",\"bookTitle\",\"subject\",\"Series Title\",\"Interest Level\",\"Publisher\",\"Book content\",\"grade\",\"board\"],\"books\":[]}";

	public static Response saveUserStateData(int type,int bookID1,String classID,String userToken,String deviceID,String deviceType)
	{
		Response jsonResponse = null;
		try {			
			saveUserStateData = "{\"userStates\":[{\"bookID\":\""+bookID1+"\",\"classID\":\""+classID+"\","
					+ "\"type\":\""+type+"\",\"data\":\"0.1,#0000001\",\"status\":\"new\",\"folioID\":\"2\",\"pageID\":\"2\"}]}";
					
			Log.startTestCase("SaveUserStateData");
			Log.info("Body : "+saveUserStateData);
			Log.info("userToken : "+userToken);
			Log.info("URL : "+"/DistributionServices/services/api/reader/ugc/"+deviceID+"/"+deviceType+"/saveUserStateData");
			
			jsonResponse = given()
					.header("Content-Type","application/json")
					.header("usertoken",userToken)
					.body(saveUserStateData)					
					.post("/DistributionServices/services/api/reader/ugc/"+deviceID+"/"+deviceType+"/saveUserStateData");
			

			Log.info("SaveUserStateData Response: "+jsonResponse.then().extract().response().prettyPrint());
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
