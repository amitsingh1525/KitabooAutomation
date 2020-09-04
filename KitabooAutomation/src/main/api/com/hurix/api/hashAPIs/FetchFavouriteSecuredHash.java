package com.hurix.api.hashAPIs;

import static io.restassured.RestAssured.given;
import io.restassured.response.Response;

import org.apache.http.HttpStatus;

import com.hurix.api.runner.RestAssured;
import com.hurix.api.utility.MD5Genration;
import com.hurix.api.utility.Validation;
import com.hurix.automation.utility.Log;

public class FetchFavouriteSecuredHash {
	
	public static Response fetchFavouriteSecuredHash(String userToken,String deviceID,String deviceType )
	{

		Response jsonResponse = null;
		try {

			Log.startTestCase("FetchFavouriteSecured_Hash");
			System.out.println("Header HASH == " +MD5Genration.hashGenration(RestAssured.detail+"/DistributionServices/services/api/reader/user/"+deviceID+"/"+deviceType+"/fetchFavouriteBooks"));
			jsonResponse = given()
					.header("usertoken",userToken)	
					.header("hash",MD5Genration.hashGenration(RestAssured.detail+"/DistributionServices/services/api/reader/user/"+deviceID+"/"+deviceType+"/fetchFavouriteBooks"))
					.get("/DistributionServices/services/api/reader/user/"+deviceID+"/"+deviceType+"/fetchFavouriteBooks");
			/*Validation.responseHeaderCodeValidation(jsonResponse, HttpStatus.SC_OK);
			Validation.responseCodeValidation1(jsonResponse, HttpStatus.SC_OK);
			Validation.responseTimeValidation(jsonResponse);
			Validation.responseKeyValidation_key(jsonResponse, ""+RestAssured.bookID1+"");*/
			

			Log.info("FetchFavouriteSecured_Hash Response: "+jsonResponse.then().extract().response().prettyPrint());
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
