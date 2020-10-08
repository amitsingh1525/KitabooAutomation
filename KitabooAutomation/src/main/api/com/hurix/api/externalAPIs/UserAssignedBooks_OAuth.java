package com.hurix.api.externalAPIs;

import static io.restassured.RestAssured.given;
import io.restassured.response.Response;
import com.hurix.automation.utility.Log;

public class UserAssignedBooks_OAuth {
	
	//public static String GETuserAssignedBooks_OAuthPath=""+com.hurix.api.utility.ExcelUtils.getbaseURI()+"/DistributionServices/ext/api/userAssignedBooks?clientUserId="+com.hurix.api.runner.RestAssured.clientUserID+"";
	
	public static Response userAssignedBooks_OAuth(String consumerKey, String consumerSecret,String clientUserID){
		//System.out.println("GETbooks_OAuthPath: " +GETuserAssignedBooks_OAuthPath);		
		Response jsonResponse = null;
		try {
			Log.startTestCase("userAssignedBooks_OAuth");
			jsonResponse = given()
					.auth()
					.oauth(consumerKey, consumerSecret, "", "")
					.get("/DistributionServices/ext/api/userAssignedBooks?clientUserId="+clientUserID+"");
			
			Log.info("UserAssignedBooks_OAuth Response: "+jsonResponse.then().extract().response().prettyPrint());
		} catch (Exception exp) 
		{
			Log.fail(exp.getMessage());
			exp.printStackTrace();
		}
		Log.endTestCase("End");
		return jsonResponse;
		
	}

	public static Response userAssignedBooks_OAuth_with_pagi(long startIndex, long endIndex,String consumerKey, String consumerSecret,String clientUserID){
		//System.out.println("GETuserAssignedBooks_OAuth_with_pagi: " +GETuserAssignedBooks_OAuthPath);		
		Response jsonResponse = null;
		try {
			Log.startTestCase("userAssignedBooks_OAuth_with_pagi");
			jsonResponse = given()
					.auth()
					.oauth(consumerKey, consumerSecret, "", "")
					.header("startIndex",startIndex)
					.header("endIndex", endIndex)
					.get("/DistributionServices/ext/api/userAssignedBooks?clientUserId="+clientUserID+"");
			
			Log.info("UserAssignedBooks_OAuth_with_pagi Response: "+jsonResponse.then().extract().response().prettyPrint());
		} catch (Exception exp) 
		{
			Log.fail(exp.getMessage());
			exp.printStackTrace();
		}
		Log.endTestCase("End");
		return jsonResponse;
		
	}

}
