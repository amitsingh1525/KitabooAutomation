package com.hurix.api.externalAPIs;

import static io.restassured.RestAssured.given;
import io.restassured.response.Response;

import com.hurix.automation.utility.Log;

public class FetchScormDataForClass {
	public static Response fetchScormDataForClass(String consumerKey, String consumerSecret,int clientClassID)
	{		
		Response jsonResponse = null;
		try {
			Log.startTestCase("FetchScormDataForClass");
			
			jsonResponse = given()
					.auth()
					.oauth(consumerKey, consumerSecret, "", "")
					.header("Content-Type","application/json")
					.queryParam("client_Class_Id", clientClassID)
					.get("/DistributionServices/ext/api/fetchScormDataForClass");
						
			Log.info("FetchScormDataForClass Response: "+jsonResponse.then().extract().response().prettyPrint());
		} catch (Exception exp) 
		{
			Log.fail(exp.getMessage());
			Log.fail("fails due to"+ exp.getCause());
		}
		Log.endTestCase("End");
		return jsonResponse;		
	}
}
