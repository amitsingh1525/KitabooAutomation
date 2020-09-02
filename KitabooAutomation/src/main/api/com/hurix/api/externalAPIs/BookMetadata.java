package com.hurix.api.externalAPIs;

import static io.restassured.RestAssured.given;
import io.restassured.response.Response;

import com.hurix.api.utility.Validation;
import com.hurix.automation.utility.Log;

public class BookMetadata {
	
	//public static String GETbookMetadataPath=""+com.hurix.api.utility.ExcelUtils.getbaseURI()+"/DistributionServices/ext/api/book/"+com.hurix.api.runner.RestAssured.bookID1+"/bookMetadata";
	
	public static Response bookMetadata(String consumerKey, String consumerSecret)
	{
		Response jsonResponse = null;
		try {
			
			Log.startTestCase("bookMetadata");
			//System.out.println("RequestURL:" +GETbookMetadataPath);
			jsonResponse = given()
					.auth()
					.oauth(consumerKey, consumerSecret, "", "")
					.header("Content-Type","application/json")								
					.get("/DistributionServices/ext/api/book/"+com.hurix.api.runner.RestAssured.bookID1+"/bookMetadata");
					Validation.responseHeaderCodeValidation(jsonResponse, 200);
					Validation.responseCodeValidation1(jsonResponse, 200);
					Validation.responseTimeValidation(jsonResponse);
					
							
			Log.info("BookMetadata Response: "+jsonResponse.then().extract().response().prettyPrint());
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
