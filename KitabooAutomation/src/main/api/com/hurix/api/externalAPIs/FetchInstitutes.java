package com.hurix.api.externalAPIs;

import static io.restassured.RestAssured.given;
import io.restassured.response.Response;

import com.hurix.automation.utility.Log;

public class FetchInstitutes {

	public static Response fetchInstitutes(String consumerKey, String consumerSecret)
	{
		Response jsonResponse = null;
		try {

			Log.startTestCase("FetchInstitutes");		

			Log.info("URL : "+"/DistributionServices/ext/api/institutes");
			Log.info("consumerKey : "+consumerKey);
			Log.info("consumerSecret : "+consumerSecret);

			jsonResponse = given()
					.auth()
					.oauth(consumerKey, consumerSecret, "", "")
					.get("/DistributionServices/ext/api/institutes");

			Log.info("FetchInstitutes Response: "+jsonResponse.then().extract().response().prettyPrint());
		} catch (Exception exp) 
		{
			Log.fail(exp.getMessage());
			Log.fail("fails due to"+ exp.getCause());
		}
		Log.endTestCase("End");
		return jsonResponse;
	}
}

