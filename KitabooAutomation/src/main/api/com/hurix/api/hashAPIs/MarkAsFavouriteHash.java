package com.hurix.api.hashAPIs;

import static io.restassured.RestAssured.given;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import com.hurix.api.runner.RestAssured;
import com.hurix.api.utility.MD5Genration;
import com.hurix.api.utility.Validation;
import com.hurix.automation.utility.Log;

public class MarkAsFavouriteHash {

	public static Response markAsFavouriteHash(int bookID1,String userToken,String deviceID,String deviceType)
	{
		String POSTmarkAsFavouriteBody = "{\"bookId\":\""+bookID1+"\"}";
		Response jsonResponse = null;
		try {

			Log.startTestCase("MarkAsFavourite_Hash");
			System.out.println("Header HASH == " +MD5Genration.hashGenration(RestAssured.detail+"/DistributionServices/services/api/reader/user/"+deviceID+"/"+deviceType+"/markAsFavourite"));
			jsonResponse = given()
					.header("usertoken",userToken)	
					.header("hash",MD5Genration.hashGenration(RestAssured.detail+"/DistributionServices/services/api/reader/user/"+deviceID+"/"+deviceType+"/markAsFavourite"))
					.body(POSTmarkAsFavouriteBody)
					.post("/DistributionServices/services/api/reader/user/"+deviceID+"/"+deviceType+"/markAsFavourite");
			Validation.responseHeaderCodeValidation(jsonResponse, HttpStatus.SC_OK);
			Validation.responseCodeValidation1(jsonResponse, HttpStatus.SC_OK);
			Validation.responseTimeValidation(jsonResponse);
			//Validation.responseKeyValidation_key(jsonResponse, "totalCategories");			

			Log.info("MarkAsFavourite_Hash Response: "+jsonResponse.then().extract().response().prettyPrint());
		} catch (Exception exp) 
		{
			Log.fail(exp.getMessage());
			exp.printStackTrace();
		}
		Log.endTestCase("End");
		return jsonResponse;
	}

}
