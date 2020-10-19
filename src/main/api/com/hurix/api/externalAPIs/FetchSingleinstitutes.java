package com.hurix.api.externalAPIs;

import static io.restassured.RestAssured.given;
import io.restassured.response.Response;

import com.hurix.automation.utility.Log;

public class FetchSingleinstitutes {
	public static Response fetchSingleinstitutes(int InstitutesID,String consumerKey, String consumerSecret)
	{
		Response jsonResponse = null;
		try {

			Log.startTestCase("FetchSingleinstitutes");		

			Log.info("URL : "+"/DistributionServices/ext/api/institute/"+InstitutesID+"");
			Log.info("consumerKey : "+consumerKey);
			Log.info("consumerSecret : "+consumerSecret);

			jsonResponse = given()
					.auth()
					.oauth(consumerKey, consumerSecret, "", "")
					.get("/DistributionServices/ext/api/institute/"+InstitutesID+"");

			Log.info("FetchSingleinstitutes Response: "+jsonResponse.then().extract().response().prettyPrint());
		} catch (Exception exp) 
		{
			Log.fail(exp.getMessage());
			Log.fail("fails due to"+ exp.getCause());
		}
		Log.endTestCase("End");
		return jsonResponse;
	}	
}
