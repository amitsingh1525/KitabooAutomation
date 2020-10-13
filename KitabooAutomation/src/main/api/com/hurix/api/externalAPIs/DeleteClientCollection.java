package com.hurix.api.externalAPIs;

import static io.restassured.RestAssured.given;
import io.restassured.response.Response;
import com.hurix.automation.utility.Log;

public class DeleteClientCollection {
	
	public static Response deleteClientCollection(long clientCollectionId,String consumerKey, String consumerSecret)
	{		
		Response jsonResponse = null;
		try {
			Log.startTestCase("DeleteClientCollection");
			Log.info("clientCollectionId : "+clientCollectionId);
			jsonResponse = given()
					.auth()
					.oauth(consumerKey, consumerSecret, "", "")
					.header("Content-Type","application/json")
					.delete("/DistributionServices/ext/api/collection/client/"+clientCollectionId+"");		
			
			Log.info("DeleteClientCollection Response: "+jsonResponse.then().extract().response().prettyPrint());
		} catch (Exception exp) 
		{
			Log.fail(exp.getMessage());
			Log.fail("fails due to"+ exp.getCause());
		}
		Log.endTestCase("End");
		return jsonResponse;		
	}

}
