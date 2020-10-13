package com.hurix.api.externalAPIs;

import static io.restassured.RestAssured.given;
import io.restassured.response.Response;
import com.hurix.automation.utility.Log;

public class UpdateClientUserID {
	
	public static Response updateClientUserID(String clientUserID,String targetID,String consumerKey, String consumerSecret)
	{		
		String updateClientUserIDBody =	"{\"user\":{\"sourceID\":\""+clientUserID+"\",\"targetID\":\""+targetID+"\"}}";
		Response jsonResponse = null;
		try {
			Log.startTestCase("UpdateClientUserID");
			Log.info("clientUserID : "+clientUserID);
			Log.info("updateClientUserIDBody : "+updateClientUserIDBody);
			jsonResponse = given()
					.auth()
					.oauth(consumerKey, consumerSecret, "", "")
					.header("Content-Type","application/json")
					.body(updateClientUserIDBody)
					.put("/DistributionServices/ext/api/updateClientUserID");

			Log.info("UpdateClientUserID Response: "+jsonResponse.then().extract().response().prettyPrint());
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
