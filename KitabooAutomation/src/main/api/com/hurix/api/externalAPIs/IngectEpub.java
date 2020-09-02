package com.hurix.api.externalAPIs;

import static io.restassured.RestAssured.given;
import io.restassured.response.Response;

import com.hurix.api.utility.Validation;
import com.hurix.automation.utility.Log;

public class IngectEpub {
	
	private static String IngectEpubBody;
	public static Response ingectEpub_ext(String consumerKey, String consumerSecret,String isbnMeta)
	{	IngectEpubBody = "{\"filePath\":\"https://hurix-staging-content.s3.amazonaws.com/test/"+isbnMeta+".epub?AWSAccessKeyId=AKIA4PI2NOPJS3DPE77X&Expires=1603889267&Signature=yJt%2BAsW39SwEBzAbOJLlEhVpqoc%3D\"}";
			
		Response jsonResponse = null;
		try {
			Log.startTestCase("IngectEpub");
			jsonResponse = given()
					.auth()
					.oauth(consumerKey, consumerSecret, "", "")
					.header("Content-Type","application/json")
					.body(IngectEpubBody)
					.post("/DistributionServices/ext/api/book/ingestEpubFile");
			Validation.responseHeaderCodeValidation(jsonResponse, 200);
			Validation.responseCodeValidation1(jsonResponse, 200);
			Validation.responseTimeValidation(jsonResponse);
			Validation.responseKeyValidation_key(jsonResponse, "The request for the uploadEpub taken successfully.");
			
			Log.info("IngectEpub Response: "+jsonResponse.then().extract().response().prettyPrint());
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
