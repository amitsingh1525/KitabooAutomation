package com.hurix.api.readerAPIs;

import static io.restassured.RestAssured.given;
import io.restassured.response.Response;

import com.hurix.automation.utility.Log;

public class FetchPreferredLocale {
	
public static String FetchPreferredLocale;
	
	public static Response fetchPreferredLocale()
	{
		
		Response jsonResponse = null;
		try {
			
			Log.startTestCase("fetchPreferredLocale");
		
			jsonResponse = given()
					.header("usertoken",com.hurix.api.runner.RestAssured.userToken)									
					.get("/DistributionServices/services/api/reader/user/123/IPAD/fetchPreferredLocale");
			
			Log.info("fetchPreferredLocale Response: "+jsonResponse.then().extract().response().prettyPrint());
		} catch (Exception exp) 
		{
			System.out.println(exp.getMessage());
			System.out.println(exp.getCause());
			exp.printStackTrace();
		}
		Log.endTestCase("End");
		return jsonResponse;
	}


}
