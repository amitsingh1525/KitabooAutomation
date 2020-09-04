package com.hurix.api.externalAPIs;

import static io.restassured.RestAssured.given;
import io.restassured.response.Response;

import org.apache.hc.core5.http.HttpStatus;

import com.hurix.api.utility.Validation;
import com.hurix.automation.utility.Log;

public class EpubStatus {
	public static Response epubStatus(String consumerKey, String consumerSecret,int epubID)
	{		
		Response jsonResponse = null;
		try {
			Log.startTestCase("EpubStatus_OAuth");
			
			jsonResponse = given()
					.auth()
					.oauth(consumerKey, consumerSecret, "", "")
					.header("Content-Type","application/json")
					.queryParam("id", epubID)
					.get("/DistributionServices/ext/api/epubStatus");
			/*Validation.responseHeaderCodeValidation(jsonResponse, HttpStatus.SC_OK);
			Validation.responseCodeValidation1(jsonResponse, HttpStatus.SC_OK);
			Validation.responseTimeValidation(jsonResponse);
			Validation.responseKeyValidation_key(jsonResponse, "status");
			Validation.responseKeyValidation_key(jsonResponse, "100");*/
			
			Log.info("EpubStatus_OAuth Response: "+jsonResponse.then().extract().response().prettyPrint());
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
