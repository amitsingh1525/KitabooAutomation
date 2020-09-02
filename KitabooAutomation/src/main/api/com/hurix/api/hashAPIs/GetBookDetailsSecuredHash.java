package com.hurix.api.hashAPIs;

import static io.restassured.RestAssured.given;
import io.restassured.response.Response;

import org.apache.http.HttpStatus;

import com.hurix.api.runner.RestAssured;
import com.hurix.api.utility.MD5Genration;
import com.hurix.api.utility.Validation;
import com.hurix.automation.utility.Log;

public class GetBookDetailsSecuredHash {
	
	public static Response getBookDetailsSecuredHash(long startDate)
	{

		Response jsonResponse = null;
		try {

			Log.startTestCase("GetBookDetailsSecuredHash");
			System.out.println("Header HASH == " +MD5Genration.hashGenration(RestAssured.detail+"/DistributionServices/services/api/reader/books/145644544/PC/books/v2/categories"));
			jsonResponse = given()
					.header("usertoken",com.hurix.api.runner.RestAssured.userToken)	
					.header("hash",MD5Genration.hashGenration(RestAssured.detail+"/DistributionServices/services/api/reader/distribution/123/pc/book/details?bookID="+RestAssured.ebookID1+"&type="+RestAssured.assetType+"&t="+startDate+""))
					.get("/DistributionServices/services/api/reader/distribution/123/pc/book/details?bookID="+RestAssured.ebookID1+"&type="+RestAssured.assetType+"&t="+startDate+"");
			Validation.responseHeaderCodeValidation(jsonResponse, HttpStatus.SC_OK);
			Validation.responseCodeValidation1(jsonResponse, HttpStatus.SC_OK);
			Validation.responseTimeValidation(jsonResponse);
			Validation.responseKeyValidation_key(jsonResponse, "bookId");
			Validation.responseKeyValidation_key(jsonResponse, "category");
			Validation.responseKeyValidation_key(jsonResponse, "archiveDate");
			

			Log.info("GetBookDetailsSecuredHash Response: "+jsonResponse.then().extract().response().prettyPrint());
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
