package com.hurix.api.externalAPIs;

import static io.restassured.RestAssured.given;
import io.restassured.response.Response;

import com.hurix.api.utility.Validation;
import com.hurix.automation.utility.Log;

public class ListBooks_OAuth {
	

	public static Response GETListBooks_OAuth(String consumerKey, String consumerSecret)
	{			
		Response jsonResponse = null;
		try {
			Log.startTestCase("GETbooks_OAuth");
			jsonResponse = given()
					.auth()
					.oauth(consumerKey, consumerSecret, "", "")
					.header("Content-Type","application/x-www-form-urlencoded")
					.get("/DistributionServices/ext/api/ListBooks");
			Validation.responseHeaderCodeValidation(jsonResponse, 200);
			Validation.responseCodeValidation1(jsonResponse, 200);
			Validation.responseTimeValidation(jsonResponse);
			
			Log.info("GETListBooks_OAuth Response: "+jsonResponse.then().extract().response().prettyPrint());
		} catch (Exception exp) 
		{
			System.out.println(exp.getMessage());
			System.out.println(exp.getCause());
			exp.printStackTrace();
		}
		Log.endTestCase("End");
		return jsonResponse;
		
	}
	public static Response GETListBooks_OAuth_With_Pagi(long startIndex, long endIndex,String consumerKey, String consumerSecret)
	{
		Response jsonResponse = null;
		try {
			Log.startTestCase("GETListBooks_OAuth_With_Pagi");
			jsonResponse = given()
					.auth()
					.oauth(consumerKey, consumerSecret, "", "")
					.header("Content-Type","application/x-www-form-urlencoded")
					.header("startIndex",startIndex)
					.header("endIndex", endIndex)
					.post("/DistributionServices/ext/api/ListBooks");
			Validation.responseHeaderCodeValidation(jsonResponse, 200);
			Validation.responseCodeValidation1(jsonResponse, 200);
			Validation.responseTimeValidation(jsonResponse);
			
			Log.info("GETListBooks_OAuth_With_Pagi Response: "+jsonResponse.then().assertThat().statusCode(200).extract().response().prettyPrint());
		} catch (Exception exp) 
		{
			System.out.println(exp.getMessage());
			System.out.println(exp.getCause());
			exp.printStackTrace();
		}
		Log.endTestCase("End");
		return jsonResponse;
		
	}

}
