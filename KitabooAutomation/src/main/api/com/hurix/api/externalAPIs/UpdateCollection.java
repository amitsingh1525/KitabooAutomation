package com.hurix.api.externalAPIs;

import static io.restassured.RestAssured.given;
import io.restassured.response.Response;
import com.hurix.automation.utility.Log;

public class UpdateCollection {
	
	public static Response updateCollection(long clientCollectionId,String consumerKey, String consumerSecret,int bookID1,int bookID2,int bookID3)
	{		
		Response jsonResponse = null;
		try {
			Log.startTestCase("UpdateCollection");
			String updateCollectionBODY = "{\"collection\":{\"clientCollectionId\":\""+clientCollectionId+"\",\"title\":"
					+ "\"Hey World\",\"coverImage\":\"https://dictera-widgets-production.s3.amazonaws.com/data/distribution/thumbnails/1520.png?timestamp=1468401501\","
					+ "\"books\":[{\"id\":"+bookID1+"},{\"id\":"+bookID2+"},{\"id\":"+bookID3+"}]}}";
			Log.info("updateCollectionBODY : "+updateCollectionBODY);
			jsonResponse = given()
					.auth()
					.oauth(consumerKey, consumerSecret, "", "")
					.header("Content-Type","application/json")
					.body(updateCollectionBODY)
					.put("/DistributionServices/ext/api/collection");
		
			
			Log.info("UpdateCollection Response: "+jsonResponse.then().extract().response().prettyPrint());
		} catch (Exception exp) 
		{
			Log.fail(exp.getMessage());
			Log.fail("fails due to"+ exp.getCause());
		}
		Log.endTestCase("End");
		return jsonResponse;		
	}
}
