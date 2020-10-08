package com.hurix.api.externalAPIs;

import static io.restassured.RestAssured.given;
import io.restassured.response.Response;

import com.hurix.automation.utility.Log;

public class DownloadBook_ext {
	
	public static Response downloadBook_ext(String consumerKey, String consumerSecret,int bookID1,String deviceID,String deviceType)
	{
		Response jsonResponse = null;
		try {

			Log.startTestCase("DownloadBook_OAuth");
			Log.info("URL : "+"/DistributionServices/ext/api/"+deviceID+"/"+deviceType+"/"+bookID1+"/downloadBook");
			Log.info("consumerKey : "+consumerKey);
			Log.info("consumerSecret : "+consumerSecret);
			
			jsonResponse = given()
					.auth()
					.oauth(consumerKey, consumerSecret, "", "")
					.header("Content-Type","application/json")
					//.body(generatepinBody)					
					.get("/DistributionServices/ext/api/"+deviceID+"/"+deviceType+"/"+bookID1+"/downloadBook");

			Log.info("DownloadBook_OAuth Response: "+jsonResponse.then().extract().response().prettyPrint());
		} catch (Exception exp) 
		{
			Log.fail(exp.getMessage());
			exp.printStackTrace();
		}
		Log.endTestCase("End");
		return jsonResponse;
	}

}
