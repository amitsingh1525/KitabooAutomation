package com.hurix.api.externalAPIs;

import static io.restassured.RestAssured.given;

import org.apache.http.HttpStatus;

import io.restassured.response.Response;

import com.hurix.api.utility.Validation;
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
			Validation.responseHeaderCodeValidation(jsonResponse, HttpStatus.SC_OK);
			Validation.responseCodeValidation1(jsonResponse, HttpStatus.SC_OK);
			Validation.responseTimeValidation(jsonResponse);
			Validation.responseKeyValidation_key(jsonResponse, "isbn");
			Validation.responseKeyValidation_key(jsonResponse, "formats");
			Validation.responseKeyValidation_key(jsonResponse, "id");
			Log.info("UserAssignedBooks_OAuth Response: "+jsonResponse.then().extract().response().prettyPrint());
		} catch (Exception exp) 
		{
			System.out.println(exp.getMessage());
			System.out.println(exp.getCause());
			exp.printStackTrace();
		}
		Log.endTestCase("End");
		return jsonResponse;
		
	}

	public static Response userAssignedBooks_OAuth_with_pagi(long startIndex, long endIndex,String consumerKey, String consumerSecret){
		//System.out.println("GETuserAssignedBooks_OAuth_with_pagi: " +GETuserAssignedBooks_OAuthPath);		
		Response jsonResponse = null;
		try {
			Log.startTestCase("userAssignedBooks_OAuth_with_pagi");
			jsonResponse = given()
					.auth()
					.oauth(consumerKey, consumerSecret, "", "")
					.header("startIndex",startIndex)
					.header("endIndex", endIndex)
					.get("/DistributionServices/ext/api/userAssignedBooks?clientUserId="+com.hurix.api.runner.RestAssured.clientUserID+"");
			Validation.responseHeaderCodeValidation(jsonResponse, HttpStatus.SC_OK);
			Validation.responseCodeValidation1(jsonResponse, HttpStatus.SC_OK);
			Validation.responseTimeValidation(jsonResponse);
			Validation.responseKeyValidation_key(jsonResponse, "isbn");
			Validation.responseKeyValidation_key(jsonResponse, "formats");
			Validation.responseKeyValidation_key(jsonResponse, "id");
			Log.info("UserAssignedBooks_OAuth_with_pagi Response: "+jsonResponse.then().extract().response().prettyPrint());
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
