package com.hurix.api.externalAPIs;

import static io.restassured.RestAssured.given;
import io.restassured.response.Response;
import com.hurix.automation.utility.Log;

public class Booktoc {
	public static Response booktoc(String bookreferenceid,String access_token)
	{		
		Response jsonResponse = null;
		try {
			Log.startTestCase("BookToc");
			Log.info("bookreferenceid : "+bookreferenceid);
			Log.info("access_token : "+access_token);
			
			Log.info("booktocURL : "+"/DistributionServices/ext/api/booktoc/"+bookreferenceid+"");
			jsonResponse = given()
					.auth()
					.oauth2(access_token)
					.get("/DistributionServices/ext/api/booktoc/"+bookreferenceid+"");
						
			Log.info("Booktoc Response: "+jsonResponse.then().extract().response().prettyPrint());
		} catch (Exception exp) 
		{
			Log.fail(exp.getMessage());
			Log.fail("fails due to"+ exp.getCause());
		}
		Log.endTestCase("End");
		return jsonResponse;		
	}
	
}
