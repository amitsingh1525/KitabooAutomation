package com.hurix.api.externalAPIs;

import static io.restassured.RestAssured.given;
import io.restassured.response.Response;

import com.hurix.api.utility.Validation;
import com.hurix.automation.utility.Log;

public class SearchV2_OAuth {
	
	//public static String POSTsearchV2Path=""+com.hurix.api.utility.ExcelUtils.getbaseURI()+"/DistributionServices/ext/api/searchV2";

	private static String searchV2Body = "{\"searchText\":\"Native\",\"searchOn\":\"both\",\"searchType\":\"partial\",\"books\":[],\"searchField\":[\"author\",\"ISBN\",\"description\",\"bookTitle\",\"subject\",\"Publisher\",\"Book content\"],\"from\":0,\"size\":50,\"userId\":\""+com.hurix.api.runner.RestAssured.clientUserID+"\"}";
	public static Response searchV2_OAuth(String consumerKey, String consumerSecret)
	{
		Response jsonResponse = null;
		try {
			
			Log.startTestCase("SearchV2_OAuth");
			//System.out.println("RequestURL:" +POSTsearchV2Path);
			System.out.println("searchV2Body: "+searchV2Body);
			jsonResponse = given()
					.auth()
					.oauth(consumerKey, consumerSecret, "", "")
					.header("Content-Type","application/json")
					.body(searchV2Body)					
					.post("/DistributionServices/ext/api/searchV2");
			Validation.responseHeaderCodeValidation(jsonResponse, 200);
			Validation.responseCodeValidation1(jsonResponse, 200);
			Validation.responseTimeValidation(jsonResponse);
			Validation.responseKeyValidation_key(jsonResponse, "searchText");
			Validation.responseKeyValidation_key(jsonResponse, "bookTitle");
			Validation.responseKeyValidation_key(jsonResponse, "description");	
							
			Log.info("SearchV2_OAuth Response: "+jsonResponse.then().extract().response().prettyPrint());
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
