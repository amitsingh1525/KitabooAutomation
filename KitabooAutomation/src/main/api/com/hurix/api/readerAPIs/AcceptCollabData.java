package com.hurix.api.readerAPIs;

import static io.restassured.RestAssured.given;
import io.restassured.response.Response;

import com.hurix.automation.utility.Log;

public class AcceptCollabData {
	private static String acceptCollabDataBody;// = "{\"from\":0,\"size\":50,\"searchText\":\"Native\",\"searchOn\":\"both\",\"searchType\":\"partial\",\"searchField\":[\"author\",\"ISBN\",\"description\",\"bookTitle\",\"subject\",\"Series Title\",\"Interest Level\",\"Publisher\",\"Book content\",\"grade\",\"board\"],\"books\":[]}";

	public static Response acceptCollabData(int UGCId,String userToken,String deviceID,String deviceType)
	{
		Response jsonResponse = null;
		try {			
			acceptCollabDataBody= "{\"acceptUgcList\":[{\"status\":1,\"ugcID\":\""+UGCId+"\"}]}";
					
			Log.startTestCase("AcceptCollabData");
			Log.info("Body : "+acceptCollabDataBody);
			Log.info("userToken : "+userToken);
			Log.info("URL : "+"/DistributionServices/services/api/reader/collab/"+deviceID+"/"+deviceType+"/acceptCollabData");
			
			jsonResponse = given()
					.header("Content-Type","application/json")
					.header("usertoken",userToken)
					.body(acceptCollabDataBody)					
					.post("/DistributionServices/services/api/reader/collab/"+deviceID+"/"+deviceType+"/acceptCollabData");
			
			Log.info("AcceptCollabData Response: "+jsonResponse.then().extract().response().prettyPrint());
		} catch (Exception exp) 
		{
			Log.fail(exp.getMessage());
			Log.fail("fails due to"+ exp.getCause());
			//exp.printStackTrace();
		}
		Log.endTestCase("End");
		return jsonResponse;
	}	
}
