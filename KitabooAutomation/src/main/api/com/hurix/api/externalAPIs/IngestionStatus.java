package com.hurix.api.externalAPIs;

import static io.restassured.RestAssured.given;
import io.restassured.response.Response;
import com.hurix.automation.utility.Log;

public class IngestionStatus {
	
	public static Response ingestionStatus(String consumerKey, String consumerSecret,String isbning)
	{
		Response jsonResponse = null;
		try {
			Thread.sleep(8000);
			Log.startTestCase("IngestionStatus.isbn="+isbning+"");
					
			//System.out.println("RequestURL:" +GETbookMetadataPath);
			jsonResponse = given()
					.auth()
					.oauth(consumerKey, consumerSecret, "", "")
					.header("Content-Type","application/json")								
					.get("/DistributionServices/ext/api/book/ingestionStatus/"+isbning+"");		
			
												
			Log.info("IngestionStatus.isbning="+isbning+" Response: "+jsonResponse.then().extract().response().prettyPrint());
		} catch (Exception exp) 
		{
			Log.fail(exp.getMessage());
			Log.fail("fails due to"+ exp.getCause());
		}
		Log.endTestCase("End");
		return jsonResponse;
	}
	
}
