package com.hurix.api.externalAPIs;

import static io.restassured.RestAssured.given;
import io.restassured.response.Response;
import com.hurix.automation.utility.Log;

public class UpdateKitabooUser {
	
	public static Response updateKitabooUser(String emailid,String clientUserID,String password,String consumerKey, String consumerSecret)
	{
		Response jsonResponse = null;
		try {
			String updateKitabooUserBODY ="{\"user\":{\"firstName\":\"Jmeter\",\"lastName\":\"Testing\",\"userName\":"
					+ "\""+emailid+"\",\"password\":\""+password+"\",\"clientUserID\":\""+clientUserID+"\","
					+ "\"email\":\""+emailid+"\",\"deleted\":0}}" ;
			Log.startTestCase("UpdateKitabooUser");		
		
			Log.info("URL : "+"/DistributionServices/ext/api/updateKitabooUser");
			Log.info("consumerKey : "+consumerKey);
			Log.info("consumerSecret : "+consumerSecret);
			Log.info("updateKitabooUserBODY : "+updateKitabooUserBODY);
			jsonResponse = given()
					.auth()
					.oauth(consumerKey, consumerSecret, "", "")
					.header("Content-Type","application/json")
					.body(updateKitabooUserBODY)
					.put("/DistributionServices/ext/api/updateKitabooUser");
										
			Log.info("UpdateKitabooUser Response: "+jsonResponse.then().extract().response().prettyPrint());
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
