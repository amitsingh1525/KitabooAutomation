package com.hurix.api.readerAPIs;

import static io.restassured.RestAssured.given;
import io.restassured.response.Response;

import com.hurix.automation.utility.Log;

public class SaveCollabData {
	
	private static String saveCollabDataBody;// = "{\"from\":0,\"size\":50,\"searchText\":\"Native\",\"searchOn\":\"both\",\"searchType\":\"partial\",\"searchField\":[\"author\",\"ISBN\",\"description\",\"bookTitle\",\"subject\",\"Series Title\",\"Interest Level\",\"Publisher\",\"Book content\",\"grade\",\"board\"],\"books\":[]}";

	public static Response saveCollabData(int UGCId,int bookID1,int userID,String userToken,String deviceID,String deviceType)
	{
		Response jsonResponse = null;
		try {			
			saveCollabDataBody= "{\"collabData\":[{\"ugcID\":"+UGCId+",\"receivers\":[{\"id\":"+userID+"}]}]}";
					
			Log.startTestCase("SaveCollabData");
			Log.info("Body : "+saveCollabDataBody);
			Log.info("userToken : "+userToken);
			Log.info("URL : "+"/DistributionServices/services/api/reader/collab/"+deviceID+"/"+deviceType+"/"+bookID1+"/saveCollabData");
			
			jsonResponse = given()
					.header("Content-Type","application/json")
					.header("usertoken",userToken)
					.body(saveCollabDataBody)				
					.post("/DistributionServices/services/api/reader/collab/"+deviceID+"/"+deviceType+"/"+bookID1+"/saveCollabData");
			
			Log.info("SaveCollabData Response: "+jsonResponse.then().extract().response().prettyPrint());
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
