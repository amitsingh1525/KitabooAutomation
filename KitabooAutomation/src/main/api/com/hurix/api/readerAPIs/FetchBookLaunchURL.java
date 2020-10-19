package com.hurix.api.readerAPIs;

import static io.restassured.RestAssured.given;
import io.restassured.response.Response;

import com.hurix.automation.utility.Log;

public class FetchBookLaunchURL {
	
	public static Response fetchBookLaunchURL(String eBookId1,String userToken)
	{		
		Response jsonResponse = null;
		try {
			
			Log.startTestCase("FetchBookLaunchURL");
			Log.info("eBookId1 : "+eBookId1);
			Log.info("userToken : "+userToken);
			Log.info("URL : "+"/DistributionServices/services/api/reader/book/fetchBookLaunchURL");
			jsonResponse = given()
					.header("usertoken",userToken)		
					.formParam("eBookId", eBookId1)
					.get("/DistributionServices/services/api/reader/book/fetchBookLaunchURL");
			
			Log.info("FetchBookLaunchURL Response: "+jsonResponse.then().extract().response().prettyPrint());
		} catch (Exception exp) 
		{
			Log.fail(exp.getMessage());
			Log.fail("fails due to"+ exp.getCause());
		}
		Log.endTestCase("End");
		return jsonResponse;
	}
}
