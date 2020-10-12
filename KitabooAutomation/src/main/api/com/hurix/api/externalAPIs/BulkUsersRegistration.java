package com.hurix.api.externalAPIs;

import static io.restassured.RestAssured.given;
import io.restassured.response.Response;

import com.hurix.api.utility.EpochTime;
import com.hurix.automation.utility.Log;

public class BulkUsersRegistration {

	static String bulkUsersRegistration=null;
	public static Response bulkUsersRegistration(String consumerKey, String consumerSecret,String email)
	{	
		bulkUsersRegistration ="["+email+"]";
		Response jsonResponse = null;
		try {
			Log.startTestCase("BulkUsersRegistration");
			Log.info("consumerKey : "+consumerKey);
			Log.info("consumerSecret : "+consumerSecret);
			Log.info("email : "+email);
			Log.info("URL : "+"/DistributionServices/ext/api/registerUser");
			Log.info("bulkUsersBODY : "+bulkUsersRegistration);
			jsonResponse = given()
					.auth()
					.oauth(consumerKey, consumerSecret, "", "")
					.header("Content-Type","application/json")			
					.body(bulkUsersRegistration)
					.post("/DistributionServices/ext/api/bulkUsersRegistration");

			Log.info("BulkUsersRegistration Response: "+jsonResponse.then().extract().response().prettyPrint());
		} catch (Exception exp) 
		{
			Log.fail(exp.getMessage());
		}
		Log.endTestCase("End");
		return jsonResponse;
	}
	
	public static Response bulkUsersRegistration_new(String consumerKey, String consumerSecret)
	{	
		bulkUsersRegistration ="[\"restAPI.test01"+EpochTime.current()+"@yopmail.com\",\"restAPI.test02"+EpochTime.current()+"@yopmail.com\","
				+ "\"restAPI.test02"+EpochTime.current()+"@yopmail.com\"]";
		Response jsonResponse = null;
		try {
			Log.startTestCase("BulkUsersRegistration_New");
			Log.info("consumerKey : "+consumerKey);
			Log.info("consumerSecret : "+consumerSecret);
			Log.info("URL : "+"/DistributionServices/ext/api/registerUser");
			Log.info("bulkUsersBODY : "+bulkUsersRegistration);
			jsonResponse = given()
					.auth()
					.oauth(consumerKey, consumerSecret, "", "")
					.header("Content-Type","application/json")			
					.body(bulkUsersRegistration)
					.post("/DistributionServices/ext/api/bulkUsersRegistration");

			Log.info("BulkUsersRegistration_New Response: "+jsonResponse.then().extract().response().prettyPrint());
		} catch (Exception exp) 
		{
			Log.fail(exp.getMessage());
			Log.fail("fails due to"+ exp.getCause());
		}
		Log.endTestCase("End");
		return jsonResponse;
	}
}
