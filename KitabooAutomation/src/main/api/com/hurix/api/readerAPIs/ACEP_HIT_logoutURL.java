package com.hurix.api.readerAPIs;

import static io.restassured.RestAssured.given;
import io.restassured.response.Response;

import com.hurix.automation.utility.Log;

public class ACEP_HIT_logoutURL {
	
	public static Response aCEP_HIT_logoutURL(String logoutURL)
	{
		Response jsonResponse = null;
		try {
			Log.startTestCase("ACEP_HIT_logoutURL");
			Log.info("logoutURL : " +logoutURL);
			
			jsonResponse = given()
					.get(""+logoutURL+"");
			
			Log.info("ACEP_HIT_logoutURL Response : "+jsonResponse.then().extract().response().prettyPrint());
		} catch (Exception exp) 
		{
			Log.fail(exp.getMessage());
			exp.printStackTrace();
		}
		Log.endTestCase("End");
		return jsonResponse;
	}

}
