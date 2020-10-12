package com.hurix.api.externalAPIs;

import static io.restassured.RestAssured.given;
import io.restassured.response.Response;
import com.hurix.automation.utility.Log;

public class FetchAllOrdersForInstitute {
	
	public static Response fetchAllOrdersForInstitute(Object instituteId,String consumerKey, String consumerSecret,String deviceID,String deviceType)
	{
		Response jsonResponse = null;
		try {
			
			Log.startTestCase("FetchAllOrdersForInstitute");		
		
			Log.info("URL : "+"/DistributionServices/ext/api/license/"+deviceID+"/"+deviceType+"/fetchAllOrdersForInstitute");
			Log.info("consumerKey : "+consumerKey);
			Log.info("consumerSecret : "+consumerSecret);
			Log.info("instituteId : "+instituteId);
			jsonResponse = given()
					.auth()
					.oauth(consumerKey, consumerSecret, "", "")
					.header("Content-Type","application/json")
					.header("instituteId",instituteId)
					.get("/DistributionServices/ext/api/license/"+deviceID+"/"+deviceType+"/fetchAllOrdersForInstitute");
										
			Log.info("FetchAllOrdersForInstitute Response: "+jsonResponse.then().extract().response().prettyPrint());
		} catch (Exception exp) 
		{
			Log.fail(exp.getMessage());
			exp.printStackTrace();
		}
		Log.endTestCase("End");
		return jsonResponse;
		}
}
