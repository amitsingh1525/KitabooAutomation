package com.hurix.api.hashAPIs;

import static io.restassured.RestAssured.given;

import org.apache.http.HttpStatus;

import io.restassured.response.Response;

import com.hurix.api.runner.RestAssured;
import com.hurix.api.utility.MD5Genration;
import com.hurix.api.utility.Validation;
import com.hurix.automation.utility.Log;

public class CategoriesV2Hash {
	
	public static Response categoriesV2Hash(String userToken,String deviceID,String deviceType)
	{

		Response jsonResponse = null;
		try {

			Log.startTestCase("CategoriesV2_Hash");
			System.out.println("Header HASH == " +MD5Genration.hashGenration(RestAssured.detail+"/DistributionServices/services/api/reader/books/"+deviceID+"/"+deviceType+"/books/v2/categories"));
			jsonResponse = given()
					.header("usertoken",userToken)	
					.header("hash",MD5Genration.hashGenration(RestAssured.detail+"/DistributionServices/services/api/reader/books/"+deviceID+"/"+deviceType+"/books/v2/categories"))
					.get("/DistributionServices/services/api/reader/books/"+deviceID+"/"+deviceType+"/books/v2/categories");
			Validation.responseHeaderCodeValidation(jsonResponse, HttpStatus.SC_OK);
			Validation.responseCodeValidation1(jsonResponse, HttpStatus.SC_OK);
			Validation.responseTimeValidation(jsonResponse);
			Validation.responseKeyValidation_key(jsonResponse, "totalCategories");
			

			Log.info("CategoriesV2_Hash Response: "+jsonResponse.then().extract().response().prettyPrint());
		} catch (Exception exp) 
		{
			Log.fail(exp.getMessage());
			Log.fail("fails due to"+ exp.getCause());
			
		}
		Log.endTestCase("End");
		return jsonResponse;
	}

}
