package com.hurix.api.hashAPIs;

import static io.restassured.RestAssured.given;
import io.restassured.response.Response;

import org.apache.http.HttpStatus;

import com.hurix.api.runner.RestAssured;
import com.hurix.api.utility.MD5Genration;
import com.hurix.api.utility.Validation;
import com.hurix.automation.utility.Log;

public class RefreshBookListHash {
	
	public static Response refreshBookListHash(String userToken,String deviceID,String deviceType)
	{

		Response jsonResponse = null;
		try {

			Log.startTestCase("RefreshBookList_Hash");
			System.out.println("Header HASH == " +MD5Genration.hashGenration(RestAssured.detail+"/DistributionServices/services/api/reader/distribution/"+deviceID+"/"+deviceType+"/refreshBookList"));
			jsonResponse = given()
					.header("usertoken",userToken)	
					.header("hash",MD5Genration.hashGenration(RestAssured.detail+"/DistributionServices/services/api/reader/distribution/"+deviceID+"/"+deviceType+"/refreshBookList"))
					.get("/DistributionServices/services/api/reader/distribution/"+deviceID+"/"+deviceType+"/refreshBookList");
			Validation.responseHeaderCodeValidation(jsonResponse, HttpStatus.SC_OK);
			Validation.responseCodeValidation1(jsonResponse, HttpStatus.SC_OK);
			Validation.responseTimeValidation(jsonResponse);
			Validation.responseKeyValidation_key(jsonResponse, "collection");
			Validation.responseKeyValidation_key(jsonResponse, "category");
			Validation.responseKeyValidation_key(jsonResponse, "isbn");
			

			Log.info("RefreshBookList_Hash Response: "+jsonResponse.then().extract().response().prettyPrint());
		} catch (Exception exp) 
		{
			Log.fail(exp.getMessage());
			exp.printStackTrace();
		}
		Log.endTestCase("End");
		return jsonResponse;
	}


}
