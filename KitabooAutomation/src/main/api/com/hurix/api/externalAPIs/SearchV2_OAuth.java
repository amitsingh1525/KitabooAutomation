package com.hurix.api.externalAPIs;

import static io.restassured.RestAssured.given;
import io.restassured.response.Response;
import com.hurix.automation.utility.Log;

public class SearchV2_OAuth {
	
	//public static String POSTsearchV2Path=""+com.hurix.api.utility.ExcelUtils.getbaseURI()+"/DistributionServices/ext/api/searchV2";
	private static String searchV2Body;// = "{\"searchText\":\"Native\",\"searchOn\":\"both\",\"searchType\":\"partial\",\"books\":[],\"searchField\":[\"author\",\"ISBN\",\"description\",\"bookTitle\",\"subject\",\"Publisher\",\"Book content\"],\"from\":0,\"size\":50,\"userId\":\""+clientUserID+"\"}";
	
	public static Response searchV2_OAuth(String searchTEXT,String consumerKey, String consumerSecret,String clientUserID)
	{
		searchV2Body = "{\"searchText\":\""+searchTEXT+"\",\"searchOn\":\"both\",\"searchType\":\"partial\",\"books\":[],\"searchField\":[\"author\",\"ISBN\",\"description\",\"bookTitle\",\"subject\",\"Publisher\",\"Book content\"],\"from\":0,\"size\":50,\"userId\":\""+clientUserID+"\"}";
		Response jsonResponse = null;
		try {
			
			Log.startTestCase("SearchV2_OAuth");
			Log.info("searchTEXT : "+searchTEXT);
			Log.info("searchV2Body : "+searchV2Body);
			Log.info("URL : "+"/DistributionServices/ext/api/searchV2");
			Log.info("consumerKey : "+consumerKey);
			Log.info("consumerSecret : "+consumerSecret);
			//System.out.println("RequestURL:" +POSTsearchV2Path);
			System.out.println("searchV2Body: "+searchV2Body);
			jsonResponse = given()
					.auth()
					.oauth(consumerKey, consumerSecret, "", "")
					.header("Content-Type","application/json")
					.body(searchV2Body)					
					.post("/DistributionServices/ext/api/searchV2");
										
			Log.info("SearchV2_OAuth Response: "+jsonResponse.then().extract().response().prettyPrint());
		} catch (Exception exp) 
		{
			Log.fail(exp.getMessage());
			Log.fail("fails due to"+ exp.getCause());
		}
		Log.endTestCase("End");
		return jsonResponse;
	}
}
