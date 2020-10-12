package com.hurix.api.externalAPIs;

import static io.restassured.RestAssured.given;
import io.restassured.response.Response;
import com.hurix.automation.utility.Log;

public class Epubcontentextract {
	
	public static Response epubcontentextract(String isbn_reflow,String consumerKey, String consumerSecret)
	{		
		Response jsonResponse = null;
		try {
			Log.startTestCase("Epubcontentextract");
			Log.info("isbn_reflow : "+isbn_reflow);
			Log.info("consumerKey : "+consumerKey);
			Log.info("consumerSecret "+consumerSecret);
			jsonResponse = given()
					.auth()
					.oauth(consumerKey, consumerSecret, "", "")
					.header("Content-Type","application/json")
					.get("/DistributionServices/ext/api/epubcontentextract/"+isbn_reflow+"");
						
			Log.info("Epubcontentextract Response: "+jsonResponse.then().extract().response().prettyPrint());
		} catch (Exception exp) 
		{
			Log.fail(exp.getMessage());
			Log.fail("fails due to"+ exp.getCause());
			exp.printStackTrace();
		}
		Log.endTestCase("End");
		return jsonResponse;		
	}
}
