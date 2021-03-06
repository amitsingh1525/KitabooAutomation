package com.hurix.api.externalAPIs;

import static io.restassured.RestAssured.given;
import io.restassured.response.Response;
import com.hurix.automation.utility.Log;

public class BookMetadata {
	
	//public static String GETbookMetadataPath=""+com.hurix.api.utility.ExcelUtils.getbaseURI()+"/DistributionServices/ext/api/book/"+com.hurix.api.runner.RestAssured.bookID1+"/bookMetadata";
	
	public static Response bookMetadata(String consumerKey, String consumerSecret,int bookID1)
	{
		Response jsonResponse = null;
		try {
		
			Log.startTestCase("bookMetadata");
			//System.out.println("RequestURL:" +GETbookMetadataPath);
			jsonResponse = given()
					.auth()
					.oauth(consumerKey, consumerSecret, "", "")
					.header("Content-Type","application/json")								
					.get("/DistributionServices/ext/api/book/"+bookID1+"/bookMetadata");
									
							
			Log.info("BookMetadata Response: "+jsonResponse.then().extract().response().prettyPrint());
		} catch (Exception exp) 
		{
			Log.fail(exp.getMessage());
			Log.fail("fails due to"+ exp.getCause());
		}
		Log.endTestCase("End");
		return jsonResponse;
	}

}
