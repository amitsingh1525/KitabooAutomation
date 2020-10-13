package com.hurix.api.externalAPIs;

import static io.restassured.RestAssured.given;
import io.restassured.response.Response;

import com.hurix.automation.utility.Log;

public class ChangePassword {
	public static Response changePassword(String userName,String password,String Newpassword,String consumerKey, String consumerSecret)
	{		
		String uploadEpubBody = "{\"user\":{\"userName\":\""+userName+"\",\"password\":\""+password+"\"},"
				+ "\"newPassword\":\""+Newpassword+"\"}";
				
		Response jsonResponse = null;
		try {
			Log.startTestCase("UpdateBookMetadata");
			Log.info("userName : "+userName);
			Log.info("password : "+password);
			Log.info("uploadEpubBody : "+uploadEpubBody);
			
			jsonResponse = given()
					.auth()
					.oauth(consumerKey, consumerSecret, "", "")
					.header("Content-Type","application/json")
					.body(uploadEpubBody)
					.post("/DistributionServices/ext/api/changePassword");

			Log.info("UpdateBookMetadata Response: "+jsonResponse.then().extract().response().prettyPrint());
		} catch (Exception exp) 
		{
			Log.fail(exp.getMessage());
			exp.printStackTrace();
		}
		Log.endTestCase("End");
		return jsonResponse;		
	}
}

