package com.hurix.api.externalAPIs;

import static io.restassured.RestAssured.given;
import io.restassured.response.Response;
import com.hurix.api.utility.EpochTime;
import com.hurix.automation.utility.Log;

public class AddInstitute {
	
	public static Response addInstitute(String consumerKey, String consumerSecret)
	{
		Response jsonResponse = null;
		try {
			String AddInstituteBody="{\"name\":\"sales admin"+EpochTime.current()+"\",\"clientInstituteID\":"
					+ "\"test"+EpochTime.current()+"\",\"admin\":{\"firstName\":\"sales\",\"lastName\":\"admin\",\"email\":"
					+ "\"sales.admin"+EpochTime.current()+"@hurix.com\"}}";
			Log.startTestCase("AddInstitute");		
		
			Log.info("URL : "+"/DistributionServices/ext/api/institute");
			Log.info("consumerKey : "+consumerKey);
			Log.info("consumerSecret : "+consumerSecret);
			
			jsonResponse = given()
					.auth()
					.oauth(consumerKey, consumerSecret, "", "")
					.header("Content-Type","application/json")
					.body(AddInstituteBody)
					.post("/DistributionServices/ext/api/institute");
										
			Log.info("AddInstitute Response: "+jsonResponse.then().extract().response().prettyPrint());
		} catch (Exception exp) 
		{
			Log.fail(exp.getMessage());
			Log.fail("fails due to"+ exp.getCause());
		}
		Log.endTestCase("End");
		return jsonResponse;
	}
}

