package com.hurix.api.externalAPIs;

import static io.restassured.RestAssured.given;
import io.restassured.response.Response;

import com.hurix.automation.utility.Log;

public class Books_OAuth {
	//public static String consumerKey = ""+com.hurix.api.utility.ExcelUtils.getConsumer_key()+"";
	//public static String consumerSecret = ""+com.hurix.api.utility.ExcelUtils.getsecret_key()+"";
	
	//public static String GETbooks_OAuthPath=""+com.hurix.api.utility.ExcelUtils.getbaseURI()+"/DistributionServices/ext/api/books";
	

	public static Response books_OAuth(String consumerKey, String consumerSecret){
		//System.out.println("GETbooks_OAuthPath: " +GETbooks_OAuthPath);		
		Response jsonResponse = null;
		try {
			Log.startTestCase("books_OAuth");
			jsonResponse = given()
					.auth()
					.oauth(consumerKey, consumerSecret, "", "")
					.get("/DistributionServices/ext/api/books");
			
			Log.info("Books_OAuth Response: "+jsonResponse.then().extract().response().prettyPrint());
		} catch (Exception exp) 
		{
			Log.fail(exp.getMessage());
			Log.fail("fails due to"+ exp.getCause());
		}
		Log.endTestCase("End");
		return jsonResponse;
		
	}

}
