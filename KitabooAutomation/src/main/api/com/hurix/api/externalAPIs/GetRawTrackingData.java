package com.hurix.api.externalAPIs;

import static io.restassured.RestAssured.given;

import org.mortbay.jetty.HttpStatus;

import com.hurix.automation.utility.Log;

import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;

public class GetRawTrackingData{

	public static Response getRawTracking(long startDate, long endDate, String consumerKey, String consumerSecret){
		
		Response jsonResponse = null;
		try {
			Log.startTestCase("getRawTracking");
			jsonResponse = given().queryParam("startDate",startDate).queryParam("endDate", endDate)
					.auth()
					.oauth(consumerKey, consumerSecret, "", "")
					.get("/DistributionServices/ext/api/getRawTrackingData");
			
			Log.info("Response: "+jsonResponse.then().extract().response().prettyPrint());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Log.endTestCase("End");
		return jsonResponse;
		
	}
}
