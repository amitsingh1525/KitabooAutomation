package com.hurix.api.externalAPIs;

import static io.restassured.RestAssured.given;
import io.restassured.response.Response;
import com.hurix.api.utility.Validation;
import com.hurix.automation.utility.Log;

public class IngestionStatus {
	
	public static Response ingestionStatus(String consumerKey, String consumerSecret,String isbning)
	{
		Response jsonResponse = null;
		try {
			Thread.sleep(8000);
			Log.startTestCase("IngestionStatus.isbning="+isbning+"");
					
			//System.out.println("RequestURL:" +GETbookMetadataPath);
			jsonResponse = given()
					.auth()
					.oauth(consumerKey, consumerSecret, "", "")
					.header("Content-Type","application/json")								
					.get("/DistributionServices/ext/api/book/ingestionStatus/"+isbning+"");
			
			//Response responseCode=jsonResponse.then().extract().path("responseCode");
			Validation.responseHeaderCodeValidation(jsonResponse , 200);
			Validation.responseCodeValidation1(jsonResponse , 200);			
			Validation.responseTimeValidation(jsonResponse);			
							
			Log.info("IngestionStatus.isbning="+isbning+" Response: "+jsonResponse.then().extract().response().prettyPrint());
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
