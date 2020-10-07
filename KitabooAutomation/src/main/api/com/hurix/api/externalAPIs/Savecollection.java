package com.hurix.api.externalAPIs;

import static io.restassured.RestAssured.given;
import io.restassured.response.Response;
import com.hurix.api.utility.*;
import com.hurix.automation.utility.Log;

public class Savecollection {
	public static Response savecollection(long clientCollectionId,String consumerKey, String consumerSecret,int bookID1,int bookID2){
		
		Response jsonResponse = null;
		try {
			String Savecollection= "{\"collection\":{\"clientCollectionId\":\""+clientCollectionId+"\",\"title\":\"Hey World\",\"coverImage\":"
					+ "\"https://dictera-widgets-production.s3.amazonaws.com/data/distribution/thumbnails/1520.png?timestamp=1468401501\","
					+ "\"books\":[{\"id\":"+bookID1+"},{\"id\":"+bookID2+"}]}}";
			Log.startTestCase("Savecollection");
			Log.info("consumerKey : "+consumerKey);
			Log.info("consumerSecret : "+consumerSecret);
			Log.info("Savecollection_BODY : "+Savecollection);
			jsonResponse = given()
					.auth()
					.oauth(consumerKey, consumerSecret, "", "")
					.header("Content-Type","application/json")
					.body(Savecollection)
					.post("/DistributionServices/ext/api/collection");
			Validation.responseHeaderCodeValidation(jsonResponse, 200);
			Validation.responseCodeValidation1(jsonResponse, 200);
			Validation.responseTimeValidation(jsonResponse);
			
			Log.info("Savecollection Response: "+jsonResponse.then().extract().response().prettyPrint());
		} catch (Exception exp) 
		{
			Log.fail(exp.getMessage());
			exp.printStackTrace();
		}
		Log.endTestCase("End");
		return jsonResponse;
		
	}
}
