package com.hurix.api.externalAPIs;

import static io.restassured.RestAssured.given;
import io.restassured.response.Response;
import com.hurix.automation.utility.Log;

public class UpdateClientBookId {
	public static Response updateClientBookId(int bookID1,String clientBookID,String consumerKey, String consumerSecret)
	{
		Response jsonResponse = null;
		try {
			
			Log.startTestCase("UpdateClientBookId");
			//System.out.println("RequestURL:" +GETgetBookMetadataPath);
			jsonResponse = given()
					.auth()
					.oauth(consumerKey, consumerSecret, "", "")
					.header("Content-Type","application/json")	
					.queryParam("clientBookId", clientBookID)
					.put("/DistributionServices/ext/api/"+bookID1+"/updateClientBookId");
					
							
			Log.info("UpdateClientBookId Response: "+jsonResponse.then().extract().response().prettyPrint());
		} catch (Exception exp) 
		{
			Log.fail(exp.getMessage());
			Log.fail("fails due to"+ exp.getCause());
			//exp.printStackTrace();
		}
		Log.endTestCase("End");
		return jsonResponse;
	}
}
