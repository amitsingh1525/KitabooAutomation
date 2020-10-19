package com.hurix.api.externalAPIs;

import static io.restassured.RestAssured.given;
import io.restassured.response.Response;
import com.hurix.automation.utility.Log;

public class IngectEpub {

	public static String IngectEpubBody;	
	public static String third;
	public static Response ingectEpub_ext(String consumerKey, String consumerSecret,String filePath)
	{		
		Response jsonResponse = null;
		try {
			IngectEpubBody = "{\"filePath\":\""+filePath+"\"}";
			Log.startTestCase("IngectEpub");
			Log.info("filePath: "+IngectEpubBody);
			Log.info("consumerKey : "+consumerKey);
			Log.info("consumerSecret : "+consumerSecret);
			Log.info("filePath : "+filePath);
			Log.info("URL : "+"/DistributionServices/ext/api/book/ingestEpubFile");
			
			jsonResponse = given()
					.auth()
					.oauth(consumerKey, consumerSecret, "", "")
					.header("Content-Type","application/json")
					.body(IngectEpubBody)
					.post("/DistributionServices/ext/api/book/ingestEpubFile");
			
			Log.info("IngectEpub Response: "+jsonResponse.then().extract().response().prettyPrint());
		}
		catch (Exception exp) 
		{
			Log.fail(exp.getMessage());
			Log.fail("fails due to"+ exp.getCause());
		}
		Log.endTestCase("End");
		return jsonResponse;		
	}
}