package com.hurix.api.externalAPIs;

import static io.restassured.RestAssured.given;
import io.restassured.response.Response;
import com.hurix.automation.utility.Log;

public class EpubStatus {
	
	public static Response epubStatus(String consumerKey, String consumerSecret,int epubID)
	{		
		Response jsonResponse = null;
		try {
			Log.startTestCase("EpubStatus");
			Log.info("epubID : "+epubID);
			Log.info("URL : "+"/DistributionServices/ext/api/epubStatus");
			jsonResponse = given()
					.auth()
					.oauth(consumerKey, consumerSecret, "", "")
					.header("Content-Type","application/json")
					.queryParam("id", epubID)
					.get("/DistributionServices/ext/api/epubStatus");
						
			Log.info("EpubStatus Response: "+jsonResponse.then().extract().response().prettyPrint());
		} catch (Exception exp) 
		{
			Log.fail(exp.getMessage());
			Log.fail("fails due to"+ exp.getCause());
		}
		Log.endTestCase("End");
		return jsonResponse;		
	}
}
