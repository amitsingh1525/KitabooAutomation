package com.hurix.api.hashAPIs;

import static io.restassured.RestAssured.given;
import io.restassured.response.Response;

import org.apache.http.HttpStatus;

import com.hurix.api.readerAPIs.MarkAsFavourite;
import com.hurix.api.runner.RestAssured;
import com.hurix.api.utility.MD5Genration;
import com.hurix.api.utility.Validation;
import com.hurix.automation.utility.Log;

public class UnMarkAsFavourite {
	
	public static Response unMarkAsFavourite()
	{

		Response jsonResponse = null;
		try {

			Log.startTestCase("UnMarkAsFavourite_Hash");
			System.out.println("Header HASH == " +MD5Genration.hashGenration(RestAssured.detail+"/DistributionServices/services/api/reader/user/123/IPAD/unMarkAsFavourite?bookid="+RestAssured.bookID1+""));
			jsonResponse = given()
					.header("usertoken",com.hurix.api.runner.RestAssured.userToken)	
					.header("hash",MD5Genration.hashGenration(RestAssured.detail+"/DistributionServices/services/api/reader/user/123/IPAD/unMarkAsFavourite?bookid="+RestAssured.bookID1+""))
					.get("/DistributionServices/services/api/reader/user/123/IPAD/unMarkAsFavourite?bookid="+RestAssured.bookID1+"");
			Validation.responseHeaderCodeValidation(jsonResponse, HttpStatus.SC_OK);
			Validation.responseCodeValidation1(jsonResponse, HttpStatus.SC_OK);
			Validation.responseTimeValidation(jsonResponse);
			Validation.responseKeyValidation_key(jsonResponse, "OK");
			

			Log.info("UnMarkAsFavourite Response: "+jsonResponse.then().extract().response().prettyPrint());
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
