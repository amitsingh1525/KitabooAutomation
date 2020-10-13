package com.hurix.api.externalAPIs;

import static io.restassured.RestAssured.given;
import io.restassured.response.Response;

import com.hurix.automation.utility.Log;

public class FetchDeviceIds {
	public static Response fetchDeviceIds(String deviceType,String consumerKey, String consumerSecret)
	{
		Response jsonResponse = null;
		try {

			Log.startTestCase("FetchDeviceIds");		

			Log.info("URL : "+"/DistributionServices/ext/api/books/deviceIds/"+deviceType+"");
			Log.info("consumerKey : "+consumerKey);
			Log.info("consumerSecret : "+consumerSecret);
			Log.info("deviceType : "+deviceType);
			
			jsonResponse = given()
					.auth()
					.oauth(consumerKey, consumerSecret, "", "")
					.header("Content-Type","application/json")
					//.formParam("bookId", UserBookID)
					.get("/DistributionServices/ext/api/books/deviceIds/"+deviceType+"");

			Log.info("FetchDeviceIds Response: "+jsonResponse.then().extract().response().prettyPrint());
		} catch (Exception exp) 
		{
			Log.fail(exp.getMessage());
			Log.fail("fails due to"+ exp.getCause());
		}
		Log.endTestCase("End");
		return jsonResponse;
	}
}
